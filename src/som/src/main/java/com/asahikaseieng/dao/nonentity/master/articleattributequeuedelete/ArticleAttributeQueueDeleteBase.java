/*
 * Created on 2009/05/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.articleattributequeuedelete;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * ArticleAttributeQueueDeleteクラス.
 * @author t0011036
 */
public class ArticleAttributeQueueDeleteBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ArticleAttributeQueueDeleteBase() {
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

	/** COLUMNアノテーション originalItemCd */
	public static final String originalItemCd_COLUMN = "ORIGINAL_ITEM_CD";

	/** COLUMNアノテーション originalItemVersion */
	public static final String originalItemVersion_COLUMN = "ORIGINAL_ITEM_VERSION";

	/** COLUMNアノテーション customerItemCd */
	public static final String customerItemCd_COLUMN = "CUSTOMER_ITEM_CD";

	/** COLUMNアノテーション abcDivision */
	public static final String abcDivision_COLUMN = "ABC_DIVISION";

	/** COLUMNアノテーション defaultLocation */
	public static final String defaultLocation_COLUMN = "DEFAULT_LOCATION";

	/** COLUMNアノテーション taxDivision */
	public static final String taxDivision_COLUMN = "TAX_DIVISION";

	/** COLUMNアノテーション taxRatio */
	public static final String taxRatio_COLUMN = "TAX_RATIO";

	/** COLUMNアノテーション priceCalcDivision */
	public static final String priceCalcDivision_COLUMN = "PRICE_CALC_DIVISION";

	/** COLUMNアノテーション sellingPrice */
	public static final String sellingPrice_COLUMN = "SELLING_PRICE";

	/** COLUMNアノテーション deliveryTime */
	public static final String deliveryTime_COLUMN = "DELIVERY_TIME";

	/** COLUMNアノテーション certificationAttach */
	public static final String certificationAttach_COLUMN = "CERTIFICATION_ATTACH";

	/** COLUMNアノテーション deliveryLeadTime */
	public static final String deliveryLeadTime_COLUMN = "DELIVERY_LEAD_TIME";

	/** COLUMNアノテーション safetyLeadTime */
	public static final String safetyLeadTime_COLUMN = "SAFETY_LEAD_TIME";

	/** COLUMNアノテーション expireMonths */
	public static final String expireMonths_COLUMN = "EXPIRE_MONTHS";

	/** COLUMNアノテーション contractMonths */
	public static final String contractMonths_COLUMN = "CONTRACT_MONTHS";

	/** COLUMNアノテーション paletteProducts */
	public static final String paletteProducts_COLUMN = "PALETTE_PRODUCTS";

	/** COLUMNアノテーション janCd */
	public static final String janCd_COLUMN = "JAN_CD";

	/** COLUMNアノテーション itfCd */
	public static final String itfCd_COLUMN = "ITF_CD";

	/** COLUMNアノテーション sectionCd */
	public static final String sectionCd_COLUMN = "SECTION_CD";

	/** COLUMNアノテーション accountsCd */
	public static final String accountsCd_COLUMN = "ACCOUNTS_CD";

	/** COLUMNアノテーション financialClassCd */
	public static final String financialClassCd_COLUMN = "FINANCIAL_CLASS_CD";

	/** COLUMNアノテーション keepDivision */
	public static final String keepDivision_COLUMN = "KEEP_DIVISION";

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

	private String originalItemCd;

	private java.math.BigDecimal originalItemVersion;

	private String customerItemCd;

	private String abcDivision;

	private String defaultLocation;

	private java.math.BigDecimal taxDivision;

	private java.math.BigDecimal taxRatio;

	private java.math.BigDecimal priceCalcDivision;

	private java.math.BigDecimal sellingPrice;

	private java.math.BigDecimal deliveryTime;

	private java.math.BigDecimal certificationAttach;

	private java.math.BigDecimal deliveryLeadTime;

	private java.math.BigDecimal safetyLeadTime;

	private java.math.BigDecimal expireMonths;

	private java.math.BigDecimal contractMonths;

	private java.math.BigDecimal paletteProducts;

	private String janCd;

	private String itfCd;

	private String sectionCd;

	private String accountsCd;

	private String financialClassCd;

	private java.math.BigDecimal keepDivision;

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
	 * originalItemCd取得.
	 * @return originalItemCd
	 */
	public String getOriginalItemCd() {
		return this.originalItemCd;
	}

	/**
	 * originalItemCd設定.
	 * @param originalItemCd originalItemCd
	 */
	public void setOriginalItemCd(final String originalItemCd) {
		this.originalItemCd = originalItemCd;
	}

	/**
	 * originalItemVersion取得.
	 * @return originalItemVersion
	 */
	public java.math.BigDecimal getOriginalItemVersion() {
		return this.originalItemVersion;
	}

	/**
	 * originalItemVersion設定.
	 * @param originalItemVersion originalItemVersion
	 */
	public void setOriginalItemVersion(final java.math.BigDecimal originalItemVersion) {
		this.originalItemVersion = originalItemVersion;
	}

	/**
	 * customerItemCd取得.
	 * @return customerItemCd
	 */
	public String getCustomerItemCd() {
		return this.customerItemCd;
	}

	/**
	 * customerItemCd設定.
	 * @param customerItemCd customerItemCd
	 */
	public void setCustomerItemCd(final String customerItemCd) {
		this.customerItemCd = customerItemCd;
	}

	/**
	 * abcDivision取得.
	 * @return abcDivision
	 */
	public String getAbcDivision() {
		return this.abcDivision;
	}

	/**
	 * abcDivision設定.
	 * @param abcDivision abcDivision
	 */
	public void setAbcDivision(final String abcDivision) {
		this.abcDivision = abcDivision;
	}

	/**
	 * defaultLocation取得.
	 * @return defaultLocation
	 */
	public String getDefaultLocation() {
		return this.defaultLocation;
	}

	/**
	 * defaultLocation設定.
	 * @param defaultLocation defaultLocation
	 */
	public void setDefaultLocation(final String defaultLocation) {
		this.defaultLocation = defaultLocation;
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
	 * sellingPrice取得.
	 * @return sellingPrice
	 */
	public java.math.BigDecimal getSellingPrice() {
		return this.sellingPrice;
	}

	/**
	 * sellingPrice設定.
	 * @param sellingPrice sellingPrice
	 */
	public void setSellingPrice(final java.math.BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	/**
	 * deliveryTime取得.
	 * @return deliveryTime
	 */
	public java.math.BigDecimal getDeliveryTime() {
		return this.deliveryTime;
	}

	/**
	 * deliveryTime設定.
	 * @param deliveryTime deliveryTime
	 */
	public void setDeliveryTime(final java.math.BigDecimal deliveryTime) {
		this.deliveryTime = deliveryTime;
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
	 * deliveryLeadTime取得.
	 * @return deliveryLeadTime
	 */
	public java.math.BigDecimal getDeliveryLeadTime() {
		return this.deliveryLeadTime;
	}

	/**
	 * deliveryLeadTime設定.
	 * @param deliveryLeadTime deliveryLeadTime
	 */
	public void setDeliveryLeadTime(final java.math.BigDecimal deliveryLeadTime) {
		this.deliveryLeadTime = deliveryLeadTime;
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
	 * paletteProducts取得.
	 * @return paletteProducts
	 */
	public java.math.BigDecimal getPaletteProducts() {
		return this.paletteProducts;
	}

	/**
	 * paletteProducts設定.
	 * @param paletteProducts paletteProducts
	 */
	public void setPaletteProducts(final java.math.BigDecimal paletteProducts) {
		this.paletteProducts = paletteProducts;
	}

	/**
	 * janCd取得.
	 * @return janCd
	 */
	public String getJanCd() {
		return this.janCd;
	}

	/**
	 * janCd設定.
	 * @param janCd janCd
	 */
	public void setJanCd(final String janCd) {
		this.janCd = janCd;
	}

	/**
	 * itfCd取得.
	 * @return itfCd
	 */
	public String getItfCd() {
		return this.itfCd;
	}

	/**
	 * itfCd設定.
	 * @param itfCd itfCd
	 */
	public void setItfCd(final String itfCd) {
		this.itfCd = itfCd;
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
	 * financialClassCd取得.
	 * @return financialClassCd
	 */
	public String getFinancialClassCd() {
		return this.financialClassCd;
	}

	/**
	 * financialClassCd設定.
	 * @param financialClassCd financialClassCd
	 */
	public void setFinancialClassCd(final String financialClassCd) {
		this.financialClassCd = financialClassCd;
	}

	/**
	 * keepDivision取得.
	 * @return keepDivision
	 */
	public java.math.BigDecimal getKeepDivision() {
		return this.keepDivision;
	}

	/**
	 * keepDivision設定.
	 * @param keepDivision keepDivision
	 */
	public void setKeepDivision(final java.math.BigDecimal keepDivision) {
		this.keepDivision = keepDivision;
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

