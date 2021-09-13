package org.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.vo.AttachVo;
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
	
	// 게시글 댓글 갯수 확인
	public List<Map<Object, Object>> answerCount();
	// 게시글 첨부파일 갯수 확인
	public List<Map<Object, Object>> fileCount();
	
	//게시글 상세페이지
	public BoardVo read(int num) throws Exception;
	// 게시글 상세페이지 답글
	public List<BoardVo> answer(int num) throws Exception;
	// 게시글 조회 시 조회수 증가
	public void readCount(int num) throws Exception;
	// 게시글 첨부파일 확인
	public List<Map<Object, Object>> fileList(int bNum) throws Exception;
	// 게시글 첨부파일 다운로드
	public Map<String, Object> downFile(int num) throws Exception;
	
	// 게시글 수정
	public void edit(BoardVo vo, MultipartHttpServletRequest mpReq) throws Exception;
	
	// 게시글 삭제
	public void delete(int num) throws Exception;
}
