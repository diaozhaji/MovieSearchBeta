package com.NG.activity;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.NG.entity.MovieDetailEntity;
import com.NG.loader.MovieDetailInfoLoader;
import com.NG.moviesearchbeta.R;


public class MovieDetailActivity extends Activity{
	final static String TAG = "MovieDetailActivity";
	
	private String url;
	private String imageUrl;
	private ProgressDialog proDialog;
	
	private MovieDetailEntity mMovie;
	private MovieDetailInfoLoader movieInfo;
	
	private TabHost tabhost;
	//Views
	private TextView titleView;
	private TextView summaryView;
	private ImageView image;
	private TextView ratingView;
	private TextView directorsView;
	private TextView castsView;
	private TextView countriesView;
	private TextView collectView;
	private TextView genresView;
	private TextView yearView;
	
	//button
	private ImageView backBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_activity_test);
		
		initView();
		
		
		try {
			initData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		new Thread(new LoadData()).start();
		proDialog.show();
	}
	
	
	private void initView() {
		// TODO Auto-generated method stub
		titleView = (TextView)findViewById(R.id.layout_title_txt);
		
		backBtn = (ImageView)findViewById(R.id.title_button_back);
		backBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
			
		});
		
		tabhost = (TabHost)findViewById(R.id.tabhost);  
        tabhost.setup();
        
        tabhost.addTab(tabhost.newTabSpec("tab1")     
                .setIndicator("��    ��",getResources().getDrawable(R.drawable.icon
                		))     
                .setContent(R.id.content1));     
        tabhost.addTab(tabhost.newTabSpec("tab2")     
                .setIndicator("��    ��",getResources().getDrawable(R.drawable.icon))     
                .setContent(R.id.text2));     
        tabhost.addTab(tabhost.newTabSpec("tab3")     
                .setIndicator("��ص�Ӱ",getResources().getDrawable(R.drawable.icon))     
                .setContent(R.id.text3));
		
		image = (ImageView)findViewById(R.id.detail_activity_img);
		summaryView = (TextView)findViewById(R.id.detail_summary);
		ratingView  = (TextView)findViewById(R.id.rating);
		directorsView = (TextView)findViewById(R.id.directors);
		castsView = (TextView)findViewById(R.id.casts);
		countriesView = (TextView)findViewById(R.id.countries);
		collectView = (TextView)findViewById(R.id.collect_count);
		genresView  = (TextView)findViewById(R.id.genres);
		yearView = (TextView)findViewById(R.id.year);
		
		
		proDialog = new ProgressDialog(this);
		proDialog.setTitle(R.string.loading);
		proDialog.setMessage("�������ĵȴ�...");	
		
	}


	private void initData() throws IOException {
		Bundle bundle = getIntent().getExtras();		
		String id = bundle.getString("id");
		imageUrl = bundle.getString("imageurl");
		//String id = "3926132";
		//imageUrl = "http://img3.douban.com/mpic/s4356687.jpg";		
		
		
		url = "http://192.158.31.250/search/"+id+"/";
		
		Log.d(TAG, imageUrl);
		Log.d(TAG, url);
		
		
		movieInfo = new MovieDetailInfoLoader();
	}
	
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message message) {
			
			titleView.setText(mMovie.getTitle());			
			try{
				
				ratingView.setText(mMovie.getRating_average());				
				directorsView.setText("���ݣ�"+mMovie.getDirectors());
				castsView.setText("��Ա��"+mMovie.getCasts());
				countriesView.setText("������"+mMovie.getCountries());
				collectView.setText("������"+mMovie.getCollect_count());
				genresView.setText("���ͣ�"+mMovie.getGenres());
				yearView.setText("��ӳʱ�䣺"+mMovie.getYear());
				summaryView.setText("\t"+mMovie.getSummary() + "...");
				/*
				String summary = mMovie.getSummary();
				int maxLen = 220;
				if (summary.length() > maxLen) {
					summaryView.setText("\t"+summary.substring(0, maxLen) + "...");
				}*/
			} catch (Exception e) {
				// TODO: handle exception
			}
			new Thread(){
				public void run(){
					try {
						URL aryURI = new URL(mMovie.getImage_medium());
						InputStream is = aryURI.openStream();
						Bitmap bm = BitmapFactory.decodeStream(is);
						if (bm == null) {
							image.setBackgroundColor(R.drawable.detail_img_loading);
						}
						is.close();
						image.setImageBitmap(bm);
						
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				
			}.start();
			
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
					
				mHandler.sendEmptyMessage(choice);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
