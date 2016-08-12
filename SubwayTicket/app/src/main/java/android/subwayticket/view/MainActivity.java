package android.subwayticket.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.subwayticket.R;
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
