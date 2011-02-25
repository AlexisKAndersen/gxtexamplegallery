package com.ratul.gxtexamplegalary.client.grid;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.filters.DateFilter;
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;
import com.extjs.gxt.ui.client.widget.grid.filters.ListFilter;
import com.extjs.gxt.ui.client.widget.grid.filters.NumericFilter;
import com.extjs.gxt.ui.client.widget.grid.filters.StringFilter;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Element;
import com.ratul.gxtexamplegalary.client.TestData;
import com.ratul.gxtexamplegalary.client.model.Employee;


/**
 * 
 * @author Administrator
 */
public class SortingGrid extends LayoutContainer {

	protected void onRender(Element parent, int index) {

		super.onRender(parent, index);
	    setLayout(new FlowLayout(10));
	    
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
			@Override
			public Object render(Employee model, String property,
					com.extjs.gxt.ui.client.widget.grid.ColumnData config,
					int rowIndex, int colIndex, ListStore<Employee> store,
					Grid<Employee> grid) {
				double val = (Double) model.get(property);
				String style = val < 70000 ? "red" : "green";
				return "<span style='color:" + style + "'>"
						+ number.format(val) + "</span>";

			}
		};
		column.setRenderer(checkSalary);
		configs.add(column);

		column = new ColumnConfig("joiningdate", "Joining Date", 100);
		column.setAlignment(HorizontalAlignment.RIGHT);
		column.setDateTimeFormat(DateTimeFormat.getShortDateFormat());
		configs.add(column);

		ListStore<Employee> employeeList = new ListStore<Employee>();
		employeeList.add(TestData.getEmployees());

		ColumnModel cm = new ColumnModel(configs);

		GridFilters filters = new GridFilters();
		filters.setLocal(true);

		NumericFilter numericFilter = new NumericFilter("salary");
		StringFilter nameFilter = new StringFilter("name");
		StringFilter designationFilter = new StringFilter("designation");
		DateFilter dateFilter = new DateFilter("joiningdate");

		ListStore<ModelData> departmentStore = new ListStore<ModelData>();
		departmentStore.add(departement("General Administration"));
		departmentStore.add(departement("Information Technology"));
		departmentStore.add(departement("Marketing"));
		departmentStore.add(departement("Accounts"));
		ListFilter listFilter = new ListFilter("department", departmentStore);
		listFilter.setDisplayProperty("departmentname");

		filters.addFilter(numericFilter);
		filters.addFilter(nameFilter);
		filters.addFilter(designationFilter);
		filters.addFilter(dateFilter);
		filters.addFilter(listFilter);

		final Grid<Employee> grid = new Grid<Employee>(employeeList, cm);
		grid.setStyleAttribute("borderTop", "none");
		grid.setAutoExpandColumn("name");
		grid.setBorders(true);
		grid.setStripeRows(true);
		grid.getView().setForceFit(true);
		grid.setColumnLines(true);
		grid.addPlugin(filters);

		ContentPanel cp = new ContentPanel();  
	     cp.setBodyBorder(false);  
	     cp.setHeading("Employee List With Filtering Option");  
	     cp.setButtonAlign(HorizontalAlignment.CENTER);  
	     cp.setLayout(new FitLayout());  
	     cp.setSize(700, 300); 
	     cp.add(grid);  
	     add(cp);
	}

	private ModelData departement(String departement) {
		ModelData model = new BaseModelData();
		model.set("departmentname", departement);
		return model;
	}

}
