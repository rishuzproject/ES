package com.elecnor.ecosystem.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

public class EcosystemInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		// If Request url is not of login, then only check session and valid
		// user
		if (!request.getRequestURI().equals("/ElecnorES/home")
				&& !request.getRequestURI().equals("/ElecnorES")
				&& !request.getRequestURI().equals("/ElecnorES/")
				&& !request.getRequestURI().equals("/ElecnorES/staticProduct")
				&& !request.getRequestURI().equals("/ElecnorES/staticAbout")
				&& !request.getRequestURI().equals("/ElecnorES/staticContact")
				&& !request.getRequestURI().equals("/ElecnorES/staticHome")
				&& !request.getRequestURI().equals("/ElecnorES/error")
				&& !request.getRequestURI().equals("/ElecnorES/belcoLogin")
				&& !request.getRequestURI().equals("/ElecnorES/belcoLogin#")
				&& !request.getRequestURI().contains("assets")
				&& !request.getRequestURI().contains("/ElecnorES/forgotPassword")
				&& !request.getRequestURI().contains("/ElecnorES/sendingEmailForActivation")
				&& !request.getRequestURI().contains("/ElecnorES/activateTemporaryUserAccount")
				&& !request.getRequestURI().contains("/ElecnorES/excludeInterceptor/")
				&& !request.getRequestURI().equals("/ElecnorES/checkEmailIdInTemporaryUserDetails")
				&& !request.getRequestURI().equals("/ElecnorES/saveTemporaryRegistrationFormAction")
				&& !request.getRequestURI().equals("/ElecnorES/getUserMappedRoles")
				) {
			// If Session Is empty or user is not logged in -- then redirecting
			// the request to home page
			HttpSession session = request.getSession(false);
			if (session == null || session.getAttribute("selectedUser") == null) {
				final ModelAndView mv = new ModelAndView("sessionExpire");
				ModelAndViewDefiningException mvde = new ModelAndViewDefiningException(
						mv);
				throw mvde;
			}
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}
