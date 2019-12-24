package com.wenqi.cms.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.bawei.wenqi.utils.StringUtil;
import com.wenqi.cms.service.UserService;

public class AuthUserInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Object userInfo = request.getSession().getAttribute(CmsConstant.UserSessionKey);
		if(userInfo!=null) {
			return true;
		}
		String username = CookieUtil.getCookieByName(request, "username");
		if(StringUtil.isNotBlank(username)) {
			UserService userService = SpringBeanUtils.getBean(UserService.class);
			userInfo = userService.getByUsername(username);
			request.getSession().setAttribute(CmsConstant.UserSessionKey,userInfo);
		}
	    response.sendRedirect("/user/login");
		return false;
	}

}
