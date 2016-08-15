package android.subwayticket.bean;

/**
 * Created by Zach on 2016/8/12.
 */
public class User {
    @SerializedName("userID")
    private int userID;
    @SerializedName("userName")
    private String userName;
    @SerializedName("password")
    private String password;


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

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


    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

}
