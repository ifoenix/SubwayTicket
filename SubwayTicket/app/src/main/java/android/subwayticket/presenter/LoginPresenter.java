package android.subwayticket.presenter;

import android.content.Context;
import android.subwayticket.bean.Order;
import android.subwayticket.bean.User;
import android.subwayticket.model.IModel.IOrderModel;
import android.subwayticket.model.IModel.IUserModel;
import android.subwayticket.model.OrderImpl;
import android.subwayticket.model.UserImpl;
import android.subwayticket.view.IView.IOrderView;
import android.subwayticket.view.IView.IUserView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Zach on 2016/8/9.
 */
public class LoginPresenter {
    private IUserModel mUserModel;
    private IUserView mUserView;
    public  Context context;


    public LoginPresenter(IUserView mUserView, Context context) {
        this.mUserView = mUserView;
        mUserModel=new UserImpl();
        this.context=context;
    }

    public void userLogin(final String userName, final String password) {
        mUserModel.login(userName, password).
                observeOn(AndroidSchedulers.mainThread()).
                subscribeOn(Schedulers.io()).
                subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("tag", e + "");
                        Toast.makeText(context, e + "", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNext(User user) {

                        Toast.makeText(context, user.toString(), Toast.LENGTH_LONG).show();
                        mUserView.login(userName, password);


                    }
                });


    }
}
