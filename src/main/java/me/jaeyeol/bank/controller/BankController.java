package me.jaeyeol.bank.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import me.jaeyeol.bank.dto.AcctDTO;
import me.jaeyeol.bank.dto.HistDTO;
import me.jaeyeol.bank.service.IBankService;

@RestController
@RequestMapping(value = "/api", method = RequestMethod.POST)
public class BankController {

	@Autowired
	private IBankService bankSvc;

	@ResponseBody
	@PostMapping("/bankAccount")
	public String bankAccount(@RequestBody Map<String, Object> map) {

		String div = (String) map.get("div");
		AcctDTO acctDto = new AcctDTO();

		acctDto.setAcctNo((String) map.get("acctNo"));
		acctDto.setAcctPw((String) map.get("acctPw"));
		acctDto.setAcctBal(Long.parseLong(String.valueOf(map.get("amount"))));

		String msg = bankSvc.update(div, acctDto);

		return msg;
	}

	@PostMapping("/sltHistory")
	@ResponseBody
	public List<HistDTO> sltHistory(@RequestBody Map<String, Object> map) {

		AcctDTO acctDto = new AcctDTO();

		acctDto.setAcctNo((String) map.get("acctNo"));
		acctDto.setAcctPw((String) map.get("acctPw"));

		int page = Integer.parseInt(String.valueOf(map.get("pageNo")));

		List<HistDTO> histList = bankSvc.sltHistMulti(page, acctDto);

		return histList;
	}

	@PostMapping("/transfer")
	@ResponseBody
	public String transferProcess(@RequestBody Map<String, Object> map) {
		AcctDTO acctDto = new AcctDTO();

		acctDto.setAcctNo((String) map.get("acctNo"));
		acctDto.setAcctPw((String) map.get("acctPw"));
		acctDto.setAcctBal(Long.parseLong(String.valueOf(map.get("amount"))));
		String recvAcct = (String) map.get("recvAcct");

		String msg = bankSvc.accountTransfer(acctDto, recvAcct);

		return msg;
	}

}
