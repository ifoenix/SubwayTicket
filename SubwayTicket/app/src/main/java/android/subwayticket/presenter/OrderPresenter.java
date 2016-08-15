package android.subwayticket.presenter;

import android.content.Context;
import android.subwayticket.bean.Order;
import android.subwayticket.model.IModel.IOrderModel;
import android.subwayticket.model.OrderImpl;
import android.subwayticket.view.IView.IOrderView;
import android.util.Log;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Zach on 2016/8/13.
 */
public class OrderPresenter {

    private static  final  String TAG="OrderPresenter";
    private IOrderModel mOrderModel;
    private IOrderView mOrderView;
    private Context context;

    public OrderPresenter(IOrderView mOrderView,Context context) {
        this.mOrderView = mOrderView;
        this.context=context;
        mOrderModel=new OrderImpl();
    }

    public void getOrdersData(){
//        mOrderView.showProgress();
        mOrderModel.getOrders()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Order>>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG,"加载完成！！");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG,e+"");
                    }

                    @Override
                    public void onNext(List<Order> orderList) {
                        mOrderView.showOrderInfo(orderList);
                    }
                });
    }
}
