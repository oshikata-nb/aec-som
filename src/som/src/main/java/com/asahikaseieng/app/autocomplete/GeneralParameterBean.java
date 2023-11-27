/*
 * Created on 2009/02/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete;

import java.io.Serializable;

/**
 * コード・名称の汎用Bean AutoBeanみたく、ある特定処理用のフィールドを追加しないこと。 その場合は、継承して拡張すること。
 * （何のために、クラス名に汎用と付けているか考えること)
 * @author tosco
 */
public class GeneralParameterBean implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** コード */
	private String code;

	/** 名称 */
	private String name;

	private String bankCd;

	private String bankName;

	private String branchCd;

	private String branchName;

	private String bankMasterCd;

	private String bankMasterName;

	/**
	 * コンストラクタ
	 */
	public GeneralParameterBean() {
	}

	/**
	 * コンストラクタ
	 * @param code コード
	 * @param name 名称
	 */
	public GeneralParameterBean(final String code, final String name) {
		this.code = code;
		this.name = name;
	}

	/**
	 * コードを取得します。
	 * @return コード
	 */
	public String getCode() {
		return code;
	}

	/**
	 * コードを設定します。
	 * @param code コード
	 */
	public void setCode(final String code) {
		this.code = code;
	}

	/**
	 * 名称を取得します。
	 * @return 名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 名称を設定します。
	 * @param name 名称
	 */
	public void setName(final String name) {
		this.name = name;
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
	 * bankMasterCdを取得します。
	 * @return bankMasterCd
	 */
	public String getBankMasterCd() {
		return bankMasterCd;
	}

	/**
	 * bankMasterCdを設定します。
	 * @param bankMasterCd bankMasterCd
	 */
	public void setBankMasterCd(final String bankMasterCd) {
		this.bankMasterCd = bankMasterCd;
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
	 * bankNameを取得します。
	 * @return bankName
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * bankNameを設定します。
	 * @param bankName bankName
	 */
	public void setBankName(final String bankName) {
		this.bankName = bankName;
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
	 * branchNameを取得します。
	 * @return branchName
	 */
	public String getBranchName() {
		return branchName;
	}

	/**
	 * branchNameを設定します。
	 * @param branchName branchName
	 */
	public void setBranchName(final String branchName) {
		this.branchName = branchName;
	}
}
