package com.expressflow.controller;

/*
 * Copyright (c) 2011 Martin Vasko, Ph.D.
 * 
 * licensing@expressflow.com http://expressflow.com/license
 */

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.expressflow.Constants;
import com.expressflow.datastore.PMF;
import com.expressflow.datastore.User;

@Controller
@RequestMapping("/login")
public class LoginController {
	 private static Logger logger = Logger.getLogger( LoginController.class.getSimpleName());
     
     @Autowired
     private PMF pmf;
     
     @SuppressWarnings("unchecked")
     @ModelAttribute("user")
     @RequestMapping(value = "/", method = RequestMethod.POST)
     public User login( @RequestBody User user, HttpServletRequest request ) throws IOException {
             logger.info(user.getUsername() + " logged in");
             
     PersistenceManager pm = PMF.getManager();

             Query query = pm.newQuery("select from com.expressflow.bpel.datastore.User " +
                             "where password == passwordParam && usernname == nameParam " +
                             "parameters String passwordParam, String nameParam");
             
             List<User> users;
             try {
                     users = (List<User>) query.execute( user.getPassword(), user.getUsername() );
                     if( users.size() > 0 ) {
                             User luser = users.get(0);
                             request.getSession(true).setAttribute( Constants.SESSION_USER , luser);
                             return luser;
                     }
             } finally {
                     query.closeAll();
             }

     return null;
     }
     
     public PMF getPmf() {
             return pmf;
     }

     public void setPmf(PMF pmf) {
             this.pmf = pmf;
     }
}
