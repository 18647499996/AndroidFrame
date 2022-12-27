package com.limin.myapplication3.utils;

import android.content.Context;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import androidx.annotation.RequiresApi;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;
import androidx.core.os.CancellationSignal;

import android.util.Base64;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;

import java.security.KeyStore;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2020/6/15
 */
public class FingerprintManagerUtils {

    private static final String ANDROID_KEY_STORE = "AndroidKeyStore";
    private static final String KEY_ALGORITHM = KeyProperties.KEY_ALGORITHM_AES;
    private static final String KEY_BLOCK_MODE = KeyProperties.BLOCK_MODE_CBC;
    private static final String KEY_PADDING = KeyProperties.ENCRYPTION_PADDING_PKCS7;
    private static final String TRANSFORMATION = KEY_ALGORITHM + "/" + KEY_BLOCK_MODE + "/" + KEY_PADDING;
    private final String KEY_ALIAS;
    private FingerprintManagerCompat fingerprintManagerCompat;



    private static volatile FingerprintManagerUtils instance = null;
    private CancellationSignal mCancelSignal;

    private FingerprintManagerUtils(String keyAlias) {
        KEY_ALIAS = keyAlias;
    }

    public static FingerprintManagerUtils getInstance(Context context) {
        //single chcekout
        if (null == instance) {
            synchronized (FingerprintManagerUtils.class) {
                // double checkout
                if (null == instance) {
                    instance = new FingerprintManagerUtils(context.getPackageName() + "fingerprint");

                }
            }
        }
        return instance;
    }

    public void init(Context context) {
        fingerprintManagerCompat = FingerprintManagerCompat.from(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void encrypt(){
        mCancelSignal = new CancellationSignal();
        FingerprintManagerCompat.CryptoObject cryptoObject = new FingerprintManagerCompat.CryptoObject(getEncryptCipher());
        fingerprintManagerCompat.authenticate(cryptoObject, 0, mCancelSignal, new FingerprintManagerCompat.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errMsgId, CharSequence errString) {
                super.onAuthenticationError(errMsgId, errString);
            }

            @Override
            public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
                super.onAuthenticationHelp(helpMsgId, helpString);
            }

            @Override
            public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                try {
                    Cipher cipher = result.getCryptoObject().getCipher();
                    byte[] bytes = cipher.doFinal("helloworld".getBytes());
                    //保存加密过后的字符串
//                    LogUtils.d("指纹信息：" + new String(bytes));
                    SPUtils.getInstance().put("hello", android.util.Base64.encodeToString(bytes, android.util.Base64.URL_SAFE));
                    LogUtils.d("加密指纹信息：" + android.util.Base64.encodeToString(bytes, android.util.Base64.URL_SAFE));
                    //保存用于做AES-CBC加密变换的初始向量数组
                    byte[] iv = cipher.getIV();
//                    LogUtils.d("IV信息：" + new String(iv));
                    SPUtils.getInstance().put("iv", android.util.Base64.encodeToString(iv, Base64.URL_SAFE));
                    LogUtils.d("加密IV：" + android.util.Base64.encodeToString(iv, Base64.URL_SAFE));

                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        },null);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void decrypt() {

        //保存用于做AES-CBC
        String ivStr = SPUtils.getInstance().getString("iv");
        byte[] iv = Base64.decode(ivStr, Base64.URL_SAFE);

        mCancelSignal = new CancellationSignal();
        FingerprintManagerCompat.CryptoObject object = new FingerprintManagerCompat.CryptoObject(getDecryptCipher(iv));
        fingerprintManagerCompat.authenticate(object, 0, mCancelSignal, new FingerprintManagerCompat.AuthenticationCallback() {
                    @Override
                    public void onAuthenticationError(int errMsgId, CharSequence errString) {
                        super.onAuthenticationError(errMsgId, errString);
                    }

                    @Override
                    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
                        super.onAuthenticationHelp(helpMsgId, helpString);
                    }

                    @Override
                    public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
                        try {

                            Cipher cipher = result.getCryptoObject().getCipher();
                            String text = SPUtils.getInstance().getString("hello");
                            byte[] input = Base64.decode(text, Base64.URL_SAFE);
                            byte[] bytes = cipher.doFinal(input);
                            //保存加密过后的字符串
                            LogUtils.d("TestFingerprintActivity", "解密的字符串为: " + new String(bytes));
                            SPUtils.getInstance().put("hello", text);

                            byte[] iv = cipher.getIV();
                            LogUtils.d("TestFingerprintActivity", "解密iv:" + Base64.encodeToString(iv, Base64.URL_SAFE));

                            //保存用于做AES-CBC
//                            LogUtils.d("解密成功");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onAuthenticationFailed() {
                        super.onAuthenticationFailed();
                    }
                }, null);

       /* try {
            String text = SharePreferenceTool.getInstance().getStr("hello");
            byte[] input = Base64.decode(text, Base64.URL_SAFE);

            //保存用于做AES-CBC
            String ivStr = SharePreferenceTool.getInstance().getStr("iv");
            byte[] iv = Base64.decode(ivStr, Base64.URL_SAFE);

            Cipher cipher = mKeyGenTool.getDecryptCipher(iv);
            byte[] output = cipher.doFinal(input);

            Log.d("TestFingerprintActivity", "加密后的字符串为: " + new String(output));
            Toast.makeText(this, "解密成功", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public Cipher getEncryptCipher() {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, getKey());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cipher;
    }

    /**
     * 获取解密的cipher
     *
     * @param initializeVector 加密cipher的一些参数
     *                         包括initialize vector(AES加密中 以CBC模式加密需要一个初始的数据块，解密时同样需要这个初始块)
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public Cipher getDecryptCipher(byte[] initializeVector) {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(TRANSFORMATION);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(initializeVector);
            cipher.init(Cipher.DECRYPT_MODE, getKey(), ivParameterSpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cipher;
    }

    /**
     * 获取key，首先从秘钥库中根据别名获取key，如果秘钥库中不存在，则创建一个key，并存入秘钥库中
     *
     * @return
     * @throws Exception
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private SecretKey getKey() throws Exception {
        SecretKey secretKey = null;
        KeyStore keyStore = KeyStore.getInstance(ANDROID_KEY_STORE);
        keyStore.load(null);
        if (keyStore.isKeyEntry(KEY_ALIAS)) {
            KeyStore.SecretKeyEntry secretKeyEntry = (KeyStore.SecretKeyEntry) keyStore.getEntry(KEY_ALIAS, null);
            secretKey = secretKeyEntry.getSecretKey();
        } else {
            secretKey = createKey();
        }

        return secretKey;
    }


    /**
     * 在Android中，key的创建之后必须存储在秘钥库才能使用
     *
     * @return
     * @throws Exception
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private SecretKey createKey() throws Exception {
        //在创建KeyGenerator的时候，第二个参数指定provider为AndroidKeyStore，这样创建的key就会被存放在这个秘钥库中
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM, ANDROID_KEY_STORE);
        KeyGenParameterSpec spec = new KeyGenParameterSpec
                .Builder(KEY_ALIAS, KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                .setBlockModes(KEY_BLOCK_MODE)
                .setEncryptionPaddings(KEY_PADDING)
                //这个设置为true，表示这个key必须是通过了用户认证才可以使用
                .setUserAuthenticationRequired(true)
                .build();
        keyGenerator.init(spec);
        return keyGenerator.generateKey();
    }

}
