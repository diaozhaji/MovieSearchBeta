package com.NG.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.NG.loader.SearchExampleLoader;
import com.NG.loader.SimpleInfoLoder;
import com.NG.moviesearchbeta.R;
import com.NG.adapter.SearchExampleAdapter;
import com.NG.adapter.SearhResultAdapter;
import com.NG.entity.SingleEntity;

/**
 * 
 * @author jiyuan 程序入口
 */

public class SearchResultPage extends ListActivity {

	final static String TAG = "SearchResultPage";

	private Context mContext;
	
	private ListView mlistView;// 存取搜索信息的列表控件

	private SearchExampleAdapter sAdapter; // 用于开始时的list
	private SearhResultAdapter mAdapater; // 用来加载BaseAdapater

	public List<SingleEntity> aList;// MovieBriefPojo 返回的泛型LIST
	private SingleEntity mbp;// 传递点击事件的 击点
	public List<String> sList;

	public Spinner sp;// 选择要搜索的类型

	private ProgressDialog proDialog;

	private String name;
	private Handler handler;
	String[] types = { "搜电影" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_result_page);
		mContext = this;
		name = "戳泪点";

		initView();
		//initData();

	}

	private void initView() {

		mbp = new SingleEntity();
		initProDialog();
		proDialog.show();
		mlistView = getListView();
		
		//searchData();

		

	}
	
	void initProDialog() {
		proDialog = new ProgressDialog(this);
		proDialog.setTitle(R.string.loading);
		proDialog.setMessage("请您耐心等待...");
	}

	public void searchData(String s) {
		name = s;
		new Thread(downloadRun).start();
		handler = new Handler() {
			public void handleMessage(Message msg) {
				if (msg.what == 0) {
					// msg.obj是获取handler发送信息传来的数据
					List<SingleEntity> seList = (ArrayList<SingleEntity>) msg.obj;
					// 给ListView绑定数据
					showall(seList);

				}
			}
		};
		// proDialog.dismiss();
	}

	

	Runnable downloadRun = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				aList = new SimpleInfoLoder().findXml(name);
			} catch (IOException e) {
				// TODO Auto-generated catch block

				e.printStackTrace();
			}
			handler.sendMessage(handler.obtainMessage(0, aList));
		}
	};
	/*
	private void initData() {
		// TODO Auto-generated method stub
		sList = new ArrayList<String>();

		getStringList();

	}

	private void getStringList() {

		proDialog.show();
		new Thread(downloadExample).start();
		handler = new Handler() {
			public void handleMessage(Message msg) {
				if (msg.what == 0) {
					// msg.obj是获取handler发送信息传来的数据
					sList = (ArrayList<String>) msg.obj;
					System.out.println("接收到了handler的数据");
				}

				sAdapter = new SearchExampleAdapter(mContext, sList);
				mlistView.setAdapter(sAdapter);
				proDialog.dismiss();
			}
		};

	}

	Runnable downloadExample = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub

			try {
				sList = new SearchExampleLoader().getExample();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			handler.sendMessage(handler.obtainMessage(0, sList));
		}
	};*/

	/**
	 * @author tianqiujie 添加自定义布局器InfoAdapter
	 */

	public void showall(List<SingleEntity> list) {

		if (list == null) {
			// 没有结果情况
			Toast.makeText(getApplicationContext(), "没有返回结果",
					Toast.LENGTH_SHORT).show();
		} else {
			mAdapater = new SearhResultAdapter(this, list);

			mlistView.setAdapter(mAdapater);
			mlistView.setOnScrollListener(mScrollListener);
		}
		proDialog.dismiss();
	}

	/**
	 * @author tianqiujie 跳转单个电影的界面 传入图片链接和点击索引值
	 */

	protected void onListItemClick(ListView l, View view, int position, long id) {

		mbp = aList.get(position);
		Intent intent = new Intent();
		// intent.setClass(MainActivity.this, ShortCommentActivity.class);
		// intent.setClass(MainActivity.this, MovieDetailActivity.class);

		intent.setClass(SearchResultPage.this, com.NG.activity.detailTest.class);
		Bundle bundle = new Bundle();
		bundle.putString("id", mbp.getFirstUrl().toString());
		bundle.putString("imageurl", mbp.getImageUrl().toString());
		// bundle.putString("type", "电影");
		intent.putExtras(bundle);
		startActivity(intent);

	}

	/**
	 * @author tianqiujie 当listView滚动停止以后才开始异步加载图片
	 */

	OnScrollListener mScrollListener = new OnScrollListener() {

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			switch (scrollState) {
			case OnScrollListener.SCROLL_STATE_FLING:
				mAdapater.setFlagBusy(true);
				break;
			case OnScrollListener.SCROLL_STATE_IDLE:
				mAdapater.setFlagBusy(false);
				break;
			case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
				mAdapater.setFlagBusy(false);
				break;
			default:
				break;
			}
			Log.d(TAG, "scroll");
			mAdapater.notifyDataSetChanged();
		}

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {

		}
	};

	// 提示框方法
	public void openOptionDialog(String string) {
		new AlertDialog.Builder(this).setTitle("提示")
				.setMessage(string + "不能为空!")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				}).show();
	}

	/*
	 * @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
	 * 
	 * if (keyCode == KeyEvent.KEYCODE_BACK) { if (flag == true) {
	 * 
	 * mlistView.setAdapter(sAdapter);
	 * 
	 * flag = false; } else { if (isExit == false) { isExit = true;
	 * Toast.makeText(this, "再按一次后退键退出应用程序", Toast.LENGTH_SHORT) .show();
	 * 
	 * } else { finish(); System.exit(0); } } } if (keyCode ==
	 * KeyEvent.KEYCODE_DEL){ System.out.println("KEYCODE_DEL"); }
	 * 
	 * return false; }
	 */

	/*
	 * @Override public boolean dispatchKeyEvent(KeyEvent event) {
	 * System.out.println(event.toString());
	 * 
	 * if (event.getAction() == KeyEvent.ACTION_UP && event.getKeyCode() ==
	 * KeyEvent.KEYCODE_BACK) { // 返回桌面 } if (event.getAction() ==
	 * KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_MENU) { //
	 * 弹出退出程序的确定框 } if (event.getAction() == KeyEvent.ACTION_UP &&
	 * event.getKeyCode() == KeyEvent.KEYCODE_DEL) ;
	 * 
	 * return super.dispatchKeyEvent(event); }
	 */

}