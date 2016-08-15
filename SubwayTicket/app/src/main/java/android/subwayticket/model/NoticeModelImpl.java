package android.subwayticket.model;

import android.subwayticket.bean.Notice;
import android.subwayticket.bean.NoticeListBean;
import android.subwayticket.constant.Constant;
import android.subwayticket.model.IModel.INoticeModel;
import android.subwayticket.net.NoticeService;
import android.util.Log;


import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Zach on 2016/8/8.
 */
public class NoticeModelImpl implements INoticeModel {
    private  static final String TAG="NoticeModelImpl";

    @Override
    public Observable<List<Notice>> getNotices() {
        Observable mObservable=Observable.create(new Observable.OnSubscribe<List<Notice>>(){
            @Override
            public void call(Subscriber<? super List<Notice>> subscriber) {
                Retrofit retrofit=new Retrofit.
                        Builder().
                        baseUrl(Constant.BASE_URL).
                        addConverterFactory(GsonConverterFactory.create()).
                        addCallAdapterFactory(RxJavaCallAdapterFactory.create()).
                        build();
                Call<NoticeListBean> call=retrofit.
                        create(NoticeService.class).getNotices();
                 List<Notice> noticeList=null;
                try {
                   noticeList=call.execute().body().getNoticeList();
                    subscriber.onNext(noticeList);
                    Log.e(TAG,noticeList.toString());
                }catch (Exception e){
                    e.printStackTrace();
                }
                subscriber.onCompleted();

            }
        });
        return mObservable;
    }
}
