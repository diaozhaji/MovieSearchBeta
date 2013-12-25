package com.NG.loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchExampleLoader {
	public List<String> getExample() throws IOException{
		
		List<String> result = new ArrayList<String>();
		
		String uString = "http://192.158.31.250/search/navigation_list/?type=0";
		URL url = new URL(uString);

		StringBuilder builder = new StringBuilder();
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(url.openStream()));
		for (String s = bufferedReader.readLine(); s != null; s = bufferedReader
				.readLine()) {
			builder.append(s);
		}
		
		JSONArray jsonArray = null;
		try {
			JSONObject jsonObject = new JSONObject(builder.toString());
			jsonArray = jsonObject.getJSONArray("data");
			
			for(int i=0;i<jsonArray.length();i++){
				System.out.println(jsonArray.getString(i));
				result.add(jsonArray.getString(i));
			}
			
		} catch (JSONException e) {

			e.printStackTrace();
		}
		
		
		
		return result;
		
	}
}
