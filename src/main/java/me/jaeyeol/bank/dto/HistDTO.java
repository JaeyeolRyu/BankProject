package me.jaeyeol.bank.dto;

import java.sql.Timestamp;

public class HistDTO { // 거래내역 table
	
	private long dealNo;
	private String sendAcct;
	private String dealType;
	private String recvAcct;
	private Timestamp dealDate;
	private long amount;
	private long balance;
	
	public long getdealNo() {
		return dealNo;
	}
	public void setdealNo(int dealNo) {
		this.dealNo = dealNo;
	}
	public String getsendAcct() {
		return sendAcct;
	}
	public void setsendAcct(String sendAcct) {
		this.sendAcct = sendAcct;
	}
	public String getdealType() {
		return dealType;
	}
	public void setdealType(String dealType) {
		this.dealType = dealType;
	}
	public String getrecvAcct() {
		return recvAcct;
	}
	public void setrecvAcct(String recvAcct) {
		this.recvAcct = recvAcct;
	}
	public Timestamp getDate() {
		return dealDate;
	}
	public void setDate(Timestamp dealDate) {
		this.dealDate = dealDate;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return "HistDTO [보내는 계좌 = " + sendAcct + ", 거래 유형 = " + dealType + ", 받는 계좌 = " + recvAcct + ", 거래일시= "
				+ dealDate + ", 금액 = " + amount + "]";
	}
	
}
