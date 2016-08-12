package android.subwayticket.model;



import android.subwayticket.bean.User;
import android.subwayticket.bean.UserBean;
import android.subwayticket.model.IModel.IUserRegisterModel;
import android.subwayticket.net.RegisterService;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Zach on 2016/8/9.
 */
public class UserRegisterImpl implements IUserRegisterModel {

    @Override
    public Observable<User> register(final String userName, final String password) {
        Observable<User> mRegisterObserable=Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {
                Retrofit retrofit=new Retrofit.Builder().
                        baseUrl("http://192.168.1.146:8080").
                        addConverterFactory(GsonConverterFactory.create()).
                        addCallAdapterFactory(RxJavaCallAdapterFactory.create()).
                        build();
                Call<UserBean> registerCall=retrofit.create(RegisterService.class).
                        registerService(userName,password);
                User user=new User();
                try{
                    user=registerCall.execute().body().getUser();

                    subscriber.onNext(user);
                }catch (Exception e){
                    e.printStackTrace();
                }

                subscriber.onCompleted();



            }
        });
        return mRegisterObserable;
    }
}
