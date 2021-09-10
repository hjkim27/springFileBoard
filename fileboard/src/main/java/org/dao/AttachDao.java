package org.dao;

import java.util.Map;

import org.vo.AttachVo;

public interface AttachDao {
	public int insert(AttachVo vo);
	public void update(AttachVo vo);
	public AttachVo files(int bNum);
	public int countFile(int bNum);
	public Map<Object, Object> countFileList();
}
