package com.NG.viewpager;

import java.util.ArrayList;
import java.util.List;

import com.NG.moviesearchbeta.R;


import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.ViewGroup;

public class ViewPagerActivity extends FragmentActivity {
	private ViewPager m_vp;
	private fragment1 mfragment1;
	private fragment2 mfragment2;
	private fragment3 mfragment3;
	//ҳ���б�
	private ArrayList<Fragment> fragmentList;
	//�����б�
	ArrayList<String>   titleList    = new ArrayList<String>();
	//ͨ��pagerTabStrip�������ñ��������
	private PagerTabStrip pagerTabStrip;
	
	private PagerTitleStrip pagerTitleStrip;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpager_activity);
		
		m_vp = (ViewPager)findViewById(R.id.viewpager);
		
		pagerTabStrip=(PagerTabStrip) findViewById(R.id.pagertab);
		//�����»��ߵ���ɫ
		pagerTabStrip.setTabIndicatorColor(getResources().getColor(android.R.color.holo_green_dark)); 
		//���ñ�������ɫ
		pagerTabStrip.setBackgroundColor(getResources().getColor(android.R.color.white));
		
//		pagerTitleStrip=(PagerTitleStrip) findViewById(R.id.pagertab);
//		//���ñ�������ɫ
//		pagerTitleStrip.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
		
		mfragment1 = new fragment1();
		mfragment2 = new fragment2();
		mfragment3 = new fragment3();

		fragmentList = new ArrayList<Fragment>();
		fragmentList.add(mfragment1);
		fragmentList.add(mfragment2);
		fragmentList.add(mfragment3);
		
	    titleList.add("��һҳ ");
	    titleList.add("�ڶ�ҳ");
	    titleList.add("����ҳ ");
		
		m_vp.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));
		//m_vp.setOffscreenPageLimit(2);
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
