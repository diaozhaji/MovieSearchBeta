package com.NG.adapter;

import java.util.List;

import com.NG.moviesearchbeta.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SquareAdapter extends BaseAdapter{
	private Context mContext;
	private LayoutInflater inflater; 
    private List<String> sqList;
    
    
    public SquareAdapter(Context context , List<String> list){
    	super(); 
    	mContext = context;
        inflater = LayoutInflater.from(context);
        sqList = list;
    }
    
    
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (null != sqList) 
        { 
            return sqList.size(); 
        } else
        { 
            return 0; 
        } 
	}
	
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return sqList.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		// TODO Auto-generated method stub
		SquareViewHolder viewHolder; 
        if (convertView == null) 
        { 
            convertView = inflater.inflate(R.layout.squared_item, null); 
            viewHolder = new SquareViewHolder(); 
            viewHolder.title = (TextView) convertView.findViewById(R.id.squared_title);  
            convertView.setTag(viewHolder); 
        } else
        { 
            viewHolder = (SquareViewHolder) convertView.getTag(); 
        } 
        final String sq = sqList.get(position);
        viewHolder.title.setText(sq);
        
        return convertView; 
	}
}

class SquareViewHolder 
{ 
    public TextView title; 
} 
