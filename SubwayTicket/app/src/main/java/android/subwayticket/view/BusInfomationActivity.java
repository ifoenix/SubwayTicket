package android.subwayticket.view;


import android.content.Intent;
import android.os.Bundle;
import android.subwayticket.R;
import android.subwayticket.constant.Constant;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TableRow;
import android.widget.Toast;

/**
 * Created by Zach on 2016/7/29.
 */
public class BusInfomationActivity extends AppCompatActivity implements View.OnClickListener{
    private TableRow tr_safety;
    private TableRow tr_checkticket;
    private TableRow tr_legal;
    private Toolbar toolbar;

//    @Bind(R.id.topbar)
//    TopBar mTopBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businfo);
        Toast.makeText(this, Constant.userID+"",Toast.LENGTH_LONG).show();

        initView();
        initEvent();

    }

    private void initEvent() {
        tr_safety.setOnClickListener(this);
        tr_checkticket.setOnClickListener(this);
        tr_legal.setOnClickListener(this);
    }

    private void initView() {
        tr_safety= (TableRow) findViewById(R.id.tr_safety);
        tr_checkticket= (TableRow) findViewById(R.id.tr_checkticket);
        tr_legal= (TableRow) findViewById(R.id.tr_legal);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.drawable.topbar_back_selector);
        setSupportActionBar(toolbar);


    }

    @Override
    public void onClick(View view) {

        int id=view.getId();
        switch (id){
            case  R.id.tr_safety :
                Intent safetyIntent =new Intent(BusInfomationActivity.this,SafetyActivity.class);
                startActivity(safetyIntent);
                break;

            case R.id.tr_checkticket :
                Intent checkticketIntent=new Intent(BusInfomationActivity.this,CheckTicketActivity.class);
                startActivity(checkticketIntent);
                break;

            case R.id.tr_legal :
                Intent legalIntent =new Intent(BusInfomationActivity.this,LegalActivity.class);
                startActivity(legalIntent);
                break;
        }

    }
}