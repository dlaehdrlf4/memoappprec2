package com.pk.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemoDAO {

	@Autowired
	private SqlSession sqlSession;
	
	//전체 데이터 개수를 가져오는 메소드
	public int totalcount()  {
		return sqlSession.selectOne("memo.memocount");
	}
	
	//전체 데이터 목록을 가져오는 메소드
	public List<Map<String,Object>> memolist(){
		return sqlSession.selectList("memo.memolist");
	}
	
	//num을 가져와서 하나의 데이터를 찾아오는 메소드
	public Map<String, Object> memodetail(int num){
		return sqlSession.selectOne("memo.memodetail", num);
	}
	
	
	//Map으로 파라미터를 저장해서 데이터를 추가하는 메소드
	public int memoinsert(Map<String,Object> map) {
		return sqlSession.insert("memo.memoinsert", map); //map을 넘겨주는것을 잘해주어야 한다.
	}
	
	//num을 파라미터로 받아서 데이터를 삭제하는 메소드
	public int memodelete(int num) {
		return sqlSession.delete("memo.memodelete", num);
	}
	
}
