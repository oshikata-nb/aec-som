/*
 * Created on 2009/03/09
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.purchasedelivery;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.purchasedelivery.PurchaseDeliveryList;
import com.asahikaseieng.dao.nonentity.purchasedelivery.PurchaseDeliveryListPagerCondition;
import com.asahikaseieng.dao.nonentity.purchasedeliveryforreport.PurchaseDeliveryListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 納期回答検索画面 Formクラス.
 * @author tosco
 * 
 */
public final class PurchaseDeliveryListForm extends AbstractSearchForm {

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
	// 検索用.
	//

	/** 検索入力：発注番号 */
	private String srhBuySubcontractOrderNo;

	/** 検索入力：購買ステータス */
	private String srhStatus;

	/** 検索入力：発注日FROM */
	private String srhOrderDateFrom;

	/** 検索入力：発注日TO */
	private String srhOrderDateTo;

	/** 検索入力：納品希望日FROM */
	private String srhSuggestedDeliverlimitDateFrom;

	/** 検索入力：納品希望日TO */
	private String srhSuggestedDeliverlimitDateTo;

	/** 検索入力：納入ロケーションコード */
	private String srhLocationCd;

	/** 検索入力：納入ロケーション名称 */
	private String srhLocationName;

	/** 検索入力：仕入先コード */
	private String srhVenderCd;

	/** 検索入力：仕入先名称 */
	private String srhVenderName;

	/** 検索入力：品目コード */
	private String srhItemCd;

	/** 検索入力：品目名称 */
	private String srhItemName;

	/** 検索入力：他社コード１ */
	private String srhOtherCompanyCd1;

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

	/** 検索入力：発注書NO */
	private String srhOrderSheetNo;

	/** 検索入力：担当部署コード */
	private String srhChargeOrganizationCd;

	/** 検索入力：担当部署名称 */
	private String srhTantoSectionName;

	/** 帳票EXCELダウンロードフラグ */
	private boolean excelReportDownloadFlg;

	/** 帳票Excel検索用 */
	private PurchaseDeliveryListConditionForReport reportCondition;

	//
	// インスタンスフィールド.
	//

	/** リスト */
	private List<PurchaseDeliveryList> searchList;

	/**
	 * コンストラクタ.
	 */
	public PurchaseDeliveryListForm() {
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
		return PurchaseDeliveryListPagerCondition.class;
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
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("search".equals(getOp())) {
			/* イレギュラーだけど、ここでリストをクリア */
			setSearchList(new ArrayList<PurchaseDeliveryList>());
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
		setSearchList(new ArrayList<PurchaseDeliveryList>());

		// 検索入力：発注番号
		setSrhBuySubcontractOrderNo(null);
		// 検索入力：購買ステータス
		setSrhStatus(null);
		// 検索入力：発注日FROM
		setSrhOrderDateFrom(null);
		// 検索入力：発注日TO
		setSrhOrderDateTo(null);
		// 検索入力：納品希望日FROM
		setSrhSuggestedDeliverlimitDateFrom(null);
		// 検索入力：納品希望日TO
		setSrhSuggestedDeliverlimitDateTo(null);
		// 検索入力：納入ロケーションコード
		setSrhLocationCd(null);
		// 検索入力：納入ロケーション名称
		setSrhLocationName(null);
		// 検索入力：仕入先コード
		setSrhVenderCd(null);
		// 検索入力：仕入先名称
		setSrhVenderName(null);
		// 検索入力：品目コード
		setSrhItemCd(null);
		// 検索入力：品目名称
		setSrhItemName(null);
		// 検索入力：他社コード１
		setSrhOtherCompanyCd1(null);
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
		// 検索入力：発注書NO
		setSrhOrderSheetNo(null);
		// 検索入力：担当部署コード
		setSrhChargeOrganizationCd(null);
		// 検索入力：担当部署名称
		setSrhTantoSectionName(null);
		// 帳票Excelダウンロードフラグ
		setExcelReportDownloadFlg(false);

		// 帳票Excel検索条件
		setReportCondition(null);
	}

	/**
	 * 一覧リストを取得します。
	 * @return List<PurchaseDeliveryList> 一覧リスト
	 */
	public List<PurchaseDeliveryList> getSearchList() {
		return searchList;
	}

	/**
	 * 一覧リストを設定します。
	 * 
	 * @param searchList 一覧リスト
	 */
	public void setSearchList(final List<PurchaseDeliveryList> searchList) {
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
	 * 検索入力：発注日FROM取得.
	 * @return String 発注日FROM
	 */
	public String getSrhOrderDateFrom() {
		return this.srhOrderDateFrom;
	}

	/**
	 * 検索入力：発注日FROM設定.
	 * @param srhOrderDateFrom 発注日FROM
	 */
	public void setSrhOrderDateFrom(final String srhOrderDateFrom) {
		this.srhOrderDateFrom = srhOrderDateFrom;
	}

	/**
	 * 検索入力：発注日TO取得.
	 * @return String 発注日TO
	 */
	public String getSrhOrderDateTo() {
		return srhOrderDateTo;
	}

	/**
	 * 検索入力：発注日TO設定.
	 * @param srhOrderDateTo 発注日TO
	 */
	public void setSrhOrderDateTo(final String srhOrderDateTo) {
		this.srhOrderDateTo = srhOrderDateTo;
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
	 * 検索入力：発注書NO取得.
	 * @return String 発注書NO
	 */
	public String getSrhOrderSheetNo() {
		return this.srhOrderSheetNo;
	}

	/**
	 * 検索入力：発注書NO設定.
	 * @param srhOrderSheetNo 発注書NO
	 */
	public void setSrhOrderSheetNo(final String srhOrderSheetNo) {
		this.srhOrderSheetNo = srhOrderSheetNo;
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
	 * excelReportDownloadFlgを取得します。
	 * @return excelReportDownloadFlg excelReportDownloadFlg
	 */
	public boolean isExcelReportDownloadFlg() {
		return excelReportDownloadFlg;
	}

	/**
	 * excelReportDownloadFlgを設定します。
	 * @param excelReportDownloadFlg excelReportDownloadFlg
	 */
	public void setExcelReportDownloadFlg(final boolean excelReportDownloadFlg) {
		this.excelReportDownloadFlg = excelReportDownloadFlg;
	}

	/**
	 * reportConditionを取得します。
	 * @return reportCondition reportCondition
	 */
	public PurchaseDeliveryListConditionForReport getReportCondition() {
		return reportCondition;
	}

	/**
	 * reportConditionを設定します。
	 * @param reportCondition reportCondition
	 */
	public void setReportCondition(
			final PurchaseDeliveryListConditionForReport reportCondition) {
		this.reportCondition = reportCondition;
	}

}
