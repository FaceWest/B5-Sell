package com.yc.SellStore.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yc.SellStore.bean.Clientinfo;

@Controller
public class SkipAction {
 @GetMapping("/toblog")
 public String blog() {
	 System.err.println("进来了吧");
	 return "blog";
 }
 @GetMapping("/dashboard")
 public String dashboard(HttpServletRequest request,Model model) {
	 HttpSession session= request.getSession();
	 Clientinfo ci=(Clientinfo) session.getAttribute("loginClient");
	 System.out.println(ci);
	 model.addAttribute("userinfo",ci);
	 return"dashboard";
 }

 
 @GetMapping("/category")
 public String category() {
	 return"category";
 }
 
 
}
