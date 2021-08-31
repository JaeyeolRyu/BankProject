package me.jaeyeol.bank.service;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import me.jaeyeol.bank.dto.AcctDTO;
import me.jaeyeol.bank.dto.HistDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class BankServiceImplTest {

	@Autowired
	IBankService bankSvc;

	@Test
	@Commit
	@DisplayName("입출금")
	void testUpdate() {
		
		AcctDTO acctDto = new AcctDTO();
		
		acctDto.setAcctNo("1111-1111");
		acctDto.setAcctPw("1111");
		acctDto.setAcctBal(3000);
		
		String str = bankSvc.update("deposit", acctDto);
		System.out.println(str);
	}
	
	@Test
	@DisplayName("거래내역 다건 조회")
	void testSltHistMulti() {

		AcctDTO acctDto = new AcctDTO();
		
		acctDto.setAcctNo("1111-1111");
		acctDto.setAcctPw("1111");
		
		List<HistDTO> list = bankSvc.sltHistMulti(1, acctDto);
	
		for(HistDTO histDto : list) {
			System.out.println( histDto.toString());
		}
	
	}
	
	@Test
	@Commit
	@DisplayName("계좌이체")
	void testTransfer() {
		
		AcctDTO acctDto = new AcctDTO();
		
		acctDto.setAcctNo("1024-1024");
		acctDto.setAcctPw("0127");
		acctDto.setAcctBal(7700);
		String recvAcct = ("1111-1111");
		
		String str = bankSvc.accountTransfer(acctDto, recvAcct);
		System.out.println(str);
	}

//	@Test
//	@Commit
//	@DisplayName("DB값 변경되는지 test")
//	void testDbTest() {
//		
//		AcctDTO acctDto = new AcctDTO();
//		
//		acctDto.setAcctNo("1111-1111");
//		acctDto.setAcctPw("1111");
//		acctDto.setAcctBalace(7129830);
//		
//		bankSvc.dbTest(acctDto);
//		
//	}
	
//	@Test
//	@Commit
//	@DisplayName("리턴값이 DTO 가 아니면 받아와지는지")
//	void testTestOne() {
//		
//		String acctNo = "1111-1111";
//
//		String res = bankSvc.testOne(acctNo);
//		
//		System.out.println("계좌 비밀번호 : " + res);
//		
//	}
	
}
