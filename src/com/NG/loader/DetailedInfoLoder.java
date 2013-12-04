package com.NG.loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import android.util.Log;

import com.NG.entity.MovieDetaileEntityOld;

/**
 * 
 * @author tianqiujie 传入对应点击选项的 XML地址 及图片URL 解析对应XML 将数据存入相关电影的实体
 */

public class DetailedInfoLoder {
	private static final String TAG = "DetailedInfoLoder";
	
	public DetailedInfoLoder(){
		Log.d(TAG, "conduct");
	}
	
	
	/**
	 * 
	 * @param singleurl
	 *            包含详细信息的XML地址
	 * @param imageUrl
	 *            被点击项的图片
	 * @return 存入数据的泛型LIST
	 */
	@SuppressWarnings("unused")
	public List<MovieDetaileEntityOld> findMovieJsonTwo(String singleUrl,
			String imageUrl) throws IOException, ParserConfigurationException,
			SAXException {	
		List<MovieDetaileEntityOld> result = new ArrayList<MovieDetaileEntityOld>();
		MovieDetaileEntityOld movieDetailedPojo = new MovieDetaileEntityOld();
		URL url = new URL(singleUrl);
		Log.d(TAG, singleUrl);
		
		// 获取数据存入StringBuilder里面
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(url.openStream()));
		for (String s = bufferedReader.readLine(); s != null; s = bufferedReader
				.readLine()) {
			stringBuilder.append(s);
		}		

		JSONObject jsonObject = null;

		/*
		 * private String movieName;//电影名称 private String author;//导演 private
		 * String writer;//编剧 private String imageUrl;//图片位置 private String
		 * summary;//内容简介 private String webSite;//官网网址
		 */

		try {
			jsonObject = new JSONObject(stringBuilder.toString());
			Log.d(TAG, stringBuilder.toString());
			// 电影名称
			movieDetailedPojo.setTitle(jsonObject.getString("title"));
			Log.d(TAG, jsonObject.getString("title"));
			
			// 图片url
			movieDetailedPojo.setImageUrl(jsonObject.getString("image_medium"));
			Log.d(TAG, jsonObject.getString("image_medium"));
			// 简介
			movieDetailedPojo.setSummary(jsonObject.getString("summary"));
			Log.d(TAG, jsonObject.getString("summary"));
			

			result.add(movieDetailedPojo);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

}
