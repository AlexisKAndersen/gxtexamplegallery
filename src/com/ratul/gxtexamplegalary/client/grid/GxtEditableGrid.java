package com.ratul.gxtexamplegalary.client.grid;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
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
public class GxtEditableGrid extends LayoutContainer {

	protected void onRender(Element parent, int index) {
	    super.onRender(parent, index);
	    setLayout(new FlowLayout(10));
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		ColumnConfig column = new ColumnConfig();
		column.setId("name");
		column.setHeader("Employee Name");
		column.setWidth(200);
		TextField<String> text = new TextField<String>();  
	    text.setAllowBlank(false);  
	    text.setAutoValidate(true);  
	    column.setEditor(new CellEditor(text));
	    configs.add(column);

	    final SimpleComboBox<String> combo = new SimpleComboBox<String>();  
	    combo.setTriggerAction(TriggerAction.ALL);  
	    combo.add("General Administration");  
	    combo.add("Information Technology");  
	    combo.add("Marketing");  
	    combo.add("Accounts");  
	    
	    CellEditor editor = new CellEditor(combo) {  
	      @Override  
	      public Object preProcessValue(Object value) {  
	        if (value == null) {  
	          return value;  
	        }  
	        return combo.findModel(value.toString());  
	      }  
	  
	      @Override  
	      public Object postProcessValue(Object value) {  
	        if (value == null) {  
	          return value;  
	        }  
	        return ((ModelData) value).get("value");  
	      }  
	    };  
	    
		column = new ColumnConfig("department", "Department", 150);
		column.setAlignment(HorizontalAlignment.LEFT);
		column.setEditor(editor);  
		configs.add(column);

		column = new ColumnConfig("designation", "Designation", 150);
		column.setAlignment(HorizontalAlignment.LEFT);
		TextField<String> text2 = new TextField<String>();  
	    text2.setAllowBlank(false);  
	    text2.setAutoValidate(true);  
		column.setEditor(new CellEditor(text2));
		configs.add(column);

		column = new ColumnConfig("salary", "Slary", 100);
		column.setAlignment(HorizontalAlignment.RIGHT);
		final NumberFormat number = NumberFormat.getFormat("0.00");
		GridCellRenderer<Employee> checkSalary = new GridCellRenderer<Employee>() {
			public String render(Employee model, String property,
					ColumnData config, int rowIndex, int colIndex,
					ListStore<Employee> employeeList, Grid<Employee> grid) {
				double val = (Double) model.get(property);
				String style = val < 70000 ? "red" : "green";
				return "<span style='color:" + style + "'>"
						+ number.format(val) + "</span>";
			}
		};
		column.setRenderer(checkSalary);
		column.setEditor(new CellEditor(new NumberField()));  
		configs.add(column);

		DateField dateField = new DateField();  
	    dateField.getPropertyEditor().setFormat(DateTimeFormat.getFormat("MM/dd/y"));
	    
		column = new ColumnConfig("joiningdate", "Joining Date", 100);
		column.setAlignment(HorizontalAlignment.RIGHT);
		column.setEditor(new CellEditor(dateField));  
	    column.setDateTimeFormat(DateTimeFormat.getMediumDateFormat());
		configs.add(column);

		ListStore<Employee> employeeList = new ListStore<Employee>();
		employeeList.add(TestData.getEmployees());

		ColumnModel cm = new ColumnModel(configs);

		

		ContentPanel cp = new ContentPanel();
		cp.setBodyBorder(false);
		cp.setHeading("Editable Grid");
		cp.setButtonAlign(HorizontalAlignment.CENTER);
		cp.setLayout(new FitLayout());
		cp.setSize(700, 300);
		
		
		final EditorGrid<Employee> grid = new EditorGrid<Employee>(employeeList, cm);
		grid.setStyleAttribute("borderTop", "none");
		grid.setAutoExpandColumn("name");
		grid.setBorders(true);
		grid.setStripeRows(true);
		
		cp.add(grid);
		add(cp);
	}
}
