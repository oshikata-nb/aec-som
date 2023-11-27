/*
 * Created on Mon Jan 19 17:33:44 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.commonattributequeue;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * CommonAttributeQueueBaseクラス.
 * @author t0011036
 */
public class CommonAttributeQueueBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public CommonAttributeQueueBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "COMMON_ATTRIBUTE_QUEUE";


//	/** IDアノテーション itemCd. */
//	public static final String itemCd_ID = "assigned";
//	/** IDアノテーション version. */
//	public static final String version_ID = "assigned";

	/** COLUMNアノテーション itemCd. */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション version. */
	public static final String version_COLUMN = "VERSION";

	/** COLUMNアノテーション expireMonths. */
	public static final String expireMonths_COLUMN = "EXPIRE_MONTHS";

	/** COLUMNアノテーション contractMonths. */
	public static final String contractMonths_COLUMN = "CONTRACT_MONTHS";

	/** COLUMNアノテーション orderInfo. */
	public static final String orderInfo_COLUMN = "ORDER_INFO";

	/** COLUMNアノテーション remarks. */
	public static final String remarks_COLUMN = "REMARKS";

	/** COLUMNアノテーション applicationLaw. */
	public static final String applicationLaw_COLUMN = "APPLICATION_LAW";

	/** COLUMNアノテーション inputDate. */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd. */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate. */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd. */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	//
	// インスタンスフィールド
	//

	private String itemCd;

	private java.math.BigDecimal version;

	private java.math.BigDecimal expireMonths;

	private java.math.BigDecimal contractMonths;

	private String orderInfo;

	private String remarks;

	private String applicationLaw;

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
	 * orderInfo取得.
	 * @return orderInfo
	 */
	public String getOrderInfo() {
		return this.orderInfo;
	}

	/**
	 * orderInfo設定.
	 * @param orderInfo orderInfo
	 */
	public void setOrderInfo(final String orderInfo) {
		this.orderInfo = orderInfo;
	}

	/**
	 * remarks取得.
	 * @return remarks
	 */
	public String getRemarks() {
		return this.remarks;
	}

	/**
	 * remarks設定.
	 * @param remarks remarks
	 */
	public void setRemarks(final String remarks) {
		this.remarks = remarks;
	}

	/**
	 * applicationLaw取得.
	 * @return applicationLaw
	 */
	public String getApplicationLaw() {
		return this.applicationLaw;
	}

	/**
	 * applicationLaw設定.
	 * @param applicationLaw applicationLaw
	 */
	public void setApplicationLaw(final String applicationLaw) {
		this.applicationLaw = applicationLaw;
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
