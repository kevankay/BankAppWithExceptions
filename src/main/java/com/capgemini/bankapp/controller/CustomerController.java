package com.capgemini.bankapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.capgemini.bankapp.entities.Customer;
import com.capgemini.bankapp.exception.CustomerNotFoundException;

import com.capgemini.bankapp.service.CustomerService;
import com.capgemini.bankapp.service.impl.CustomerServiceImpl;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerServiceImpl customerServiceImpl;

	@Autowired
	HttpServletRequest request;

	@Autowired
	HttpSession session;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getHomePage() {
		return "home";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String showHome() {
		return "home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(Model model) {
		model.addAttribute("customer", new Customer());
		return "login";
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(@ModelAttribute Customer customer, HttpServletRequest request, HttpSession session)
			throws CustomerNotFoundException {
		Customer c = customerService.authenticate(customer);
		session = request.getSession();
		if (c != null) {
			session.setAttribute("customer", c);
			return "home";
		}
		return "loginError"; // check
	}

	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public String getlogoutPage(Model model) {
		model.addAttribute("customer", new Customer());
		return "login";
	}

	@RequestMapping(value = "/editProfile", method = RequestMethod.GET)
	public String getEditPage(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		model.addAttribute("customer", customer);
		return "updateProfileForm";
	}

	@RequestMapping(value = "/updateProfileForm.do", method = RequestMethod.POST)
	public String updateProfile(@ModelAttribute Customer customer, HttpServletRequest request) {
		if (customerService.updateProfile(customer) != null)
			return "successEdit";
		return "errorPage";
	}

	

	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public String changePasswordPage() {
		return "updatePassword";
	}

	@RequestMapping(value = "/updatePassword.do", method = RequestMethod.POST)
	public String changePassword(Model model, HttpSession session, HttpServletRequest request,
			@RequestParam String oldPassword, @RequestParam String newPassword) {
		Customer customer = (Customer) session.getAttribute("customer");
		customerService.updatePassword(customer, oldPassword, newPassword);
		session.setAttribute("customer", customer);
		return "passwordSuccess";

	}


}