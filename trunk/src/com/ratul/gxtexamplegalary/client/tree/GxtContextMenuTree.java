package com.ratul.gxtexamplegalary.client.tree;

import java.util.List;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.ratul.gxtexamplegalary.client.TestData;
import com.ratul.gxtexamplegalary.client.icons.ExampleIcons;
import com.ratul.gxtexamplegalary.client.model.Folder;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GxtContextMenuTree extends LayoutContainer {
	public static final ExampleIcons ICONS = GWT.create(ExampleIcons.class);
	private int count = 1;

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		setLayout(new FlowLayout(10));
		final Folder rootNode = TestData.getTreeModel();

		final TreeStore<ModelData> store = new TreeStore<ModelData>();
		store.add((List<ModelData>) rootNode.getChildren(), true);

		final TreePanel<ModelData> tree = new TreePanel<ModelData>(store);
		tree.setDisplayProperty("name");
		tree.getStyle().setLeafIcon(ICONS.user_add());
		tree.setWidth(250);

		Menu contextMenu = new Menu();
		contextMenu.setWidth(140);

		MenuItem insert = new MenuItem();
		insert.setText("Insert Item");
		insert.setIcon(ICONS.add());
		contextMenu.add(insert);

		MenuItem remove = new MenuItem();
		remove.setText("Remove Selected");
		remove.setIcon(ICONS.delete());
		contextMenu.add(remove);
	
		insert.addSelectionListener(new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				ModelData folder = tree.getSelectionModel().getSelectedItem();
				if (folder != null) {
					Folder child = new Folder("Add Child " + count++);
					store.add(folder, child, false);
					tree.setExpanded(folder, true);
				}
			}
		});
		
		remove.addSelectionListener(new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				List<ModelData> selected = tree.getSelectionModel()
						.getSelectedItems();
				for (ModelData sel : selected) {
					store.remove(sel);
				}
			}
		});
		
		tree.setContextMenu(contextMenu);
        add(tree);
	}
}
