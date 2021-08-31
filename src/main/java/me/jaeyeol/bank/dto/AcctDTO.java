package me.jaeyeol.bank.dto;

public class AcctDTO { // 잔고 table 
	
	private String acctNo;
	private String acctPw;
	private long acctBal;
	
	public String getAcctNo() {
		return acctNo;
	}
	
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}
	
	public String getAcctPw() {
		return acctPw;
	}
	
	public void setAcctPw(String acctPw) {
		this.acctPw = acctPw;
	}
	
	public long getAcctBal() {
		return acctBal;
	}
	
	public void setAcctBal(long acctBal) {
		this.acctBal = acctBal;
	}
	
}
