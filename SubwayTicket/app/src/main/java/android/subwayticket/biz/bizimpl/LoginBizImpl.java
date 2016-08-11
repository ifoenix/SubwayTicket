package android.subwayticket.biz.bizimpl;

import android.subwayticket.bean.User;
import android.subwayticket.biz.LoginBiz;
import android.subwayticket.listener.OnLoginListener;

/**
 * Created by Zach on 2016/8/11.
 */
public class LoginBizImpl implements LoginBiz {

    @Override
    public void Login(User user, OnLoginListener onLoginListener) {
        //登陆请求网络
        if (user != null) {
            user.setMsg("登录成功");
            onLoginListener.onSucces(user);
        } else onLoginListener.onFailed("登录失败");
    }
}
