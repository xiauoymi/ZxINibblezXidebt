/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.app;

import java.io.File;

import org.apache.catalina.Service;
import org.apache.catalina.connector.Connector;
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
	      Tomcat tomcat = new Tomcat();
//	      Service service = tomcat.getService();

	      //The port that we should run on can be set into an environment variable
	      //Look for that variable and default to 8080 if it isn't there.
	      String webPort = System.getProperty("PORT") == null ? System.getenv("PORT") : System.getProperty("PORT");
	      String webappDirLocation = System.getProperty("WEBAPP_DIR") == null ? System.getenv("WEBAPP_DIR") : System.getProperty("WEBAPP_DIR");
	      String ksPath = System.getProperty("KS_PATH") == null ? System.getenv("KS_PATH") : System.getProperty("KS_PATH");
	      String ksPassword = System.getProperty("KS_PASSWORD") == null ? System.getenv("KS_PASSWORD") : System.getProperty("KS_PASSWORD");
	      String keyAlias = System.getProperty("KEY_ALIAS") == null ? System.getenv("KEY_ALIAS") : System.getProperty("KEY_ALIAS");
	      System.out.println("detected env variables: PORT[" + webPort +"] , WEBAPP_DIR["+webappDirLocation + "].");

	      if(webPort == null || webPort.isEmpty()) {
	          webPort = "9000";
	      }

	      tomcat.setPort(Integer.valueOf(webPort));
	      tomcat.setConnector(getSSLConnector(tomcat.getConnector(), ksPath, ksPassword, keyAlias, Integer.valueOf(webPort)));
//	      service.addConnector(getSSLConnector(ksPath, ksPassword, keyAlias, Integer.valueOf(webPort)));

	      tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
	      System.out.println("configuring app with basedir: " + new File("./" + webappDirLocation).getAbsolutePath());

	      tomcat.start();
	      tomcat.getServer().await();
	      
	      System.out.println("Started Tomcat Server on port: "+ webPort+".");
	  }
	
	public static Connector getSSLConnector(Connector httpsConnector, String ksPath, String ksPassword, String keyAlias, Integer webPort){
//			Connector httpsConnector = new Connector();
	       httpsConnector.setPort(webPort);
	       httpsConnector.setSecure(true);
	       httpsConnector.setScheme("https");
	       httpsConnector.setAttribute("keyAlias", keyAlias);
	       httpsConnector.setAttribute("keystorePass", ksPassword);
	       httpsConnector.setAttribute("keystoreFile", ksPath);
	       httpsConnector.setAttribute("keystoreType", "JCEKS");
	       httpsConnector.setAttribute("clientAuth", "false");
	       httpsConnector.setAttribute("sslProtocol", "TLSv1.2");
	       httpsConnector.setAttribute("SSLEnabled", true);
	       return httpsConnector;
	       
	}
}
