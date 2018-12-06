package com.pk.server;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pk.server.domain.Member;
import com.pk.server.service.MemberService;

@RestController
public class DataController {
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="member/login", method=RequestMethod.GET)
	public Map<String, Object> login(
			HttpServletRequest request){
		Map<String, Object>map = new HashMap<String, Object>();
		//결과 받아오기
		Member member = memberService.login(request);
		//로그인 실패
		if(member == null) {
			member = new Member();
			member.setId("NULL");
		}
		//결과를 저장
		map.put("result", member);
		
		return map;
	}
}
