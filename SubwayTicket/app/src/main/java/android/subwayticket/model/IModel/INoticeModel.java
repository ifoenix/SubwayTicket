package android.subwayticket.model.IModel;


import android.subwayticket.bean.Notice;

import java.util.List;

import rx.Observable;


/**
 * Created by Zach on 2016/8/5.
 */
public interface INoticeModel  {

    public Observable<List<Notice>> getNotices();

}
