package org.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import org.vo.ReplyVo;

@Repository
public class ReplyDaoImpl implements ReplyDao {
	private SqlSessionTemplate sqlSessionTemplate;
	
	public ReplyDaoImpl(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	@Override
	public void insertReply(ReplyVo vo) throws Exception {
		sqlSessionTemplate.insert("insertReply", vo);
	}

	@Override
	public void updateReply(Map<String, Object> hs) throws Exception {
		sqlSessionTemplate.update("updateReply", hs);
	}

	@Override
	public void deleteReply(int num) throws Exception {
		sqlSessionTemplate.delete("deleteReply", num);
	}

	@Override
	public List<ReplyVo> replyList(int bNum) throws Exception {
		return sqlSessionTemplate.selectList("replyList", bNum);
	}

	@Override
	public List<Map<Object, Object>> countReply() throws Exception {
		return sqlSessionTemplate.selectList("countReply");
	}
}
