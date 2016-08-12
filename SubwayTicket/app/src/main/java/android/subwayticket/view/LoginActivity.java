package android.subwayticket.view;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.subwayticket.R;
import android.subwayticket.presenter.LoginPresenter;
import android.subwayticket.utils.BaseActivity;
import android.subwayticket.utils.FlatButton;
import android.subwayticket.view.IView.IUserView;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;



/**
 * Created by Liam on 2016/7/23.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener,IUserView {

    private TextView mTvForgotPass;
    private TextView mRegister;
    private FlatButton mBtLogin;
    private FlatButton mBtFindPass;
    private FlatButton mBtPhoneLogin;
    private FlatButton mBtCancel;
    private EditText mEtAccount;
    private EditText mEtPass;
    private Dialog setBottumDialog;
    private View mDialogView;
    private LoginPresenter mLoginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inintView();
        //初始化登陆按钮，为按钮添加事件
        inintLoginButton();
        mLoginPresenter=new LoginPresenter(this,LoginActivity.this);


    }

    private void inintLoginButton() {

        mBtLogin = (FlatButton) findViewById(R.id.bt_login_submit);
        mBtLogin.setText("登   陆");
        mBtLogin.setBackgroundColor(0xff0099cc);
        mBtLogin.setFocusColor(0xffffff);
        mBtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               executeLogin();
                Log.i("tag","登录成功！");

                    Log.i("tag","登录....！");
                    Intent loginIntent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(loginIntent);


            }
        });

    }

    private void inintView() {
        mTvForgotPass = (TextView) findViewById(R.id.tv_login_dialog);
        mRegister = (TextView) findViewById(R.id.tv_login_register);

        //初始化输入框
        mEtAccount = (EditText) findViewById(R.id.et_login_accout);
        mEtPass = (EditText) findViewById(R.id.et_login_pass);

        mTvForgotPass.setOnClickListener(this);
        mRegister.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login_dialog:
                showDialog();
                break;

            case R.id.bt_login_findpass:

                setBottumDialog.dismiss();
                Intent findpassIntent =new Intent(LoginActivity.this,FindPasswordActivity.class);
                startActivity(findpassIntent);
                break;
            case R.id.bt_login_phonelogin:

                setBottumDialog.dismiss();
                break;
            case R.id.bt_login_cancel:

                setBottumDialog.dismiss();
                break;
            case R.id.tv_login_register:
                Intent registerIntent =  new Intent();
                registerIntent.setClass(LoginActivity.this,RegisterActivity.class);
                startActivity(registerIntent);
                break;
        }
    }

    public void showDialog() {
        setBottumDialog = new AlertDialog.Builder(this).create();
        setBottumDialog.show();
        mDialogView = View.inflate(getApplicationContext(), R.layout.login_dialog, null);

        Window window = setBottumDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);  //此处可以设置dialog显示的位置

        setBottumDialog.getWindow().setContentView(mDialogView);
        WindowManager windowManager = this.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = setBottumDialog.getWindow().getAttributes();
        lp.width = display.getWidth();
        setBottumDialog.getWindow().setAttributes(lp);
        bindDialog();
    }

    private void bindDialog() {
        //无法登陆-初始化按钮
        mBtFindPass = (FlatButton) mDialogView.findViewById(R.id.bt_login_findpass);
        mBtPhoneLogin = (FlatButton) mDialogView.findViewById(R.id.bt_login_phonelogin);
        mBtCancel = (FlatButton) mDialogView.findViewById(R.id.bt_login_cancel);

        //为无法登陆列表按钮初始化事件
        mBtFindPass.setOnClickListener(this);
        mBtPhoneLogin.setOnClickListener(this);
        mBtCancel.setOnClickListener(this);

        mBtPhoneLogin.setBackgroundColor(0xff33b5e5);
        mBtPhoneLogin.setFocusColor(0xff9933CC);


        mBtFindPass.setBackgroundColor(0xff33b5e5);
        mBtFindPass.setFocusColor(0xff9933CC);


        mBtCancel.setBackgroundColor(0xff33b5e5);
        mBtCancel.setFocusColor(0xff9933CC);

    }

    @Override
    public void login(String userName, String password) {
        mLoginPresenter.userLogin(userName,password);
    }
    public void executeLogin(){
       String userName=mEtAccount.getText().toString();
       String password=mEtPass.getText().toString();
        login(userName,password);

    }
}
