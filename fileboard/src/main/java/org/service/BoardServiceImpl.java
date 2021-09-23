package org.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dao.AttachDao;
import org.dao.BoardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.util.FileUtils;
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
		if(fileList!=null && fileList.size()>0) {
			for (int i = 0; i < fileList.size(); i++) {
				attachDao.insert(fileList.get(i));
			}
		}
	}

	@Override
	public List<BoardVo> list(int start, int end) throws Exception {
		Map<String, Object> hs = new HashMap<String, Object>();
		hs.put("start", start);
		hs.put("end", end);
		return boardDao.selectAll(hs);
	}

	@Override
	public int listSize() {
		return boardDao.boardCount();
	}

	@Override
	public List<BoardVo> search(String type, String str, int start, int end) throws Exception {
		HashMap<String, Object> hs = new HashMap<String, Object>();
		hs.put("type", type);
		hs.put("str", str);
		hs.put("start", start);
		hs.put("end", end);
		return boardDao.search(hs);
	}

	@Override
	public int searchSize(String type, String str) {
		Map<String, Object> hs = new HashMap<String, Object>();
		hs.put("type", type);
		hs.put("str", str);
		return boardDao.boardCount(hs);
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
		if(fileList!=null && fileList.size()>0) {
			for (int i = 0; i < fileList.size(); i++) {
				long fileSize = (long) fileList.get(i).get("fileSize"); 
				if(fileSize!=0) {
					attachDao.insert(fileList.get(i));
				}
			}
		}
	}

	@Override
	public void delete(String type, int num) throws Exception {
		if(type.equals("article")) {
			boardDao.delete(num);
		}
		Map<String, Object> hs = new HashMap<String, Object>();
		hs.put("type", type);
		hs.put("num", num);
		attachDao.delete(hs);
	}
}
