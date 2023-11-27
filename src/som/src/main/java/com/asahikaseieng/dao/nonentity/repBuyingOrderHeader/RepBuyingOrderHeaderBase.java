/*
 * Created on 2009/06/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repBuyingOrderHeader;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * RepBuyingOrderHeaderクラス.
 * @author kanri-user
 */
public class RepBuyingOrderHeaderBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RepBuyingOrderHeaderBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション key */
	public static final String key_COLUMN = "KEY";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション venderName */
	public static final String venderName_COLUMN = "VENDER_NAME";

	/** COLUMNアノテーション payeeCd */
	public static final String payeeCd_COLUMN = "PAYEE_CD";

	/** COLUMNアノテーション payeeName */
	public static final String payeeName_COLUMN = "PAYEE_NAME";

	/** COLUMNアノテーション debitTitleCd */
	public static final String debitTitleCd_COLUMN = "DEBIT_TITLE_CD";

	/** COLUMNアノテーション debitTitleName */
	public static final String debitTitleName_COLUMN = "DEBIT_TITLE_NAME";

	/** COLUMNアノテーション creditTitleCd */
	public static final String creditTitleCd_COLUMN = "CREDIT_TITLE_CD";

	/** COLUMNアノテーション creditTitleName */
	public static final String creditTitleName_COLUMN = "CREDIT_TITLE_NAME";

	/** COLUMNアノテーション accountDebitSectionCd */
	public static final String accountDebitSectionCd_COLUMN = "ACCOUNT_DEBIT_SECTION_CD";

	/** COLUMNアノテーション accountDebitSectionName */
	public static final String accountDebitSectionName_COLUMN = "ACCOUNT_DEBIT_SECTION_NAME";

	/** COLUMNアノテーション accountCreditSectionCd */
	public static final String accountCreditSectionCd_COLUMN = "ACCOUNT_CREDIT_SECTION_CD";

	/** COLUMNアノテーション accountCreditSectionName */
	public static final String accountCreditSectionName_COLUMN = "ACCOUNT_CREDIT_SECTION_NAME";

	//
	// インスタンスフィールド
	//

	private String key;

	private String venderCd;

	private String venderName;

	private String payeeCd;

	private String payeeName;

	private String debitTitleCd;

	private String debitTitleName;

	private String creditTitleCd;

	private String creditTitleName;

	private String accountDebitSectionCd;

	private String accountDebitSectionName;

	private String accountCreditSectionCd;

	private String accountCreditSectionName;

	//
	// インスタンスメソッド
	//

	/**
	 * key取得.
	 * @return key
	 */
	public String getKey() {
		return this.key;
	}

	/**
	 * key設定.
	 * @param key key
	 */
	public void setKey(final String key) {
		this.key = key;
	}

	/**
	 * venderCd取得.
	 * @return venderCd
	 */
	public String getVenderCd() {
		return this.venderCd;
	}

	/**
	 * venderCd設定.
	 * @param venderCd venderCd
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * venderName取得.
	 * @return venderName
	 */
	public String getVenderName() {
		return this.venderName;
	}

	/**
	 * venderName設定.
	 * @param venderName venderName
	 */
	public void setVenderName(final String venderName) {
		this.venderName = venderName;
	}

	/**
	 * payeeCd取得.
	 * @return payeeCd
	 */
	public String getPayeeCd() {
		return this.payeeCd;
	}

	/**
	 * payeeCd設定.
	 * @param payeeCd payeeCd
	 */
	public void setPayeeCd(final String payeeCd) {
		this.payeeCd = payeeCd;
	}

	/**
	 * payeeName取得.
	 * @return payeeName
	 */
	public String getPayeeName() {
		return this.payeeName;
	}

	/**
	 * payeeName設定.
	 * @param payeeName payeeName
	 */
	public void setPayeeName(final String payeeName) {
		this.payeeName = payeeName;
	}

	/**
	 * debitTitleCd取得.
	 * @return debitTitleCd
	 */
	public String getDebitTitleCd() {
		return this.debitTitleCd;
	}

	/**
	 * debitTitleCd設定.
	 * @param debitTitleCd debitTitleCd
	 */
	public void setDebitTitleCd(final String debitTitleCd) {
		this.debitTitleCd = debitTitleCd;
	}

	/**
	 * debitTitleName取得.
	 * @return debitTitleName
	 */
	public String getDebitTitleName() {
		return this.debitTitleName;
	}

	/**
	 * debitTitleName設定.
	 * @param debitTitleName debitTitleName
	 */
	public void setDebitTitleName(final String debitTitleName) {
		this.debitTitleName = debitTitleName;
	}

	/**
	 * creditTitleCd取得.
	 * @return creditTitleCd
	 */
	public String getCreditTitleCd() {
		return this.creditTitleCd;
	}

	/**
	 * creditTitleCd設定.
	 * @param creditTitleCd creditTitleCd
	 */
	public void setCreditTitleCd(final String creditTitleCd) {
		this.creditTitleCd = creditTitleCd;
	}

	/**
	 * creditTitleName取得.
	 * @return creditTitleName
	 */
	public String getCreditTitleName() {
		return this.creditTitleName;
	}

	/**
	 * creditTitleName設定.
	 * @param creditTitleName creditTitleName
	 */
	public void setCreditTitleName(final String creditTitleName) {
		this.creditTitleName = creditTitleName;
	}

	/**
	 * accountDebitSectionCd取得.
	 * @return accountDebitSectionCd
	 */
	public String getAccountDebitSectionCd() {
		return this.accountDebitSectionCd;
	}

	/**
	 * accountDebitSectionCd設定.
	 * @param accountDebitSectionCd accountDebitSectionCd
	 */
	public void setAccountDebitSectionCd(final String accountDebitSectionCd) {
		this.accountDebitSectionCd = accountDebitSectionCd;
	}

	/**
	 * accountDebitSectionName取得.
	 * @return accountDebitSectionName
	 */
	public String getAccountDebitSectionName() {
		return this.accountDebitSectionName;
	}

	/**
	 * accountDebitSectionName設定.
	 * @param accountDebitSectionName accountDebitSectionName
	 */
	public void setAccountDebitSectionName(final String accountDebitSectionName) {
		this.accountDebitSectionName = accountDebitSectionName;
	}

	/**
	 * accountCreditSectionCd取得.
	 * @return accountCreditSectionCd
	 */
	public String getAccountCreditSectionCd() {
		return this.accountCreditSectionCd;
	}

	/**
	 * accountCreditSectionCd設定.
	 * @param accountCreditSectionCd accountCreditSectionCd
	 */
	public void setAccountCreditSectionCd(final String accountCreditSectionCd) {
		this.accountCreditSectionCd = accountCreditSectionCd;
	}

	/**
	 * accountCreditSectionName取得.
	 * @return accountCreditSectionName
	 */
	public String getAccountCreditSectionName() {
		return this.accountCreditSectionName;
	}

	/**
	 * accountCreditSectionName設定.
	 * @param accountCreditSectionName accountCreditSectionName
	 */
	public void setAccountCreditSectionName(final String accountCreditSectionName) {
		this.accountCreditSectionName = accountCreditSectionName;
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

