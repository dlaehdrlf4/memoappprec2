package com.pk.server;


import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pk.server.dao.MemoDAO;


@Controller
public class HomeController {
	@Autowired
	private SqlSession sqlSession;
	
	//데이터베이스 접속 정보 테스트
	//@Autowired
	//private DataSource dataSource;
	
	//테스트하기 위해서 만들었다.
	@Autowired
	private MemoDAO memoDao;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		//실행 시켜서 해시코드가 나오는지 확인
		/*
		System.out.println(
				"sqlSession:" + sqlSession.toString());
				*/
		//여기서 터지면 root-context에 sqlSession쪽 설정을 확인한다.
		//System.out.println(sqlSession);
		
		
		//Map<String,Object> map = new HashMap<String,Object>();
		//map.put("title","메모입니다.");
		//map.put("content","메모의 내용입니다.");
		//map.put("regdate","2016-11-11 12:00:00");
		//map.put("image_path","sample.png");
		//System.out.println(sqlSession.delete("memo.memodelete",3));
		
		//--------------- 위에는 4일날 한거
		
		//데이터 개수를 가져오기
		//System.out.println(memoDao.totalcount());
		//전체 데이터 가져오기 
		//System.out.println(memoDao.memolist());
		
		//System.out.println(memoDao.memodetail(5));
		//System.out.println(memoDao.memodelete(5));
		
		//insert는 맵을 만들어야 한다.
		//Map<String,Object> map = new HashMap<>();
		//map.put("title", "제목 테스트");
		//map.put("content","내용테스트");
		//map.put("regdate","2018-12-05 12:00:00");
		//map.put("image_path", "이미지 테스트");
		//System.out.println(memoDao.memoinsert(map));
		

		return "home";
	}
	@RequestMapping(value = "memo/insert", method = RequestMethod.GET)
	public String insert(Locale locale, Model model) {
		return "memo/insert";
	}
}





