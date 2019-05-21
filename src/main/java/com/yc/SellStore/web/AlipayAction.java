package com.yc.SellStore.web;

import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeCloseModel;
import com.alipay.api.domain.AlipayTradeFastpayRefundQueryModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
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

	  @Autowired
	    private AlipayClient alipayClient;
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
	/**
     * 校验签名
     * @param request
     * @return
     */
    public boolean rsaCheckV1(HttpServletRequest request){
        // https://docs.open.alipay.com/54/106370
        // 获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }

        try {
            boolean verifyResult = AlipaySignature.rsaCheckV1(params,
                    alipayProperties.getAlipayPublicKey(),
                    alipayProperties.getCharset(),
                    alipayProperties.getSignType());

            return verifyResult;
        } catch (AlipayApiException e) {
            log.debug("verify sigin error, exception is:{}", e);
            return false;
        }
    }
    @RequestMapping("/notify")
    public String notify(HttpServletRequest request) throws AlipayApiException, UnsupportedEncodingException {
        // 一定要验签，防止黑客篡改参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        StringBuilder notifyBuild = new StringBuilder("/****************************** alipay notify ******************************/\n");
        parameterMap.forEach((key, value) -> notifyBuild.append(key + "=" + value[0] + "\n") );
        log.info(notifyBuild.toString());


        boolean flag = this.rsaCheckV1(request);
        if (flag) {
            /**
             * TODO 需要严格按照如下描述校验通知数据的正确性
             *
             * 商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
             * 并判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
             * 同时需要校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
             *
             * 上述有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。
             * 在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。
             * 在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
             */

            //交易状态
            String tradeStatus = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
            // 商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
            // TRADE_FINISHED(表示交易已经成功结束，并不能再对该交易做后续操作);
            // TRADE_SUCCESS(表示交易已经成功结束，可以对该交易做后续操作，如：分润、退款等);
            if(tradeStatus.equals("TRADE_FINISHED")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，
                // 并判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），并执行商户的业务程序
                //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //如果签约的是可退款协议，退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
                //如果没有签约可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
            } else if (tradeStatus.equals("TRADE_SUCCESS")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，
                // 并判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），并执行商户的业务程序
                //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //如果签约的是可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。

            }

            return "success";
        }

        return "fail";
    }
    
    
    /**
     * 退款查询
     * @param orderNo 商户订单号
     * @param refundOrderNo 请求退款接口时，传入的退款请求号，如果在退款请求时未传入，则该值为创建交易时的外部订单号
     * @return
     * @throws AlipayApiException
     */
    @GetMapping("/refundQuery")
    @ResponseBody
    public String refundQuery(String orderNo, String refundOrderNo) throws AlipayApiException {
        AlipayTradeFastpayRefundQueryRequest alipayRequest = new AlipayTradeFastpayRefundQueryRequest();

        AlipayTradeFastpayRefundQueryModel model=new AlipayTradeFastpayRefundQueryModel();
        model.setOutTradeNo(orderNo);
        model.setOutRequestNo(refundOrderNo);
        alipayRequest.setBizModel(model);

        AlipayTradeFastpayRefundQueryResponse alipayResponse = alipayClient.execute(alipayRequest);
        System.out.println(alipayResponse.getBody());

        return alipayResponse.getBody();
    }

    @PostMapping("/close")
    @ResponseBody
    public String close(String orderNo) throws AlipayApiException {
        AlipayTradeCloseRequest alipayRequest = new AlipayTradeCloseRequest();
        AlipayTradeCloseModel model =new AlipayTradeCloseModel();
        model.setOutTradeNo(orderNo);
        alipayRequest.setBizModel(model);

        AlipayTradeCloseResponse alipayResponse = alipayClient.execute(alipayRequest);
        System.out.println(alipayResponse.getBody());

        return alipayResponse.getBody();
    }
    
    /**
     * 退款
     * @param orderNo 商户订单号
     * @return
     */
    @PostMapping("/refund")
    @ResponseBody
    public String refund(String orderNo) throws AlipayApiException {
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();

        AlipayTradeRefundModel model=new AlipayTradeRefundModel();
        // 商户订单号
        model.setOutTradeNo(orderNo);
        // 退款金额
        model.setRefundAmount("0.01");
        // 退款原因
        model.setRefundReason("无理由退货");
        // 退款订单号(同一个订单可以分多次部分退款，当分多次时必传)
//        model.setOutRequestNo(UUID.randomUUID().toString());
        alipayRequest.setBizModel(model);

        AlipayTradeRefundResponse alipayResponse = alipayClient.execute(alipayRequest);
        System.out.println(alipayResponse.getBody());

        return alipayResponse.getBody();
    }
	
}
