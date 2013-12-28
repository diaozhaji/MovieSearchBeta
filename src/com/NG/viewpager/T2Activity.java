package com.NG.viewpager;

import java.util.ArrayList;
import java.util.List;

import com.NG.adapter.OtherslikePictureAdapter;
import com.NG.adapter.SquareAdapter;
import com.NG.moviesearchbeta.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

public class T2Activity extends Activity{
	private List<String> squaredList;
	private GridView gridView;
	private Context mContext;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.squared_activity);
		
		String[] a = {"热门",
		            "章子怡",
		            "搞笑",
		            "奥斯卡",
		            "还不错",
		            "惊悚",
		            "美国",
		            "日本",
		            "韩国"
		            ,"搞笑的电影"};
		
		squaredList = new ArrayList<String>();
		
		for(int i=0;i<a.length;i++){
			squaredList.add(a[i]);
		}
		
		gridView = (GridView) findViewById(R.id.squered_view);
		
		SquareAdapter sqadapter = new SquareAdapter(this, squaredList);
		sqadapter.getItem(0);
		gridView.setAdapter(sqadapter);
		
	}
}

