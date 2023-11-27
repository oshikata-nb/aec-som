/*
 * Created on 2007/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.bumon;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.struts.AbstractForm;

/**
 * 会計部門マスタ詳細 Formクラス
 * @author TanakaSatoru
 */
public final class BumonDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	private String dirtyFlg; /* 変更フラグ */

	//
	// インスタンスフィールド
	//

	/** 会計部門コード */
	private String sectionCd;

	/** 会計部門名称 */
	private String sectionName;

	/** 会計部門略称 */
	private String sectionShortedName;

	/** 会計システム連結コード */
	private String accountCd;

	/** 更新日時 */
	private java.sql.Timestamp updateDate;

	// 新規用切替フラグ
	private String newFlg;

	/**
	 * コンストラクタ.会計部門マスタ詳細
	 */
	public BumonDetailForm() {
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		if ("init".equals(getOp())) {
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

		/** 会計部門コード */
		setSectionCd(null);

		/** 会計部門名称 */
		setSectionName(null);

		/** 会計部門略称名称 */
		setSectionShortedName(null);

		/** 会計システム連結コード */
		setAccountCd(null);

		/** 更新日時 */
		setUpdateDate(null);

		// 新規更新切替フラグ
		setNewFlg(null);

		setDirtyFlg(null);
	}

	//	
	// インスタンスメソッド
	//		

	/**
	 * 会計部門コード取得.
	 * @return sectionCd
	 */
	public String getSectionCd() {
		return this.sectionCd;
	}

	/**
	 * 会計部門コード設定.
	 * @param sectionCd sectionCd
	 */
	public void setSectionCd(final String sectionCd) {
		this.sectionCd = sectionCd;
	}

	/**
	 * 会計部門名称取得.
	 * @return sectionName
	 */
	public String getSectionName() {
		return this.sectionName;
	}

	/**
	 * 会計部門名称設定.
	 * @param sectionName sectionName
	 */
	public void setSectionName(final String sectionName) {
		this.sectionName = sectionName;
	}

	/**
	 * 会計部門略称取得.
	 * @return sectionShortedName
	 */
	public String getSectionShortedName() {
		return this.sectionShortedName;
	}

	/**
	 * 会計部門略称設定.
	 * @param sectionShortedName sectionShortedName
	 */
	public void setSectionShortedName(final String sectionShortedName) {
		this.sectionShortedName = sectionShortedName;
	}

	/**
	 * 更新日時取得.
	 * @return updateDate
	 */
	public java.sql.Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * 更新日時設定.
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * dirtyFlgを取得します。
	 * @return dirtyFlg
	 */
	public String getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * dirtyFlgを設定します。
	 * @param dirtyFlg dirtyFlg
	 */
	public void setDirtyFlg(final String dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
	}

	/**
	 * accountCdを取得します。
	 * @return accountCd
	 */
	public String getAccountCd() {
		return accountCd;
	}

	/**
	 * accountCdを設定します。
	 * @param accountCd accountCd
	 */
	public void setAccountCd(final String accountCd) {
		this.accountCd = accountCd;
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
}
