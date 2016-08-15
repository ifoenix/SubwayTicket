package android.subwayticket.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Zach on 2016/8/8.
 */
public class  NoticeListBean {

    @SerializedName("Notices")
    private List<Notice> noticeList;

    public List<Notice> getNoticeList() {
        return noticeList;
    }

    public void setNoticeList(List<Notice> noticeList) {
        this.noticeList = noticeList;
    }


}
