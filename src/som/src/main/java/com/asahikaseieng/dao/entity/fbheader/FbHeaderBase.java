/* 注意：table2daoで作成されました。
 *　     編集しないでください。table2daoで上書されます。
 * Created on Tue Apr 28 09:03:33 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.fbheader;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * FbHeaderBaseクラス.
 * @author t0011036
 */
public class FbHeaderBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public FbHeaderBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "FB_HEADER";


//	/** IDアノテーション paymentDate. */
//	public static final String paymentDate_ID = "assigned";

	/** COLUMNアノテーション dataDivision. */
	public static final String dataDivision_COLUMN = "DATA_DIVISION";

	/** COLUMNアノテーション divisionCd. */
	public static final String divisionCd_COLUMN = "DIVISION_CD";

	/** COLUMNアノテーション categoryDivision. */
	public static final String categoryDivision_COLUMN = "CATEGORY_DIVISION";

	/** COLUMNアノテーション transferClientCd. */
	public static final String transferClientCd_COLUMN = "TRANSFER_CLIENT_CD";

	/** COLUMNアノテーション transferClientName. */
	public static final String transferClientName_COLUMN = "TRANSFER_CLIENT_NAME";

	/** COLUMNアノテーション trasferDate. */
	public static final String trasferDate_COLUMN = "TRASFER_DATE";

	/** COLUMNアノテーション bankMasterCd. */
	public static final String bankMasterCd_COLUMN = "BANK_MASTER_CD";

	/** COLUMNアノテーション bankKanaName. */
	public static final String bankKanaName_COLUMN = "BANK_KANA_NAME";

	/** COLUMNアノテーション branchCd. */
	public static final String branchCd_COLUMN = "BRANCH_CD";

	/** COLUMNアノテーション branchKanaName. */
	public static final String branchKanaName_COLUMN = "BRANCH_KANA_NAME";

	/** COLUMNアノテーション accountDivision. */
	public static final String accountDivision_COLUMN = "ACCOUNT_DIVISION";

	/** COLUMNアノテーション accountNo. */
	public static final String accountNo_COLUMN = "ACCOUNT_NO";

	/** COLUMNアノテーション dummy. */
	public static final String dummy_COLUMN = "DUMMY";

	/** COLUMNアノテーション dlDate. */
	public static final String dlDate_COLUMN = "DL_DATE";

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

	private String divisionCd;

	private String categoryDivision;

	private String transferClientCd;

	private String transferClientName;

	private String trasferDate;

	private String bankMasterCd;

	private String bankKanaName;

	private String branchCd;

	private String branchKanaName;

	private String accountDivision;

	private String accountNo;

	private String dummy;

	/** ファイル作成日時 */
	private Timestamp dlDate;

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
	 * divisionCd取得.
	 * @return divisionCd
	 */
	public String getDivisionCd() {
		return this.divisionCd;
	}

	/**
	 * divisionCd設定.
	 * @param divisionCd divisionCd
	 */
	public void setDivisionCd(final String divisionCd) {
		this.divisionCd = divisionCd;
	}

	/**
	 * categoryDivision取得.
	 * @return categoryDivision
	 */
	public String getCategoryDivision() {
		return this.categoryDivision;
	}

	/**
	 * categoryDivision設定.
	 * @param categoryDivision categoryDivision
	 */
	public void setCategoryDivision(final String categoryDivision) {
		this.categoryDivision = categoryDivision;
	}

	/**
	 * transferClientCd取得.
	 * @return transferClientCd
	 */
	public String getTransferClientCd() {
		return this.transferClientCd;
	}

	/**
	 * transferClientCd設定.
	 * @param transferClientCd transferClientCd
	 */
	public void setTransferClientCd(final String transferClientCd) {
		this.transferClientCd = transferClientCd;
	}

	/**
	 * transferClientName取得.
	 * @return transferClientName
	 */
	public String getTransferClientName() {
		return this.transferClientName;
	}

	/**
	 * transferClientName設定.
	 * @param transferClientName transferClientName
	 */
	public void setTransferClientName(final String transferClientName) {
		this.transferClientName = transferClientName;
	}

	/**
	 * trasferDate取得.
	 * @return trasferDate
	 */
	public String getTrasferDate() {
		return this.trasferDate;
	}

	/**
	 * trasferDate設定.
	 * @param trasferDate trasferDate
	 */
	public void setTrasferDate(final String trasferDate) {
		this.trasferDate = trasferDate;
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
	 * accountDivision取得.
	 * @return accountDivision
	 */
	public String getAccountDivision() {
		return this.accountDivision;
	}

	/**
	 * accountDivision設定.
	 * @param accountDivision accountDivision
	 */
	public void setAccountDivision(final String accountDivision) {
		this.accountDivision = accountDivision;
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
	 * ファイル作成日時を取得します。
	 * @return Timestamp ファイル作成日時
	 */
	public Timestamp getDlDate() {
		return dlDate;
	}

	/**
	 * ファイル作成日時を設定します。
	 * @param dlDate ファイル作成日時
	 */
	public void setDlDate(final Timestamp dlDate) {
		this.dlDate = dlDate;
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
