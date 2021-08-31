package me.jaeyeol.bank.dao1;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import me.jaeyeol.bank.dto.AcctDTO;

@Repository
public class AcctDAO1Impl implements IAcctDAO1 {

	@Autowired
//	@Qualifier(value = "db1SqlSessionTemplate")
	private SqlSession db1Session;
	private String namespace = "me.jaeyeol.bank.dao1.AcctDAO1Impl";

	@Override
	public AcctDTO sltOneAcct(String acctNo) {
		return db1Session.selectOne(namespace + ".sltOneAcct", acctNo);
	}

	@Override
	public void updateBalance(AcctDTO acctDto) {
		db1Session.update(namespace + ".updateBalance", acctDto);
	}

}
