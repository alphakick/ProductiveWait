package com.stallion.services;

import static org.junit.Assert.*;

import org.junit.Test;

public class SMSServiceTest {

	@Test
	public void testSendMessage() {
		SMSService smsService = new SMSService();
		smsService.sendSMS();
	}

}
