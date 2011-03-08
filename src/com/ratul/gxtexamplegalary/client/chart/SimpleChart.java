package com.ratul.gxtexamplegalary.client.chart;

import com.extjs.gxt.charts.client.Chart;
import com.extjs.gxt.charts.client.model.ChartModel;
import com.extjs.gxt.charts.client.model.ToolTip;
import com.extjs.gxt.charts.client.model.ToolTip.MouseStyle;
import com.extjs.gxt.charts.client.model.axis.XAxis;
import com.extjs.gxt.charts.client.model.axis.YAxis;
import com.extjs.gxt.charts.client.model.charts.HorizontalBarChart;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.user.client.Element;

public class SimpleChart extends LayoutContainer 
{
	protected void onRender(Element parent, int index) {

		super.onRender(parent, index);
	    setLayout(new FlowLayout(10));
	    
	    ContentPanel cp = new ContentPanel();  
	    cp.setHeaderVisible(false);
	    cp.setFrame(true);  
	    cp.setSize(500, 600);  
	    cp.setLayout(new FitLayout());
	    
	    String url = "chart/open-flash-chart.swf";  
	    final Chart chart = new Chart(url);
	    
	    chart.setBorders(true);  
	    chart.setChartModel(getHorizontalBarChartModel());  
	    cp.add(chart);      
	    this.add(cp);

	}
	
	private ChartModel getHorizontalBarChartModel() 
	{ 
	  //Create a ChartModel with the Chart Title and some style attributes
	  ChartModel cm = new ChartModel("Students by Department", "font-size: 14px; font-family:      Verdana; text-align: center;");
	 
	  XAxis xa = new XAxis();
	  //set the maximum, minimum and the step value for the X axis
	  xa.setRange(0, 200, 50);  
	  cm.setXAxis(xa);
	  
	  YAxis ya = new YAxis();
	  //Add the labels to the Y axis  
	  ya.addLabels("CSE", "EEE", "CE", "ME","CHE");  
	  ya.setOffset(true);  
	  cm.setYAxis(ya);

	  //create a Horizontal Bar Chart object and add bars to the object  
	  HorizontalBarChart bchart = new HorizontalBarChart();  
	  bchart.setTooltip("#val#Students");  
	  bchart.addBars(new HorizontalBarChart.Bar(60, "#ffff00")); 
	  //different color for different bars 
	  bchart.addBars(new HorizontalBarChart.Bar(180, "#0000ff"));  
	  bchart.addBars(new HorizontalBarChart.Bar(180, "#00ff00"));  
	  bchart.addBars(new HorizontalBarChart.Bar(120, "#ff0000"));
	  bchart.addBars(new HorizontalBarChart.Bar(120, "#333ccc"));

	  //add the bchart as the Chart Config of the ChartModel
	  cm.addChartConfig(bchart);       
	  cm.setTooltipStyle(new ToolTip(MouseStyle.FOLLOW));  
	  return cm;  
	}

}
