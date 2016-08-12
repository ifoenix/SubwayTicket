package android.subwayticket.view;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.subwayticket.R;
import android.subwayticket.utils.BaseActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.dreamlive.hotimglibrary.entity.HotArea;
import com.dreamlive.hotimglibrary.utils.FileUtils;
import com.dreamlive.hotimglibrary.view.HotClickView;

import java.io.InputStream;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LineActivity extends BaseActivity implements HotClickView.OnClickListener {


    private EditText mStartTV = null;
    private EditText mEndTV = null;
    private HotClickView mHotView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line);


        initView();
        initParam();
        initDatas();
    }

    private void initView() {
        mStartTV = (EditText) findViewById(R.id.et_start);
        mEndTV = (EditText) findViewById(R.id.et_end);
    }

    private void initParam() {
        mHotView = (HotClickView) findViewById(R.id.a_main_hotview);
//        mHotView.setCanMove(false);
//        mHotView.setCanScale(false);
    }

    protected void initDatas() {
        AssetManager assetManager = getResources().getAssets();
        InputStream imgInputStream = null;
        InputStream fileInputStream = null;
        try {
            imgInputStream = assetManager.open("GZsubway.jpg");
            fileInputStream = assetManager.open("GZsubway.xml");
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


        //Toast.makeText(MainActivity.this, "你点击了" + mStartTV.getText().toString(), Toast.LENGTH_SHORT).show();
        if (mStartTV.getText().toString().equals("") && mEndTV.getText().toString().equals("")) {
            mStartTV.setHint("起点："+hotArea.getDesc());
            mStartTV.setText(hotArea.getDesc());
            //mStartTV.setText(hotArea.getDesc());

        } else if (mEndTV.getText().toString().equals("") && mStartTV.getText().toString() != null) {
            mEndTV.setHint("终点："+hotArea.getDesc());
            mEndTV.setText(hotArea.getDesc());
            //mEndTV.setText(hotArea.getDesc());
        } else if (mStartTV.getText().toString() != null && mEndTV.getText().toString() != null) {
            mStartTV.setHint("起点："+hotArea.getDesc());
            mEndTV.setHint("终点");
            mStartTV.setText(hotArea.getDesc());
            mEndTV.setText("");
            //mStartTV.setText(hotArea.getDesc());
            //mEndTV.animateText("");



            //Toast.makeText(MainActivity.this, "你点击了" + mStartTV.getText().toString(), Toast.LENGTH_SHORT).show();
        }
        //Toast.makeText(MainActivity.this, "你点击了" + mStartTV.getText().toString(), Toast.LENGTH_SHORT).show();
    }


}

