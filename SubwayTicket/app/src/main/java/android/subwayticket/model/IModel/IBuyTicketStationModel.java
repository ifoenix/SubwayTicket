package android.subwayticket.model.IModel;


import android.subwayticket.bean.BuyTicketStation;

import rx.Observable;


/**
 * Created by lilingyong on 16/8/13.
 */
public interface IBuyTicketStationModel {
    public Observable<BuyTicketStation> getStartAndEndPoint(final String startstation, final String endstation);
    public Observable<BuyTicketStation> getPrice(final String distance);
    public Observable<BuyTicketStation> buyTicket(final float price,final int userId);

}
