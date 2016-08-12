package android.subwayticket.presenter;

import android.content.Context;
import android.subwayticket.bean.User;
import android.subwayticket.model.IModel.IUserRegisterModel;
import android.subwayticket.model.UserRegisterImpl;
import android.subwayticket.view.IView.IUserRegisterView;
import android.util.Log;
import android.widget.Toast;



import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Zach on 2016/8/9.
 */
public class RegisterPresenter {
    private static final String TAG="RegisterPresenter";
    private IUserRegisterModel mRegisterModel;
    private IUserRegisterView mRegisterView;
    private Context context;

    public RegisterPresenter(IUserRegisterView mRegisterView, Context context) {
        this.mRegisterView = mRegisterView;
        this.context = context;
        mRegisterModel=new UserRegisterImpl();
    }

    public void userRegister(final String userName, final String password){
        mRegisterModel.register(userName,password).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(context,"注册完成!",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG,e+"");
                        Toast.makeText(context,"无法注册，请重新注册!",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNext(User user) {
                        Toast.makeText(context,"正在注册",Toast.LENGTH_LONG).show();
                        mRegisterView.register(userName,password);
                    }
                });

    }
}
