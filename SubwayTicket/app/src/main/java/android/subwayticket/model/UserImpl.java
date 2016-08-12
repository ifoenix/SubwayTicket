package android.subwayticket.model;



import android.subwayticket.bean.User;
import android.subwayticket.bean.UserBean;
import android.subwayticket.model.IModel.IUserModel;
import android.subwayticket.net.LoginService;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Zach on 2016/8/9.
 */
public class UserImpl implements IUserModel {
    private static final String TAG="UserImpl";
    @Override
    public Observable<User> login(final String userName, final String password) {
       Observable mUserObservable=Observable.create(new Observable.OnSubscribe<User>() {

           @Override
           public void call(Subscriber<? super User> subscriber) {
               Retrofit retrofit=new Retrofit.Builder().
                       baseUrl("http://192.168.1.146:8080").
                       addConverterFactory(GsonConverterFactory.create()).
                       addCallAdapterFactory(RxJavaCallAdapterFactory.create()).
                       build();

               Call<UserBean> userCall=retrofit.
                       create(LoginService.class).
                       getUser(userName,password);
               User user=new User();
               try{
                   user=userCall.execute().body().getUser();

                   subscriber.onNext(user);
               }catch (Exception e){
                   e.printStackTrace();
               }

               subscriber.onCompleted();
           }
       });

        return mUserObservable;
    }
}
