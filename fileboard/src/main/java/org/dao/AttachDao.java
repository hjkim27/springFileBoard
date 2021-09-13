package org.dao;

import java.util.List;
import java.util.Map;

import org.vo.AttachVo;

public interface AttachDao {
	// 첨부파일 추가
	public int insert(AttachVo vo);
	
	// 게시글 리스트에서 첨부파일 갯수 확인
	public List<Map<Object, Object>> countFileList();

	// 게시글 첨부파일 조회
	public List<Map<Object, Object>> files(int bNum);
	
	// 게시글 첨부파일 다운로드
	public Map<String, Object> downFile(int num) throws Exception;
	
	// 첨부파일 삭제
	public void delete(int num);
	
	// 첨부파일 수정
	public void update(AttachVo vo);
}
