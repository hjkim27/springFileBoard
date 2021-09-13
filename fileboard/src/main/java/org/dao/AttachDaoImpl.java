package org.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import org.vo.AttachVo;

@Repository
public class AttachDaoImpl implements AttachDao {
	public SqlSessionTemplate sqlSessionTemplate;
	
	public AttachDaoImpl(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	public int insert(AttachVo vo) {
		return sqlSessionTemplate.insert("insert", vo);
	}

	public List<Map<Object, Object>> countFileList() {
		return sqlSessionTemplate.selectList("countFileList");
	}

	public List<Map<Object, Object>> files(int bNum) {
		return sqlSessionTemplate.selectList("files", bNum);
	}

	public Map<String, Object> downFile(int num) throws Exception {
		return sqlSessionTemplate.selectOne("downFile", num);
	}

	public void delete(int num) {
		sqlSessionTemplate.delete("delete", num);
	}

	public void update(AttachVo vo) {
		sqlSessionTemplate.update("update", vo);
	}

}
