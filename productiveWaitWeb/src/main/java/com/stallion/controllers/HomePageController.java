package com.stallion.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.stallion.domain.objects.Account;
import com.stallion.domain.objects.User;
import com.stallion.services.AccountService;
import com.stallion.services.UserService;


@Controller
public class HomePageController{
	
	final static Logger logger = Logger.getLogger(HomePageController.class);

	
	@RequestMapping(value = "/searchByPhone", method = RequestMethod.POST)
	protected ModelAndView searchByPhone(HttpServletRequest request, HttpServletResponse response,@ModelAttribute User userform) throws Exception {
		logger.debug("Searching visitor by phone Number: " + request.getParameter("phonenumber"));
		UserService userService = new UserService();
		AccountService accountService = new AccountService();
		int userId = userService.addUser(userform);
		Account account = new Account();
		account.setFirstName(userform.getFirstName());
		account.setLastName(userform.getLastName());
		account.setUserId(userId);
		int accountId = accountService.addAccount(account);
		ModelAndView model = null;
		if(accountId > 0) {
			//TODO: Need to validate the email address by sending the link.
			model = new ModelAndView("homepage");
		}		
		return model;
	}

}
