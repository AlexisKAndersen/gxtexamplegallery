/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package com.ratul.gxtexamplegalary.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.ratul.gxtexamplegalary.client.model.Employee;
import com.ratul.gxtexamplegalary.client.model.EmployeeTreeNode;
import com.ratul.gxtexamplegalary.client.model.Folder;

public class TestData {

	public static List<Employee> getEmployees() {
		List<Employee> employees = new ArrayList<Employee>();
		DateTimeFormat f = DateTimeFormat.getFormat("yyyy-mm-dd");
		employees.add(new Employee("Hollie Voss", "General Administration",
				"Executive Director", 150000, f.parse("2006-05-01")));
		employees.add(new Employee("Emerson Milton", "Information Technology",
				"CTO", 120000, f.parse("2007-03-01")));
		employees.add(new Employee("Christina Blake", "Information Technology",
				"Project Manager", 90000, f.parse("2008-08-01")));
		employees.add(new Employee("Heriberto Rush", "Information Technology",
				"Senior S/W Engineer", 70000, f.parse("2009-02-07")));
		employees.add(new Employee("Candice Carson", "Information Technology",
				"S/W Engineer", 60000, f.parse("2007-11-01")));
		employees.add(new Employee("Chad Andrews", "Information Technology",
				"Senior S/W Engineer", 70000, f.parse("2008-02-01")));
		employees.add(new Employee("Dirk Newman", "Information Technology",
				"S/W Engineer", 62000, f.parse("2009-03-01")));
		employees.add(new Employee("Bell Snedden", "Information Technology",
				"S/W Engineer", 73000, f.parse("2007-07-07")));
		employees.add(new Employee("Benito Meeks", "Marketing",
				"General Manager", 105000, f.parse("2008-02-01")));
		employees.add(new Employee("Gail Horton", "Marketing", "Executive",
				55000, f.parse("2009-05-01")));
		employees.add(new Employee("Claudio Engle", "Marketing", "Executive",
				58000, f.parse("2008-09-03")));
		employees.add(new Employee("Buster misjenou", "Accounts", "Executive",
				52000, f.parse("2008-02-07")));
		return employees;
	}

	public static Folder getTreeModel() {
		DateTimeFormat f = DateTimeFormat.getFormat("yyyy-mm-dd");
		Folder[] folders = new Folder[] {
			new Folder("General Administration", new Folder[] {
				new Folder("General Manager", new EmployeeTreeNode[] {
					new EmployeeTreeNode("Hollie Voss", 150000, f.parse("2006-05-01")),
					new EmployeeTreeNode("Heriberto Rush", 150000,f.parse("2007-08-01")), }),
				new Folder("Executive", new EmployeeTreeNode[] {
					new EmployeeTreeNode("Christina Blake", 45000,f.parse("2008-11-01")),
					new EmployeeTreeNode("Chad Andrews", 45000, f.parse("2008-07-01")), }), }),
	
			new Folder("Information Technology",new Folder[] {
				new Folder("Senior S/W Engineer",new EmployeeTreeNode[] {
					new EmployeeTreeNode("Dirk Newman", 70000,f.parse("2007-08-21")),
					new EmployeeTreeNode("Emerson Milton",72000,f.parse("2009-05-07")),
					new EmployeeTreeNode("Gail Horton", 680000,f.parse("2008-05-01")), }),
				new Folder("S/W Engineer",new EmployeeTreeNode[] {
					new EmployeeTreeNode("Claudio Engle", 50000,f.parse("2007-02-01")),
					new EmployeeTreeNode("Buster misjenou",52000,f.parse("2009-06-10")),
					new EmployeeTreeNode("Bell Snedden", 50000,f.parse("2008-12-01")),
					new EmployeeTreeNode("Benito Meeks", 55000,f.parse("2006-05-01")), }), }),
	
			new Folder("Marketing", new Folder[] { 
				new Folder("Executive",new EmployeeTreeNode[] {
					new EmployeeTreeNode("Candice Carson", 50000, f.parse("2007-08-21")),
					new EmployeeTreeNode("Mildred Starnes", 50000,f.parse("2008-05-01")),
					new EmployeeTreeNode("Claudio Engle", 50000, f.parse("2009-06-15")), }), }), 
		};

		Folder root = new Folder("root");
		for (int i = 0; i < folders.length; i++) {
			root.add((Folder) folders[i]);
		}

		return root;
	}
}