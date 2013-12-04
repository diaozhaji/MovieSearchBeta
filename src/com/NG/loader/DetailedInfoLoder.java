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
 * @author tianqiujie �����Ӧ���ѡ��� XML��ַ ��ͼƬURL ������ӦXML �����ݴ�����ص�Ӱ��ʵ��
 */

public class DetailedInfoLoder {
	private static final String TAG = "DetailedInfoLoder";
	
	public DetailedInfoLoder(){
		Log.d(TAG, "conduct");
	}
	
	
	/**
	 * 
	 * @param singleurl
	 *            ������ϸ��Ϣ��XML��ַ
	 * @param imageUrl
	 *            ��������ͼƬ
	 * @return �������ݵķ���LIST
	 */
	@SuppressWarnings("unused")
	public List<MovieDetaileEntityOld> findMovieJsonTwo(String singleUrl,
			String imageUrl) throws IOException, ParserConfigurationException,
			SAXException {	
		List<MovieDetaileEntityOld> result = new ArrayList<MovieDetaileEntityOld>();
		MovieDetaileEntityOld movieDetailedPojo = new MovieDetaileEntityOld();
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

		/*
		 * private String movieName;//��Ӱ���� private String author;//���� private
		 * String writer;//��� private String imageUrl;//ͼƬλ�� private String
		 * summary;//���ݼ�� private String webSite;//������ַ
		 */

		try {
			jsonObject = new JSONObject(stringBuilder.toString());
			Log.d(TAG, stringBuilder.toString());
			// ��Ӱ����
			movieDetailedPojo.setTitle(jsonObject.getString("title"));
			Log.d(TAG, jsonObject.getString("title"));
			
			// ͼƬurl
			movieDetailedPojo.setImageUrl(jsonObject.getString("image_medium"));
			Log.d(TAG, jsonObject.getString("image_medium"));
			// ���
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
