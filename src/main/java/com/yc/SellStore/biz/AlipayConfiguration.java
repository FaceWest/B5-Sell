package com.yc.SellStore.biz;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.demo.trade.service.AlipayTradeService;
import com.alipay.demo.trade.service.impl.AlipayTradeServiceImpl;

@Configuration
@EnableConfigurationProperties(AlipayProperties.class)
public class AlipayConfiguration {

    private AlipayProperties properties;

    public AlipayConfiguration(AlipayProperties properties) {
        this.properties = properties;
    }

    @Bean
    public AlipayTradeService alipayTradeService() {
        return new AlipayTradeServiceImpl.ClientBuilder()
                .setGatewayUrl(properties.getGatewayUrl())
                .setAppid(properties.getAppid())
                .setPrivateKey(properties.getAppPrivateKey())
                .setAlipayPublicKey(properties.getAlipayPublicKey())
                .setSignType(properties.getSignType())
                .build();
    }
    /**
	 * alipay-sdk-java
	 * @return
	 */
	@Bean
	public AlipayClient alipayClient(){
		return new DefaultAlipayClient(properties.getGatewayUrl(),
				properties.getAppid(),
				properties.getAppPrivateKey(),
				properties.getFormate(),
				properties.getCharset(),
				properties.getAlipayPublicKey(),
				properties.getSignType());
	}

}