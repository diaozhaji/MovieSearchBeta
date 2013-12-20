package com.NG.activity;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.NG.activity.MovieDetailActivity.LoadData;
import com.NG.adapter.ShortCommentAdapter;
import com.NG.entity.MovieDetailEntity;
import com.NG.entity.ShortComment;
import com.NG.loader.MovieDetailInfoLoader;
import com.NG.loader.SimpleInfoLoder;
import com.NG.moviesearchbeta.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ListView;

public class ShortCommentActivity extends Activity{
	
	final static String TAG = "ShortCommentActivity";
	
	private Context mContext;
	private ListView testListView;
	private ShortCommentAdapter mAdapter;
	private ProgressDialog proDialog;
	
	private String url;
	private List<ShortComment> mlist;
	
	private MovieDetailEntity mMovie;
	private MovieDetailInfoLoader movieInfo;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.short_comment_acitvity);
		mContext = this;
		initView();
		Log.d(TAG, "url");
		
		url = "http://192.158.31.250/search/3649049/";
		
		new Thread(new LoadData()).start();
		proDialog.show();
	}

	private void initView() {
		// TODO Auto-generated method stub
		testListView = (ListView)findViewById(R.id.short_comment_activity_list);
		proDialog = new ProgressDialog(this);
		proDialog.setTitle(R.string.loading);
		proDialog.setMessage("ÇëÄúÄÍÐÄµÈ´ý...");	
		
		movieInfo = new MovieDetailInfoLoader();
	}
	
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message message) {
					
			try{
				
				mlist = mMovie.getShort_comments();
				mAdapter = new ShortCommentAdapter( mContext , mlist);
				testListView.setAdapter(mAdapter);
				
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			proDialog.dismiss();
                
		}
	};
	class LoadData implements Runnable {

		@Override
		public void run() {
			int choice = 0;
			Log.d(TAG, "run()");
			try {				
				mMovie = movieInfo.parserMovieJson(url);	
				Log.d(TAG, "mMovie");
				mHandler.sendEmptyMessage(choice);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	
	
}
