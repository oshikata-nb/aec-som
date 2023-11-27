/*
 * Created on 2009/02/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderremarklist;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * OrderRemarkListBaseクラス.
 * @author tosco
 */
public class OrderRemarkListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OrderRemarkListBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "REMARK";

	/** COLUMNアノテーション remarkNo. */
	public static final String remarkNo_COLUMN = "REMARK_NO";

	/** COLUMNアノテーション venderDivision. */
	public static final String venderDivision_COLUMN = "VENDER_DIVISION";

	/** COLUMNアノテーション venderCd. */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション deliveryCd. */
	public static final String deliveryCd_COLUMN = "DELIVERY_CD";

	/** COLUMNアノテーション itemCd. */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション remark01. */
	public static final String remark01_COLUMN = "REMARK01";

	/** COLUMNアノテーション remark12. */
	public static final String remark12_COLUMN = "REMARK12";

	/** COLUMNアノテーション remark13. */
	public static final String remark13_COLUMN = "REMARK13";

	/** COLUMNアノテーション remark16. */
	public static final String remark16_COLUMN = "REMARK16";

	//
	// インスタンスフィールド
	//

	private java.math.BigDecimal remarkNo;

	private String venderDivision;

	private String venderCd;

	private String deliveryCd;

	private String itemCd;

	private String remark01;

	private String remark12;

	private String remark13;

	private String remark15;

	private String remark16;

	//
	// インスタンスメソッド
	//

	/**
	 * 備考番号(PK)取得.
	 * @return remarkNo
	 */
	public java.math.BigDecimal getRemarkNo() {
		return this.remarkNo;
	}

	/**
	 * 備考番号(PK)設定.
	 * @param remarkNo remarkNo
	 */
	public void setRemarkNo(final java.math.BigDecimal remarkNo) {
		this.remarkNo = remarkNo;
	}

	/**
	 * venderDivisionを取得します。
	 * @return venderDivision
	 */
	public String getVenderDivision() {
		return venderDivision;
	}

	/**
	 * venderDivisionを設定します。
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
	 * remark12を取得します。
	 * @return remark12
	 */
	public String getRemark12() {
		return this.remark12;
	}

	/**
	 * remark12を設定します。
	 * @param remark12 remark12
	 */
	public void setRemark12(final String remark12) {
		this.remark12 = remark12;
	}

	/**
	 * remark13を取得します。
	 * @return remark13
	 */
	public String getRemark13() {
		return this.remark13;
	}

	/**
	 * remark13を設定します。
	 * @param remark13 remark13
	 */
	public void setRemark13(final String remark13) {
		this.remark13 = remark13;
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
	 * remark16を設定します。
	 * @param remark16 remark16
	 */
	public void setRemark16(final String remark16) {
		this.remark16 = remark16;
	}

	/**
	 * remark16を取得します。
	 * @return remark16
	 */
	public String getRemark16() {
		return remark16;
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

}
