/*
 * Created on 2009/03/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.grecipe;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.dao.nonentity.grecipe.GrecipeLabelList;

/**
 * 詳細タブ Formクラス.
 * @author tosco
 */
public final class GrecipeSpecificsForm extends AbstractGrecipeForm {

	private static final long serialVersionUID = 1L;

	/** 原処方 検索結果一覧 */
	private List<GrecipeLabelList> searchGrList;

	/** ダウンロードフラグ */
	private Boolean downloadFlg;

	/** ダウンロード区分 */
	private String downloadDiv;

	/** リンク */
	private String srhLink;

	/**
	 * コンストラクタ.
	 */
	public GrecipeSpecificsForm() {
	}

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
		if ("init".equals(getOp())) {
			clear();
		}
		/* ダウンロードフラグを倒す */
		setDownloadFlg(false);

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
		return errors;
	}

	/**
	 * クリア処理.
	 */
	@Override
	protected void clear() {
		super.clear();

		// 原処方データリスト
		setSearchGrList(null);
		// ダウンロード区分
		setDownloadDiv(null);
		// ダウンロードフラグ
		setDownloadFlg(false);
	}

	// getter,setter

	/**
	 * 原処方 検索結果一覧を取得します。
	 * @return searchGrList
	 */
	public List<GrecipeLabelList> getSearchGrList() {
		return searchGrList;
	}

	/**
	 * 原処方 検索結果一覧を設定します。
	 * @param searchGrList 原処方 検索結果一覧
	 */
	public void setSearchGrList(final List<GrecipeLabelList> searchGrList) {
		this.searchGrList = searchGrList;
	}

	/**
	 * ダウンロード区分を取得します。
	 * @return downloadDiv
	 */
	public String getDownloadDiv() {
		return downloadDiv;
	}

	/**
	 * ダウンロード区分を設定します。
	 * @param downloadDiv ダウンロード区分
	 */
	public void setDownloadDiv(final String downloadDiv) {
		this.downloadDiv = downloadDiv;
	}

	/**
	 * ダウンロードフラグを取得します。
	 * @return downloadFlg
	 */
	public Boolean getDownloadFlg() {
		return downloadFlg;
	}

	/**
	 * ダウンロードフラグを設定します。
	 * @param downloadFlg ダウンロードフラグ
	 */
	public void setDownloadFlg(final Boolean downloadFlg) {
		this.downloadFlg = downloadFlg;
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
