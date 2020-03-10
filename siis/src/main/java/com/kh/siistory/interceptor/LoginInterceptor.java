package com.kh.siistory.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter{

	@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
			// TODO Auto-generated method stub
			HttpServletRequest req = (HttpServletRequest)request;
			HttpServletResponse resp = (HttpServletResponse)response;
			String email = (String)req.getSession().getAttribute("email");
			boolean login = email!=null;
			
			if(login) {
				return true;
			}else {
				return false;
			}
			
//			return super.preHandle(request, response, handler);
		}
	
}
