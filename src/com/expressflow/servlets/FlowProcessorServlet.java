package com.expressflow.servlets;

import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

import com.expressflow.datastore.PMF;
import com.expressflow.engine.xml.Parser;
import com.expressflow.jdo.Process;
import com.expressflow.model.Variable;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

public class FlowProcessorServlet extends HttpServlet {
	private static final long serialVersionUID = -4333457979548248927L;

	private static final Logger log = Logger
			.getLogger(FlowProcessorServlet.class.getName());

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String processId = request.getParameter("key");
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			Key k = KeyFactory.createKey(Process.class.getSimpleName(),
					processId);
			Process process = pm.getObjectById(Process.class, k);

			Document doc = null;
			MemcacheService memcache = MemcacheServiceFactory
					.getMemcacheService(process.getId());

			Parser parser = new Parser(processId);
			doc = new SAXBuilder().build(new InputSource(new StringReader(
					process.getExecXml())));
			Element root = doc.getRootElement();
			List<?> rootInfo = root.getChildren();

			// 1st pass parse - build variables structure
			parser.parseVariables(processId, root);

			// Inject parameters passed from endpoint
			Iterator<?> parIterator = request.getParameterMap().entrySet()
					.iterator();
			while (parIterator.hasNext()) {
				Map.Entry pEntry = (Map.Entry) parIterator.next();

				Variable v = (Variable) memcache.get(pEntry.getKey());
				// Variable v = mSingleton.variables.get(pEntry.getKey());
				if (v != null && pEntry.getValue() != null) {
					v.setValue(((String[]) pEntry.getValue())[0]);
					memcache.put(pEntry.getKey().toString(), v);
					// mSingleton.variables.put(pEntry.getKey().toString(), v);
				}
			}

			// 2nd pass parse - execute the process
			Iterator<?> elementIterator = rootInfo.iterator();
			while (elementIterator.hasNext())
				parser.executingParse((Element) elementIterator.next());

			// ProcessService pService = new ProcessService();
			// pService.executeProcess(processId, process.getExecXml(),
			// request.getParameterMap());
			// process.setTimesExecuted(process.getTimesExecuted() + 1);
			// Reset the Memcache
			memcache = MemcacheServiceFactory.getMemcacheService(process
					.getId());
			memcache.clearAll();
		} catch (Exception e) {
			log.log(Level.ALL, e.getMessage());
		} finally {
			pm.close();
		}

	}
}
