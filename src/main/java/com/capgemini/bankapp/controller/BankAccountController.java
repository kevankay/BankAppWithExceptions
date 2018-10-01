package com.capgemini.bankapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.capgemini.bankapp.entities.Customer;
import com.capgemini.bankapp.exception.LowBalanceException;

import com.capgemini.bankapp.service.BankAccountService;

@Controller
public class BankAccountController {

	@Autowired
	private BankAccountService bankAccountService;

	
	@RequestMapping(value = "/checkBalance.do", method = RequestMethod.GET)
	public String checkBalance(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		model.addAttribute("customer", customer);
		return "getBalance";
	}

	@RequestMapping(value = "/transferAmount", method = RequestMethod.GET)
	public String getFundTransferPage() {
		return "fundTransfer";
	}

	@RequestMapping(value = "/transferAmount.do", method = RequestMethod.POST)
	public String fundTransfer(HttpSession session, HttpServletRequest request, Model model,
			@RequestParam long fromAcc, @RequestParam long toAcc, @RequestParam double amount)
			throws LowBalanceException {
		Customer customer = (Customer) session.getAttribute("customer");
		if (bankAccountService.fundTransfer(fromAcc, toAcc, amount)) {
			session.setAttribute("customer", customer);
			return "fundTransferSuccess";
		}
		return "errorPage";
	}

}

