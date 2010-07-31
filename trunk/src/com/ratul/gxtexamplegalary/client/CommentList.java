package com.ratul.gxtexamplegalary.client;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.ratul.gxtexamplegalary.client.TestData;
import com.ratul.gxtexamplegalary.client.model.CommentModel;
import com.ratul.gxtexamplegalary.client.model.Employee;

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
		
	    Gxtexamplegalary.greetingService.getAllComment(new AsyncCallback<List<CommentModel>>() {

			@Override
			public void onFailure(Throwable caught) {
				errorMessage.setText("data loading failure");

			}

			@Override
			public void onSuccess(List<CommentModel> result) {
				commentList.add(result);

			}
		});
		
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		ColumnConfig column = new ColumnConfig("comments", "Comments", 580);
		column.setAlignment(HorizontalAlignment.LEFT);
		column.setStyle("background-color:#DFE8F6");
		column.setRenderer(new GridCellRenderer<ModelData>() {
			public Object render(ModelData model, String property,
					ColumnData config, int rowIndex, int colIndex,
					ListStore<ModelData> store, Grid<ModelData> grid) {
				return "<div style=\"height:inherit; background-color: #E1F4FC;padding:10px;font-size:12px;\">"
						+"<span style=\"color:#53556F;font-size:12px;\">"
						+model.get("postedBy")+"</span><br>"
						+ "<span style=\"color:#96B1BC;text-decoration: underline;\">"
						+model.get("postedDate")+"</span>"
						+ "<br><br>"
						+ model.get("comments") + "</div><br>";
			}

		});
		column.setDateTimeFormat(DateTimeFormat.getShortDateFormat());
		configs.add(column); 
	   
		ColumnModel cm = new ColumnModel(configs);
		Grid<CommentModel> grid = new Grid<CommentModel>(commentList, cm);
		grid.setHideHeaders(false);
		grid.setBorders(false);
		grid.setStripeRows(false);
		grid.setWidth(585);
		grid.setHeight(500);
		ContentPanel cp = new ContentPanel();
		cp.setHeaderVisible(false);
		cp.setCollapsible(true);
		cp.setLayout(new FitLayout());  
	    cp.setHeight(500);
		cp.setWidth(590); 
	    cp.add(grid);  
	     
	    add(cp);
	}
	private ListStore<CommentModel> commentList = new ListStore<CommentModel>();
	private Label errorMessage = new Label();
}
