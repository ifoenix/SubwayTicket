package android.subwayticket.view;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.subwayticket.R;
import android.subwayticket.utils.FlatButton;
import android.subwayticket.utils.MyLocationListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dreamlive.hotimglibrary.entity.HotArea;
import com.dreamlive.hotimglibrary.utils.FileUtils;
import com.dreamlive.hotimglibrary.view.HotClickView;
import com.michael.easydialog.EasyDialog;

import java.io.InputStream;


public class BuyTicketActivity extends Activity implements HotClickView.OnClickListener {


    private EditText mStartTV = null;
    private EditText mEndTV = null;
    private HotClickView mHotView;
    private int x;
    private int y;
    private FlatButton mFlatButton;
    private LinearLayout mEasyDialogLinearLayout;
    private TextView mStationTitle;
    private TextView mStationSatrt;
    private TextView mStationEnd;
    private EasyDialog stationDialog;
    private EditText mEtStartStation;
    private EditText mEtEndStation;
    private TextView mTvMoney;
    private TextView mCity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyticket);

        Toast.makeText(this, MyLocationListener.city, Toast.LENGTH_LONG).show();
        initView();
        mCity.setText(MyLocationListener.city);
        initParam();
        initDatas();
    }


    private void initView() {
        mStartTV = (EditText) findViewById(R.id.et_start);
        mEndTV = (EditText) findViewById(R.id.et_end);
        mFlatButton = (FlatButton) findViewById(R.id.bt_buyticket_submit);
        mFlatButton.setFocusColor(0xffffff);
        mFlatButton.setBackgroundColor(0xff00ddff);

        mEtStartStation = (EditText) findViewById(R.id.et_buyticket_startstation);
        mEtEndStation = (EditText) findViewById(R.id.et_buyticket_endstation);
        mTvMoney = (TextView) findViewById(R.id.tv_buyticket_money);
        mCity = (TextView) findViewById(R.id.buyticket_city);

    }

    private void initParam() {
        mHotView = (HotClickView) findViewById(R.id.a_main_hotview);

    }

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

    private void initDialogView() {
        mEasyDialogLinearLayout = (LinearLayout) View.inflate(this, R.layout.buyticket_dialog, null);
        mStationTitle = (TextView) mEasyDialogLinearLayout.findViewById(R.id.tv_buyticket_title);
        mStationSatrt = (TextView) mEasyDialogLinearLayout.findViewById(R.id.tv_buyticket_start);
        mStationEnd = (TextView) mEasyDialogLinearLayout.findViewById(R.id.tv_buyticket_end);

        mStationSatrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEtStartStation.setText(mStationTitle.getText().toString());
                stationDialog.dismiss();

            }
        });
        mStationEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEtEndStation.setText(mStationTitle.getText().toString());
                stationDialog.dismiss();
            }
        });
    }
}

