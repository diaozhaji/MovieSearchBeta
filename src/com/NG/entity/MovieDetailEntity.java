package com.NG.entity;
/**
 * @author jiyuan
 * Movie Entity
 * 
 * */


public class MovieDetailEntity {

	private String subject_id;		//��Ӱid (����붹���API��ͬ)
	private String title;			//����
	private String original_title;	//ԭ��
	private String aka;				//����
	private String rating_average;	//����
	private String ratings_count;	//��������
	private String wish_count;		//�뿴����
	private String collect_count;	//��������	
	private String image_medium;	//��ͼƬ����  (����붹��API��ͬ)	
	private String directors;		//����
	private String countries;		//��Ƭ����/����
	private String genres;			//��Ӱ����
	private String casts;			//��Ա
	private String summary;			//����
	private String comments_count;	//��������
	private String reviews_count;	//Ӱ������
	private String year;			//���
	private String summary_segmentation;	//???
	private Object comments;
	/*
	private String subtype;			//��Ŀ����, movie����tv
	private String image_small;
	private String image_large;
	private String rating_stars;	//�Ǽ�  ?????
	private String rating_max;
	private String rating_min;
	private String douban_site;
	private String mobile_url;		//�ƶ�����ĿҳURL
	private String do_count;		//�ڿ�����������ǵ��Ӿ磬Ĭ��ֵΪ0������ǵ�ӰֵΪnull

	private String seasons_count;	//�ܼ���(tv only)
	private String current_season;	//��ǰ����(tv only)
	private String episodes_count;	//��ǰ���ļ���(tv only)
	private String schedule_url;	//ӰѶҳURL

	 */
	
	public String getTitle() {
		return title;
	}
	public String getOriginal_title() {
		return original_title;
	}
	public void setOriginal_title(String original_title) {
		this.original_title = original_title;
	}
	public String getAka() {
		return aka;
	}
	public void setAka(String aka) {
		this.aka = aka;
	}
	public String getWish_count() {
		return wish_count;
	}
	public void setWish_count(String wish_count) {
		this.wish_count = wish_count;
	}
	public String getReviews_count() {
		return reviews_count;
	}
	public void setReviews_count(String reviews_count) {
		this.reviews_count = reviews_count;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getSummary_segmentation() {
		return summary_segmentation;
	}
	public void setSummary_segmentation(String summary_segmentation) {
		this.summary_segmentation = summary_segmentation;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubject_id() {
		return subject_id;
	}
	public void setSubject_id(String subject_id) {
		this.subject_id = subject_id;
	}
	public String getCountries() {
		return countries;
	}
	public void setCountries(String countries) {
		this.countries = countries;
	}
	public String getRating_average() {
		return rating_average;
	}
	public void setRating_average(String rating_average) {
		this.rating_average = rating_average;
	}
	public String getGenres() {
		return genres;
	}
	public void setGenres(String genres) {
		this.genres = genres;
	}
	public String getImage_medium() {
		return image_medium;
	}
	public void setImage_medium(String image_medium) {
		this.image_medium = image_medium;
	}
	public String getCasts() {
		return casts;
	}
	public void setCasts(String casts) {
		this.casts = casts;
	}
	public String getCollect_count() {
		return collect_count;
	}
	public void setCollect_count(String collect_count) {
		this.collect_count = collect_count;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDirectors() {
		return directors;
	}
	public void setDirectors(String directors) {
		this.directors = directors;
	}
	public String getComments_count() {
		return comments_count;
	}
	public void setComments_count(String comments_count) {
		this.comments_count = comments_count;
	}
	public String getRatings_count() {
		return ratings_count;
	}
	public void setRatings_count(String ratings_count) {
		this.ratings_count = ratings_count;
	}
	
	
}
