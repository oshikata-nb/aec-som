/*
 * Created on 2009/02/17
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.accept;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.accept.AcceptList;
import com.asahikaseieng.dao.nonentity.accept.AcceptListPagerCondition;
import com.asahikaseieng.dao.nonentity.acceptforreport.AcceptListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 受入仕入検索画面 Formクラス.
 * @author tosco
 * 
 */
public final class AcceptListForm extends AbstractSearchForm {

	private static final long serialVersionUID = 1L;

	private String dirtyFlg; /* 変更フラグ */

	/* ページの明細行数 */
	private static final int PAGE_ROW;

	/* 最大データ数 */
	private static final int DATA_ROW;

	static {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.SYSTEM_PROPERTIES);

		PAGE_ROW = Integer.parseInt(rb.getString("linage.purchase.list"));
		DATA_ROW = Integer.parseInt(rb.getString("threshold.purchase.list"));
	}

	//
	// 検索用.
	//

	/** 検索入力：発注番号 */
	private String srhBuySubcontractOrderNo;

	/** 検索入力：仕入番号 */
	private String srhSlipNo;

	/** 検索入力：品目コード */
	private String srhItemCd;

	/** 検索入力：品目名称 */
	private String srhItemName;

	/** 検索入力：仕入先コード */
	private String srhVenderCd;

	/** 検索入力：仕入先名称 */
	private String srhVenderName;

	/** 検索入力：他社コード１ */
	private String srhOtherCompanyCd1;

	/** 検索入力：担当部署コード */
	private String srhChargeOrganizationCd;

	/** 検索入力：担当部署名称 */
	private String srhTantoSectionName;

	/** 検索入力：納品希望日FROM */
	private String srhSuggestedDeliverlimitDateFrom;

	/** 検索入力：納品希望日TO */
	private String srhSuggestedDeliverlimitDateTo;

	/** 検索入力：受入日付FROM */
	private String srhAcceptDateFrom;

	/** 検索入力：受入日付TO */
	private String srhAcceptDateTo;

	/** 検索入力：部署コード */
	private String srhOrganizationCd;

	/** 検索入力：部署名称 */
	private String srhSectionName;

	/** 検索入力：発注担当者コード */
	private String srhTantoCd;

	/** 検索入力：発注担当者名称 */
	private String srhTantoNm;

	/** 検索入力：オーダー区分 */
	private String srhOrderDivision;

	/** 検索入力：納入ロケーションコード */
	private String srhLocationCd;

	/** 検索入力：納入ロケーション名称 */
	private String srhLocationName;

	/** 検索入力：購買ステータス */
	private String srhStatus;

	/** 検索入力：仕入ステータス */
	private String srhStatus2;

	/** 検索入力：分類コード */
	private String srhCategoryDivision;

	/** 仕入区分コンボボックス */
	private List<ComboBoxItems> stockinDivisionCombo;

	/** 検索入力：月次更新済みチェック */
	private boolean srhMonthlyUpdCheck;

	/** 帳票EXCELダウンロードフラグ */
	private boolean excelReportDownloadFlg;

	/** 帳票Excel検索用 */
	private AcceptListConditionForReport reportCondition;

	/** ボタンステータス */
	private String buttonStatus;

	//
	// インスタンスフィールド.
	//

	/** リスト */
	private List<AcceptList> searchList;

	/**
	 * コンストラクタ.
	 */
	public AcceptListForm() {
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
		return AcceptListPagerCondition.class;
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

		// 帳票Excelダウンロードフラグ
		setExcelReportDownloadFlg(false);
		// 月次更新済みチェック
		setSrhMonthlyUpdCheck(false);
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("search".equals(getOp())) {
			/* イレギュラーだけど、ここでリストをクリア */
			setSearchList(new ArrayList<AcceptList>());
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}

		return errors;
	}

	/**
	 * 初期化.
	 */
	public void clear() {

		// 検索リストのクリア
		setSearchList(new ArrayList<AcceptList>());

		// 検索入力：発注番号
		setSrhBuySubcontractOrderNo(null);
		// 検索入力：仕入番号
		setSrhSlipNo(null);
		// 検索入力：品目コード
		setSrhItemCd(null);
		// 検索入力：品目名称
		setSrhItemName(null);
		// 検索入力：仕入先コード
		setSrhVenderCd(null);
		// 検索入力：仕入先名称
		setSrhVenderName(null);
		// 検索入力：他社コード１
		setSrhOtherCompanyCd1(null);
		// 検索入力：担当部署コード
		setSrhChargeOrganizationCd(null);
		// 検索入力：担当部署名称
		setSrhTantoSectionName(null);
		// 検索入力：納品希望日FROM
		setSrhSuggestedDeliverlimitDateFrom(null);
		// 検索入力：納品希望日TO
		setSrhSuggestedDeliverlimitDateTo(null);
		// 検索入力：受入日FROM
		setSrhAcceptDateFrom(null);
		// 検索入力：受入日TO
		setSrhAcceptDateTo(null);
		// 検索入力：部署コード
		setSrhOrganizationCd(null);
		// 検索入力：部署名称
		setSrhSectionName(null);
		// 検索入力：発注担当者コード
		setSrhTantoCd(null);
		// 検索入力：発注担当者名称
		setSrhTantoNm(null);
		// 検索入力：オーダー区分
		setSrhOrderDivision(null);
		// 検索入力：納入ロケーションコード
		setSrhLocationCd(null);
		// 検索入力：納入ロケーション名称
		setSrhLocationName(null);
		// 検索入力：購買ステータス
		setSrhStatus(null);
		// 検索入力：仕入ステータス
		setSrhStatus2(null);
		// 検索入力：分類コード
		setSrhCategoryDivision(null);
		// 仕入区分コンボボックス
		setStockinDivisionCombo(null);
		// 月次更新済みチェック
		setSrhMonthlyUpdCheck(false);
		// 帳票Excel検索条件
		setReportCondition(null);
		// 帳票Excelダウンロードフラグ
		setExcelReportDownloadFlg(false);

		setButtonStatus(null);

	}

	/**
	 * 一覧リストを取得します。
	 * @return List<AcceptList> 一覧リスト
	 */
	public List<AcceptList> getSearchList() {
		return searchList;
	}

	/**
	 * 一覧リストを設定します。
	 * 
	 * @param searchList 一覧リスト
	 */
	public void setSearchList(final List<AcceptList> searchList) {
		this.searchList = searchList;
	}

	//
	// 検索入力.
	//

	/**
	 * 検索入力：発注番号取得.
	 * @return String 発注番号
	 */
	public String getSrhBuySubcontractOrderNo() {
		return this.srhBuySubcontractOrderNo;
	}

	/**
	 * 検索入力：発注番号設定.
	 * @param srhBuySubcontractOrderNo 発注番号
	 */
	public void setSrhBuySubcontractOrderNo(
			final String srhBuySubcontractOrderNo) {
		this.srhBuySubcontractOrderNo = srhBuySubcontractOrderNo;
	}

	/**
	 * 検索入力：仕入番号取得.
	 * @return String 仕入番号
	 */
	public String getSrhSlipNo() {
		return srhSlipNo;
	}

	/**
	 * 検索入力：仕入番号設定.
	 * @param srhSlipNo 仕入番号
	 */
	public void setSrhSlipNo(final String srhSlipNo) {
		this.srhSlipNo = srhSlipNo;
	}

	/**
	 * 検索入力：品目コード取得.
	 * @return String 品目コード
	 */
	public String getSrhItemCd() {
		return this.srhItemCd;
	}

	/**
	 * 検索入力：品目コード設定.
	 * @param srhItemCd 品目コード
	 */
	public void setSrhItemCd(final String srhItemCd) {
		this.srhItemCd = srhItemCd;
	}

	/**
	 * 検索入力：品目名称取得.
	 * @return String 品目名称
	 */
	public String getSrhItemName() {
		return srhItemName;
	}

	/**
	 * 検索入力：品目名称設定.
	 * @param srhItemName 品目名称
	 */
	public void setSrhItemName(final String srhItemName) {
		this.srhItemName = srhItemName;
	}

	/**
	 * 検索入力.仕入先コード取得
	 * @return String 仕入先コード
	 */
	public String getSrhVenderCd() {
		return this.srhVenderCd;
	}

	/**
	 * 検索入力.仕入先コード設定 *
	 * @param srhVenderCd 仕入先コード
	 */
	public void setSrhVenderCd(final String srhVenderCd) {
		this.srhVenderCd = srhVenderCd;
	}

	/**
	 * 検索入力：仕入先名称取得.
	 * @return String 仕入先名称
	 */
	public String getSrhVenderName() {
		return srhVenderName;
	}

	/**
	 * 検索入力：仕入先名称設定.
	 * @param srhVenderName 仕入先名称
	 */
	public void setSrhVenderName(final String srhVenderName) {
		this.srhVenderName = srhVenderName;
	}

	/**
	 * 検索入力：他社コード１取得.
	 * @return String 他社コード１
	 */
	public String getSrhOtherCompanyCd1() {
		return srhOtherCompanyCd1;
	}

	/**
	 * 検索入力：他社コード１設定.
	 * @param srhOtherCompanyCd1 他社コード１
	 */
	public void setSrhOtherCompanyCd1(final String srhOtherCompanyCd1) {
		this.srhOtherCompanyCd1 = srhOtherCompanyCd1;
	}

	/**
	 * 検索入力.担当部署コード取得
	 * @return String 担当部署コード
	 */
	public String getSrhChargeOrganizationCd() {
		return this.srhChargeOrganizationCd;
	}

	/**
	 * 検索入力.担当部署コード設定 *
	 * @param srhChargeOrganizationCd 担当部署コード
	 */
	public void setSrhChargeOrganizationCd(final String srhChargeOrganizationCd) {
		this.srhChargeOrganizationCd = srhChargeOrganizationCd;
	}

	/**
	 * 検索入力：担当部署名称取得.
	 * @return String 担当部署名称
	 */
	public String getSrhTantoSectionName() {
		return srhTantoSectionName;
	}

	/**
	 * 検索入力：担当部署名称設定.
	 * @param srhTantoSectionName 担当部署名称
	 */
	public void setSrhTantoSectionName(final String srhTantoSectionName) {
		this.srhTantoSectionName = srhTantoSectionName;
	}

	/**
	 * 検索入力.部署コード取得
	 * @return String 部署コード
	 */
	public String getSrhOrganizationCd() {
		return this.srhOrganizationCd;
	}

	/**
	 * 検索入力.部署コード設定 *
	 * @param srhOrganizationCd 部署コード
	 */
	public void setSrhOrganizationCd(final String srhOrganizationCd) {
		this.srhOrganizationCd = srhOrganizationCd;
	}

	/**
	 * 検索入力：部署名称取得.
	 * @return String 部署名称
	 */
	public String getSrhSectionName() {
		return srhSectionName;
	}

	/**
	 * 検索入力：部署名称設定.
	 * @param srhSectionName 部署名称
	 */
	public void setSrhSectionName(final String srhSectionName) {
		this.srhSectionName = srhSectionName;
	}

	/**
	 * 検索入力：納品希望日FROM取得.
	 * @return String 納品希望日FROM
	 */
	public String getSrhSuggestedDeliverlimitDateFrom() {
		return this.srhSuggestedDeliverlimitDateFrom;
	}

	/**
	 * 検索入力：納品希望日FROM設定.
	 * @param srhSuggestedDeliverlimitDateFrom 納品希望日FROM
	 */
	public void setSrhSuggestedDeliverlimitDateFrom(
			final String srhSuggestedDeliverlimitDateFrom) {
		this.srhSuggestedDeliverlimitDateFrom = srhSuggestedDeliverlimitDateFrom;
	}

	/**
	 * 検索入力：納品希望日TO取得.
	 * @return String 納品希望日TO
	 */
	public String getSrhSuggestedDeliverlimitDateTo() {
		return srhSuggestedDeliverlimitDateTo;
	}

	/**
	 * 検索入力：納品希望日TO設定.
	 * @param srhSuggestedDeliverlimitDateTo 納品希望日TO
	 */
	public void setSrhSuggestedDeliverlimitDateTo(
			final String srhSuggestedDeliverlimitDateTo) {
		this.srhSuggestedDeliverlimitDateTo = srhSuggestedDeliverlimitDateTo;
	}

	/**
	 * 検索入力：受入日付FROM取得.
	 * @return String 受入日付FROM
	 */
	public String getSrhAcceptDateFrom() {
		return srhAcceptDateFrom;
	}

	/**
	 * 検索入力：受入日付FROM設定.
	 * @param srhAcceptDateFrom 受入日付FROM
	 */
	public void setSrhAcceptDateFrom(final String srhAcceptDateFrom) {
		this.srhAcceptDateFrom = srhAcceptDateFrom;
	}

	/**
	 * 検索入力：受入日付TO取得.
	 * @return String 受入日付TO
	 */
	public String getSrhAcceptDateTo() {
		return srhAcceptDateTo;
	}

	/**
	 * 検索入力：受入日付TO設定.
	 * @param srhAcceptDateTo 受入日付TO
	 */
	public void setSrhAcceptDateTo(final String srhAcceptDateTo) {
		this.srhAcceptDateTo = srhAcceptDateTo;
	}

	/**
	 * 検索入力：発注担当者コード取得.
	 * @return String 発注担当者コード
	 */
	public String getSrhTantoCd() {
		return this.srhTantoCd;
	}

	/**
	 * 検索入力：発注担当者コード設定.
	 * @param srhTantoCd 発注担当者コード
	 */
	public void setSrhTantoCd(final String srhTantoCd) {
		this.srhTantoCd = srhTantoCd;
	}

	/**
	 * 検索入力：発注担当者名称取得.
	 * @return String 発注担当者名称
	 */
	public String getSrhTantoNm() {
		return srhTantoNm;
	}

	/**
	 * 検索入力：発注担当者名称設定.
	 * @param srhTantoNm 発注担当者名称
	 */
	public void setSrhTantoNm(final String srhTantoNm) {
		this.srhTantoNm = srhTantoNm;
	}

	/**
	 * 検索入力：オーダー区分取得.
	 * @return String オーダー区分
	 */
	public String getSrhOrderDivision() {
		return this.srhOrderDivision;
	}

	/**
	 * 検索入力：オーダー区分設定.
	 * @param srhOrderDivision オーダー区分
	 */
	public void setSrhOrderDivision(final String srhOrderDivision) {
		this.srhOrderDivision = srhOrderDivision;
	}

	/**
	 * 検索入力：納入ロケーションコード取得.
	 * @return String 納入ロケーションコード
	 */
	public String getSrhLocationCd() {
		return this.srhLocationCd;
	}

	/**
	 * 検索入力：納入ロケーションコード設定.
	 * @param srhLocationCd 納入ロケーションコード
	 */
	public void setSrhLocationCd(final String srhLocationCd) {
		this.srhLocationCd = srhLocationCd;
	}

	/**
	 * 検索入力：納入ロケーション名称取得.
	 * @return String 納入ロケーション名称
	 */
	public String getSrhLocationName() {
		return srhLocationName;
	}

	/**
	 * 検索入力：納入ロケーション名称設定.
	 * @param srhLocationName 納入ロケーション名称
	 */
	public void setSrhLocationName(final String srhLocationName) {
		this.srhLocationName = srhLocationName;
	}

	/**
	 * 検索入力：購買ステータス取得.
	 * @return String 購買ステータス
	 */
	public String getSrhStatus() {
		return this.srhStatus;
	}

	/**
	 * 検索入力：購買ステータス設定.
	 * @param srhStatus 購買ステータス
	 */
	public void setSrhStatus(final String srhStatus) {
		this.srhStatus = srhStatus;
	}

	/**
	 * 検索入力：仕入ステータス取得.
	 * @return String 仕入ステータス
	 */
	public String getSrhStatus2() {
		return srhStatus2;
	}

	/**
	 * 検索入力：仕入ステータス設定.
	 * @param srhStatus2 仕入ステータス
	 */
	public void setSrhStatus2(final String srhStatus2) {
		this.srhStatus2 = srhStatus2;
	}

	/**
	 * 検索入力：分類コード取得.
	 * @return String 分類コード
	 */
	public String getSrhCategoryDivision() {
		return this.srhCategoryDivision;
	}

	/**
	 * 検索入力：分類コード設定.
	 * @param srhCategoryDivision 分類コード
	 */
	public void setSrhCategoryDivision(final String srhCategoryDivision) {
		this.srhCategoryDivision = srhCategoryDivision;
	}

	/**
	 * 仕入区分コンボボックス取得
	 * @return List<ComboBoxItems> 仕入区分コンボボックス
	 */
	public List<ComboBoxItems> getStockinDivisionCombo() {
		return stockinDivisionCombo;
	}

	/**
	 * 仕入区分コンボボックス設定
	 * @param stockinDivisionCombo 仕入区分コンボボックス
	 */
	public void setStockinDivisionCombo(
			final List<ComboBoxItems> stockinDivisionCombo) {
		this.stockinDivisionCombo = stockinDivisionCombo;
	}

	/**
	 * 月次更新済みチェック取得
	 * @return boolean 月次更新済みチェック
	 */
	public boolean isSrhMonthlyUpdCheck() {
		return srhMonthlyUpdCheck;
	}

	/**
	 * 月次更新済みチェック設定
	 * @param srhMonthlyUpdCheck 月次更新済みチェック
	 */
	public void setSrhMonthlyUpdCheck(final boolean srhMonthlyUpdCheck) {
		this.srhMonthlyUpdCheck = srhMonthlyUpdCheck;
	}

	/**
	 * 変更フラグを取得します。
	 * @return String 変更フラグ
	 */
	public String getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * 変更フラグを設定します。
	 * @param dirtyFlg 変更フラグ
	 */
	public void setDirtyFlg(final String dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
	}

	/**
	 * 帳票Excel出力フラグを取得します。
	 * @return boolean 帳票Excel出力フラグ
	 */
	public boolean isExcelReportDownloadFlg() {
		return excelReportDownloadFlg;
	}

	/**
	 * 帳票Excel出力フラグを設定します。
	 * @param excelReportDownloadFlg 帳票Excel出力フラグ
	 */
	public void setExcelReportDownloadFlg(final boolean excelReportDownloadFlg) {
		this.excelReportDownloadFlg = excelReportDownloadFlg;
	}

	/**
	 * reportConditionを取得します。
	 * @return reportCondition reportCondition
	 */
	public AcceptListConditionForReport getReportCondition() {
		return reportCondition;
	}

	/**
	 * reportConditionを設定します。
	 * @param reportCondition reportCondition
	 */
	public void setReportCondition(
			final AcceptListConditionForReport reportCondition) {
		this.reportCondition = reportCondition;
	}

	/**
	 * ボタンステータス取得
	 * @return String ボタンステータス
	 */
	public String getButtonStatus() {
		return buttonStatus;
	}

	/**
	 * ボタンステータス
	 * @param buttonStatus ボタンステータス
	 */
	public void setButtonStatus(final String buttonStatus) {
		this.buttonStatus = buttonStatus;
	}

}
