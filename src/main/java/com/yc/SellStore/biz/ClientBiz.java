package com.yc.SellStore.biz;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.SellStore.bean.Clientinfo;
import com.yc.SellStore.bean.ClientinfoExample;
import com.yc.SellStore.dao.ClientinfoMapper;
import com.yc.SellStore.biz.BizException;

@Service
@Transactional(rollbackFor= BizException.class)
public class ClientBiz {
@Resource
private ClientinfoMapper cim;


public Clientinfo login(Clientinfo ci) throws BizException{
	ClientinfoExample cie = new ClientinfoExample();
	cie.createCriteria().andClientnameLike(ci.getClientname()).andPwdLike(ci.getPwd());
	List<Clientinfo> list=cim.selectByExample(cie);
	if(list.size()==0) {
		throw new BizException("用户名或密码错误");
	}
	return list.get(0);
}


}
