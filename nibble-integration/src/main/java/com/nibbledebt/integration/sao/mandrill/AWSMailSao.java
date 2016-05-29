package com.nibbledebt.integration.sao.mandrill;


import java.io.IOException;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.ListVerifiedEmailAddressesResult;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.simpleemail.model.VerifyEmailAddressRequest;
import com.nibbledebt.common.error.NotificationException;

@Component()
public class AWSMailSao {
    private static final String FROM = "tyler@nibbledebt.com";
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(AWSMailSao.class);
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
       // verifyEmailAddress(ses, toEmail.get(0));
        
     // Construct an object to contain the recipient address.
        Destination destination = new Destination().withToAddresses(toEmail);
        
        // Create the subject and body of the message.
        Content textBody = new Content().withData(content); 
        Body body = new Body().withHtml(textBody);
        
        // Create a message with the specified subject and body.
        Message message = new Message().withSubject(new Content().withData(subject)).withBody(body);
        // Assemble the email.
        SendEmailRequest request = new SendEmailRequest().withSource(FROM).withDestination(destination).withMessage(message);
        
        try
        {        
            LOG.info("Attempting to send an email through Amazon SES by using the AWS SDK for Java...");
        
            // Instantiate an Amazon SES client, which will make the service call. The service call requires your AWS credentials. 
            // Because we're not providing an argument when instantiating the client, the SDK will attempt to find your AWS credentials 
            // using the default credential provider chain. The first place the chain looks for the credentials is in environment variables 
            // AWS_ACCESS_KEY_ID and AWS_SECRET_KEY. 
            // For more information, see http://docs.aws.amazon.com/AWSSdkDocsJava/latest/DeveloperGuide/credentials.html
            //AmazonSimpleEmailServiceClient client = new AmazonSimpleEmailServiceClient();
               
            // Choose the AWS region of the Amazon SES endpoint you want to connect to. Note that your sandbox 
            // status, sending limits, and Amazon SES identity-related settings are specific to a given AWS 
            // region, so be sure to select an AWS region in which you set up Amazon SES. Here, we are using 
            // the US West (Oregon) region. Examples of other regions that Amazon SES supports are US_EAST_1 
            // and EU_WEST_1. For a complete list, see http://docs.aws.amazon.com/ses/latest/DeveloperGuide/regions.html 
            Region REGION = Region.getRegion(Regions.US_EAST_1);
            ses.setRegion(REGION);
       
            // Send the email.
            ses.sendEmail(request);  
            LOG.info("Email sent!");
        }
        catch (Exception ex) 
        {
            LOG.info("The email was not sent.");
            LOG.info("Error message: " + ex.getMessage());
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
        LOG.info("Please check the email address " + address + " to verify it");
        //System.exit(0);
    }
}
