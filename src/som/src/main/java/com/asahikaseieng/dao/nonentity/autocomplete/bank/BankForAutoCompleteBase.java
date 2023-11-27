/*
 * Created on 2009/05/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.bank;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * BankForAutoCompleteクラス.
 * @author t0011036
 */
public class BankForAutoCompleteBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public BankForAutoCompleteBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション bankCd */
	public static final String bankCd_COLUMN = "BANK_CD";

	/** COLUMNアノテーション bankName */
	public static final String bankName_COLUMN = "BANK_NAME";

	/** COLUMNアノテーション branchCd */
	public static final String branchCd_COLUMN = "BRANCH_CD";

	/** COLUMNアノテーション branchName */
	public static final String branchName_COLUMN = "BRANCH_NAME";

	/** COLUMNアノテーション bankMasterCd */
	public static final String bankMasterCd_COLUMN = "BANK_MASTER_CD";

	/** COLUMNアノテーション bankMasterName */
	public static final String bankMasterName_COLUMN = "BANK_MASTER_NAME";

	//
	// インスタンスフィールド
	//

	private String bankCd;

	private String bankName;

	private String branchCd;

	private String branchName;

	private String bankMasterCd;

	private String bankMasterName;

	//
	// インスタンスメソッド
	//

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
	 * bankCdを取得します。
	 * @return bankCd
	 */
	public String getBankCd() {
		return bankCd;
	}

	/**
	 * bankCdを設定します。
	 * @param bankCd bankCd
	 */
	public void setBankCd(final String bankCd) {
		this.bankCd = bankCd;
	}

	/**
	 * bankMasterNameを取得します。
	 * @return bankMasterName
	 */
	public String getBankMasterName() {
		return bankMasterName;
	}

	/**
	 * bankMasterNameを設定します。
	 * @param bankMasterName bankMasterName
	 */
	public void setBankMasterName(final String bankMasterName) {
		this.bankMasterName = bankMasterName;
	}

	/**
	 * branchCdを取得します。
	 * @return branchCd
	 */
	public String getBranchCd() {
		return branchCd;
	}

	/**
	 * branchCdを設定します。
	 * @param branchCd branchCd
	 */
	public void setBranchCd(final String branchCd) {
		this.branchCd = branchCd;
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
