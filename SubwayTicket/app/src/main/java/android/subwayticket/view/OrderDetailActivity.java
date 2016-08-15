package android.subwayticket.view;

import android.content.Intent;
import android.os.Bundle;
import android.subwayticket.R;
import android.subwayticket.bean.Order;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Zach on 2016/8/13.
 */
public class OrderDetailActivity extends AppCompatActivity {
    private TextView tv_start;
    private TextView tv_terminal;
    private TextView tv_price;
    private TextView tv_orderID;
    private TextView tv_time;
    private  Order order;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_order_info);
        Intent intent=getIntent();
        order= (Order) intent.getSerializableExtra("order");
        Log.i("orders",order.toString());
        initView();
    }

    private void initView() {
        tv_start= (TextView) findViewById(R.id.tv_order_detail_start);
        tv_terminal= (TextView) findViewById(R.id.tv_order_detail_terminal);
        tv_price= (TextView) findViewById(R.id.tv_order_detail_price);
        tv_orderID= (TextView) findViewById(R.id.tv_order_detail_id);
        tv_time= (TextView) findViewById(R.id.tv_order_detail_time);
        Log.i("tag",order.getTerminal());

        tv_start.setText(order.getStart());
        tv_terminal.setText(order.getTerminal());
        tv_price.setText(order.getPrice()+"");
        tv_orderID.setText(order.getOrderID());


    }


}
