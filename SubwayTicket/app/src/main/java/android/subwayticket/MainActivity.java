package android.subwayticket;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.subwayticket.adapter.NoticeRecyclerAdapter;
import android.subwayticket.bean.Notice;
import android.subwayticket.presenter.NoticePresenter;
import android.subwayticket.utils.ItemDivider;
import android.subwayticket.view.BusInfomationActivity;
import android.subwayticket.view.BuyTicketActivity;
import android.subwayticket.view.CircleImageView;
import android.subwayticket.view.IView.INoticeView;
import android.subwayticket.view.LoginActivity;
import android.subwayticket.view.NoticeDetailActivity;
import android.subwayticket.view.OperGuideActivity;
import android.subwayticket.view.OrderActivity;
import android.subwayticket.view.SettingsActivity;
import android.subwayticket.view.SiteActivity;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import autocarousel.kevin.autocarousel.imageloop.ImageLoopView;


//import com.lidroid.xutils.HttpUtils;
//import com.lidroid.xutils.exception.HttpException;
//import com.lidroid.xutils.http.ResponseInfo;
//import com.lidroid.xutils.http.callback.RequestCallBack;
//import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class MainActivity extends AppCompatActivity implements INoticeView, View.OnClickListener {


	private Handler handler = new Handler();

	private File targetFile;
	private Toolbar toolbar;
	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;

	private ImageView iv_site;
	private ImageView iv_operation;
	private ImageView iv_ticket;
	private ImageView iv_businfo;
	private CircleImageView civ_login;
	private List<Notice> mNoticeList=new ArrayList<Notice>();

	private RecyclerView recyclerView;
	private LinearLayoutManager mLayoutManager;
	private ProgressBar mProgressBar;
	private NoticePresenter mNoticePresenter;
	private NoticeRecyclerAdapter adapter;
	private RecyclerView.ItemDecoration itemDecoration;
	private NavigationView mNavigationView;
	private ImageLoopView imageLoopView;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//初始化控件
		initView();
		//为控件添加点击事件
		initEvent();
		initToolBar();
		initDrawerLayout();
		initRecycler();
		initImagedata();
	}

	private void initImagedata() {
		String[] imageUrls = {"https://www.baidu.com/img/bd_logo1.png",
				"https://www.baidu.com/img/bd_logo1.png",
				"https://www.baidu.com/img/bd_logo1.png",
				"https://www.baidu.com/img/bd_logo1.png",
				"http://pic2.zhimg.com/a62f9985cae17fe535a99901db18eba9.jpg"};
		String[] titles = {"JB鑫",
				"JB勇",
				"草泥马不",
				"吃屎把",
				"JB勇东莞叫鸡被抓"};
		for (int i = 0; i < 5; i++) {
			imageLoopView.addImageTitle(imageUrls[i], titles[i]);
		}
       imageLoopView.setIndicator_size(12);
		imageLoopView.setIndicator_space(12);
		imageLoopView.setDuration(3000);
		imageLoopView.commit();
	}

	private void initRecycler() {
		//创建布局管理对象
		mLayoutManager=new LinearLayoutManager(this);
		mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		recyclerView.setLayoutManager(mLayoutManager);
		//创建列表项分隔线对象
		itemDecoration=new ItemDivider(this);
		//为recyclerView控件指定分个线对象
		recyclerView.addItemDecoration(itemDecoration);
		adapter=new NoticeRecyclerAdapter(mNoticeList,MainActivity.this);
		adapter.setRecyclerViewItemClickListener(mOnRecyclerViewItemClickListener);
		recyclerView.setAdapter(adapter);

		mNoticePresenter=new NoticePresenter(this,this);
		mNoticePresenter.getNoticeData();
	}

	private void initDrawerLayout() {
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close);
		mDrawerToggle.syncState();
		mDrawerLayout.setDrawerListener(mDrawerToggle);
	}

	private void initEvent() {
		iv_site.setOnClickListener(this);
		iv_operation.setOnClickListener(this);
		iv_ticket.setOnClickListener(this);
		iv_businfo.setOnClickListener(this);
		civ_login.setOnClickListener(this);

		mNavigationView.setNavigationItemSelectedListener(
				new NavigationView.OnNavigationItemSelectedListener(){

					@Override
					public boolean onNavigationItemSelected(MenuItem item) {
						switch (item.getItemId()){
							case R.id.menu_order :
								Intent orderIntent=new Intent(MainActivity.this, OrderActivity.class);
							startActivity(orderIntent);
							break;
							case R.id.menu_remind :

							case R.id.menu_setting :
								Intent intent=new Intent(MainActivity.this, SettingsActivity.class);
								startActivity(intent);
								break;

						}
						item.setChecked(true);
						mDrawerLayout.closeDrawers();
						return true;
					}
				});



	}

	private void initToolBar() {
		toolbar.setTitle("");
		toolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);
		setSupportActionBar(toolbar);
	}

	private void initView() {

		toolbar = (Toolbar) findViewById(R.id.toolbar);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
		iv_ticket= (ImageView) findViewById(R.id.iv_ticket);
		iv_site= (ImageView) findViewById(R.id.iv_site);
		iv_operation= (ImageView) findViewById(R.id.iv_operation);
		iv_businfo= (ImageView) findViewById(R.id.iv_businfo);
		mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
		civ_login= (CircleImageView) mNavigationView.getHeaderView(0).findViewById(R.id.civ_head_login);
		recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
		mProgressBar= (ProgressBar) findViewById(R.id.notice_progress);
		imageLoopView = (ImageLoopView) findViewById(R.id.imageloopview);
	}



	@Override
	public void onClick(View view) {
		int id = view.getId();

		switch (id) {

			case R.id.iv_ticket:
				/**
				 * 跳转到手机购票界面
				 */
				Intent ticketIntent = new Intent(MainActivity.this, BuyTicketActivity.class);
				this.startActivity(ticketIntent);
				break;

			case R.id.iv_site:
				/**
				 * 点击跳转到站点信息界面
				 */
				Intent siteIntent = new Intent(MainActivity.this, SiteActivity.class);
				this.startActivity(siteIntent);
				break;

			case R.id.iv_operation:
				/**
				 * 跳转到操作指南界面
				 */
				Intent operationIntent = new Intent(MainActivity.this, OperGuideActivity.class);
				this.startActivity(operationIntent);
				break;

			case R.id.iv_businfo:
				/**
				 * 跳转到乘车须知界面
				 */
				Intent businfoIntent = new Intent(MainActivity.this, BusInfomationActivity.class);
				this.startActivity(businfoIntent);
				break;

			case R.id.civ_head_login:
				/**
				 * 跳转到乘车须知界面
				 */
				Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
				this.startActivity(loginIntent);
				break;

		}
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		MenuInflater inflater =this.getMenuInflater();
//		inflater.inflate(R.menu.menu, menu);
//
//
//		return true;
//	}
//
//	public boolean onOptionsItemSelected(MenuItem item){
//		switch (item.getItemId()){
//			case R.id.menu_setting :
//				Toast.makeText(MainActivity.this,"你点击了菜单按钮",Toast.LENGTH_LONG).show();
//				Intent intent=new Intent(MainActivity.this, SettingsActivity.class);
//				startActivity(intent);
//				break;
//			case R.id.menu_order :
//				Toast.makeText(MainActivity.this,"你点击了菜单按钮",Toast.LENGTH_LONG).show();
//				Intent orderIntent=new Intent(MainActivity.this, OrderActivity.class);
//				startActivity(orderIntent);
//				break;
//		}
//		return  false;
//	}



	@Override
	public void showProgress() {
		mProgressBar.setVisibility(View.VISIBLE);
		Toast.makeText(MainActivity.this,"在加载中...",Toast.LENGTH_LONG).show();
	}

	@Override
	public void hideProgress() {
		mProgressBar.setVisibility(View.GONE);
		Toast.makeText(MainActivity.this,"加载完成...",Toast.LENGTH_LONG).show();
	}

	@Override
	public void showNotices(List<Notice> noticeList) {
		adapter.addNoticeAll(noticeList);

	}

	private NoticeRecyclerAdapter.OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener=
					new NoticeRecyclerAdapter.OnRecyclerViewItemClickListener() {
						@Override
						public void onItemClick(View view, int position) {
							Notice notice=adapter.getItem(position);


							Intent intent=new Intent(MainActivity.this,NoticeDetailActivity.class);
							Bundle bundle=new Bundle();
							bundle.putSerializable("notice",notice);
							intent.putExtras(bundle);
							startActivity(intent);
						}
					};


}
