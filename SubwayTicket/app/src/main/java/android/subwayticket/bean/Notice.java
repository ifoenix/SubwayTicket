package android.subwayticket.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Charles on 2016/7/25.
 */
public class Notice implements Serializable{

    @SerializedName("noticeID")
    private  int noticeID;
    @SerializedName("noticeTitle")
    private String noticeTitle;
    @SerializedName("noticeContent")
    private String noticeContent;
    @SerializedName("createTime")
    private String createTime;

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public int getNoticeID() {
        return noticeID;
    }

    public void setNoticeID(int noticeID) {
        this.noticeID = noticeID;
    }


    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Notice(int noticeID, String noticeTitle, String noticeContent, String createTime) {
        this.noticeID = noticeID;
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "noticeID=" + noticeID +
                ", noticeTitle='" + noticeTitle + '\'' +
                ", noticeContent='" + noticeContent + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
