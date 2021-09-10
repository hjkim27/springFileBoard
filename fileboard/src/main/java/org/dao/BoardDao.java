package org.dao;

import java.util.List;
import java.util.Map;

import org.vo.BoardVo;

public interface BoardDao {
	public int insert(BoardVo vo);
	public void update(BoardVo vo);
	public void delete(int num);
	public List<BoardVo> selectAll(int start, int end);
	public List<BoardVo> search(String type, String str, int start, int end);	// type: writer, title	str: 검색내용
	public BoardVo detail(int num);
	public void hitIt(int num);
	
	public List<BoardVo> detailAnswer(int num);
	public Map<Object, Object> boardRef();
	public int boardCount();
	public int boardCount(String type, String str);
}
