package com.NG.viewpager;

import com.NG.entity.MovieDetailEntity;
import com.NG.loader.MovieDetailInfoLoader;
import com.NG.moviesearchbeta.R;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class fragment1 extends Fragment {
	final static String TAG = "fragment1";
	private View mMainView;

	private Context mContext;
	private String url;
	private String imageUrl;

	private MovieDetailEntity mMovie;
	private MovieDetailInfoLoader movieInfo;

	private LayoutInflater inflater;

	// Views
	private TextView summaryView;
	private TextView countriesView;
	private TextView collectView;
	private TextView genresView;
	private TextView yearView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.v("huahua", "fragment1-->onCreate()");

		inflater = getActivity().getLayoutInflater();
		mMainView = inflater.inflate(R.layout.detail_summary,
				(ViewGroup) getActivity().findViewById(R.id.viewpager), false);

		mContext = getActivity().getApplicationContext();
		initView();

		url = "http://192.158.31.250/search/3649049/";

		new Thread(new LoadData()).start();

	}

	private void initView() {
		// TODO Auto-generated method stub
		countriesView = (TextView) mMainView.findViewById(R.id.countries);
		collectView = (TextView) mMainView.findViewById(R.id.collect_count);
		genresView = (TextView) mMainView.findViewById(R.id.genres);
		yearView = (TextView) mMainView.findViewById(R.id.year);
		summaryView = (TextView) mMainView.findViewById(R.id.detail_summary);

		movieInfo = new MovieDetailInfoLoader();
	}

	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message message) {

			try {
				countriesView.setText("地区：" + mMovie.getCountries());
				collectView.setText("人气：" + mMovie.getCollect_count());
				genresView.setText("类型：" + mMovie.getGenres());
				yearView.setText("上映时间：" + mMovie.getYear());
				summaryView.setText("\t" + mMovie.getSummary() + "...");

			} catch (Exception e) {
				// TODO: handle exception
			}

			// proDialog.dismiss();

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
		Log.v("huahua", "fragment1-->onCreateView()");
		/*
		ViewGroup p = (ViewGroup) mMainView.getParent();
		if (p != null) {
			p.removeAllViewsInLayout();
			Log.v("huahua", "fragment1-->移除已存在的View");
		}*/

		return mMainView;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.v("huahua", "fragment1-->onDestroy()");
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.v("huahua", "fragment1-->onPause()");
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.v("huahua", "fragment1-->onResume()");
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.v("huahua", "fragment1-->onStart()");
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.v("huahua", "fragment1-->onStop()");
	}

}
