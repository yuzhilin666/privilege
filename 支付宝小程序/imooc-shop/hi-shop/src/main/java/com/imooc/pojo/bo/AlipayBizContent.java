package com.imooc.pojo.bo;

public class AlipayBizContent {

    private String out_trade_no;		// 商户订单号，自己平台的
    private float total_amount;			// 支付总金额，单位(元)，例：19.99
    private String subject;				// 商品名称
    private String buyer_id;			// 买家用户id，支付宝的唯一userId
    
    private String body;					// 自定义参数
	
    public AlipayBizContent() {
		super();
	}
    
    public AlipayBizContent(String out_trade_no, float total_amount, String subject, String buyer_id) {
		super();
		this.out_trade_no = out_trade_no;
		this.total_amount = total_amount;
		this.subject = subject;
		this.buyer_id = buyer_id;
	}
    
	public AlipayBizContent(String out_trade_no, float total_amount, String subject, String buyer_id, String body) {
		super();
		this.out_trade_no = out_trade_no;
		this.total_amount = total_amount;
		this.subject = subject;
		this.buyer_id = buyer_id;
		this.body = body;
	}
    
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public float getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(float total_amount) {
		this.total_amount = total_amount;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBuyer_id() {
		return buyer_id;
	}
	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}