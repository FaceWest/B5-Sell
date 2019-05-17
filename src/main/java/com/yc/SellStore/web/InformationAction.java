package com.yc.SellStore.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.yc.SellStore.bean.Address;
import com.yc.SellStore.bean.Clientinfo;
import com.yc.SellStore.biz.AliyunMessageUtil;
import com.yc.SellStore.biz.InformationBiz;
import com.yc.SellStore.biz.uploadBiz;
import com.yc.SellStore.dao.AddressMapper;
import com.yc.SellStore.dao.ClientinfoMapper;
@Controller
@SessionAttributes("code")

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
	
	@PostMapping("/changePwd")
	public String changePwd(HttpServletRequest request,@ModelAttribute ("clientinfo")Clientinfo client,@RequestParam("Oldpwd")String Oldpwd,
			@RequestParam("loginedPwd") String loginedPwd ,@RequestParam() String repwd,@RequestParam("pwd")String pwd,
			@RequestParam("clientid") int clientid,Model model) {
		if(!Oldpwd.equals(loginedPwd)) {
			String message = "原密码错误，请重新输入";
			model.addAttribute("message",message);
			return "user-profile";
		}else if(!repwd.equals(pwd)) {
			String message = "两次密码不正确，请重新输入";
			model.addAttribute("message",message);
			return "user-profile";
		}else {
			client.setPwd(pwd);
			client.setClientid(clientid);
			System.out.println(client);
			ibiz.changePwd(client);
			String message = "修改成功";
			model.addAttribute("message",message);
			return "user-profile";

		}
	}
	
	
	
	@PostMapping("/changePhone")
	public String changePhone(@ModelAttribute ("clientinfo")Clientinfo client,HttpServletRequest request,Model model
			,@RequestParam("Oldphone") String Oldphone,@RequestParam("clientid") int clientid,@RequestParam("loginedphone") String loginedphone
			,@RequestParam("userCode") String userCode,@SessionAttribute("code") String code) {
			if(!Oldphone.equals(loginedphone)) {
				String message = "旧手机号码错误，请重新输入！";
				model.addAttribute("message",message);
				return "user-profile";
			}else if(!userCode.equals(code)||userCode.trim().isEmpty()){
				String message ="验证码输入错误，请重新输入！";
				model.addAttribute("message",message);
				return "user-profile";
			}else {
				client.setClientid(clientid);
				ibiz.changePhone(client);
				String message = "修改成功";
				model.addAttribute("message",message);
				return "user-profile";
			}
	}
	
	@PostMapping("/sendMsg")
	@ResponseBody
	public Map<String,Object> sendMsg(@RequestBody Map<String,Object> requestMap,Model model) throws ClientException {
		String phoneNumber=requestMap.get("phoneNumber").toString();
		String code = createRandomNum(6);
	    String jsonContent = "{\"code\":\"" + code + "\"}";
	    model.addAttribute("code", code);
	    
	    Map<String,String> paramMap = new HashMap<String,String>();
	    
	    paramMap.put("phoneNumber", phoneNumber);
	    paramMap.put("msgSign", "朝西FaceWest");
	    paramMap.put("templateCode", "SMS_165417925");
	    paramMap.put("jsonContent", jsonContent);
	    System.out.println(paramMap);
	    SendSmsResponse sendSmsResponse = AliyunMessageUtil.sendSms(paramMap);
	    System.out.println(sendSmsResponse.getCode());
	    if(!(sendSmsResponse.getCode()!=null&&sendSmsResponse.getCode().equals("OK"))) {
	    	
	    	
	    }

		return null;
	}
	public static String createRandomNum(int num){
	    String randomNumStr = "";
	    for(int i = 0; i < num;i ++){
	        int randomNum = (int)(Math.random() * 10);
	        randomNumStr += randomNum;
	    }
	    return randomNumStr;
	}
	
	
	
}
