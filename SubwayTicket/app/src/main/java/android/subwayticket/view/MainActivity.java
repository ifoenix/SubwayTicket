package android.subwayticket.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.subwayticket.R;
import android.subwayticket.utils.MyLocationListener;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.google.gson.Gson;

import java.io.File;

//import com.lidroid.xutils.HttpUtils;
//import com.lidroid.xutils.exception.HttpException;
//import com.lidroid.xutils.http.ResponseInfo;
//import com.lidroid.xutils.http.callback.RequestCallBack;
//import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


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

	public LocationClient mLocationClient = null;
	public BDLocationListener myListener = new MyLocationListener();



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//定位信息
		mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
		mLocationClient.registerLocationListener(myListener);    //注册监听函数
		initLocation();

		//初始化控件
		initView();
		//为控件添加点击事件
		initEvent();
		initToolBar();
		initDrawerLayout();
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
//		civ_login.setOnClickListener(this);


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
		civ_login= (CircleImageView) findViewById(R.id.civ_head_login);


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
	private void initLocation() {
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
		);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
		option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
		int span = 1000;
		option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
		option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
		option.setOpenGps(true);//可选，默认false,设置是否使用gps
		option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
		option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
		option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
		option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
		option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
		option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
		mLocationClient.setLocOption(option);
		mLocationClient.start();
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		MenuInflater inflater =this.getMenuInflater();
//		inflater.inflate(R.menu.menu, menu);
//
//		return true;
//	}
//
//	public boolean onOptionsItemSelected(MenuItem item){
//		switch (item.getItemId()){
//			case R.id.menu_login :
//				Intent loginIntent=new Intent(MainActivity.this,LoginActivity.class);
//				startActivity(loginIntent);
//				break;
//		}
//		return true;
//	}

}
