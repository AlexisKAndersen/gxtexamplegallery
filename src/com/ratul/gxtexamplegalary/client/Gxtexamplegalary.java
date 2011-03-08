package com.ratul.gxtexamplegalary.client;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Status;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.ratul.gxtexamplegalary.client.chart.SimpleChart;
import com.ratul.gxtexamplegalary.client.grid.GxtEditableGrid;
import com.ratul.gxtexamplegalary.client.grid.GxtGridExample;
import com.ratul.gxtexamplegalary.client.grid.GxtGridGrouping;
import com.ratul.gxtexamplegalary.client.grid.GxtPagingExample;
import com.ratul.gxtexamplegalary.client.grid.SortingGrid;
import com.ratul.gxtexamplegalary.client.misc.BasicDnD;
import com.ratul.gxtexamplegalary.client.misc.ListViewXTemplate;
import com.ratul.gxtexamplegalary.client.tree.GxtBasicTree;
import com.ratul.gxtexamplegalary.client.tree.GxtContextMenuTree;
import com.ratul.gxtexamplegalary.client.tree.GxtFilterTree;
import com.ratul.gxtexamplegalary.client.treegrid.GxtBasicTreeGrid;
import com.ratul.gxtexamplegalary.client.treegrid.GxtEditorTreeGrid;
import com.ratul.gxtexamplegalary.client.treegrid.GxtRowEditorTreeGrid;
import com.ratul.gxtexamplegalary.client.treegrid.GxtRowNumberTreeGrid;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Gxtexamplegalary implements EntryPoint {

//	public static final GreetingServiceAsync greetingService = GWT
//			.create(GreetingService.class);

	public void onModuleLoad() 
	{
		busyStaus = new Status();
		final TextArea txaInput = new TextArea();
		final TextField<String> txtPostedBy = new TextField<String>();
		txaInput.setWidth(200);
		txtPostedBy.setWidth(200);
		txtPostedBy.setEmptyText("Your Name...");
		txtPostedBy.setTitle("Write your name here");
		txaInput.setTitle("Write your comments here");
		txaInput.setEmptyText("Write your comments here...");
		Button button = new Button("Post Comment");
		
		button.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) 
			{
				busyStaus.setBusy("");
				if (!txtPostedBy.getRawValue().equals("") && !txaInput.getRawValue().equals("")) {
					GreetingService.Util.getInstance().postComment(txaInput.getRawValue(),txtPostedBy.getRawValue(),
							new AsyncCallback<Void>() {

								@Override
								public void onFailure(Throwable caught) {
									Info.display("Failure:(","Comment is not posted.");
									busyStaus.clearStatus("");
								}

								@Override
								public void onSuccess(Void result) {
									Info.display("Success:)","Comment is posted successfully.");
									txaInput.setValue("");
									txtPostedBy.setValue("");
									busyStaus.clearStatus("");
								}
							});

				}
			}
		});

		RootPanel.get("nameField").add(txtPostedBy);
		RootPanel.get("commentField").add(txaInput);
		RootPanel.get("sendButton").add(button);
		RootPanel.get("busyIcon").add(busyStaus);
		
		this.initializeWindows();
		this.initializeNStyledLabels();
		this.addLabelHandler();

		RootPanel.get("basicgrid").add(simpleGrid);
		RootPanel.get("paginggrid").add(pageGrid);
		RootPanel.get("editablegrid").add(editGrid);
		RootPanel.get("groupinggrid").add(groupingGreed);
		RootPanel.get("filtergrid").add(filterGrid);

		RootPanel.get("basictree").add(basicTree);
		RootPanel.get("contextmenutree").add(contextMenuTree);
		RootPanel.get("filtertree").add(filterTree);

		RootPanel.get("basictreegrid").add(basicTreeGrid);
		RootPanel.get("editortreegrid").add(editorTreeGrid);
		RootPanel.get("roweditortreegrid").add(rowEditorTreeGrid);
		RootPanel.get("rownumbertreegrid").add(rowNumberTreeGrid);
		
		RootPanel.get("basicChart").add(basicChart);
		RootPanel.get("xTemplate").add(listViewXTemplate);
		RootPanel.get("dragDrop").add(basicDnD);

		RootPanel.get("notwrittentutorial3").add(tutorialNotWritten3);
		RootPanel.get("notwrittentutorial4").add(tutorialNotWritten4);

		RootPanel.get("commentList").add(showComment);
		RootPanel.get("dvFeedback").add(feedBack);
		RootPanel.get("subscribeUser").add(subscribeUser);

	}

	private void initializeNStyledLabels() 
	{
		feedBack = new Label();
		subscribeUser = new Label("Subscribe");
		
		simpleGrid = new Label();
		editGrid = new Label();
		pageGrid = new Label();
		groupingGreed = new Label();
		filterGrid = new Label();

		basicTree = new Label();
		contextMenuTree = new Label();
		filterTree = new Label();

		basicTreeGrid = new Label();
		editorTreeGrid = new Label();
		rowEditorTreeGrid = new Label();
		rowNumberTreeGrid = new Label();
		
		basicChart = new Label();
		listViewXTemplate = new Label();
		basicDnD = new Label();
		
		showComment = new Label("View Comments");

		tutorialNotWritten3 = new Label("Tutorial");
		tutorialNotWritten4 = new Label("Tutorial");

		feedBack.setStylePrimaryName("feedback_bar");
		
		simpleGrid.setStylePrimaryName("label_basic_grid");
		editGrid.setStylePrimaryName("label_edit_grid");
		pageGrid.setStylePrimaryName("label_paging_grid");
		groupingGreed.setStylePrimaryName("label_grouping_grid");
		filterGrid.setStylePrimaryName("label_filter_grid");

		basicTree.setStylePrimaryName("label_basic_tree");
		contextMenuTree.setStylePrimaryName("label_contextmenu_tree");
		filterTree.setStylePrimaryName("label_filter_tree");

		basicTreeGrid.setStylePrimaryName("label_basic_treegrid");
		editorTreeGrid.setStylePrimaryName("label_editor_treegrid");
		rowEditorTreeGrid.setStylePrimaryName("label_roweditor_treegrid");
		rowNumberTreeGrid.setStylePrimaryName("label_rownnumber_treegrid");

		basicChart.setStylePrimaryName("label_simple_chart");
		listViewXTemplate.setStylePrimaryName("label_listview_xtemplate");
		basicDnD.setStylePrimaryName("label_basic_dnd");
		
		tutorialNotWritten3.setStylePrimaryName("label_tutorial_notwritten");
		tutorialNotWritten4.setStylePrimaryName("label_tutorial_notwritten");
		
		showComment.setStylePrimaryName("label_veiw_comment");
		subscribeUser.setStylePrimaryName("label_veiw_comment");
	}

	private void initializeWindows() {
		tutorialNotWrittenWn = new Window();
		tutorialNotWrittenWn.setHeading("Tutorial Is Not Written");
		tutorialNotWrittenWn.setWidth(300);
		tutorialNotWrittenWn.setHeight(150);

		Label displayText = new Label(
				"Tutorial about this feature is coming very soon");
		tutorialNotWrittenWn.add(displayText);

		CommentForm commentForm = new CommentForm();
		feedBackWn = this.getWindow("Give Your Feedback",210,530,commentForm);
		commentForm.setWindow(feedBackWn);
		
		UserSubscriptionForm userSubsForm = new UserSubscriptionForm();
		subscribeUserWn = this.getWindow("Subscribe to be updated with comments",180,430,userSubsForm);
		userSubsForm.setWindow(subscribeUserWn);
		
		basicGridWn = this.getWindow("Basic Grid Example",350,730,new GxtGridExample());
		basicTreeWn = this.getWindow("Basic Tree Example",350,720,new GxtBasicTree());
		editableGridWn = this.getWindow("Editable Grid Example",350,730,new GxtEditableGrid());
		groupingGreedWn = this.getWindow("Grouping Grid Example",470,730,new GxtGridGrouping());
		pageGridWn = this.getWindow("Pagination Example",350,730,new GxtPagingExample());

		contextMenuTreeWn = this.getWindow("Tree With Context Menu Example",350,720,new GxtContextMenuTree());
		filterTreeWn = this.getWindow("Tree With Filter Functionality Example",350,720,new GxtFilterTree());

		basicTreeGridWn = this.getWindow("Basic Tree Grid Example",350,720,new GxtBasicTreeGrid());
		editorTreeGridWn = this.getWindow("Editor Tree Grid Example",350,720,new GxtEditorTreeGrid());
		rowEditorTreeGridWn = this.getWindow("Row Editor Tree Grid Example",350,720,new GxtRowEditorTreeGrid());
		rowNumberTreeGridWn = this.getWindow("Row Number Tree Grid Example",350,720,new GxtRowNumberTreeGrid());
		filterGridWn = this.getWindow("Filter Grid Example",350,720,new SortingGrid());
		
		basicChartWn = this.getWindow("Simple Chart Example",650,550,new SimpleChart());
		listViewXTemplateWn = this.getWindow("List View with XTemplate",650,550,new ListViewXTemplate());
		basicDnDWn = this.getWindow("Basic Drag & Drop Example",650,600,new BasicDnD());
		
	}

	private void addLabelHandler()
	{
		this.addHandlerMethod(feedBack,feedBackWn);
		this.addHandlerMethod(subscribeUser,subscribeUserWn);
		
		this.addHandlerMethod(simpleGrid,basicGridWn);
		this.addHandlerMethod(pageGrid,pageGridWn);
		this.addHandlerMethod(editGrid,editableGridWn);
		this.addHandlerMethod(groupingGreed,groupingGreedWn);
		this.addHandlerMethod(filterGrid,filterGridWn);
		
		this.addHandlerMethod(basicTree,basicTreeWn);
		this.addHandlerMethod(contextMenuTree,contextMenuTreeWn);
		this.addHandlerMethod(filterTree,filterTreeWn);
		
		
		this.addHandlerMethod(basicTreeGrid,basicTreeGridWn);
		this.addHandlerMethod(editorTreeGrid,editorTreeGridWn);
		this.addHandlerMethod(rowEditorTreeGrid,rowEditorTreeGridWn);
		this.addHandlerMethod(rowNumberTreeGrid,rowNumberTreeGridWn);
		
		this.addHandlerMethod(basicChart,basicChartWn);
		this.addHandlerMethod(listViewXTemplate,listViewXTemplateWn);
		this.addHandlerMethod(basicDnD,basicDnDWn);
		
		this.addHandlerMethod(tutorialNotWritten3,tutorialNotWrittenWn);
		
		
		showComment.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				commentListWindow = createCommentListWindow();
				commentListWindow.show();

			}
		});
	}
	private void addHandlerMethod(Label label, final Window window) 
	{
		label.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				window.show();
			}
		});
	}

	private Window createCommentListWindow() {
		Window win = new Window();
		win.setMaximizable(true);
		win.setHeading("Comments");
		win.setWidth(620);
		win.setHeight(550);
		win.add(new CommentList());
		Button subscribeButton = new Button("Subscribe");
		subscribeButton.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				UserSubscriptionForm userSubsForm = new UserSubscriptionForm();
				subscribeUserWn = getWindow("Subscribe to be updated with comments",180,430,userSubsForm);
				userSubsForm.setWindow(subscribeUserWn);
				subscribeUserWn.show();
			}
		});
		win.addButton(subscribeButton);
		return win;
	}
	private Window getWindow(String header, int height, int width, LayoutContainer container) {
		Window win = new Window();
		win.setMaximizable(true);
		win.setHeading(header);
		win.setWidth(width);
		win.setHeight(height);
		win.add(container);
		return win;
	}
	
	Label simpleGrid;
	Label editGrid;
	Label pageGrid;
	Label basicTree;
	Label groupingGreed;
	Label contextMenuTree;
	Label filterTree;
	Label basicTreeGrid;
	Label editorTreeGrid;
	Label rowEditorTreeGrid;
	Label rowNumberTreeGrid;
	Label filterGrid;
	Label basicChart;
	Label listViewXTemplate;
	Label basicDnD;
	Label feedBack;
	Label subscribeUser;
	
	Label showComment;
	
	Label tutorialNotWritten3;
	Label tutorialNotWritten4;

	Window basicGridWn;
	Window groupingGreedWn;
	Window pageGridWn;
	Window basicTreeWn;
	Window editableGridWn;
	Window contextMenuTreeWn;
	Window filterTreeWn;
	Window basicTreeGridWn;
	Window editorTreeGridWn;
	Window rowEditorTreeGridWn;
	Window rowNumberTreeGridWn;
	Window commentListWindow;
	Window tutorialNotWrittenWn;
	Window filterGridWn;
	Window basicChartWn;
	Window listViewXTemplateWn;
	Window basicDnDWn;
	Window feedBackWn;
	Window subscribeUserWn;
	private Status busyStaus;
}
