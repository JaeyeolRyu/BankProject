package me.jaeyeol.bank.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import me.jaeyeol.bank.dao1.IAcctDAO1;
import me.jaeyeol.bank.dto.AcctDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:mappers/xml/*.xml"})
class IAcctDAO1Test {
	
	@Autowired
	IAcctDAO1 acctDao;

	@Test
	@DisplayName("계좌 조회")
	void testSltOneAcct() {
		String acct = "1024-1024";
		System.out.println("getPw" + acctDao.sltOneAcct(acct).getAcctPw());
		
	}

	@Test
	@Commit
	@DisplayName("입금 출금")
	void testUpdateBalance() {
		
		AcctDTO acctDto = new AcctDTO();
		acctDto.setAcctNo("1111-1111");
		acctDto.setAcctPw("1111");
		acctDto.setAcctBal(3000);
		
		acctDao.updateBalance(acctDto);
		
	}

}
