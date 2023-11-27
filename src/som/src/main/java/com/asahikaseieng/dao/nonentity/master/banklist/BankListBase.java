/*
 * Created on 2009/05/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.banklist;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * BankListクラス.
 * @author t0011036
 */
public class BankListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public BankListBase() {
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

	private String bankKanaName;

	private String bankName;

	private String branchCd;

	private String branchName;

	private String bankMasterCd;

	private String bankMasterName;

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

