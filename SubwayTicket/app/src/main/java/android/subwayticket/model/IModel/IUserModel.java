package android.subwayticket.model.IModel;



import android.subwayticket.bean.User;

import rx.Observable;

/**
 * Created by Zach on 2016/8/9.
 */
public interface IUserModel {
    public Observable<User> login(String username, String password);
}
