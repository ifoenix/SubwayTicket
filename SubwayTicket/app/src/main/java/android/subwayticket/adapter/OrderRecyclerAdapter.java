package android.subwayticket.adapter;

import android.content.Context;
import android.subwayticket.R;
import android.subwayticket.bean.Notice;
import android.subwayticket.bean.Order;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.Collection;
import java.util.List;

import cn.smssdk.gui.DefaultContactViewItem;

/**
 * Created by Zach on 2016/8/13.
 */
public class  OrderRecyclerAdapter extends RecyclerView.Adapter<OrderRecyclerAdapter.MyViewHolder>{

    private List<Order> mOrderList;
    private Context context;
    private LayoutInflater inflater;
    private  OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener;


    public OrderRecyclerAdapter(List<Order> orderList, Context context) {
        this.mOrderList = orderList;
        this.context = context;
        inflater=LayoutInflater.from(context);

    }

    //创建控件
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_order_list,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    //为控件设置数据
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (holder instanceof MyViewHolder){
            Order order=mOrderList.get(position);
            Log.i("tag",order.toString());

            Log.i("tag","ddddd");
            if (order == null){
                Log.i("tag","bbbbb");
                return;
            }
            Log.i("tag",order.getStart());
            Log.i("tag",order.getOrderID());
            Log.i("tag",order.getTerminal());
            holder.start.setText(order.getStart());
            holder.terminal.setText(order.getTerminal());
//            holder.price.setText(order.getPrice()+"");
//            holder.orderID.setText(order.getOrderID());

        }
    }

    @Override
    public int getItemCount() {
        return mOrderList.size();
    }

    public void addOrderAll(Collection<? extends Order> collection){
        mOrderList.addAll(collection);
        notifyDataSetChanged();
    }
    public void  setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener listener){
        mOnRecyclerViewItemClickListener=listener;
    }

    public Order getItem(int position){
        return mOrderList ==null ? null : mOrderList.get(position);
    }

    public interface OnRecyclerViewItemClickListener{
        void OnRecyclerItemClickListener(View view,int position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView start;
        private final TextView terminal;
//        private final TextView price;
//        private final TextView orderID;
//        private final TextView buyTime;

        public MyViewHolder(View view){
            super(view);
            start= (TextView) view.findViewById(R.id.tv_order_start);
            terminal= (TextView) view.findViewById(R.id.tv_order_terminal);
//            price= (TextView) view.findViewById(R.id.tv_price);
//            orderID= (TextView) view.findViewById(R.id.tv_order_id);
//            buyTime= (TextView) view.findViewById(R.id.tv_order_time);
            view.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            if (mOnRecyclerViewItemClickListener !=null){
                mOnRecyclerViewItemClickListener.OnRecyclerItemClickListener(view,this.getPosition());
            }
        }
    }
}
