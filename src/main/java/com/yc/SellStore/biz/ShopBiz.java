package com.yc.SellStore.biz;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.SellStore.bean.Collect;
import com.yc.SellStore.bean.CollectExample;
import com.yc.SellStore.bean.Goods;
import com.yc.SellStore.bean.GoodsExample;
import com.yc.SellStore.bean.Orderlist;
import com.yc.SellStore.bean.OrderlistExample;
import com.yc.SellStore.bean.Shopcar;
import com.yc.SellStore.bean.ShopcarExample;
import com.yc.SellStore.dao.CollectMapper;
import com.yc.SellStore.dao.GoodsMapper;
import com.yc.SellStore.dao.OrderlistMapper;
import com.yc.SellStore.dao.ShopcarMapper;
@Service
public class ShopBiz
{
	@Resource
	private OrderlistMapper om;
	@Resource
	private ShopcarMapper sm;
	@Resource
	private CollectMapper cm;
	@Resource
	private GoodsMapper gm;
	
	
	public void addShopcar(Shopcar car) {
		sm.insertSelective(car);
	}

	public void addMyFavorite(Collect collect) {
		cm.insertSelective(collect);
	}

	public void BuyNow(Orderlist list) {
		om.insertSelective(list);
	}
	
	public List<Orderlist> query(int clientid){
		OrderlistExample oe = new OrderlistExample();
		oe.createCriteria().andClientidEqualTo(clientid);
		List<Orderlist> list = om.selectByExample(oe);
		return list;
	}

	public Goods queryGoods(Integer integer) {
		GoodsExample ge = new GoodsExample();
		ge.createCriteria().andGoodsidEqualTo(integer);
		List<Goods> list = gm.selectByExample(ge);
		return list.get(0);
		}
	
	
	public List<Collect> querycollect(Integer clientid) {
		CollectExample ce = new CollectExample();
		ce.createCriteria().andClientidEqualTo(clientid);
		List<Collect> list  = cm.selectByExample(ce);
		
		
		return list;
	}

	public List<Shopcar> queryshopcar(int clientid) {
		ShopcarExample se = new ShopcarExample();
		se.createCriteria().andClientidEqualTo(clientid);
		List<Shopcar> list = sm.selectByExample(se);
		return list;
	}
}

