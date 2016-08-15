package android.subwayticket.model;

import android.subwayticket.bean.Order;
import android.subwayticket.bean.OrderListBean;
import android.subwayticket.constant.Constant;
import android.subwayticket.model.IModel.IOrderModel;
import android.subwayticket.net.OrderService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Zach on 2016/8/13.
 */
public class OrderImpl implements IOrderModel{
    @Override
    public Observable<List<Order>> getOrders() {

        Observable mObservable=Observable.create(new Observable.OnSubscribe<List<Order>>(){

            @Override
            public void call(Subscriber<? super List<Order>> subscriber) {
                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl(Constant.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();

                Call<OrderListBean> orderCall=retrofit.
                        create(OrderService.class).getOrderInfo();
                List<Order> orderList=null;
                try{
                    orderList=orderCall.execute().body().getOrderList();
                    subscriber.onNext(orderList);
                }catch (Exception e){
                    e.printStackTrace();
                }
                subscriber.onCompleted();
            }
        });

        return mObservable;
    }
}
