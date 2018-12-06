package com.pk.server.service;

import javax.servlet.http.HttpServletRequest;

import com.pk.server.domain.Member;

//사용자의 요청을 처리하기 위한 메소드의 원형을 만드는 곳
public interface MemberService {
	//로그인을 처리하기 위한 메소드
	public Member login(HttpServletRequest request);
}
