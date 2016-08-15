package android.subwayticket.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Zach on 2016/8/10.
 */
public class OrderListBean {
    @SerializedName("Orders")
    private List<Order> orderList;

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
