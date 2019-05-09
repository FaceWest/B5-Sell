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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.validation.Errors;
import com.yc.SellStore.bean.Clientinfo;
import com.yc.SellStore.biz.BizException;
import com.yc.SellStore.biz.ClientBiz;
import com.yc.SellStore.dao.ClientinfoMapper;

@Controller
@SessionAttributes("loginedClient")
public class Loginaction {
	@Resource
	private ClientinfoMapper cim;
	@Resource
	private ClientBiz cbiz;
	
	@GetMapping("index")
	public String home(){
		return "home";
	}
	
	
	@GetMapping("/login")
	public String login(@ModelAttribute("Clientinfo") Clientinfo ci) {
		return "login";
	}
	

	@PostMapping("tologin")
	public String tologin(@ModelAttribute @Valid Clientinfo ci,Errors errors,Model model,HttpServletRequest request) {
		if(errors.hasErrors()) {
			return "login";
		}
		try {
			Clientinfo dbci = cbiz.login(ci);
			model.addAttribute("loginedClient",dbci);
			System.out.println("登录成功");
			return "home";
		} catch (BizException e) {
			e.printStackTrace();
			model.addAttribute("msg",e.getMessage());
			return "login";
		}
		
	}
	
	@GetMapping("/register")
	public String register(@ModelAttribute("Clientinfo") Clientinfo ci) {
		return"register";
	}
	@PostMapping("/toregister")
	public String toregister(@ModelAttribute("Clientinfo") @Valid Clientinfo ci,Errors errors,Model model) {
		if(errors.hasErrors()) {
			return "register";
		}
		cbiz.register(ci);
		model.addAttribute("msg","注册成功");
		
		
		return "home";
	}
	
}
