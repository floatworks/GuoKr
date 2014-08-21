package com.guokr.xml.model;

import java.util.List;

public class ArticleList {
	private int id;
	private List<Subject> subject; 
	private String title;
	private String author;
	private String time;
	private String comment;
	private String summary_img;
	private String summary;
	private String url;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getSummary_img() {
		return summary_img;
	}

	public void setSummary_img(String summary_img) {
		this.summary_img = summary_img;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Override
	public String toString() {
		return title + "_" + author + "_" + time + "_" + comment + "_"
				+ summary_img + "_" + summary;
	}
	
	public List<Subject> getSubject() {
		return subject;
	}

	public void setSubject(List<Subject> subject) {
		this.subject = subject;
	}


	
	public class Subject {
		private String text;
		private String bgcolor;

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public String getBgcolor() {
			return bgcolor;
		}

		public void setBgcolor(String bgcolor) {
			this.bgcolor = bgcolor;
		} 
	}
 
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
