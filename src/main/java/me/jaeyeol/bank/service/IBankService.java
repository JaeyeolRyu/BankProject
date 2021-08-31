package me.jaeyeol.bank.service;

import java.util.List;

import me.jaeyeol.bank.dto.AcctDTO;
import me.jaeyeol.bank.dto.HistDTO;

public interface IBankService {
	
	/* 있어야 할 메소드 입금 출금 조회.
	  
	  고려사항 입금 - acct로 처리 후 hist에 저장 (tx)
	  		  출금 - acct로 처리 후 hist에 저장 (tx)
	  		  이체 - acct로 처리 후 hist에 저장 (tx) 보내는 계좌는 비번확인, 받는계좌는 계좌유무만 확인.
	  		  조회 - 거래내역이 많을 시 다보여주는거 말안됌 적당히 끊어서 보여줄 것. hist로 처리.
	
	*/
	
	List<HistDTO> sltHistMulti(int pageNo, AcctDTO acctDto);
	
	String update(String div , AcctDTO acctDto); //입금, 출금
	
	String accountTransfer(AcctDTO acctDto, String recvAcctNo);

}
