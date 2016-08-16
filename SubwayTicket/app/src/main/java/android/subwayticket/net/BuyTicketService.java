package android.subwayticket.net;

import android.subwayticket.bean.BuyTicketStation;
import android.subwayticket.bean.BuyTicketStationBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by lilingyong on 16/8/13.
 */
public interface BuyTicketService {
    /*
    * 获取站点坐标
    * */
    @FormUrlEncoded
    @POST("/subwayticket/getZuoBiao.do")
    Call<BuyTicketStation> getPoint(@Field("stationName1") String stationName1, @Field("stationName2") String stationName2);

    /*
    * 获取价格
    * */
    @GET("/subwayticket/getPrice.do")
    Call<BuyTicketStation> getPrice(@Query("distance") String distance);

    /*
    * 购票
    * */
    @GET("/subwayticket/buyticket.do")
    Call<BuyTicketStationBean> buyTicket(@Query("price") float price,@Query("userId") int userId);
}
