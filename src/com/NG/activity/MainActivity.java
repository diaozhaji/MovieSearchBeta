package com.NG.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.view.KeyEvent;
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
import android.widget.Toast;

import com.NG.loader.SearchExampleLoader;
import com.NG.loader.SimpleInfoLoder;
import com.NG.moviesearchbeta.R;
import com.NG.adapter.SearchExampleAdapter;
import com.NG.adapter.SearhResultAdapter;
import com.NG.entity.SingleEntity;

/**
 * 
 * @author tianqiujie �������
 */

public class MainActivity extends ListActivity {

	final static String TAG = "SearchMovie";
	final static int LOAD_DATA = 1;
	
	private static boolean flag = false;	//���ڹ����һ��List�ͽ��List
	private static boolean isExit = false;	//���ڹ����Ƿ��˳�Ӧ��
	private Context mContext;
	private ListView mlistView;// ��ȡ������Ϣ���б�ؼ�
	
	private SearchExampleAdapter sAdapter;	// ���ڿ�ʼʱ��list
	private SearhResultAdapter mAdapater;	// ��������BaseAdapater

	private ImageView search_button;// ������ť
	public EditText editText;// ������
	
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
		setContentView(R.layout.main);
		mContext = this;
		
		initProDialog();
		initView();
		initData();
		
	}

	private void initData() {
		// TODO Auto-generated method stub
		sList = new ArrayList<String> ();
		
		getStringList();
		
		
		
	}
	private void getStringList(){
		/*
		String[] a = {"���뿴�������ĵ�Ӱ","����������ı�ĵ�Ӱ",
				"��һ�»��ֵĵ�Ӱ","��ү�ҵ㾪㤵ĵ�Ӱ",
				"�����ҵ����˵ĵ�Ӱô","��һ��̼��ĵ�Ӱ����"};
		for(int i=0;i<a.length;i++){
			sList.add(a[i]);
		}*/
		proDialog.show();
		new Thread(downloadExample).start();
		handler = new Handler() {
			public void handleMessage(Message msg) {
				if (msg.what == 0) {
					// msg.obj�ǻ�ȡhandler������Ϣ����������
					sList = (ArrayList<String>) msg.obj;
					System.out.println("���յ���handler������");
				}
				
				sAdapter = new SearchExampleAdapter(mContext,sList);
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
	};

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
		
		mlistView = getListView();
		
	}

	public void searchData() {
		name = editText.getText().toString();
		if (name.trim().length() < 1) {
			openOptionDialog("��������");
			proDialog.dismiss();
			return;
			
		} else {
			new Thread(downloadRun).start();
		}

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
		//proDialog.dismiss();
	}

	void initProDialog() {
		proDialog = new ProgressDialog(this);
		proDialog.setTitle(R.string.loading);
		proDialog.setMessage("�������ĵȴ�...");
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
	 * @author tianqiujie ����Զ��岼����InfoAdapter
	 */

	public void showall(List<SingleEntity> list) {
		
		if(list==null){
			//û�н�����
			Toast.makeText(getApplicationContext(), "û�з��ؽ��",
					Toast.LENGTH_SHORT).show();
		}else{
			mAdapater = new SearhResultAdapter(this, list);

			mlistView.setAdapter(mAdapater);
			mlistView.setOnScrollListener(mScrollListener);
			flag = true;
		}	
		proDialog.dismiss();
	}

	/**
	 * @author tianqiujie ��ת������Ӱ�Ľ��� ����ͼƬ���Ӻ͵������ֵ
	 */

	protected void onListItemClick(ListView l, View view, int position, long id) {
		if(!flag){
			editText.setText(sList.get(position));
		}
		if(flag){
			mbp = aList.get(position);
			Intent intent = new Intent();
			//intent.setClass(MainActivity.this, ShortCommentActivity.class);
			
			intent.setClass(MainActivity.this, MovieDetailActivity.class);
			intent.setClass(MainActivity.this, detailTest.class);
			Bundle bundle = new Bundle();
			bundle.putString("id", mbp.getFirstUrl().toString());
			bundle.putString("imageurl", mbp.getImageUrl().toString());
			// bundle.putString("type", "��Ӱ");
			intent.putExtras(bundle);
			startActivity(intent);
		}
		
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
			Log.d(TAG,"scroll");
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
	
	
    
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		System.out.println("TabHost_Index.java onKeyDown");
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if(flag == true){
				
				mlistView.setAdapter(sAdapter);
				
				flag = false;
			}
			else{
				if(isExit == false ) {
					isExit = true;
					Toast.makeText(this, "�ٰ�һ�κ��˼��˳�Ӧ�ó���", Toast.LENGTH_SHORT).show();

				} else {
					finish();
					System.exit(0);
				}
			}
		}
		return false;
	}
}