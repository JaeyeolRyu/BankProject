package me.jaeyeol.bank.dao1;

import me.jaeyeol.bank.dto.AcctDTO;

public interface IAcctDAO1 { //잔고 table 관련. 
	
	AcctDTO sltOneAcct(String acctNo); // 잔고확인, 계좌유무등 모두 처리
	void updateBalance(AcctDTO acctDto); // 계좌가 존재할 시 update로 처리해야함.
	
}

/* 
 *필요사항
	
	입금 (본인 계좌이므로 유효성 체크 필요 X)
	출금 (마이너스 통장가능 여부 선택(?), 계좌에 돈 여부 체크해야함)
	계좌이체 (보낼계좌가 유효한지 유효성 체크 필요, 여유가 되면 일일 한도 500만원 잡고 체크할 것(매일 초기화 해야하는 상황생김 구현 힘들듯.))
	잔액조회
	
	BankDTO는 아이디와 잔고만 가지고 있음 Service단에서 잔고, 입금 계좌 존재 유무등을 처리해줘야함.

	출금시 잔고 보유 여부 확인 위한 select 필요 & 계좌이체시 상대계좌 유무 체크 위한 slt 필요 => 하나로 처리가능 

*/

// 만약 과제를 처리함이 계좌가 다 존재한다는 상태에서 시작을 한다면... insert는 필요가 없음. 계좌가 존재하지 않는 상태에서 시작한다면 필요. 
