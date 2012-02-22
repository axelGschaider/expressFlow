package com.expressflow.servlets.timer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.expressflow.datastore.PMF;
import com.expressflow.jdo.Process;
import com.expressflow.jdo.Timer;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;


public class TimerServlet extends HttpServlet {

	private static final long serialVersionUID = -3895151291801770551L;
	
	private static final Logger log = Logger.getLogger(TimerServlet.class
			.getName()); 
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException {
		Date now = new Date();
		log.info("[POST]: Heartbeat alive at " + now.getTime() +  " millis.");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		SimpleDateFormat sdfToDate = new SimpleDateFormat("yyyy-mm-dd HH:mm");
		Date now = new Date();
		log.info("[GET]: Heartbeat alive at " + now.getTime() +  " millis.");
		
			PersistenceManager pm = PMF.getManager();
			Query query = pm.newQuery(Timer.class);
			query.setFilter("trigger <= triggerParam");
			query.declareParameters("long triggerParam");
			try {
				List<Timer> activeTimers = (List<Timer>) query
						.execute(sdfToDate.parse(sdfToDate.format(now)).getTime());
				for (Timer timer : activeTimers) {
					Query pq = pm.newQuery(Process.class);
					pq.setFilter("id == idParam");
					pq.declareParameters("String idParam");
					List<Process> activeProcesses = (List<Process>)pq.execute(timer.getProcessId().toUpperCase());
					for(Process p : activeProcesses){
						try {
							// TaskQueues do not support UserService functions...
							// Add the process to the flow-processing queue
							TaskOptions taskOptions = TaskOptions.Builder.withUrl("/_ah/queue/flowprocessor");
							
							Map<String, String[]> parameterMap = request.getParameterMap();
							for (Map.Entry<String, String[]> parameter : parameterMap.entrySet()) {
								for(String value : parameter.getValue()){
									taskOptions.param(parameter.getKey(), value);
								}
							}
							
							Queue queue = QueueFactory.getQueue("flowprocessor");
					        queue.add(taskOptions.param("key", p.getId()));
					
						} catch (Exception e) {
							log.info(e.getMessage());
						} 
//						ProcessService pService = new ProcessService();
//						pService.executeProcess(p.getId(), p.getExecXml(), new HashMap<String, String[]>());
//						p.setTimesExecuted(p.getTimesExecuted() + 1);
					}
					pm.deletePersistent(timer);
				}
			} catch (Exception e) {
				log.info(e.getMessage());
			}
			finally{
				pm.close();
			}
		
	}
}
