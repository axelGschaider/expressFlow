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

<h3>Using expressFlow Designer</h3>

<br />

<p>

After <a href="<%= userService.createLoginURL(request.getRequestURI()) %>">logging in</a> you can immediately start to use the designer! 

Start designing your own workflows by dragging a basic activity from the Toolbox on the left:</p>

    <table style="width: 100%;">

        <tr>

            <td align="center">

                <img alt="Basic Activities" src="http://expressflow.s3.amazonaws.com/expressbpel/GettingStarted/BasicActivities.PNG" /></td>

        </tr>

        <tr>

            <td align="center">

                <b>Figure 1</b>:

                Drag a basic activity from the <b>Basic Activities</b> Toolbox

            </td>

        </tr>

        </table>

        <br />

and drop it on the process design area in the middle of the designer canvas:

<table style="width: 100%;">

        <tr>

            <td align="center">

                <img alt="Sample Process" src="http://expressflow.s3.amazonaws.com/expressbpel/GettingStarted/SampleProcess1.PNG" /></td>

        </tr>

        <tr>

            <td align="center">

                <b>Figure 2</b>:

                Drop activities on the process design area

            </td>

        </tr>

        </table>

        <br />

After dropping the activity to the designer you are almost complete! Just fine tune the settings in the 

properties panel. To adjust the default settings to your needs click the element on the design area and 

the properties will appear on the right in the properties panel. 

<table style="width: 100%;">

        <tr>

            <td align="center">

                <img alt="Sample Process" src="http://expressflow.s3.amazonaws.com/expressbpel/GettingStarted/ProcessProperties.PNG" /></td>

        </tr>

        <tr>

            <td align="center">

                <b>Figure 3</b>:

                Properties panel of the whole process

            </td>

        </tr>

        </table>

        <br />

    To get an better overview of the described features we captured a video using the designer. 

    In this sample video the basic features of the designer are presented:           

    <p>

    <table style="width: 100%;">

        <tr>

            <td align="center">

    <object width="500" height="350"> <param name="movie" value="http://www.youtube.com/v/U_yF7gQSuVE"> </param> <embed src="http://www.youtube.com/v/U_yF7gQSuVE" type="application/x-shockwave-flash" width="500" height="350"> </embed> </object>

</td>

        </tr>

        <tr>

            <td align="center">

                <b>Video 1</b>:

                Create a simple process

            </td>

        </tr>

        </table> 

</p>
            

<table style="width: 100%;">
<tr>
        <td align="center"> 
           <a href="/website/HelloWorkflow.jsp">Next ></a>
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