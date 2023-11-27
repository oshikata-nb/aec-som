
package com.asahikaseieng.dao.entity.orderimportfileout;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * OrderImportFileOutBaseクラス.
 */
public class OrderImportFileOutBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OrderImportFileOutBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "ORDER_IMPORT_FILE_OUT";


	/** COLUMNアノテーション seq. */
	public static final String seq_COLUMN = "SEQ";

	/** COLUMNアノテーション areaName. */
	public static final String pkNo_COLUMN = "PK_NO";

	/** COLUMNアノテーション pkRow. */
	public static final String pkRow_COLUMN = "PK_ROW";
	
	/** COLUMNアノテーション screenRow. */
	public static final String screenRow_COLUMN = "SCREEN_ROW";
	
	/** COLUMNアノテーション deliveryName. */
	public static final String deliveryName_COLUMN = "DELIVERY_NAME";
	
	/** COLUMNアノテーション itemName. */
	public static final String itemName_COLUMN = "ITEM_NAME";
	

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
	private BigDecimal seq;

	private String pkNo;

	private BigDecimal pkRow;
	
	private BigDecimal screenRow;
	
	private String deliveryName;
	
	private String itemName;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	//
	// インスタンスメソッド
	//

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


	/**
	 * pkNoを取得します。
	 * @return pkNo
	 */
	public String getPkNo() {
		return pkNo;
	}

	/**
	 * pkNoを設定します。
	 * @param pkNo pkNo
	 */
	public void setPkNo(String pkNo) {
		this.pkNo = pkNo;
	}

	/**
	 * pkRowを取得します。
	 * @return pkRow
	 */
	public BigDecimal getPkRow() {
		return pkRow;
	}

	/**
	 * pkRowを設定します。
	 * @param pkRow pkRow
	 */
	public void setPkRow(BigDecimal pkRow) {
		this.pkRow = pkRow;
	}

	/**
	 * seqを取得します。
	 * @return seq
	 */
	public BigDecimal getSeq() {
		return seq;
	}

	/**
	 * seqを設定します。
	 * @param seq seq
	 */
	public void setSeq(BigDecimal seq) {
		this.seq = seq;
	}

	/**
	 * screenRowを取得します。
	 * @return screenRow
	 */
	public BigDecimal getScreenRow() {
		return screenRow;
	}

	/**
	 * screenRowを設定します。
	 * @param screenRow screenRow
	 */
	public void setScreenRow(BigDecimal screenRow) {
		this.screenRow = screenRow;
	}

	/**
	 * deliveryNameを取得します。
	 * @return deliveryName
	 */
	public String getDeliveryName() {
		return deliveryName;
	}

	/**
	 * deliveryNameを設定します。
	 * @param deliveryName deliveryName
	 */
	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}

	/**
	 * itemNameを取得します。
	 * @return itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * itemNameを設定します。
	 * @param itemName itemName
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
}
