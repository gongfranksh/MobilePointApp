package personal.wl.mobilepointapp.entity.pos;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

/**
 * Entity mapped to table "PMT_DM_REL".
 */
@Entity
public class PmtDmRel {

    @Id(autoincrement = true)
    private Long id;
    private String Braid;
    private String DMId;
    private java.util.Date DMBeginDate;
    private java.util.Date DMEndDate;
    private String SupId;
    private String Proid;
    private Double OrigSalePrice;
    private Double SalePrice;
    private Long TimeStamp;

    @Generated
    public PmtDmRel() {
    }

    public PmtDmRel(Long id) {
        this.id = id;
    }

    @Generated
    public PmtDmRel(Long id, String Braid, String DMId, java.util.Date DMBeginDate, java.util.Date DMEndDate, String SupId, String Proid, Double OrigSalePrice, Double SalePrice, Long TimeStamp) {
        this.id = id;
        this.Braid = Braid;
        this.DMId = DMId;
        this.DMBeginDate = DMBeginDate;
        this.DMEndDate = DMEndDate;
        this.SupId = SupId;
        this.Proid = Proid;
        this.OrigSalePrice = OrigSalePrice;
        this.SalePrice = SalePrice;
        this.TimeStamp = TimeStamp;
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

    public String getDMId() {
        return DMId;
    }

    public void setDMId(String DMId) {
        this.DMId = DMId;
    }

    public java.util.Date getDMBeginDate() {
        return DMBeginDate;
    }

    public void setDMBeginDate(java.util.Date DMBeginDate) {
        this.DMBeginDate = DMBeginDate;
    }

    public java.util.Date getDMEndDate() {
        return DMEndDate;
    }

    public void setDMEndDate(java.util.Date DMEndDate) {
        this.DMEndDate = DMEndDate;
    }

    public String getSupId() {
        return SupId;
    }

    public void setSupId(String SupId) {
        this.SupId = SupId;
    }

    public String getProid() {
        return Proid;
    }

    public void setProid(String Proid) {
        this.Proid = Proid;
    }

    public Double getOrigSalePrice() {
        return OrigSalePrice;
    }

    public void setOrigSalePrice(Double OrigSalePrice) {
        this.OrigSalePrice = OrigSalePrice;
    }

    public Double getSalePrice() {
        return SalePrice;
    }

    public void setSalePrice(Double SalePrice) {
        this.SalePrice = SalePrice;
    }

    public Long getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(Long TimeStamp) {
        this.TimeStamp = TimeStamp;
    }

}