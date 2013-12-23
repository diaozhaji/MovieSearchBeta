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

import com.NG.entity.MovieDetailEntity;
import com.NG.entity.MovieDetaileEntityOld;
import com.NG.entity.OthersLike;
import com.NG.entity.ShortComment;
import com.NG.utils.StringUtil;

import android.util.Log;

public class MovieDetailInfoLoader {
	private static final String TAG = "MovieDetailInfoLoader";
	//private List<ShortComment> mlist = new ArrayList<ShortComment>();
	public MovieDetailInfoLoader(){
		Log.d(TAG, "constractor()  do thing");
	}
	
	public MovieDetailEntity parserMovieJson(String singleUrl)
				throws IOException, ParserConfigurationException,SAXException {
		List<MovieDetailEntity> result = new ArrayList<MovieDetailEntity>();
		MovieDetailEntity movieDetailedPojo = new MovieDetailEntity();
		List<ShortComment> sclist = new ArrayList<ShortComment>();
		List< OthersLike > ollist = new ArrayList<OthersLike>();
		
		URL url = new URL(singleUrl);
		Log.d(TAG, singleUrl);
		
		// ��ȡ���ݴ���StringBuilder����
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(url.openStream()));
		for (String s = bufferedReader.readLine(); s != null; s = bufferedReader
				.readLine()) {
			stringBuilder.append(s);
		}		

		JSONObject jsonObject = null;
		JSONArray commentsJson = null;
		JSONArray otherslikeJson = null;
		
		try {
			jsonObject = new JSONObject(stringBuilder.toString());
			Log.d(TAG, stringBuilder.toString());
			// ��Ӱ����
			movieDetailedPojo.setTitle(jsonObject.getString("title"));			
			// ͼƬurl
			movieDetailedPojo.setImage_medium(jsonObject.getString("image_medium"));
			// ���
			movieDetailedPojo.setSummary(jsonObject.getString("summary"));			
			// ����
			movieDetailedPojo.setRating_average(jsonObject.getString("rating_average"));
			Log.d(TAG, movieDetailedPojo.getRating_average());
			// ����
			String directors = jsonObject.getString("directors");
			directors = StringUtil.removeDelimiter(directors);
			movieDetailedPojo.setDirectors(directors);
			Log.d(TAG, movieDetailedPojo.getDirectors());
			//���
			movieDetailedPojo.setYear(jsonObject.getString("year"));
			Log.d(TAG, movieDetailedPojo.getYear());
			//xxx�˿���---����
			movieDetailedPojo.setCollect_count(jsonObject.getString("collect_count"));
			Log.d(TAG, movieDetailedPojo.getCollect_count());
			//����
			String countries = jsonObject.getString("countries");
			countries = StringUtil.removeDelimiter(countries);
			movieDetailedPojo.setCountries(countries);
			Log.d(TAG, "countriey:"+movieDetailedPojo.getCountries());
			//����
			String genres = jsonObject.getString("genres");
			genres = StringUtil.removeDelimiter(genres);
			movieDetailedPojo.setGenres(genres);
			Log.d(TAG, "����:"+movieDetailedPojo.getGenres());
			//����
			String casts = jsonObject.getString("casts");
			casts = StringUtil.removeDelimiter(casts);
			movieDetailedPojo.setCasts(casts);
			Log.d(TAG, "����:"+movieDetailedPojo.getCasts());
			//�û���ǩ
			String userTags = jsonObject.getString("user_tags");
			userTags = StringUtil.dealUserTagsString(userTags);
			movieDetailedPojo.setUser_tags(userTags);
			Log.d(TAG, "��ǩ��"+userTags);
			
			commentsJson = jsonObject.getJSONArray("comments");
			Log.d(TAG, "commentsJson's length is:"+commentsJson.length());
			sclist = StringUtil.jsonArrayToShortCommentList(commentsJson);
			//Log.d(TAG, "comments :"+commentsJson.getJSONObject(2).getString("user_comment"));
			movieDetailedPojo.setShort_comments(sclist);
			
			otherslikeJson = jsonObject.getJSONArray("others_like");
			ollist = StringUtil.jsonArrayToOthersLikeList(otherslikeJson);
			movieDetailedPojo.setOthers_like(ollist);
			
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return movieDetailedPojo;


		
	}

}
