package com.yc.SellStore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//Mabatis 接口扫描
@MapperScan("com.yc.SellStore.dao")
public class SellStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(SellStoreApplication.class, args);
	}

}
