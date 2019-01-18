package personal.wl.mobilepointapp.entity.pos;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

/**
 * Entity mapped to table "PRODUCT".
 */
@Entity
public class Product implements Serializable {

    @Id(autoincrement = true)
    private Long id;
    private String Proid;
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
    private Double PacketQty;
    private String TaxType;
    private Double InTax;
    private Double TaxPrice;
    private Double InPrice;
    private Double MinOrderQty;
    private Double NormalPrice;
    private Double MemberPrice;
    private Double GroupPrice;
    private Double ReturnRat;
    private Double vipdiscount;
    private Double posdiscount;
    private String Status;
    private String Operatorid;
    private java.util.Date CreateDate;
    private java.util.Date UpdateDate;
    private java.util.Date stopdate;
    private Long TimeStamp;

    @Generated
    public Product() {
    }

    public Product(Long id) {
        this.id = id;
    }

    @Generated
    public Product(Long id, String Proid, String Barcode, String ProName, String ProSName, String ClassId, String Spec, String BrandId, String StatId, String Grade, String Area, String SupId, String MeasureId, Double PacketQty, String TaxType, Double InTax, Double TaxPrice, Double InPrice, Double MinOrderQty, Double NormalPrice, Double MemberPrice, Double GroupPrice, Double ReturnRat, Double vipdiscount, Double posdiscount, String Status, String Operatorid, java.util.Date CreateDate, java.util.Date UpdateDate, java.util.Date stopdate, Long TimeStamp) {
        this.id = id;
        this.Proid = Proid;
        this.Barcode = Barcode;
        this.ProName = ProName;
        this.ProSName = ProSName;
        this.ClassId = ClassId;
        this.Spec = Spec;
        this.BrandId = BrandId;
        this.StatId = StatId;
        this.Grade = Grade;
        this.Area = Area;
        this.SupId = SupId;
        this.MeasureId = MeasureId;
        this.PacketQty = PacketQty;
        this.TaxType = TaxType;
        this.InTax = InTax;
        this.TaxPrice = TaxPrice;
        this.InPrice = InPrice;
        this.MinOrderQty = MinOrderQty;
        this.NormalPrice = NormalPrice;
        this.MemberPrice = MemberPrice;
        this.GroupPrice = GroupPrice;
        this.ReturnRat = ReturnRat;
        this.vipdiscount = vipdiscount;
        this.posdiscount = posdiscount;
        this.Status = Status;
        this.Operatorid = Operatorid;
        this.CreateDate = CreateDate;
        this.UpdateDate = UpdateDate;
        this.stopdate = stopdate;
        this.TimeStamp = TimeStamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProid() {
        return Proid;
    }

    public void setProid(String Proid) {
        this.Proid = Proid;
    }

    public String getBarcode() {
        return Barcode;
    }

    public void setBarcode(String Barcode) {
        this.Barcode = Barcode;
    }

    public String getProName() {
        return ProName;
    }

    public void setProName(String ProName) {
        this.ProName = ProName;
    }

    public String getProSName() {
        return ProSName;
    }

    public void setProSName(String ProSName) {
        this.ProSName = ProSName;
    }

    public String getClassId() {
        return ClassId;
    }

    public void setClassId(String ClassId) {
        this.ClassId = ClassId;
    }

    public String getSpec() {
        return Spec;
    }

    public void setSpec(String Spec) {
        this.Spec = Spec;
    }

    public String getBrandId() {
        return BrandId;
    }

    public void setBrandId(String BrandId) {
        this.BrandId = BrandId;
    }

    public String getStatId() {
        return StatId;
    }

    public void setStatId(String StatId) {
        this.StatId = StatId;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String Grade) {
        this.Grade = Grade;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String Area) {
        this.Area = Area;
    }

    public String getSupId() {
        return SupId;
    }

    public void setSupId(String SupId) {
        this.SupId = SupId;
    }

    public String getMeasureId() {
        return MeasureId;
    }

    public void setMeasureId(String MeasureId) {
        this.MeasureId = MeasureId;
    }

    public Double getPacketQty() {
        return PacketQty;
    }

    public void setPacketQty(Double PacketQty) {
        this.PacketQty = PacketQty;
    }

    public String getTaxType() {
        return TaxType;
    }

    public void setTaxType(String TaxType) {
        this.TaxType = TaxType;
    }

    public Double getInTax() {
        return InTax;
    }

    public void setInTax(Double InTax) {
        this.InTax = InTax;
    }

    public Double getTaxPrice() {
        return TaxPrice;
    }

    public void setTaxPrice(Double TaxPrice) {
        this.TaxPrice = TaxPrice;
    }

    public Double getInPrice() {
        return InPrice;
    }

    public void setInPrice(Double InPrice) {
        this.InPrice = InPrice;
    }

    public Double getMinOrderQty() {
        return MinOrderQty;
    }

    public void setMinOrderQty(Double MinOrderQty) {
        this.MinOrderQty = MinOrderQty;
    }

    public Double getNormalPrice() {
        return NormalPrice;
    }

    public void setNormalPrice(Double NormalPrice) {
        this.NormalPrice = NormalPrice;
    }

    public Double getMemberPrice() {
        return MemberPrice;
    }

    public void setMemberPrice(Double MemberPrice) {
        this.MemberPrice = MemberPrice;
    }

    public Double getGroupPrice() {
        return GroupPrice;
    }

    public void setGroupPrice(Double GroupPrice) {
        this.GroupPrice = GroupPrice;
    }

    public Double getReturnRat() {
        return ReturnRat;
    }

    public void setReturnRat(Double ReturnRat) {
        this.ReturnRat = ReturnRat;
    }

    public Double getVipdiscount() {
        return vipdiscount;
    }

    public void setVipdiscount(Double vipdiscount) {
        this.vipdiscount = vipdiscount;
    }

    public Double getPosdiscount() {
        return posdiscount;
    }

    public void setPosdiscount(Double posdiscount) {
        this.posdiscount = posdiscount;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getOperatorid() {
        return Operatorid;
    }

    public void setOperatorid(String Operatorid) {
        this.Operatorid = Operatorid;
    }

    public java.util.Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(java.util.Date CreateDate) {
        this.CreateDate = CreateDate;
    }

    public java.util.Date getUpdateDate() {
        return UpdateDate;
    }

    public void setUpdateDate(java.util.Date UpdateDate) {
        this.UpdateDate = UpdateDate;
    }

    public java.util.Date getStopdate() {
        return stopdate;
    }

    public void setStopdate(java.util.Date stopdate) {
        this.stopdate = stopdate;
    }

    public Long getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(Long TimeStamp) {
        this.TimeStamp = TimeStamp;
    }

}
