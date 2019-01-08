package personal.wl.mobilepointapp.entity.pos;

import java.sql.*;

   /**
    * @filename:sale_daily.java
    * @create date:2019-01-08 15:55:42
    * @creater:weiliang 
    * @file desc:sale_daily entity
    * @file version:V0.01 
    */ 


public class SaleDaily {
	private String BraId;
	private Date SaleDate;
	private String ProId;
	private String Barcode;
	private String ClassId;
	private String IsDM;
	private String IsPmt;
	private String IsTimePrompt;
	private Long SaleTax;
	private String PosNo;
	private String SalerId;
	private String SaleMan;
	private String SaleType;
	private Long SaleQty;
	private Long SaleAmt;
	private Long SaleDisAmt;
	private Long TransDisAmt;
	private Long NormalPrice;
	private Long CurPrice;
	private Long AvgCostPrice;
	private Long LastCostPrice;
	private String SaleId;
	private String MemCardNo;
	private String InvoiceId;
	private Long Points1;
	private Long Points;
	private Long ReturnRat;
	private Long cash1;
	private Long cash2;
	private Long cash3;
	private Long cash4;
	private Long cash5;
	private Long cash6;
	private Long cash7;
	private Long cash8;
	private Integer id;
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
	public void setProId(String ProId){
	this.ProId=ProId;
	}
	public String getProId(){
		return ProId;
	}
	public void setBarcode(String Barcode){
	this.Barcode=Barcode;
	}
	public String getBarcode(){
		return Barcode;
	}
	public void setClassId(String ClassId){
	this.ClassId=ClassId;
	}
	public String getClassId(){
		return ClassId;
	}
	public void setIsDM(String IsDM){
	this.IsDM=IsDM;
	}
	public String getIsDM(){
		return IsDM;
	}
	public void setIsPmt(String IsPmt){
	this.IsPmt=IsPmt;
	}
	public String getIsPmt(){
		return IsPmt;
	}
	public void setIsTimePrompt(String IsTimePrompt){
	this.IsTimePrompt=IsTimePrompt;
	}
	public String getIsTimePrompt(){
		return IsTimePrompt;
	}
	public void setSaleTax(Long SaleTax){
	this.SaleTax=SaleTax;
	}
	public Long getSaleTax(){
		return SaleTax;
	}
	public void setPosNo(String PosNo){
	this.PosNo=PosNo;
	}
	public String getPosNo(){
		return PosNo;
	}
	public void setSalerId(String SalerId){
	this.SalerId=SalerId;
	}
	public String getSalerId(){
		return SalerId;
	}
	public void setSaleMan(String SaleMan){
	this.SaleMan=SaleMan;
	}
	public String getSaleMan(){
		return SaleMan;
	}
	public void setSaleType(String SaleType){
	this.SaleType=SaleType;
	}
	public String getSaleType(){
		return SaleType;
	}
	public void setSaleQty(Long SaleQty){
	this.SaleQty=SaleQty;
	}
	public Long getSaleQty(){
		return SaleQty;
	}
	public void setSaleAmt(Long SaleAmt){
	this.SaleAmt=SaleAmt;
	}
	public Long getSaleAmt(){
		return SaleAmt;
	}
	public void setSaleDisAmt(Long SaleDisAmt){
	this.SaleDisAmt=SaleDisAmt;
	}
	public Long getSaleDisAmt(){
		return SaleDisAmt;
	}
	public void setTransDisAmt(Long TransDisAmt){
	this.TransDisAmt=TransDisAmt;
	}
	public Long getTransDisAmt(){
		return TransDisAmt;
	}
	public void setNormalPrice(Long NormalPrice){
	this.NormalPrice=NormalPrice;
	}
	public Long getNormalPrice(){
		return NormalPrice;
	}
	public void setCurPrice(Long CurPrice){
	this.CurPrice=CurPrice;
	}
	public Long getCurPrice(){
		return CurPrice;
	}
	public void setAvgCostPrice(Long AvgCostPrice){
	this.AvgCostPrice=AvgCostPrice;
	}
	public Long getAvgCostPrice(){
		return AvgCostPrice;
	}
	public void setLastCostPrice(Long LastCostPrice){
	this.LastCostPrice=LastCostPrice;
	}
	public Long getLastCostPrice(){
		return LastCostPrice;
	}
	public void setSaleId(String SaleId){
	this.SaleId=SaleId;
	}
	public String getSaleId(){
		return SaleId;
	}
	public void setMemCardNo(String MemCardNo){
	this.MemCardNo=MemCardNo;
	}
	public String getMemCardNo(){
		return MemCardNo;
	}
	public void setInvoiceId(String InvoiceId){
	this.InvoiceId=InvoiceId;
	}
	public String getInvoiceId(){
		return InvoiceId;
	}
	public void setPoints1(Long Points1){
	this.Points1=Points1;
	}
	public Long getPoints1(){
		return Points1;
	}
	public void setPoints(Long Points){
	this.Points=Points;
	}
	public Long getPoints(){
		return Points;
	}
	public void setReturnRat(Long ReturnRat){
	this.ReturnRat=ReturnRat;
	}
	public Long getReturnRat(){
		return ReturnRat;
	}
	public void setCash1(Long cash1){
	this.cash1=cash1;
	}
	public Long getCash1(){
		return cash1;
	}
	public void setCash2(Long cash2){
	this.cash2=cash2;
	}
	public Long getCash2(){
		return cash2;
	}
	public void setCash3(Long cash3){
	this.cash3=cash3;
	}
	public Long getCash3(){
		return cash3;
	}
	public void setCash4(Long cash4){
	this.cash4=cash4;
	}
	public Long getCash4(){
		return cash4;
	}
	public void setCash5(Long cash5){
	this.cash5=cash5;
	}
	public Long getCash5(){
		return cash5;
	}
	public void setCash6(Long cash6){
	this.cash6=cash6;
	}
	public Long getCash6(){
		return cash6;
	}
	public void setCash7(Long cash7){
	this.cash7=cash7;
	}
	public Long getCash7(){
		return cash7;
	}
	public void setCash8(Long cash8){
	this.cash8=cash8;
	}
	public Long getCash8(){
		return cash8;
	}
	public void setId(Integer id){
	this.id=id;
	}
	public Integer getId(){
		return id;
	}
}

