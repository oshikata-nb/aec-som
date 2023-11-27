/*
 * Created on 2010/09/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.salesinout;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * SalesInoutSearchListクラス.
 * @author t1344224
 */
public class SalesInoutSearchListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public SalesInoutSearchListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション inoutNo */
	public static final String inoutNo_COLUMN = "INOUT_NO";

	/** COLUMNアノテーション lotNo */
	public static final String lotNo_COLUMN = "LOT_NO";

	/** COLUMNアノテーション inoutQty */
	public static final String inoutQty_COLUMN = "INOUT_QTY";

	/** COLUMNアノテーション inoutWeight */
	public static final String inoutWeight_COLUMN = "INOUT_WEIGHT";

	/** COLUMNアノテーション inoutDate */
	public static final String inoutDate_COLUMN = "INOUT_DATE";

	/** COLUMNアノテーション ryName */
	public static final String ryName_COLUMN = "RY_NAME";

	/** COLUMNアノテーション inputorName */
	public static final String inputorName_COLUMN = "INPUTOR_NAME";

	//
	// インスタンスフィールド
	//

	private String inoutNo;

	private String lotNo;

	private java.math.BigDecimal inoutQty;

	private java.math.BigDecimal inoutWeight;

	private String inoutDate;

	private String ryName;

	private String inputorName;

	//
	// インスタンスメソッド
	//

	/**
	 * inoutNo取得.
	 * @return inoutNo
	 */
	public String getInoutNo() {
		return this.inoutNo;
	}

	/**
	 * inoutNo設定.
	 * @param inoutNo inoutNo
	 */
	public void setInoutNo(final String inoutNo) {
		this.inoutNo = inoutNo;
	}

	/**
	 * lotNo取得.
	 * @return lotNo
	 */
	public String getLotNo() {
		return this.lotNo;
	}

	/**
	 * lotNo設定.
	 * @param lotNo lotNo
	 */
	public void setLotNo(final String lotNo) {
		this.lotNo = lotNo;
	}

	/**
	 * inoutQty取得.
	 * @return inoutQty
	 */
	public java.math.BigDecimal getInoutQty() {
		return this.inoutQty;
	}

	/**
	 * inoutQty設定.
	 * @param inoutQty inoutQty
	 */
	public void setInoutQty(final java.math.BigDecimal inoutQty) {
		this.inoutQty = inoutQty;
	}

	/**
	 * inoutWeight取得.
	 * @return inoutWeight
	 */
	public java.math.BigDecimal getInoutWeight() {
		return inoutWeight;
	}

	/**
	 * inoutWeight設定.
	 * @param inoutWeight inoutWeight
	 */
	public void setInoutWeight(final java.math.BigDecimal inoutWeight) {
		this.inoutWeight = inoutWeight;
	}

	/**
	 * inoutDate取得.
	 * @return inoutDate
	 */
	public String getInoutDate() {
		return this.inoutDate;
	}

	/**
	 * inoutDate設定.
	 * @param inoutDate inoutDate
	 */
	public void setInoutDate(final String inoutDate) {
		this.inoutDate = inoutDate;
	}

	/**
	 * ryName取得.
	 * @return ryName
	 */
	public String getRyName() {
		return this.ryName;
	}

	/**
	 * ryName設定.
	 * @param ryName ryName
	 */
	public void setRyName(final String ryName) {
		this.ryName = ryName;
	}

	/**
	 * inputorCd取得.
	 * @return inputorCd
	 */
	public String getInputorName() {
		return this.inputorName;
	}

	/**
	 * inputorCd設定.
	 * @param inputorCd inputorCd
	 */
	public void setInputorName(final String inputorCd) {
		this.inputorName = inputorCd;
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
