package personal.wl.mobilepointapp.entity.pos;
import java.sql.*;

   /**
    * @filename:product_barcode.java
    * @create date:2019-01-08 14:38:18
    * @creater:weiliang 
    * @file desc:product_barcode entity
    * @file version:V0.01 
    */ 


public class Product_barcode{
	private String ProId;
	private String BarCode;
	private String Barmode;
	private Long quantity;
	private Long NormalPrice;
	private Long MemberPrice;
	private String MainFlag;
	private Date CreateDate;
	private Date UpdateDate;
	private String Spec;
	private String Operatorid;
	private Integer timestamp;
	public void setProId(String ProId){
	this.ProId=ProId;
	}
	public String getProId(){
		return ProId;
	}
	public void setBarCode(String BarCode){
	this.BarCode=BarCode;
	}
	public String getBarCode(){
		return BarCode;
	}
	public void setBarmode(String Barmode){
	this.Barmode=Barmode;
	}
	public String getBarmode(){
		return Barmode;
	}
	public void setQuantity(Long quantity){
	this.quantity=quantity;
	}
	public Long getQuantity(){
		return quantity;
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
	public void setMainFlag(String MainFlag){
	this.MainFlag=MainFlag;
	}
	public String getMainFlag(){
		return MainFlag;
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
	public void setSpec(String Spec){
	this.Spec=Spec;
	}
	public String getSpec(){
		return Spec;
	}
	public void setOperatorid(String Operatorid){
	this.Operatorid=Operatorid;
	}
	public String getOperatorid(){
		return Operatorid;
	}
	public void setTimestamp(Integer timestamp){
	this.timestamp=timestamp;
	}
	public Integer getTimestamp(){
		return timestamp;
	}
}

