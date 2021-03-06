/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.app;

import java.io.File;

import org.apache.catalina.startup.Tomcat;

/**
 * 
 * This is the main class that runs the embedded tomcat container to
 * run this web application.
 * 
 * @author ralam1
 *
 */
public class TomcatMain {
	public static void main(String[] args) throws Exception {
		  String webappDirLocation = "src/main/webapp/";
	      Tomcat tomcat = new Tomcat();

	      //The port that we should run on can be set into an environment variable
	      //Look for that variable and default to 8080 if it isn't there.
	      String webPort = System.getenv("PORT");
	      if(webPort == null || webPort.isEmpty()) {
	          webPort = "9000";
	      }

	      tomcat.setPort(Integer.valueOf(webPort));

	      tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
	      System.out.println("configuring app with basedir: " + new File("./" + webappDirLocation).getAbsolutePath());

	      tomcat.start();
	      tomcat.getServer().await();
	  }
}
