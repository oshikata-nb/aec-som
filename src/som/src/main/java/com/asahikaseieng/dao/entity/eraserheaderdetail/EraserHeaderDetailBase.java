/* 注意：table2daoで作成されました。
 *　     編集しないでください。table2daoで上書されます。
 * Created on Thu Jan 21 14:36:16 JST 2010
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.eraserheaderdetail;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * EraserHeaderDetailBaseクラス.
 * @author t0011036
 */
public class EraserHeaderDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public EraserHeaderDetailBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "ERASER_HEADER_DETAIL";


//	/** IDアノテーション eraserNo. */
//	public static final String eraserNo_ID = "assigned";

	/** COLUMNアノテーション eraserNo. */
	public static final String eraserNo_COLUMN = "ERASER_NO";

	/** COLUMNアノテーション slipNo. */
	public static final String slipNo_COLUMN = "SLIP_NO";

	/** COLUMNアノテーション creditNo. */
	public static final String creditNo_COLUMN = "CREDIT_NO";

	/** COLUMNアノテーション rowNo. */
	public static final String rowNo_COLUMN = "ROW_NO";

	/** COLUMNアノテーション eraserAmount. */
	public static final String eraserAmount_COLUMN = "ERASER_AMOUNT";

	/** COLUMNアノテーション salesEraserDivision. */
	public static final String salesEraserDivision_COLUMN = "SALES_ERASER_DIVISION";

	/** COLUMNアノテーション creditEraserDivision. */
	public static final String creditEraserDivision_COLUMN = "CREDIT_ERASER_DIVISION";

	/** COLUMNアノテーション eraserDate. */
	public static final String eraserDate_COLUMN = "ERASER_DATE";

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

	private String eraserNo;

	private String slipNo;

	private String creditNo;

	private java.math.BigDecimal rowNo;

	private java.math.BigDecimal eraserAmount;

	private java.math.BigDecimal salesEraserDivision;

	private java.math.BigDecimal creditEraserDivision;

	private java.sql.Timestamp eraserDate;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	//
	// インスタンスメソッド
	//

	/**
	 * eraserNo取得.
	 * @return eraserNo
	 */
	public String getEraserNo() {
		return this.eraserNo;
	}

	/**
	 * eraserNo設定.
	 * @param eraserNo eraserNo
	 */
	public void setEraserNo(final String eraserNo) {
		this.eraserNo = eraserNo;
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
	 * creditNo取得.
	 * @return creditNo
	 */
	public String getCreditNo() {
		return this.creditNo;
	}

	/**
	 * creditNo設定.
	 * @param creditNo creditNo
	 */
	public void setCreditNo(final String creditNo) {
		this.creditNo = creditNo;
	}

	/**
	 * rowNo取得.
	 * @return rowNo
	 */
	public java.math.BigDecimal getRowNo() {
		return this.rowNo;
	}

	/**
	 * rowNo設定.
	 * @param rowNo rowNo
	 */
	public void setRowNo(final java.math.BigDecimal rowNo) {
		this.rowNo = rowNo;
	}

	/**
	 * eraserAmount取得.
	 * @return eraserAmount
	 */
	public java.math.BigDecimal getEraserAmount() {
		return this.eraserAmount;
	}

	/**
	 * eraserAmount設定.
	 * @param eraserAmount eraserAmount
	 */
	public void setEraserAmount(final java.math.BigDecimal eraserAmount) {
		this.eraserAmount = eraserAmount;
	}

	/**
	 * salesEraserDivision取得.
	 * @return salesEraserDivision
	 */
	public java.math.BigDecimal getSalesEraserDivision() {
		return this.salesEraserDivision;
	}

	/**
	 * salesEraserDivision設定.
	 * @param salesEraserDivision salesEraserDivision
	 */
	public void setSalesEraserDivision(final java.math.BigDecimal salesEraserDivision) {
		this.salesEraserDivision = salesEraserDivision;
	}

	/**
	 * creditEraserDivision取得.
	 * @return creditEraserDivision
	 */
	public java.math.BigDecimal getCreditEraserDivision() {
		return this.creditEraserDivision;
	}

	/**
	 * creditEraserDivision設定.
	 * @param creditEraserDivision creditEraserDivision
	 */
	public void setCreditEraserDivision(final java.math.BigDecimal creditEraserDivision) {
		this.creditEraserDivision = creditEraserDivision;
	}

	/**
	 * eraserDate取得.
	 * @return eraserDate
	 */
	public java.sql.Timestamp getEraserDate() {
		return this.eraserDate;
	}

	/**
	 * eraserDate設定.
	 * @param eraserDate eraserDate
	 */
	public void setEraserDate(final java.sql.Timestamp eraserDate) {
		this.eraserDate = eraserDate;
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
