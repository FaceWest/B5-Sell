package com.yc.SellStore.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.yc.SellStore.bean.Clientinfo;
import com.yc.SellStore.bean.Collect;
import com.yc.SellStore.bean.Goods;
import com.yc.SellStore.bean.GoodsE;
import com.yc.SellStore.bean.Orderlist;
import com.yc.SellStore.bean.Shopcar;
import com.yc.SellStore.biz.ShopBiz;

@Controller
public class DashBoardAction {
	@Resource
	private ShopBiz sbiz;
	@GetMapping("/dashboard")
	 public String dashboard(HttpServletRequest request,Model model,@SessionAttribute("loginedClient") Clientinfo loginedClient) {
		int clientid = loginedClient.getClientid();
		List<Orderlist> list = sbiz.query(clientid);
		Integer[]goodsid = new Integer[list.size()];
		Integer[]count = new Integer[list.size()];
		Date[] date = new Date[list.size()];
		String[] orderId = new String[list.size()];
		String[] status = new String[list.size()];
		
		for(int i=0 ;i<list.size();i++) {
			Orderlist order = list.get(i);
			goodsid[i]=order.getGoodsid();
			count[i]=order.getCount();
			date[i]=order.getOrdertime();
			orderId[i] =order.getOrderid();
			status[i]= order.getStatus();
		}
		if(goodsid.length==0){
			return"dashboard";
		}else {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<GoodsE> goodslist = new ArrayList<GoodsE>();		
		for(int i =0;i<goodsid.length;i++) {
			Goods goods  =sbiz.queryGoods(goodsid[i]);
			GoodsE goodsE = new GoodsE();
			goodsE.setPic(goods.getPic());
			goodsE.setGoodsname(goods.getGoodsname());
			goodsE.setOrdertime(df.format(date[i]));
			goodsE.setOrderId(orderId[i]);
			goodsE.setCount(count[i]);
			goodsE.setStatus(status[i]);
			goodslist.add(goodsE);
		}
		System.out.println(goodslist);
		model.addAttribute("goodslist",goodslist);
		 return"dashboard";
		}
	 }
	
	
	
	@GetMapping("dashboard-favourite")
	public String favourite(HttpServletRequest request,Model model,@SessionAttribute("loginedClient") Clientinfo loginedClient) {
		int clientid = loginedClient.getClientid();
		List<Collect> list = sbiz.querycollect(clientid);
		Integer[]goodsid = new Integer[list.size()];
		Date[] date = new Date[list.size()];
		
		for(int i=0 ;i<list.size();i++) {
			Collect collect = list.get(i);
			goodsid[i]=collect.getGoodsid();
			date[i]=collect.getOrdertime();
		}
		if(goodsid.length==0){
			return"dashboard-favourite";
		}else {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<GoodsE> goodslist = new ArrayList<GoodsE>();		
		for(int i =0;i<goodsid.length;i++) {
			Goods goods  =sbiz.queryGoods(goodsid[i]);
			GoodsE goodsE = new GoodsE();
			goodsE.setPic(goods.getPic());
			goodsE.setGoodsname(goods.getGoodsname());
			goodsE.setOrdertime(df.format(date[i]));
			goodslist.add(goodsE);
		}
		System.out.println(goodslist);
		model.addAttribute("goodslist",goodslist);
		return "dashboard-favourite";
	}
	}
	
	
	
	@GetMapping("dashboard-shopcar")
	public  String shopcar(HttpServletRequest request,Model model,@SessionAttribute("loginedClient") Clientinfo loginedClient) {
		int clientid = loginedClient.getClientid();
		List<Shopcar> list = sbiz.queryshopcar(clientid);
		Integer[]goodsid = new Integer[list.size()];
		Date[] date = new Date[list.size()];
		Integer count[] = new Integer[list.size()];
		for(int i=0 ;i<list.size();i++) {
			Shopcar car = list.get(i);
			goodsid[i]=car.getGoodsid();
			date[i]=car.getOrdertime();
			count[i]= car.getCount();
		}
		if(goodsid.length==0){
			return"dashboard-shopcar";
		}else {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<GoodsE> goodslist = new ArrayList<GoodsE>();		
		for(int i =0;i<goodsid.length;i++) {
			Goods goods  =sbiz.queryGoods(goodsid[i]);
			GoodsE goodsE = new GoodsE();
			goodsE.setPic(goods.getPic());
			goodsE.setGoodsname(goods.getGoodsname());
			goodsE.setOrdertime(df.format(date[i]));
			goodsE.setCount(count[i]);
			goodslist.add(goodsE);
		}
		System.out.println(goodslist);
		model.addAttribute("goodslist",goodslist);
		return "dashboard-shopcar";
	}
	
}
}
