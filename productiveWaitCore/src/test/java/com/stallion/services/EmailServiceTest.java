package com.stallion.services;

import org.junit.Test;

public class EmailServiceTest {
	
	@Test
	public void testSendEmail() {
		EmailService emailService = new EmailService();
		emailService.sendEmail("kams.bulusu@gmail.com");
	}

}
