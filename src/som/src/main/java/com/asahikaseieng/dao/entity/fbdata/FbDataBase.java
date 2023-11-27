/* 注意：table2daoで作成されました。
 *　     編集しないでください。table2daoで上書されます。
 * Created on Tue Apr 28 09:03:44 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.fbdata;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * FbDataBaseクラス.
 * @author t0011036
 */
public class FbDataBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public FbDataBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "FB_DATA";


//	/** IDアノテーション accountNo. */
//	public static final String accountNo_ID = "assigned";
//	/** IDアノテーション bankMasterCd. */
//	public static final String bankMasterCd_ID = "assigned";
//	/** IDアノテーション branchCd. */
//	public static final String branchCd_ID = "assigned";
//	/** IDアノテーション depositDivision. */
//	public static final String depositDivision_ID = "assigned";
//	/** IDアノテーション paymentDate. */
//	public static final String paymentDate_ID = "assigned";

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

	private String dataDivision;

	private String bankMasterCd;

	private String bankKanaName;

	private String branchCd;

	private String branchKanaName;

	private String clearinghouse;

	private String depositDivision;

	private String accountNo;

	private String accountStockhold;

	private String trasferAmount;

	private String newCd;

	private String customerCd1;

	private String customerCd2;

	/** 振込指定(7固定) */
	private String transferDivision;

	private String dummy;

	/** 振込金額(計算用） */
	private BigDecimal transferAmountN;

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
	 * bankMasterCd取得.
	 * @return bankMasterCd
	 */
	public String getBankMasterCd() {
		return this.bankMasterCd;
	}

	/**
	 * bankMasterCd設定.
	 * @param bankMasterCd bankMasterCd
	 */
	public void setBankMasterCd(final String bankMasterCd) {
		this.bankMasterCd = bankMasterCd;
	}

	/**
	 * bankKanaName取得.
	 * @return bankKanaName
	 */
	public String getBankKanaName() {
		return this.bankKanaName;
	}

	/**
	 * bankKanaName設定.
	 * @param bankKanaName bankKanaName
	 */
	public void setBankKanaName(final String bankKanaName) {
		this.bankKanaName = bankKanaName;
	}

	/**
	 * branchCd取得.
	 * @return branchCd
	 */
	public String getBranchCd() {
		return this.branchCd;
	}

	/**
	 * branchCd設定.
	 * @param branchCd branchCd
	 */
	public void setBranchCd(final String branchCd) {
		this.branchCd = branchCd;
	}

	/**
	 * branchKanaName取得.
	 * @return branchKanaName
	 */
	public String getBranchKanaName() {
		return this.branchKanaName;
	}

	/**
	 * branchKanaName設定.
	 * @param branchKanaName branchKanaName
	 */
	public void setBranchKanaName(final String branchKanaName) {
		this.branchKanaName = branchKanaName;
	}

	/**
	 * clearinghouse取得.
	 * @return clearinghouse
	 */
	public String getClearinghouse() {
		return this.clearinghouse;
	}

	/**
	 * clearinghouse設定.
	 * @param clearinghouse clearinghouse
	 */
	public void setClearinghouse(final String clearinghouse) {
		this.clearinghouse = clearinghouse;
	}

	/**
	 * depositDivision取得.
	 * @return depositDivision
	 */
	public String getDepositDivision() {
		return this.depositDivision;
	}

	/**
	 * depositDivision設定.
	 * @param depositDivision depositDivision
	 */
	public void setDepositDivision(final String depositDivision) {
		this.depositDivision = depositDivision;
	}

	/**
	 * accountNo取得.
	 * @return accountNo
	 */
	public String getAccountNo() {
		return this.accountNo;
	}

	/**
	 * accountNo設定.
	 * @param accountNo accountNo
	 */
	public void setAccountNo(final String accountNo) {
		this.accountNo = accountNo;
	}

	/**
	 * accountStockhold取得.
	 * @return accountStockhold
	 */
	public String getAccountStockhold() {
		return this.accountStockhold;
	}

	/**
	 * accountStockhold設定.
	 * @param accountStockhold accountStockhold
	 */
	public void setAccountStockhold(final String accountStockhold) {
		this.accountStockhold = accountStockhold;
	}

	/**
	 * trasferAmount取得.
	 * @return trasferAmount
	 */
	public String getTrasferAmount() {
		return this.trasferAmount;
	}

	/**
	 * trasferAmount設定.
	 * @param trasferAmount trasferAmount
	 */
	public void setTrasferAmount(final String trasferAmount) {
		this.trasferAmount = trasferAmount;
	}

	/**
	 * newCd取得.
	 * @return newCd
	 */
	public String getNewCd() {
		return this.newCd;
	}

	/**
	 * newCd設定.
	 * @param newCd newCd
	 */
	public void setNewCd(final String newCd) {
		this.newCd = newCd;
	}

	/**
	 * customerCd1取得.
	 * @return customerCd1
	 */
	public String getCustomerCd1() {
		return this.customerCd1;
	}

	/**
	 * customerCd1設定.
	 * @param customerCd1 customerCd1
	 */
	public void setCustomerCd1(final String customerCd1) {
		this.customerCd1 = customerCd1;
	}

	/**
	 * customerCd2取得.
	 * @return customerCd2
	 */
	public String getCustomerCd2() {
		return this.customerCd2;
	}

	/**
	 * customerCd2設定.
	 * @param customerCd2 customerCd2
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
