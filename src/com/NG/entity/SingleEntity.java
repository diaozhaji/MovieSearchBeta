package com.NG.entity;

/**
 * 
 * @author tianqiujie
 * 电影概要实体
 *
 */
public class SingleEntity {
	private String authorName;		//电影导演名称属性
	private String title;			//电影名称属性
	private String firstUrl;		//链接属性
	private String imageUrl;		//图片链接属性
	private String adjs;			//形容词
	private String user_tags;		//用户标签
	private String year;
	private String rating_average; 
	private String countries;
	
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getFirstUrl() {
		return firstUrl;
	}
	public void setFirstUrl(String firstUrl) {
		this.firstUrl = firstUrl;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getMovieName() {
		return title;
	}
	public void setMovieName(String movieName) {
		this.title = movieName;
	}
	public String getAdjs() {
		return adjs;
	}
	public void setAdjs(String adj) {
		this.adjs = adj;
	}
	public String getUser_tags() {
		return user_tags;
	}
	public void setUser_tags(String user_tags) {
		this.user_tags = user_tags;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getRating_average() {
		return rating_average;
	}
	public void setRating_average(String rating_average) {
		this.rating_average = rating_average;
	}
	public String getCountries() {
		return countries;
	}
	public void setCountries(String countries) {
		this.countries = countries;
	}
	
	

}
