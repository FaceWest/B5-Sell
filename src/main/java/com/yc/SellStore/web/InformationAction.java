package com.yc.SellStore.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.yc.SellStore.bean.Address;
import com.yc.SellStore.bean.Clientinfo;
import com.yc.SellStore.biz.InformationBiz;
import com.yc.SellStore.biz.uploadBiz;
import com.yc.SellStore.dao.AddressMapper;
import com.yc.SellStore.dao.ClientinfoMapper;
@Controller
public class InformationAction {
	@Resource
	ClientinfoMapper cm;
	@Resource
	AddressMapper am;
	@Resource
	InformationBiz ibiz;
	@Resource
	uploadBiz upload;
	
	@GetMapping("/user-profile")
	 public String userProfile(HttpServletRequest request,Model model , @ModelAttribute ("clientinfo")Clientinfo client) {
		Clientinfo ci = (Clientinfo) request.getSession().getAttribute("loginedClient");
		Clientinfo dbci = ibiz.queryId(ci);
		int id = dbci.getClientid();
		Address address = ibiz.queryAddress(id);
		client.setAddress(address.getAddress());
		model.addAttribute("address",address);
		 return "user-profile";
	 }
	@PostMapping("/editinfo")
	public String changeinformation(HttpServletRequest request,Model model , @ModelAttribute ("clientinfo")Clientinfo client
			,@RequestParam ("file")MultipartFile file) {
		System.out.println("进来了");
		
		String headpic = upload.upload(file);
		if(headpic.equals("文件为空，请重新上传")==false) {
			client.setHeadpic(headpic);
			ibiz.ChangeInfo(client);
			Address address = new Address();
			address.setAddress(client.getAddress());
			address.setClientid(client.getClientid());
			ibiz.ChangeAddress(address);
			request.getSession().setAttribute("loginedClient", client);
			String message = "修改成功";
			model.addAttribute("message",message);
			return "user-profile";
		}
		if(headpic.equals("上传失败")) {
			String message="服务器错误，修改失败";
			model.addAttribute("message",message);
			return "user-profile";
		}
		else {
		ibiz.ChangeInfo(client);
		Address address = new Address();
		address.setAddress(client.getAddress());
		address.setClientid(client.getClientid());
		ibiz.ChangeAddress(address);
		request.getSession().setAttribute("loginedClient", client);
		String message = "修改成功";
		model.addAttribute("message",message);
		return "user-profile";
		}
	}
	
	
	
	
}
