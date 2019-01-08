package personal.wl.mobilepointapp.entity;
import java.sql.*;

   /**
    * @filename:promote_branch_rel.java
    * @create date:2019-01-08 14:39:41
    * @creater:weiliang 
    * @file desc:promote_branch_rel entity
    * @file version:V0.01 
    */ 


public class Promote_branch_rel{
	private String Dmid;
	private String BraId;
	private String Operatorid;
	private Date createdate;
	private Date updatedate;
	private String remarks;
	private Integer timestamp;
	public void setDmid(String Dmid){
	this.Dmid=Dmid;
	}
	public String getDmid(){
		return Dmid;
	}
	public void setBraId(String BraId){
	this.BraId=BraId;
	}
	public String getBraId(){
		return BraId;
	}
	public void setOperatorid(String Operatorid){
	this.Operatorid=Operatorid;
	}
	public String getOperatorid(){
		return Operatorid;
	}
	public void setCreatedate(Date createdate){
	this.createdate=createdate;
	}
	public Date getCreatedate(){
		return createdate;
	}
	public void setUpdatedate(Date updatedate){
	this.updatedate=updatedate;
	}
	public Date getUpdatedate(){
		return updatedate;
	}
	public void setRemarks(String remarks){
	this.remarks=remarks;
	}
	public String getRemarks(){
		return remarks;
	}
	public void setTimestamp(Integer timestamp){
	this.timestamp=timestamp;
	}
	public Integer getTimestamp(){
		return timestamp;
	}
}

