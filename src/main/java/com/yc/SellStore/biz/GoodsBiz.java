package com.yc.SellStore.biz;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.SellStore.bean.Goods;
import com.yc.SellStore.bean.GoodsExample;
import com.yc.SellStore.bean.Picture;
import com.yc.SellStore.bean.PictureExample;
import com.yc.SellStore.dao.GoodsMapper;
import com.yc.SellStore.dao.PictureMapper;

@Service
public class GoodsBiz {
	@Resource
	private GoodsMapper gm;
	@Resource
	private PictureMapper pm;

	public List<Goods> search(Goods goods) {
		GoodsExample ge = new GoodsExample();
		if(goods.getGoodsname()==null||goods.getGoodsname().trim().isEmpty()) {
		List<Goods> list =	gm.selectByExample(null);
		return list;
		}
		else {
			System.out.println(goods.getGoodsname());
		ge.createCriteria().andGoodsnameEqualTo(goods.getGoodsname());
		List<Goods> list =	gm.selectByExample(ge);
		return list;
		}
	}


	public String[] queryPicPath(int id) {
		PictureExample pe = new PictureExample();
		pe.createCriteria().andGoodsidEqualTo(id);
		List<Picture> list = pm.selectByExample(pe);
		String [] picPath = new String[list.size()];
		for(int i = 0;i<list.size();i++) {
			Picture p = list.get(i);
			picPath[i]=p.getPicture();
		}
		return picPath;
	}


	public List<Goods> selectById(int id) {
		GoodsExample ge = new GoodsExample();
		ge.createCriteria().andGoodsidEqualTo(id);
		List<Goods> list  = gm.selectByExample(ge);
		return list;
	}
	

}
