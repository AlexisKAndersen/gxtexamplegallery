/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package com.ratul.gxtexamplegalary.client.model;

import java.util.Date;

import com.extjs.gxt.ui.client.data.BaseTreeModel;

public class EmployeeTreeNode extends BaseTreeModel {
 private static final long serialVersionUID = 1L;

public EmployeeTreeNode() {
  }

  public EmployeeTreeNode(String name, double salary, Date joiningdate) {
    set("name", name);
    set("salary", salary);
    set("joiningdate", joiningdate);
  }

  public Date getJoiningdate() {
    return (Date) get("joiningdate");
  }

  public String getName() {
    return (String) get("name");
  }

  public double getSalary() {
    Double salary = (Double) get("salary");
    return salary.doubleValue();
  }
  public String toString() {
    return getName();
  }
}
