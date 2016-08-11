package android.subwayticket.presenter;

import android.subwayticket.bean.User;
import android.subwayticket.biz.LoginBiz;
import android.subwayticket.biz.bizimpl.LoginBizImpl;
import android.subwayticket.listener.OnLoginListener;
import android.subwayticket.view.IView;

/**
 * Created by Zach on 2016/8/11.
 */
public class LoginPresenter {
    IView iView;

    public LoginPresenter(IView iView) {
        this.iView = iView;
    }

    public void Login(User user) {
        LoginBiz loginBiz = new LoginBizImpl();
        loginBiz.Login(user, new OnLoginListener() {
            @Override
            public void onSucces(User user) {
                   iView.setMessage(user.getMsg());
            }

            @Override
            public void onFailed(String message) {

            }
        });
    }
}
