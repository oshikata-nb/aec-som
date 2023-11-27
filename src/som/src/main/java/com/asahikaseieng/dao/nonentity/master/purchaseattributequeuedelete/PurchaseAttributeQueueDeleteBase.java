/*
 * Created on 2009/05/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.purchaseattributequeuedelete;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * PurchaseAttributeQueueDeleteクラス.
 * @author t0011036
 */
public class PurchaseAttributeQueueDeleteBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public PurchaseAttributeQueueDeleteBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション version */
	public static final String version_COLUMN = "VERSION";

	/** COLUMNアノテーション status */
	public static final String status_COLUMN = "STATUS";

	/** COLUMNアノテーション parentItemCd */
	public static final String parentItemCd_COLUMN = "PARENT_ITEM_CD";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション supplierItemCd */
	public static final String supplierItemCd_COLUMN = "SUPPLIER_ITEM_CD";

	/** COLUMNアノテーション deliveryCd */
	public static final String deliveryCd_COLUMN = "DELIVERY_CD";

	/** COLUMNアノテーション purchaseLeadTime */
	public static final String purchaseLeadTime_COLUMN = "PURCHASE_LEAD_TIME";

	/** COLUMNアノテーション safetyLeadTime */
	public static final String safetyLeadTime_COLUMN = "SAFETY_LEAD_TIME";

	/** COLUMNアノテーション priceCalcDivision */
	public static final String priceCalcDivision_COLUMN = "PRICE_CALC_DIVISION";

	/** COLUMNアノテーション purchasePrice */
	public static final String purchasePrice_COLUMN = "PURCHASE_PRICE";

	/** COLUMNアノテーション taxDivision */
	public static final String taxDivision_COLUMN = "TAX_DIVISION";

	/** COLUMNアノテーション taxRatio */
	public static final String taxRatio_COLUMN = "TAX_RATIO";

	/** COLUMNアノテーション purchaseTrigger */
	public static final String purchaseTrigger_COLUMN = "PURCHASE_TRIGGER";

	/** COLUMNアノテーション extraRatio */
	public static final String extraRatio_COLUMN = "EXTRA_RATIO";

	/** COLUMNアノテーション multiSupplierDivision */
	public static final String multiSupplierDivision_COLUMN = "MULTI_SUPPLIER_DIVISION";

	/** COLUMNアノテーション serverDivision */
	public static final String serverDivision_COLUMN = "SERVER_DIVISION";

	/** COLUMNアノテーション certificationAttach */
	public static final String certificationAttach_COLUMN = "CERTIFICATION_ATTACH";

	/** COLUMNアノテーション cockDivision */
	public static final String cockDivision_COLUMN = "COCK_DIVISION";

	/** COLUMNアノテーション inspectionCategory */
	public static final String inspectionCategory_COLUMN = "INSPECTION_CATEGORY";

	/** COLUMNアノテーション inspectionType */
	public static final String inspectionType_COLUMN = "INSPECTION_TYPE";

	/** COLUMNアノテーション expireMonths */
	public static final String expireMonths_COLUMN = "EXPIRE_MONTHS";

	/** COLUMNアノテーション contractMonths */
	public static final String contractMonths_COLUMN = "CONTRACT_MONTHS";

	/** COLUMNアノテーション materialMakerName */
	public static final String materialMakerName_COLUMN = "MATERIAL_MAKER_NAME";

	/** COLUMNアノテーション suppliedGoodsDivision */
	public static final String suppliedGoodsDivision_COLUMN = "SUPPLIED_GOODS_DIVISION";

	/** COLUMNアノテーション sectionCd */
	public static final String sectionCd_COLUMN = "SECTION_CD";

	/** COLUMNアノテーション accountsCd */
	public static final String accountsCd_COLUMN = "ACCOUNTS_CD";

	/** COLUMNアノテーション leaseDrumFlag */
	public static final String leaseDrumFlag_COLUMN = "LEASE_DRUM_FLAG";

	/** COLUMNアノテーション orderPoint */
	public static final String orderPoint_COLUMN = "ORDER_POINT";

	/** COLUMNアノテーション orderQty */
	public static final String orderQty_COLUMN = "ORDER_QTY";

	/** COLUMNアノテーション lorryDivision */
	public static final String lorryDivision_COLUMN = "LORRY_DIVISION";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	//
	// インスタンスフィールド
	//

	private String itemCd;

	private java.math.BigDecimal version;

	private java.math.BigDecimal status;

	private String parentItemCd;

	private String venderCd;

	private String supplierItemCd;

	private String deliveryCd;

	private java.math.BigDecimal purchaseLeadTime;

	private java.math.BigDecimal safetyLeadTime;

	private java.math.BigDecimal priceCalcDivision;

	private java.math.BigDecimal purchasePrice;

	private java.math.BigDecimal taxDivision;

	private java.math.BigDecimal taxRatio;

	private java.math.BigDecimal purchaseTrigger;

	private java.math.BigDecimal extraRatio;

	private java.math.BigDecimal multiSupplierDivision;

	private java.math.BigDecimal serverDivision;

	private java.math.BigDecimal certificationAttach;

	private java.math.BigDecimal cockDivision;

	private String inspectionCategory;

	private java.math.BigDecimal inspectionType;

	private java.math.BigDecimal expireMonths;

	private java.math.BigDecimal contractMonths;

	private String materialMakerName;

	private String suppliedGoodsDivision;

	private String sectionCd;

	private String accountsCd;

	private java.math.BigDecimal leaseDrumFlag;

	private java.math.BigDecimal orderPoint;

	private java.math.BigDecimal orderQty;

	private java.math.BigDecimal lorryDivision;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

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
	 * status取得.
	 * @return status
	 */
	public java.math.BigDecimal getStatus() {
		return this.status;
	}

	/**
	 * status設定.
	 * @param status status
	 */
	public void setStatus(final java.math.BigDecimal status) {
		this.status = status;
	}

	/**
	 * parentItemCd取得.
	 * @return parentItemCd
	 */
	public String getParentItemCd() {
		return this.parentItemCd;
	}

	/**
	 * parentItemCd設定.
	 * @param parentItemCd parentItemCd
	 */
	public void setParentItemCd(final String parentItemCd) {
		this.parentItemCd = parentItemCd;
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
	 * supplierItemCd取得.
	 * @return supplierItemCd
	 */
	public String getSupplierItemCd() {
		return this.supplierItemCd;
	}

	/**
	 * supplierItemCd設定.
	 * @param supplierItemCd supplierItemCd
	 */
	public void setSupplierItemCd(final String supplierItemCd) {
		this.supplierItemCd = supplierItemCd;
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
	 * safetyLeadTime取得.
	 * @return safetyLeadTime
	 */
	public java.math.BigDecimal getSafetyLeadTime() {
		return this.safetyLeadTime;
	}

	/**
	 * safetyLeadTime設定.
	 * @param safetyLeadTime safetyLeadTime
	 */
	public void setSafetyLeadTime(final java.math.BigDecimal safetyLeadTime) {
		this.safetyLeadTime = safetyLeadTime;
	}

	/**
	 * priceCalcDivision取得.
	 * @return priceCalcDivision
	 */
	public java.math.BigDecimal getPriceCalcDivision() {
		return this.priceCalcDivision;
	}

	/**
	 * priceCalcDivision設定.
	 * @param priceCalcDivision priceCalcDivision
	 */
	public void setPriceCalcDivision(final java.math.BigDecimal priceCalcDivision) {
		this.priceCalcDivision = priceCalcDivision;
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
	 * taxDivision取得.
	 * @return taxDivision
	 */
	public java.math.BigDecimal getTaxDivision() {
		return this.taxDivision;
	}

	/**
	 * taxDivision設定.
	 * @param taxDivision taxDivision
	 */
	public void setTaxDivision(final java.math.BigDecimal taxDivision) {
		this.taxDivision = taxDivision;
	}

	/**
	 * taxRatio取得.
	 * @return taxRatio
	 */
	public java.math.BigDecimal getTaxRatio() {
		return this.taxRatio;
	}

	/**
	 * taxRatio設定.
	 * @param taxRatio taxRatio
	 */
	public void setTaxRatio(final java.math.BigDecimal taxRatio) {
		this.taxRatio = taxRatio;
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
	 * extraRatio取得.
	 * @return extraRatio
	 */
	public java.math.BigDecimal getExtraRatio() {
		return this.extraRatio;
	}

	/**
	 * extraRatio設定.
	 * @param extraRatio extraRatio
	 */
	public void setExtraRatio(final java.math.BigDecimal extraRatio) {
		this.extraRatio = extraRatio;
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
	 * serverDivision取得.
	 * @return serverDivision
	 */
	public java.math.BigDecimal getServerDivision() {
		return this.serverDivision;
	}

	/**
	 * serverDivision設定.
	 * @param serverDivision serverDivision
	 */
	public void setServerDivision(final java.math.BigDecimal serverDivision) {
		this.serverDivision = serverDivision;
	}

	/**
	 * certificationAttach取得.
	 * @return certificationAttach
	 */
	public java.math.BigDecimal getCertificationAttach() {
		return this.certificationAttach;
	}

	/**
	 * certificationAttach設定.
	 * @param certificationAttach certificationAttach
	 */
	public void setCertificationAttach(final java.math.BigDecimal certificationAttach) {
		this.certificationAttach = certificationAttach;
	}

	/**
	 * cockDivision取得.
	 * @return cockDivision
	 */
	public java.math.BigDecimal getCockDivision() {
		return this.cockDivision;
	}

	/**
	 * cockDivision設定.
	 * @param cockDivision cockDivision
	 */
	public void setCockDivision(final java.math.BigDecimal cockDivision) {
		this.cockDivision = cockDivision;
	}

	/**
	 * inspectionCategory取得.
	 * @return inspectionCategory
	 */
	public String getInspectionCategory() {
		return this.inspectionCategory;
	}

	/**
	 * inspectionCategory設定.
	 * @param inspectionCategory inspectionCategory
	 */
	public void setInspectionCategory(final String inspectionCategory) {
		this.inspectionCategory = inspectionCategory;
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
	 * expireMonths取得.
	 * @return expireMonths
	 */
	public java.math.BigDecimal getExpireMonths() {
		return this.expireMonths;
	}

	/**
	 * expireMonths設定.
	 * @param expireMonths expireMonths
	 */
	public void setExpireMonths(final java.math.BigDecimal expireMonths) {
		this.expireMonths = expireMonths;
	}

	/**
	 * contractMonths取得.
	 * @return contractMonths
	 */
	public java.math.BigDecimal getContractMonths() {
		return this.contractMonths;
	}

	/**
	 * contractMonths設定.
	 * @param contractMonths contractMonths
	 */
	public void setContractMonths(final java.math.BigDecimal contractMonths) {
		this.contractMonths = contractMonths;
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
	 * sectionCd取得.
	 * @return sectionCd
	 */
	public String getSectionCd() {
		return this.sectionCd;
	}

	/**
	 * sectionCd設定.
	 * @param sectionCd sectionCd
	 */
	public void setSectionCd(final String sectionCd) {
		this.sectionCd = sectionCd;
	}

	/**
	 * accountsCd取得.
	 * @return accountsCd
	 */
	public String getAccountsCd() {
		return this.accountsCd;
	}

	/**
	 * accountsCd設定.
	 * @param accountsCd accountsCd
	 */
	public void setAccountsCd(final String accountsCd) {
		this.accountsCd = accountsCd;
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
	 * orderPoint取得.
	 * @return orderPoint
	 */
	public java.math.BigDecimal getOrderPoint() {
		return this.orderPoint;
	}

	/**
	 * orderPoint設定.
	 * @param orderPoint orderPoint
	 */
	public void setOrderPoint(final java.math.BigDecimal orderPoint) {
		this.orderPoint = orderPoint;
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
	 * inputDate取得.
	 * @return inputDate
	 */
	public java.sql.Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * inputDate設定.
	 * @param inputDate inputDate
	 */
	public void setInputDate(final java.sql.Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * inputorCd取得.
	 * @return inputorCd
	 */
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * inputorCd設定.
	 * @param inputorCd inputorCd
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * updateDate取得.
	 * @return updateDate
	 */
	public java.sql.Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * updateDate設定.
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * updatorCd取得.
	 * @return updatorCd
	 */
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * updatorCd設定.
	 * @param updatorCd updatorCd
	 */
	public void setUpdatorCd(final String updatorCd) {
		this.updatorCd = updatorCd;
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

