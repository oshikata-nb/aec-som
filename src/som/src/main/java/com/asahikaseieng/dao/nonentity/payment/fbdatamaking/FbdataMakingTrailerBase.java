/*
 * Created on 2009/06/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.fbdatamaking;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * ＦＢデータ作成 ＦＢトレーラー用BaseEntityクラス.
 * @author tosco
 */
public class FbdataMakingTrailerBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public FbdataMakingTrailerBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "FB_TRAILER";

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

	/** データ区分（データレコード：2固定） */
	private String dataDivision;

	/** 合計件数 */
	private String trasferTotalNumber;

	/** 合計金額 */
	private String trasferTotalAmount;

	/** ダミー */
	private String dummy;

	/** 合計件数(数値） */
	private BigDecimal transferTotalNumberN;

	/** 合計金額(数値) */
	private BigDecimal transferTotalAmountN;

	/** 振込日付(YYYYMMDD) */
	private String paymentDate;

	/** 登録日時 */
	private Timestamp inputDate;

	/** 登録者ID */
	private String inputorCd;

	/** 更新日時 */
	private Timestamp updateDate;

	/** 更新者ID */
	private String updatorCd;

	//
	// インスタンスメソッド
	//

	/**
	 * データ区分（データレコード：2固定）取得.
	 * @return String データ区分（データレコード：2固定）
	 */
	public String getDataDivision() {
		return this.dataDivision;
	}

	/**
	 * データ区分（データレコード：2固定）設定.
	 * @param dataDivision データ区分（データレコード：2固定）
	 */
	public void setDataDivision(final String dataDivision) {
		this.dataDivision = dataDivision;
	}

	/**
	 * 合計件数取得.
	 * @return String 合計件数
	 */
	public String getTrasferTotalNumber() {
		return this.trasferTotalNumber;
	}

	/**
	 * 合計件数設定.
	 * @param trasferTotalNumber 合計件数
	 */
	public void setTrasferTotalNumber(final String trasferTotalNumber) {
		this.trasferTotalNumber = trasferTotalNumber;
	}

	/**
	 * 合計金額取得.
	 * @return String 合計金額
	 */
	public String getTrasferTotalAmount() {
		return this.trasferTotalAmount;
	}

	/**
	 * 合計金額設定.
	 * @param trasferTotalAmount 合計金額
	 */
	public void setTrasferTotalAmount(final String trasferTotalAmount) {
		this.trasferTotalAmount = trasferTotalAmount;
	}

	/**
	 * スペース取得.
	 * @return String スペース
	 */
	public String getDummy() {
		return this.dummy;
	}

	/**
	 * スペース設定.
	 * @param dummy スペース
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
	 * 振込日付(YYYYMMDD)取得.
	 * @return String 振込日付(YYYYMMDD)
	 */
	public String getPaymentDate() {
		return this.paymentDate;
	}

	/**
	 * 振込日付(YYYYMMDD)設定.
	 * @param paymentDate 振込日付(YYYYMMDD)
	 */
	public void setPaymentDate(final String paymentDate) {
		this.paymentDate = paymentDate;
	}

	/**
	 * 登録日時取得.
	 * @return Timestamp 登録日時
	 */
	public Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * 登録日時設定.
	 * @param inputDate 登録日時
	 */
	public void setInputDate(final Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * 登録者ID取得.
	 * @return String 登録者ID
	 */
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * 登録者ID設定.
	 * @param inputorCd 登録者ID
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * 更新日時取得.
	 * @return Timestamp 更新日時
	 */
	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * 更新日時設定.
	 * @param updateDate 更新日時
	 */
	public void setUpdateDate(final Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * 更新者ID取得.
	 * @return String 更新者ID
	 */
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * 更新者ID設定.
	 * @param updatorCd 更新者ID
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
