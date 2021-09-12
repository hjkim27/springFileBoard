package org.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import org.vo.BoardVo;

@Repository //(외부IO처리 : 퍼시스턴트레이어, DB와 같은 외부 I/O를 처리함)
public class BoardDaoImpl implements BoardDao {
	private SqlSessionTemplate sqlSessionTemplate;

	public BoardDaoImpl(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	public int insert(BoardVo vo) {
		return sqlSessionTemplate.insert("insert", vo);
	}

	public List<BoardVo> selectAll(int start, int end) {
		HashMap<String, Integer> hs = new HashMap<String, Integer>();
		hs.put("start", start);
		hs.put("end", end);
		return sqlSessionTemplate.selectList("selectAll", hs);
	}
	public int boardCount() {
		return sqlSessionTemplate.selectOne("boardCount");
	}
	
	public List<BoardVo> search(String type, String str, int start, int end) {
		HashMap<String, Object> hs = new HashMap<String, Object>();
		hs.put("type", type);
		hs.put("str", str);
		hs.put("start", start);
		hs.put("end", end);
		return sqlSessionTemplate.selectList("search", hs);		
	}
	public int boardCount(String type, String str) {
		HashMap<String, String> hs = new HashMap<String, String>();
		hs.put("type", type);
		hs.put("str", str);
		return sqlSessionTemplate.selectOne("searchBoardCount", hs);
	}
	
	public BoardVo detail(int num) {
		return sqlSessionTemplate.selectOne("detail", num);
	}
	
	public List<BoardVo> detailAnswer(int num) {
		return sqlSessionTemplate.selectList("detailAnswer", num);
	}

	public void hitIt(int num) {
		sqlSessionTemplate.update("hitIt", num);
	}

	
	
	
	public void update(BoardVo vo) {
		sqlSessionTemplate.update("update", vo);
	}

	public void delete(int num) {
		sqlSessionTemplate.delete("delete", num);
	}
	

//	확인 필요
	public Map<Object, Object> boardRef() {
		return sqlSessionTemplate.selectMap("boardRef", "ref", "refcount");
	}

	


}
