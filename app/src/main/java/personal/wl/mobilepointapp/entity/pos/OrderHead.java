package personal.wl.mobilepointapp.entity.pos;

import java.sql.*;

   /**
    * @filename:order_head.java
    * @create date:2019-01-08 15:57:57
    * @creater:weiliang 
    * @file desc:order_head entity
    * @file version:V0.01 
    */ 


public class OrderHead {
	private String OrderId;
	private Date InputDate;
	private String BraId;
	private String SupId;
	private String OrderType;
	private String OrderMode;
	private String DMId;
	private Date PreReceiptDate;
	private Date EndDate;
	private String Status;
	private String Status1;
	private Date ReceiptDate;
	private String ReceiptId;
	private String ManagerId;
	private String OperatorId;
	private String Remark;
	private Date updatedate;
	private String payableflag;
	private Integer printNum;
	private String sendflag;
	private String ReceiptMan;
	private Long OrderAmt;
	private Long ReceiptAmt;
	private String AuditMan1;
	private String AuditMan2;
	private String AuditMan3;
	private String AuditMan4;
	private String AuditMan5;
	private String origreceiptid;
	public void setOrderId(String OrderId){
	this.OrderId=OrderId;
	}
	public String getOrderId(){
		return OrderId;
	}
	public void setInputDate(Date InputDate){
	this.InputDate=InputDate;
	}
	public Date getInputDate(){
		return InputDate;
	}
	public void setBraId(String BraId){
	this.BraId=BraId;
	}
	public String getBraId(){
		return BraId;
	}
	public void setSupId(String SupId){
	this.SupId=SupId;
	}
	public String getSupId(){
		return SupId;
	}
	public void setOrderType(String OrderType){
	this.OrderType=OrderType;
	}
	public String getOrderType(){
		return OrderType;
	}
	public void setOrderMode(String OrderMode){
	this.OrderMode=OrderMode;
	}
	public String getOrderMode(){
		return OrderMode;
	}
	public void setDMId(String DMId){
	this.DMId=DMId;
	}
	public String getDMId(){
		return DMId;
	}
	public void setPreReceiptDate(Date PreReceiptDate){
	this.PreReceiptDate=PreReceiptDate;
	}
	public Date getPreReceiptDate(){
		return PreReceiptDate;
	}
	public void setEndDate(Date EndDate){
	this.EndDate=EndDate;
	}
	public Date getEndDate(){
		return EndDate;
	}
	public void setStatus(String Status){
	this.Status=Status;
	}
	public String getStatus(){
		return Status;
	}
	public void setStatus1(String Status1){
	this.Status1=Status1;
	}
	public String getStatus1(){
		return Status1;
	}
	public void setReceiptDate(Date ReceiptDate){
	this.ReceiptDate=ReceiptDate;
	}
	public Date getReceiptDate(){
		return ReceiptDate;
	}
	public void setReceiptId(String ReceiptId){
	this.ReceiptId=ReceiptId;
	}
	public String getReceiptId(){
		return ReceiptId;
	}
	public void setManagerId(String ManagerId){
	this.ManagerId=ManagerId;
	}
	public String getManagerId(){
		return ManagerId;
	}
	public void setOperatorId(String OperatorId){
	this.OperatorId=OperatorId;
	}
	public String getOperatorId(){
		return OperatorId;
	}
	public void setRemark(String Remark){
	this.Remark=Remark;
	}
	public String getRemark(){
		return Remark;
	}
	public void setUpdatedate(Date updatedate){
	this.updatedate=updatedate;
	}
	public Date getUpdatedate(){
		return updatedate;
	}
	public void setPayableflag(String payableflag){
	this.payableflag=payableflag;
	}
	public String getPayableflag(){
		return payableflag;
	}
	public void setPrintNum(Integer printNum){
	this.printNum=printNum;
	}
	public Integer getPrintNum(){
		return printNum;
	}
	public void setSendflag(String sendflag){
	this.sendflag=sendflag;
	}
	public String getSendflag(){
		return sendflag;
	}
	public void setReceiptMan(String ReceiptMan){
	this.ReceiptMan=ReceiptMan;
	}
	public String getReceiptMan(){
		return ReceiptMan;
	}
	public void setOrderAmt(Long OrderAmt){
	this.OrderAmt=OrderAmt;
	}
	public Long getOrderAmt(){
		return OrderAmt;
	}
	public void setReceiptAmt(Long ReceiptAmt){
	this.ReceiptAmt=ReceiptAmt;
	}
	public Long getReceiptAmt(){
		return ReceiptAmt;
	}
	public void setAuditMan1(String AuditMan1){
	this.AuditMan1=AuditMan1;
	}
	public String getAuditMan1(){
		return AuditMan1;
	}
	public void setAuditMan2(String AuditMan2){
	this.AuditMan2=AuditMan2;
	}
	public String getAuditMan2(){
		return AuditMan2;
	}
	public void setAuditMan3(String AuditMan3){
	this.AuditMan3=AuditMan3;
	}
	public String getAuditMan3(){
		return AuditMan3;
	}
	public void setAuditMan4(String AuditMan4){
	this.AuditMan4=AuditMan4;
	}
	public String getAuditMan4(){
		return AuditMan4;
	}
	public void setAuditMan5(String AuditMan5){
	this.AuditMan5=AuditMan5;
	}
	public String getAuditMan5(){
		return AuditMan5;
	}
	public void setOrigreceiptid(String origreceiptid){
	this.origreceiptid=origreceiptid;
	}
	public String getOrigreceiptid(){
		return origreceiptid;
	}
}

