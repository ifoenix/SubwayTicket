package android.subwayticket;

import android.os.Handler;
import android.subwayticket.bean.User;
import android.subwayticket.presenter.LoginPresenter;
import android.subwayticket.view.IView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements IView {

    private EditText username;
    private EditText password;
    Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
    }

    private void Login() {
        LoginPresenter loginPresenter = new LoginPresenter(this);
        User user = new User();
        user.setUsername(username.getText().toString());
        user.setPassword(password.getText().toString());
        loginPresenter.Login(user);
    }

    @Override
    public void setMessage(final String msg) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                username.setText(msg);
            }
        });
    }

    public void click(View view) {

        Login();
    }
}
