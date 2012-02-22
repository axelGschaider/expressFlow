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

<h3>Hello Workflow - Part 2</h3>

<br />

<p>

We want to grant full access to the process to enable our 

community members to directly manipulate the process design. So we need to 

add them to the role "Business Administrator". To do this, select the role 

in the tree and click Add Member (<img src="http://expressflow.s3.amazonaws.com/expressbpel/HelloWorkflow/AddMember.PNG" />).

<table style="width: 100%;">

        <tr>

            <td align="center">

                <img alt="Business Administrator" src="http://expressflow.s3.amazonaws.com/expressbpel/HelloWorkflow/HelloWorkflow4.PNG" />

            </td>

        </tr>

        <tr>

        <td align="center">

        <b>Figure 5</b>:

                Determine role <b>Business Administrator</b>

        </td>

        </tr>

        </table>

        <br />

The <b>Add Business Process Members Dialog</b> appears where you can search 

for participants already registered to the plattform by providing the 

<i>username</i> and the <i>email</i>. 

<table style="width: 100%;">

        <tr>

            <td align="center">

                <img alt="Search for Participants" src="http://expressflow.s3.amazonaws.com/expressbpel/HelloWorkflow/HelloWorkflow5.PNG" />

            </td>

        </tr>

        <tr>

        <td align="center">

        <b>Figure 6</b>:

                Search for participants

        </td>

        </tr>

        </table>

        <br />

	<br />

        The system lists all registered participants with that username as illustrated below.

<table style="width: 100%;">

        <tr>

            <td align="center">

                <img alt="Add Participants" src="http://expressflow.s3.amazonaws.com/expressbpel/HelloWorkflow/HelloWorkflow6.PNG" />

            </td>

        </tr>

        <tr>

        <td align="center">

        <b>Figure 7</b>:

                Add a participant

        </td>

        </tr>

        </table>

        <br />

Just select the participant and click <b>Add</b> to add the participant to the previous selected role 

(Business Administrator, as we want to enable our community members to change the process design, remember?).

The final role model is illustrated in figure 8.

<table style="width: 100%;">

        <tr>

            <td align="center">

                <img alt="Resulting role model" src="http://expressflow.s3.amazonaws.com/expressbpel/HelloWorkflow/HelloWorkflow7.PNG" />

            </td>

        </tr>

        <tr>

        <td align="center">

        <b>Figure 8</b>:

               Resulting role model

        </td>

        </tr>

        </table>

        <br />

The process is now shared with user "martin1" and can be extended, changed and deleted by him. <br />

Just a final word to the notification mechanism: Every member of the process gets notified about process updates via email. So choose the memberships wisely ;)

</p>

<p>
            

<table style="width: 100%;">
<tr>
        <td align="center"> 
           <a href="/website/HelloWorkflow.jsp">< Prev</a>
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