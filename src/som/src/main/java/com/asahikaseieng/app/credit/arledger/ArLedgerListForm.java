/*
 * Created on 2008/07/01
 *
 * $copyright$
 */
package com.asahikaseieng.app.credit.arledger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.credit.arledger.ArLedgerList;
import com.asahikaseieng.dao.nonentity.credit.arledger.ArLedgerPagerCondition;
import com.asahikaseieng.dao.nonentity.credit.arledgerlistforreport.RepArLedgerConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 売掛元帳 Formクラス
 * @author tosco
 */
public final class ArLedgerListForm extends AbstractSearchForm {

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
	// 検索用.売掛元帳
	//

	/** 検索入力：部門コード */
	private String srhSectionCd;

	/** 検索入力：部門名称 */
	private String srhSectionName;

	/** 検索入力：担当者コード */
	private String srhTantoCd;

	/** 検索入力：担当者名称 */
	private String srhTantoNm;

	/** 検索入力：取引先コード */
	private String srhVenderCd;

	/** 検索入力：取引先名称 */
	private String srhVenderName;

	/** 検索入力：対象年月 */
	private String srhTargetMonth;

	/** 検索入力：通常処理分 */
	private boolean srhNormalFlg;

	/** 検索入力：仮締処理分 */
	private boolean srhTempClosingFlg;

	/** 検索入力：対象区分 */
	private BigDecimal srhNormalTemp;

	/** 帳票検索用 */
	private RepArLedgerConditionForReport reportCondition;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	//
	// 一覧用.売掛元帳
	//

	/** リスト */
	private List<ArLedgerList> searchList;

	/**
	 * コンストラクタ.売掛元帳
	 */
	public ArLedgerListForm() {
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
		return ArLedgerPagerCondition.class;
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
			// 検索入力：通常処理分
			setSrhNormalFlg(false);
			// 検索入力：仮締処理分
			setSrhTempClosingFlg(false);
		}

		// Excelダウンロードフラグ
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
			setSearchList(new ArrayList<ArLedgerList>());
		}

		if ("search".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}
		return errors;
	}

	/**
	 * 初期化.売掛元帳
	 */
	public void clear() {

		// 検索リストのクリア
		setSearchList(new ArrayList<ArLedgerList>());

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
		// 検索入力：通常処理分
		setSrhNormalFlg(false);
		// 検索入力：仮締処理分
		setSrhTempClosingFlg(false);
		// 検索入力：対象区分
		setSrhNormalTemp(new BigDecimal("0"));
		// 帳票検索条件
		setReportCondition(null);
	}

	/**
	 * 検索結果リストを取得します。
	 * @return List<ArLedgerList> 検索結果リスト
	 * 
	 */
	public List<ArLedgerList> getSearchList() {
		return searchList;
	}

	/**
	 * 検索結果リストを設定します。
	 * @param searchList 検索結果リスト
	 * 
	 */
	public void setSearchList(final List<ArLedgerList> searchList) {
		this.searchList = searchList;
	}

	//
	// 検索入力.売掛元帳
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
	 * 検索入力：取引先コード取得.
	 * @return String 取引先コード
	 * 
	 */
	public String getSrhVenderCd() {
		return srhVenderCd;
	}

	/**
	 * 検索入力：取引先コード設定.
	 * @param srhVenderCd 取引先コード
	 * 
	 */
	public void setSrhVenderCd(final String srhVenderCd) {
		this.srhVenderCd = srhVenderCd;
	}

	/**
	 * 検索入力：取引先名称取得.
	 * @return String 取引先名称
	 */
	public String getSrhVenderName() {
		return srhVenderName;
	}

	/**
	 * 検索入力：取引先名称設定.
	 * @param srhVenderName 取引先名称
	 */
	public void setSrhVenderName(final String srhVenderName) {
		this.srhVenderName = srhVenderName;
	}

	/**
	 * 対象年月を取得します。
	 * @return srhTargetMonth
	 */
	public String getSrhTargetMonth() {
		return srhTargetMonth;
	}

	/**
	 * 対象年月を設定します。
	 * @param srhTargetMonth 対象年月
	 */
	public void setSrhTargetMonth(final String srhTargetMonth) {
		this.srhTargetMonth = srhTargetMonth;
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

	/**
	 * 対象区分(0:通常 1:仮締め 2:両方)取得.
	 * @return String 対象区分(0:通常 1:仮締め 2:両方)
	 */
	public String getTargetKbn() {
		String targetKbn = "99";
		if (this.isSrhNormalFlg() && !this.isSrhTempClosingFlg()) {
			targetKbn = "0"; // 通常
		} else if (!this.isSrhNormalFlg() && this.isSrhTempClosingFlg()) {
			targetKbn = "1"; // 仮締め
		} else if (this.isSrhNormalFlg() && this.isSrhTempClosingFlg()) {
			targetKbn = "2"; // 両方
		}
		return targetKbn;
	}

	//
	// インスタンスメソッド.売掛元帳
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
	 * reportConditionを取得します。
	 * @return reportCondition reportCondition
	 */
	public RepArLedgerConditionForReport getReportCondition() {
		return reportCondition;
	}

	/**
	 * reportConditionを設定します。
	 * @param reportCondition reportConditionフラグ
	 */
	public void setReportCondition(
			final RepArLedgerConditionForReport reportCondition) {
		this.reportCondition = reportCondition;
	}
}
