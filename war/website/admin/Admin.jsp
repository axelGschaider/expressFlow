<%@page import="com.expressflow.datastore.process.Variable"%>
<%@page import="com.expressflow.datastore.process.Activity"%>
<%@page import="java.util.List"%>
<%@page import="javax.jdo.PersistenceManager"%>
<%@page import="com.expressflow.datastore.PMF"%>
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
    <script type="text/javascript" src="js/prototype.js"></script>
    <script type="text/javascript" src="js/scriptaculous.js?load=effects,builder"></script>
    <script type="text/javascript" src="js/lightbox.js"></script>
   <link rel="Stylesheet" href="../../css/lightbox.css" type="text/css" media="screen" />
</head>
<body>
    <div id="master_header">
        <div id="master_headertop">
                <a href="http://www.expressflow.com"><img src="http://expressflow.s3.amazonaws.com/index/logo.png" alt="expressFlow" /></a>
        </div>
        <div id="master_headerbottom"></div>
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
            <h2>Engine Administration</h2>
            
            <br />
            
            <h3>Activities</h3>
            
            <br />
            
            <%
            
            PersistenceManager pm = PMF.getManager();
            String query = "select from " + Activity.class.getName();
            List<Activity> activities = (List<Activity>)pm.newQuery(query).execute();
            if(activities.isEmpty()){
            %>
            
            <br />There are currently no activities to manage.
            
           <%
            } else {
				for(Activity a : activities){
				%>
			
				<p><%= a.getEfid().toString() %>&nbsp;<b><%= a.getName() %></b></p>
			
				<%
            	}
            }
			%>
			
			<br />
			
			<h3>Variables</h3>
			
			<br />
			
			<%
			String queryVariables = "select from " + Variable.class.getName();
            List<Variable> variables = (List<Variable>)pm.newQuery(queryVariables).execute();
            if(variables.isEmpty()){
            %>
            
            <br />There are currently no variables to manage.
            
           	<%
            } else {
				for(Variable v : variables){
				%>
			
				<p><%= v.getEfid().toString() %>&nbsp;<b><%= v.getName() %></b></p>
			
				<%
            	}
            }
			%>
            
    <%
    pm.close();	
    } else {
	%>
            
            <a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Login</a> to administrate this engine instance.
            
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