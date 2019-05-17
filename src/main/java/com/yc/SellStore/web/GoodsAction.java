package com.yc.SellStore.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.yc.SellStore.bean.Goods;
import com.yc.SellStore.biz.GoodsBiz;
import com.yc.SellStore.dao.GoodsMapper;

@Controller
public class GoodsAction {
	@Resource
	private GoodsMapper gm;
	@Resource
	private GoodsBiz gbiz;
	@GetMapping("/search")
	public String search(Model model,@ModelAttribute("goodsinfo") Goods goods) {
		List<Goods> goodsList = gbiz.search(goods);
		System.out.println(goodsList);
		model.addAttribute("goodsList", goodsList);
		return "category";
	}

}
