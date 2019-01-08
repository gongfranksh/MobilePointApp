package personal.wl.mobilepointapp.entity.pos;

import java.sql.*;

   /**
    * @filename:branch.java
    * @create date:2019-01-08 15:52:28
    * @creater:weiliang 
    * @file desc:branch entity
    * @file version:V0.01 
    */ 


public class Branch{
	private String HqId;
	private String BraId;
	private String BraName;
	private String BraSName;
	private String BraType;
	private String Addr;
	private String Tel;
	private String Fax;
	private String ZipCode;
	private String DistId;
	private String Master;
	private Date OpenDate;
	private String SizeCode;
	private Long Square;
	private String AlloPriority;
	private Long AlloDiscount;
	private Integer AlloPeroid;
	private String Whid;
	private Long Sprice_quotiety;
	private Long Mprice_quotiety;
	private Integer VehicleNum;
	private Integer EmpCount;
	private String Status;
	private Date CreateDate;
	private Date UpdateDate;
	private String Flag1;
	private String Flag2;
	private Date usedate;
	private String serialno;
	private String serialno1;
	private String cstore;
	private Integer PosNum;
	private String showorderprice;
	private String showreceiptamt;
	private String showprofit;
	private String DmCostFlag;
	private String IsOpen;
	public void setHqId(String HqId){
	this.HqId=HqId;
	}
	public String getHqId(){
		return HqId;
	}
	public void setBraId(String BraId){
	this.BraId=BraId;
	}
	public String getBraId(){
		return BraId;
	}
	public void setBraName(String BraName){
	this.BraName=BraName;
	}
	public String getBraName(){
		return BraName;
	}
	public void setBraSName(String BraSName){
	this.BraSName=BraSName;
	}
	public String getBraSName(){
		return BraSName;
	}
	public void setBraType(String BraType){
	this.BraType=BraType;
	}
	public String getBraType(){
		return BraType;
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
	public void setZipCode(String ZipCode){
	this.ZipCode=ZipCode;
	}
	public String getZipCode(){
		return ZipCode;
	}
	public void setDistId(String DistId){
	this.DistId=DistId;
	}
	public String getDistId(){
		return DistId;
	}
	public void setMaster(String Master){
	this.Master=Master;
	}
	public String getMaster(){
		return Master;
	}
	public void setOpenDate(Date OpenDate){
	this.OpenDate=OpenDate;
	}
	public Date getOpenDate(){
		return OpenDate;
	}
	public void setSizeCode(String SizeCode){
	this.SizeCode=SizeCode;
	}
	public String getSizeCode(){
		return SizeCode;
	}
	public void setSquare(Long Square){
	this.Square=Square;
	}
	public Long getSquare(){
		return Square;
	}
	public void setAlloPriority(String AlloPriority){
	this.AlloPriority=AlloPriority;
	}
	public String getAlloPriority(){
		return AlloPriority;
	}
	public void setAlloDiscount(Long AlloDiscount){
	this.AlloDiscount=AlloDiscount;
	}
	public Long getAlloDiscount(){
		return AlloDiscount;
	}
	public void setAlloPeroid(Integer AlloPeroid){
	this.AlloPeroid=AlloPeroid;
	}
	public Integer getAlloPeroid(){
		return AlloPeroid;
	}
	public void setWhid(String Whid){
	this.Whid=Whid;
	}
	public String getWhid(){
		return Whid;
	}
	public void setSprice_quotiety(Long Sprice_quotiety){
	this.Sprice_quotiety=Sprice_quotiety;
	}
	public Long getSprice_quotiety(){
		return Sprice_quotiety;
	}
	public void setMprice_quotiety(Long Mprice_quotiety){
	this.Mprice_quotiety=Mprice_quotiety;
	}
	public Long getMprice_quotiety(){
		return Mprice_quotiety;
	}
	public void setVehicleNum(Integer VehicleNum){
	this.VehicleNum=VehicleNum;
	}
	public Integer getVehicleNum(){
		return VehicleNum;
	}
	public void setEmpCount(Integer EmpCount){
	this.EmpCount=EmpCount;
	}
	public Integer getEmpCount(){
		return EmpCount;
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
	public void setFlag1(String Flag1){
	this.Flag1=Flag1;
	}
	public String getFlag1(){
		return Flag1;
	}
	public void setFlag2(String Flag2){
	this.Flag2=Flag2;
	}
	public String getFlag2(){
		return Flag2;
	}
	public void setUsedate(Date usedate){
	this.usedate=usedate;
	}
	public Date getUsedate(){
		return usedate;
	}
	public void setSerialno(String serialno){
	this.serialno=serialno;
	}
	public String getSerialno(){
		return serialno;
	}
	public void setSerialno1(String serialno1){
	this.serialno1=serialno1;
	}
	public String getSerialno1(){
		return serialno1;
	}
	public void setCstore(String cstore){
	this.cstore=cstore;
	}
	public String getCstore(){
		return cstore;
	}
	public void setPosNum(Integer PosNum){
	this.PosNum=PosNum;
	}
	public Integer getPosNum(){
		return PosNum;
	}
	public void setShoworderprice(String showorderprice){
	this.showorderprice=showorderprice;
	}
	public String getShoworderprice(){
		return showorderprice;
	}
	public void setShowreceiptamt(String showreceiptamt){
	this.showreceiptamt=showreceiptamt;
	}
	public String getShowreceiptamt(){
		return showreceiptamt;
	}
	public void setShowprofit(String showprofit){
	this.showprofit=showprofit;
	}
	public String getShowprofit(){
		return showprofit;
	}
	public void setDmCostFlag(String DmCostFlag){
	this.DmCostFlag=DmCostFlag;
	}
	public String getDmCostFlag(){
		return DmCostFlag;
	}
	public void setIsOpen(String IsOpen){
	this.IsOpen=IsOpen;
	}
	public String getIsOpen(){
		return IsOpen;
	}
}

