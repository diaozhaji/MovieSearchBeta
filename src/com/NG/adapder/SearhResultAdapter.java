package com.NG.adapder;


import java.util.ArrayList;
import java.util.List;

import com.NG.cache.ImageLoader;
import com.NG.entity.SingleEntity;
import com.NG.moviesearchbeta.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SearhResultAdapter extends BaseAdapter{
	
	private static final String TAG = "SearhResultAdapter";
	private boolean mBusy = false;

	public void setFlagBusy(boolean busy) {
		this.mBusy = busy;
	}
	
	private Context mContext;
	private List<SingleEntity> aList;	
	private ImageLoader mImageLoader;
	
	
	public SearhResultAdapter(Context context, List<SingleEntity> seList) {
		this.mContext = context;
		aList = seList;
		mImageLoader = new ImageLoader(context);
	}
	
	public ImageLoader getImageLoader(){
		return mImageLoader;
	}
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return aList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if (position >= getCount()) {
			return null;
		}
		return aList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext)
					.inflate(R.layout.all_single_show, null);
			viewHolder = new ViewHolder();
			viewHolder.mTextView = (TextView) convertView
					.findViewById(R.id.all_title);
			viewHolder.contentTextView = (TextView)convertView
					.findViewById(R.id.all_content);
			viewHolder.mImageView = (ImageView) convertView
					.findViewById(R.id.allimageview);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}		
		SingleEntity movieBriefPojo = aList.get(position);
		
		viewHolder.mTextView.setText(movieBriefPojo.getMovieName());
		viewHolder.contentTextView.setText("µ¼ÑÝ£º" + movieBriefPojo.getAuthorName());		
		viewHolder.mImageView.setBackgroundResource(R.drawable.rc_item_bg);
		
		String url = movieBriefPojo.getImageUrl();
		Log.d(TAG, url);
		if (!mBusy) {
			mImageLoader.DisplayImage(url, viewHolder.mImageView, false);
		} else {
			mImageLoader.DisplayImage(url, viewHolder.mImageView, false);
		}
		
		return convertView;
	}
	
	static class ViewHolder {
		TextView mTextView;
		TextView contentTextView;
		ImageView mImageView;
	}

}
