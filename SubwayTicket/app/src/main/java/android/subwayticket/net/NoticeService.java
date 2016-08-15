package android.subwayticket.net;


import android.subwayticket.bean.NoticeListBean;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Zach on 2016/8/8.
 */
public interface NoticeService {
    @GET("/subwayticket/showAll.do")
    Call<NoticeListBean> getNotices();
}
