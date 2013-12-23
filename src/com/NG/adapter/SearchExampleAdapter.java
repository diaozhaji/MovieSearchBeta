package com.NG.adapter;

import java.util.List;
import com.NG.moviesearchbeta.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SearchExampleAdapter extends BaseAdapter{
	
	private static final String TAG = "SearchExampleAdapter";
	
	private Context mContext;
	private List<String> aList;	

	
	
	public SearchExampleAdapter(Context context, List<String> sList) {
		this.mContext = context;
		aList = sList;

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
					.inflate(R.layout.search_example_item, null);
			viewHolder = new ViewHolder();
			viewHolder.mTextView = (TextView) convertView
						.findViewById(R.id.search_example_text);

			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}		
		String s = aList.get(position);
		
		viewHolder.mTextView.setText(s);

		
		return convertView;
	}
	
	static class ViewHolder {
		TextView mTextView;
		
	}

}
