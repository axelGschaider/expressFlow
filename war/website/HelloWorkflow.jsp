<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.google.appengine.api.users.UserService"%>
<%@page import="com.google.appengine.api.users.User"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
   <title>expressFlow - Simple, fast and flexible workflow design</title>
   <meta name="description" content="Design, administrate and share business processes in teams: Collaborate in mashup design, business process design and business process knowledge management" />
   <link href="../css/Stylesheet.css" rel="Stylesheet" type="text/css" />
    <script type="text/javascript" src="../js/prototype.js"></script>
    <script type="text/javascript" src="../js/scriptaculous.js?load=effects,builder"></script>
    <script type="text/javascript" src="../js/lightbox.js"></script>
   <link rel="Stylesheet" href="css/lightbox.css" type="text/css" media="screen" />
</head>
<body>
    <form id="MasterPageForm" runat="server">
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
           
        <!-- /Main -->
   
        </div>
        <div id="master_content">
            <div id="master_contentplaceholder">
            
            <!-- Main Content -->
            
              <h3>Hello Workflow - Part 1</h3>
<br />
<p>You start designing a process by simply dragging an Activity 
(or Copy, Assignment, REST Invoke, WSDL Invoke, Structured Activities, ...)
onto the design area in the center of the designer and drop them in the 
circles.
<table style="width: 100%;">
        <tr>
            <td align="center">
                <img alt="Drag an item" src="http://expressflow.s3.amazonaws.com/documentation/HelloWorkflow0.PNG" />
            </td>
            <td align="center">
                <img alt="Drop an item" src="http://expressflow.s3.amazonaws.com/documentation/HelloWorkflow1.PNG" /></td>
        </tr>
        <tr>
        <td align="center">
        <b>Figure 1</b>:
                Drag an item from the <b>Basic Activities</b> Toolbox ...
        </td>
            <td align="center">
                <b>Figure 2</b>:
                ... and drop it onto the <b>Design Area</b>.
            </td>
        </tr>
        </table>
        <br />
After modeling the process you may see some similar things (we use a rather simple sample ;)
<table style="width: 100%;">
        <tr>
            <td align="center">
                <img alt="Start of the day" src="http://expressflow.s3.amazonaws.com/documentation/HelloWorkflow2.PNG" />
            </td>
        </tr>
        <tr>
        <td align="center">
        <b>Figure 3</b>:
                The "all-day-favorite" workflow
        </td>
        </tr>
        </table>
        <br />
Clicking on the start icon (<img alt="Start" src="http://expressflow.s3.amazonaws.com/documentation/Start.PNG" />) 
activates the Process properties area. As illustrated in the figure below you may 
adapt the attributes to your needs. In this tutorial we focus on the <b>Role</b> tree:
<table style="width: 100%;">
        <tr>
            <td align="center">
                <img alt="Process properties" src="http://expressflow.s3.amazonaws.com/documentation/HelloWorkflow3.PNG" />
            </td>
        </tr>
        <tr>
        <td align="center">
        <b>Figure 4</b>:
                Process properties
        </td>
        </tr>
        </table>
        <br />
The role model of expressFlow is based upon the generic role model 
introduced by <a href="http://en.wikipedia.org/wiki/BPEL4People" target="_blank">BPEL4People</a>
 (the specification can be found <a href="http://www.ibm.com/developerworks/webservices/library/specification/ws-bpel4people/" target="_blank">here</a>):
<br />
<br />
<ul>
<li><i>Creator</i>:<br />
This is an <b>extension</b> of the BPEL4People role model. The Creator created the process and is determined automatically. Users cannot define creators by their own. Very simple: If you save a new process - you are the Creator!
</li>
<li><i>Business Administrator</i>:<br />
Business Administrators have full access to the process. By adding members to this role the process is <b>shared</b> among them. Be aware that all Business Administrator members have <b>full</b> access to the process (full access includes <i>DELETE</i> ;)
</li>
<li><i>Process Stakeholder</i>:<br />
Process Stakeholders can view the process. By adding members to this role the process is <b>shared</b> among them. They have <i>readonly</i> access to the process. That means that they cannot change the process design and cannot add roles to the process.
</li>
<li><i>Process Initiator</i>:<br />
The Proecss Initiator starts the process. By defining this role the responsibility of the process initiation is determined. 
</li>
</ul>
<br />
After this short overview of the expressFlow role model we are ready to apply it onto our process.
</p>
<p>
<table style="width: 100%;">
<tr>
        <td align="center"> 
           <a href="/website/GettingStarted.jsp">< Prev</a>&nbsp;<a href="/website/HelloWorkflow1.jsp">Next ></a>
            </td>
        </tr>
        </table>
</p>
            
            <!-- /Main Content -->
            
            </div>
            <div id="master_contentfooter">
            	<%@ include file="/website/templates/footer.htm" %>
            </div>
        </div>
       </div>
    </form>
</body>
</html>