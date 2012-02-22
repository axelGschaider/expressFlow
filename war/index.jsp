<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.google.appengine.api.users.UserService"%>
<%@page import="com.google.appengine.api.users.User"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
   <title>expressFlow - Simple, fast and flexible workflow design</title>
   <meta http-equiv="Content-type" content="text/html;charset=ISO-8859-1" />
   <meta name="description" content="Design, administrate and share business processes in teams: Collaborate in mashup design, business process design and business process knowledge management" />
   <link href="css/Stylesheet.css" rel="Stylesheet" type="text/css" />
    <script type="text/javascript" src="js/prototype.js"></script>
    <script type="text/javascript" src="js/scriptaculous.js?load=effects,builder"></script>
    <script type="text/javascript" src="js/lightbox.js"></script>
   <link rel="Stylesheet" href="css/lightbox.css" type="text/css" media="screen" />
   <link rel="icon" type="image/png" href="/favicon.png" />
</head>
<body>
    <div id="master_header">
        <!-- <div id="master_headertop"> -->
        <a href="http://www.expressflow.com">
                <img src="https://s3-eu-west-1.amazonaws.com/elasticflow/website/logo.png" alt="expressFlow" border="0"/>
        </a>
        <!-- </div> -->
        <!-- <div id="master_headerbottom"></div> -->
    </div>
    <div id="master_page">
        <div id="master_menu">
        <!-- Main -->
        <div class="sidebar">
        		<div class="sidebarheader">Welcome</div>
                <div id="master_sidebarLogin" class="sidebarcontent">
               		<%@ include file="/website/templates/Main.jsp" %>
                </div>
            </div>
        
        </div>
        
        <div id="master_content">
            <div id="master_contentplaceholder">
            
            
	<%
	UserService us = UserServiceFactory.getUserService();
    User u = userService.getCurrentUser();
    if (u != null) {
%>
<!-- Main Content -->
<table style="width: 100%;">
	<tr>
    <td align="center">
    <h2>Welcome to expressFlow!</h2>
    </td>
    </tr>
	<tr>
	<td>
		<table style="width: 100%;">
    <tr><td align="center">
    <img src="https://s3-eu-west-1.amazonaws.com/elasticflow/website/expressFlow.png"/><br />
    </td></tr>
	</table>
	expressFlow deployed the BETA program for collaborative workflow modeling by the use of Google App Engine. The framework consists of 
	a workflow designer ready to use in the browser and a Cloud computing aware workflow engine.<br />
	<ol>
	<li><a href="/designer/Designer">Model</a> your workflows in the browser</li>
	<li><a href="/mobileform/18002">Control</a> them from your smartphone</li>
	<li>Execute the workflows in the Google Cloud</li>
	</ol>
	Enjoy modeling in the cloud!
	</td>
    </tr>	    
	</table>
	
	<br />
            <!-- /Main Content -->
            </div>
<%
    } else {
%>
<!-- Main Content -->
<table style="width: 100%;">
	<tr>
    <td align="center">
    <h2>Social BPM in the Google cloud!</h2>
    </td>
    </tr>
	<tr>
	<td>
		<table style="width: 100%;">
    <tr><td align="center">
    <img src="https://s3-eu-west-1.amazonaws.com/elasticflow/website/expressFlow.png"/><br />
    </td></tr>
	</table>
	<h2>What's next?</h2>
	<br />
	> <a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Log in with your Google Account</a><br/>
	> <a href="http://expressflow.com/doc/10-min-fast-track.html" target="_blank">Get started</a><br/>
	> <a href="http://expressflow.com/doc/social-bpm.html">Social BPM Intro</a><br/>
	<br />
            <!-- /Main Content -->
            </div>
	<%
    }
%>
            
            <div id="master_contentfooter">
            <%@ include file="/website/templates/footer.htm" %>
            </div>
       </div>
</div>
</body>
</html>