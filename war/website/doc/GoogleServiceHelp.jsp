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
   <link href="../../css/Stylesheet.css" rel="Stylesheet" type="text/css" />
    <script type="text/javascript" src="../../js/prototype.js"></script>
    <script type="text/javascript" src="../../js/scriptaculous.js?load=effects,builder"></script>
    <script type="text/javascript" src="../../js/lightbox.js"></script>
   <link rel="Stylesheet" href="../../css/lightbox.css" type="text/css" media="screen" />
</head>
<body>
    <div id="master_header">
        <!-- <div id="master_headertop"> -->
        <a href="http://www.expressflow.com">
                <img src="http://expressflow.s3.amazonaws.com/index/logo.png" alt="expressFlow" border="0"/>
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

<!-- Main Content -->
            <h3>Google Service Help</h3>
    <br />
    <p>
    Enabling the expressFlow Engine access to the Google Services adds a number of useful features:
	</p>
	<br />
	<ul>
	<li><b>Google Docs:</b> You can orchestrate access to your docs with expressFlow. Share, delete or move your Google Documents by the use of 
	expressFlow! Design a workflow in the browser and start it from your mobile phone!<br />
	><a href=""> See how it works!</a></li>
	<br />
	<li><b>Google Tasks:</b> U</li>
	</ul>
	<br />
            <!-- /Main Content -->
            </div>

            
            <div id="master_contentfooter">
            <%@ include file="/website/templates/footer.htm" %>
            </div>
       </div>
</div>
</body>
</html>