/*
 * Created on 2009/05/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.remarkdetail;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * RemarkDetailクラス.
 * @author t0011036
 */
public class RemarkDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RemarkDetailBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション remarkNo */
	public static final String remarkNo_COLUMN = "REMARK_NO";

	/** COLUMNアノテーション venderDivision */
	public static final String venderDivision_COLUMN = "VENDER_DIVISION";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション deliveryCd */
	public static final String deliveryCd_COLUMN = "DELIVERY_CD";

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション remark01 */
	public static final String remark01_COLUMN = "REMARK01";

	/** COLUMNアノテーション remark11 */
	public static final String remark11_COLUMN = "REMARK11";

	/** COLUMNアノテーション remark12 */
	public static final String remark12_COLUMN = "REMARK12";

	/** COLUMNアノテーション remark13 */
	public static final String remark13_COLUMN = "REMARK13";

	/** COLUMNアノテーション remark06 */
	public static final String remark06_COLUMN = "REMARK06";

	/** COLUMNアノテーション remark15 */
	public static final String remark15_COLUMN = "REMARK15";

	/** COLUMNアノテーション remark16 */
	public static final String remark16_COLUMN = "REMARK16";

	/** COLUMNアノテーション venderName1 */
	public static final String venderName1_COLUMN = "VENDER_NAME1";

	/** COLUMNアノテーション deliveryName1 */
	public static final String deliveryName1_COLUMN = "DELIVERY_NAME1";

	/** COLUMNアノテーション otherCompanyCd1 */
	public static final String otherCompanyCd1_COLUMN = "OTHER_COMPANY_CD1";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	//
	// インスタンスフィールド
	//

	private java.math.BigDecimal remarkNo;

	private String venderDivision;

	private String venderCd;

	private String deliveryCd;

	private String itemCd;

	private String remark01;

	private String remark11;

	private String remark12;

	private String remark13;

	private String remark06;

	private String remark15;

	private String remark16;

	private String venderName1;

	private String deliveryName1;

	private String otherCompanyCd1;

	private String itemName;

	private java.sql.Timestamp updateDate;

	//
	// インスタンスメソッド
	//

	/**
	 * remarkNo取得.
	 * @return remarkNo
	 */
	public java.math.BigDecimal getRemarkNo() {
		return this.remarkNo;
	}

	/**
	 * remarkNo設定.
	 * @param remarkNo remarkNo
	 */
	public void setRemarkNo(final java.math.BigDecimal remarkNo) {
		this.remarkNo = remarkNo;
	}

	/**
	 * venderDivision取得.
	 * @return venderDivision
	 */
	public String getVenderDivision() {
		return this.venderDivision;
	}

	/**
	 * venderDivision設定.
	 * @param venderDivision venderDivision
	 */
	public void setVenderDivision(final String venderDivision) {
		this.venderDivision = venderDivision;
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
	 * remark01取得.
	 * @return remark01
	 */
	public String getRemark01() {
		return this.remark01;
	}

	/**
	 * remark01設定.
	 * @param remark01 remark01
	 */
	public void setRemark01(final String remark01) {
		this.remark01 = remark01;
	}

	/**
	 * remark11取得.
	 * @return remark11
	 */
	public String getRemark11() {
		return this.remark11;
	}

	/**
	 * remark11設定.
	 * @param remark11 remark11
	 */
	public void setRemark11(final String remark11) {
		this.remark11 = remark11;
	}

	/**
	 * remark12取得.
	 * @return remark12
	 */
	public String getRemark12() {
		return this.remark12;
	}

	/**
	 * remark12設定.
	 * @param remark12 remark12
	 */
	public void setRemark12(final String remark12) {
		this.remark12 = remark12;
	}

	/**
	 * remark13取得.
	 * @return remark13
	 */
	public String getRemark13() {
		return this.remark13;
	}

	/**
	 * remark13設定.
	 * @param remark13 remark13
	 */
	public void setRemark13(final String remark13) {
		this.remark13 = remark13;
	}

	/**
	 * remark06取得.
	 * @return remark06
	 */
	public String getRemark06() {
		return this.remark06;
	}

	/**
	 * remark06設定.
	 * @param remark06 remark06
	 */
	public void setRemark06(final String remark06) {
		this.remark06 = remark06;
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
	 * otherCompanyCd1取得.
	 * @return otherCompanyCd1
	 */
	public String getOtherCompanyCd1() {
		return this.otherCompanyCd1;
	}

	/**
	 * otherCompanyCd1設定.
	 * @param otherCompanyCd1 otherCompanyCd1
	 */
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = otherCompanyCd1;
	}

	/**
	 * itemName取得.
	 * @return itemName
	 */
	public String getItemName() {
		return this.itemName;
	}

	/**
	 * itemName設定.
	 * @param itemName itemName
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
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

	/**
	 * remark15を取得します。
	 * @return remark15
	 */
	public String getRemark15() {
		return remark15;
	}

	/**
	 * remark15を設定します。
	 * @param remark15 remark15
	 */
	public void setRemark15(String remark15) {
		this.remark15 = remark15;
	}

	/**
	 * remark16を取得します。
	 * @return remark16
	 */
	public String getRemark16() {
		return remark16;
	}

	/**
	 * remark16を設定します。
	 * @param remark16 remark16
	 */
	public void setRemark16(String remark16) {
		this.remark16 = remark16;
	}
}
