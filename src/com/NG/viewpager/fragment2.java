package com.NG.viewpager;

import java.util.List;

import com.NG.adapter.ShortCommentAdapter;
import com.NG.entity.MovieDetailEntity;
import com.NG.entity.ShortComment;
import com.NG.loader.MovieDetailInfoLoader;
import com.NG.moviesearchbeta.R;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class fragment2 extends Fragment{
	
	final static String TAG = "fragment2";
	
	private View mMainView;
	
	private Context mContext;
	private ListView testListView;
	private ShortCommentAdapter mAdapter;
	//private ProgressDialog proDialog;
	
	private String url;
	private List<ShortComment> mlist;
	
	private MovieDetailEntity mMovie;
	private MovieDetailInfoLoader movieInfo;
	
	private LayoutInflater inflater;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.v("huahua", "fragment2-->onCreate()");
		
		inflater = getActivity().getLayoutInflater();
		mMainView = inflater.inflate(R.layout.short_comment_acitvity, (ViewGroup)getActivity().findViewById(R.id.viewpager), false);
		
		mContext = getActivity().getApplicationContext();
		initView();
		
				
		//String id = bundle.getString("id");
		//Log.d(TAG, "url");
		//url = "http://192.158.31.250/search/"+id+"/";
		url = "http://192.158.31.250/search/3649049/";
		
		new Thread(new LoadData()).start();
		//proDialog.show();
	
	}
	private void initView() {
		// TODO Auto-generated method stub
		testListView = (ListView) mMainView.findViewById(R.id.short_comment_activity_list);
		//proDialog = new ProgressDialog(mContext);
		//proDialog.setTitle(R.string.loading);
		//proDialog.setMessage("请您耐心等待...");	
		
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
			
			Bundle bundle2 = new Bundle();
			
			
			//proDialog.dismiss();
                
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

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.v("huahua", "fragment2-->onCreateView()");
		/*
		ViewGroup p = (ViewGroup) mMainView.getParent(); 
        if (p != null) { 
            p.removeAllViewsInLayout(); 
            Log.v("huahua", "fragment2-->移除已存在的View");
        } */
		
		return mMainView;
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.v("huahua", "fragment2-->onDestroy()");
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.v("huahua", "fragment2-->onPause()");
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.v("huahua", "fragment2-->onResume()");
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.v("huahua", "fragment2-->onStart()");
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.v("huahua", "fragment2-->onStop()");
	}

}
