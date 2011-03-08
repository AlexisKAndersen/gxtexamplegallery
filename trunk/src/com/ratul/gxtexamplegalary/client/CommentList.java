package com.ratul.gxtexamplegalary.client;

import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.Status;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.ratul.gxtexamplegalary.client.model.CommentModel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CommentList extends LayoutContainer {
	
		/**
	 * This is the entry point method.
	 */
	@Override
	  protected void onRender(Element parent, int index) {
	    super.onRender(parent, index);
	    setLayout(new FlowLayout(10));
	    statusMessage = new Status();
        statusMessage.setText("");
        statusMessage.setWidth(50);
        
        statusMessage.setBusy("");
	    GreetingService.Util.getInstance().getAllComment(new AsyncCallback<List<CommentModel>>() {

			@Override
			public void onFailure(Throwable caught) {
				Info.display("Failure!", "Data Loading Failure :(");
				statusMessage.clearStatus("");
			}

			@Override
			public void onSuccess(List<CommentModel> result) {
				commentList.add(result);
				statusMessage.clearStatus("");
			}
		});

		ListView<CommentModel> lView = new ListView<CommentModel>();
		lView.setTemplate(getTemplate());
		lView.setStore(commentList);

		ContentPanel cp = new ContentPanel();
		cp.setBodyBorder(false);
		cp.setHeaderVisible(false);
		cp.setButtonAlign(HorizontalAlignment.CENTER);
		cp.setLayout(new FitLayout());
		cp.setSize(590, 470);
		cp.add(lView);
		cp.add(statusMessage);
		this.add(cp);

	}
	private native String getTemplate() /*-{
		return [
			'<tpl for=".">',
			'<div style="height:auto; background-color: #E1F4FC;padding:10px;font-size:12px;text-align:left;">',
			'<span style="color:#53556F;font-size:12px;text-align:left;">{postedBy}</span><br>',
			'<span style="color:#96B1BC;text-decoration: underline;text-align:left;">{postedDate}</span><br><br>',
			'{comments}</div><br>', '</tpl>', '' ].join("");

	}-*/;
	private ListStore<CommentModel> commentList = new ListStore<CommentModel>();
	private Status statusMessage;
}
