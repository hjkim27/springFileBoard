package org.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.vo.BoardVo;

public interface BoardService {
	//게시글 작성
	public void insert(BoardVo vo, MultipartHttpServletRequest mpReq) throws Exception;
	
	// 게시글 목록 조회
	public List<BoardVo> list(int start, int end) throws Exception;
	public int listSize();
	
	// 게시글 목록 검색
	public List<BoardVo> search(String type, String str, int start, int end) throws Exception;
	public int searchSize(String type, String str);
	
	//게시글 상세페이지
	public BoardVo read(int num) throws Exception;
	// 게시글 상세페이지 답글
	public List<BoardVo> answer(int num) throws Exception;
	// 게시글 조회 시 조회수 증가
	public void readCount(int num) throws Exception;
	
	// 게시글 수정
	public void edit(BoardVo vo) throws Exception;
	
	// 게시글 삭제
	public void delete(int num) throws Exception;
}
