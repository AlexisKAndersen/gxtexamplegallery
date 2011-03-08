package com.ratul.gxtexamplegalary.client.misc;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.user.client.Element;
import com.ratul.gxtexamplegalary.client.TestData;
import com.ratul.gxtexamplegalary.client.model.Employee;

public class ListViewXTemplate extends LayoutContainer {
	protected void onRender(Element parent, int index) {

		super.onRender(parent, index);
		setLayout(new FlowLayout(10));

		ListStore<Employee> employeeList = new ListStore<Employee>();
		employeeList.add(TestData.getEmployees());

		ListView<Employee> lView = new ListView<Employee>();
		// getTemplate() returns the desired template
		lView.setTemplate(getTemplate());
		lView.setStore(employeeList);

		ContentPanel cp = new ContentPanel();
		cp.setBodyBorder(false);
		cp.setHeaderVisible(false);
		cp.setButtonAlign(HorizontalAlignment.CENTER);
		cp.setLayout(new FitLayout());
		cp.setSize(500, 600);
		cp.add(lView);
		this.add(cp);
	}

	private native String getTemplate() /*-{
		return [
				'<tpl for=".">',
				'<div style="border: 1px solid #DDDDDD;float:left;margin:4px 0 4px  4px; padding:2px;width:220px;">',
				'<div style="color:#1C3C78;font-weight:bold;padding-bottom:5px;padding-top:2px;text-decoration:underline;">{name}</div>',
				'<div style="color:green">Department:{department}</div>',
				'<div style="color:blue">Designation:{designation}</div>',
				'<div style="color:black;padding-bottom:2px;">Salary:{salary}</div>',
				'</div>', '</tpl>', '' ].join("");

	}-*/;

}
