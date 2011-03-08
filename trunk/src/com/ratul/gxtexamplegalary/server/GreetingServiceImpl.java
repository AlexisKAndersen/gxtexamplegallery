package com.ratul.gxtexamplegalary.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ratul.gxtexamplegalary.client.GreetingService;
import com.ratul.gxtexamplegalary.client.model.CommentModel;
import com.ratul.gxtexamplegalary.client.util.ClientException;
import com.ratul.gxtexamplegalary.server.converter.CommentConverter;
import com.ratul.gxtexamplegalary.server.entity.Comments;
import com.ratul.gxtexamplegalary.server.entity.SubscribedUsers;

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
	public void postComment(String comment, String postedBy) throws ClientException
	{
		Comments comments = new Comments(comment, postedBy, new Date());
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(comments);
			sendMailToUser(comments);
		}
		catch(Exception ex)
		{
			throw new ClientException(ex.getLocalizedMessage());
		}
		finally {
			pm.close();
		}
		
	}

	@Override
	public void saveSubscribedUser(String name, String email)throws ClientException 
	{
		SubscribedUsers user = new SubscribedUsers(name, email);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(user);
		} 
		catch(Exception ex)
		{
			throw new ClientException(ex.getLocalizedMessage());
		}
		finally 
		{
			pm.close();
		}
	}
	
	private void sendMailToUser(Comments comment) throws Exception
	{
		try
		{
			String[] receivers;
			PersistenceManager pm = PMF.get().getPersistenceManager();	
			String query = "select from " + SubscribedUsers.class.getName();
			List<SubscribedUsers> userList = (List<SubscribedUsers>) pm.newQuery(query).execute();
			if(!userList.isEmpty())
			{
				receivers = new String[userList.size()];
				for(int i=0; i<userList.size(); i++)
				{
					receivers[i] = userList.get(i).getUserEmail();
				}
				String table = "A new comments is posted<br><br><table>";
				table += "<tr><td>Posted By:</td><td>" + comment.getPostedBy() + "</td></tr>";
		        table += "<tr><td>Date:</td><td>" + comment.getPostedDate() + "</td></tr>";
		        table += "<tr><td>Comments:</td><td>" + comment.getComments() + "</td></tr>";
		        table += "</table>";
		        MailUtility.sendMail(table, "[gxtexamplegallery.appspot.com] A New Comment is posted at "+comment.getPostedDate(), receivers);
			}
			
		}
		catch(Exception ex)
		{
			throw ex;
		}
	}
}
