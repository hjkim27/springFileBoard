package org.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.vo.AttachVo;

public class AttachDaoImpl implements AttachDao {
	public SqlSessionTemplate sqlSessionTemplate;
	
	public AttachDaoImpl(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	public int insert(AttachVo vo) {
		return sqlSessionTemplate.insert("insert", vo);
	}

	public void update(AttachVo vo) {
		sqlSessionTemplate.update("update", vo);
	}

	public AttachVo files(int bNum) {
		return sqlSessionTemplate.selectOne("files", bNum);
	}

	public int countFile(int bNum) {
		List<Integer> result =  sqlSessionTemplate.selectList("countFile", bNum);
		return result.isEmpty()? null: result.get(0);
	}

	public Map<Object, Object> countFileList() {
		return sqlSessionTemplate.selectMap("countFileList", "bNum", "filecount");
	}

}
