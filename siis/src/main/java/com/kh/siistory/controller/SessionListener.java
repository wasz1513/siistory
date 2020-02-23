package com.kh.siistory.controller;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.springframework.beans.factory.annotation.Autowired;

import com.kh.siistory.entity.ConnectTableDto;
import com.kh.siistory.repository.ConnectTableDao;

public class SessionListener implements HttpSessionBindingListener {

	@Autowired
	private ConnectTableDao connecttableDao;

//싱글톤 객체를 통해 접속자 수를 관리하고자 한다.
	private static SessionListener sessionListener = null;

//접속 session 의 정보를 담을 바구니 
	private static Hashtable loginUsers = new Hashtable();


// 싱글톤 처리
	public static synchronized SessionListener getInstance() {

		if (sessionListener == null) {
			sessionListener = new SessionListener();
		}
		
		return sessionListener;
	}
// 세션이 연결 되었을 때 
	@Override
	public void valueBound(HttpSessionBindingEvent event) {

		System.out.println("세션 연결 시점 입니다.");
		loginUsers.put(event.getSession(),event.getSession().getId());
		System.out.println(getUserCount());
	}

// 세션이 끊겼을 때 dao를 실행하고 싶다

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println("session 상태 제거 시점");
		System.out.println(event.getSession().getId());
		loginUsers.remove(event.getSession().getId());

		ConnectTableDto dto = ConnectTableDto.builder().build();
		connecttableDao.exit_session(dto);

		
	}
	//로그인 시점에 세션 아이디별로 집어 넣는다 . 
	public void setSession (HttpSession session) {
		System.out.println(session.getId());

		session.setAttribute(session.getId(), this);
	}
	

//접속중인 사용자수 카운트 메소드
	public int getUserCount() {
		return loginUsers.size();
	}
	
	 public void removeSession(HttpSession session1) {
	        Enumeration e = loginUsers.keys();
	        HttpSession session = null;
	        while(e.hasMoreElements()){
	            session = (HttpSession)e.nextElement();
	            if(loginUsers.get(session).equals(session1.getId())){
	                //세션이 invalidate될때 HttpSessionBindingListener를 
	                //구현하는 클레스의 valueUnbound()함수가 호출된다.
	                session.invalidate();
	            }
	       }
	    }
	 
//	 SessionListener.getInstance().removeSession((HttpSession) session);
//		
//		System.out.println((HttpSession)session);
	 

	
	
	
	
}
