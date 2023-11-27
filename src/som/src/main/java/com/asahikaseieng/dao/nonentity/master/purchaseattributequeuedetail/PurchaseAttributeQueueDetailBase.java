/*
 * Created on 2009/05/08
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.purchaseattributequeuedetail;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * PurchaseAttributeQueueDetailクラス.
 * @author t0011036
 */
public class PurchaseAttributeQueueDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public PurchaseAttributeQueueDetailBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション version */
	public static final String version_COLUMN = "VERSION";

	/** COLUMNアノテーション purchaseStatus */
	public static final String purchaseStatus_COLUMN = "PURCHASE_STATUS";

	/** COLUMNアノテーション materialMakerName */
	public static final String materialMakerName_COLUMN = "MATERIAL_MAKER_NAME";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション venderName1 */
	public static final String venderName1_COLUMN = "VENDER_NAME1";

	/** COLUMNアノテーション deliveryCd */
	public static final String deliveryCd_COLUMN = "DELIVERY_CD";

	/** COLUMNアノテーション deliveryName1 */
	public static final String deliveryName1_COLUMN = "DELIVERY_NAME1";

	/** COLUMNアノテーション purchaseLeadTime */
	public static final String purchaseLeadTime_COLUMN = "PURCHASE_LEAD_TIME";

	/** COLUMNアノテーション purchasePrice */
	public static final String purchasePrice_COLUMN = "PURCHASE_PRICE";

	/** COLUMNアノテーション purchaseTaxDivision */
	public static final String purchaseTaxDivision_COLUMN = "PURCHASE_TAX_DIVISION";

	/** COLUMNアノテーション purchaseTrigger */
	public static final String purchaseTrigger_COLUMN = "PURCHASE_TRIGGER";

	/** COLUMNアノテーション multiSupplierDivision */
	public static final String multiSupplierDivision_COLUMN = "MULTI_SUPPLIER_DIVISION";

	/** COLUMNアノテーション purchaseOrderPoint */
	public static final String purchaseOrderPoint_COLUMN = "PURCHASE_ORDER_POINT";

	/** COLUMNアノテーション orderQty */
	public static final String orderQty_COLUMN = "ORDER_QTY";

	/** COLUMNアノテーション inspectionType */
	public static final String inspectionType_COLUMN = "INSPECTION_TYPE";

	/** COLUMNアノテーション purchasePriceCalcDivision */
	public static final String purchasePriceCalcDivision_COLUMN = "PURCHASE_PRICE_CALC_DIVISION";

	/** COLUMNアノテーション suppliedGoodsDivision */
	public static final String suppliedGoodsDivision_COLUMN = "SUPPLIED_GOODS_DIVISION";

	/** COLUMNアノテーション leaseDrumFlag */
	public static final String leaseDrumFlag_COLUMN = "LEASE_DRUM_FLAG";

	/** COLUMNアノテーション lorryDivision */
	public static final String lorryDivision_COLUMN = "LORRY_DIVISION";

	/** COLUMNアノテーション purchaseSectionCd */
	public static final String purchaseSectionCd_COLUMN = "PURCHASE_SECTION_CD";

	/** COLUMNアノテーション purchaseSectionName */
	public static final String purchaseSectionName_COLUMN = "PURCHASE_SECTION_NAME";

	/** COLUMNアノテーション purchaseAccountsCd */
	public static final String purchaseAccountsCd_COLUMN = "PURCHASE_ACCOUNTS_CD";

	/** COLUMNアノテーション purchaseAccountsName */
	public static final String purchaseAccountsName_COLUMN = "PURCHASE_ACCOUNTS_NAME";

	/** COLUMNアノテーション purchaseUpdateDate */
	public static final String purchaseUpdateDate_COLUMN = "PURCHASE_UPDATE_DATE";

	//
	// インスタンスフィールド
	//

	private String itemCd;

	private java.math.BigDecimal version;

	private java.math.BigDecimal purchaseStatus;

	private String materialMakerName;

	private String venderCd;

	private String venderName1;

	private String deliveryCd;

	private String deliveryName1;

	private java.math.BigDecimal purchaseLeadTime;

	private java.math.BigDecimal purchasePrice;

	private java.math.BigDecimal purchaseTaxDivision;

	private java.math.BigDecimal purchaseTrigger;

	private java.math.BigDecimal multiSupplierDivision;

	private java.math.BigDecimal purchaseOrderPoint;

	private java.math.BigDecimal orderQty;

	private java.math.BigDecimal inspectionType;

	private java.math.BigDecimal purchasePriceCalcDivision;

	private String suppliedGoodsDivision;

	private java.math.BigDecimal leaseDrumFlag;

	private java.math.BigDecimal lorryDivision;

	private String purchaseSectionCd;

	private String purchaseSectionName;

	private String purchaseAccountsCd;

	private String purchaseAccountsName;

	private java.sql.Timestamp purchaseUpdateDate;

	//
	// インスタンスメソッド
	//

	/**
	 * itemCd取得.
	 * @return itemCd
	 */
	public String getItemCd() {
		return this.itemCd;
	}

	/**
	 * itemCd設定.
	 * @param itemCd itemCd
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * version取得.
	 * @return version
	 */
	public java.math.BigDecimal getVersion() {
		return this.version;
	}

	/**
	 * version設定.
	 * @param version version
	 */
	public void setVersion(final java.math.BigDecimal version) {
		this.version = version;
	}

	/**
	 * purchaseStatus取得.
	 * @return purchaseStatus
	 */
	public java.math.BigDecimal getPurchaseStatus() {
		return this.purchaseStatus;
	}

	/**
	 * purchaseStatus設定.
	 * @param purchaseStatus purchaseStatus
	 */
	public void setPurchaseStatus(final java.math.BigDecimal purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}

	/**
	 * materialMakerName取得.
	 * @return materialMakerName
	 */
	public String getMaterialMakerName() {
		return this.materialMakerName;
	}

	/**
	 * materialMakerName設定.
	 * @param materialMakerName materialMakerName
	 */
	public void setMaterialMakerName(final String materialMakerName) {
		this.materialMakerName = materialMakerName;
	}

	/**
	 * venderCd取得.
	 * @return venderCd
	 */
	public String getVenderCd() {
		return this.venderCd;
	}

	/**
	 * venderCd設定.
	 * @param venderCd venderCd
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * venderName1取得.
	 * @return venderName1
	 */
	public String getVenderName1() {
		return this.venderName1;
	}

	/**
	 * venderName1設定.
	 * @param venderName1 venderName1
	 */
	public void setVenderName1(final String venderName1) {
		this.venderName1 = venderName1;
	}

	/**
	 * deliveryCd取得.
	 * @return deliveryCd
	 */
	public String getDeliveryCd() {
		return this.deliveryCd;
	}

	/**
	 * deliveryCd設定.
	 * @param deliveryCd deliveryCd
	 */
	public void setDeliveryCd(final String deliveryCd) {
		this.deliveryCd = deliveryCd;
	}

	/**
	 * deliveryName1取得.
	 * @return deliveryName1
	 */
	public String getDeliveryName1() {
		return this.deliveryName1;
	}

	/**
	 * deliveryName1設定.
	 * @param deliveryName1 deliveryName1
	 */
	public void setDeliveryName1(final String deliveryName1) {
		this.deliveryName1 = deliveryName1;
	}

	/**
	 * purchaseLeadTime取得.
	 * @return purchaseLeadTime
	 */
	public java.math.BigDecimal getPurchaseLeadTime() {
		return this.purchaseLeadTime;
	}

	/**
	 * purchaseLeadTime設定.
	 * @param purchaseLeadTime purchaseLeadTime
	 */
	public void setPurchaseLeadTime(final java.math.BigDecimal purchaseLeadTime) {
		this.purchaseLeadTime = purchaseLeadTime;
	}

	/**
	 * purchasePrice取得.
	 * @return purchasePrice
	 */
	public java.math.BigDecimal getPurchasePrice() {
		return this.purchasePrice;
	}

	/**
	 * purchasePrice設定.
	 * @param purchasePrice purchasePrice
	 */
	public void setPurchasePrice(final java.math.BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	/**
	 * purchaseTaxDivision取得.
	 * @return purchaseTaxDivision
	 */
	public java.math.BigDecimal getPurchaseTaxDivision() {
		return this.purchaseTaxDivision;
	}

	/**
	 * purchaseTaxDivision設定.
	 * @param purchaseTaxDivision purchaseTaxDivision
	 */
	public void setPurchaseTaxDivision(final java.math.BigDecimal purchaseTaxDivision) {
		this.purchaseTaxDivision = purchaseTaxDivision;
	}

	/**
	 * purchaseTrigger取得.
	 * @return purchaseTrigger
	 */
	public java.math.BigDecimal getPurchaseTrigger() {
		return this.purchaseTrigger;
	}

	/**
	 * purchaseTrigger設定.
	 * @param purchaseTrigger purchaseTrigger
	 */
	public void setPurchaseTrigger(final java.math.BigDecimal purchaseTrigger) {
		this.purchaseTrigger = purchaseTrigger;
	}

	/**
	 * multiSupplierDivision取得.
	 * @return multiSupplierDivision
	 */
	public java.math.BigDecimal getMultiSupplierDivision() {
		return this.multiSupplierDivision;
	}

	/**
	 * multiSupplierDivision設定.
	 * @param multiSupplierDivision multiSupplierDivision
	 */
	public void setMultiSupplierDivision(final java.math.BigDecimal multiSupplierDivision) {
		this.multiSupplierDivision = multiSupplierDivision;
	}

	/**
	 * purchaseOrderPoint取得.
	 * @return purchaseOrderPoint
	 */
	public java.math.BigDecimal getPurchaseOrderPoint() {
		return this.purchaseOrderPoint;
	}

	/**
	 * purchaseOrderPoint設定.
	 * @param purchaseOrderPoint purchaseOrderPoint
	 */
	public void setPurchaseOrderPoint(final java.math.BigDecimal purchaseOrderPoint) {
		this.purchaseOrderPoint = purchaseOrderPoint;
	}

	/**
	 * orderQty取得.
	 * @return orderQty
	 */
	public java.math.BigDecimal getOrderQty() {
		return this.orderQty;
	}

	/**
	 * orderQty設定.
	 * @param orderQty orderQty
	 */
	public void setOrderQty(final java.math.BigDecimal orderQty) {
		this.orderQty = orderQty;
	}

	/**
	 * inspectionType取得.
	 * @return inspectionType
	 */
	public java.math.BigDecimal getInspectionType() {
		return this.inspectionType;
	}

	/**
	 * inspectionType設定.
	 * @param inspectionType inspectionType
	 */
	public void setInspectionType(final java.math.BigDecimal inspectionType) {
		this.inspectionType = inspectionType;
	}

	/**
	 * purchasePriceCalcDivision取得.
	 * @return purchasePriceCalcDivision
	 */
	public java.math.BigDecimal getPurchasePriceCalcDivision() {
		return this.purchasePriceCalcDivision;
	}

	/**
	 * purchasePriceCalcDivision設定.
	 * @param purchasePriceCalcDivision purchasePriceCalcDivision
	 */
	public void setPurchasePriceCalcDivision(final java.math.BigDecimal purchasePriceCalcDivision) {
		this.purchasePriceCalcDivision = purchasePriceCalcDivision;
	}

	/**
	 * suppliedGoodsDivision取得.
	 * @return suppliedGoodsDivision
	 */
	public String getSuppliedGoodsDivision() {
		return this.suppliedGoodsDivision;
	}

	/**
	 * suppliedGoodsDivision設定.
	 * @param suppliedGoodsDivision suppliedGoodsDivision
	 */
	public void setSuppliedGoodsDivision(final String suppliedGoodsDivision) {
		this.suppliedGoodsDivision = suppliedGoodsDivision;
	}

	/**
	 * leaseDrumFlag取得.
	 * @return leaseDrumFlag
	 */
	public java.math.BigDecimal getLeaseDrumFlag() {
		return this.leaseDrumFlag;
	}

	/**
	 * leaseDrumFlag設定.
	 * @param leaseDrumFlag leaseDrumFlag
	 */
	public void setLeaseDrumFlag(final java.math.BigDecimal leaseDrumFlag) {
		this.leaseDrumFlag = leaseDrumFlag;
	}

	/**
	 * lorryDivision取得.
	 * @return lorryDivision
	 */
	public java.math.BigDecimal getLorryDivision() {
		return this.lorryDivision;
	}

	/**
	 * lorryDivision設定.
	 * @param lorryDivision lorryDivision
	 */
	public void setLorryDivision(final java.math.BigDecimal lorryDivision) {
		this.lorryDivision = lorryDivision;
	}

	/**
	 * purchaseSectionCd取得.
	 * @return purchaseSectionCd
	 */
	public String getPurchaseSectionCd() {
		return this.purchaseSectionCd;
	}

	/**
	 * purchaseSectionCd設定.
	 * @param purchaseSectionCd purchaseSectionCd
	 */
	public void setPurchaseSectionCd(final String purchaseSectionCd) {
		this.purchaseSectionCd = purchaseSectionCd;
	}

	/**
	 * purchaseSectionName取得.
	 * @return purchaseSectionName
	 */
	public String getPurchaseSectionName() {
		return this.purchaseSectionName;
	}

	/**
	 * purchaseSectionName設定.
	 * @param purchaseSectionName purchaseSectionName
	 */
	public void setPurchaseSectionName(final String purchaseSectionName) {
		this.purchaseSectionName = purchaseSectionName;
	}

	/**
	 * purchaseAccountsCd取得.
	 * @return purchaseAccountsCd
	 */
	public String getPurchaseAccountsCd() {
		return this.purchaseAccountsCd;
	}

	/**
	 * purchaseAccountsCd設定.
	 * @param purchaseAccountsCd purchaseAccountsCd
	 */
	public void setPurchaseAccountsCd(final String purchaseAccountsCd) {
		this.purchaseAccountsCd = purchaseAccountsCd;
	}

	/**
	 * purchaseAccountsName取得.
	 * @return purchaseAccountsName
	 */
	public String getPurchaseAccountsName() {
		return this.purchaseAccountsName;
	}

	/**
	 * purchaseAccountsName設定.
	 * @param purchaseAccountsName purchaseAccountsName
	 */
	public void setPurchaseAccountsName(final String purchaseAccountsName) {
		this.purchaseAccountsName = purchaseAccountsName;
	}

	/**
	 * purchaseUpdateDate取得.
	 * @return purchaseUpdateDate
	 */
	public java.sql.Timestamp getPurchaseUpdateDate() {
		return this.purchaseUpdateDate;
	}

	/**
	 * purchaseUpdateDate設定.
	 * @param purchaseUpdateDate purchaseUpdateDate
	 */
	public void setPurchaseUpdateDate(final java.sql.Timestamp purchaseUpdateDate) {
		this.purchaseUpdateDate = purchaseUpdateDate;
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}

