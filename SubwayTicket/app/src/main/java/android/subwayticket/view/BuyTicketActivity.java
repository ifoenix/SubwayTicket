package android.subwayticket.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.subwayticket.R;
import android.subwayticket.presenter.BuyTicketPresenter;
import android.subwayticket.utils.FlatButton;
import android.subwayticket.utils.MyLocationListener;
import android.subwayticket.view.IView.IBuyTicketView;
import android.subwayticket.view.IView.IGetPriceView;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.dreamlive.hotimglibrary.entity.HotArea;
import com.dreamlive.hotimglibrary.utils.FileUtils;
import com.dreamlive.hotimglibrary.view.HotClickView;
import com.michael.easydialog.EasyDialog;

import java.io.InputStream;
import java.net.URLDecoder;


public class BuyTicketActivity extends Activity implements HotClickView.OnClickListener, IBuyTicketView {


    private EditText mStartTV = null;
    private EditText mEndTV = null;
    private HotClickView mHotView;
    private int x;
    private int y;
    private LinearLayout mEasyDialogLinearLayout;
    private TextView mStationTitle;
    private TextView mStationSatrt;
    private TextView mStationEnd;
    private EasyDialog stationDialog;
    private EditText mEtStartStation;
    private EditText mEtEndStation;
    private TextView mCity;
    private BuyTicketPresenter buyTicketPresenter;

    private View mDialogView;
    private TextView mSubmitDialogStartStation;
    private TextView mSubmitDialogEndstation;
    private TextView mSubmitDialogMoney;
    private FlatButton mSubmitButton;

    private Dialog mSubmitDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_buyticket);
        bindSubmitDialog();
        Toast.makeText(this, MyLocationListener.city, Toast.LENGTH_LONG).show();
        initView();
        mCity.setText(MyLocationListener.city);
        initParam();
        initDatas();
    }

    /*
    * 初始化各个视图
    * */
    private void initView() {
        mStartTV = (EditText) findViewById(R.id.et_start);
        mEndTV = (EditText) findViewById(R.id.et_end);

        mEtStartStation = (EditText) findViewById(R.id.et_buyticket_startstation);
        mEtEndStation = (EditText) findViewById(R.id.et_buyticket_endstation);
        mCity = (TextView) findViewById(R.id.buyticket_city);


    }

    private void initParam() {
        mHotView = (HotClickView) findViewById(R.id.a_main_hotview);

    }

    /*
    * 初始化hotingview,加载区域文件以及照片文件
    * */
    protected void initDatas() {
        AssetManager assetManager = getResources().getAssets();
        InputStream imgInputStream = null;
        InputStream fileInputStream = null;
        try {
            imgInputStream = assetManager.open("GZsubway.jpg");
            fileInputStream = assetManager.open("GZsubway.xml");
           /*
           * 为触碰点添加事件,获取触碰时的X,Y坐标
           * */
            mHotView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    x = (int) motionEvent.getRawX();
                    y = (int) motionEvent.getRawY();
                    return false;
                }
            });
            mHotView.setImageBitmap(fileInputStream, imgInputStream, HotClickView.FIT_XY);
            mHotView.setOnClickListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            FileUtils.closeInputStream(imgInputStream);
            FileUtils.closeInputStream(fileInputStream);
        }
    }

    /*
    * hotingView 点击事件
    * */
    @Override
    public void OnClick(View view, HotArea hotArea) {

        initDialogView();
        mStationTitle.setText(hotArea.getDesc());
        stationDialog = new EasyDialog(BuyTicketActivity.this)
                .setLayout(mEasyDialogLinearLayout)
                .setBackgroundColor(BuyTicketActivity.this.getResources().getColor(R.color.background_color_purple))
                .setLocation(new int[]{x, y})
                .setGravity(EasyDialog.GRAVITY_TOP)
                .setAnimationAlphaShow(300, 0.0f, 1.0f)
                .setAnimationAlphaDismiss(300, 1.0f, 0.0f)
                .setTouchOutsideDismiss(true)
                .setMatchParent(false)
                .setOutsideColor(BuyTicketActivity.this.getResources().getColor(R.color.outside_color_gray))
                .show();
    }

    /*
    * 初始化对话框
    * 为对话框添加TextView 添加点击事件已达到获取价格
    * */
    private void initDialogView() {
        mEasyDialogLinearLayout = (LinearLayout) View.inflate(this, R.layout.buyticket_dialog, null);
        mStationTitle = (TextView) mEasyDialogLinearLayout.findViewById(R.id.tv_buyticket_title);
        mStationSatrt = (TextView) mEasyDialogLinearLayout.findViewById(R.id.tv_buyticket_start);
        mStationEnd = (TextView) mEasyDialogLinearLayout.findViewById(R.id.tv_buyticket_end);
        bindSubmitDialog();

        mStationSatrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mEtStartStation.setText(mStationTitle.getText().toString());
                mSubmitDialogStartStation.setText(mStationTitle.getText().toString());
                mSubmitDialogEndstation.setText(mEtEndStation.getText().toString());
                if (mEtEndStation.getText().toString().isEmpty()) {
                    stationDialog.dismiss();
                } else {
                    initDialogDataAndSendData();
                }
            }
        });
        mStationEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mEtEndStation.setText(mStationTitle.getText().toString());
                mSubmitDialogEndstation.setText(mStationTitle.getText().toString());
                mSubmitDialogStartStation.setText(mEtStartStation.getText().toString());
                if (mEtStartStation.getText().toString().isEmpty()) {

                    stationDialog.dismiss();
                } else {
                    initDialogDataAndSendData();
                }

            }
        });
    }
     /*
     * 向payactivity发送数据
     * */
    public void initDialogDataAndSendData(){
        postPrice();
        showSubmitDialog();
        stationDialog.dismiss();
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSubmitDialog.dismiss();
                Intent intent = new Intent(BuyTicketActivity.this, PayActivity.class);
                intent.putExtra("startstation",mSubmitDialogStartStation.getText().toString());
                intent.putExtra("endstation",mSubmitDialogEndstation.getText().toString());
                intent.putExtra("money",mSubmitDialogMoney.getText().toString());
                startActivity(intent);
            }
        });

    }
    /*
    * 获取票价,显示在mTvMoney中
    * */

    private void postPrice() {
        buyTicketPresenter = new BuyTicketPresenter(new IGetPriceView() {
            @Override
            public void hideGetPriceLoad() {

            }

            @Override
            public void showGetPriceLoad() {

            }

            @Override
            public void setPrice(float price) {
                mSubmitDialogMoney.setText(price + "");


            }
        }, BuyTicketActivity.this);
        buyTicketPresenter.getPoint(URLDecoder.decode(mEtStartStation.getText().toString()), mEtEndStation.getText().toString());


    }

    /*
    * 确认购票弹出框
    * */

    public void showSubmitDialog() {
        mSubmitDialog = new android.support.v7.app.AlertDialog.Builder(this).create();
        mSubmitDialog.show();
        Window window = mSubmitDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);  //此处可以设置dialog显示的位置
        mSubmitDialog.getWindow().setContentView(mDialogView);
        WindowManager windowManager = this.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = mSubmitDialog.getWindow().getAttributes();
        lp.width = display.getWidth();
        mSubmitDialog.getWindow().setAttributes(lp);

    }

    /*
    *绑定确认对话框VIEW
    * */
    private void bindSubmitDialog() {
        mDialogView = View.inflate(this, R.layout.buyticket_submit_dialog, null);
        mSubmitDialogStartStation = (TextView) mDialogView.findViewById(R.id.tv_submit_startstation);
        mSubmitDialogEndstation = (TextView) mDialogView.findViewById(R.id.tv_submit_endstation);
        mSubmitDialogMoney = (TextView) mDialogView.findViewById(R.id.tv_submit_money);
        mSubmitButton = (FlatButton) mDialogView.findViewById(R.id.bt_submit_buyticket);
    }


    @Override
    public void hideBuyTicketLoad() {

    }

    @Override
    public void showBuyTicketLoad() {

    }

    @Override
    public void buyTicketSuccess() {

    }

}

