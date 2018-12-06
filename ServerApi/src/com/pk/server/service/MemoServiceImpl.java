package com.pk.server.service;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.pk.server.dao.MemoDAO;

@Service
public class MemoServiceImpl implements MemoService {

	@Autowired
	private MemoDAO memoDao;

	@Override
	public Map<String, Object> memolist(HttpServletRequest request) {
		
		//전체 데이터 개수 가져오기
		int totalCount = memoDao.totalcount();
		
		List<Map<String, Object>> list = memoDao.memolist();
		
		Map<String, Object> map = new HashMap<>();
		map.put("totalcount", totalCount);
		map.put("memos",list);
		return map;
	}

	@Override
	public Map<String, Object> memodetail(
			HttpServletRequest request) {
		//num 이라는 파라미터를 읽어서 정수로 변경
		int num = Integer.parseInt(request.getParameter("num"));
		Map<String,Object> map = memoDao.memodetail(num);
		return map;
	}

	//<a href="num=1 "> //뒤에 공백이 있으니까 터져버린다. 
	@Override
	public Map<String, Object> memodelete(HttpServletRequest request) {
			//파라미터 읽어오기
			int num  = Integer.parseInt(request.getParameter("num"));
			
			//데이터 베이스의 삽입, 삭제 , 갱신 작업을 수행하면 영향받은 행의 개수 리턴
			//0이 리턴되면 잘못된것이 아니고 조건에 맞는 데이터가 없는 것입니다.
			int r = memoDao.memodelete(num);
			
			Map<String, Object> map = new HashMap<>();
			if(r >= 0) {
			map.put("result","success");
			}else {
				map.put("result", "Fail");
			}
			return  map;
	}

	@Override
	public Map<String, Object> memoinsert(MultipartHttpServletRequest request) {
			
		System.out.println("클라이언트 요청 도달");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			System.out.println(content);
			//파라미터로 전달된  파일 찾아오기
			MultipartFile image = request.getFile("image_path");
			
			// 파일 업로드 할  프로젝트 내의 실제 경로 가져오기
			//서블릿 3.0이상을 사용하면 request 대신에
			//request.getServletContext() 사용
			String uploadPath = request.getRealPath("/memoimage");
			//자바에서 랜덤한 65글자의 만들어 줄 수 있는 객체 생성
			UUID uid = UUID.randomUUID();
			//원본 파일 이름 찾아오기
			String filename = image.getOriginalFilename();
			//DAO 에 데이터베이스 수행 메소드에 전달할 파라미터 생성
			Map<String, Object> map = new HashMap<>();
			try {
				//업로드할 파일이 있다면
				if(filename.length() > 0 ) {
					//랜덤한 문자열과 파일의 확장자를 연결해서 새로운 파일이름 만들기
					int idx = filename.lastIndexOf(".");
					String filepath = uid  + filename.substring(idx);
					//업로드할 파일 경로 만들기
					String upload = uploadPath + "/" + filepath;
					File f = new File(upload);
					//파일 업로드
					image.transferTo(f);
					map.put("image_path",filepath);
					
				}else {
					//파일이 없음
					map.put("image_path", " ");
				}
				map.put("title",title);
				map.put("content", content);
				//오늘 날짜 및 시간을 yyyy-MM-dd h:m:s 의 형식의 문자열로 만들기
				Calendar cal = Calendar.getInstance();
				String regdate = cal.get(Calendar.YEAR) + "-" + 
				cal.get(Calendar.MONTH+1) + "-" + 
				cal.get(Calendar.DAY_OF_MONTH) + " " +
				cal.get(Calendar.HOUR) + ":" +
				cal.get(Calendar.MINUTE) + ":" +
				cal.get(Calendar.SECOND);
				//Date와 DataFormatter의 조합으로 가능
				map.put("regdate", regdate);
				
				
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			System.out.println("파라미터:" + map);
			int r = memoDao.memoinsert(map);
			Map<String, Object> resultMap = 
						new HashMap<>();
			if(r > 0) {
				resultMap.put("result", "success");
			}else {
				resultMap.put("result", "fail");
			}
			System.out.println("결과: " + resultMap);
			return resultMap;
			
	}
}
