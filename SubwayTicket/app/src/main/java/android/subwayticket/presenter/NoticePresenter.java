package android.subwayticket.presenter;

import android.content.Context;
import android.subwayticket.bean.Notice;
import android.subwayticket.model.IModel.INoticeModel;
import android.subwayticket.model.NoticeModelImpl;
import android.subwayticket.view.IView.INoticeView;
import android.util.Log;


import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Zach on 2016/8/8.
 */
public class NoticePresenter {
    private  static final String TAG="NoticePresenter";
    private INoticeView mNoticeView;
    private INoticeModel mNoticeModel;
    private Context context;

    public NoticePresenter(INoticeView mNoticeView, Context context) {
        this.mNoticeView = mNoticeView;
        mNoticeModel=new NoticeModelImpl();
        this.context=context;
    }

    public void getNoticeData(){
        mNoticeView.showProgress();
        mNoticeModel.getNotices().
                observeOn(AndroidSchedulers.mainThread()).
                subscribeOn(Schedulers.io()).
                subscribe(new Subscriber<List<Notice>>() {
                    @Override
                    public void onCompleted() {
                        mNoticeView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG,e.getMessage());

                    }

                    @Override
                    public void onNext(List<Notice> noticeList) {
                        mNoticeView.showNotices(noticeList);
                    }
                });
    }


}
