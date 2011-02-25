package com.ratul.gxtexamplegalary.client.tree;

import java.util.List;

import com.extjs.gxt.ui.client.data.BaseTreeLoader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelIconProvider;
import com.extjs.gxt.ui.client.data.TreeLoader;
import com.extjs.gxt.ui.client.data.TreeModel;
import com.extjs.gxt.ui.client.data.TreeModelReader;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.StoreFilterField;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.ratul.gxtexamplegalary.client.TestData;
import com.ratul.gxtexamplegalary.client.icons.ExampleIcons;
import com.ratul.gxtexamplegalary.client.model.Folder;

public class GxtFilterTree extends LayoutContainer {
	public static final ExampleIcons ICONS = GWT.create(ExampleIcons.class);

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		setLayout(new FlowLayout(10));
		TreeLoader<ModelData> loader = new BaseTreeLoader<ModelData>(
				new TreeModelReader<List<ModelData>>());

		TreeStore<ModelData> store = new TreeStore<ModelData>(loader);

		TreePanel<ModelData> tree = new TreePanel<ModelData>(store);
		tree.setAutoLoad(true);
		tree.setDisplayProperty("name");
		tree.setWidth(250);
		tree.setIconProvider(new ModelIconProvider<ModelData>() {
			public AbstractImagePrototype getIcon(ModelData model) {
				if (((TreeModel) model).isLeaf()) {
					return ICONS.user_add();
				}
				return null;
			}
		});

		loader.load(TestData.getTreeModel());

		StoreFilterField<ModelData> filter = new StoreFilterField<ModelData>() {

			@Override
			protected boolean doSelect(Store<ModelData> store,
					ModelData parent, ModelData record, String property,
					String filter) {
				// only match leaf nodes
				if (record instanceof Folder) {
					return false;
				}
				String name = record.get("name");
				name = name.toLowerCase();
				if (name.startsWith(filter.toLowerCase())) {
					return true;
				}
				return false;
			}

		};
		filter.bind(store);

		VerticalPanel panel = new VerticalPanel();
		panel.addStyleName("x-small-editor");
		panel.setSpacing(8);

		panel.add(new Html("<span class=text>Enter a search string such as 'dirk'</span>"));
		panel.add(filter);
		panel.add(tree);
		add(panel);
	}
}
