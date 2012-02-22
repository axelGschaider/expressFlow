package com.expressflow.servlets.designer;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class DesignerServlet extends HttpServlet {
	private static final long serialVersionUID = -4571931798472512888L;

	private static final Logger log = Logger.getLogger(DesignerServlet.class
			.getName());

	public void doGet(HttpServletRequest requ, HttpServletResponse resp)
			throws IOException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if (user != null) {
			resp.setContentType("text/html");
			resp.getOutputStream()
					.println(
									"<html>"
									+ "<head>"
									+ "<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"
									+ "<title id='title'>expressFlow - Social BPM in the cloud</title>"
									+ "<!-- CSS -->"
									+ "<!-- base library -->"
									+ "<link rel='stylesheet' type='text/css' href='http://cdn.sencha.io/ext-4.0.2a/resources/css/ext-all-gray.css' />"
									+ "<link rel='stylesheet' type='text/css' href='css/Message.css' />"
									+ "<link rel='icon' type='image/png' href='/favicon.png' />"
									+ "<!-- overrides to base library -->"
									+ "<!-- ** Javascript ** -->"
									+ "<!-- ExtJS library: all widgets -->"
									+ "<script type='text/javascript' src='http://cdn.sencha.io/ext-4.0.2a/ext-all-debug.js'></script>"
									+ "<script type='text/javascript' src='ux/statusbar/StatusBar.js'></script>"
									+ "<script type='text/javascript' src='Ext/core/DomHelper.js'></script>"
									+ "<script type='text/javascript' src='Ext/message/Message.js'></script>"
									+ "<!-- Raphael -->"
									+ "<script type='text/javascript' src='https://s3.amazonaws.com/expressflow/raphael/raphael-min.js'></script>"
									+ "<!-- extensions -->"
									+ "<script type='text/javascript' src='com/expressflow/dd/ClickHandler.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/dd/DragElementHelper.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/dd/xFDragZone.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/dd/xFDropTarget.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/dd/xFDDProxy.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/controller/DesignerController.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/controller/ManageProcessesController.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/controller/GoogleServicesController.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/controller/DeployProcessController.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/designer/Designer.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/designer/DesignerArea.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/model/Advice.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/model/Element.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/model/Assign.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/model/Scope.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/model/Condition.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/model/Annotate.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/model/End.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/model/GoogleDocs.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/model/GoogleCalendar.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/model/MobileForm.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/model/Process.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/model/SendEmail.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/model/SendSMS.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/model/Start.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/model/Timer.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/model/Types.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/model/UrlShortener.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/model/User.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/model/Variable.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/model/xml/XProcess.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/parser/Parser.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/store/AdvicesStore.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/store/ElementsStore.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/store/MobileFormsStore.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/store/ProcessStore.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/store/VariablesStore.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/store/UserStore.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/view/Annotate.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/view/Start.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/view/Timer.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/view/End.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/view/Viewport.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/view/forms/AnnotateForm.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/view/forms/ConditionForm.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/view/forms/EndForm.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/view/forms/GoogleDocsForm.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/view/forms/MobileFormForm.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/view/forms/MobileFormContainer.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/view/forms/SendEmailForm.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/view/forms/SendSMSForm.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/view/forms/StartForm.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/view/forms/TimerForm.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/view/forms/UrlShortenerForm.js'></script>"
									+ "<script type='text/javascript' src='com/expressflow/app.js'></script>"
									+ "</head>"
									+ "<body oncontextmenu='return false;'>"
									+ "</body>" + "</html>");
		} else {
			resp.sendRedirect(resp.encodeRedirectURL(userService
					.createLoginURL(requ.getRequestURI())));
		}

	}
}
