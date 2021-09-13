package org.service;

import java.util.List;
import java.util.Map;

import org.dao.AttachDao;
import org.dao.BoardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.util.FileUtils;
import org.vo.AttachVo;
import org.vo.BoardVo;

@Service // 로직처리 : 서비스레이어, 내부에서 자바 로직을 처리함
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDao boardDao;
	@Autowired
	private FileUtils fileUtils;
	@Autowired
	private AttachDao attachDao;

	@Override
	public void insert(BoardVo vo, MultipartHttpServletRequest mpReq) throws Exception {
		boardDao.insert(vo);
		List<Map<String, Object>> fileList = fileUtils.fileInfo(vo, mpReq);
		for (int i = 0; i < fileList.size(); i++) {
			attachDao.insert(fileList.get(i));
		}
	}

	@Override
	public List<BoardVo> list(int start, int end) throws Exception {
		return boardDao.selectAll(start, end);
	}

	@Override
	public int listSize() {
		return boardDao.boardCount();
	}

	@Override
	public List<BoardVo> search(String type, String str, int start, int end) throws Exception {
		return boardDao.search(type, str, start, end);
	}

	@Override
	public int searchSize(String type, String str) {
		return boardDao.boardCount(type, str);
	}

	@Override
	public List<Map<Object, Object>> answerCount() {
		return boardDao.boardRef();
	}

	@Override
	public List<Map<Object, Object>> fileCount() {
		return attachDao.countFileList();
	}

	@Override
	public BoardVo read(int num) throws Exception {
		return boardDao.detail(num);
	}

	@Override
	public List<BoardVo> answer(int num) throws Exception {
		return boardDao.detailAnswer(num);
	}

	@Override
	public void readCount(int num) throws Exception {
		boardDao.hitIt(num);

	}

	@Override
	public List<Map<Object, Object>> fileList(int bNum) throws Exception {
		return attachDao.files(bNum);
	}

	@Override
	public Map<String, Object> downFile(int num) throws Exception {
		return attachDao.downFile(num);
	}

	@Override
	public void edit(BoardVo vo, MultipartHttpServletRequest mpReq) throws Exception {
		boardDao.update(vo);
		List<Map<String, Object>> fileList = fileUtils.fileInfo(vo, mpReq);
		for (int i = 0; i < fileList.size(); i++) {
			attachDao.insert(fileList.get(i));
		}
	}

	@Override
	public void delete(String type, int num) throws Exception {
		if(type.equals("article")) {
			boardDao.delete(num);
		}
		attachDao.delete(type, num);
	}
}
