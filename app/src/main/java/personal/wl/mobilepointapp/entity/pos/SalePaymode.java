package personal.wl.mobilepointapp.entity.pos;

import java.sql.*;

   /**
    * @filename:sale_paymode.java
    * @create date:2019-01-08 15:56:11
    * @creater:weiliang 
    * @file desc:sale_paymode entity
    * @file version:V0.01 
    */ 


public class SalePaymode {
	private String BraId;
	private Date SaleDate;
	private String SaleId;
	private String SalerId;
	private String PaymodeId;
	private Long PayMoney;
	private String cardtype;
	private String cardno;
	public void setBraId(String BraId){
	this.BraId=BraId;
	}
	public String getBraId(){
		return BraId;
	}
	public void setSaleDate(Date SaleDate){
	this.SaleDate=SaleDate;
	}
	public Date getSaleDate(){
		return SaleDate;
	}
	public void setSaleId(String SaleId){
	this.SaleId=SaleId;
	}
	public String getSaleId(){
		return SaleId;
	}
	public void setSalerId(String SalerId){
	this.SalerId=SalerId;
	}
	public String getSalerId(){
		return SalerId;
	}
	public void setPaymodeId(String PaymodeId){
	this.PaymodeId=PaymodeId;
	}
	public String getPaymodeId(){
		return PaymodeId;
	}
	public void setPayMoney(Long PayMoney){
	this.PayMoney=PayMoney;
	}
	public Long getPayMoney(){
		return PayMoney;
	}
	public void setCardtype(String cardtype){
	this.cardtype=cardtype;
	}
	public String getCardtype(){
		return cardtype;
	}
	public void setCardno(String cardno){
	this.cardno=cardno;
	}
	public String getCardno(){
		return cardno;
	}
}

