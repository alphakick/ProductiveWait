package com.stallion.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.stallion.domain.objects.Account;
import com.stallion.services.VisitorService;

public class VisitorController extends AbstractController{
	
	final static Logger logger = Logger.getLogger(VisitorController.class);
	
	@Override
	@RequestMapping(value = "/getVisitorByPhone", method = RequestMethod.POST)
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		VisitorService visitorService = new VisitorService();
		String phoneNumber = request.getParameter("phoneNumber");
		logger.debug("get Visitor By phoneNumber: " + phoneNumber);
		Account account = (Account)request.getSession().getAttribute("account");
		visitorService.getVisitorByPhone(phoneNumber, account.getAccountId());
		ModelAndView model = new ModelAndView("index");
		return model;
	}

}
