package com.yc.SellStore.web;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.demo.trade.model.GoodsDetail;
import com.alipay.demo.trade.model.builder.AlipayTradePrecreateRequestBuilder;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.service.AlipayTradeService;
import com.yc.SellStore.biz.AlipayProperties;
import com.yc.SellStore.biz.PayUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/alipay")
public class AlipayAction {
	@Resource
	private AlipayTradeService alipayTradeService;
	
	@Resource
    private AlipayProperties alipayProperties;

    private static Log log = LogFactory.getLog(AlipayProperties.class);
	
	
	/**
	 * 当面扫码付，扫码支付
	 */
	@GetMapping("/precreate")
	@ResponseBody 
	public void precreate(HttpServletRequest request ,HttpServletResponse response) throws Exception{
		//订单编号，暂时使用普通订单号
		String outTradeNo =UUID.randomUUID().toString();
		//订单标题
		String subject = "测试订单";
		//订单描述
		String body = "购买两件商品共计20.0元";
		//【必填】 订单总金额
		String totalAmount ="0.01";
		//不可打折金额
		String 	undiscountableAmount = "";
		
		//卖家支付宝账号ID，若为空，则默认为appid
		String sellerId="";
		//【必填】商户门店编号
		String storeId = "test_Store_id";
		
        // 商户操作员编号，添加此参数可以为商户操作员做销售统计
        String operatorId = "test_operator_id";
        
        //商品明细列表
        List<GoodsDetail> goodsDetailList  = new ArrayList<>();
        GoodsDetail goods1 = GoodsDetail.newInstance("goods_id001", "李志专辑", 1, 1);
        goodsDetailList.add(goods1);
        GoodsDetail goods2 = GoodsDetail.newInstance("goods_id002", "李志专辑合集", 1, 2);
        goodsDetailList.add(goods2);
        
        //支付超时，线下交易定义为5分钟
        String timeoutExpress = "5m";
        
        
        AlipayTradePrecreateRequestBuilder builder = new AlipayTradePrecreateRequestBuilder()
        		.setStoreId(storeId)
        		.setSubject(subject)
        		.setBody(body)
        		.setGoodsDetailList(goodsDetailList)
        		.setSellerId(sellerId)
        		.setOperatorId(operatorId)
        		.setTimeoutExpress(timeoutExpress)
        		.setUndiscountableAmount(undiscountableAmount)
        		.setNotifyUrl(alipayProperties.getNotifyUrl())
        		.setOutTradeNo(outTradeNo)
        		.setTotalAmount(totalAmount);
        
        
        AlipayF2FPrecreateResult result = alipayTradeService.tradePrecreate(builder);
        String qrCodeUrl = null;
        switch(result.getTradeStatus()) {
        case SUCCESS:
        	log.info("支付宝预下单成功");
        	AlipayTradePrecreateResponse res = result.getResponse();
        	BufferedImage image = PayUtil.getQRCodeImge(res.getQrCode());
        	
        	response.setContentType("image/jpeg");
        	response.setHeader("Pragma", "no-cache");
        	response.setHeader("Cache-Control", "no-cache");
        	response.setIntHeader("Expires", -1);
        	ImageIO.write(image, "JPEG", response.getOutputStream());
        	break;
        	
        case FAILED:
        	log.error("支付宝预下单失败");
        	break;
        	
        	
        case UNKNOWN:
        	log.error("系统异常，下单情况未知");
        	break;
        default:
        	log.error("不支持的交易状态，交易返回异常！！！");
        	break;
        }
        
        
        
	}
	
	
}
