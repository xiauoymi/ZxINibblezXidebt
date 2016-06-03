/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.common.notifier;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.util.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

import com.nibbledebt.common.error.NotificationException;
import com.nibbledebt.common.logging.LogLevel;
import com.nibbledebt.common.logging.Loggable;
import com.nibbledebt.domain.model.NibblerData;
import com.nibbledebt.domain.model.TransactionSummary;
import com.nibbledebt.integration.sao.mandrill.AWSMailSao;

/**
 * @author ralam1
 *
 */
@Aspect
@Component
public class NotifyAspect {
	private static final String ACCOUNT_CREATED_EMAIL_SUBJ = "Nibble Account Activation";
	private static final String PASSWORD_RESET_EMAIL_SUBJ = "Nibble Password Reset";
	private static final String INVITE_EMAIL_SUBJ = " needs your help.";
	private static final String ACCOUNT_LINKED_EMAIL_SUBJ = "Nibble Account Linked";
	
	@Autowired
	private AWSMailSao awsMailSao;
	
	@Autowired
	private VelocityEngineFactoryBean velocityEngineFactory;
	
	@Pointcut("execution(@com.globber.services.notification.Notify) && @annotation(notify) && args(nibblerData) ")
	public void send(Notify notify, NibblerData nibblerData){}
	
	@Loggable(logLevel=LogLevel.INFO)
	@AfterReturning("@annotation(notify) && args(nibblerData)")
	public void sendNotification(JoinPoint joinPoint, Notify notify, NibblerData nibblerData) throws NotificationException{
		try {
			List<String> toEmails = new ArrayList<>();
			toEmails.add(nibblerData.getEmail());			
			
			if(notify.notifyType() == NotifyType.ACCOUNT_CREATED){
				VelocityContext acCtx = new VelocityContext();
				acCtx.put("activation_code", nibblerData.getActivationCode());
				Template acTmpl = velocityEngineFactory.createVelocityEngine().getTemplate("account-created.vm");
				StringWriter acWriter = new StringWriter();
				acTmpl.merge(acCtx, acWriter);				
				awsMailSao.sendEmail(ACCOUNT_CREATED_EMAIL_SUBJ, acWriter.toString(), toEmails);
			} else if(notify.notifyType() == NotifyType.PASSWORD_RESET){
				VelocityContext prCtx = new VelocityContext();
				prCtx.put("reset_code", nibblerData.getResetCode());
				prCtx.put("email", nibblerData.getEmail());
				Template prTmpl = velocityEngineFactory.createVelocityEngine().getTemplate("password-reset.vm");
				StringWriter writer = new StringWriter();
				prTmpl.merge(prCtx, writer);
				awsMailSao.sendEmail(PASSWORD_RESET_EMAIL_SUBJ, writer.toString(), toEmails);
			} else if(notify.notifyType() == NotifyType.INVITE){
				StringBuffer buffer = new StringBuffer(StringUtils.capitalizeFirstLetter(nibblerData.getFirstName()));
				buffer.append(" ");
				buffer.append(StringUtils.capitalizeFirstLetter(nibblerData.getLastName()));
				VelocityContext prCtx = new VelocityContext();
				prCtx.put("invite_code", nibblerData.getInvitationCode());
				prCtx.put("nibbler_name", buffer.toString());
				Template prTmpl = velocityEngineFactory.createVelocityEngine().getTemplate("invite-sent.vm");
				StringWriter writer = new StringWriter();
				prTmpl.merge(prCtx, writer);
				awsMailSao.sendEmail(buffer.toString() + INVITE_EMAIL_SUBJ, writer.toString(), nibblerData.getInviteEmails());
			}else if(notify.notifyType() == NotifyType.ACCOUNT_LINKED){
				VelocityContext acCtx = new VelocityContext();
				acCtx.put("firstName", nibblerData.getFirstName());
				acCtx.put("lastName", nibblerData.getLastName());
				acCtx.put("referral", nibblerData.getReferral());
				Template acTmpl = velocityEngineFactory.createVelocityEngine().getTemplate("account-linked.vm");
				StringWriter acWriter = new StringWriter();
				acTmpl.merge(acCtx, acWriter);				
				awsMailSao.sendEmail(ACCOUNT_LINKED_EMAIL_SUBJ, acWriter.toString(), toEmails);
			}
		} catch (Exception e) {
			throw new NotificationException("Error reading template.", e);
		}
		
	}
	
	
	@Loggable(logLevel=LogLevel.INFO)
	@AfterReturning(pointcut="@annotation(notify)",returning="summary")
	public void sendWeeklyTrxSummary(JoinPoint joinPoint, Notify notify,TransactionSummary summary) throws NotificationException{
		try {
			List<String> toEmails = new ArrayList<String>();
			toEmails.add(summary.getEmail());					
			if(notify.notifyType() == NotifyType.DEFAULT){
				VelocityContext acCtx = new VelocityContext();
				Template acTmpl = velocityEngineFactory.createVelocityEngine().getTemplate("weekly-update.vm");
				StringWriter acWriter = new StringWriter();
				acTmpl.merge(acCtx, acWriter);				
				awsMailSao.sendEmail("Weekly Reporting", acWriter.toString(), toEmails);
			} 
		} catch (Exception e) {
			throw new NotificationException("Error reading template : "+e.getMessage());
		}
		
	}
}
