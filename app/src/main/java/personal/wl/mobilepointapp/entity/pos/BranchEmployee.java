package personal.wl.mobilepointapp.entity.pos;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

/**
 * Entity mapped to table "BRANCH_EMPLOYEE".
 */
@Entity
public class BranchEmployee implements Serializable {

    @Id(autoincrement = true)
    private Long id;
    private String Braid;
    private String Empid;
    private String EmpName;
    private String Password;
    private String Domain;
    private Double Discount;
    private String Status;
    private String Pinyin;
    private Long timestamp;

    @Generated
    public BranchEmployee() {
    }

    public BranchEmployee(Long id) {
        this.id = id;
    }

    @Generated
    public BranchEmployee(Long id, String Braid, String Empid, String EmpName, String Password, Double Discount, String Status, Long timestamp) {
        this.id = id;
        this.Braid = Braid;
        this.Empid = Empid;
        this.EmpName = EmpName;
        this.Password = Password;
        this.Discount = Discount;
        this.Status = Status;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBraid() {
        return Braid;
    }

    public void setBraid(String Braid) {
        this.Braid = Braid;
    }

    public String getEmpid() {
        return Empid;
    }

    public void setEmpid(String Empid) {
        this.Empid = Empid;
    }

    public String getEmpName() {
        return EmpName;
    }

    public void setEmpName(String EmpName) {
        this.EmpName = EmpName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public Double getDiscount() {
        return Discount;
    }

    public void setDiscount(Double Discount) {
        this.Discount = Discount;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getDomain() {
        return Domain;
    }

    public void setDomain(String domain) {
        Domain = domain;
    }

    public String getPinyin() {
        return Pinyin;
    }

    public void setPinyin(String pinyin) {
        Pinyin = pinyin;
    }
}
