package android.subwayticket.view;

import android.content.Intent;
import android.os.Bundle;
import android.subwayticket.R;
import android.subwayticket.presenter.RegisterPresenter;
import android.subwayticket.utils.BaseActivity;
import android.subwayticket.utils.FlatButton;
import android.subwayticket.view.IView.IUserRegisterView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jude.swipbackhelper.SwipeBackHelper;
import com.rengwuxian.materialedittext.MaterialEditText;

import cn.smssdk.SMSSDK;

public class RegisterActivity extends BaseActivity implements IUserRegisterView {

    private String APP_KEY = "152da7b9f0db3";
    private String APP_SECRECT = "fab681f85a7370ceeb9164a98e3193de";
    private boolean mWarn;
    private SMSSDK mSmssdk;

    private FlatButton mBtRegister;
    private MaterialEditText mEtAccount;
    private MaterialEditText mEtPass1;
    private MaterialEditText mEtPass2;


    private String account;
    private String pass1;
    private String pass2;
    private RegisterPresenter mRegisterPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mSmssdk = new SMSSDK();
        mSmssdk.initSDK(this, APP_KEY, APP_SECRECT, mWarn);
        initView();
        initRegisterEvent();

        SwipeBackHelper.getCurrentPage(this)
                .setSwipeBackEnable(true);
    }



    /**
     * 初始化单击事件
     */
    private void initRegisterEvent() {
        //注册按钮单击事件
        mBtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                account = mEtAccount.getText().toString();
                pass1 = mEtPass1.getText().toString();
                pass2 = mEtPass2.getText().toString();
                if (!account.isEmpty() && !pass1.isEmpty()){
                    if (ifPassTrue()){
                        Log.i("tag",pass1+"==="+pass2);
                        register(account,pass1);
                        Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }

                }else{


                    Toast.makeText(RegisterActivity.this,"注册shibai,请重新注册!",Toast.LENGTH_LONG).show();
                }
//                if (ifPassTrue()) {
////                    bt_register_submit();
//                }
            }
        });
    }

    private void initView() {
        mBtRegister = (FlatButton) findViewById(R.id.bt_register_submit);
        mEtAccount = (MaterialEditText) findViewById(R.id.et_register_account);
        mEtPass1 = (MaterialEditText) findViewById(R.id.et_register_pass1);
        mEtPass2 = (MaterialEditText) findViewById(R.id.et_register_pass2);

        mBtRegister.setBackgroundColor(0xff0099cc);
        mBtRegister.setFocusColor(0xffffff);


        Log.i("tag","dfhdhfj");

        mRegisterPresenter=new RegisterPresenter(this,this);

    }

    private boolean ifPassTrue() {

        if (pass1.equals(pass2)) {
            return true;
        } else
        {
            mEtPass1.setError("两次密码不正确！");
            return false;
        }

    }


//    public void bt_register_submit() {
//        //打开注册页面
//        RegisterPage registerPage = new RegisterPage();
//        registerPage.setRegisterCallback(new EventHandler() {
////            public void afterEvent(int event, int result, Object data) {
////// 解析注册结果
////                if (result == SMSSDK.RESULT_COMPLETE) {
////                    @SuppressWarnings("unchecked")
////                    HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
////                    String country = (String) phoneMap.get("country");
////                    String phone = (String) phoneMap.get("phone");
////                    Toast.makeText(RegisterActivity.this, phone, Toast.LENGTH_SHORT).show();
////
////// 提交用户信息
////                    //registerUser(country, phone);
////                }
////            }
//        });
//        registerPage.show(this);
//    }

    @Override
    public void register(String userName, String password) {
        mRegisterPresenter.userRegister(userName,password);
        Log.i("tag","shuchushuju");
    }
}
