package com.limin.myapplication3.model;

/**
 * Description
 *
 * @author Created by: Li_Min
 * Time:2018/8/2
 */
public class UserModel {

    /**
     * uid : 54625
     * mobile : 13155800935
     * password : 6bf6412845855e56efd219e59d031776
     * openid :
     * nickname : 李天成3333
     * sex : 1
     * regTime : 1508829139
     * headimg : http://community-static.27aichi.com/upload/topic/2018-03-05/3904c5af0e22581e23d2e5d044ef6037
     * descriptions :
     * birthday : 1125532800
     * isSafe : 0
     * equipment :
     * appLogin : true
     * token : eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyaWQiOnsidXNlcmlkIjo1NDYyNX0sInN1YiI6IjEzMTU1ODAwOTM1IiwiZXhwIjoxNTIxMTgzMjU3fQ.MLDdGCXsrZDWpzqIf3FrA3gsjGqMhCBmkroXi7YqGzg
     */

    private String uid;
    private String mobile;
    private String password;
    private String openid;
    private String nickname;
    private int sex;
    private long regTime;
    private String headimg;
    private String descriptions;
    private long birthday;
    private int isSafe;
    private boolean isStaff;
    private String equipment;
    private boolean appLogin;
    private String token;
    private String hxCustom;
    private int userType;
    private String userIdentity;
    private boolean isHyMember;

    public String getHxCustom() {
        return hxCustom;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public long getRegTime() {
        return regTime;
    }

    public void setRegTime(long regTime) {
        this.regTime = regTime;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public int getIsSafe() {
        return isSafe;
    }

    public void setIsSafe(int isSafe) {
        this.isSafe = isSafe;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public boolean isAppLogin() {
        return appLogin;
    }

    public void setAppLogin(boolean appLogin) {
        this.appLogin = appLogin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String  gethxCustom() {
        return hxCustom;
    }

    public void setHxCustom(String hxCustom) {
        this.hxCustom = hxCustom;
    }

    public boolean isStaff() {
        return isStaff;
    }

    public void setStaff(boolean staff) {
        isStaff = staff;
    }

    public String getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(String userIdentity) {
        this.userIdentity = userIdentity;
    }

    public boolean isHyMember() {
        return isHyMember;
    }

    public void setHyMember(boolean hyMember) {
        isHyMember = hyMember;
    }
}
