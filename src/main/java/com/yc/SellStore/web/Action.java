package com.yc.SellStore.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Action {
 @GetMapping("/toblog")
 public String blog() {
	 System.err.println("进来了吧");
	 return "blog";
 }
}
