package com.elecnor.ecosystem.helper;

import org.springframework.web.servlet.ModelAndView;

public class StaticHeaderFooterHelper {

	public ModelAndView getMAVForLoginPage(String loginTypeForLogin, ModelAndView mav) {
		if (loginTypeForLogin.equalsIgnoreCase("belcoLogin")) {
			mav.setViewName("belcoLogin");
		} else {
			mav.setViewName("staticPageHome");
		}
		return mav;
		
	}

	public ModelAndView getMAVForSuccess(String loginTypeForLogin, ModelAndView mav) {
		if (loginTypeForLogin.equalsIgnoreCase("belcoLogin")) {
			mav = new ModelAndView("belcoLogin");
		} else {
			mav = new ModelAndView("staticPageHome");
		}
		return mav;
	}

}
