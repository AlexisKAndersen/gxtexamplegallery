package com.ratul.gxtexamplegalary.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.ratul.gxtexamplegalary.client.model.CommentModel;
import com.ratul.gxtexamplegalary.client.util.ClientException;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService 
{
	public static class Util {
        private static GreetingServiceAsync instance;
        public static GreetingServiceAsync getInstance() {
            if (instance == null) {
                instance = GWT.create(GreetingService.class);
            }
            return instance;
        }
    }
	public String greetServer(String name);
	public void postComment(String comment, String postedBy) throws ClientException;
	public List<CommentModel> getAllComment();
	public void saveSubscribedUser(String name,String email) throws ClientException;
}
