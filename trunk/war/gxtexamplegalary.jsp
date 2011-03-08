<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.jdo.Query" %>
<%@ page import="com.ratul.gxtexamplegalary.server.entity.Comments" %>
<%@ page import="com.ratul.gxtexamplegalary.server.PMF" %>
<%@ page import="javax.jdo.PersistenceManager" %>
<%@ page import="java.util.List" %>


<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta content='Ext GWT (GXT) Example Gallery With Tutorials' name='description'/>
<meta content='Ext GWT, GWT,GXT set up,GXT Grid Example,Ext GWT(GXT) Grid Local pagination, GXT(Ext GWT) editable Grid, Grouping in GXT(Ext GWT) Grid,GXT(Ext GWT) Tree,GXT(Ext GWT) Tree Filter,GXT(Ext GWT) Editable TreeGrid, Session Management in GWT,GWT and Servlet, GXT Chart, Using XTemplate with GXT ListView,reCaptcha, GXT Drag Drop, GXT Remote Pagination, GXT (Ext GWT) Filter Grid' name='keywords'/>
<script type="text/javascript" language="javascript"
	src="gxtexamplegalary/gxtexamplegalary.nocache.js"></script>
<link type="text/css" rel="stylesheet" href="Gxtexamplegalary.css">
<link type="text/css" rel="stylesheet" href="roundedcorner.css">
<link rel="stylesheet" type="text/css" href="css/gxt-all.css" />
<link rel="shortcut icon" href="images/favicon.png" type="image/x-icon">
<script language='javascript' src='flash/swfobject.js'></script>
<title>Ext GWT (GXT) Example Gallery</title>

<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-4647271-7']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>

</head>
<body>

<iframe src="javascript:''" id="__gwt_historyFrame" tabIndex='-1'
	style="position: absolute; width: 0; height: 0; border: 0"></iframe>

<div id="dvFeedback"></div>	
<div id="header">
	<div id ="logo">
	</div>
	<div class = "topmenu">
		<div class="itemRight" id="subscribeUser"></div>
		<div class="itemRightSpace"></div>
		<div class="itemRight"><a target="_blank" href="http://gxtexamplegallery.googlecode.com/svn/trunk/src/">Source Code</a></div>
		<div class="itemRightSpace"></div>
		<div class="itemRight"><a target="_blank" href="http://zawoad.appspot.com">About Me</a></div>
		<div class="itemRightSpace"></div>
		<div class="itemRight" id="commentList"></div>
	</div>
</div>	
<div id="mainbg">
	
		<div id="main-container" class="clearfix">
			<table cellpadding="0" cellspacing="0" align="center" border="0"
			bgcolor="#ffffff" width="100%">
			<tr>
				<td colspan="1"><span style="background-color:#DFF7FF; padding:0px 68px 3px 3px;">Grid</span></td>
				<td colspan="4"></td>
			</tr>
			<tr>
				<td colspan="5"> <hr style="height:1px; width:98%;background-color:#D1E7EF;"/></td>
			</tr>
			<tr height="140px">
				<td width="20%" align="center" valign="top">
				<div id="basicgrid"></div>
				<a href="http://zawoad.blogspot.com/2009/08/how-to-create-simple-grid-using-gxtext.html" class="a_tutorial" target="_blank">Tutorial</a></td>
				<td width="20%" align="center" valign="top">
				<div id="paginggrid"></div>
				<a href="http://zawoad.blogspot.com/2009/08/working-with-gxtext-gwt-grid-add-local.html" class="a_tutorial" target="_blank">Tutorial</a></td>
				<td width="20%" align="center" valign="top">
				<div id="editablegrid"></div>
				<a href="http://zawoad.blogspot.com/2009/09/working-with-gxtext-gwt-grid-create.html" class="a_tutorial" target="_blank">Tutorial</a></td>
				<td width="20%" align="center" valign="top">
				<div id="groupinggrid"></div>
				<a href="http://zawoad.blogspot.com/2009/08/working-with-gxtext-gwt-grid-add.html" class="a_tutorial" target="_blank">Tutorial</a></td>
				<td width="20%" align="center" valign="top">
				<div id="filtergrid"></div>
				<a href="http://zawoad.blogspot.com/2011/02/working-with-gxt-ext-gwt-grid-add.html" class="a_tutorial" target="_blank">Tutorial</a></td>
				
			</tr>
			<tr>
				<td colspan="1"><span style="background-color:#DFF7FF; padding:0px 68px 3px 3px;">Tree</span></td>
				<td colspan="4"></td>
			</tr>
			<tr>
				<td colspan="5"> <hr style="height:1px; width:98%;background-color:#D1E7EF;"/></td>
			</tr>
			<tr height="140px">
				<td width="20%" align="center" valign="top">
				<div id="basictree"></div>
				<a href="http://zawoad.blogspot.com/2009/09/how-to-create-simple-tree-using-gxtext.html" class="a_tutorial" target="_blank">Tutorial</a></td>
				<td width="20%" align="center" valign="top">
				<div id="contextmenutree"></div>
				<a href="http://zawoad.blogspot.com/2009/09/add-context-menu-with-gxtext-gwt.html" class="a_tutorial" target="_blank">Tutorial</a></td>
				<td width="20%" align="center" valign="top">
				<div id="filtertree"></div>
				<a href="http://zawoad.blogspot.com/2009/09/add-filter-functionality-with-gxtext.html" class="a_tutorial" target="_blank">Tutorial</a></td>
				<td width="40%" colspan="2"></td>
			</tr>
			<tr>
				<td colspan="1"><span style="background-color:#DFF7FF; padding:0px 35px 3px 3px;">Tree Grid</span></td>
				<td colspan="4"></td>
			</tr>
			<tr>
				<td colspan="5"> <hr style="height:1px; width:98%;background-color:#D1E7EF;"/></td>
			</tr>
			<tr height="140px">
				<td width="20%" align="center" valign="top">
				<div id="basictreegrid"></div>
				<a href="http://zawoad.blogspot.com/2009/11/how-to-create-basic-treegrid-using.html" class="a_tutorial" target="_blank">Tutorial</a>
				</td>
				<td width="20%" align="center" valign="top">
				<div id="editortreegrid"></div>
				<a href="http://zawoad.blogspot.com/2009/12/how-to-create-editable-treegrid-in.html" class="a_tutorial" target="_blank">Tutorial</a>
				</td>
				<td width="20%" align="center" valign="top">
				<div id="roweditortreegrid"></div>
				<div id="notwrittentutorial3"></div>
				</td>
				<td width="20%" align="center" valign="top">
				<div id="rownumbertreegrid"></div>
				<div id="notwrittentutorial4"></div>
				</td>
				<td width="20%"></td>
			</tr>
			
			<tr>
				<td colspan="1"><span style="background-color:#DFF7FF; padding:0px 3px 3px 3px;">Miscellaneous</span></td>
				<td colspan="4"></td>
			</tr>
			<tr>
				<td colspan="5"> <hr style="height:1px; width:98%;background-color:#D1E7EF;"/></td>
			</tr>
			<tr height="140px">
				<td width="20%" align="center" valign="top">
				<div id="basicChart"></div>
				<a href="http://zawoad.blogspot.com/2010/04/getting-started-with-gxt-ext-gwt-chart.html" class="a_tutorial" target="_blank">Tutorial</a>
				</td>
				<td width="20%" align="center" valign="top">
				<div id="xTemplate"></div>
				<a href="http://zawoad.blogspot.com/2010/05/how-to-use-gxt-xtemplate-with-listview.html" class="a_tutorial" target="_blank">Tutorial</a>
				</td>
				<td width="20%" align="center" valign="top">
				<div id="dragDrop"></div>
				<a href="http://zawoad.blogspot.com/2010/08/working-with-dnd-framework-of-ext-gwt.html" class="a_tutorial" target="_blank">Tutorial</a>
				</td>
				<td width="20%" align="center" valign="top">
				<div id="rownumbertreegrid"></div>
				<div id="notwrittentutorial4"></div>
				</td>
				<td width="20%"></td>
			</tr>
		</table>
		</div>
		
		
	
</div>
    <div id="bottom">
    <table width="99%" cellspacing="30px">
    <tr>
    <td width="25%" style="vertical-align:top">
    	<div class="bottom_header">Other Useful Tutorials of GWT & GXT</div>
    	<div><a class="bottomLink" target="_blank" href="http://zawoad.blogspot.com/2010/07/working-with-gxt-grid-add-remote.html">How to Add Remote Pagination</a></div>
    	<div><a class="bottomLink" target="_blank" href="http://zawoad.blogspot.com/2010/06/google-app-engine-jdo-and-gxtext-gwt.html">Google App Engine, JDO & GXT</a></div>
    	<div><a class="bottomLink" target="_blank" href="http://zawoad.blogspot.com/2010/04/how-to-call-servlet-in-gwt.html">Calling a Servlet in GWT</a></div>
    	<div><a class="bottomLink" target="_blank" href="http://zawoad.blogspot.com/2009/12/session-management-set-and-get-data.html">Session Management in GWT</a></div>
    	<div><a class="bottomLink" target="_blank" href="http://zawoad.blogspot.com/2009/06/how-to-use-ext-gwt-in-eclipse-34-to.html">How to Use GXT in Eclipse</a></div>
    	<div><a class="bottomLink" target="_blank" href="http://zawoad.blogspot.com/2009/06/getting-started-with-gwt-create-your.html">Getting Started With GWT</a></div>
    </td>
    <td width="35%" style="vertical-align:top">
    	<div class="bottom_header">Recent Comments</div>
    	<%
    PersistenceManager pm = PMF.get().getPersistenceManager();
    String query = "select from " + Comments.class.getName() + " order by postedDate desc";
    Query q = pm.newQuery(query);
    q.setRange(0,3);
    List<Comments> greetings = (List<Comments>)q.execute();
    if (!greetings.isEmpty()) 
    {
    	for (Comments g : greetings) {
	%>
		<div style="height:inherit; padding:10px;font-size:13px; width:300px; clear:both;">
			<span style="color:#8EC3CF; font-weight:bold; font-size:11px"><%= g.getPostedBy()%></span><br>
			<span style="color:#BFE8FF; font-style: italic; width:300px;"><%= g.getComments()%></span>
		</div>
    	<br>
    	<hr style="height:1px; background-color:#6BA1BF;">
    
    <%
    	}
    }
    %>
    </td>
    <td width="30%" style="vertical-align:top">
    	<table align="center" width="100%">
    		<tr>
    		<td>
    		<!--buzz button-->
    	<a href="http://www.google.com/buzz/post"
    	class="google-buzz-button"
    	title="Google Buzz"
    	data-message="Message Text"
    	data-url="http://http://gxtexamplegallery.appspot.com"
    	data-locale="en"
    	data-button-style="normal-count"></a>
    	<script type="text/javascript" src="http://www.google.com/buzz/api/button.js"></script>
    <!--buzz button end-->
    
    &nbsp;
    
    <!--tweetme button-->
    	<script type="text/javascript">
    		tweetmeme_source = 'zawoad';
    		tweetmeme_url = 'http://gxtexamplegallery.appspot.com';
		</script>
		<script type="text/javascript" src="http://tweetmeme.com/i/scripts/button.js"></script>
	<!--tweetme button end-->
&nbsp;

	<!--dig button-->
			<script type="text/javascript">
			(function() {
			var s = document.createElement('SCRIPT'), s1 = document.getElementsByTagName('SCRIPT')[0];
			s.type = 'text/javascript';
			s.async = true;
			s.src = 'http://widgets.digg.com/buttons.js';
			s1.parentNode.insertBefore(s, s1);
			})();
			</script>
			<a class="DiggThisButton DiggMedium"></a>
	<!--dig button end-->
    		</td>
    		</tr>
    		
	      		<tr style="color:white;">
	        		<td>
	        		<div class="bottom_header">Leave Your Comment</div></td>
	        		       
	      		</tr>
	      		<tr style="color:white;">
	        		<td colspan="2"><hr style="height: 1px; width:80%;"></td>        
	      		</tr>
	      		<tr>
	        		<td width="20%" style="color:#FFFFFF;"> Name:</td>
	        	</tr>
	      		<tr>
	        		<td id="nameField"></td>
	      		</tr>
	      		<tr>
	        		<td style="vertical-align: top;color:#FFFFFF;"> Comment:</td>
	        	</tr>
	      		<tr>
	        		<td id="commentField"></td>
	      		</tr>
	      		<tr>
	        		<td><span id="sendButton" style="float:left;"></span><span id="busyIcon" style="float:left;margin-left:10px;"></span></td>
	      		</tr>
                <tr style="color:white;">
	        		<tdheight="20px"></td>        
	      		</tr>
	    	</table>
    </td>
    </tr>
    </table>
    		
		
		
    </div>
<!--div id="footer">
	<div style="margin:auto;width:98%">
		<div class="itemLeft"><img src="images/icon_code_repository.png"><a class="bottomLink" target="_blank" href="http://gxtexamplegallery.googlecode.com/svn/trunk/src/">Source Code</a></div>
        <div class="itemLeftSpace"></div>
        <div class="itemLeft"><img src="images/home2.png"><a class="bottomLink" target="_blank" href="http://zawoad.blogspot.com">Developer's Home</a></div>
        <div class="itemLeftSpace"></div>
        <div class="itemLeft" id="commentList"><img src="images/comment.png"></div>
        <div class="itemLeftSpace"></div>
        <div class="itemRight"><a class="bottomLink" onClick="window.open('http://www.facebook.com/sharer.php?u=http%3A%2F%2Fgxtexamplegallery.appspot.com','Face Book Share','width=600,height=300')"><img src="images/fb_share.jpg"></a></div>
        <div class="itemRightSpace"></div>
        <div class="itemRight">
        <script src="http://cdn.socialtwist.com/200812034021/script.js"></script>
        <img onClick="cw(this, {id:'200812034021',link: window.location, title: document.title })" onMouseOver="showHoverMap(this, '200812034021', window.location, document.title)" onMouseOut="hideHoverMap(this)" src="http://images.socialtwist.com/200812034021/button.png" style="border: 0pt none; padding: 0pt; margin: 0pt;">
        </div>
        <div class="itemRightSpace"></div>
        
	</div>
</div--!>

</body>
</html>
