/*
 * Created on 2009/01/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mgrecipe;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeLabelList;

/**
 * 詳細タブ Formクラス.
 * @author tosco
 */
public final class MgrecipeSpecificsForm extends AbstractMgrecipeForm {

	private static final long serialVersionUID = 1L;

	/** 原処方 検索結果一覧 */
	private List<MgrecipeLabelList> searchGrList;

	/** 基本処方 検索結果一覧 */
	private List<MgrecipeLabelList> searchMrList;

	/** ダウンロードフラグ */
	private Boolean downloadFlg;

	/** ダウンロード区分 */
	private String downloadDiv;

	/** 原処方レシピインデックスフラグ */
	private String oriRecFlg;

	/** リンク */
	private String srhLink;

	/**
	 * コンストラクタ.
	 */
	public MgrecipeSpecificsForm() {
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
			//Validatorによる判定
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
		// 基本処方データリスト
		setSearchMrList(null);
		// ダウンロード区分
		setDownloadDiv(null);
		// ダウンロードフラグ
		setDownloadFlg(false);
		// 原処方レシピインデックスフラグ
		setOriRecFlg(null);
	}

	//getter,setter

	/**
	 * 原処方 検索結果一覧を取得します。
	 * @return searchGrList
	 */
	public List<MgrecipeLabelList> getSearchGrList() {
		return searchGrList;
	}

	/**
	 * 原処方 検索結果一覧を設定します。
	 * @param searchGrList 原処方 検索結果一覧
	 */
	public void setSearchGrList(final List<MgrecipeLabelList> searchGrList) {
		this.searchGrList = searchGrList;
	}

	/**
	 * 基本処方 検索結果一覧を取得します。
	 * @return searchMrList
	 */
	public List<MgrecipeLabelList> getSearchMrList() {
		return searchMrList;
	}

	/**
	 * 基本処方 検索結果一覧を設定します。
	 * @param searchMrList 基本処方 検索結果一覧
	 */
	public void setSearchMrList(final List<MgrecipeLabelList> searchMrList) {
		this.searchMrList = searchMrList;
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
	 * 原処方レシピインデックスフラグを取得します。
	 * @return oriRecFlg
	 */
	public String getOriRecFlg() {
		return oriRecFlg;
	}

	/**
	 * 原処方レシピインデックスフラグを設定します。
	 * @param oriRecFlg 原処方レシピインデックスフラグ
	 */
	public void setOriRecFlg(final String oriRecFlg) {
		this.oriRecFlg = oriRecFlg;
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
