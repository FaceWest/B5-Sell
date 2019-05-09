package com.yc.SellStore.web;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.validation.Errors;

import com.yc.SellStore.bean.Admininfo;
import com.yc.SellStore.bean.Admininfo;
import com.yc.SellStore.biz.AdminBiz;
import com.yc.SellStore.biz.BizException;
import com.yc.SellStore.dao.AdmininfoMapper;

@Controller
@SessionAttributes("bkloginedClient")

public class BkLoginaction {
	@Resource
	private AdmininfoMapper aim;
	@Resource
	private AdminBiz abiz;
	
	@GetMapping("bkindex")
	public String home(){
		return "home";
	}
	
	
	@GetMapping("/bklogin")
	public String bklogin(@ModelAttribute("Admininfo") Admininfo ai) {
		return "bklogin";
	}
	

	@PostMapping("tobklogin")
	public String tobklogin(@ModelAttribute @Valid Admininfo ai,Errors errors,Model model,HttpServletRequest request) {
		if(errors.hasErrors()) {
			return "bklogin";
		}
		try {
			Admininfo dbci = abiz.bklogin(ai);
			model.addAttribute("bkloginedClient",dbci);
			System.out.println("登录成功");
			return "home";
		} catch (BizException e) {
			e.printStackTrace();
			model.addAttribute("msg",e.getMessage());
			return "bklogin";
		}
		
	}
	
	/*
	 * @GetMapping("/register") public String register(@ModelAttribute("Admininfo")
	 * Admininfo ai) { return"register"; }
	 * 
	 * @PostMapping("/toregister") public String
	 * toregister(@ModelAttribute("Admininfo") @Valid Admininfo ai,Errors
	 * errors,Model model) { if(errors.hasErrors()) { return "register"; }
	 * abiz.register(ai); model.addAttribute("msg","注册成功");
	 * 
	 * 
	 * return "home"; }
	 */
	
}
