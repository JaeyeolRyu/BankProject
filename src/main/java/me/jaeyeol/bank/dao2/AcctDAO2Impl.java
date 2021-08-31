package me.jaeyeol.bank.dao2;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import me.jaeyeol.bank.dto.AcctDTO;

@Repository
public class AcctDAO2Impl implements IAcctDAO2 {

	@Autowired
	@Qualifier(value = "db2SqlSessionTemplate")
	private SqlSession db2Session;
	private String namespace = "me.jaeyeol.bank.dao2.AcctDAO2Impl";

	@Override
	public AcctDTO sltOneAcct(String acctNo) {
		return db2Session.selectOne(namespace + ".sltOneAcct", acctNo);
	}

	@Override
	public void updateBalance(AcctDTO acctDto) {
		db2Session.update(namespace + ".updateBalance", acctDto);
	}

}
