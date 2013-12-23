package com.NG.utils;

import java.util.ArrayList;
import java.util.List;
import java.lang.String.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.NG.entity.OthersLike;
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
	
	public static String dealAdjString(String s){
		String a[] = s.split(",");
		String result = "";

		for(int i=0;i<a.length;i++){
			String b[] = a[i].split("=");
			result += b[0]+" ";	
		}
		return result;
	}
	public static String dealUserTagsString(String s){
		String a[] = s.split("гд");
		String result = "";

		for(int i=0;i<a.length;i++){
			String b[] = a[i].split("<>");
			result += b[0]+" ";
		}
		return result;
	}
	
	public static ShortComment jsonObjectToShortComment(JSONObject j){		
		ShortComment sc = new ShortComment();		
		try {
			
			sc.setUserName(j.getString("user_name"));
			sc.setComment(j.getString("user_comment"));
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sc;
		
	}
	
	public static List<ShortComment> jsonArrayToShortCommentList(JSONArray ja){
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
	
	public static OthersLike jsonObjectToOthersLike(JSONObject j){		
		OthersLike ol = new OthersLike();	
		try {
			
			ol.setName(j.getString("name"));
			ol.setSubject_id(j.getString("subject_id"));
			ol.setImage_url(j.getString("image_url"));
			System.out.println(j.getString("image_url"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ol;
		
	}
	
	
	public static List<OthersLike> jsonArrayToOthersLikeList(JSONArray ja){
		int length = ja.length();
		OthersLike ol = new OthersLike();
		List<OthersLike> olList = new ArrayList<OthersLike>();
		for(int i=0;i<length;i++){
			try {
				
				ol = jsonObjectToOthersLike(ja.getJSONObject(i));
				olList.add(ol);
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		System.out.println("OthersLike : "+olList.toString());
		return olList;
		
	}
	
	
}
