package personal.wl.mobilepointapp.entity.pos;

import java.sql.*;

   /**
    * @filename:branch_employee.java
    * @create date:2019-01-08 15:53:02
    * @creater:weiliang 
    * @file desc:branch_employee entity
    * @file version:V0.01 
    */ 


public class Branch_employee{
	private String BraId;
	private String EmpId;
	private String EmpName;
	private String Sex;
	private Date Birthday;
	private String Addr;
	private String Tel;
	private String Mobile;
	private Date WorkDate;
	private Date LeaveDate;
	private String EmpType;
	private String DeptId;
	private String Password;
	private String Authority;
	private String Status;
	private Date CreateDate;
	private Integer timestamp;
	private String domainaccounts;
	private Long discount;
	public void setBraId(String BraId){
	this.BraId=BraId;
	}
	public String getBraId(){
		return BraId;
	}
	public void setEmpId(String EmpId){
	this.EmpId=EmpId;
	}
	public String getEmpId(){
		return EmpId;
	}
	public void setEmpName(String EmpName){
	this.EmpName=EmpName;
	}
	public String getEmpName(){
		return EmpName;
	}
	public void setSex(String Sex){
	this.Sex=Sex;
	}
	public String getSex(){
		return Sex;
	}
	public void setBirthday(Date Birthday){
	this.Birthday=Birthday;
	}
	public Date getBirthday(){
		return Birthday;
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
	public void setMobile(String Mobile){
	this.Mobile=Mobile;
	}
	public String getMobile(){
		return Mobile;
	}
	public void setWorkDate(Date WorkDate){
	this.WorkDate=WorkDate;
	}
	public Date getWorkDate(){
		return WorkDate;
	}
	public void setLeaveDate(Date LeaveDate){
	this.LeaveDate=LeaveDate;
	}
	public Date getLeaveDate(){
		return LeaveDate;
	}
	public void setEmpType(String EmpType){
	this.EmpType=EmpType;
	}
	public String getEmpType(){
		return EmpType;
	}
	public void setDeptId(String DeptId){
	this.DeptId=DeptId;
	}
	public String getDeptId(){
		return DeptId;
	}
	public void setPassword(String Password){
	this.Password=Password;
	}
	public String getPassword(){
		return Password;
	}
	public void setAuthority(String Authority){
	this.Authority=Authority;
	}
	public String getAuthority(){
		return Authority;
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
	public void setTimestamp(Integer timestamp){
	this.timestamp=timestamp;
	}
	public Integer getTimestamp(){
		return timestamp;
	}
	public void setDomainaccounts(String domainaccounts){
	this.domainaccounts=domainaccounts;
	}
	public String getDomainaccounts(){
		return domainaccounts;
	}
	public void setDiscount(Long discount){
	this.discount=discount;
	}
	public Long getDiscount(){
		return discount;
	}
}

