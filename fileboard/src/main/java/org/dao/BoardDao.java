package org.dao;

import java.util.List;
import java.util.Map;

import org.vo.BoardVo;

public interface BoardDao {
	// 게시글 작성
	public void insert(BoardVo vo) throws Exception;
	
	// 게시글 목록 조회
	public List<BoardVo> selectAll(Map<String, Object> hs) throws Exception;
	public int boardCount();
	
	// 게시글 검색
	public List<BoardVo> search(Map<String, Object> hs) throws Exception;	// type: writer, title	str: 검색내용
	public int boardCount(Map<String, Object> hs);
	
	// 게시글 상세페이지
	public BoardVo detail(int num) throws Exception;

	// 게시글 답글
	public List<BoardVo> detailAnswer(int num);
	
	// 게시글 조회 시 조회수 증가
	public void hitIt(int num);

	// 게시글 수정
	public void update(BoardVo vo);
	
	// 게시글 삭제
	public void delete(int num);
	
	// 게시글 이전글, 다음글 확인
	public Integer nextNum(Map<String, Object> hs) throws Exception;
}
