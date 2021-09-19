package org.dao;

import java.util.List;
import java.util.Map;

import org.vo.ReplyVo;

public interface ReplyDao {
	//댓글 작성
	public void insertReply(ReplyVo vo) throws Exception;
	
	//댓글 수정
	public void updateReply(Map<String, Object> hs) throws Exception;
	
	//댓글 삭제
	public void deleteReply(int num) throws Exception;
	
	//게시글 댓글 전체 확인
	public List<ReplyVo> replyList(int bNum) throws Exception;
	
	//게시글 댓글 갯수 확인
	public List<Map<Object, Object>> countReply() throws Exception; 
}
