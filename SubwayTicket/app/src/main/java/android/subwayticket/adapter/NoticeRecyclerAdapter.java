package android.subwayticket.adapter;

import android.content.Context;
import android.subwayticket.R;
import android.subwayticket.bean.Notice;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collection;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Zach on 2016/8/13.
 */
public class  NoticeRecyclerAdapter extends RecyclerView.Adapter<NoticeRecyclerAdapter.MyViewHolder> {

    private List<Notice> mNoticeList;

    private Context context;
    private  LayoutInflater inflater;
    private OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener;

    public NoticeRecyclerAdapter(List<Notice> mNoticeList, Context context) {
        this.mNoticeList = mNoticeList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }



    //用于创建控件
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= inflater.inflate(R.layout.item_notice_list,parent,false);
        MyViewHolder mViewHolder=new MyViewHolder(view);
        return mViewHolder;
    }

    //为控件设置数据
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        if(holder instanceof MyViewHolder){
            Notice notice=mNoticeList.get(position);
            Log.i("tag",notice.toString());
            if (notice==null){
                return;
            }

            holder.notice_title.setText(notice.getNoticeTitle());
            holder.notice_time.setText(notice.getCreateTime());
        }
    }

    @Override
    public int getItemCount() {
        return mNoticeList.size();
    }

    public void addNoticeAll(Collection<? extends Notice> collection){
        mNoticeList.addAll(collection);
        notifyDataSetChanged();
    }

    public  Notice getItem(int position){
        return mNoticeList == null ? null:mNoticeList.get(position);
    }
    public  void setRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener ){
        this.mOnRecyclerViewItemClickListener=onRecyclerViewItemClickListener;
    }
    public static interface OnRecyclerViewItemClickListener{
        void onItemClick(View view,int position);
    }

     class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private  final TextView notice_time;
        private  final TextView notice_title;

        public MyViewHolder(View itemView){
            super(itemView);
            notice_time= (TextView) itemView.findViewById(R.id.notice_time);
            notice_title= (TextView) itemView.findViewById(R.id.notice_title);
            itemView.setOnClickListener(this);
        }

         @Override
         public void onClick(View view) {
             if (mOnRecyclerViewItemClickListener != null){
                 mOnRecyclerViewItemClickListener.onItemClick(view,this.getPosition());
             }
         }
     }
}
