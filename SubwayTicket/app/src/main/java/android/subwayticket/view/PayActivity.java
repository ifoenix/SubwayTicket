package android.subwayticket.view;

import android.content.Intent;
import android.os.Bundle;
import android.subwayticket.R;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by lilingyong on 16/8/14.
 */
public class PayActivity extends AppCompatActivity {
    private TextView mStartStation;
    private TextView mEndStation;
    private TextView mMoney;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        initView();
        setData();
    }

    private void setData() {
        Intent dataintent = this.getIntent();
        mStartStation.setText(dataintent.getStringExtra("startstation"));
        mEndStation.setText(dataintent.getStringExtra("endstation"));
        mMoney.setText(dataintent.getStringExtra("money"));
    }

    private void initView() {
        mStartStation = (TextView) findViewById(R.id.tv_pay_startstation);
        mEndStation = (TextView) findViewById(R.id.tv_pay_endstation);
        mMoney = (TextView) findViewById(R.id.tv_pay_money);

    }
}
