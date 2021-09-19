package org.service;

import java.util.List;
import java.util.Map;

import org.vo.ReplyVo;

public interface ReplyService {
	// 댓글 작성
	public void insert(ReplyVo vo) throws Exception;
	
	// 댓글 수정
	public void update(String content, int num) throws Exception; 
	
	// 댓글 삭제
	public void delete(int num) throws Exception;
	
	// 댓글, 대댓 확인
	public List<ReplyVo> answerAll(int bNum) throws Exception;

	// 게시글 댓글 갯수 확인
	public List<Map<Object, Object>> answerCount() throws Exception;
}
