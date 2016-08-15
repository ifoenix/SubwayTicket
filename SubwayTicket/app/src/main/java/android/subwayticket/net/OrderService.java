package android.subwayticket.net;

import android.subwayticket.bean.OrderListBean;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Zach on 2016/8/13.
 */
public interface OrderService {
    @GET("/subwayticket/ticketRecord.do")
//    @GET("/Subwayticket03/order.json")
    Call<OrderListBean> getOrderInfo();
}
