package com.NG.adapter;

import java.util.List;

import com.NG.adapter.SearhResultAdapter.ViewHolder;
import com.NG.entity.ShortComment;
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

public class ShortCommentAdapter extends BaseAdapter {
	
	private static final String TAG = "ShortCommentAdapter";
	
	private Context mContext;
	private List<ShortComment> shortCommentList;
	
	public ShortCommentAdapter(Context context, List<ShortComment> list){
		this.mContext = context;
		shortCommentList = list;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return shortCommentList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if (position >= getCount()) {
			return null;
		}
		return shortCommentList.get(position);
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
					.inflate(R.layout.short_comment_show, null);
			viewHolder = new ViewHolder();
			viewHolder.mTextView = (TextView) convertView
					.findViewById(R.id.short_comment_text);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}		
		ShortComment mSC = shortCommentList.get(position);
		
		viewHolder.mTextView.setText(mSC.getComment());
		
		
		return convertView;
	}
	
	static class ViewHolder {
		TextView mTextView;
		
	}

}
