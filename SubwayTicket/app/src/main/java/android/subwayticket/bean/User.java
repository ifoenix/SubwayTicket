package android.subwayticket.bean;

/**
 * Created by Zach on 2016/8/12.
 */
public class User {
    private String userName;
    private String password;
    private String msg;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

}
