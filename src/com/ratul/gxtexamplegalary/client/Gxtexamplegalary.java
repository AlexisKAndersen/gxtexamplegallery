package com.ratul.gxtexamplegalary.client;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
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
import com.ratul.gxtexamplegalary.client.grid.GxtEditableGrid;
import com.ratul.gxtexamplegalary.client.grid.GxtGridGrouping;
import com.ratul.gxtexamplegalary.client.grid.GxtPagingExample;
import com.ratul.gxtexamplegalary.client.grid.SortingGrid;
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

	public static final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	public void onModuleLoad() {
		final TextArea txaInput = new TextArea();
		final TextField<String> txtPostedBy = new TextField<String>();
		txaInput.setWidth(500);
		txtPostedBy.setWidth(200);
		txtPostedBy.setEmptyText("Your Name...");
		txtPostedBy.setTitle("Write your name here");
		txaInput.setTitle("Write your comments here");
		txaInput.setEmptyText("Write your comments here...");
		Button button = new Button("Post Comment");
		final Label label = new Label();
		label.setStylePrimaryName("label_success");
		button.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				if (!txtPostedBy.getRawValue().equals("") && !txaInput.getRawValue().equals("")) {
					greetingService.postComment(txaInput.getRawValue(),txtPostedBy.getRawValue(),
							new AsyncCallback<Void>() {

								@Override
								public void onFailure(Throwable caught) {
									label.setText("failure");

								}

								@Override
								public void onSuccess(Void result) {
									label
											.setText("Comment is posted successfully!");
									txaInput.setValue("");
									txtPostedBy.setValue("");
							
								}
							});

				}
			}
		});

		RootPanel.get("nameField").add(txtPostedBy);
		RootPanel.get("commentField").add(txaInput);
		RootPanel.get("sendButton").add(button);
		RootPanel.get("message").add(label);
		
		this.initializeWindows();
		this.initializeNStyledLabels();
		this.addHandlerMethod();

		RootPanel.get("basicgrid").add(simpleGrid);
		RootPanel.get("paginggrid").add(pageGrid);
		RootPanel.get("editablegrid").add(editGrid);
		RootPanel.get("groupinggrid").add(groupingGreed);

		RootPanel.get("basictree").add(basicTree);
		RootPanel.get("contextmenutree").add(contextMenuTree);
		RootPanel.get("filtertree").add(filterTree);

		RootPanel.get("basictreegrid").add(basicTreeGrid);
		RootPanel.get("editortreegrid").add(editorTreeGrid);
		RootPanel.get("roweditortreegrid").add(rowEditorTreeGrid);
		RootPanel.get("rownumbertreegrid").add(rowNumberTreeGrid);

		RootPanel.get("notwrittentutorial3").add(tutorialNotWritten3);
		RootPanel.get("notwrittentutorial4").add(tutorialNotWritten4);

		RootPanel.get("commentList").add(showComment);
		

	}

	private void initializeNStyledLabels() {
		simpleGrid = new Label();
		editGrid = new Label();
		pageGrid = new Label();
		groupingGreed = new Label();

		basicTree = new Label();
		contextMenuTree = new Label();
		filterTree = new Label();

		basicTreeGrid = new Label();
		editorTreeGrid = new Label();
		rowEditorTreeGrid = new Label();
		rowNumberTreeGrid = new Label();
		
		showComment = new Label("View Comments");

		tutorialNotWritten3 = new Label("Tutorial");
		tutorialNotWritten4 = new Label("Tutorial");

		simpleGrid.setStylePrimaryName("label_basic_grid");
		editGrid.setStylePrimaryName("label_edit_grid");
		pageGrid.setStylePrimaryName("label_paging_grid");
		groupingGreed.setStylePrimaryName("label_grouping_grid");

		basicTree.setStylePrimaryName("label_basic_tree");
		contextMenuTree.setStylePrimaryName("label_contextmenu_tree");
		filterTree.setStylePrimaryName("label_filter_tree");

		basicTreeGrid.setStylePrimaryName("label_basic_treegrid");
		editorTreeGrid.setStylePrimaryName("label_editor_treegrid");
		rowEditorTreeGrid.setStylePrimaryName("label_roweditor_treegrid");
		rowNumberTreeGrid.setStylePrimaryName("label_rownnumber_treegrid");

		tutorialNotWritten3.setStylePrimaryName("label_tutorial_notwritten");
		tutorialNotWritten4.setStylePrimaryName("label_tutorial_notwritten");
		
		showComment.setStylePrimaryName("label_veiw_comment");
	}

	private void initializeWindows() {
		tutorialNotWrittenWn = new Window();
		tutorialNotWrittenWn.setHeading("Tutorial Is Not Written");
		tutorialNotWrittenWn.setWidth(300);
		tutorialNotWrittenWn.setHeight(150);

		Label displayText = new Label(
				"Tutorial about this feature is coming very soon");
		tutorialNotWrittenWn.add(displayText);

		basicGridWn = this.createSimpleGridWindow();
		basicTreeWn = this.createBasicTreeWindow();
		editableGridWn = this.createEditableGridWindow();
		groupingGreedWn = this.createGroupingGridWindow();
		pageGridWn = this.createPagingGridWindow();

		contextMenuTreeWn = this.createContextMenuTreeWindow();
		filterTreeWn = this.createFilterTreeWindow();

		basicTreeGridWn = this.createBasicTreeGridWindow();
		editorTreeGridWn = this.createEditorTreeGridWindow();
		rowEditorTreeGridWn = this.createRowEditorTreeGridWindow();
		rowNumberTreeGridWn = this.createRowNumberTreeGridWindow();
		
	}

	private void addHandlerMethod() {
		simpleGrid.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				basicGridWn.show();
			}
		});
		editGrid.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				editableGridWn.show();

			}
		});
		pageGrid.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				pageGridWn.show();

			}
		});
		groupingGreed.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				groupingGreedWn.show();

			}
		});

		basicTree.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				basicTreeWn.show();

			}
		});
		contextMenuTree.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				contextMenuTreeWn.show();

			}
		});
		filterTree.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				filterTreeWn.show();

			}
		});
		basicTreeGrid.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				basicTreeGridWn.show();

			}
		});
		editorTreeGrid.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				editorTreeGridWn.show();

			}
		});
		rowEditorTreeGrid.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				rowEditorTreeGridWn.show();

			}
		});
		rowNumberTreeGrid.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				rowNumberTreeGridWn.show();

			}
		});

		tutorialNotWritten3.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				tutorialNotWrittenWn.show();

			}
		});
		tutorialNotWritten4.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				tutorialNotWrittenWn.show();

			}
		});
		showComment.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				commentListWindow = createCommentListWindow();
				commentListWindow.show();

			}
		});
	}

	private Window createSimpleGridWindow() {
		Window win = new Window();
		win.setMaximizable(true);
		win.setHeading("Basic Grid Example");
		win.setWidth(720);
		win.setHeight(350);
		win.add(new SortingGrid());
		return win;
	}

	private Window createEditableGridWindow() {
		Window win = new Window();
		win.setMaximizable(true);
		win.setHeading("Editable Grid Example");
		win.setWidth(720);
		win.setHeight(350);
		win.add(new GxtEditableGrid());
		return win;
	}

	private Window createGroupingGridWindow() {
		Window win = new Window();
		win.setMaximizable(true);
		win.setHeading("Grouping Grid Example");
		win.setWidth(720);
		win.setHeight(350);
		win.add(new GxtGridGrouping());
		return win;
	}

	private Window createPagingGridWindow() {
		Window win = new Window();
		win.setMaximizable(true);
		win.setHeading("Pagination Example");
		win.setWidth(720);
		win.setHeight(350);
		win.add(new GxtPagingExample());
		return win;
	}

	private Window createBasicTreeWindow() {
		Window win = new Window();
		win.setMaximizable(true);
		win.setHeading("Basic Tree Example");
		win.setWidth(720);
		win.setHeight(350);
		win.add(new GxtBasicTree());
		return win;
	}

	private Window createContextMenuTreeWindow() {
		Window win = new Window();
		win.setMaximizable(true);
		win.setHeading("Tree With Context Menu Example");
		win.setWidth(720);
		win.setHeight(350);
		win.add(new GxtContextMenuTree());
		return win;
	}

	private Window createFilterTreeWindow() {
		Window win = new Window();
		win.setMaximizable(true);
		win.setHeading("Tree With Filter Functionality Example");
		win.setWidth(720);
		win.setHeight(350);
		win.add(new GxtFilterTree());
		return win;
	}

	private Window createBasicTreeGridWindow() {
		Window win = new Window();
		win.setMaximizable(true);
		win.setHeading("Basic Tree Grid Example");
		win.setWidth(720);
		win.setHeight(350);
		win.add(new GxtBasicTreeGrid());
		return win;
	}

	private Window createEditorTreeGridWindow() {
		Window win = new Window();
		win.setMaximizable(true);
		win.setHeading("Editor Tree Grid Example");
		win.setWidth(720);
		win.setHeight(350);
		win.add(new GxtEditorTreeGrid());
		return win;
	}

	private Window createRowEditorTreeGridWindow() {
		Window win = new Window();
		win.setMaximizable(true);
		win.setHeading("Row Editor Tree Grid Example");
		win.setWidth(720);
		win.setHeight(350);
		win.add(new GxtRowEditorTreeGrid());
		return win;
	}

	private Window createRowNumberTreeGridWindow() {
		Window win = new Window();
		win.setMaximizable(true);
		win.setHeading("Row Number Tree Grid Example");
		win.setWidth(720);
		win.setHeight(350);
		win.add(new GxtRowNumberTreeGrid());
		return win;
	}

	private Window createCommentListWindow() {
		Window win = new Window();
		win.setMaximizable(true);
		win.setHeading("Comments");
		win.setWidth(620);
		win.setHeight(520);
		win.add(new CommentList());
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
}
