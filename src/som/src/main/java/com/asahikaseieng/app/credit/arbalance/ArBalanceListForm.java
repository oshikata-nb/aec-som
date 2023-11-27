/*
 * Created on 2008/07/01
 *
 * $copyright$
 */
package com.asahikaseieng.app.credit.arbalance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.credit.arbalance.ArBalanceList;
import com.asahikaseieng.dao.nonentity.credit.arbalance.ArBalancePagerCondition;
import com.asahikaseieng.dao.nonentity.credit.arbalanceforreport.ArBalanceListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 売掛残高一覧表 Formクラス
 * @author tosco
 */
public final class ArBalanceListForm extends AbstractSearchForm {

	private static final long serialVersionUID = 1L;

	/* 変更フラグ */
	private String dirtyFlg;

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
	// 検索用.売掛残高一覧表
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

	/** 検索入力：出力区分（売掛残） */
	private String srhCreditAmountDivi;

	/** 検索入力：出力区分（未収金残） */
	private String srhAccruedDebitDivi;

	/** 検索入力：通常処理分 */
	private boolean srhNormalFlg;

	/** 検索入力：仮締処理分 */
	private boolean srhTempClosingFlg;

	/** 検索入力：対象区分 */
	private BigDecimal srhNormalTemp;

	//
	// 一覧用.売掛残高一覧
	//

	/** リスト */
	private List<ArBalanceList> searchList;

	/* 帳票Excel用検索条件 */
	private ArBalanceListConditionForReport condition;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/**
	 * コンストラクタ.売掛残高一覧From
	 */
	public ArBalanceListForm() {
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
		return ArBalancePagerCondition.class;
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
			// 検索入力：出力区分（売掛残）
			setSrhCreditAmountDivi(null);
			// 検索入力：出力区分（未収金残）
			setSrhAccruedDebitDivi(null);
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
			setSearchList(new ArrayList<ArBalanceList>());
		}

		return errors;
	}

	/**
	 * 初期化.売掛残高一覧
	 */
	public void clear() {

		// 検索リストのクリア
		setSearchList(new ArrayList<ArBalanceList>());

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
		// 検索入力：出力区分（売掛残）
		setSrhCreditAmountDivi(null);
		// 検索入力：出力区分（未収金残）
		setSrhAccruedDebitDivi(null);
		// 検索入力：通常処理分
		setSrhNormalFlg(false);
		// 検索入力：仮締処理分
		setSrhTempClosingFlg(false);
		// 検索入力：対象区分
		setSrhNormalTemp(new BigDecimal("0"));
	}

	/**
	 * 売掛残高一覧表：searchListを取得します。
	 * 
	 * @return searchList
	 */
	public List<ArBalanceList> getSearchList() {
		return searchList;
	}

	/**
	 * 売掛残高一覧表：searchListを設定します。
	 * 
	 * @param searchList searchList
	 */
	public void setSearchList(final List<ArBalanceList> searchList) {
		this.searchList = searchList;
	}

	//
	// 検索入力.売掛残高一覧表

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
	 * @param srhSectionCd BumonCd
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
	 * 検索入力：対象年月を取得します。
	 * 
	 * @return srhTargetMonth
	 */
	public String getSrhTargetMonth() {
		return srhTargetMonth;
	}

	/**
	 * 検索入力：対象年月を設定します。
	 * 
	 * @param srhTargetMonth 対象年月
	 */
	public void setSrhTargetMonth(final String srhTargetMonth) {
		this.srhTargetMonth = srhTargetMonth;
	}

	/**
	 * 検索入力：出力区分（売掛残）
	 * @return String 出力区分
	 * 
	 */
	public String getSrhCreditAmountDivi() {
		return srhCreditAmountDivi;
	}

	/**
	 * 検索入力：出力区分（売掛残）
	 * @param srhCreditAmountDivi 検索入力：出力区分（売掛残）
	 * 
	 */
	public void setSrhCreditAmountDivi(final String srhCreditAmountDivi) {
		this.srhCreditAmountDivi = srhCreditAmountDivi;
	}

	/**
	 * 検索入力：出力区分（未収金残）を取得します。
	 * @return srhAccruedDebitDivi
	 */
	public String getSrhAccruedDebitDivi() {
		return srhAccruedDebitDivi;
	}

	/**
	 * 検索入力：出力区分（未収金残）を設定します。
	 * @param srhAccruedDebitDivi 検索入力：出力区分（未収金残）
	 */
	public void setSrhAccruedDebitDivi(final String srhAccruedDebitDivi) {
		this.srhAccruedDebitDivi = srhAccruedDebitDivi;
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
	// インスタンスメソッド.売掛残高一覧
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
	 * srhNormalTempを取得します。
	 * @return srhNormalTemp
	 */
	public BigDecimal getSrhNormalTemp() {
		return srhNormalTemp;
	}

	/**
	 * srhNormalTempを設定します。
	 * @param srhNormalTemp srhNormalTemp
	 */
	public void setSrhNormalTemp(final BigDecimal srhNormalTemp) {
		this.srhNormalTemp = srhNormalTemp;
	}

	/**
	 * conditionを取得します。
	 * @return condition
	 */
	public ArBalanceListConditionForReport getCondition() {
		return condition;
	}

	/**
	 * conditionを設定します。
	 * @param condition condition
	 */
	public void setCondition(final ArBalanceListConditionForReport condition) {
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
