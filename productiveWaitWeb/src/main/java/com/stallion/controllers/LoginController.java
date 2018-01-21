package com.stallion.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.stallion.domain.objects.Account;
import com.stallion.domain.objects.User;
import com.stallion.services.AccountService;
import com.stallion.services.UserService;

@Controller
//@RequestMapping("user")
public class LoginController{
	
	final static Logger logger = Logger.getLogger(LoginController.class);

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	protected ModelAndView register(HttpServletRequest request, HttpServletResponse response,@ModelAttribute User userform) throws Exception {
		logger.debug("Registring new user: " + userform.toString());
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
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	protected ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView model = null;
		User user = (User)request.getSession().getAttribute("user");
		if(user != null) {
			logger.debug("Logging out user: " + user.getUserName());	
		    request.getSession().invalidate();
			model = new ModelAndView("index");
			model.addObject("success", "You have successfully Logged out");
		}
		return model;
	}
	

	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	protected ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		UserService userService = new UserService();
		AccountService accountService = new AccountService();
		User user = userService.isUserRegistered(userName, password);
		ModelAndView model = null;
		if(user != null) {
			logger.debug(userName + " is an Registered User");
			request.getSession().setAttribute("user", user);
			Account account = accountService.isAccountExist(user.getUserId());
			if(account != null && account.getAccountId() >0) {
				request.getSession().setAttribute("account", account);
			}
			model = new ModelAndView("homepage");
		}else {
			logger.debug("Invalid Username/password for user :" + userName);
			 model = new ModelAndView("/login");
			 model.addObject("error", "Invalid Username/password for user :" + userName);
			//throw new Exception("Invalid Username/password");
		}
		// TODO Auto-generated method stub
		
		return model;
	}
	
	@RequestMapping("/helloWorld")
    public String helloWorld(Model model) {
        model.addAttribute("message", "Hello World!");
        return "helloWorld";
    }
	
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public String submit(@Validated @ModelAttribute("user")User user, 
//			  BindingResult result,  ModelMap model) {
//			    if (result.hasErrors()) {
//			        return "error";
//			    }
//			     
//			    //Do Something
//			    return "employeeView";
//			}

}
