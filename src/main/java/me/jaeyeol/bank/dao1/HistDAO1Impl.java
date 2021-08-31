package me.jaeyeol.bank.dao1;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import me.jaeyeol.bank.dto.HistDTO;

@Repository
public class HistDAO1Impl implements IHistDAO1{

	@Autowired
//	@Qualifier(value = "db1SqlSessionTemplate")
	private SqlSession db1Session;
	private String namespace = "me.jaeyeol.bank.dao1.HistDAO1Impl";
	
	@Override
	public List<HistDTO> sltMulti(HashMap<String,Object> map) {

		return db1Session.selectList(namespace+".sltMulti",map);
	}

	@Override
	public void histInsert(HistDTO histDto) {
		db1Session.insert(namespace+".histInsert", histDto);
	}

	@Override
	public int getSerialNo() {
		return db1Session.selectOne(namespace+".getSerialNo");
	}

}
