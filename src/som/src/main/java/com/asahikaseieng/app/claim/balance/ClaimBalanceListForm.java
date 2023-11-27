/*
 * Created on 2008/07/01
 *
 * $copyright$
 */
package com.asahikaseieng.app.claim.balance;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.claim.balance.ClaimBalanceList;
import com.asahikaseieng.dao.nonentity.claim.balance.ClaimBalancePagerCondition;
import com.asahikaseieng.dao.nonentity.claim.balancelistforreport.ClaimBalanceListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 請求残高一覧 Formクラス
 * @author tosco
 */
public final class ClaimBalanceListForm extends AbstractSearchForm {

	private static final long serialVersionUID = 1L;

	private String dirtyFlg; /* 変更フラグ */

	/* ページの明細行数 */
	private static final int PAGE_ROW;

	/* 最大データ数 */
	private static final int DATA_ROW;

	static {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.SYSTEM_PROPERTIES);

		PAGE_ROW = Integer.parseInt(rb.getString("linage.common"));
		DATA_ROW = Integer.parseInt(rb.getString("threshold.common"));
	}

	//
	// 検索用.請求残高一覧表

	//

	/** 検索入力：部門コード */
	private String srhSectionCd;

	/** 検索入力：部門名称 */
	private String srhSectionName;

	/** 検索入力：担当者コード */
	private String srhTantoCd;

	/** 検索入力：担当者名称 */
	private String srhTantoNm;

	/** 検索入力：請求先コード */
	private String srhVenderCd;

	/** 検索入力：請求先名称 */
	private String srhVenderName;

	/** 検索入力：対象年月 */
	private String srhTargetMonth;

	/** 検索入力：出力区分 */
	private boolean srhOutputDivision;

	/** 検索入力：通常処理分 */
	private boolean srhNormalFlg;

	/** 検索入力：仮締処理分 */
	private boolean srhTempClosingFlg;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/** 帳票Excel用検索条件 */
	private ClaimBalanceListConditionForReport condition;

	//
	// 一覧用.請求残高一覧
	//

	/** リスト */
	private List<ClaimBalanceList> searchList;

	/**
	 * コンストラクタ.請求残高一覧マスタ
	 */
	public ClaimBalanceListForm() {
	}

	/**
	 * {@inheritDoc}
	 */
	protected int getLimit() {
		return PAGE_ROW;
	}

	/**
	 * {@inheritDoc}
	 */
	protected int getThreshold() {
		return DATA_ROW;
	}

	/**
	 * {@inheritDoc}
	 */
	protected Class getPagerConditionClass() {
		return ClaimBalancePagerCondition.class;
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

		if ("search".equals(getOp())) {
			/* チェックボックスの初期化 */
			// 検索入力：出力区分
			setSrhOutputDivision(false);
			// 検索入力：通常処理分
			setSrhNormalFlg(false);
			// 検索入力：仮締処理分
			setSrhTempClosingFlg(false);
			// 検索条件
			setCondition(null);
		}

		/* ダウンロードフラグを倒す */
		setExcelDownloadFlg(false);
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("search".equals(getOp())) {
			/* イレギュラーだけど、ここでリストをクリア */
			setSearchList(new ArrayList<ClaimBalanceList>());
		}

		// if ("search".equals(getOp())) {
		// /* Validatorによる判定 */
		// errors = super.validate(mapping, request);
		// if (!isSrhNormalFlg() && !isSrhTempClosingFlg()) {
		// // 対象区分を選択して下さい。
		// errors.add("srhNormalFlg",
		// new ActionMessage("errors.targetkbn"));
		// }
		// }
		return errors;
	}

	/**
	 * 初期化.所属マスタ
	 */
	public void clear() {

		// 検索リストのクリア */
		setSearchList(new ArrayList<ClaimBalanceList>());

		// 検索入力：部門コード
		setSrhSectionCd(null);
		// 検索入力：部門名称
		setSrhSectionName(null);
		// 検索入力：担当者コード
		setSrhTantoCd(null);
		// 検索入力：担当者名称
		setSrhTantoNm(null);
		// 検索入力：請求先コード
		setSrhVenderCd(null);
		// 検索入力：請求先名称
		setSrhVenderName(null);
		// 検索入力：対象年月
		setSrhTargetMonth(null);
		// 検索入力：出力区分
		setSrhOutputDivision(false);
		// 検索入力：通常処理分
		setSrhNormalFlg(false);
		// 検索入力：仮締処理分
		setSrhTempClosingFlg(false);

		// 変更フラグ
		setDirtyFlg(null);
	}

	/**
	 * 請求残高一覧表：searchListを取得します。
	 * 
	 * @return searchList
	 */
	public List<ClaimBalanceList> getSearchList() {
		return searchList;
	}

	/**
	 * 請求残高一覧表：searchListを設定します。
	 * 
	 * @param searchList searchList
	 */
	public void setSearchList(final List<ClaimBalanceList> searchList) {
		this.searchList = searchList;
	}

	//
	// 検索入力.請求残高一覧表
	//

	/**
	 * 検索入力：部門コード取得.
	 * @return srhSectionCd
	 */
	public String getSrhSectionCd() {
		return this.srhSectionCd;
	}

	/**
	 * 検索入力：部門コード設定.
	 * @param srhSectionCd sectionCd
	 */
	public void setSrhSectionCd(final String srhSectionCd) {
		this.srhSectionCd = srhSectionCd;
	}

	/**
	 * 検索入力：部門名称取得.
	 * @return srhSectionName
	 */
	public String getSrhSectionName() {
		return this.srhSectionName;
	}

	/**
	 * 検索入力：部門名称設定.
	 * @param srhSectionName srhSectionName
	 */
	public void setSrhSectionName(final String srhSectionName) {
		this.srhSectionName = srhSectionName;
	}

	/**
	 * 検索入力：担当者コードを取得します。
	 * @return srhTantoCd
	 */
	public String getSrhTantoCd() {
		return srhTantoCd;
	}

	/**
	 * 検索入力：担当者コードを設定します。
	 * @param srhTantoCd 検索入力：担当者コード
	 */
	public void setSrhTantoCd(final String srhTantoCd) {
		this.srhTantoCd = srhTantoCd;
	}

	/**
	 * 検索入力：担当者名称を取得します。
	 * @return srhTantoNm
	 */
	public String getSrhTantoNm() {
		return srhTantoNm;
	}

	/**
	 * 検索入力：担当者名称を設定します。
	 * @param srhTantoNm 検索入力：担当者名称
	 */
	public void setSrhTantoNm(final String srhTantoNm) {
		this.srhTantoNm = srhTantoNm;
	}

	/**
	 * 検索入力：請求先コード取得.
	 * @return String 請求先コード
	 */
	public String getSrhVenderCd() {
		return srhVenderCd;
	}

	/**
	 * 検索入力：請求先コード設定.
	 * @param srhVenderCd 請求先コード
	 */
	public void setSrhVenderCd(final String srhVenderCd) {
		this.srhVenderCd = srhVenderCd;
	}

	/**
	 * 検索入力：請求先名称取得.
	 * @return String 請求先名称
	 */
	public String getSrhVenderName() {
		return srhVenderName;
	}

	/**
	 * 検索入力：請求先名称設定.
	 * @param srhVenderName 請求先名称
	 */
	public void setSrhVenderName(final String srhVenderName) {
		this.srhVenderName = srhVenderName;
	}

	/**
	 * 検索入力：出力区分取得.
	 * @return boolean 出力区分
	 * 
	 */
	public boolean isSrhOutputDivision() {
		return srhOutputDivision;
	}

	/**
	 * 検索入力：出力区分設定.
	 * @param srhOutputDivision 出力区分
	 * 
	 */
	public void setSrhOutputDivision(final boolean srhOutputDivision) {
		this.srhOutputDivision = srhOutputDivision;
	}

	/**
	 * 検索入力：通常処理分取得.
	 * @return boolean 通常処理分
	 */
	public boolean isSrhNormalFlg() {
		return srhNormalFlg;
	}

	/**
	 * 検索入力：通常処理分設定
	 * @param srhNormalFlg 通常処理分
	 */
	public void setSrhNormalFlg(final boolean srhNormalFlg) {
		this.srhNormalFlg = srhNormalFlg;
	}

	/**
	 * 検索入力：仮締処理分取得.
	 * @return boolean 仮締処理分
	 */
	public boolean isSrhTempClosingFlg() {
		return srhTempClosingFlg;
	}

	/**
	 * 検索入力：仮締処理分設定
	 * @param srhTempClosingFlg 仮締処理分
	 */
	public void setSrhTempClosingFlg(final boolean srhTempClosingFlg) {
		this.srhTempClosingFlg = srhTempClosingFlg;
	}

	//
	// インスタンスメソッド.請求残高一覧
	//

	/**
	 * dirtyFlgを取得します。
	 * 
	 * @return dirtyFlg
	 */
	public String getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * dirtyFlgを設定します。
	 * 
	 * @param dirtyFlg dirtyFlg
	 */
	public void setDirtyFlg(final String dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
	}

	/**
	 * 対象年月を取得します。
	 * 
	 * @return srhTargetMonth
	 */
	public String getSrhTargetMonth() {
		return srhTargetMonth;
	}

	/**
	 * 対象年月を設定します。
	 * 
	 * @param srhTargetMonth 対象年月
	 */
	public void setSrhTargetMonth(final String srhTargetMonth) {
		this.srhTargetMonth = srhTargetMonth;
	}

	/**
	 * conditionを取得します。
	 * @return condition
	 */
	public ClaimBalanceListConditionForReport getCondition() {
		return condition;
	}

	/**
	 * conditionを設定します。
	 * @param condition condition
	 */
	public void setCondition(final ClaimBalanceListConditionForReport condition) {
		this.condition = condition;
	}

	/**
	 * excelDownloadFlgを取得します。
	 * @return excelDownloadFlg
	 */
	public boolean isExcelDownloadFlg() {
		return excelDownloadFlg;
	}

	/**
	 * excelDownloadFlgを設定します。
	 * @param excelDownloadFlg excelDownloadFlg
	 */
	public void setExcelDownloadFlg(final boolean excelDownloadFlg) {
		this.excelDownloadFlg = excelDownloadFlg;
	}
}
