/*
 * Created on 2009/08/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.remarklistforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * RemarkListForReportクラス.
 * @author t0011036
 */
public class RemarkListForReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RemarkListForReportBase() {
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

	/** COLUMNアノテーション remark02 */
	public static final String remark02_COLUMN = "REMARK02";

	/** COLUMNアノテーション remark03 */
	public static final String remark03_COLUMN = "REMARK03";

	/** COLUMNアノテーション remark04 */
	public static final String remark04_COLUMN = "REMARK04";

	/** COLUMNアノテーション remark05 */
	public static final String remark05_COLUMN = "REMARK05";

	/** COLUMNアノテーション remark06 */
	public static final String remark06_COLUMN = "REMARK06";

	/** COLUMNアノテーション remark07 */
	public static final String remark07_COLUMN = "REMARK07";

	/** COLUMNアノテーション remark08 */
	public static final String remark08_COLUMN = "REMARK08";

	/** COLUMNアノテーション remark09 */
	public static final String remark09_COLUMN = "REMARK09";

	/** COLUMNアノテーション remark10 */
	public static final String remark10_COLUMN = "REMARK10";

	/** COLUMNアノテーション remark11 */
	public static final String remark11_COLUMN = "REMARK11";

	/** COLUMNアノテーション remark12 */
	public static final String remark12_COLUMN = "REMARK12";

	/** COLUMNアノテーション remark13 */
	public static final String remark13_COLUMN = "REMARK13";

	/** COLUMNアノテーション remark14 */
	public static final String remark14_COLUMN = "REMARK14";

	/** COLUMNアノテーション remark15 */
	public static final String remark15_COLUMN = "REMARK15";

	/** COLUMNアノテーション remark16 */
	public static final String remark16_COLUMN = "REMARK16";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション venderName1 */
	public static final String venderName1_COLUMN = "VENDER_NAME1";

	/** COLUMNアノテーション deliveryName1 */
	public static final String deliveryName1_COLUMN = "DELIVERY_NAME1";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション inputorName */
	public static final String inputorName_COLUMN = "INPUTOR_NAME";

	/** COLUMNアノテーション updatorName */
	public static final String updatorName_COLUMN = "UPDATOR_NAME";

	//
	// インスタンスフィールド
	//

	private java.math.BigDecimal remarkNo;

	private String venderDivision;

	private String venderCd;

	private String deliveryCd;

	private String itemCd;

	private String remark01;

	private String remark02;

	private String remark03;

	private String remark04;

	private String remark05;

	private String remark06;

	private String remark07;

	private String remark08;

	private String remark09;

	private String remark10;

	private String remark11;

	private String remark12;

	private String remark13;

	private String remark14;

	private String remark15;

	private String remark16;

	private String inputorCd;

	private java.sql.Timestamp inputDate;

	private String updatorCd;

	private java.sql.Timestamp updateDate;

	private String venderName1;

	private String deliveryName1;

	private String itemName;

	private String inputorName;

	private String updatorName;

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
	 * remark02取得.
	 * @return remark02
	 */
	public String getRemark02() {
		return this.remark02;
	}

	/**
	 * remark02設定.
	 * @param remark02 remark02
	 */
	public void setRemark02(final String remark02) {
		this.remark02 = remark02;
	}

	/**
	 * remark03取得.
	 * @return remark03
	 */
	public String getRemark03() {
		return this.remark03;
	}

	/**
	 * remark03設定.
	 * @param remark03 remark03
	 */
	public void setRemark03(final String remark03) {
		this.remark03 = remark03;
	}

	/**
	 * remark04取得.
	 * @return remark04
	 */
	public String getRemark04() {
		return this.remark04;
	}

	/**
	 * remark04設定.
	 * @param remark04 remark04
	 */
	public void setRemark04(final String remark04) {
		this.remark04 = remark04;
	}

	/**
	 * remark05取得.
	 * @return remark05
	 */
	public String getRemark05() {
		return this.remark05;
	}

	/**
	 * remark05設定.
	 * @param remark05 remark05
	 */
	public void setRemark05(final String remark05) {
		this.remark05 = remark05;
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
	 * remark07取得.
	 * @return remark07
	 */
	public String getRemark07() {
		return this.remark07;
	}

	/**
	 * remark07設定.
	 * @param remark07 remark07
	 */
	public void setRemark07(final String remark07) {
		this.remark07 = remark07;
	}

	/**
	 * remark08取得.
	 * @return remark08
	 */
	public String getRemark08() {
		return this.remark08;
	}

	/**
	 * remark08設定.
	 * @param remark08 remark08
	 */
	public void setRemark08(final String remark08) {
		this.remark08 = remark08;
	}

	/**
	 * remark09取得.
	 * @return remark09
	 */
	public String getRemark09() {
		return this.remark09;
	}

	/**
	 * remark09設定.
	 * @param remark09 remark09
	 */
	public void setRemark09(final String remark09) {
		this.remark09 = remark09;
	}

	/**
	 * remark10取得.
	 * @return remark10
	 */
	public String getRemark10() {
		return this.remark10;
	}

	/**
	 * remark10設定.
	 * @param remark10 remark10
	 */
	public void setRemark10(final String remark10) {
		this.remark10 = remark10;
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
	 * remark14取得.
	 * @return remark14
	 */
	public String getRemark14() {
		return this.remark14;
	}

	/**
	 * remark14設定.
	 * @param remark14 remark14
	 */
	public void setRemark14(final String remark14) {
		this.remark14 = remark14;
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
	 * inputorName取得.
	 * @return inputorName
	 */
	public String getInputorName() {
		return this.inputorName;
	}

	/**
	 * inputorName設定.
	 * @param inputorName inputorName
	 */
	public void setInputorName(final String inputorName) {
		this.inputorName = inputorName;
	}

	/**
	 * updatorName取得.
	 * @return updatorName
	 */
	public String getUpdatorName() {
		return this.updatorName;
	}

	/**
	 * updatorName設定.
	 * @param updatorName updatorName
	 */
	public void setUpdatorName(final String updatorName) {
		this.updatorName = updatorName;
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
	public void setRemark15(final String remark15) {
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
	public void setRemark16(final String remark16) {
		this.remark16 = remark16;
	}
}
