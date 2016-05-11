package com.nibbledebt.integration.sao.mandrill;


import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.simpleemail.AWSJavaMailTransport;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.ListVerifiedEmailAddressesResult;
import com.amazonaws.services.simpleemail.model.VerifyEmailAddressRequest;
import com.nibbledebt.common.error.NotificationException;

@Component()
public class AWSMailSao {
    private static final String FROM = "tyler@nibbledebt.com";

    /**
     * Send email using the AWS service.
     * 
     * @param subject
     * @param content
     * @param toEmails
     * @throws NotificationException
     */
    public void sendEmail(String subject, String content, List<String> toEmail) throws IOException, NotificationException {
        /*
         * Important: Be sure to fill in your AWS access credentials in the
         * AwsCredentials.properties file before you try to run this sample.
         * http://aws.amazon.com/security-credentials
         */
        PropertiesCredentials credentials = new PropertiesCredentials(
        		AWSMailSao.class
                        .getResourceAsStream("AwsCredentials.properties"));
        AmazonSimpleEmailService ses = new AmazonSimpleEmailServiceClient(credentials);

        verifyEmailAddress(ses, FROM);

		/*
		 * Setup JavaMail to use the Amazon Simple Email Service by specifying
		 * the "aws" protocol.
		 */
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "aws");

        /*
         * Setting mail.aws.user and mail.aws.password are optional. Setting
         * these will allow you to send mail using the static transport send()
         * convince method.  It will also allow you to call connect() with no
         * parameters. Otherwise, a user name and password must be specified
         * in connect.
         */
        props.setProperty("mail.aws.user", "AKIAJCUEGJLQGVM4NGFQ");
        props.setProperty("mail.aws.password", "tcb0TrTFoSa8BTXTTRBK6QmD0sMFtli1iG0jpzVW");

        Session session = Session.getInstance(props);

        try {
            // Create a new Message
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(FROM));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail.get(0)));
            msg.setSubject(subject);
            msg.setContent(content, "text/html");
            msg.saveChanges();

            // Reuse one Transport object for sending all your messages
            // for better performance
            Transport t = new AWSJavaMailTransport(session, null);
            t.connect();
            t.sendMessage(msg, null);

            // Close your transport when you're completely done sending
            // all your messages
            t.close();
        } catch (AddressException e) {
            e.printStackTrace();
            System.out.println("Caught an AddressException, which means one or more of your "
                    + "addresses are improperly formatted.");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Caught a MessagingException, which means that there was a "
                    + "problem sending your message to Amazon's E-mail Service check the "
                    + "stack trace for more information.");
        }
    }

    /**
     * Sends a request to Amazon Simple Email Service to verify the specified
     * email address. This triggers a verification email, which will contain a
     * link that you can click on to complete the verification process.
     *
     * @param ses
     *            The Amazon Simple Email Service client to use when making
     *            requests to Amazon SES.
     * @param address
     *            The email address to verify.
     */
    private static void verifyEmailAddress(AmazonSimpleEmailService ses, String address) {
        ListVerifiedEmailAddressesResult verifiedEmails = ses.listVerifiedEmailAddresses();
        if (verifiedEmails.getVerifiedEmailAddresses().contains(address)) return;

        ses.verifyEmailAddress(new VerifyEmailAddressRequest().withEmailAddress(address));
        System.out.println("Please check the email address " + address + " to verify it");
        System.exit(0);
    }
}
