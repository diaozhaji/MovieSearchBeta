package com.NG.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.NG.entity.ShortComment;

/**
 * @author JiYuan
 * 
 */
public class StringUtil {
	
	public static String removeDelimiter(String s){
		s = s.replace("гд", "  ");
		return s;		
	}
	
	public static ShortComment jsonObjectToShortComment(JSONObject j){		
		ShortComment sc = new ShortComment();		
		try {
			
			sc.setUserName(j.getString("user_name"));
			sc.setComment(j.getString("user_comment"));
			System.out.println(j.getString("user_comment"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sc;
		
	}
	
	public static List<ShortComment> jsonArrayToList(JSONArray ja){
		int length = ja.length();
		ShortComment sc = new ShortComment();
		List<ShortComment> scList = new ArrayList<ShortComment>();
		
		for(int i=0;i<length;i++){
			try {
				
				sc = jsonObjectToShortComment(ja.getJSONObject(i));
				scList.add(sc);
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return scList;
		
	}
	
	
}
