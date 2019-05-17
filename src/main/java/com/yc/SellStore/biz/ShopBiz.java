package com.yc.SellStore.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.SellStore.bean.Collect;
import com.yc.SellStore.bean.Orderlist;
import com.yc.SellStore.bean.OrderlistExample;
import com.yc.SellStore.dao.CollectMapper;
import com.yc.SellStore.dao.OrderlistMapper;
@Service
public class ShopBiz
{
	@Resource
	private OrderlistMapper om;
	@Resource
	private CollectMapper cm;

	public void addShopcar(Orderlist list) {
		OrderlistExample oe = new OrderlistExample();
		oe.createCriteria().andClientidEqualTo(list.getClientid());
		om.insertSelective(list);
	}

	public void addMyFavorite(Collect collect) {
		cm.insertSelective(collect);
	}

	public void BuyNow(Orderlist list) {
		om.insertSelective(list);
	}
}

