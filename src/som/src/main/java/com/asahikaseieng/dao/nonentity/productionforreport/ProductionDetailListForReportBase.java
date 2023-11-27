/*
 * Created on 2009/09/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.productionforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * ProductionDetailListForReportクラス.
 * @author kanri-user
 */
public class ProductionDetailListForReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ProductionDetailListForReportBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション orderCd */
	public static final String orderCd_COLUMN = "ORDER_CD";

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション orderLet */
	public static final String orderLet_COLUMN = "ORDER_LET";

	/** COLUMNアノテーション orderExpectQty */
	public static final String orderExpectQty_COLUMN = "ORDER_EXPECT_QTY";

	/** COLUMNアノテーション orderAcceptQty */
	public static final String orderAcceptQty_COLUMN = "ORDER_ACCEPT_QTY";

	/** COLUMNアノテーション orderComment */
	public static final String orderComment_COLUMN = "ORDER_COMMENT";

	/** COLUMNアノテーション orderNo */
	public static final String orderNo_COLUMN = "ORDER_NO";

	/** COLUMNアノテーション orderRowNo */
	public static final String orderRowNo_COLUMN = "ORDER_ROW_NO";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション inputorName */
	public static final String inputorName_COLUMN = "INPUTOR_NAME";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション updatorName */
	public static final String updatorName_COLUMN = "UPDATOR_NAME";

	//
	// インスタンスフィールド
	//

	private String orderCd;

	private String itemCd;

	private String itemName;

	private java.sql.Timestamp orderLet;

	private java.math.BigDecimal orderExpectQty;

	private java.math.BigDecimal orderAcceptQty;

	private String orderComment;

	private String orderNo;

	private java.math.BigDecimal orderRowNo;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private String inputorName;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	private String updatorName;

	//
	// インスタンスメソッド
	//

	/**
	 * orderCd取得.
	 * @return orderCd
	 */
	public String getOrderCd() {
		return this.orderCd;
	}

	/**
	 * orderCd設定.
	 * @param orderCd orderCd
	 */
	public void setOrderCd(final String orderCd) {
		this.orderCd = orderCd;
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
	 * orderLet取得.
	 * @return orderLet
	 */
	public java.sql.Timestamp getOrderLet() {
		return this.orderLet;
	}

	/**
	 * orderLet設定.
	 * @param orderLet orderLet
	 */
	public void setOrderLet(final java.sql.Timestamp orderLet) {
		this.orderLet = orderLet;
	}

	/**
	 * orderExpectQty取得.
	 * @return orderExpectQty
	 */
	public java.math.BigDecimal getOrderExpectQty() {
		return this.orderExpectQty;
	}

	/**
	 * orderExpectQty設定.
	 * @param orderExpectQty orderExpectQty
	 */
	public void setOrderExpectQty(final java.math.BigDecimal orderExpectQty) {
		this.orderExpectQty = orderExpectQty;
	}

	/**
	 * orderAcceptQty取得.
	 * @return orderAcceptQty
	 */
	public java.math.BigDecimal getOrderAcceptQty() {
		return this.orderAcceptQty;
	}

	/**
	 * orderAcceptQty設定.
	 * @param orderAcceptQty orderAcceptQty
	 */
	public void setOrderAcceptQty(final java.math.BigDecimal orderAcceptQty) {
		this.orderAcceptQty = orderAcceptQty;
	}

	/**
	 * orderComment取得.
	 * @return orderComment
	 */
	public String getOrderComment() {
		return this.orderComment;
	}

	/**
	 * orderComment設定.
	 * @param orderComment orderComment
	 */
	public void setOrderComment(final String orderComment) {
		this.orderComment = orderComment;
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
	 * orderRowNo取得.
	 * @return orderRowNo
	 */
	public java.math.BigDecimal getOrderRowNo() {
		return this.orderRowNo;
	}

	/**
	 * orderRowNo設定.
	 * @param orderRowNo orderRowNo
	 */
	public void setOrderRowNo(final java.math.BigDecimal orderRowNo) {
		this.orderRowNo = orderRowNo;
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
}

