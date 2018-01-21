package com.stallion.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.stallion.domain.objects.Account;
import com.stallion.domain.objects.User;
import com.stallion.domain.objects.Visitor;
import com.stallion.services.AccountService;
import com.stallion.services.UserService;
import com.stallion.services.VisitorService;

@Controller
public class VisitorController{
	
	final static Logger logger = Logger.getLogger(VisitorController.class);
	
	@RequestMapping(value = "/getVisitorByPhone", method = RequestMethod.GET)
	//protected @ResponseBody ModelAndView getVisitorByPhone(HttpServletRequest request, HttpServletResponse response) throws Exception {
	protected @ResponseBody List<Visitor> getVisitorByPhone(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Visitor> visitors = (List<Visitor>)request.getSession().getAttribute("visitorsPerAccount");
		String phoneNumber = request.getParameter("term");
		if(visitors == null) {
			logger.debug("Visitor list not in session. Getting from DB");
			VisitorService visitorService = new VisitorService();
			Account account = (Account)request.getSession().getAttribute("account");
			visitors = visitorService.getVisitorByAccount(account.getAccountId());
			request.getSession().setAttribute("visitorsPerAccount", visitors);
		}
		List<Visitor> results = new ArrayList<Visitor>();
		for (Visitor visitor : visitors) {
			if (visitor.getPhoneNumber().contains(phoneNumber)) {
				results.add(visitor);
			}
		}
		logger.debug("Matched results: " + results.size());
//		ModelAndView model = new ModelAndView("homepage","matchedVisitors",results);
//		return model;
		return results;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addVisitor", method = RequestMethod.POST)
	protected void addVisitor(HttpServletRequest request, HttpServletResponse response,@ModelAttribute Visitor visitorform) throws Exception {
		logger.debug("Registring new visitor: " + visitorform.toString());
		List<Visitor> visitorQueue = (List<Visitor>)request.getSession().getAttribute("visitorQueue");
		if(visitorQueue == null) {
			visitorQueue = new ArrayList<Visitor>();
		}
		Visitor visitor = new Visitor();
		visitor.setVisitorQueueIndex(visitorQueue.size() + 1);
		visitor.setAccountId(((Account)request.getSession().getAttribute("account")).getAccountId());		
		visitor.setAddressLine1(request.getParameter("address"));
		visitor.setFirstName(request.getParameter("firstName"));
		visitor.setLastName(request.getParameter("lastName"));
		visitor.setEmail(request.getParameter("email"));
		visitor.setPhoneNumber(request.getParameter("phoneNumber"));
		visitor.setCity(request.getParameter("city"));
		visitor.setState(request.getParameter("state"));
		visitor.setCountry(request.getParameter("country"));
		
		
		VisitorService visitorService = new VisitorService();
		logger.debug("Adding visitor to the Database");
		visitorService.addVisitor(visitor);
		visitorQueue.add(visitor);
		logger.debug("Adding new visitor to existing Queue:" + visitor.toString());
		request.getSession().setAttribute("visitorQueue", visitorQueue);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addExistingVisitor", method = RequestMethod.GET)
	protected void addExistingVisitor(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Visitor> visitorQueue = (List<Visitor>)request.getSession().getAttribute("visitorQueue");
		if(visitorQueue == null) {
			visitorQueue = new ArrayList<Visitor>();
		}
		Visitor visitor = new Visitor();
		visitor.setVisitorQueueIndex(visitorQueue.size() + 1);
		visitor.setAccountId(((Account)request.getSession().getAttribute("account")).getAccountId());
		visitor.setFirstName(request.getParameter("firstName"));
		visitor.setLastName(request.getParameter("lastName"));
		visitor.setPhoneNumber(request.getParameter("phoneNumber"));
		logger.debug("Adding existing visitor to Queue:" + visitor.toString());
		visitorQueue.add(visitor);
		request.getSession().setAttribute("visitorQueue", visitorQueue);
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/deleteVisitorFromQueue", method = RequestMethod.GET)
	protected void deleteVisitorFromQueue(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Visitor> visitorQueue = (List<Visitor>)request.getSession().getAttribute("visitorQueue");
		String phoneNumber = request.getParameter("phoneNumber") ;
		logger.debug("Deleting existing visitor from Queue phoneNumber: " + phoneNumber);
		for (Iterator<Visitor> it = visitorQueue.iterator(); it.hasNext(); ) {
			Visitor visitor = it.next();
			if(StringUtils.isNotBlank(visitor.getPhoneNumber()) && visitor.getPhoneNumber().equals(phoneNumber)) {
				it.remove();
			}
		}
		request.getSession().setAttribute("visitorQueue", visitorQueue);
	}



}
