/*
 * Created on 2009/03/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.grecipe;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

/**
 * その他タブ Formクラス.
 * @author tosco
 */
public final class GrecipeOtherForm extends AbstractGrecipeForm {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public GrecipeOtherForm() {
	}

	/** 備考 */
	private String generalRecipeRemark;

	/** 変更フラグ */
	private String insertFlg;

	/** リンク */
	private String srhLink;

	/**
	 * Beanの全てのプロパティをデフォルトの状態にリセット
	 * 
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
	 */
	@Override
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

	}

	/**
	 * 入力データの検証
	 * 
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
	 * @return ActionErrors 検証エラー内容
	 */
	@Override
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;
		if ("regist".equals(getOp())) {
			// Validatorによる判定
			errors = super.validate(mapping, request);
		}
		if ("insert".equals(getOp())) {
			// Validatorによる判定
			errors = super.validate(mapping, request);
		}
		return errors;
	}

	/**
	 * クリア処理.
	 */
	@Override
	protected void clear() {
		super.clear();

		/** 備考 */
		setGeneralRecipeRemark(null);
		/** 変更フラグ */
		setInsertFlg(null);
	}

	/**
	 * 原処方備考を取得します。
	 * @return generalRecipeRemark
	 */
	public String getGeneralRecipeRemark() {
		return generalRecipeRemark;
	}

	/**
	 * 原処方備考を設定します。
	 * @param generalRecipeRemark 原処方備考
	 */
	public void setGeneralRecipeRemark(final String generalRecipeRemark) {
		this.generalRecipeRemark = generalRecipeRemark;
	}

	/**
	 * 変更フラグを取得します。
	 * @return insertFlg
	 */
	public String getInsertFlg() {
		return insertFlg;
	}

	/**
	 * 変更フラグを設定します。
	 * @param insertFlg 変更フラグ
	 */
	public void setInsertFlg(final String insertFlg) {
		this.insertFlg = insertFlg;
	}

	/**
	 * srhLinkを取得します。
	 * @return srhLink
	 */
	public String getSrhLink() {
		return srhLink;
	}

	/**
	 * srhLinkを設定します。
	 * @param srhLink srhLink
	 */
	public void setSrhLink(final String srhLink) {
		this.srhLink = srhLink;
	}
}
