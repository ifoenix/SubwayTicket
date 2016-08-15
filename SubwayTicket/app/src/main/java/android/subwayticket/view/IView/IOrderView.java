package android.subwayticket.view.IView;

import android.subwayticket.bean.Order;

import java.util.List;

/**
 * Created by Zach on 2016/8/13.
 */
public interface IOrderView {

    void showOrderInfo(List<Order> orderList);

}
