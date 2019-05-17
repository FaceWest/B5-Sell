package com.yc.SellStore.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yc.SellStore.bean.Goods;
import com.yc.SellStore.biz.GoodsBiz;

@Controller
public class SingAction {
	@Resource
	private GoodsBiz gb;
	@GetMapping("/single")
	public String show(Model model,@RequestParam("id") int id) {
		List<Goods> list = gb.selectById(id);
		System.out.println(list);
		model.addAttribute("goodsinfo",list);
		return "single";
	}

}
