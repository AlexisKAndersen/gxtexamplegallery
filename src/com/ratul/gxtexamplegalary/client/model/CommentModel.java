package com.ratul.gxtexamplegalary.client.model;

import java.util.Date;

import com.extjs.gxt.ui.client.data.BaseModel;

public class CommentModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CommentModel(){}
	
	public CommentModel(String comments, String postedBy, Date date) {
		set("comments", comments);
		set("postedDate", date);
		set("postedBy",postedBy);
	}

	public String getComments() {
		return (String) get("comments");
	}

	public String getPostedBy() {
		return (String) get("postedBy");
	}
	
	public Date getStartingDate() {
		return ((Date) get("postedDate"));
	}
}
