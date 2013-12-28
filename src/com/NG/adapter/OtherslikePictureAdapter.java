package com.NG.adapter;

import java.util.ArrayList;
import java.util.List;

import com.NG.activity.detailTest;
import com.NG.cache.ImageLoader;
import com.NG.entity.OthersLike;
import com.NG.entity.Picture;
import com.NG.moviesearchbeta.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class OtherslikePictureAdapter extends BaseAdapter{ 
	private Context mContext;
	private LayoutInflater inflater; 
    private List<OthersLike> olList;
    private ImageLoader mImageLoader;
    
    
    public OtherslikePictureAdapter(Context context , List<OthersLike> list){
    	super(); 
    	mContext = context;
        inflater = LayoutInflater.from(context);
        olList = list;
        mImageLoader = new ImageLoader(context);
    }
    
 
    public int getCount() 
    { 
        if (null != olList) 
        { 
            return olList.size(); 
        } else
        { 
            return 0; 
        } 
    } 
 
    public Object getItem(int position) 
    { 
        return olList.get(position); 
    } 
 
    public long getItemId(int position) 
    { 
        return position; 
    } 
 
    public View getView(int position, View convertView, ViewGroup parent) 
    { 
        ViewHolder viewHolder; 
        if (convertView == null) 
        { 
            convertView = inflater.inflate(R.layout.otherlike_item, null); 
            viewHolder = new ViewHolder(); 
            viewHolder.title = (TextView) convertView.findViewById(R.id.otherslike_title); 
            viewHolder.image = (ImageView) convertView.findViewById(R.id.otherslike_image); 
            convertView.setTag(viewHolder); 
        } else
        { 
            viewHolder = (ViewHolder) convertView.getTag(); 
        } 
        final OthersLike ol = olList.get(position);
        
        viewHolder.title.setText(ol.getName());
        
        String url = ol.getImage_url();
        mImageLoader.DisplayImage(url, viewHolder.image, false);
        
        convertView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Activity activity = (Activity) mContext;
				Intent intent = new Intent(mContext, detailTest.class);
				Bundle bundle = new Bundle();
				bundle.putString("id", ol.getSubject_id());
				bundle.putString("imageurl", ol.getImage_url());
				intent.putExtras(bundle);
				activity.startActivity(intent);
				activity.finish();
			}
		});
        
        return convertView; 
    }

}

class ViewHolder 
{ 
    public TextView title; 
    public ImageView image; 
} 
