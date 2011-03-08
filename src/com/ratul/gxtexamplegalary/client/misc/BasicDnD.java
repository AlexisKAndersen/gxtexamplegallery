package com.ratul.gxtexamplegalary.client.misc;

import java.util.List;

import com.extjs.gxt.ui.client.core.El;
import com.extjs.gxt.ui.client.dnd.DragSource;
import com.extjs.gxt.ui.client.dnd.DropTarget;
import com.extjs.gxt.ui.client.event.DNDEvent;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.FlowData;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.user.client.Element;
import com.ratul.gxtexamplegalary.client.TestData;
import com.ratul.gxtexamplegalary.client.model.Employee;

public class BasicDnD extends LayoutContainer {
	protected void onRender(Element parent, int index) {

		super.onRender(parent, index);
		setLayout(new FlowLayout(10));
		ContentPanel cp = new ContentPanel();
		cp.setHeaderVisible(false);
		HorizontalPanel hp = new HorizontalPanel();
		hp.setSpacing(10);

		final LayoutContainer sourceContainer = new LayoutContainer();
		sourceContainer.setLayoutOnChange(true);
		sourceContainer.setWidth(300);

		List<Employee> employeeList = TestData.getEmployees();
		for (Employee employee : employeeList) {
			final Html html = new Html(
					"<div style=\"font-size:11px; border: 1px solid #DDDDDD;float:left;margin:4px 0 4px  4px; padding:2px;width:220px;\">"
							+ "<div style=\"color:#1C3C78;font-weight:bold;padding-bottom:5px;padding-top:2px;text-decoration:underline;\">"
							+ employee.getName()
							+ "</div>"
							+ "<div style=\"color:green\">Department:"
							+ employee.getDepartment()
							+ "</div>"
							+ "<div style=\"color:blue\">Designation:"
							+ employee.getDesignation()
							+ "</div>"
							+ "<div style=\"color:black;padding-bottom:2px;\">Salary:"
							+ employee.getSalary() + "</div>" + "</div>");
			sourceContainer.add(html, new FlowData(3));

			DragSource source = new DragSource(html) {
				@Override
				protected void onDragStart(DNDEvent event) {
					event.setData(html);
					event.getStatus().update(
							El.fly(html.getElement()).cloneNode(true));
				}
			};

		}

		final LayoutContainer targetContainer = new LayoutContainer();
		targetContainer.setLayoutOnChange(true);
		targetContainer.setBorders(true);
		targetContainer.setSize(300, 600);

		DropTarget target = new DropTarget(targetContainer) {
			@Override
			protected void onDragDrop(DNDEvent event) {
				super.onDragDrop(event);
				Html html = event.getData();
				targetContainer.add(html);
			}
		};
		target.setOverStyle("drag-ok");

		hp.add(targetContainer);
		hp.add(sourceContainer);
		cp.add(hp);
		this.add(cp);

	}
}
