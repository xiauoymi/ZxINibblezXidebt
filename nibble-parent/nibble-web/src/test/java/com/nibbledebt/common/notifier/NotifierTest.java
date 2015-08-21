/**
 * 
 */
package com.nibbledebt.common.notifier;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.exception.VelocityException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.velocity.VelocityEngineFactory;

/**
 * @author ralam1
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class NotifierTest {
	@Autowired
	private VelocityEngineFactory velocityEngineFactory;
	
	@Test
	public void testVelociyEngine() throws ResourceNotFoundException, ParseErrorException, VelocityException, IOException{
		VelocityContext context = new VelocityContext();
		context.put("activate_link", "testlink");
		Template acTmpl = velocityEngineFactory.createVelocityEngine().getTemplate("account-created.vm");
		StringWriter writer = new StringWriter();
		acTmpl.merge(context, writer);
		System.out.println("body "+writer.toString());
	}
}
