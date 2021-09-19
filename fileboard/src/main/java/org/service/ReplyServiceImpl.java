package org.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dao.ReplyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vo.BoardVo;
import org.vo.ReplyVo;

@Service
public class ReplyServiceImpl implements ReplyService {
	@Autowired
	private ReplyDao replyDao;
	
	@Override
	public void insert(ReplyVo vo) throws Exception {
		replyDao.insertReply(vo);
	}

	@Override
	public void update(String content, int num) throws Exception {
		HashMap<String, Object> hs = new HashMap<String, Object>();
		hs.put("content", content);
		hs.put("num", num);
		replyDao.updateReply(hs);
	}

	@Override
	public void delete(int num) throws Exception {
		replyDao.deleteReply(num);
	}

	@Override
	public List<ReplyVo> answerAll(int bNum) throws Exception {
		return replyDao.replyList(bNum);
	}

	@Override
	public List<Map<Object, Object>> answerCount() throws Exception {
		return replyDao.countReply();
	}

}
