package org.service;

import java.util.List;

import org.vo.BoardVo;

public interface BoardService {
	public void insert(BoardVo vo) throws Exception;
	public List<BoardVo> list(int start, int end) throws Exception;
	public int listSize();
}
