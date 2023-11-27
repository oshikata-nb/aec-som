/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.bank;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.struts.AbstractForm;

/**
 * 銀行詳細 Formクラス.
 * @author t0011036
 */
public final class BankDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/* 銀行コード */
	private String bankCd;

	/* 銀行カナ名称 */
	private String bankKanaName;

	/* 銀行名称 */
	private String bankName;

	/* 支店コード */
	private String branchCd;

	/* 支店カナ名称 */
	private String branchKanaName;

	/* 支店名称 */
	private String branchName;

	/* 銀行マスタコード */
	private String bankMasterCd;

	/* 銀行マスタ名称 */
	private String bankMasterName;

	/* 振込手数料 */
	private BigDecimal fee;

	/* 更新日時 */
	private java.sql.Timestamp updateDate;

	/* 変更フラグ */
	private Boolean dirtyFlg;

	/* 新規フラグ */
	private String newFlg;

	/**
	 * コンストラクタ.
	 */
	public BankDetailForm() {
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {
		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			/* クリア処理 */
			clear();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("update".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}

		return errors;
	}

	/**
	 * クリア処理.
	 */
	public void clear() {
		setBankCd(null);
		setBankKanaName(null);
		setBankName(null);
		setBranchCd(null);
		setBranchName(null);
		setBranchKanaName(null);
		setBankMasterCd(null);
		setBankMasterName(null);
		setFee(new BigDecimal("440"));
		setUpdateDate(null);
		setDirtyFlg(null);
		setNewFlg(null);
	}

	/**
	 * newFlgを取得します。
	 * @return newFlg
	 */
	public String getNewFlg() {
		return newFlg;
	}

	/**
	 * newFlgを設定します。
	 * @param newFlg newFlg
	 */
	public void setNewFlg(final String newFlg) {
		this.newFlg = newFlg;
	}

	/**
	 * updateDateを取得します。
	 * @return updateDate
	 */
	public java.sql.Timestamp getUpdateDate() {
		return updateDate;
	}

	/**
	 * updateDateを設定します。
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * dirtyFlgを取得します。
	 * @return dirtyFlg
	 */
	public Boolean getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * dirtyFlgを設定します。
	 * @param dirtyFlg dirtyFlg
	 */
	public void setDirtyFlg(final Boolean dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
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

	/**
	 * feeを取得します。
	 * @return fee
	 */
	public BigDecimal getFee() {
		return fee;
	}

	/**
	 * feeを設定します。
	 * @param fee fee
	 */
	public void setFee(final BigDecimal fee) {
		this.fee = fee;
	}

	/**
	 * bankKanaNameを取得します。
	 * @return bankKanaName
	 */
	public String getBankKanaName() {
		return bankKanaName;
	}

	/**
	 * bankKanaNameを設定します。
	 * @param bankKanaName bankKanaName
	 */
	public void setBankKanaName(final String bankKanaName) {
		this.bankKanaName = bankKanaName;
	}

	/**
	 * branchKanaNameを取得します。
	 * @return branchKanaName
	 */
	public String getBranchKanaName() {
		return branchKanaName;
	}

	/**
	 * branchKanaNameを設定します。
	 * @param branchKanaName branchKanaName
	 */
	public void setBranchKanaName(final String branchKanaName) {
		this.branchKanaName = branchKanaName;
	}
}
