package com.ratul.gxtexamplegalary.client.treegrid;

import java.util.Arrays;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.RowNumberer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.treegrid.TreeGrid;
import com.extjs.gxt.ui.client.widget.treegrid.TreeGridCellRenderer;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.ratul.gxtexamplegalary.client.TestData;
import com.ratul.gxtexamplegalary.client.icons.ExampleIcons;
import com.ratul.gxtexamplegalary.client.model.Folder;

public class GxtRowNumberTreeGrid extends LayoutContainer {
	public static final ExampleIcons ICONS = GWT.create(ExampleIcons.class);

	@Override
	protected void onRender(Element parent, int index) 
	{
		super.onRender(parent, index);
		setLayout(new FlowLayout(10));
		Folder model = TestData.getTreeModel();

		TreeStore<ModelData> store = new TreeStore<ModelData>();
		store.add(model.getChildren(), true);

		RowNumberer numberer = new RowNumberer();

		ColumnConfig name = new ColumnConfig("name", "Name", 100);
		name.setRenderer(new TreeGridCellRenderer<ModelData>());

		ColumnConfig salary = new ColumnConfig("salary", "Salary", 100);
		ColumnConfig date = new ColumnConfig("joiningdate", "Joining Date", 100); 

		ColumnModel cm = new ColumnModel(Arrays.asList(numberer, name, salary,
				date));

		ContentPanel cp = new ContentPanel();
		cp.setBodyBorder(false);
		cp.setHeading("RowNumber TreeGrid");
		cp.setButtonAlign(HorizontalAlignment.CENTER);
		cp.setLayout(new FitLayout());
		cp.setFrame(true);
		cp.setSize(600, 300);

		TreeGrid<ModelData> tree = new TreeGrid<ModelData>(store, cm);
		tree.addPlugin(numberer);
		tree.setBorders(true);
		tree.getStyle().setLeafIcon(ICONS.user_add());
		tree.setSize(400, 400);
		tree.setAutoExpandColumn("name");
		tree.setTrackMouseOver(false);

		cp.add(tree);
		add(cp);
	}
	
}
