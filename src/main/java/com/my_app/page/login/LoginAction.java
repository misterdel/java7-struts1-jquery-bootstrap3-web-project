package com.my_app.page.login;

import static java.lang.Boolean.TRUE;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.my_app.AppConstants;
import com.my_app.page.login.service.LoginService;
import com.my_app.page.login.service.LoginServiceFactory;

public class LoginAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {

		final LoginService loginService = new LoginServiceFactory().create((Connection) req.getAttribute("conn"));

		final LoginForm form = (LoginForm) actionForm;

		req.setAttribute("form", form);

		if (TRUE.equals(req.getSession().getAttribute(AppConstants.SESSION_ATTR_KEY_IS_LOGGED_IN))) {
			return mapping.findForward("actionHomeRedir");
		} else if ("submit".equals(form.getAction())) {
			return performSubmit(mapping, form, req, res, loginService);
		} else {
			return this.performLogin(mapping, form, req, res);
		}
	}

	private ActionForward performLogin(ActionMapping mapping, LoginForm actionForm, HttpServletRequest req,
			HttpServletResponse res) {
		return mapping.findForward("loginPage");
	}

	private ActionForward performSubmit(ActionMapping mapping, LoginForm form, HttpServletRequest req,
			HttpServletResponse res, LoginService loginService) {

		if (loginService.validate(form)) {
			req.getSession().setAttribute(AppConstants.SESSION_ATTR_KEY_IS_LOGGED_IN, true);
			req.getSession().setAttribute("username", form.getUsername());

			return mapping.findForward("actionHomeRedir");
		} else {
			super.saveErrors(req, form.getActionErrors());
			req.setAttribute("actionErrors", form.getActionErrors());
			req.setAttribute("validated", true);
			return mapping.findForward("loginPage");
		}
	}
}