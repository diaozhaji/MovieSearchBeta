package com.NG.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.NG.loader.SimpleInfoLoder;
import com.NG.moviesearchbeta.R;
import com.NG.adapder.InfoAdapter;
import com.NG.adapder.SearhResultAdapter;
import com.NG.entity.SingleEntity;

/**
 * 
 * @author tianqiujie 程序入口
 */

public class MainActivity extends ListActivity {

	final static String TAG = "m_debug";
	final static int LOAD_DATA = 1;

	private ListView mlistView;// 存取搜索信息的列表控件

	private SearhResultAdapter mAdapater;// 用来加载BaseAdapater

	private ImageView search_button;// 搜索按钮
	public EditText editText;// 搜索框
	public List<SingleEntity> aList;// MovieBriefPojo 返回的泛型LIST

	private SingleEntity mbp;// 传递点击事件的 击点

	public Spinner sp;// 选择要搜索的类型

	private ProgressDialog proDialog;

	private String name;
	private Handler handler;
	String[] types = { "搜电影" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		initView();
		initProDialog();
	}

	private void initView() {
		mbp = new SingleEntity();
		search_button = (ImageView) findViewById(R.id.search_button);
		search_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				proDialog.show();
				searchData();

				// mHandler.sendEmptyMessage(LOAD_DATA);
			}
		});

		editText = (EditText) findViewById(R.id.edittext);

	}

	public void searchData() {
		name = editText.getText().toString();
		if (name.trim().length() < 1) {
			openOptionDialog("搜索条件");
			return;
		} else {
			new Thread(downloadRun).start();
		}

		handler = new Handler() {
			public void handleMessage(Message msg) {
				if (msg.what == 0) {
					// msg.obj是获取handler发送信息传来的数据
					@SuppressWarnings("unchecked")
					List<SingleEntity> seList = (ArrayList<SingleEntity>) msg.obj;
					// 给ListView绑定数据
					showall(seList);

				}
			}
		};
	}

	void initProDialog() {
		proDialog = new ProgressDialog(this);
		proDialog.setTitle(R.string.loading);
		proDialog.setMessage("请您耐心等待...");
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

	/**
	 * @author tianqiujie 添加自定义布局器InfoAdapter
	 */

	public void showall(List<SingleEntity> list) {
		mlistView = getListView();

		mAdapater = new SearhResultAdapter(this, list);

		mlistView.setAdapter(mAdapater);
		//mlistView.setOnScrollListener(mScrollListener);

		proDialog.dismiss();
	}

	/**
	 * @author tianqiujie 跳转单个电影的界面 传入图片链接和点击索引值
	 */

	protected void onListItemClick(ListView l, View view, int position, long id) {
		mbp = aList.get(position);
		Intent intent = new Intent();
		// intent.setClass(MainActivity.this, DetailActivity.class);
		intent.setClass(MainActivity.this, MovieDetailActivity.class);
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
}