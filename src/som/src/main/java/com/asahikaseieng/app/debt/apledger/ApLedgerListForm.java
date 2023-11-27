/*
 * Created on 2008/07/09
 *
 * $copyright$
 */
package com.asahikaseieng.app.debt.apledger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.debt.apledger.ApLedgerList;
import com.asahikaseieng.dao.nonentity.debt.apledger.ApLedgerPagerCondition;
import com.asahikaseieng.dao.nonentity.debt.apledgerlistforreport.RepApLedgerConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 買掛元帳 Formクラス
 * @author tosco
 */
public final class ApLedgerListForm extends AbstractSearchForm {

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
	// 検索用.買掛元帳
	//

	/** 検索入力：部署コード */
	private String srhOrganizationCd;

	/** 検索入力：部署名称 */
	private String srhOrganizationName;

	/** 検索入力：担当者コード */
	private String srhTantoCd;

	/** 検索入力：担当者名称 */
	private String srhTantoNm;

	/** 検索入力：取引先コード */
	private String srhVenderCd;

	/** 検索入力：取引先名称1 */
	private String srhVenderName1;

	/** 検索入力：対象年月 */
	private String srhTargetMonth;

	/** 検索入力：通常処理分 */
	private boolean srhNormalFlg;

	/** 検索入力：仮締処理分 */
	private boolean srhTempClosingFlg;

	/** 検索入力：対象区分 */
	private BigDecimal srhNormalTemp;

	/** 帳票検索用 */
	private RepApLedgerConditionForReport reportCondition;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	//
	// 一覧用.買掛元帳
	//

	/** リスト */
	private List<ApLedgerList> searchList;

	/**
	 * コンストラクタ.買掛元帳
	 */
	public ApLedgerListForm() {
	}

	//
	// インスタンスメソッド.買掛元帳
	//

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
		return ApLedgerPagerCondition.class;
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
			setSearchList(new ArrayList<ApLedgerList>());
		}

		if ("search".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}
		return errors;
	}

	/**
	 * 初期化.買掛元帳
	 */
	public void clear() {

		/** 検索リストのクリア */
		setSearchList(new ArrayList<ApLedgerList>());

		// 検索入力：部署コード
		setSrhOrganizationCd(null);
		// 検索入力：部署名称
		setSrhOrganizationName(null);
		// 検索入力：担当者コード
		setSrhTantoCd(null);
		// 検索入力：担当者名称
		setSrhTantoNm(null);
		// 検索入力：支払先コード
		setSrhVenderCd(null);
		// 検索入力：支払先名称
		setSrhVenderName1(null);
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

	/**
	 * 検索結果リストを取得します。
	 * @return List<ApLedgerList> 検索結果リスト
	 * 
	 */
	public List<ApLedgerList> getSearchList() {
		return searchList;
	}

	/**
	 * 検索結果リストを設定します。
	 * @param searchList 検索結果リスト
	 * 
	 */
	public void setSearchList(final List<ApLedgerList> searchList) {
		this.searchList = searchList;
	}

	//
	// 検索入力.買掛元帳

	//

	/**
	 * 検索入力：部署コード取得.
	 * @return srhOrganizationCd
	 */
	public String getSrhOrganizationCd() {
		return this.srhOrganizationCd;
	}

	/**
	 * 検索入力：部署コード設定.
	 * @param srhOrganizationCd 検索入力：部署コード
	 */
	public void setSrhOrganizationCd(final String srhOrganizationCd) {
		this.srhOrganizationCd = srhOrganizationCd;
	}

	/**
	 * 検索入力：部署名称取得.
	 * @return srhOrganizationName
	 */
	public String getSrhOrganizationName() {
		return this.srhOrganizationName;
	}

	/**
	 * 検索入力：部署名称設定.
	 * @param srhOrganizationName 検索入力：部署名称
	 */
	public void setSrhOrganizationName(final String srhOrganizationName) {
		this.srhOrganizationName = srhOrganizationName;
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
	 * 検索入力：取引先名称1取得.
	 * @return String 取引先名称1
	 */
	public String getSrhVenderName1() {
		return srhVenderName1;
	}

	/**
	 * 検索入力：取引先名称1設定.
	 * @param srhVenderName1 取引先名称1
	 */
	public void setSrhVenderName1(final String srhVenderName1) {
		this.srhVenderName1 = srhVenderName1;
	}

	/**
	 * 対象年月を取得します。
	 * 
	 * 
	 * @return srhTargetMonth
	 */
	public String getSrhTargetMonth() {
		return srhTargetMonth;
	}

	/**
	 * 対象年月を設定します。
	 * 
	 * 
	 * @param srhTargetMonth 対象年月
	 * 
	 */
	public void setSrhTargetMonth(final String srhTargetMonth) {
		this.srhTargetMonth = srhTargetMonth;
	}

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
	 * srhNormalFlgを取得します。
	 * @return srhNormalFlg
	 */
	public boolean isSrhNormalFlg() {
		return srhNormalFlg;
	}

	/**
	 * srhNormalFlgを設定します。
	 * @param srhNormalFlg srhNormalFlg
	 */
	public void setSrhNormalFlg(final boolean srhNormalFlg) {
		this.srhNormalFlg = srhNormalFlg;
	}

	/**
	 * srhTempClosingFlgを取得します。
	 * @return srhTempClosingFlg
	 */
	public boolean isSrhTempClosingFlg() {
		return srhTempClosingFlg;
	}

	/**
	 * srhTempClosingFlgを設定します。
	 * @param srhTempClosingFlg srhTempClosingFlg
	 */
	public void setSrhTempClosingFlg(final boolean srhTempClosingFlg) {
		this.srhTempClosingFlg = srhTempClosingFlg;
	}

	/**
	 * reportConditionを取得します。
	 * @return reportCondition reportCondition
	 */
	public RepApLedgerConditionForReport getReportCondition() {
		return reportCondition;
	}

	/**
	 * reportConditionを設定します。
	 * @param reportCondition reportConditionフラグ
	 */
	public void setReportCondition(
			final RepApLedgerConditionForReport reportCondition) {
		this.reportCondition = reportCondition;
	}

}
