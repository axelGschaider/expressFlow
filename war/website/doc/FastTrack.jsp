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

<h3 style="text-align: center;">The 10 minutes Fast Track to Workflow Modeling</h3> 
<h5 style="text-align: left;">Minute 2:</h5> 
<p style="text-align: left;"><span style="font-family: verdana,geneva;">Why start at Minute 2? Well, we guess it nearly took you a minute to read the title, right? Ok, seriously now ;-)</span><br /><span style="font-family: verdana,geneva;">Thanks for your interest in workflow modeling in general and the expressFlow framework in particular! Let's start!</span></p> 
<p><span style="font-family: verdana,geneva;">Things you need to complete this track:</span></p> 
<ol> 
<li><span style="font-family: verdana,geneva;">Access to your Google Account</span></li> 
<li><span style="font-family: verdana,geneva;">Confidence in expressFlow</span></li> 
</ol> 
<p><span style="font-family: verdana,geneva;">As the first point is quite common with the increasing number of Google Services the second is not these days. Hundreds of new projects, start ups and other kinds of services ask you for your email address. You might already be using some spam adresses for such subscription email lists. And now: Your holy GMail account! Give it away to some new project!? No way! <br />And right here we promise: We take your data REALLY seriously. Look in our code! Actually this was one (beside many others) reason to choose the GPL license model. This ensures that all expressFlow framework instances you might find some days are built on publicly available code. <br />And Google? Well, here is there <a href="http://www.google.com/intl/en/privacy/privacy-policy.html" target="_blank">Privacy Policy</a>.</span></p> 
<h5><span style="font-family: Tahoma;">Minute 5:</span></h5> 
<p><span style="font-family: verdana,geneva;">Yes right - we are half through. Data and privacy are the corner stones in SaaS frameworks built atop of Cloud computing infrastructures. And now: Model some workflow!</span></p> 
<p>Point your browser to the <a href="http://www.elasticflow.com/designer/Start.html" target="_blank">expressFlow Designer </a>instance deployed on the Google App Engine and authenticate to the expressFlow App.</p> 
<p>You should see something like this.</p> 
<p><span style="font-family: Tahoma;"><a rel="lightbox" href="http://expressflow.com/uploads/images/doc/fasttrack/Designer.PNG"><img src="http://expressflow.com/uploads/images/doc/fasttrack/Designer_thumb.png" alt="" width="500"/></a><br /></span></p> 
<p>The designer consists of three areas: On the left you see the Toolbox containing all the elements you can orchestrate to workflows. Right in the middle you see the main designer area. There you may drop the services to assemble your workflows. On the right the details area displays detailed information of the current active element.</p> 
<h5><span style="font-family: Tahoma;">Minute 6:</span></h5> 
<p><span style="font-family: verdana,geneva;">Now drag a Mobile Human Task from the Toolbox on the left and drop it on the black arrow in the center of the design area. If everything went fine your workflow should look like the one in the image below.</span></p> 
<p><span style="font-family: verdana, geneva;"><a rel="lightbox" href="http://expressflow.com/uploads/images/doc/fasttrack/DesignerWithHT.PNG"><img src="http://expressflow.com/uploads/images/doc/fasttrack/DesignerWithHT_thumb.png" alt="" width="500" /></a><br /></span></p> 
<p><span style="font-family: verdana,geneva;">Click on the newly created Mobile Human Task element and the details view of the mobile Human Task should come up in the details area on the right of the designer.</span></p> 
<p><span style="font-family: verdana, geneva;"><img src="http://expressflow.com/uploads/images/doc/fasttrack/HTDetails.PNG" alt="" width="301" height="208" /><br /></span></p> 
<p><span style="font-family: verdana,geneva;">Now you are able to define the input elements of the workflow. We choose: <span style="color: #00ff00;">Email </span>and <span style="color: #00ff00;">Name</span>. Add these two elements to the form by clicking on <span style="color: #00ff00;">Add</span> and filling out the text-boxes as illustrated below. Ensure the data is correct and finalize the forms by clicking <span style="color: #00ff00;">Ok</span> on each Form element.</span></p> 
<p><span style="font-family: verdana, geneva;"><img src="http://expressflow.com/uploads/images/doc/fasttrack/HTDetailsWithForm.PNG" alt="" width="301" height="233" /><br /></span></p> 
<p><span style="font-family: verdana,geneva;">Now you need to save the Mobile Human Task Endpoint by clicking on <span style="color: #00ff00;">Save</span>. The new data elements are automatically injected in the workflow model and a PopUp asks you, if you want to receive an email containing the URL to your newly created Mobile Human Task Endpoint. Select Yes if you do want to receive the Endpoint details.</span></p> 
<h5><span style="font-family: tahoma,arial,helvetica,sans-serif;">Minute 7:</span></h5> 
<p><span style="font-family: verdana,geneva;">After adding two elements to the mobile Human Task Form your workflow should look like the one below. Beside this you should have received an email containing an URL like<br /><a href="http://www.elasticflow.com/mobileform/---SOMENUMBER">http://www.elasticflow.com/mobileform/---SOMENUMBER---</a></span></p> 
<p style="text-align: left;"><span style="font-family: verdana,geneva; color: #ffffff;"><a rel="lightbox" href="http://expressflow.com/uploads/images/doc/fasttrack/DesignerWithFullHT.PNG"><img src="http://expressflow.com/uploads/images/doc/fasttrack/DesignerWithFullHT_thumb.png" alt="" width="500" /></a>Do not click it or share the address with your friends, as the workflow is not finished yet!</span></p> 
<p><span style="font-family: verdana,geneva;">Add an Email Service invocation <strong>after</strong> the Mobile Human Task by dragging a <em>Send Email </em>Element and dropping it in the design area. By clicking on the element you can modify the element details to the settings outlined in the image below.</span></p> 
<p><span style="font-family: verdana, geneva;"><a rel="lightbox" href="http://expressflow.com/uploads/images/doc/fasttrack/DesignerWithEmail.PNG"><img src="http://expressflow.com/uploads/images/doc/fasttrack/DesignerWithEmail_thumb.png" alt="" width="500" /></a><br /></span></p> 
<p><span style="font-family: verdana,geneva;">Save your settings in the details view - this finalizes the element settings in the workflow model. </span></p> 
<h5><span style="font-family: verdana,geneva;">Minute 8:</span></h5> 
<p><span style="font-family: verdana,geneva;">The Workflow Model is ready. Let's save it before we proceed: <span style="color: #00ff00;">Main</span> &gt; <span style="color: #00ff00;">Save Process</span></span></p> 
<p><span style="font-family: verdana,geneva;">Ready for deployment: Main &gt; Deploy Process</span></p> 
<p><span style="font-family: verdana,geneva;">This brings up the Deployment Wizard illustrated below. </span></p> 
<p><span style="font-family: verdana, geneva;"><img src="http://expressflow.com/uploads/images/doc/fasttrack/DeployHTWizard.PNG" alt="" width="500" /><br /></span></p> 
<p><span style="font-family: verdana,geneva;">Just leave everything by default and finish the deployment by clicking <span style="color: #00ff00;">Ok</span>. </span></p> 
<h5><span style="font-family: verdana,geneva;">Minute 9:</span></h5> 
<p><span style="font-family: verdana,geneva;">Everything is ready. Open your email account with the address of your newly created Mobile Human Task Endpoint address and point your smartphone browser there. You should see the form illustrated below.</span></p> 
<p><span style="font-family: verdana, geneva;"><img src="http://expressflow.com/uploads/images/doc/fasttrack/MobileHTForm.PNG" alt="" width="491" height="398" /><br /></span></p> 
<p><span style="font-family: verdana,geneva;">Enter some data (your name and your spam email address for example ;-) and hit <span style="color: #00ff00;">Execute</span>.</span></p> 
<p><span style="font-family: verdana,geneva;">This starts the workflow modeled in this track and sends an email to the provided address.</span></p> 
<h5><span style="font-family: verdana,geneva;">Minute 10:</span></h5> 
<p><span style="font-family: verdana,geneva;">Finished. Enjoy modeling in the cloud!</span></p> 

            <!-- /Main Content -->
            </div>

            
            <div id="master_contentfooter">
            <%@ include file="/website/templates/footer.htm" %>
            </div>
       </div>
</div>
</body>
</html>