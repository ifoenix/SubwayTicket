package android.subwayticket.view.IView;


import android.subwayticket.bean.Notice;

import java.util.List;

/**
 * Created by Zach on 2016/8/8.
 */
public interface INoticeView {
    void showProgress();
    void hideProgress();
    void showNotices(List<Notice> noticeList);

}
