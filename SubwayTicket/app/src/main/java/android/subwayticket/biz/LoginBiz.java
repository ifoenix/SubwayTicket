package android.subwayticket.biz;

import android.subwayticket.bean.User;
import android.subwayticket.listener.OnLoginListener;

/**
 * Created by Zach on 2016/8/11.
 */
public interface LoginBiz {
    void Login(User user, OnLoginListener onLoginListener);
}
