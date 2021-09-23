package org.dao;

import java.util.List;
import java.util.Map;

public interface AttachDao {
	// 첨부파일 추가
	public int insert(Map<String, Object> map);
	
	// 게시글 리스트에서 첨부파일 갯수 확인
	public List<Map<Object, Object>> countFileList();

	// 게시글 첨부파일 조회
	public List<Map<Object, Object>> files(int bNum);
	
	// 게시글 첨부파일 다운로드
	public Map<String, Object> downFile(int num) throws Exception;
	
	// 첨부파일 삭제처리
	public void delete(Map<String, Object> hs) throws Exception;
}
