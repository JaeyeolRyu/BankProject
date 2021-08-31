package me.jaeyeol.bank.dao2;

import java.util.HashMap;
import java.util.List;

import me.jaeyeol.bank.dto.HistDTO;

public interface IHistDAO2 {  //히스토리는 insert select 존재해야함. 거래내역을 임의로 지우는 행위 x
	List<HistDTO> sltMulti(HashMap<String,Object> map);
	void histInsert(HistDTO histDto);
	int getSerialNo();
	
}
