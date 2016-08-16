package android.subwayticket.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lilingyong on 16/8/13.
 */
public class BuyTicketStation {
    private String mStartStation;
    private String mEndStation;
    private Double mStartPoint;
    private Double mEndPoint;
    @SerializedName("price")
    private float price;
    private String distance;
    @SerializedName("startN")
    private Double startN;
    @SerializedName("startY")
    private Double startY;
    @SerializedName("terminalN")
    private Double terminalN;
    @SerializedName("terminalY")
    private Double terminalY;

    public Double getStartN() {
        return startN;
    }

    public void setStartN(Double startN) {
        this.startN = startN;
    }

    public Double getStartY() {
        return startY;
    }

    public void setStartY(Double startY) {
        this.startY = startY;
    }

    public Double getTerminalN() {
        return terminalN;
    }

    public void setTerminalN(Double terminalN) {
        this.terminalN = terminalN;
    }

    public Double getTerminalY() {
        return terminalY;
    }

    public void setTerminalY(Double terminalY) {
        this.terminalY = terminalY;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getmStartStation() {
        return mStartStation;
    }

    public void setmStartStation(String mStartStation) {
        this.mStartStation = mStartStation;
    }

    public String getmEndStation() {
        return mEndStation;
    }

    public void setmEndStation(String mEndStation) {
        this.mEndStation = mEndStation;
    }

    public Double getmStartPoint() {
        return mStartPoint;
    }

    public void setmStartPoint(Double mStartPoint) {
        this.mStartPoint = mStartPoint;
    }

    public Double getmEndPoint() {
        return mEndPoint;
    }

    public void setmEndPoint(Double mEndPoint) {
        this.mEndPoint = mEndPoint;
    }
}
