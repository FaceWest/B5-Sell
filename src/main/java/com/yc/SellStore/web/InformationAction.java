package com.yc.SellStore.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.yc.SellStore.bean.Clientinfo;
@Controller
public class InformationAction {
	@GetMapping("/user-profile")
	 public String userProfile(HttpServletRequest request,Model model , @ModelAttribute ("clientinfo")Clientinfo client) {
		 return "user-profile";
	 }
	
	
}
