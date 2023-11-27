/*
 * Created on 2009/07/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repsalesslipforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * RepSalesSlipListForReportクラス.
 * @author kanri-user
 */
public class RepSalesSlipListForReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RepSalesSlipListForReportBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション salesNo */
	public static final String salesNo_COLUMN = "SALES_NO";

	/** COLUMNアノテーション salesSlipNo */
	public static final String salesSlipNo_COLUMN = "SALES_SLIP_NO";

	/** COLUMNアノテーション salesDate */
	public static final String salesDate_COLUMN = "SALES_DATE";

	/** COLUMNアノテーション scheduledShippingDate */
	public static final String scheduledShippingDate_COLUMN = "SCHEDULED_SHIPPING_DATE";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション deliveryCd */
	public static final String deliveryCd_COLUMN = "DELIVERY_CD";

	/** COLUMNアノテーション orderNo */
	public static final String orderNo_COLUMN = "ORDER_NO";

	/** COLUMNアノテーション carryCd */
	public static final String carryCd_COLUMN = "CARRY_CD";

	/** COLUMNアノテーション upperLocationCd */
	public static final String upperLocationCd_COLUMN = "UPPER_LOCATION_CD";

	/** COLUMNアノテーション buySubcontractOrderNo */
	public static final String buySubcontractOrderNo_COLUMN = "BUY_SUBCONTRACT_ORDER_NO";

	/** COLUMNアノテーション inputDivision */
	public static final String inputDivision_COLUMN = "INPUT_DIVISION";

	/** COLUMNアノテーション keepFlag */
	public static final String keepFlag_COLUMN = "KEEP_FLAG";

	/** COLUMNアノテーション categoryDivision */
	public static final String categoryDivision_COLUMN = "CATEGORY_DIVISION";

	/** COLUMNアノテーション accountYears */
	public static final String accountYears_COLUMN = "ACCOUNT_YEARS";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	//
	// インスタンスフィールド
	//

	private String salesNo;

	private String salesSlipNo;

	private String salesDate;

	private String scheduledShippingDate;

	private String venderCd;

	private String deliveryCd;

	private String orderNo;

	private String carryCd;

	private String upperLocationCd;

	private String buySubcontractOrderNo;

	private String inputDivision;

	private String keepFlag;

	private String categoryDivision;

	private String accountYears;

	private java.sql.Timestamp updateDate;

	//
	// インスタンスメソッド
	//

	/**
	 * salesNo取得.
	 * @return salesNo
	 */
	public String getSalesNo() {
		return this.salesNo;
	}

	/**
	 * salesNo設定.
	 * @param salesNo salesNo
	 */
	public void setSalesNo(final String salesNo) {
		this.salesNo = salesNo;
	}

	/**
	 * salesSlipNo取得.
	 * @return salesSlipNo
	 */
	public String getSalesSlipNo() {
		return this.salesSlipNo;
	}

	/**
	 * salesSlipNo設定.
	 * @param salesSlipNo salesSlipNo
	 */
	public void setSalesSlipNo(final String salesSlipNo) {
		this.salesSlipNo = salesSlipNo;
	}

	/**
	 * salesDate取得.
	 * @return salesDate
	 */
	public String getSalesDate() {
		return this.salesDate;
	}

	/**
	 * salesDate設定.
	 * @param salesDate salesDate
	 */
	public void setSalesDate(final String salesDate) {
		this.salesDate = salesDate;
	}

	/**
	 * scheduledShippingDate取得.
	 * @return scheduledShippingDate
	 */
	public String getScheduledShippingDate() {
		return this.scheduledShippingDate;
	}

	/**
	 * scheduledShippingDate設定.
	 * @param scheduledShippingDate scheduledShippingDate
	 */
	public void setScheduledShippingDate(final String scheduledShippingDate) {
		this.scheduledShippingDate = scheduledShippingDate;
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
	 * orderNo取得.
	 * @return orderNo
	 */
	public String getOrderNo() {
		return this.orderNo;
	}

	/**
	 * orderNo設定.
	 * @param orderNo orderNo
	 */
	public void setOrderNo(final String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * carryCd取得.
	 * @return carryCd
	 */
	public String getCarryCd() {
		return this.carryCd;
	}

	/**
	 * carryCd設定.
	 * @param carryCd carryCd
	 */
	public void setCarryCd(final String carryCd) {
		this.carryCd = carryCd;
	}

	/**
	 * upperLocationCd取得.
	 * @return upperLocationCd
	 */
	public String getUpperLocationCd() {
		return this.upperLocationCd;
	}

	/**
	 * upperLocationCd設定.
	 * @param upperLocationCd upperLocationCd
	 */
	public void setUpperLocationCd(final String upperLocationCd) {
		this.upperLocationCd = upperLocationCd;
	}

	/**
	 * buySubcontractOrderNo取得.
	 * @return buySubcontractOrderNo
	 */
	public String getBuySubcontractOrderNo() {
		return this.buySubcontractOrderNo;
	}

	/**
	 * buySubcontractOrderNo設定.
	 * @param buySubcontractOrderNo buySubcontractOrderNo
	 */
	public void setBuySubcontractOrderNo(final String buySubcontractOrderNo) {
		this.buySubcontractOrderNo = buySubcontractOrderNo;
	}

	/**
	 * inputDivision取得.
	 * @return inputDivision
	 */
	public String getInputDivision() {
		return this.inputDivision;
	}

	/**
	 * inputDivision設定.
	 * @param inputDivision inputDivision
	 */
	public void setInputDivision(final String inputDivision) {
		this.inputDivision = inputDivision;
	}

	/**
	 * keepFlag取得.
	 * @return keepFlag
	 */
	public String getKeepFlag() {
		return this.keepFlag;
	}

	/**
	 * keepFlag設定.
	 * @param keepFlag keepFlag
	 */
	public void setKeepFlag(final String keepFlag) {
		this.keepFlag = keepFlag;
	}

	/**
	 * categoryDivision取得.
	 * @return categoryDivision
	 */
	public String getCategoryDivision() {
		return this.categoryDivision;
	}

	/**
	 * categoryDivision設定.
	 * @param categoryDivision categoryDivision
	 */
	public void setCategoryDivision(final String categoryDivision) {
		this.categoryDivision = categoryDivision;
	}

	/**
	 * accountYears取得.
	 * @return accountYears
	 */
	public String getAccountYears() {
		return this.accountYears;
	}

	/**
	 * accountYears設定.
	 * @param accountYears accountYears
	 */
	public void setAccountYears(final String accountYears) {
		this.accountYears = accountYears;
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
