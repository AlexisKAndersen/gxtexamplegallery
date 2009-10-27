package com.ratul.gxtexamplegalary.server.converter;

import com.ratul.gxtexamplegalary.client.model.CommentModel;
import com.ratul.gxtexamplegalary.server.entity.Comments;

public class CommentConverter 
{
	public static CommentModel entityToModel(Comments comment)
	{
		CommentModel model = new CommentModel(comment.getComments(), comment.getPostedBy(), comment.getPostedDate());
		return model;
	}
}
