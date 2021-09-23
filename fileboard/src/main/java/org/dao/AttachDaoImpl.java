package org.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AttachDaoImpl implements AttachDao {
	public SqlSessionTemplate sqlSessionTemplate;
	
	public AttachDaoImpl(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	public int insert(Map<String, Object> map) {
		return sqlSessionTemplate.insert("insert", map);
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

	public void delete(Map<String, Object> hs) throws Exception {
		sqlSessionTemplate.delete("delete", hs);
	}

}
