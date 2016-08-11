package android.subwayticket.listener;

import android.subwayticket.bean.User;

/**
 * Created by Zach on 2016/8/11.
 */
public interface OnLoginListener {
    void onSucces(User user);
    void onFailed(String message);
}
