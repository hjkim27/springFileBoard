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
	
	public void insert(BoardVo vo) {
		sqlSessionTemplate.insert("insertBoard", vo);
	}

	public List<BoardVo> selectAll(Map<String, Object> hs) {
		return sqlSessionTemplate.selectList("selectAll", hs);
	}
	public int boardCount() {
		return sqlSessionTemplate.selectOne("boardCount");
	}
	
	public List<BoardVo> search(Map<String, Object> hs) {
		return sqlSessionTemplate.selectList("search", hs);		
	}
	public int boardCount(Map<String, Object> hs) {
		return sqlSessionTemplate.selectOne("searchBoardCount", hs);
	}
	
	public BoardVo detail(int num) {
		List<BoardVo> list = sqlSessionTemplate.selectList("detail", num);
		return list.isEmpty()? null :list.get(0);
	}
	
	public List<BoardVo> detailAnswer(int num) {
		return sqlSessionTemplate.selectList("detailAnswer", num);
	}

	public void hitIt(int num) {
		sqlSessionTemplate.update("hitIt", num);
	}

	public void update(BoardVo vo) {
		sqlSessionTemplate.update("updateBoard", vo);
	}

	public void delete(int num) {
		sqlSessionTemplate.delete("deleteBoard", num);
	}

	@Override
	public Integer nextNum(Map<String, Object> hs) throws Exception {
		return sqlSessionTemplate.selectOne("nextNum", hs);
		
	}
}
