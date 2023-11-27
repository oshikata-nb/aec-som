/*
 * Created on 2009/08/25
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.banklistforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * BankListForReportクラス.
 * @author t0011036
 */
public class BankListForReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public BankListForReportBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション bankCd */
	public static final String bankCd_COLUMN = "BANK_CD";

	/** COLUMNアノテーション bankKanaName */
	public static final String bankKanaName_COLUMN = "BANK_KANA_NAME";

	/** COLUMNアノテーション bankName */
	public static final String bankName_COLUMN = "BANK_NAME";

	/** COLUMNアノテーション branchCd */
	public static final String branchCd_COLUMN = "BRANCH_CD";

	/** COLUMNアノテーション branchKanaName */
	public static final String branchKanaName_COLUMN = "BRANCH_KANA_NAME";

	/** COLUMNアノテーション branchName */
	public static final String branchName_COLUMN = "BRANCH_NAME";

	/** COLUMNアノテーション bankMasterCd */
	public static final String bankMasterCd_COLUMN = "BANK_MASTER_CD";

	/** COLUMNアノテーション bankMasterName */
	public static final String bankMasterName_COLUMN = "BANK_MASTER_NAME";

	/** COLUMNアノテーション calCd */
	public static final String calCd_COLUMN = "CAL_CD";

	/** COLUMNアノテーション dataType */
	public static final String dataType_COLUMN = "DATA_TYPE";

	/** COLUMNアノテーション categoryDivision */
	public static final String categoryDivision_COLUMN = "CATEGORY_DIVISION";

	/** COLUMNアノテーション fee */
	public static final String fee_COLUMN = "FEE";

	/** COLUMNアノテーション homeComDealCd */
	public static final String homeComDealCd_COLUMN = "HOME_COM_DEAL_CD";

	/** COLUMNアノテーション homeComDealName */
	public static final String homeComDealName_COLUMN = "HOME_COM_DEAL_NAME";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション inputorName */
	public static final String inputorName_COLUMN = "INPUTOR_NAME";

	/** COLUMNアノテーション updatorName */
	public static final String updatorName_COLUMN = "UPDATOR_NAME";

	//
	// インスタンスフィールド
	//

	private String bankCd;

	private String bankKanaName;

	private String bankName;

	private String branchCd;

	private String branchKanaName;

	private String branchName;

	private String bankMasterCd;

	private String bankMasterName;

	private String calCd;

	private java.math.BigDecimal dataType;

	private String categoryDivision;

	private java.math.BigDecimal fee;

	private String homeComDealCd;

	private String homeComDealName;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	private String inputorName;

	private String updatorName;

	//
	// インスタンスメソッド
	//

	/**
	 * bankCd取得.
	 * @return bankCd
	 */
	public String getBankCd() {
		return this.bankCd;
	}

	/**
	 * bankCd設定.
	 * @param bankCd bankCd
	 */
	public void setBankCd(final String bankCd) {
		this.bankCd = bankCd;
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
	 * bankName取得.
	 * @return bankName
	 */
	public String getBankName() {
		return this.bankName;
	}

	/**
	 * bankName設定.
	 * @param bankName bankName
	 */
	public void setBankName(final String bankName) {
		this.bankName = bankName;
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
	 * branchName取得.
	 * @return branchName
	 */
	public String getBranchName() {
		return this.branchName;
	}

	/**
	 * branchName設定.
	 * @param branchName branchName
	 */
	public void setBranchName(final String branchName) {
		this.branchName = branchName;
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
	 * bankMasterName取得.
	 * @return bankMasterName
	 */
	public String getBankMasterName() {
		return this.bankMasterName;
	}

	/**
	 * bankMasterName設定.
	 * @param bankMasterName bankMasterName
	 */
	public void setBankMasterName(final String bankMasterName) {
		this.bankMasterName = bankMasterName;
	}

	/**
	 * calCd取得.
	 * @return calCd
	 */
	public String getCalCd() {
		return this.calCd;
	}

	/**
	 * calCd設定.
	 * @param calCd calCd
	 */
	public void setCalCd(final String calCd) {
		this.calCd = calCd;
	}

	/**
	 * dataType取得.
	 * @return dataType
	 */
	public java.math.BigDecimal getDataType() {
		return this.dataType;
	}

	/**
	 * dataType設定.
	 * @param dataType dataType
	 */
	public void setDataType(final java.math.BigDecimal dataType) {
		this.dataType = dataType;
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
	 * fee取得.
	 * @return fee
	 */
	public java.math.BigDecimal getFee() {
		return this.fee;
	}

	/**
	 * fee設定.
	 * @param fee fee
	 */
	public void setFee(final java.math.BigDecimal fee) {
		this.fee = fee;
	}

	/**
	 * homeComDealCd取得.
	 * @return homeComDealCd
	 */
	public String getHomeComDealCd() {
		return this.homeComDealCd;
	}

	/**
	 * homeComDealCd設定.
	 * @param homeComDealCd homeComDealCd
	 */
	public void setHomeComDealCd(final String homeComDealCd) {
		this.homeComDealCd = homeComDealCd;
	}

	/**
	 * homeComDealName取得.
	 * @return homeComDealName
	 */
	public String getHomeComDealName() {
		return this.homeComDealName;
	}

	/**
	 * homeComDealName設定.
	 * @param homeComDealName homeComDealName
	 */
	public void setHomeComDealName(final String homeComDealName) {
		this.homeComDealName = homeComDealName;
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

