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
 * @author jiyuan �������
 */

public class SearchResultPage extends ListActivity {

	final static String TAG = "SearchResultPage";

	private Context mContext;
	
	private ListView mlistView;// ��ȡ������Ϣ���б�ؼ�

	private SearchExampleAdapter sAdapter; // ���ڿ�ʼʱ��list
	private SearhResultAdapter mAdapater; // ��������BaseAdapater

	public List<SingleEntity> aList;// MovieBriefPojo ���صķ���LIST
	private SingleEntity mbp;// ���ݵ���¼��� ����
	public List<String> sList;

	public Spinner sp;// ѡ��Ҫ����������

	private ProgressDialog proDialog;

	private String name;
	private Handler handler;
	String[] types = { "�ѵ�Ӱ" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_result_page);
		mContext = this;
		name = "�����";

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
		proDialog.setMessage("�������ĵȴ�...");
	}

	public void searchData(String s) {
		name = s;
		new Thread(downloadRun).start();
		handler = new Handler() {
			public void handleMessage(Message msg) {
				if (msg.what == 0) {
					// msg.obj�ǻ�ȡhandler������Ϣ����������
					List<SingleEntity> seList = (ArrayList<SingleEntity>) msg.obj;
					// ��ListView������
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
					// msg.obj�ǻ�ȡhandler������Ϣ����������
					sList = (ArrayList<String>) msg.obj;
					System.out.println("���յ���handler������");
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
	 * @author tianqiujie ����Զ��岼����InfoAdapter
	 */

	public void showall(List<SingleEntity> list) {

		if (list == null) {
			// û�н�����
			Toast.makeText(getApplicationContext(), "û�з��ؽ��",
					Toast.LENGTH_SHORT).show();
		} else {
			mAdapater = new SearhResultAdapter(this, list);

			mlistView.setAdapter(mAdapater);
			mlistView.setOnScrollListener(mScrollListener);
		}
		proDialog.dismiss();
	}

	/**
	 * @author tianqiujie ��ת������Ӱ�Ľ��� ����ͼƬ���Ӻ͵������ֵ
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
		// bundle.putString("type", "��Ӱ");
		intent.putExtras(bundle);
		startActivity(intent);

	}

	/**
	 * @author tianqiujie ��listView����ֹͣ�Ժ�ſ�ʼ�첽����ͼƬ
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

	// ��ʾ�򷽷�
	public void openOptionDialog(String string) {
		new AlertDialog.Builder(this).setTitle("��ʾ")
				.setMessage(string + "����Ϊ��!")
				.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

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
	 * Toast.makeText(this, "�ٰ�һ�κ��˼��˳�Ӧ�ó���", Toast.LENGTH_SHORT) .show();
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
	 * KeyEvent.KEYCODE_BACK) { // �������� } if (event.getAction() ==
	 * KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_MENU) { //
	 * �����˳������ȷ���� } if (event.getAction() == KeyEvent.ACTION_UP &&
	 * event.getKeyCode() == KeyEvent.KEYCODE_DEL) ;
	 * 
	 * return super.dispatchKeyEvent(event); }
	 */

}