/* 注意：table2daoで作成されました。
 *　     編集しないでください。table2daoで上書されます。
 * Created on Wed Apr 29 11:48:44 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.fbtrailer;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * FbTrailerBaseクラス.
 * @author t0011036
 */
public class FbTrailerBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public FbTrailerBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "FB_TRAILER";


//	/** IDアノテーション paymentDate. */
//	public static final String paymentDate_ID = "assigned";

	/** COLUMNアノテーション dataDivision. */
	public static final String dataDivision_COLUMN = "DATA_DIVISION";

	/** COLUMNアノテーション trasferTotalNumber. */
	public static final String trasferTotalNumber_COLUMN = "TRASFER_TOTAL_NUMBER";

	/** COLUMNアノテーション trasferTotalAmount. */
	public static final String trasferTotalAmount_COLUMN = "TRASFER_TOTAL_AMOUNT";

	/** COLUMNアノテーション dummy. */
	public static final String dummy_COLUMN = "DUMMY";

	/** COLUMNアノテーション transferTotalNumberN. */
	public static final String transferTotalNumberN_COLUMN = "TRANSFER_TOTAL_NUMBER_N";

	/** COLUMNアノテーション transferTotalAmountN. */
	public static final String transferTotalAmountN_COLUMN = "TRANSFER_TOTAL_AMOUNT_N";

	/** COLUMNアノテーション paymentDate. */
	public static final String paymentDate_COLUMN = "PAYMENT_DATE";

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

	private String dataDivision;

	private String trasferTotalNumber;

	private String trasferTotalAmount;

	private String dummy;

	/** 合計件数(数値） */
	private BigDecimal transferTotalNumberN;

	/** 合計金額(数値) */
	private BigDecimal transferTotalAmountN;

	private String paymentDate;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	//
	// インスタンスメソッド
	//

	/**
	 * dataDivision取得.
	 * @return dataDivision
	 */
	public String getDataDivision() {
		return this.dataDivision;
	}

	/**
	 * dataDivision設定.
	 * @param dataDivision dataDivision
	 */
	public void setDataDivision(final String dataDivision) {
		this.dataDivision = dataDivision;
	}

	/**
	 * trasferTotalNumber取得.
	 * @return trasferTotalNumber
	 */
	public String getTrasferTotalNumber() {
		return this.trasferTotalNumber;
	}

	/**
	 * trasferTotalNumber設定.
	 * @param trasferTotalNumber trasferTotalNumber
	 */
	public void setTrasferTotalNumber(final String trasferTotalNumber) {
		this.trasferTotalNumber = trasferTotalNumber;
	}

	/**
	 * trasferTotalAmount取得.
	 * @return trasferTotalAmount
	 */
	public String getTrasferTotalAmount() {
		return this.trasferTotalAmount;
	}

	/**
	 * trasferTotalAmount設定.
	 * @param trasferTotalAmount trasferTotalAmount
	 */
	public void setTrasferTotalAmount(final String trasferTotalAmount) {
		this.trasferTotalAmount = trasferTotalAmount;
	}

	/**
	 * dummy取得.
	 * @return dummy
	 */
	public String getDummy() {
		return this.dummy;
	}

	/**
	 * dummy設定.
	 * @param dummy dummy
	 */
	public void setDummy(final String dummy) {
		this.dummy = dummy;
	}

	/**
	 * 合計件数(数値）取得.
	 * @return BigDecimal 合計件数(数値）
	 */
	public BigDecimal getTransferTotalNumberN() {
		return this.transferTotalNumberN;
	}

	/**
	 * 合計件数(数値）設定.
	 * @param transferTotalNumberN 合計件数(数値）
	 */
	public void setTransferTotalNumberN(final BigDecimal transferTotalNumberN) {
		this.transferTotalNumberN = transferTotalNumberN;
	}

	/**
	 * 合計金額(数値）取得.
	 * @return BigDecimal 合計金額(数値）
	 */
	public BigDecimal getTransferTotalAmountN() {
		return this.transferTotalAmountN;
	}

	/**
	 * 合計金額(数値）設定.
	 * @param transferTotalAmountN 合計金額(数値）
	 */
	public void setTransferTotalAmountN(final BigDecimal transferTotalAmountN) {
		this.transferTotalAmountN = transferTotalAmountN;
	}

	/**
	 * paymentDate取得.
	 * @return paymentDate
	 */
	public String getPaymentDate() {
		return this.paymentDate;
	}

	/**
	 * paymentDate設定.
	 * @param paymentDate paymentDate
	 */
	public void setPaymentDate(final String paymentDate) {
		this.paymentDate = paymentDate;
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
