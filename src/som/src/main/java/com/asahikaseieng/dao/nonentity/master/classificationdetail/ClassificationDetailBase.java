/*
 * Created on 2009/09/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.classificationdetail;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * ClassificationDetailクラス.
 * @author t0011036
 */
public class ClassificationDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ClassificationDetailBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション dataType */
	public static final String dataType_COLUMN = "DATA_TYPE";

	/** COLUMNアノテーション categoryDivision */
	public static final String categoryDivision_COLUMN = "CATEGORY_DIVISION";

	/** COLUMNアノテーション dataTotalDivision */
	public static final String dataTotalDivision_COLUMN = "DATA_TOTAL_DIVISION";

	/** COLUMNアノテーション categoryName */
	public static final String categoryName_COLUMN = "CATEGORY_NAME";

	/** COLUMNアノテーション debitAccountsCd */
	public static final String debitAccountsCd_COLUMN = "DEBIT_ACCOUNTS_CD";

	/** COLUMNアノテーション debitAccountsSubCd */
	public static final String debitAccountsSubCd_COLUMN = "DEBIT_ACCOUNTS_SUB_CD";

	/** COLUMNアノテーション creditAccountsCd */
	public static final String creditAccountsCd_COLUMN = "CREDIT_ACCOUNTS_CD";

	/** COLUMNアノテーション creditAccountsSubCd */
	public static final String creditAccountsSubCd_COLUMN = "CREDIT_ACCOUNTS_SUB_CD";

	/** COLUMNアノテーション bankDivision */
	public static final String bankDivision_COLUMN = "BANK_DIVISION";

	/** COLUMNアノテーション noteDivision */
	public static final String noteDivision_COLUMN = "NOTE_DIVISION";

	/** COLUMNアノテーション arDivision */
	public static final String arDivision_COLUMN = "AR_DIVISION";

	/** COLUMNアノテーション claimDivision */
	public static final String claimDivision_COLUMN = "CLAIM_DIVISION";

	/** COLUMNアノテーション creditDivision */
	public static final String creditDivision_COLUMN = "CREDIT_DIVISION";

	/** COLUMNアノテーション paymentDivision */
	public static final String paymentDivision_COLUMN = "PAYMENT_DIVISION";

	/** COLUMNアノテーション showflg */
	public static final String showFlg_COLUMN = "SHOW_FLG";
	
	//
	// インスタンスフィールド
	//

	private java.math.BigDecimal dataType;

	private String categoryDivision;

	private java.math.BigDecimal dataTotalDivision;

	private String categoryName;

	private String debitAccountsCd;

	private String debitAccountsSubCd;

	private String creditAccountsCd;

	private String creditAccountsSubCd;

	private java.math.BigDecimal bankDivision;

	private java.math.BigDecimal noteDivision;

	private java.math.BigDecimal arDivision;

	private java.math.BigDecimal claimDivision;

	private java.math.BigDecimal creditDivision;

	private java.math.BigDecimal paymentDivision;

	private java.math.BigDecimal showFlg;
	//
	// インスタンスメソッド
	//

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
	 * dataTotalDivision取得.
	 * @return dataTotalDivision
	 */
	public java.math.BigDecimal getDataTotalDivision() {
		return this.dataTotalDivision;
	}

	/**
	 * dataTotalDivision設定.
	 * @param dataTotalDivision dataTotalDivision
	 */
	public void setDataTotalDivision(final java.math.BigDecimal dataTotalDivision) {
		this.dataTotalDivision = dataTotalDivision;
	}

	/**
	 * categoryName取得.
	 * @return categoryName
	 */
	public String getCategoryName() {
		return this.categoryName;
	}

	/**
	 * categoryName設定.
	 * @param categoryName categoryName
	 */
	public void setCategoryName(final String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * debitAccountsCd取得.
	 * @return debitAccountsCd
	 */
	public String getDebitAccountsCd() {
		return this.debitAccountsCd;
	}

	/**
	 * debitAccountsCd設定.
	 * @param debitAccountsCd debitAccountsCd
	 */
	public void setDebitAccountsCd(final String debitAccountsCd) {
		this.debitAccountsCd = debitAccountsCd;
	}

	/**
	 * debitAccountsSubCd取得.
	 * @return debitAccountsSubCd
	 */
	public String getDebitAccountsSubCd() {
		return this.debitAccountsSubCd;
	}

	/**
	 * debitAccountsSubCd設定.
	 * @param debitAccountsSubCd debitAccountsSubCd
	 */
	public void setDebitAccountsSubCd(final String debitAccountsSubCd) {
		this.debitAccountsSubCd = debitAccountsSubCd;
	}

	/**
	 * creditAccountsCd取得.
	 * @return creditAccountsCd
	 */
	public String getCreditAccountsCd() {
		return this.creditAccountsCd;
	}

	/**
	 * creditAccountsCd設定.
	 * @param creditAccountsCd creditAccountsCd
	 */
	public void setCreditAccountsCd(final String creditAccountsCd) {
		this.creditAccountsCd = creditAccountsCd;
	}

	/**
	 * creditAccountsSubCd取得.
	 * @return creditAccountsSubCd
	 */
	public String getCreditAccountsSubCd() {
		return this.creditAccountsSubCd;
	}

	/**
	 * creditAccountsSubCd設定.
	 * @param creditAccountsSubCd creditAccountsSubCd
	 */
	public void setCreditAccountsSubCd(final String creditAccountsSubCd) {
		this.creditAccountsSubCd = creditAccountsSubCd;
	}

	/**
	 * bankDivision取得.
	 * @return bankDivision
	 */
	public java.math.BigDecimal getBankDivision() {
		return this.bankDivision;
	}

	/**
	 * bankDivision設定.
	 * @param bankDivision bankDivision
	 */
	public void setBankDivision(final java.math.BigDecimal bankDivision) {
		this.bankDivision = bankDivision;
	}

	/**
	 * noteDivision取得.
	 * @return noteDivision
	 */
	public java.math.BigDecimal getNoteDivision() {
		return this.noteDivision;
	}

	/**
	 * noteDivision設定.
	 * @param noteDivision noteDivision
	 */
	public void setNoteDivision(final java.math.BigDecimal noteDivision) {
		this.noteDivision = noteDivision;
	}

	/**
	 * arDivision取得.
	 * @return arDivision
	 */
	public java.math.BigDecimal getArDivision() {
		return this.arDivision;
	}

	/**
	 * arDivision設定.
	 * @param arDivision arDivision
	 */
	public void setArDivision(final java.math.BigDecimal arDivision) {
		this.arDivision = arDivision;
	}

	/**
	 * claimDivision取得.
	 * @return claimDivision
	 */
	public java.math.BigDecimal getClaimDivision() {
		return this.claimDivision;
	}

	/**
	 * claimDivision設定.
	 * @param claimDivision claimDivision
	 */
	public void setClaimDivision(final java.math.BigDecimal claimDivision) {
		this.claimDivision = claimDivision;
	}

	/**
	 * creditDivision取得.
	 * @return creditDivision
	 */
	public java.math.BigDecimal getCreditDivision() {
		return this.creditDivision;
	}

	/**
	 * creditDivision設定.
	 * @param creditDivision creditDivision
	 */
	public void setCreditDivision(final java.math.BigDecimal creditDivision) {
		this.creditDivision = creditDivision;
	}

	/**
	 * paymentDivision取得.
	 * @return paymentDivision
	 */
	public java.math.BigDecimal getPaymentDivision() {
		return this.paymentDivision;
	}

	/**
	 * paymentDivision設定.
	 * @param paymentDivision paymentDivision
	 */
	public void setPaymentDivision(final java.math.BigDecimal paymentDivision) {
		this.paymentDivision = paymentDivision;
	}

	/**
	 * showFlgを取得します。
	 * @return showFlg
	 */
	public java.math.BigDecimal getShowFlg() {
		return showFlg;
	}

	/**
	 * showFlgを設定します。
	 * @param showFlg showFlg
	 */
	public void setShowFlg(java.math.BigDecimal showFlg) {
		this.showFlg = showFlg;
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

