package android.subwayticket.model;

import android.subwayticket.bean.BuyTicketStation;

import android.subwayticket.bean.BuyTicketStationBean;
import android.subwayticket.constant.Constant;
import android.subwayticket.model.IModel.IBuyTicketStationModel;
import android.subwayticket.net.BuyTicketService;
import android.subwayticket.utils.IbuyTicket;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;


/**
 * Created by lilingyong on 16/8/13.
 */
public class BuyTicketStationImpl implements IBuyTicketStationModel{

    /*
    * 通过起始站和终点站名称获取坐标,通过坐标调用百度地图API测算出距离
    * 再通过把距离赋予后台计算出价格,再获取价格
    * */
    @Override
    public Observable<BuyTicketStation> getStartAndEndPoint(final String stationName1, final String stationName2) {
        Observable observable = Observable.create(new Observable.OnSubscribe<BuyTicketStation>() {
            @Override
            public void call(Subscriber<? super BuyTicketStation> subscriber) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Constant.LiamBase_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
                Call<BuyTicketStation> buyTicketStationBeanCall = retrofit.create(BuyTicketService.class).getPoint(stationName1, stationName2);
                BuyTicketStation buyticketstation = new BuyTicketStation();
                BuyTicketStation buyTicketStationPrice = new BuyTicketStation();
                try {
                    buyticketstation =  buyTicketStationBeanCall.execute().body();
                    Retrofit getpriceretrofit = new Retrofit.Builder()
                            .baseUrl(Constant.LiamBase_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();
                    LatLng latLng1 = new LatLng(buyticketstation.getStartN(),buyticketstation.getStartY());
                    LatLng latLng2 = new LatLng(buyticketstation.getTerminalN(),buyticketstation.getTerminalY());
                    double distance = DistanceUtil.getDistance(latLng1, latLng2);
                    Call<BuyTicketStation> price = getpriceretrofit.create(BuyTicketService.class).getPrice(distance+"");
                    buyTicketStationPrice = price.execute().body();


                } catch (IOException e) {
                    e.printStackTrace();
                }
                subscriber.onNext(buyTicketStationPrice);
                subscriber.onCompleted();
            }
        });
        return observable;
    }

    /*
    * 获取价格
    * 在本项目中可无
    *
    * */
    @Override
    public Observable<BuyTicketStation> getPrice(final String distance) {
        Observable observable = Observable.create(new Observable.OnSubscribe<BuyTicketStation>() {
            @Override
            public void call(Subscriber<? super BuyTicketStation> subscriber) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Constant.LiamBase_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
                Call<BuyTicketStation> price = retrofit.create(BuyTicketService.class).getPrice(distance);
                BuyTicketStation buyticketstation = new BuyTicketStation();
                try {

                    buyticketstation = price.execute().body();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                subscriber.onNext(buyticketstation);
                subscriber.onCompleted();
            }
        });
        return observable;
    }

    /*
    * 把价格和userID传到后台进行购票
    * */
    @Override
    public Observable<BuyTicketStation> buyTicket(final float price,final int userId) {
        Observable observable = Observable.create(new Observable.OnSubscribe<BuyTicketStation>() {
            @Override
            public void call(Subscriber<? super BuyTicketStation> subscriber) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Constant.LiamBase_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
                Call<BuyTicketStationBean> call = retrofit.create(BuyTicketService.class).buyTicket(price,userId);
                BuyTicketStation buyTicketStation = new BuyTicketStation();
                try {
                     buyTicketStation= call.execute().body().getBuyTicketStation();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                subscriber.onNext(buyTicketStation);
                subscriber.onCompleted();
            }
        });
        return observable;
    }
}
