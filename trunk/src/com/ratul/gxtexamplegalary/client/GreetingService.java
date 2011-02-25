package com.ratul.gxtexamplegalary.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.ratul.gxtexamplegalary.client.model.CommentModel;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	String greetServer(String name);
	void postComment(String comment, String postedBy);
	List<CommentModel> getAllComment();
}
