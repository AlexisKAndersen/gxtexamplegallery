package com.ratul.gxtexamplegalary.client.tree;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ButtonBar;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.ratul.gxtexamplegalary.client.TestData;
import com.ratul.gxtexamplegalary.client.icons.ExampleIcons;
import com.ratul.gxtexamplegalary.client.model.Folder;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GxtBasicTree extends LayoutContainer {

	public static final ExampleIcons ICONS = GWT.create(ExampleIcons.class);

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		setLayout(new FlowLayout(10));
		Folder model = TestData.getTreeModel();

		TreeStore<ModelData> store = new TreeStore<ModelData>();
		store.add(model.getChildren(), true);

		final TreePanel<ModelData> tree = new TreePanel<ModelData>(store);
		tree.setDisplayProperty("name");
		tree.setWidth(250);
		tree.getStyle().setLeafIcon(ICONS.user_add());

		ButtonBar buttonBar = new ButtonBar();
		Button expand = new Button("Expand All");
		Button collapse = new Button("Collapse All");
		expand.addSelectionListener(new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				tree.expandAll();
			}
		});

		collapse.addSelectionListener(new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				tree.collapseAll();
			}
		});
		buttonBar.add(expand);
		buttonBar.add(collapse);

		add(buttonBar);
		add(tree);

	}
}
