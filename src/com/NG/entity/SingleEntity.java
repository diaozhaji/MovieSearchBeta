package com.NG.entity;

/**
 * 
 * @author tianqiujie
 * ��Ӱ��Ҫʵ��
 *
 */
public class SingleEntity {
	private String authorName;//��Ӱ������������
	private String title;//��Ӱ��������
	private String firstUrl;//��������
	private String imageUrl;//ͼƬ��������
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

}
