package android.subwayticket.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.subwayticket.R;
import android.subwayticket.adapter.OrderRecyclerAdapter;
import android.subwayticket.bean.Order;
import android.subwayticket.presenter.OrderPresenter;
import android.subwayticket.utils.ItemDivider;
import android.subwayticket.view.IView.IOrderView;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zach on 2016/8/12.
 */
public class OrderActivity extends AppCompatActivity implements IOrderView {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private ProgressBar mProgressBar;
    private OrderPresenter mOrderPresenter;
    private OrderRecyclerAdapter adapter;
    private Toolbar toolbar;
    private RecyclerView.ItemDecoration mItemDecoration;
    private List<Order> orderList = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        mOrderPresenter=new OrderPresenter(this,this);
        mOrderPresenter.getOrdersData();
        initView();
        initToolBar();
        initRecycler();
    }

    private void initRecycler() {
        //创建布局管理对象
        mLinearLayoutManager=new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        //创建列表项分隔线对象
        mItemDecoration=new ItemDivider(this);
        //为recyclerView控件指定分个线对象
        mRecyclerView.addItemDecoration(mItemDecoration);

        adapter=new OrderRecyclerAdapter(orderList,this);
        adapter.setOnRecyclerViewItemClickListener(mOrderListener);
        mRecyclerView.setAdapter(adapter);

    }

    OrderRecyclerAdapter.OnRecyclerViewItemClickListener mOrderListener=
            new OrderRecyclerAdapter.OnRecyclerViewItemClickListener() {
                @Override
                public void OnRecyclerItemClickListener(View view, int position) {
                    Order order=adapter.getItem(position);
                    Log.i("order",order.toString());
                    Intent intent=new Intent(OrderActivity.this,OrderDetailActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putSerializable("order",order);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            };

    private void initView() {
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        mRecyclerView= (RecyclerView) findViewById(R.id.recyclerview_order);
        mProgressBar= (ProgressBar) findViewById(R.id.order_progress);

    }

    private void initToolBar() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }

    @Override
    public void showOrderInfo(List<Order> orderList) {
        adapter.addOrderAll(orderList);
    }

//    @Override
//    public void showProgress() {
//        mProgressBar.setVisibility(View.VISIBLE);
//        Toast.makeText(OrderActivity.this,"在加载中...",Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    public void hideProgress() {
//        mProgressBar.setVisibility(View.GONE);
//        Toast.makeText(OrderActivity.this,"加载完成...",Toast.LENGTH_LONG).show();
//
//    }
}
