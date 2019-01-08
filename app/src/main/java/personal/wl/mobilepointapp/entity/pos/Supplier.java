package personal.wl.mobilepointapp.entity.pos;
import java.sql.*;

   /**
    * @filename:supplier.java
    * @create date:2019-01-08 14:39:14
    * @creater:weiliang 
    * @file desc:supplier entity
    * @file version:V0.01 
    */ 


public class Supplier{
	private String SupId;
	private String SupName;
	private String SupSName;
	private String SupGroupID;
	private String Addr;
	private String Tel;
	private String Fax;
	private String Zip;
	private String Legalman;
	private String Chiefman;
	private String Contactman;
	private String ContactTel;
	private String Email;
	private String Web;
	private String Attribute;
	private String SupType;
	private String CounterFlag;
	private String BankName;
	private String BankAcctNo;
	private String BankAcctName;
	private String RegisterCapital;
	private String TaxNo;
	private String LicenseNo;
	private String ProduceNo;
	private String SanitationNo;
	private String RegisterBrand;
	private String SettleMethod;
	private String PayMethod;
	private Integer SettleDays;
	private String Grade;
	private Long MinOrderQty;
	private Long MinOrderAmt;
	private Long ReserveAmt;
	private String OrderWeekday;
	private String SendWeekday;
	private Integer PreDays;
	private Integer LimitDays;
	private String CanReturn;
	private String LocalCheck;
	private String Status;
	private Date CreateDate;
	private Date UpdateDate;
	private Integer OrderDays;
	private Date startdate;
	private Date enddate;
	private String business;
	private String SCMFlag;
	private String password;
	private Integer sup_sku;
	private String SaleMethod;
	private String operatorid;
	private Integer timestamp;
	public void setSupId(String SupId){
	this.SupId=SupId;
	}
	public String getSupId(){
		return SupId;
	}
	public void setSupName(String SupName){
	this.SupName=SupName;
	}
	public String getSupName(){
		return SupName;
	}
	public void setSupSName(String SupSName){
	this.SupSName=SupSName;
	}
	public String getSupSName(){
		return SupSName;
	}
	public void setSupGroupID(String SupGroupID){
	this.SupGroupID=SupGroupID;
	}
	public String getSupGroupID(){
		return SupGroupID;
	}
	public void setAddr(String Addr){
	this.Addr=Addr;
	}
	public String getAddr(){
		return Addr;
	}
	public void setTel(String Tel){
	this.Tel=Tel;
	}
	public String getTel(){
		return Tel;
	}
	public void setFax(String Fax){
	this.Fax=Fax;
	}
	public String getFax(){
		return Fax;
	}
	public void setZip(String Zip){
	this.Zip=Zip;
	}
	public String getZip(){
		return Zip;
	}
	public void setLegalman(String Legalman){
	this.Legalman=Legalman;
	}
	public String getLegalman(){
		return Legalman;
	}
	public void setChiefman(String Chiefman){
	this.Chiefman=Chiefman;
	}
	public String getChiefman(){
		return Chiefman;
	}
	public void setContactman(String Contactman){
	this.Contactman=Contactman;
	}
	public String getContactman(){
		return Contactman;
	}
	public void setContactTel(String ContactTel){
	this.ContactTel=ContactTel;
	}
	public String getContactTel(){
		return ContactTel;
	}
	public void setEmail(String Email){
	this.Email=Email;
	}
	public String getEmail(){
		return Email;
	}
	public void setWeb(String Web){
	this.Web=Web;
	}
	public String getWeb(){
		return Web;
	}
	public void setAttribute(String Attribute){
	this.Attribute=Attribute;
	}
	public String getAttribute(){
		return Attribute;
	}
	public void setSupType(String SupType){
	this.SupType=SupType;
	}
	public String getSupType(){
		return SupType;
	}
	public void setCounterFlag(String CounterFlag){
	this.CounterFlag=CounterFlag;
	}
	public String getCounterFlag(){
		return CounterFlag;
	}
	public void setBankName(String BankName){
	this.BankName=BankName;
	}
	public String getBankName(){
		return BankName;
	}
	public void setBankAcctNo(String BankAcctNo){
	this.BankAcctNo=BankAcctNo;
	}
	public String getBankAcctNo(){
		return BankAcctNo;
	}
	public void setBankAcctName(String BankAcctName){
	this.BankAcctName=BankAcctName;
	}
	public String getBankAcctName(){
		return BankAcctName;
	}
	public void setRegisterCapital(String RegisterCapital){
	this.RegisterCapital=RegisterCapital;
	}
	public String getRegisterCapital(){
		return RegisterCapital;
	}
	public void setTaxNo(String TaxNo){
	this.TaxNo=TaxNo;
	}
	public String getTaxNo(){
		return TaxNo;
	}
	public void setLicenseNo(String LicenseNo){
	this.LicenseNo=LicenseNo;
	}
	public String getLicenseNo(){
		return LicenseNo;
	}
	public void setProduceNo(String ProduceNo){
	this.ProduceNo=ProduceNo;
	}
	public String getProduceNo(){
		return ProduceNo;
	}
	public void setSanitationNo(String SanitationNo){
	this.SanitationNo=SanitationNo;
	}
	public String getSanitationNo(){
		return SanitationNo;
	}
	public void setRegisterBrand(String RegisterBrand){
	this.RegisterBrand=RegisterBrand;
	}
	public String getRegisterBrand(){
		return RegisterBrand;
	}
	public void setSettleMethod(String SettleMethod){
	this.SettleMethod=SettleMethod;
	}
	public String getSettleMethod(){
		return SettleMethod;
	}
	public void setPayMethod(String PayMethod){
	this.PayMethod=PayMethod;
	}
	public String getPayMethod(){
		return PayMethod;
	}
	public void setSettleDays(Integer SettleDays){
	this.SettleDays=SettleDays;
	}
	public Integer getSettleDays(){
		return SettleDays;
	}
	public void setGrade(String Grade){
	this.Grade=Grade;
	}
	public String getGrade(){
		return Grade;
	}
	public void setMinOrderQty(Long MinOrderQty){
	this.MinOrderQty=MinOrderQty;
	}
	public Long getMinOrderQty(){
		return MinOrderQty;
	}
	public void setMinOrderAmt(Long MinOrderAmt){
	this.MinOrderAmt=MinOrderAmt;
	}
	public Long getMinOrderAmt(){
		return MinOrderAmt;
	}
	public void setReserveAmt(Long ReserveAmt){
	this.ReserveAmt=ReserveAmt;
	}
	public Long getReserveAmt(){
		return ReserveAmt;
	}
	public void setOrderWeekday(String OrderWeekday){
	this.OrderWeekday=OrderWeekday;
	}
	public String getOrderWeekday(){
		return OrderWeekday;
	}
	public void setSendWeekday(String SendWeekday){
	this.SendWeekday=SendWeekday;
	}
	public String getSendWeekday(){
		return SendWeekday;
	}
	public void setPreDays(Integer PreDays){
	this.PreDays=PreDays;
	}
	public Integer getPreDays(){
		return PreDays;
	}
	public void setLimitDays(Integer LimitDays){
	this.LimitDays=LimitDays;
	}
	public Integer getLimitDays(){
		return LimitDays;
	}
	public void setCanReturn(String CanReturn){
	this.CanReturn=CanReturn;
	}
	public String getCanReturn(){
		return CanReturn;
	}
	public void setLocalCheck(String LocalCheck){
	this.LocalCheck=LocalCheck;
	}
	public String getLocalCheck(){
		return LocalCheck;
	}
	public void setStatus(String Status){
	this.Status=Status;
	}
	public String getStatus(){
		return Status;
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
	public void setOrderDays(Integer OrderDays){
	this.OrderDays=OrderDays;
	}
	public Integer getOrderDays(){
		return OrderDays;
	}
	public void setStartdate(Date startdate){
	this.startdate=startdate;
	}
	public Date getStartdate(){
		return startdate;
	}
	public void setEnddate(Date enddate){
	this.enddate=enddate;
	}
	public Date getEnddate(){
		return enddate;
	}
	public void setBusiness(String business){
	this.business=business;
	}
	public String getBusiness(){
		return business;
	}
	public void setSCMFlag(String SCMFlag){
	this.SCMFlag=SCMFlag;
	}
	public String getSCMFlag(){
		return SCMFlag;
	}
	public void setPassword(String password){
	this.password=password;
	}
	public String getPassword(){
		return password;
	}
	public void setSup_sku(Integer sup_sku){
	this.sup_sku=sup_sku;
	}
	public Integer getSup_sku(){
		return sup_sku;
	}
	public void setSaleMethod(String SaleMethod){
	this.SaleMethod=SaleMethod;
	}
	public String getSaleMethod(){
		return SaleMethod;
	}
	public void setOperatorid(String operatorid){
	this.operatorid=operatorid;
	}
	public String getOperatorid(){
		return operatorid;
	}
	public void setTimestamp(Integer timestamp){
	this.timestamp=timestamp;
	}
	public Integer getTimestamp(){
		return timestamp;
	}
}

