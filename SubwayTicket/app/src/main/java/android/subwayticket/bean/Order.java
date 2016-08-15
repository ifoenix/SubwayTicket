package android.subwayticket.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Zach on 2016/7/27.
 */
public class Order implements Serializable{
    @SerializedName("id")
    private int id;
    @SerializedName("start")
    private String start;
    @SerializedName("terminal")
    private String terminal;
    @SerializedName("orderID")
    private String orderID;
    @SerializedName("price")
    private float price;



    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }



    public Order(int id, String start, String terminal, String orderID, float price) {
        this.id = id;
        this.start = start;
        this.terminal = terminal;
        this.orderID = orderID;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", start='" + start + '\'' +
                ", terminal='" + terminal + '\'' +
                ", orderID='" + orderID + '\'' +
                ", price=" + price +
                '}';
    }
}
