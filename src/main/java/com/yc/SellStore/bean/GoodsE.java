package com.yc.SellStore.bean;

public class GoodsE {
private String goodsname;
private String orderId;
private String Ordertime;
private Integer count;
private String pic;
private String status;



public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getGoodsname() {
	return goodsname;
}
public void setGoodsname(String goodsname) {
	this.goodsname = goodsname;
}
public String getOrderId() {
	return orderId;
}
public void setOrderId(String orderId) {
	this.orderId = orderId;
}
public String getOrdertime() {
	return Ordertime;
}
public void setOrdertime(String ordertime) {
	Ordertime = ordertime;
}
public Integer getCount() {
	return count;
}
public void setCount(Integer count) {
	this.count = count;
}
public String getPic() {
	return pic;
}
public void setPic(String pic) {
	this.pic = pic;
}
@Override
public String toString() {
	return "GoodsE [goodsname=" + goodsname + ", orderId=" + orderId + ", Ordertime=" + Ordertime + ", count=" + count
			+ ", pic=" + pic + ", status=" + status + "]";
}



}
