package me.jaeyeol.bank.dao2;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import me.jaeyeol.bank.dto.HistDTO;

@Repository
public class HistDAO2Impl implements IHistDAO2{

	@Autowired
	@Qualifier(value = "db2SqlSessionTemplate")
	private SqlSession db2Session;
	private String namespace = "me.jaeyeol.bank.dao2.HistDAO2Impl";
	
	@Override
	public List<HistDTO> sltMulti(HashMap<String,Object> map) {

		return db2Session.selectList(namespace+".sltMulti",map);
	}

	@Override
	public void histInsert(HistDTO histDto) {
		db2Session.insert(namespace+".histInsert", histDto);
	}

	@Override
	public int getSerialNo() {
		return db2Session.selectOne(namespace+".getSerialNo");
	}


}
