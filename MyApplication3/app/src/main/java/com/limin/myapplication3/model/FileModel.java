package com.limin.myapplication3.model;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2020/9/14
 */
public class FileModel {

    private String mFilePath;
    private String mFileName;
    private String mSize;
    private String mDate;
    private int section;
    private int msgId;
    private String mUserName;
    private int fileType;


    public FileModel(String mFilePath, String mFileName, String mSize, String mDate, int msgId, int fileType) {
        this.mFilePath = mFilePath;
        this.mFileName = mFileName;
        this.mSize = mSize;
        this.mDate = mDate;
        this.msgId = msgId;
        this.fileType = fileType;
    }

    public String getmFilePath() {
        return mFilePath;
    }

    public void setmFilePath(String mFilePath) {
        this.mFilePath = mFilePath;
    }

    public String getmFileName() {
        return mFileName;
    }

    public void setmFileName(String mFileName) {
        this.mFileName = mFileName;
    }

    public String getmSize() {
        return mSize;
    }

    public void setmSize(String mSize) {
        this.mSize = mSize;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public String getmUserName() {
        return mUserName;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    @Override
    public String toString() {
        return "FileModel{" +
                "mFilePath='" + mFilePath + '\'' +
                ", mFileName='" + mFileName + '\'' +
                ", mSize='" + mSize + '\'' +
                ", mDate='" + mDate + '\'' +
                ", section=" + section +
                ", msgId=" + msgId +
                ", mUserName='" + mUserName + '\'' +
                '}';
    }
}
