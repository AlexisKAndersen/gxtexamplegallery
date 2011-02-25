package com.ratul.gxtexamplegalary.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.ratul.gxtexamplegalary.client.model.CommentModel;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback);

	void getAllComment(AsyncCallback<List<CommentModel>> callback);

	void postComment(String comment, String postedBy,
			AsyncCallback<Void> callback);
}
