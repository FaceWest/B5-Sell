package com.yc.SellStore.biz;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.SellStore.bean.Admininfo;
import com.yc.SellStore.bean.AdmininfoExample;
import com.yc.SellStore.dao.AdmininfoMapper;

@Service
@Transactional(rollbackFor= BizException.class)
public class AdminBiz {

	@Resource
	private AdmininfoMapper aim;
	
	public Admininfo bklogin(Admininfo ci) throws BizException{
		AdmininfoExample cie = new AdmininfoExample();
		cie.createCriteria().andAdminNameLike(ci.getAdminName()).andAdminPwdLike(ci.getAdminPwd());
		List<Admininfo> list=aim.selectByExample(cie);
		if(list.size()==0) {
			throw new BizException("用户名或密码错误");
		}
		return list.get(0);
	}

	public void register(@Valid Admininfo ai) {
		// TODO Auto-generated method stub
		aim.insert(ai);
	}


	
}
