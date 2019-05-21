package com.yc.SellStore.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yc.SellStore.bean.Collect;
import com.yc.SellStore.bean.Orderlist;
import com.yc.SellStore.bean.Shopcar;
import com.yc.SellStore.biz.ShopBiz;
import com.yc.SellStore.dao.OrderlistMapper;

@Controller
public class AjaxAction {
	@Resource
	private OrderlistMapper om;
	@Resource
	private ShopBiz shopbiz;
	@PostMapping("/addShopcar")
	@ResponseBody
	public String addShopcar(@RequestParam("clientid") Integer clientid,@RequestParam("goodsid") Integer goodsid,@RequestParam("count") Integer count) {
		System.out.println(clientid);
		Shopcar car = new Shopcar();
		car.setClientid(clientid);
		car.setGoodsid(goodsid);
		car.setCount(count);
		car.setOrdertime(new Date());
		shopbiz.addShopcar(car);
		return "成功添加至购物车";
	}
	
	@PostMapping("/addMyFavorite")
	@ResponseBody
	public String addMyFavorite(@RequestParam("clientid") Integer clientid,@RequestParam("goodsid") Integer goodsid) {
		Collect collect = new Collect();
		collect.setClientid(clientid);
		collect.setGoodsid(goodsid);
		collect.setOrdertime(new Date());
		shopbiz.addMyFavorite(collect);
		String message = "成功添加至我的收藏";
		return message;
	}
	@PostMapping("/BuyNow")
	@ResponseBody
	public String BuyNow(@RequestParam("clientid") Integer clientid,@RequestParam("goodsid") Integer goodsid,@RequestParam("count") Integer count) {
		Orderlist list = new Orderlist();
		list.setClientid(clientid);
		list.setGoodsid(goodsid);
		list.setStatus("未支付");
		list.setCount(count);
		shopbiz.BuyNow(list);
		return"";
	}
}
