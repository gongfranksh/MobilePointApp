package personal.wl.mobilepointapp.entity.pos;

import java.util.Date;

/**
    * @filename:order_detail.java
    * @create date:2019-01-08 15:58:20
    * @creater:weiliang 
    * @file desc:order_detail entity
    * @file version:V0.01 
    */ 


public class OrderDetail {
	private String OrderId;
	private String ProId;
	private String Barcode;
	private String classid;
	private Long normalprice;
	private String UnitId;
	private Long OrderQty;
	private Long OrderPrice;
	private Long GifQty;
	private Long OrderTax;
	private Long SupplierQty;
	private Long ReceiptQty;
	private Long ReceiptPrice;
	private Long ReceiptGifQty;
	private Long ReceiptTax;
	private Long ReceiptAmt;
	private Long PacketQty;
	private Date ProductDate;
	private Date WarrantyDate;
	private Long MinOrderQty;
	private Long OrderMultiplier;
	private String BoxCode;
	private Integer FLowId;
	public void setOrderId(String OrderId){
	this.OrderId=OrderId;
	}
	public String getOrderId(){
		return OrderId;
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
	public void setClassid(String classid){
	this.classid=classid;
	}
	public String getClassid(){
		return classid;
	}
	public void setNormalprice(Long normalprice){
	this.normalprice=normalprice;
	}
	public Long getNormalprice(){
		return normalprice;
	}
	public void setUnitId(String UnitId){
	this.UnitId=UnitId;
	}
	public String getUnitId(){
		return UnitId;
	}
	public void setOrderQty(Long OrderQty){
	this.OrderQty=OrderQty;
	}
	public Long getOrderQty(){
		return OrderQty;
	}
	public void setOrderPrice(Long OrderPrice){
	this.OrderPrice=OrderPrice;
	}
	public Long getOrderPrice(){
		return OrderPrice;
	}
	public void setGifQty(Long GifQty){
	this.GifQty=GifQty;
	}
	public Long getGifQty(){
		return GifQty;
	}
	public void setOrderTax(Long OrderTax){
	this.OrderTax=OrderTax;
	}
	public Long getOrderTax(){
		return OrderTax;
	}
	public void setSupplierQty(Long SupplierQty){
	this.SupplierQty=SupplierQty;
	}
	public Long getSupplierQty(){
		return SupplierQty;
	}
	public void setReceiptQty(Long ReceiptQty){
	this.ReceiptQty=ReceiptQty;
	}
	public Long getReceiptQty(){
		return ReceiptQty;
	}
	public void setReceiptPrice(Long ReceiptPrice){
	this.ReceiptPrice=ReceiptPrice;
	}
	public Long getReceiptPrice(){
		return ReceiptPrice;
	}
	public void setReceiptGifQty(Long ReceiptGifQty){
	this.ReceiptGifQty=ReceiptGifQty;
	}
	public Long getReceiptGifQty(){
		return ReceiptGifQty;
	}
	public void setReceiptTax(Long ReceiptTax){
	this.ReceiptTax=ReceiptTax;
	}
	public Long getReceiptTax(){
		return ReceiptTax;
	}
	public void setReceiptAmt(Long ReceiptAmt){
	this.ReceiptAmt=ReceiptAmt;
	}
	public Long getReceiptAmt(){
		return ReceiptAmt;
	}
	public void setPacketQty(Long PacketQty){
	this.PacketQty=PacketQty;
	}
	public Long getPacketQty(){
		return PacketQty;
	}
	public void setProductDate(Date ProductDate){
	this.ProductDate=ProductDate;
	}
	public Date getProductDate(){
		return ProductDate;
	}
	public void setWarrantyDate(Date WarrantyDate){
	this.WarrantyDate=WarrantyDate;
	}
	public Date getWarrantyDate(){
		return WarrantyDate;
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
	public void setBoxCode(String BoxCode){
	this.BoxCode=BoxCode;
	}
	public String getBoxCode(){
		return BoxCode;
	}
	public void setFLowId(Integer FLowId){
	this.FLowId=FLowId;
	}
	public Integer getFLowId(){
		return FLowId;
	}
}

