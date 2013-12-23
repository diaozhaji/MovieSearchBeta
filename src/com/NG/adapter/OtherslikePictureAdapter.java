package com.NG.adapter;

import java.util.ArrayList;
import java.util.List;

import com.NG.entity.Picture;
import com.NG.moviesearchbeta.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class OtherslikePictureAdapter extends BaseAdapter{ 
	private LayoutInflater inflater; 
    private List<Picture> pictures; 
 
    public OtherslikePictureAdapter(String[] titles, int[] images, Context context) 
    { 
        super(); 
        pictures = new ArrayList<Picture>(); 
        inflater = LayoutInflater.from(context); 
        for (int i = 0; i < images.length; i++) 
        { 
            Picture picture = new Picture(titles[i], images[i]); 
            pictures.add(picture); 
        } 
    } 
 
    public int getCount() 
    { 
        if (null != pictures) 
        { 
            return pictures.size(); 
        } else
        { 
            return 0; 
        } 
    } 
 
    public Object getItem(int position) 
    { 
        return pictures.get(position); 
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
            convertView = inflater.inflate(R.layout.picture_item, null); 
            viewHolder = new ViewHolder(); 
            viewHolder.title = (TextView) convertView.findViewById(R.id.otherslike_title); 
            viewHolder.image = (ImageView) convertView.findViewById(R.id.otherslike_image); 
            convertView.setTag(viewHolder); 
        } else
        { 
            viewHolder = (ViewHolder) convertView.getTag(); 
        } 
        viewHolder.title.setText(pictures.get(position).getTitle()); 
        viewHolder.image.setImageResource(pictures.get(position).getImageId()); 
        return convertView; 
    }

}

class ViewHolder 
{ 
    public TextView title; 
    public ImageView image; 
} 
