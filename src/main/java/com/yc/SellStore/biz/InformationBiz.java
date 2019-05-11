package com.yc.SellStore.biz;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.SellStore.bean.Address;
import com.yc.SellStore.bean.AddressExample;
import com.yc.SellStore.bean.Clientinfo;
import com.yc.SellStore.bean.ClientinfoExample;
import com.yc.SellStore.dao.AddressMapper;
import com.yc.SellStore.dao.ClientinfoMapper;
@Service
public class InformationBiz {
	@Resource
	ClientinfoMapper cim;
	@Resource
	AddressMapper am;
	
	public Clientinfo queryId(Clientinfo ci) {
		ClientinfoExample cie = new ClientinfoExample();
		cie.createCriteria().andClientidEqualTo(ci.getClientid());
		List<Clientinfo> list = cim.selectByExample(cie);
		
		
		return list.get(0);
	}
	public Address queryAddress(int id) {
		AddressExample ae = new AddressExample();
		ae.createCriteria().andClientidEqualTo(id);
		List<Address> list = am.selectByExample(ae);
		return list.get(0);
	}
	public void ChangeInfo(Clientinfo client) {
		cim.updateByPrimaryKeySelective(client);
	}
	public void ChangeAddress(Address address) {
		AddressExample ae = new AddressExample();
		ae.createCriteria().andClientidEqualTo(address.getClientid());
		am.updateByExampleSelective(address, ae);
		
	}

}
