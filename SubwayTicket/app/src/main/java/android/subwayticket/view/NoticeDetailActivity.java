package android.subwayticket.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.subwayticket.R;
import android.subwayticket.bean.Notice;
import android.widget.TextView;

/**
 * Created by Zach on 2016/8/13.
 */
public class NoticeDetailActivity extends Activity {
    private TextView notice_title;
    private TextView notice_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_detail);
        initView();
        Intent intent=getIntent();
       Notice notice= (Notice) intent.getSerializableExtra("notice");

        notice_title.setText(notice.getNoticeTitle());
        notice_content.setText(notice.getNoticeContent());
    }

    private void initView() {
        notice_title= (TextView) findViewById(R.id.notice_detail_title);
        notice_content= (TextView) findViewById(R.id.notice_detail_content);
    }


}
