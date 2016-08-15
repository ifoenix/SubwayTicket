package android.subwayticket.net;



import android.subwayticket.bean.User;
import android.subwayticket.bean.UserBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Zach on 2016/8/9.
 */
public interface LoginService {
    @FormUrlEncoded()
    @POST("/subwayticket/login.do")

    Call<User> getUser(@Field("userName") String userName, @Field("password") String password);


}
