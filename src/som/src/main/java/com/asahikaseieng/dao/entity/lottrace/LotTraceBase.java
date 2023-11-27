/*
 * Created on Thu Jan 22 20:04:45 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.lottrace;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * LotTraceBaseクラス.
 * @author t0011036
 */
public class LotTraceBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public LotTraceBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "LOT_TRACE";



	/** COLUMNアノテーション locationCd. */
	public static final String locationCd_COLUMN = "LOCATION_CD";

	/** COLUMNアノテーション itemCd. */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション lotNo. */
	public static final String lotNo_COLUMN = "LOT_NO";

	/** COLUMNアノテーション eventDate. */
	public static final String eventDate_COLUMN = "EVENT_DATE";

	/** COLUMNアノテーション ioDivision. */
	public static final String ioDivision_COLUMN = "IO_DIVISION";

	/** COLUMNアノテーション funcDivision. */
	public static final String funcDivision_COLUMN = "FUNC_DIVISION";

	/** COLUMNアノテーション ioQty. */
	public static final String ioQty_COLUMN = "IO_QTY";

	/** COLUMNアノテーション slipNo. */
	public static final String slipNo_COLUMN = "SLIP_NO";

	/** COLUMNアノテーション slipLineNo. */
	public static final String slipLineNo_COLUMN = "SLIP_LINE_NO";

	/** COLUMNアノテーション orderDivision. */
	public static final String orderDivision_COLUMN = "ORDER_DIVISION";

	/** COLUMNアノテーション orderNo. */
	public static final String orderNo_COLUMN = "ORDER_NO";

	/** COLUMNアノテーション lastInDivision. */
	public static final String lastInDivision_COLUMN = "LAST_IN_DIVISION";

	/** COLUMNアノテーション lastInNo. */
	public static final String lastInNo_COLUMN = "LAST_IN_NO";

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

	private String locationCd;

	private String itemCd;

	private String lotNo;

	private java.sql.Timestamp eventDate;

	private java.math.BigDecimal ioDivision;

	private java.math.BigDecimal funcDivision;

	private java.math.BigDecimal ioQty;

	private String slipNo;

	private java.math.BigDecimal slipLineNo;

	private java.math.BigDecimal orderDivision;

	private String orderNo;

	private java.math.BigDecimal lastInDivision;

	private String lastInNo;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	//
	// インスタンスメソッド
	//

	/**
	 * locationCd取得.
	 * @return locationCd
	 */
	public String getLocationCd() {
		return this.locationCd;
	}

	/**
	 * locationCd設定.
	 * @param locationCd locationCd
	 */
	public void setLocationCd(final String locationCd) {
		this.locationCd = locationCd;
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
	 * eventDate取得.
	 * @return eventDate
	 */
	public java.sql.Timestamp getEventDate() {
		return this.eventDate;
	}

	/**
	 * eventDate設定.
	 * @param eventDate eventDate
	 */
	public void setEventDate(final java.sql.Timestamp eventDate) {
		this.eventDate = eventDate;
	}

	/**
	 * ioDivision取得.
	 * @return ioDivision
	 */
	public java.math.BigDecimal getIoDivision() {
		return this.ioDivision;
	}

	/**
	 * ioDivision設定.
	 * @param ioDivision ioDivision
	 */
	public void setIoDivision(final java.math.BigDecimal ioDivision) {
		this.ioDivision = ioDivision;
	}

	/**
	 * funcDivision取得.
	 * @return funcDivision
	 */
	public java.math.BigDecimal getFuncDivision() {
		return this.funcDivision;
	}

	/**
	 * funcDivision設定.
	 * @param funcDivision funcDivision
	 */
	public void setFuncDivision(final java.math.BigDecimal funcDivision) {
		this.funcDivision = funcDivision;
	}

	/**
	 * ioQty取得.
	 * @return ioQty
	 */
	public java.math.BigDecimal getIoQty() {
		return this.ioQty;
	}

	/**
	 * ioQty設定.
	 * @param ioQty ioQty
	 */
	public void setIoQty(final java.math.BigDecimal ioQty) {
		this.ioQty = ioQty;
	}

	/**
	 * slipNo取得.
	 * @return slipNo
	 */
	public String getSlipNo() {
		return this.slipNo;
	}

	/**
	 * slipNo設定.
	 * @param slipNo slipNo
	 */
	public void setSlipNo(final String slipNo) {
		this.slipNo = slipNo;
	}

	/**
	 * slipLineNo取得.
	 * @return slipLineNo
	 */
	public java.math.BigDecimal getSlipLineNo() {
		return this.slipLineNo;
	}

	/**
	 * slipLineNo設定.
	 * @param slipLineNo slipLineNo
	 */
	public void setSlipLineNo(final java.math.BigDecimal slipLineNo) {
		this.slipLineNo = slipLineNo;
	}

	/**
	 * orderDivision取得.
	 * @return orderDivision
	 */
	public java.math.BigDecimal getOrderDivision() {
		return this.orderDivision;
	}

	/**
	 * orderDivision設定.
	 * @param orderDivision orderDivision
	 */
	public void setOrderDivision(final java.math.BigDecimal orderDivision) {
		this.orderDivision = orderDivision;
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
	 * lastInDivision取得.
	 * @return lastInDivision
	 */
	public java.math.BigDecimal getLastInDivision() {
		return this.lastInDivision;
	}

	/**
	 * lastInDivision設定.
	 * @param lastInDivision lastInDivision
	 */
	public void setLastInDivision(final java.math.BigDecimal lastInDivision) {
		this.lastInDivision = lastInDivision;
	}

	/**
	 * lastInNo取得.
	 * @return lastInNo
	 */
	public String getLastInNo() {
		return this.lastInNo;
	}

	/**
	 * lastInNo設定.
	 * @param lastInNo lastInNo
	 */
	public void setLastInNo(final String lastInNo) {
		this.lastInNo = lastInNo;
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
