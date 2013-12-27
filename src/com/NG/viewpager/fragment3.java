package com.NG.viewpager;

import java.util.List;

import com.NG.adapter.OtherslikePictureAdapter;
import com.NG.adapter.ShortCommentAdapter;
import com.NG.entity.MovieDetailEntity;
import com.NG.entity.OthersLike;
import com.NG.loader.MovieDetailInfoLoader;
import com.NG.moviesearchbeta.R;
import com.NG.viewpager.fragment2.LoadData;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class fragment3 extends Fragment {
	private View mMainView;
	private LayoutInflater inflater;
	private Context mContext;
	private String url;
	
	private MovieDetailEntity mMovie;
	private MovieDetailInfoLoader movieInfo;
	
	// gridview
	private GridView gridView;
	
	private List<OthersLike> othersLikeList;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.v("huahua", "fragment3-->onCreate()");

		inflater = getActivity().getLayoutInflater();
		mMainView = inflater.inflate(R.layout.short_comment_acitvity, (ViewGroup)getActivity().findViewById(R.id.viewpager), false);
		
		mContext = getActivity().getApplicationContext();
		initView();
		
		url = "http://192.158.31.250/search/3649049/";
		
		new Thread(new LoadData()).start();
		
	}

	private void initView() {
		// TODO Auto-generated method stub
		gridView = (GridView) mMainView.findViewById(R.id.tab3); 
		
		movieInfo = new MovieDetailInfoLoader();
	}
	
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message message) {
					
			try{
				
				othersLikeList = mMovie.getOthers_like();
				OtherslikePictureAdapter oladapter = new OtherslikePictureAdapter(mContext,othersLikeList);
				gridView.setAdapter(oladapter);
				
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("没关相关电影");
			}
			
			//proDialog.dismiss();
                
		}
	};
	class LoadData implements Runnable {

		@Override
		public void run() {
			int choice = 0;
			try {				
				mMovie = movieInfo.parserMovieJson(url);	
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
		Log.v("huahua", "fragment3-->onCreateView()");

		ViewGroup p = (ViewGroup) mMainView.getParent();
		if (p != null) {
			p.removeAllViewsInLayout();
			Log.v("huahua", "fragment3-->移除已存在的View");
		}

		return mMainView;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.v("huahua", "fragment3-->onDestroy()");
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.v("huahua", "fragment3-->onPause()");
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.v("huahua", "fragment3-->onResume()");
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.v("huahua", "fragment3-->onStart()");
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.v("huahua", "fragment3-->onStop()");
	}

}
