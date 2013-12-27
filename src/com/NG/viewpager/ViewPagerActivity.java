package com.NG.viewpager;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.NG.adapter.OtherslikePictureAdapter;
import com.NG.adapter.ShortCommentAdapter;
import com.NG.entity.MovieDetailEntity;
import com.NG.entity.OthersLike;
import com.NG.entity.ShortComment;
import com.NG.loader.MovieDetailInfoLoader;
import com.NG.moviesearchbeta.R;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.TabHost.OnTabChangeListener;

public class ViewPagerActivity extends FragmentActivity {
	final static String TAG = "ViewPagerActivity";
	
	private ViewPager m_vp;
	private fragment1 mfragment1;
	private fragment2 mfragment2;
	private fragment3 mfragment3;
	//页面列表
	private ArrayList<Fragment> fragmentList;
	//标题列表
	ArrayList<String>   titleList    = new ArrayList<String>();
	//通过pagerTabStrip可以设置标题的属性
	private PagerTabStrip pagerTabStrip;
	
	private PagerTitleStrip pagerTitleStrip;
	private FragmentActivity mThis;
	private Context mContext;
	private String url;
	private String imageUrl;
	private ProgressDialog proDialog;
	
	private MovieDetailEntity mMovie;
	private MovieDetailInfoLoader movieInfo;
	
	private ListView shortCommentsListView;
	//private ShortCommentAdapter mAdapter;
	//private List<ShortComment> shortCommentList;
	//private List<OthersLike> othersLikeList;
	
	//Views
	private TextView titleView;
	private TextView summaryView;
	private ImageView image;
	private TextView ratingView;
	private TextView directorsView;
	private TextView castsView;
	private TextView userTagsView;
	private TextView countriesView;
	private TextView collectView;
	private TextView genresView;
	private TextView yearView;
	
	//button
	private ImageView backBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpager_activity);
		
		m_vp = (ViewPager)findViewById(R.id.viewpager);
		
		pagerTabStrip=(PagerTabStrip) findViewById(R.id.pagertab);
		//设置下划线的颜色
		pagerTabStrip.setTabIndicatorColor(getResources().getColor(android.R.color.holo_green_dark)); 
		//设置背景的颜色
		pagerTabStrip.setBackgroundColor(this.getResources().getColor(R.color.detail_bg_white));
		
//		pagerTitleStrip=(PagerTitleStrip) findViewById(R.id.pagertab);
//		//设置背景的颜色
//		pagerTitleStrip.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
		
		mfragment1 = new fragment1();
		mfragment2 = new fragment2();
		mfragment3 = new fragment3();

		fragmentList = new ArrayList<Fragment>();
		fragmentList.add(mfragment1);
		fragmentList.add(mfragment2);
		fragmentList.add(mfragment3);
		
	    titleList.add("详  情 ");
	    titleList.add("短  评");
	    titleList.add("相关电影 ");
		
		m_vp.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));
		//m_vp.setOffscreenPageLimit(2);
		
		
		mContext = this;
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
		
        
        image = (ImageView)findViewById(R.id.detail_activity_img);
		
		ratingView  = (TextView)findViewById(R.id.rating);
		directorsView = (TextView)findViewById(R.id.directors);
		castsView = (TextView)findViewById(R.id.casts);
		userTagsView = (TextView)findViewById(R.id.user_tags);
		
		//tab1 view
		countriesView = (TextView)findViewById(R.id.countries);
		collectView = (TextView)findViewById(R.id.collect_count);
		genresView  = (TextView)findViewById(R.id.genres);
		yearView = (TextView)findViewById(R.id.year);
		summaryView = (TextView)findViewById(R.id.detail_summary);
        
        //tab3 view
        //gridView = (GridView) findViewById(R.id.tab3); 
		
         
        
        //ProgressDialog
		proDialog = new ProgressDialog(this);
		proDialog.setTitle(R.string.loading);
		proDialog.setMessage("请您耐心等待...");	
		
	}
	




	private void initData() throws IOException {
		Bundle bundle = getIntent().getExtras();		
		//String id = bundle.getString("id");
		//imageUrl = bundle.getString("imageurl");
		String id = "3541415";
		imageUrl = "http://img3.douban.com/mpic/s4356687.jpg";		
		
		
		url = "http://192.158.31.250/search/"+id+"/";
		
		movieInfo = new MovieDetailInfoLoader();
	}
	
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message message) {
			
			Bundle bundle = new Bundle();
			bundle.putString("movie", "哈哈哈哈");
			
			titleView.setText(mMovie.getTitle());			
			try{
				
				ratingView.setText(mMovie.getRating_average());				
				directorsView.setText("导演："+mMovie.getDirectors());
				castsView.setText("演员："+mMovie.getCasts());
				userTagsView.setText("用户标签："+mMovie.getUser_tags());
				countriesView.setText("地区："+mMovie.getCountries());
				collectView.setText("人气："+mMovie.getCollect_count());
				genresView.setText("类型："+mMovie.getGenres());
				yearView.setText("上映时间："+mMovie.getYear());
				summaryView.setText("\t"+mMovie.getSummary() + "...");
				/*
				String summary = mMovie.getSummary();
				int maxLen = 220;
				if (summary.length() > maxLen) {
					summaryView.setText("\t"+summary.substring(0, maxLen) + "...");
				}*/
				
				//shortCommentList = mMovie.getShort_comments();				
				//othersLikeList = mMovie.getOthers_like();
				
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			/*
			if(shortCommentList==null){	
				System.out.println("没有短评");
			}
			else{
				mAdapter = new ShortCommentAdapter( mContext , shortCommentList);
				shortCommentsListView.setAdapter(mAdapter);
			}
			
			if(othersLikeList == null){
				System.out.println("没有其他用户也喜欢");
			}
			else{
				OtherslikePictureAdapter oladapter = new OtherslikePictureAdapter(mContext,othersLikeList);
				gridView.setAdapter(oladapter);
			}*/
			
			
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
	
	
	
	public class MyViewPagerAdapter extends FragmentPagerAdapter{
		public MyViewPagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public Fragment getItem(int arg0) {
			return fragmentList.get(arg0);
		}

		@Override
		public int getCount() {
			return fragmentList.size();
		}

		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			return titleList.get(position);
		}

		
	}

}
