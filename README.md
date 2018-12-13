# AndroidFrame
Android的框架之MVP +Retrofit+ RxJava + OkHttp

/**
 * Description：Retrofit管理器（Retrofit + RxJava + Okhttp）
 *
 * @author Created by: Li_Min
 * Time:2018/8/2
 */
public class BaseRetrofitManager {

    private UserService userService;
    private CommunityService communityService;
    private static SparseArray<Retrofit> sRetrofitManager = new SparseArray<>();


    private static volatile BaseRetrofitManager instance = null;

    private BaseRetrofitManager() {
    }

    public static BaseRetrofitManager getInstance() {
        //single chcekout
        if (null == instance) {
            synchronized (BaseRetrofitManager.class) {
                // double checkout
                if (null == instance) {
                    instance = new BaseRetrofitManager();
                }
            }
        }
        return instance;
    }


    public UserService baseService() {
        initRetrofit(BaseHttpUrl.MAIN_TYPE);
        return userService;
    }

    public CommunityService getCommunityService() {
        initRetrofit(BaseHttpUrl.COMMUNITY_TYPE);
        return communityService;
    }

    /**
     * 初始化Retrofit管理器配置
     *
     * @param hostType 服务器连接类型
     */
    private void initRetrofit(int hostType) {
        Retrofit retrofit = sRetrofitManager.get(hostType);
        if (retrofit == null) {
            // 初始化Retrofit配置
            Retrofit mRetrofit = new Retrofit.Builder()
                    .baseUrl(getHostType(hostType))
                    .client(getOkHttpClient(hostType))
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            sRetrofitManager.put(hostType, mRetrofit);
            switch (hostType) {
                // 主服务器地址接口信息配置
                case BaseHttpUrl.MAIN_TYPE:
                    userService = mRetrofit.create(UserService.class);
                    break;
                case BaseHttpUrl.COMMUNITY_TYPE:
                    communityService = mRetrofit.create(CommunityService.class);
                    break;
                default:
                    userService = mRetrofit.create(UserService.class);
                    break;
            }
        }

    }

    /**
     * 初始化okHttp配置
     *
     * @param hostType 服务器类型（1、用户 2、社区）
     * @return okHttp
     */
    private OkHttpClient getOkHttpClient(@BaseHttpUrl.isChekout int hostType) {
        // 初始化OkHttp配置
        OkHttpClient okHttpClient = null;
        switch (hostType) {
            case BaseHttpUrl.MAIN_TYPE:
                okHttpClient = new OkHttpClient.Builder()
                        .connectTimeout(20, TimeUnit.SECONDS)
                        .readTimeout(20, TimeUnit.SECONDS)
                        .writeTimeout(20, TimeUnit.SECONDS)
                        .addInterceptor(new LogInterceptor())
                        .addInterceptor(new CodeInterceptor())
                        .build();
                break;
            case BaseHttpUrl.COMMUNITY_TYPE:
                okHttpClient = new OkHttpClient.Builder()
                        .connectTimeout(20, TimeUnit.SECONDS)
                        .readTimeout(20, TimeUnit.SECONDS)
                        .writeTimeout(20, TimeUnit.SECONDS)
                        .addInterceptor(new ParameterInterceptor())
                        .addInterceptor(new LogInterceptor())
                        .addInterceptor(new CodeInterceptor())
                        .build();
                break;
            default:
                break;
        }
        return okHttpClient;
    }

    /**
     * 获取连接服务器地址
     *
     * @param hostType 服务器地址类型
     * @return hostUrl
     */
    private String getHostType(@BaseHttpUrl.isChekout int hostType) {
        String hostUrl;
        switch (hostType) {
            case BaseHttpUrl.MAIN_TYPE:
                // 用户服务器地址
                hostUrl = BaseHttpUrl.USER_SERVICE;
                break;
            case BaseHttpUrl.COMMUNITY_TYPE:
                // 社区服务器地址
                hostUrl = BaseHttpUrl.COMMUNITY_SERVICE;
                break;
            default:
                hostUrl = BaseHttpUrl.USER_SERVICE;
                break;

        }
        return hostUrl;
    }

    /**
     * Log日志拦截器
     */
    private static class LogInterceptor implements Interceptor {
        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();
            String bodyStr = bodyToString(request);
            LogUtils.json(this.getClass().getName(), String.format("请求参数 %s: body=   %s", URLDecoder.decode(URLDecoder.decode(String.valueOf(request.url()),"utf-8"),"utf-8"), URLDecoder.decode(URLDecoder.decode(bodyStr,"utf-8"),"utf-8")));

            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            if (response.body() != null) {
                ResponseBody body = response.peekBody(1024 * 1024);
                LogUtils.json(this.getClass().getName(), String.format(Locale.getDefault(), "返回数据 %s in %.1fms%n   %s", URLDecoder.decode(URLDecoder.decode(String.valueOf(response.request().url()),"utf-8"),"utf-8"), (t2 - t1) / 1e6d, body.string()));
            } else {
                Log.i(this.getClass().getName(), "body null");
                LogUtils.e("服务器数据异常" + request.url());

            }
            return response;

        }

        /**
         * 字符串输出
         *
         * @param request 请求数据
         * @return buffer
         */
        private static String bodyToString(final Request request) {

            try {
                final Request copy = request.newBuilder().build();
                final Buffer buffer = new Buffer();
                if (copy == null || copy.body() == null) {
                    return "";
                }
                Objects.requireNonNull(copy.body()).writeTo(buffer);
                return buffer.readUtf8();
            } catch (final IOException e) {
                return "did not work";
            }
        }
    }

    /**
     * 服务器返回code编码拦截器
     * 根据需求处理统一拦截
     * 比如token异常处理 与服务器定义好统一code编码
     */
    public static class CodeInterceptor implements Interceptor {

        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request request = chain.request();

            Response response = chain.proceed(request);
            ResponseBody body = response.peekBody(1024 * 1024);

            String bodyStr = body.string();
            int code;
            String msg;

            if (!TextUtils.isEmpty(bodyStr)) {
                BaseResult baseResult = GsonUtils.fromJson(bodyStr, BaseResult.class);
                if (null == baseResult) {
                    throw new BaseTransformerManager.ServerException();
                }
                code = baseResult.getCode();
                msg = baseResult.getMsg();
            } else {
                throw new BaseTransformerManager.ServerException();
            }

            switch (code) {
                case 0:
                    //code编码主要根据服务器返回识别
                    return response;
                case 200:
                    return response;
                case -7:
                    // 可以通过发送RxBus通知进入登录页面
                    RxBus.get().post(Constant.TOKEN);
                    throw new BaseTransformerManager.TokenException(code, msg);
                default:
                    throw new BaseTransformerManager.ServerException(code, msg);
            }
        }


    }

    /**
     * 参数拦截器
     */
    private class ParameterInterceptor implements Interceptor {

        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request request = chain.request();
            if (request.url().toString().startsWith(BaseHttpUrl.COMMUNITY_SERVICE)) {
                return chain.proceed(addCommunityParams(request, "这里设置应用识别标识（token）"));
            }
            return chain.proceed(request);
        }

        /**
         * 社区服务器参数拦截处理
         *
         * @param request 请求数据
         * @param token   token令牌
         * @return request
         */
        private Request addCommunityParams(Request request, String token) {
            // 将参数拼接到body里
            RequestBody builder = request.body();
            UserModel userModel = UserManagerUtils.getInstance().getUserModel();
            FormBody.Builder newBuilder = new FormBody.Builder();
            newBuilder.add("user",GsonUtils.toJson(userModel));
            newBuilder.add("token",token);

            if (builder instanceof FormBody) {
                FormBody formBody = (FormBody) builder;
                for (int i = 0; i <formBody.size(); i++) {
                    newBuilder.add(formBody.name(i),formBody.value(i));
                }
            }
            return request.newBuilder()
                    .method(request.method(),request.body())
                    .post(newBuilder.build())
                    .build();
        }
    }
}


/**
 * Description：RxJava线程调度器
 *
 * @author Created by: Li_Min
 * Time:2018/8/2
 */
public class BaseTransformerManager {

    /**
     * RxJava线程调度器（线程切换）
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<BaseResult<T>, T> defaultSchedulers() {
        return baseResultObservable -> baseResultObservable
                .map(new ServerResultFunc<T>())
                .onErrorResumeNext(new BaseFunction<T>())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 异常信息抓取器
     * @param <T>
     */
    private static class BaseFunction<T> implements Func1<Throwable,Observable<T>> {

        @Override
        public Observable<T> call(Throwable throwable) {
            return Observable.error(BaseException.getInstance().throwableUtils(throwable));
        }
    }

    /**
     * 服务器HttpCode编码识别器
     * @param <T>
     */
    private static class ServerResultFunc<T> implements Func1<BaseResult<T>, T> {

        @Override
        public T call(BaseResult<T> baseResult) {
            // 判断服务器code编码负数统一处理
            if (baseResult.getCode() < 0){
                throw new ServerException(baseResult.getCode(),baseResult.getMsg());
            }
            return baseResult.getData();
        }


    }

    /**
     * 服务器异常信息
     */
    public static class ServerException extends RuntimeException {

        private int code;
        private String msg;

        ServerException(){

        }

        ServerException(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    public static class TokenException extends RuntimeException {

        private int code;
        private String msg;

        TokenException(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

    }
}


/**
 * Description：自定义异常捕获及信息处理类
 *
 * @author Created by: Li_Min
 * Time:2018/8/2
 */
public class BaseExceptionManager {

    private static volatile BaseExceptionManager instance = null;

    private BaseExceptionManager(){}

    public static BaseExceptionManager getInstance(){
     //single chcekout
     if(null == instance){
        synchronized (BaseExceptionManager.class){
            // double checkout
            if(null == instance){
                instance = new BaseExceptionManager();
            }
        }
     }
     return instance;
    }

    /**
     * 异常信息处理
     * @param throwable 处理的异常信息
     * @return apiException 异常信息
     */
    public ApiException throwableUtils(Throwable throwable) {
        ApiException apiException;
        if (throwable instanceof EOFException || throwable instanceof ConnectException) {
            apiException = new ApiException(throwable, Error.HTTP_ERROR);
            apiException.setErrorMessage(Error.STR_HTTP_ERROR);
            LogUtils.e(this.getClass().getName(), "网络异常：" + apiException.getErrorMessage() + "\n异常编码：" + apiException.getCode() + "\n异常信息：" + apiException.getMessage());
            return apiException;
        } else if (throwable instanceof BaseTransformerManager.ServerException) {
            BaseTransformerManager.ServerException resultException = (BaseTransformerManager.ServerException) throwable;
            apiException = new ApiException(resultException, resultException.getCode());
            apiException.setErrorMessage(null == resultException.getMsg()? Error.STR_PARSE_ERROR : resultException.getMsg());
            LogUtils.e(this.getClass().getName(), "服务器异常：" + apiException.getErrorMessage() + "\n异常编码：" + apiException.getCode() + "\n异常信息：" + apiException.getMessage());
            return apiException;
        } else if (throwable instanceof BaseTransformerManager.TokenException){
            BaseTransformerManager.TokenException tokenException = (BaseTransformerManager.TokenException) throwable;
            apiException = new ApiException(tokenException,tokenException.getCode());
            apiException.setErrorMessage(tokenException.getMsg());
            LogUtils.e(this.getClass().getName(), "Token异常：" + apiException.getErrorMessage() + "\n异常编码：" + apiException.getCode() + "\n异常信息：" + apiException.getMessage());
            return apiException;
        }else if (throwable instanceof JsonParseException ||
                throwable instanceof JSONException ||
                throwable instanceof ParseException) {
            apiException = new ApiException(throwable, Error.PARSE_ERROR);
            apiException.setErrorMessage(Error.STR_PARSE_ERROR);
            LogUtils.e(this.getClass().getName(), "解析异常：" + apiException.getErrorMessage() + "\n异常编码：" + apiException.getCode() + "\n异常信息：" + apiException.getMessage());
            return apiException;
            //环信无网络判断
        } else if (throwable instanceof SocketException ||
                throwable instanceof UnknownHostException ||
                throwable instanceof ApiException) {
            apiException = new ApiException(throwable, Error.NO_NET_ERROR);
            apiException.setErrorMessage(Error.STR_NO_NET_ERROR);
            LogUtils.e(this.getClass().getName(), "连接异常：" + apiException.getErrorMessage() + "\n异常编码：" + apiException.getCode() + "\n异常信息：" + apiException.getMessage());
            return apiException;
        } else if (throwable instanceof SocketTimeoutException) {
            apiException = new ApiException(throwable, Error.TIME_OUT_ERROR);
            apiException.setErrorMessage(Error.STR_TIME_OUT_ERROR);
            LogUtils.e( this.getClass().getName(), "超时异常：" + apiException.getErrorMessage() + "\n异常编码：" + apiException.getCode() + "\n异常信息：" + apiException.getMessage());
            return apiException;
        } else if (throwable instanceof InterruptedException) {
            apiException = new ApiException(throwable, Error.REQUEST_INTERRUPTED_ERROR);
            apiException.setErrorMessage(Error.STR_REQUEST_INTERRUPTED_ERROR);
            LogUtils.e(this.getClass().getName(), "中断异常：" + apiException.getErrorMessage() + "\n异常编码：" + apiException.getCode() + "\n异常信息：" + apiException.getMessage());
            return apiException;
        } else {
            apiException = new ApiException(throwable, Error.UNKNOWN);
            apiException.setErrorMessage(Error.STR_UNKNOWN);
            LogUtils.e(this.getClass().getName(), "其他异常：" + apiException.getErrorMessage() + "\n异常编码：" + apiException.getCode() + "\n异常信息：" + apiException.getMessage());
            return apiException;
        }

    }

    /**
     * 自定义异常抓捕器
     */
    public static class ApiException extends Exception {

        private final Throwable throwable;
        private int code;
        private String errorMessage;

        ApiException(Throwable throwable, int code, String errorMessage) {
            this.throwable = throwable;
            this.code = code;
            this.errorMessage = errorMessage;
        }

        ApiException(Throwable throwable, int errorCode) {
            this.throwable = throwable;
            this.code = errorCode;
        }


        public Throwable getThrowable() {
            return throwable;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        String getErrorMessage() {
            return errorMessage;
        }

        private void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }

    /**
     * 异常协议信息
     */
    public class Error {
        /**
         * 未知错误
         */
        static final int UNKNOWN = 1000;
        /**
         * 解析错误
         */
        static final int PARSE_ERROR = 1001;
        /**
         * 协议出错
         */
        static final int HTTP_ERROR = 1003;

        /**
         * 无网络
         */
        static final int NO_NET_ERROR = 1004;
        /**
         * 请求超时
         */
        static final int TIME_OUT_ERROR = 1005;
        /**
         * 请求取消
         */
        static final int REQUEST_INTERRUPTED_ERROR = 1006;

        static final String STR_HTTP_ERROR = "服务器连接失败，请稍后连接";

        static final String STR_PARSE_ERROR = "服务器连接异常，请稍后连接";

        static final String STR_NO_NET_ERROR = "无网络连接，请检查您的网络";

        static final String STR_REQUEST_INTERRUPTED_ERROR = "服务器连接中断，请稍后连接";

        static final String STR_TIME_OUT_ERROR = "服务器连接超时，请稍后连接";

        static final String STR_UNKNOWN = "未知错误";

    }
}


