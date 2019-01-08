package personal.wl.mobilepointapp.entity;
import java.sql.*;

   /**
    * @filename:product.java
    * @create date:2019-01-08 14:28:00
    * @creater:weiliang 
    * @file desc:product entity
    * @file version:V0.01 
    */ 


public class Product{
	private String ProId;
	private String Barcode;
	private String ProName;
	private String ProSName;
	private String ClassId;
	private String Spec;
	private String BrandId;
	private String StatId;
	private String Grade;
	private String Area;
	private String SupId;
	private String MeasureId;
	private Long PacketQty;
	private Long PacketQty1;
	private Long Weight;
	private Long Length;
	private Long Width;
	private Long Height;
	private String TaxType;
	private Long InTax;
	private Long SaleTax;
	private Long InPrice;
	private Long TaxPrice;
	private Long NormalPrice;
	private Long MemberPrice;
	private Long GroupPrice;
	private Long VipDisCount;
	private String MainFlag;
	private String ProFlag;
	private String WeightFlag;
	private String Barmode;
	private String OrderMode;
	private Long MinOrderQty;
	private Long OrderMultiplier;
	private String FreshMode;
	private Long ReturnRat;
	private Integer WarrantyDays;
	private String UDF1;
	private String UDF2;
	private String UDF3;
	private String Status;
	private String PromtFlag;
	private String PotFlag;
	private String CanChangePrice;
	private Long avgcostprice;
	private Integer cardpoint;
	private Date CreateDate;
	private Date UpdateDate;
	private Date StopDate;
	private String SupPmtFlag;
	private String Operatorid;
	private Long testQty;
	private Long testAmt;
	private Long TestProfit;
	private String Sensitivity;
	private String SeasonalFlag;
	private Date SBegindate;
	private Date SEnddate;
	private Date StatusChgDate;
	private Integer timestamp;
	private Long guideprice;
	private String qrcodeinfo;
	private Long posdiscount;
	private String onlineflag;
	private String invoice;
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
	public void setProName(String ProName){
	this.ProName=ProName;
	}
	public String getProName(){
		return ProName;
	}
	public void setProSName(String ProSName){
	this.ProSName=ProSName;
	}
	public String getProSName(){
		return ProSName;
	}
	public void setClassId(String ClassId){
	this.ClassId=ClassId;
	}
	public String getClassId(){
		return ClassId;
	}
	public void setSpec(String Spec){
	this.Spec=Spec;
	}
	public String getSpec(){
		return Spec;
	}
	public void setBrandId(String BrandId){
	this.BrandId=BrandId;
	}
	public String getBrandId(){
		return BrandId;
	}
	public void setStatId(String StatId){
	this.StatId=StatId;
	}
	public String getStatId(){
		return StatId;
	}
	public void setGrade(String Grade){
	this.Grade=Grade;
	}
	public String getGrade(){
		return Grade;
	}
	public void setArea(String Area){
	this.Area=Area;
	}
	public String getArea(){
		return Area;
	}
	public void setSupId(String SupId){
	this.SupId=SupId;
	}
	public String getSupId(){
		return SupId;
	}
	public void setMeasureId(String MeasureId){
	this.MeasureId=MeasureId;
	}
	public String getMeasureId(){
		return MeasureId;
	}
	public void setPacketQty(Long PacketQty){
	this.PacketQty=PacketQty;
	}
	public Long getPacketQty(){
		return PacketQty;
	}
	public void setPacketQty1(Long PacketQty1){
	this.PacketQty1=PacketQty1;
	}
	public Long getPacketQty1(){
		return PacketQty1;
	}
	public void setWeight(Long Weight){
	this.Weight=Weight;
	}
	public Long getWeight(){
		return Weight;
	}
	public void setLength(Long Length){
	this.Length=Length;
	}
	public Long getLength(){
		return Length;
	}
	public void setWidth(Long Width){
	this.Width=Width;
	}
	public Long getWidth(){
		return Width;
	}
	public void setHeight(Long Height){
	this.Height=Height;
	}
	public Long getHeight(){
		return Height;
	}
	public void setTaxType(String TaxType){
	this.TaxType=TaxType;
	}
	public String getTaxType(){
		return TaxType;
	}
	public void setInTax(Long InTax){
	this.InTax=InTax;
	}
	public Long getInTax(){
		return InTax;
	}
	public void setSaleTax(Long SaleTax){
	this.SaleTax=SaleTax;
	}
	public Long getSaleTax(){
		return SaleTax;
	}
	public void setInPrice(Long InPrice){
	this.InPrice=InPrice;
	}
	public Long getInPrice(){
		return InPrice;
	}
	public void setTaxPrice(Long TaxPrice){
	this.TaxPrice=TaxPrice;
	}
	public Long getTaxPrice(){
		return TaxPrice;
	}
	public void setNormalPrice(Long NormalPrice){
	this.NormalPrice=NormalPrice;
	}
	public Long getNormalPrice(){
		return NormalPrice;
	}
	public void setMemberPrice(Long MemberPrice){
	this.MemberPrice=MemberPrice;
	}
	public Long getMemberPrice(){
		return MemberPrice;
	}
	public void setGroupPrice(Long GroupPrice){
	this.GroupPrice=GroupPrice;
	}
	public Long getGroupPrice(){
		return GroupPrice;
	}
	public void setVipDisCount(Long VipDisCount){
	this.VipDisCount=VipDisCount;
	}
	public Long getVipDisCount(){
		return VipDisCount;
	}
	public void setMainFlag(String MainFlag){
	this.MainFlag=MainFlag;
	}
	public String getMainFlag(){
		return MainFlag;
	}
	public void setProFlag(String ProFlag){
	this.ProFlag=ProFlag;
	}
	public String getProFlag(){
		return ProFlag;
	}
	public void setWeightFlag(String WeightFlag){
	this.WeightFlag=WeightFlag;
	}
	public String getWeightFlag(){
		return WeightFlag;
	}
	public void setBarmode(String Barmode){
	this.Barmode=Barmode;
	}
	public String getBarmode(){
		return Barmode;
	}
	public void setOrderMode(String OrderMode){
	this.OrderMode=OrderMode;
	}
	public String getOrderMode(){
		return OrderMode;
	}
	public void setMinOrderQty(Long MinOrderQty){
	this.MinOrderQty=MinOrderQty;
	}
	public Long getMinOrderQty(){
		return MinOrderQty;
	}
	public void setOrderMultiplier(Long OrderMultiplier){
	this.OrderMultiplier=OrderMultiplier;
	}
	public Long getOrderMultiplier(){
		return OrderMultiplier;
	}
	public void setFreshMode(String FreshMode){
	this.FreshMode=FreshMode;
	}
	public String getFreshMode(){
		return FreshMode;
	}
	public void setReturnRat(Long ReturnRat){
	this.ReturnRat=ReturnRat;
	}
	public Long getReturnRat(){
		return ReturnRat;
	}
	public void setWarrantyDays(Integer WarrantyDays){
	this.WarrantyDays=WarrantyDays;
	}
	public Integer getWarrantyDays(){
		return WarrantyDays;
	}
	public void setUDF1(String UDF1){
	this.UDF1=UDF1;
	}
	public String getUDF1(){
		return UDF1;
	}
	public void setUDF2(String UDF2){
	this.UDF2=UDF2;
	}
	public String getUDF2(){
		return UDF2;
	}
	public void setUDF3(String UDF3){
	this.UDF3=UDF3;
	}
	public String getUDF3(){
		return UDF3;
	}
	public void setStatus(String Status){
	this.Status=Status;
	}
	public String getStatus(){
		return Status;
	}
	public void setPromtFlag(String PromtFlag){
	this.PromtFlag=PromtFlag;
	}
	public String getPromtFlag(){
		return PromtFlag;
	}
	public void setPotFlag(String PotFlag){
	this.PotFlag=PotFlag;
	}
	public String getPotFlag(){
		return PotFlag;
	}
	public void setCanChangePrice(String CanChangePrice){
	this.CanChangePrice=CanChangePrice;
	}
	public String getCanChangePrice(){
		return CanChangePrice;
	}
	public void setAvgcostprice(Long avgcostprice){
	this.avgcostprice=avgcostprice;
	}
	public Long getAvgcostprice(){
		return avgcostprice;
	}
	public void setCardpoint(Integer cardpoint){
	this.cardpoint=cardpoint;
	}
	public Integer getCardpoint(){
		return cardpoint;
	}
	public void setCreateDate(Date CreateDate){
	this.CreateDate=CreateDate;
	}
	public Date getCreateDate(){
		return CreateDate;
	}
	public void setUpdateDate(Date UpdateDate){
	this.UpdateDate=UpdateDate;
	}
	public Date getUpdateDate(){
		return UpdateDate;
	}
	public void setStopDate(Date StopDate){
	this.StopDate=StopDate;
	}
	public Date getStopDate(){
		return StopDate;
	}
	public void setSupPmtFlag(String SupPmtFlag){
	this.SupPmtFlag=SupPmtFlag;
	}
	public String getSupPmtFlag(){
		return SupPmtFlag;
	}
	public void setOperatorid(String Operatorid){
	this.Operatorid=Operatorid;
	}
	public String getOperatorid(){
		return Operatorid;
	}
	public void setTestQty(Long testQty){
	this.testQty=testQty;
	}
	public Long getTestQty(){
		return testQty;
	}
	public void setTestAmt(Long testAmt){
	this.testAmt=testAmt;
	}
	public Long getTestAmt(){
		return testAmt;
	}
	public void setTestProfit(Long TestProfit){
	this.TestProfit=TestProfit;
	}
	public Long getTestProfit(){
		return TestProfit;
	}
	public void setSensitivity(String Sensitivity){
	this.Sensitivity=Sensitivity;
	}
	public String getSensitivity(){
		return Sensitivity;
	}
	public void setSeasonalFlag(String SeasonalFlag){
	this.SeasonalFlag=SeasonalFlag;
	}
	public String getSeasonalFlag(){
		return SeasonalFlag;
	}
	public void setSBegindate(Date SBegindate){
	this.SBegindate=SBegindate;
	}
	public Date getSBegindate(){
		return SBegindate;
	}
	public void setSEnddate(Date SEnddate){
	this.SEnddate=SEnddate;
	}
	public Date getSEnddate(){
		return SEnddate;
	}
	public void setStatusChgDate(Date StatusChgDate){
	this.StatusChgDate=StatusChgDate;
	}
	public Date getStatusChgDate(){
		return StatusChgDate;
	}
	public void setTimestamp(Integer timestamp){
	this.timestamp=timestamp;
	}
	public Integer getTimestamp(){
		return timestamp;
	}
	public void setGuideprice(Long guideprice){
	this.guideprice=guideprice;
	}
	public Long getGuideprice(){
		return guideprice;
	}
	public void setQrcodeinfo(String qrcodeinfo){
	this.qrcodeinfo=qrcodeinfo;
	}
	public String getQrcodeinfo(){
		return qrcodeinfo;
	}
	public void setPosdiscount(Long posdiscount){
	this.posdiscount=posdiscount;
	}
	public Long getPosdiscount(){
		return posdiscount;
	}
	public void setOnlineflag(String onlineflag){
	this.onlineflag=onlineflag;
	}
	public String getOnlineflag(){
		return onlineflag;
	}
	public void setInvoice(String invoice){
	this.invoice=invoice;
	}
	public String getInvoice(){
		return invoice;
	}
}

