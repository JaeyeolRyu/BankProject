package me.jaeyeol.bank.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.jaeyeol.bank.dao1.IAcctDAO1;
import me.jaeyeol.bank.dao1.IHistDAO1;
import me.jaeyeol.bank.dao2.IAcctDAO2;
import me.jaeyeol.bank.dao2.IHistDAO2;
import me.jaeyeol.bank.dto.AcctDTO;
import me.jaeyeol.bank.dto.HistDTO;

@Service("bankSvc")
public class BankServiceImpl implements IBankService {

	@Autowired
	IAcctDAO1 acctDao1;
	@Autowired
	IAcctDAO2 acctDao2;
	@Autowired
	IHistDAO1 histDao1;
	@Autowired
	IHistDAO2 histDao2;

	static final int MAXCNT = 10;

	@Override
	public List<HistDTO> sltHistMulti(int pageNo, AcctDTO acctDto) {
		// null 처리 어떻게 할지 정해야함. and DealNo 처리 (실제로는 보여줄 필요 없긴함.)
		AcctDTO checkDto = acctDao1.sltOneAcct(acctDto.getAcctNo());

		if (checkDto == null) {
			return null;// 에러 : 해당 계좌가 없습니다.
		} else if (!checkDto.getAcctPw().equals(acctDto.getAcctPw())) {
			return null;// 에러 : 비밀번호가 맞지 않습니다.
		} else {

			List<HistDTO> histList = new ArrayList<HistDTO>();
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("value", acctDto.getAcctNo());
			map.put("start", (pageNo - 1) * MAXCNT + 1);
			map.put("cnt", MAXCNT + 1);
			histList = histDao1.sltMulti(map);

			if (histList.size() > MAXCNT) { // 웹으로 만들어서 페이징 처리하려면 필요.
				histList.remove(MAXCNT);
			}

			return histList;
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public String update(String div, AcctDTO acctDto) {

		// 거래
		String sendAcct = "";
		String recvAcct = "";
		String acctNo = acctDto.getAcctNo();
		long amount = acctDto.getAcctBal();

		if ("withdraw".equals(div)) { // 출금일 경우 *(-1) 해준다.
			amount *= (-1);
			sendAcct = acctDto.getAcctNo();
		} else if ("deposit".equals(div)) {
			recvAcct = acctDto.getAcctNo();
		} else {
			return "에러 : 거래유형 오류";
		}
		System.out.println("-------------------------------------------------");
		System.out.println(acctNo);
		System.out.println("-------------------------------------------------");

		// 계좌정보를 받아온다.
		AcctDTO dto = acctDao1.sltOneAcct(acctNo);

		if (dto == null) {
			return "에러 : 해당계좌가 존재하지 않습니다.";
		} else if (!dto.getAcctPw().equals(acctDto.getAcctPw())) {
			return "에러 : 계좌 비밀번호가 올바르지 않습니다.";
		} else if (dto.getAcctBal() + amount < 0) {
			return "에러 : 잔액이 부족합니다.";
		} else {

			long balance = dto.getAcctBal() + amount;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			Timestamp dealTime = Timestamp.valueOf(sdf.format(date));
			dto.setAcctBal(balance);
			acctDao1.updateBalance(dto);
			
			HistDTO histDto = new HistDTO();
			
			int dealNo = histDao1.getSerialNo();
			// 보내는 계좌 받는계좌에 따라 DTO 셋팅 값이 달라야함.
			histDto.setdealNo(dealNo);
			histDto.setsendAcct(sendAcct);
			histDto.setdealType(div);
			histDto.setrecvAcct(recvAcct);
			histDto.setDate(dealTime);
			histDto.setAmount(amount);
			histDto.setBalance(balance);

			histDao1.histInsert(histDto);

		}

		return "정상 처리 되었습니다";
	}

	@Transactional
	@Override
	public String accountTransfer(AcctDTO acctDto, String recvAcctNo) {
		/*
		 * 계좌이체에 필요한 것. 내계좌 내비번 상대계좌 금액 1. 계좌번호 유무확인해야함 2. 확인되면 상대계좌가 존재하는지 확인해야함 3. 있다면
		 * 거래진행 history시간 잡아야함.
		 */

		String sendAcctNo = acctDto.getAcctNo();
		long sendAmount = acctDto.getAcctBal();

		AcctDTO sendDto = acctDao1.sltOneAcct(sendAcctNo);

		if (sendDto == null) {
			return "에러 : 해당계좌가 존재하지 않습니다.";
		} else if (!sendDto.getAcctPw().equals(acctDto.getAcctPw())) {
			return "에러 : 계좌 비밀번호가 올바르지 않습니다.";
		} else if (sendDto.getAcctBal() - sendAmount < 0) {
			return "에러 : 잔액이 부족합니다.";
		} else {

			AcctDTO recvDto = acctDao2.sltOneAcct(recvAcctNo);

			if (recvDto == null) {
				return "에러 : 계좌번호가 올바르지 않습니다.";
			} else {
				long sendAcctBal = sendDto.getAcctBal() - sendAmount;
				long recvAcctBal = recvDto.getAcctBal() + sendAmount;
				sendDto.setAcctBal(sendAcctBal);
				recvDto.setAcctBal(recvAcctBal);
//
//				Date date = new Date();
//				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				String dealTime = sdf.format(date);//

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = new Date();
				Timestamp dealTime = Timestamp.valueOf(sdf.format(date));
				
				
				int sendDealNo = histDao1.getSerialNo();
				int recvDealNo = histDao1.getSerialNo();

				acctDao1.updateBalance(sendDto);
				acctDao2.updateBalance(recvDto);

				HistDTO histDto = new HistDTO();

				// 보내는 계좌 내역
				histDto.setdealNo(sendDealNo);
				histDto.setsendAcct(sendAcctNo);
				histDto.setrecvAcct(recvAcctNo);
				histDto.setDate(dealTime);
				histDto.setAmount(sendAmount);
				histDto.setBalance(sendAcctBal);
				histDto.setdealType("withdraw");
				histDao1.histInsert(histDto);

				// 받는 계좌 내역
				histDto.setdealNo(recvDealNo);
				histDto.setrecvAcct(sendAcctNo);
				histDto.setsendAcct(recvAcctNo);
				histDto.setBalance(recvAcctBal);
				histDto.setdealType("deposit");
				histDao2.histInsert(histDto);
			}

		}

		return "거래가 정상적으로 완료 되었습니다";
	}

}
