package com.ratul.gxtexamplegalary.client.grid;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.store.GroupingStore;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.GridGroupRenderer;
import com.extjs.gxt.ui.client.widget.grid.GroupColumnData;
import com.extjs.gxt.ui.client.widget.grid.GroupingView;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Element;
import com.ratul.gxtexamplegalary.client.TestData;
import com.ratul.gxtexamplegalary.client.model.Employee;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GxtGridGrouping extends LayoutContainer {

	protected void onRender(Element parent, int index) {
	    super.onRender(parent, index);
	    setLayout(new FlowLayout(10));
		GroupingStore<Employee> employeeList = new GroupingStore<Employee>();  
	    employeeList.add(TestData.getEmployees());  
	    employeeList.groupBy("department");   
		
		 List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		 
		 ColumnConfig column = new ColumnConfig();  
	     column.setId("name");  
	     column.setHeader("Employee Name");  
	     column.setWidth(200);  
	     configs.add(column);
	     
	     column = new ColumnConfig("department", "Department", 150);  
	     column.setAlignment(HorizontalAlignment.LEFT);  
	     configs.add(column);
	     
	     column = new ColumnConfig("designation", "Designation", 150);  
	     column.setAlignment(HorizontalAlignment.LEFT);  
	     configs.add(column);
	     
	     column = new ColumnConfig("salary", "Slary", 100);  
	     column.setAlignment(HorizontalAlignment.RIGHT);  
	     final NumberFormat number = NumberFormat.getFormat("0.00");  
	     GridCellRenderer<Employee> checkSalary = new GridCellRenderer<Employee>() {  
		       public String render(Employee model, String property, ColumnData config, int rowIndex,  
		           int colIndex, ListStore<Employee> employeeList, Grid<Employee> grid) {  
		         double val = (Double) model.get(property);  
		         String style = val < 70000 ? "red" : "green";  
		         return "<span style='color:" + style + "'>" + number.format(val) + "</span>";  
		       }  
		     };  
	     column.setRenderer(checkSalary);  
	     configs.add(column);
	     
	     column = new ColumnConfig("joiningdate", "Joining Date", 100);  
	     column.setAlignment(HorizontalAlignment.RIGHT);  
	     column.setDateTimeFormat(DateTimeFormat.getShortDateFormat());  
	     configs.add(column);
	     
	     final ColumnModel cm = new ColumnModel(configs);  
	     
	     GroupingView view = new GroupingView();  
	     view.setForceFit(true);  
	     view.setGroupRenderer(new GridGroupRenderer() {  
	       public String render(GroupColumnData data) {  
	         String f = cm.getColumnById(data.field).getHeader();  
	         String l = data.models.size() == 1 ? "Item" : "Items";  
	         return f + ": " + data.group + " (" + data.models.size() + " " + l + ")";  
	       }  
	     });  
	   
	     Grid<Employee> grid = new Grid<Employee>(employeeList, cm);  
	     grid.setView(view);  
	     grid.setBorders(true); 
	     	     
	     ContentPanel cp = new ContentPanel();  
	     cp.setBodyBorder(false);  
	     cp.setHeading("Grid with Grouping");  
	     cp.setButtonAlign(HorizontalAlignment.CENTER);  
	     cp.setLayout(new FitLayout());  
	     cp.setSize(700, 420); 
	     cp.add(grid);  
	     add(cp);
	}
}
