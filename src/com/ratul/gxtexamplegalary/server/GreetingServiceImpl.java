package com.ratul.gxtexamplegalary.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ratul.gxtexamplegalary.client.GreetingService;
import com.ratul.gxtexamplegalary.client.model.CommentModel;
import com.ratul.gxtexamplegalary.server.converter.CommentConverter;
import com.ratul.gxtexamplegalary.server.entity.Comments;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	public String greetServer(String input) {
		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");
		return "Hello, " + input + "!<br><br>I am running " + serverInfo
				+ ".<br><br>It looks like you are using:<br>" + userAgent;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CommentModel> getAllComment() {
		List<CommentModel> commentList = new ArrayList<CommentModel>();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			String query = "select from " + Comments.class.getName()+" order by postedDate desc";
			List<Comments> list = (List<Comments>) pm.newQuery(query).execute();
			if (!list.isEmpty()) {
				for (Comments c : list) {
					commentList.add(CommentConverter.entityToModel(c));
				}
			}
			return commentList;
		} catch (Exception ex) {

		} finally {
			pm.close();
		}
		return null;
	}

	@Override
	public void postComment(String comment, String postedBy) {
		Comments comments = new Comments(comment, postedBy, new Date());
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(comments);
		} finally {
			pm.close();
		}
		
	}
}
