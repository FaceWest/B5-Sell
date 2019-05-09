package com.yc.SellStore.biz;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class uploadBiz {

	public String upload(MultipartFile file) {
			if(file.isEmpty()) {
				return"文件为空，请重新上传";
			}
			//获取文件名
			String fileName = file.getOriginalFilename();
			String filePath = "/Users/longhongjiang/Downloads/myweb/";
			File dest = new File (filePath+fileName);
			try {
				file.transferTo(dest);
				System.out.println("上传成功");
				return fileName ;
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "上传失败";
	}

}
