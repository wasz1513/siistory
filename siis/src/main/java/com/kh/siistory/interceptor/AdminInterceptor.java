package com.kh.siistory.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdminInterceptor extends HandlerInterceptorAdapter{

	@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
		
			HttpServletRequest req = (HttpServletRequest)request;
			HttpServletResponse resp = (HttpServletResponse)response;
			String email = (String)req.getSession().getAttribute("email");
			boolean admin = email.equals("admin");
			log.info("로그인 체크 = {}", admin);
			if(admin) {
				return true;
			}else {
				response.sendRedirect(request.getContextPath() + "/logout");
				return false;
			}

		}
}
