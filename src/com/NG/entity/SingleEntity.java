package com.NG.entity;

/**
 * 
 * @author tianqiujie
 * ��Ӱ��Ҫʵ��
 *
 */
public class SingleEntity {
	private String authorName;		//��Ӱ������������
	private String title;			//��Ӱ��������
	private String firstUrl;		//��������
	private String imageUrl;		//ͼƬ��������
	private String adjs;			//���ݴ�
	private String user_tags;		//�û���ǩ
	
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
	

}
