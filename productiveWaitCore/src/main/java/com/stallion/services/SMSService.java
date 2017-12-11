package com.stallion.services;


import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;



public class SMSService {
	
   //Test connections	
//   public static final String ACCOUNT_SID = "AC913664f326576333497e886c9b4c8cb5";
//   public static final String AUTH_TOKEN = "96f2455229bd6f7ece06fa327e6ef4e7";

   //Live connections
   public static final String ACCOUNT_SID = "AC8691d73e4631dc40f509685ac5d2f098";
   public static final String AUTH_TOKEN = "0ce004806b271755da223cae9f091592";

   final static Logger logger = Logger.getLogger(SMSService.class);
   public void sendSMS() {
	   logger.debug("sending message");
   }
   
   public static void main(String[] args) {


	    SMSService smsService = new SMSService();
//	    //Message message = smsService.sendMessage("+19085876338", "+19082823877", "Your are 3rd in the Queue and your no will come in appx 15 min.");
//	    if(message != null) {
//	    		logger.debug(message.getSid());
//	    }
	    
	    
   }
   
//   private Message sendMessage(String toNumber, String fromNumber, String messageContents) {
//	   Message message = null;
//	   if(StringUtils.isNotEmpty(toNumber) && StringUtils.isNotEmpty(fromNumber)){
//		   logger.debug("sending message to: " + toNumber + "from number: " + fromNumber + "messageContents: " + messageContents);
//		   Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//		   	message = Message.creator(new PhoneNumber(toNumber),new PhoneNumber(fromNumber),messageContents).create();
//	    	}
//	   return message;
//   }
   
   
}
