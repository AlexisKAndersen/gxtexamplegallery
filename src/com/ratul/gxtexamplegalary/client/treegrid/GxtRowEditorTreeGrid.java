package com.ratul.gxtexamplegalary.client.treegrid;

import java.util.Arrays;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.RowEditor;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.treegrid.TreeGrid;
import com.extjs.gxt.ui.client.widget.treegrid.TreeGridCellRenderer;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.ratul.gxtexamplegalary.client.TestData;
import com.ratul.gxtexamplegalary.client.icons.ExampleIcons;
import com.ratul.gxtexamplegalary.client.model.Folder;

public class GxtRowEditorTreeGrid extends LayoutContainer {
	public static final ExampleIcons ICONS = GWT.create(ExampleIcons.class);

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		setLayout(new FlowLayout(10));

		Folder model = TestData.getTreeModel();

		TreeStore<ModelData> store = new TreeStore<ModelData>();
		store.add(model.getChildren(), true);

		ColumnConfig name = new ColumnConfig("name", "Name", 100);
		name.setRenderer(new TreeGridCellRenderer<ModelData>());
		TextField<String> text = new TextField<String>();
		text.setAllowBlank(false);
		name.setEditor(new CellEditor(text));

		ColumnConfig salary = new ColumnConfig("salary", "Salary", 100);
		salary.setEditor(new CellEditor(new TextField<String>()));
		ColumnConfig date = new ColumnConfig("joiningdate", "Joining Date", 100);
		date.setEditor(new CellEditor(new TextField<String>()));

		ColumnModel cm = new ColumnModel(Arrays.asList(name, salary, date));

		ContentPanel cp = new ContentPanel();
		cp.setBodyBorder(false);
		cp.setHeading("RowEditor TreeGrid");
		cp.setButtonAlign(HorizontalAlignment.CENTER);
		cp.setLayout(new FitLayout());
		cp.setFrame(true);
		cp.setSize(600, 300);

		TreeGrid<ModelData> tree = new TreeGrid<ModelData>(store, cm);
		tree.setBorders(true);
		tree.getStyle().setLeafIcon(ICONS.user_add());
		tree.setSize(400, 400);
		tree.setAutoExpandColumn("name");
		tree.setTrackMouseOver(false);

		RowEditor<ModelData> editor = new RowEditor<ModelData>();
		tree.addPlugin(editor);

		cp.add(tree);
		add(cp);
	}
}
