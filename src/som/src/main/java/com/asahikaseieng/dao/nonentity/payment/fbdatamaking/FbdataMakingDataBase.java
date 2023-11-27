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
 * ＦＢデータ作成 ＦＢデータ用BaseEntityクラス.
 * @author tosco
 */
public class FbdataMakingDataBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public FbdataMakingDataBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "FB_DATA";

	/** COLUMNアノテーション dataDivision. */
	public static final String dataDivision_COLUMN = "DATA_DIVISION";

	/** COLUMNアノテーション bankMasterCd. */
	public static final String bankMasterCd_COLUMN = "BANK_MASTER_CD";

	/** COLUMNアノテーション bankKanaName. */
	public static final String bankKanaName_COLUMN = "BANK_KANA_NAME";

	/** COLUMNアノテーション branchCd. */
	public static final String branchCd_COLUMN = "BRANCH_CD";

	/** COLUMNアノテーション branchKanaName. */
	public static final String branchKanaName_COLUMN = "BRANCH_KANA_NAME";

	/** COLUMNアノテーション clearinghouse. */
	public static final String clearinghouse_COLUMN = "CLEARINGHOUSE";

	/** COLUMNアノテーション depositDivision. */
	public static final String depositDivision_COLUMN = "DEPOSIT_DIVISION";

	/** COLUMNアノテーション accountNo. */
	public static final String accountNo_COLUMN = "ACCOUNT_NO";

	/** COLUMNアノテーション accountStockhold. */
	public static final String accountStockhold_COLUMN = "ACCOUNT_STOCKHOLD";

	/** COLUMNアノテーション trasferAmount. */
	public static final String trasferAmount_COLUMN = "TRASFER_AMOUNT";

	/** COLUMNアノテーション newCd. */
	public static final String newCd_COLUMN = "NEW_CD";

	/** COLUMNアノテーション customerCd1. */
	public static final String customerCd1_COLUMN = "CUSTOMER_CD1";

	/** COLUMNアノテーション customerCd2. */
	public static final String customerCd2_COLUMN = "CUSTOMER_CD2";

	/** COLUMNアノテーション transferDivision. */
	public static final String transferDivision_COLUMN = "TRANSFER_DIVISION";

	/** COLUMNアノテーション dummy. */
	public static final String dummy_COLUMN = "DUMMY";

	/** COLUMNアノテーション transferAmountN. */
	public static final String transferAmountN_COLUMN = "TRANSFER_AMOUNT_N";

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

	/** 被仕向金融機関番号 */
	private String bankMasterCd;

	/** 被仕向金融機関名 */
	private String bankKanaName;

	/** 被仕向支店番号 */
	private String branchCd;

	/** 被仕向支店名 */
	private String branchKanaName;

	/** 手形交換所番号 */
	private String clearinghouse;

	/** 預金種目 */
	private String depositDivision;

	/** 口座番号 */
	private String accountNo;

	/** 受取人名 */
	private String accountStockhold;

	/** 振込金額 */
	private String trasferAmount;

	/** 新規コード */
	private String newCd;

	/** 顧客コード１ */
	private String customerCd1;

	/** 顧客コード２ */
	private String customerCd2;

	/** 振込指定(7固定) */
	private String transferDivision;

	/** ダミー */
	private String dummy;

	/** 振込金額(計算用） */
	private BigDecimal transferAmountN;

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
	 * 被仕向金融機関番号取得.
	 * @return String 被仕向金融機関番号
	 */
	public String getBankMasterCd() {
		return this.bankMasterCd;
	}

	/**
	 * 被仕向金融機関番号設定.
	 * @param bankMasterCd 被仕向金融機関番号
	 */
	public void setBankMasterCd(final String bankMasterCd) {
		this.bankMasterCd = bankMasterCd;
	}

	/**
	 * 被仕向金融機関名取得.
	 * @return String 被仕向金融機関名
	 */
	public String getBankKanaName() {
		return this.bankKanaName;
	}

	/**
	 * 被仕向金融機関名設定.
	 * @param bankKanaName 被仕向金融機関名
	 */
	public void setBankKanaName(final String bankKanaName) {
		this.bankKanaName = bankKanaName;
	}

	/**
	 * 被仕向支店番号取得.
	 * @return String 被仕向支店番号
	 */
	public String getBranchCd() {
		return this.branchCd;
	}

	/**
	 * 被仕向支店番号設定.
	 * @param branchCd 被仕向支店番号
	 */
	public void setBranchCd(final String branchCd) {
		this.branchCd = branchCd;
	}

	/**
	 * 被仕向支店名取得.
	 * @return String 被仕向支店名
	 */
	public String getBranchKanaName() {
		return this.branchKanaName;
	}

	/**
	 * 被仕向支店名設定.
	 * @param branchKanaName 被仕向支店名
	 */
	public void setBranchKanaName(final String branchKanaName) {
		this.branchKanaName = branchKanaName;
	}

	/**
	 * 手形交換所番号取得.
	 * @return String 手形交換所番号
	 */
	public String getClearinghouse() {
		return this.clearinghouse;
	}

	/**
	 * 手形交換所番号設定.
	 * @param clearinghouse 手形交換所番号
	 */
	public void setClearinghouse(final String clearinghouse) {
		this.clearinghouse = clearinghouse;
	}

	/**
	 * 預金種目取得.
	 * @return 預金種目
	 */
	public String getDepositDivision() {
		return this.depositDivision;
	}

	/**
	 * 預金種目設定.
	 * @param depositDivision 預金種目
	 */
	public void setDepositDivision(final String depositDivision) {
		this.depositDivision = depositDivision;
	}

	/**
	 * 口座番号取得.
	 * @return String 口座番号
	 */
	public String getAccountNo() {
		return this.accountNo;
	}

	/**
	 * 口座番号設定.
	 * @param accountNo 口座番号
	 */
	public void setAccountNo(final String accountNo) {
		this.accountNo = accountNo;
	}

	/**
	 * 受取人名取得.
	 * @return String 受取人名
	 */
	public String getAccountStockhold() {
		return this.accountStockhold;
	}

	/**
	 * 受取人名設定.
	 * @param accountStockhold 受取人名
	 */
	public void setAccountStockhold(final String accountStockhold) {
		this.accountStockhold = accountStockhold;
	}

	/**
	 * 振込金額取得.
	 * @return 振込金額
	 */
	public String getTrasferAmount() {
		return this.trasferAmount;
	}

	/**
	 * 振込金額設定.
	 * @param trasferAmount 振込金額
	 */
	public void setTrasferAmount(final String trasferAmount) {
		this.trasferAmount = trasferAmount;
	}

	/**
	 * 新規コード取得.
	 * @return String 新規コード
	 */
	public String getNewCd() {
		return this.newCd;
	}

	/**
	 * 新規コード設定.
	 * @param newCd 新規コード
	 */
	public void setNewCd(final String newCd) {
		this.newCd = newCd;
	}

	/**
	 * 顧客コード１取得.
	 * @return String 顧客コード１
	 */
	public String getCustomerCd1() {
		return this.customerCd1;
	}

	/**
	 * 顧客コード１設定.
	 * @param customerCd1 顧客コード１
	 */
	public void setCustomerCd1(final String customerCd1) {
		this.customerCd1 = customerCd1;
	}

	/**
	 * 顧客コード２取得.
	 * @return String 顧客コード２
	 */
	public String getCustomerCd2() {
		return this.customerCd2;
	}

	/**
	 * 顧客コード２設定.
	 * @param customerCd2 顧客コード２
	 */
	public void setCustomerCd2(final String customerCd2) {
		this.customerCd2 = customerCd2;
	}

	/**
	 * 振込指定(7固定)を取得します。
	 * @return String 振込指定(7固定)
	 */
	public String getTransferDivision() {
		return transferDivision;
	}

	/**
	 * 振込指定(7固定)を設定します。
	 * @param transferDivision 振込指定(7固定)
	 */
	public void setTransferDivision(final String transferDivision) {
		this.transferDivision = transferDivision;
	}

	/**
	 * ダミー取得.
	 * @return String ダミー
	 */
	public String getDummy() {
		return this.dummy;
	}

	/**
	 * ダミー設定.
	 * @param dummy ダミー
	 */
	public void setDummy(final String dummy) {
		this.dummy = dummy;
	}

	/**
	 * 振込金額(計算用）を取得します。
	 * @return BigDecimal 振込金額(計算用）
	 */
	public BigDecimal getTransferAmountN() {
		return transferAmountN;
	}

	/**
	 * 振込金額(計算用）を設定します。
	 * @param transferAmountN 振込金額(計算用）
	 */
	public void setTransferAmountN(final BigDecimal transferAmountN) {
		this.transferAmountN = transferAmountN;
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
