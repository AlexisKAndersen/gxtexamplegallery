package com.ratul.gxtexamplegalary.client;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.ratul.gxtexamplegalary.client.grid.GxtEditableGrid;
import com.ratul.gxtexamplegalary.client.grid.GxtGridExample;
import com.ratul.gxtexamplegalary.client.grid.GxtGridGrouping;
import com.ratul.gxtexamplegalary.client.grid.GxtPagingExample;
import com.ratul.gxtexamplegalary.client.model.CommentModel;
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

	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	public void onModuleLoad() {
		final Label label2 = new Label();
		final ListStore<CommentModel> commentList = new ListStore<CommentModel>();
		greetingService.getAllComment(new AsyncCallback<List<CommentModel>>() {

			@Override
			public void onFailure(Throwable caught) {
				label2.setText("data loading failure");

			}

			@Override
			public void onSuccess(List<CommentModel> result) {
				commentList.add(result);

			}
		});

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
				if (!txaInput.getValue().equals("")) {
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
									greetingService
											.getAllComment(new AsyncCallback<List<CommentModel>>() {

												@Override
												public void onFailure(
														Throwable caught) {
													label2
															.setText("data loading failure");

												}

												@Override
												public void onSuccess(
														List<CommentModel> result) {
													commentList.removeAll();
													commentList.add(result);

												}
											});

								}
							});

				}
			}
		});

		txaInput.setFieldLabel("Comment");
		txtPostedBy.setFieldLabel("Name");
		FormPanel cp2 = new FormPanel ();
		cp2.setFrame(true);
		cp2.setHeaderVisible(true);
		cp2.setCollapsible(true);
		cp2.setLayout(new FormLayout());
		cp2.setHeading("Post Comment");
		cp2.setHeight(180);
		cp2.setWidth(520);
		cp2.add(txtPostedBy);
		cp2.add(txaInput);
		cp2.add(button);
		cp2.add(label);
		cp2.setButtonAlign(HorizontalAlignment.CENTER); 
		FormButtonBinding binding = new FormButtonBinding(cp2);  
		binding.addButton(button);  
		
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		ColumnConfig column = new ColumnConfig("comments", "Comments", 500);
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
		grid.setHideHeaders(true);
		grid.setBorders(false);
		grid.setStripeRows(false);
		grid.setWidth(517);
		grid.setHeight(500);

		ContentPanel cp = new ContentPanel();
		cp.setHeaderVisible(true);
		cp.setCollapsible(true);
		cp.setHeading("Comments");
		cp.setHeight(500);
		cp.setWidth(520);
		cp.add(grid);
		//Draggable d = new Draggable(cp);

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

		RootPanel.get("notwrittentutorial").add(tutorialNotWritten);
		RootPanel.get("notwrittentutorial2").add(tutorialNotWritten2);
		RootPanel.get("notwrittentutorial3").add(tutorialNotWritten3);
		RootPanel.get("notwrittentutorial4").add(tutorialNotWritten4);

		RootPanel.get("commentBox").add(cp2);
		//RootPanel.get("commentButton").add(button);
		//RootPanel.get("commentLabel").add(label);
		RootPanel.get("commentList").add(cp);
		RootPanel.get("commentListError").add(label2);

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

		tutorialNotWritten = new Label("Tutorial");
		tutorialNotWritten2 = new Label("Tutorial");
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

		tutorialNotWritten.setStylePrimaryName("label_tutorial_notwritten");
		tutorialNotWritten2.setStylePrimaryName("label_tutorial_notwritten");
		tutorialNotWritten3.setStylePrimaryName("label_tutorial_notwritten");
		tutorialNotWritten4.setStylePrimaryName("label_tutorial_notwritten");
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

		tutorialNotWritten.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				tutorialNotWrittenWn.show();

			}
		});
		tutorialNotWritten2.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				tutorialNotWrittenWn.show();

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
	}

	private Window createSimpleGridWindow() {
		Window win = new Window();
		win.setMaximizable(true);
		win.setHeading("Basic Grid Example");
		win.setWidth(720);
		win.setHeight(350);
		win.add(new GxtGridExample());
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

	Label tutorialNotWritten;
	Label tutorialNotWritten2;
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

	Window tutorialNotWrittenWn;
}
