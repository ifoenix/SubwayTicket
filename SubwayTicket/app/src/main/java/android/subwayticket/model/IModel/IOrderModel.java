package android.subwayticket.model.IModel;

import android.subwayticket.bean.Order;

import java.util.List;

import rx.Observable;

/**
 * Created by Zach on 2016/8/13.
 */
public interface  IOrderModel {
   public  Observable<List<Order>> getOrders();
}
