/*
 * Created on 2009/01/13
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.purchaseorder;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.purchaseorder.PurchaseOrderList;
import com.asahikaseieng.dao.nonentity.purchaseorder.PurchaseOrderPagerCondition;
import com.asahikaseieng.dao.nonentity.purchaseorderforreport.PurchaseOrderListConditionForRepor;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 発注書一覧 Formクラス.
 * @author tosco
 * 
 */
public final class PurchaseOrderListForm extends AbstractSearchForm {

	private static final long serialVersionUID = 1L;

	private String dirtyFlg; /* 変更フラグ */

	/* ページの明細行数 */
	private static final int PAGE_ROW;

	/* 最大データ数 */
	private static final int DATA_ROW;

	static {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.SYSTEM_PROPERTIES);

		// PAGE_ROW = Integer.parseInt(rb.getString("linage.common"));
		PAGE_ROW = Integer.parseInt(rb.getString("threshold.common"));
		DATA_ROW = Integer.parseInt(rb.getString("threshold.common"));
	}

	//
	// 検索用.
	//
	/** 検索入力：発注番号 */
	private String srhBuySubcontractOrderNo;

	/** 検索入力：発注日from */
	private String srhOrderDateFrom;

	/** 検索入力：発注日to */
	private String srhOrderDateTo;

	/** 検索入力：担当部署コード */
	private String srhChargeOrganizationCd;

	/** 検索入力：担当部署名称 */
	private String srhTantoSectionName;

	/** 検索入力：部署コード */
	private String srhOrganizationCd;

	/** 検索入力：他社コード１ */
	private String srhTashaCd;

	/** 検索入力：部署名称 */
	private String srhSectionName;

	/** 検索入力：発注者コード */
	private String srhTantoCd;

	/** 検索入力：発注者名称 */
	private String srhTantoName;

	/** 検索入力：オーダー区分 */
	private String srhOrderDivision;

	/** 検索入力：仕入先コード */
	private String srhVenderCd;

	/** 検索入力：仕入先名称 */
	private String srhSupplierName;

	/** 検索入力：品目コード */
	private String srhItemCd;

	/** 検索入力：品目名称 */
	private String srhItemName;

	/** 検索入力：納品希望日from */
	private String srhSuggestedDeliverlimitDateFrom;

	/** 検索入力：納品希望日to */
	private String srhSuggestedDeliverlimitDateTo;

	/** 検索入力：納入先コード */
	private String srhLocationCd;

	/** 検索入力：納入先名称 */
	private String srhLocationName;

	/** 検索入力：購買ステータス */
	private String srhStatus;

	/** 検索入力：発注書NO */
	private String srhOrderSheetNo;

	/** まとめ処理区分 */
	private String location;

	/** 購買NO */
	private String purchaseNo;

	/** 印刷フラグ */
	private boolean printCheckBox;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/** 帳票EXCELダウンロードフラグ */
	private boolean excelReportDownloadFlg;

	/** 帳票Excel検索用 */
	private PurchaseOrderListConditionForRepor reportCondition;

	/** 通常検索かアラーム検索かの区分 */
	private String searchType;


	//
	// インスタンスフィールド.
	//
	/** リスト */
	private List<PurchaseOrderList> searchList;

	/**
	 * コンストラクタ
	 */
	public PurchaseOrderListForm() {
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
		return PurchaseOrderPagerCondition.class;
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
		if ("order".equals(getOp())) {
			clearCheck();
		}
		// 帳票Excelダウンロードフラグ
		setExcelReportDownloadFlg(false);

		this.setPrintCheckBox(Boolean.FALSE);
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("search".equals(getOp())) {
			/* イレギュラーだけど、ここでリストをクリア */
			setSearchList(new ArrayList<PurchaseOrderList>());
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}

		return errors;
	}

	/**
	 * 初期化.
	 */
	public void clear() {

		/** 検索リストのクリア */
		setSearchList(new ArrayList<PurchaseOrderList>());

		/** 検索入力：発注番号 */
		setSrhBuySubcontractOrderNo(null);
		/** 検索入力：発注日 from */
		setSrhOrderDateFrom(null);
		/** 検索入力：発注日 to */
		setSrhOrderDateTo(null);
		/** 検索入力：担当部署コード */
		setSrhChargeOrganizationCd(null);
		/** 検索入力：担当部署名称 */
		setSrhTantoSectionName(null);
		/** 検索入力：部署コード */
		setSrhOrganizationCd(null);
		/** 検索入力：部署名称 */
		setSrhSectionName(null);
		/** 検索入力：他社コード */
		setSrhTashaCd(null);
		/** 検索入力：発注者コード */
		setSrhTantoCd(null);
		/** 検索入力：発注者名称 */
		setSrhTantoName(null);
		/** 検索入力：オーダー区分 */
		setSrhOrderDivision(null);
		/** 検索入力：仕入先コード */
		setSrhVenderCd(null);
		/** 検索入力：仕入先名称 */
		setSrhSupplierName(null);
		/** 検索入力：品目コード */
		setSrhItemCd(null);
		/** 検索入力：品目名称 */
		setSrhItemName(null);
		/** 検索入力：納品希望日from */
		setSrhSuggestedDeliverlimitDateFrom(null);
		/** 検索入力：納品希望日to */
		setSrhSuggestedDeliverlimitDateTo(null);
		/** 検索入力：納入先コード */
		setSrhLocationCd(null);
		/** 検索入力：納入先名称 */
		setSrhLocationName(null);
		/** 検索入力：購買ステータス */
		setSrhStatus(null);
		/** 検索入力：発注書NO */
		setSrhOrderSheetNo(null);
		setPurchaseNo(null);
		setPrintCheckBox(false);
		/** ダウンロードフラグ */
		this.setExcelDownloadFlg(Boolean.FALSE);
		// 帳票Excelダウンロードフラグ
		setExcelReportDownloadFlg(false);

		// 帳票Excel検索条件
		setReportCondition(null);
	}

	/**
	 * 一覧リストを取得します。
	 * @return searchList
	 */
	public List<PurchaseOrderList> getSearchList() {
		return searchList;
	}

	/**
	 * 一覧リストを設定します。
	 * 
	 * @param searchList 一覧リスト
	 */
	public void setSearchList(final List<PurchaseOrderList> searchList) {
		this.searchList = searchList;
	}

	//
	// 検索入力.
	//

	/**
	 * 検索入力：発注番号取得.
	 * @return 発注番号
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
	 * 検索入力：発注日from取得.
	 * @return 発注日From
	 */
	public String getSrhOrderDateFrom() {
		return this.srhOrderDateFrom;
	}

	/**
	 * 検索入力：発注日from設定.
	 * @param srhOrderDateFrom 発注日From
	 */
	public void setSrhOrderDateFrom(final String srhOrderDateFrom) {
		this.srhOrderDateFrom = srhOrderDateFrom;
	}

	/**
	 * 検索入力：発注日To取得.
	 * @return 発注日To
	 */
	public String getSrhOrderDateTo() {
		return this.srhOrderDateTo;
	}

	/**
	 * 検索入力：発注日To設定.
	 * @param srhOrderDateTo 発注日To
	 */
	public void setSrhOrderDateTo(final String srhOrderDateTo) {
		this.srhOrderDateTo = srhOrderDateTo;
	}

	/**
	 * 検索入力：担当部署コード取得.
	 * @return 担当部署コード
	 */
	public String getSrhChargeOrganizationCd() {
		return this.srhChargeOrganizationCd;
	}

	/**
	 * 検索入力：担当部署コード設定.
	 * @param srhChargeOrganizationCd 担当部署コード
	 */
	public void setSrhChargeOrganizationCd(final String srhChargeOrganizationCd) {
		this.srhChargeOrganizationCd = srhChargeOrganizationCd;
	}

	/**
	 * 検索入力：担当部署名称を取得します。
	 * @return 担当部署名称
	 */
	public String getSrhTantoSectionName() {
		return srhTantoSectionName;
	}

	/**
	 * 検索入力：担当部署名称を設定します。
	 * @param srhTantoSectionName 担当部署名称
	 */
	public void setSrhTantoSectionName(final String srhTantoSectionName) {
		this.srhTantoSectionName = srhTantoSectionName;
	}

	/**
	 * 検索入力：部署コード取得.
	 * @return 部署コード
	 */
	public String getSrhOrganizationCd() {
		return this.srhOrganizationCd;
	}

	/**
	 * 検索入力：部署コード設定.
	 * @param srhOrganizationCd 部署コード
	 */
	public void setSrhOrganizationCd(final String srhOrganizationCd) {
		this.srhOrganizationCd = srhOrganizationCd;
	}

	/**
	 * 検索入力：部署名称取得.
	 * @return 部署名称
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
	 * 検索入力：部署名称取得.
	 * @return 部署名称
	 */
	public String getSrhTashaCd() {
		return srhTashaCd;
	}

	/**
	 * 検索入力：他社コード設定.
	 * @param srhTashaCd 他社コード
	 */
	public void setSrhTashaCd(final String srhTashaCd) {
		this.srhTashaCd = srhTashaCd;
	}

	/**
	 * 検索入力：検索入力：発注者コード取得.
	 * @return 発注者コード
	 */
	public String getSrhTantoCd() {
		return this.srhTantoCd;
	}

	/**
	 * 検索入力：発注者コード設定.
	 * @param srhTantoCd 発注者コード
	 */
	public void setSrhTantoCd(final String srhTantoCd) {
		this.srhTantoCd = srhTantoCd;
	}

	/**
	 * 発注者名称取得.
	 * @return 発注者名称
	 */
	public String getSrhTantoName() {
		return srhTantoName;
	}

	/**
	 * 発注者名称設定.
	 * @param srhTantoName 発注者名称
	 */
	public void setSrhTantoName(final String srhTantoName) {
		this.srhTantoName = srhTantoName;
	}

	/**
	 * 検索入力：オーダー区分.
	 * @return オーダー区分
	 */
	public String getSrhOrderDivision() {
		return this.srhOrderDivision;
	}

	/**
	 * 検索入力：オーダー区分.
	 * @param srhOrderDivision オーダー区分
	 */
	public void setSrhOrderDivision(final String srhOrderDivision) {
		this.srhOrderDivision = srhOrderDivision;
	}

	/**
	 * 検索入力：仕入先コード取得.
	 * @return 仕入先コード
	 */
	public String getSrhVenderCd() {
		return this.srhVenderCd;
	}

	/**
	 * 検索入力：仕入先コード設定.
	 * @param srhVenderCd 仕入先コード
	 */
	public void setSrhVenderCd(final String srhVenderCd) {
		this.srhVenderCd = srhVenderCd;
	}

	/**
	 * 検索入力：仕入先名称取得.
	 * @return 仕入先名称
	 */
	public String getSrhSupplierName() {
		return srhSupplierName;
	}

	/**
	 * 検索入力：仕入先名称設定.
	 * @param srhSupplierName 仕入先名称
	 */
	public void setSrhSupplierName(final String srhSupplierName) {
		this.srhSupplierName = srhSupplierName;
	}

	/**
	 * 検索入力：品目コード取得.
	 * @return 品目コード
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
	 * 品目名称取得.
	 * @return 品目名称
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
	 * 検索入力：納品希望日from取得.
	 * @return 納品希望日
	 */
	public String getSrhSuggestedDeliverlimitDateFrom() {
		return this.srhSuggestedDeliverlimitDateFrom;
	}

	/**
	 * 検索入力：納品希望日from設定.
	 * @param srhSuggestedDeliverlimitDateFrom 納品希望日
	 */
	public void setSrhSuggestedDeliverlimitDateFrom(
			final String srhSuggestedDeliverlimitDateFrom) {
		this.srhSuggestedDeliverlimitDateFrom = srhSuggestedDeliverlimitDateFrom;
	}

	/**
	 * 検索入力：納品希望日to取得.
	 * @return 納品希望日to
	 */
	public String getSrhSuggestedDeliverlimitDateTo() {
		return this.srhSuggestedDeliverlimitDateTo;
	}

	/**
	 * 検索入力：納品希望日to設定.
	 * @param srhSuggestedDeliverlimitDateTo 納品希望日to
	 */
	public void setSrhSuggestedDeliverlimitDateTo(
			final String srhSuggestedDeliverlimitDateTo) {
		this.srhSuggestedDeliverlimitDateTo = srhSuggestedDeliverlimitDateTo;
	}

	/**
	 * 検索入力：納入先コード取得.
	 * @return 納入先コード
	 */
	public String getSrhLocationCd() {
		return this.srhLocationCd;
	}

	/**
	 * 検索入力：納入先コード設定.
	 * @param srhLocationCd 納入先コード
	 */
	public void setSrhLocationCd(final String srhLocationCd) {
		this.srhLocationCd = srhLocationCd;
	}

	/**
	 * 検索入力：納入先名称を取得します。
	 * @return 納入先名称
	 */
	public String getSrhLocationName() {
		return srhLocationName;
	}

	/**
	 * 検索入力：納入先名称を設定します。
	 * @param srhLocationName 納入先名称
	 */
	public void setSrhLocationName(final String srhLocationName) {
		this.srhLocationName = srhLocationName;
	}

	/**
	 * 検索入力：購買ステータス取得.
	 * @return 購買ステータス
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
	 * 検索入力：発注書NO取得.
	 * @return 発注書NO
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
	 * 変更フラグを取得します。
	 * @return 変更フラグ
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
	 * まとめ処理区分を取得します。
	 * @return location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * まとめ処理区分を設定します。
	 * @param location location
	 */
	public void setLocation(final String location) {
		this.location = location;
	}

	/**
	 * 購買Noを取得します。
	 * @return purchaseNo
	 */
	public String getPurchaseNo() {
		return purchaseNo;
	}

	/**
	 * 購買Noを設定します。
	 * @param purchaseNo 購買No
	 */
	public void setPurchaseNo(final String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}

	/**
	 * 検索結果件数取得
	 * @return purchaseApprovalListLength
	 */
	public int getPurchaseApprovalListLength() {
		return searchList.size();
	}

	/**
	 * チェックボックス用クリア処理
	 */
	private void clearCheck() {
		if (getSearchList() != null) {
			for (PurchaseOrderList bean : getSearchList()) {
				bean.setOrderCheckBox(Boolean.FALSE);
			}
		}
	}

	/**
	 * 印刷フラグを取得します。
	 * @return printCheckBox
	 */
	public boolean isPrintCheckBox() {
		return printCheckBox;
	}

	/**
	 * 印刷フラグを設定します。
	 * @param printCheckBox 印刷フラグ
	 */
	public void setPrintCheckBox(final boolean printCheckBox) {
		this.printCheckBox = printCheckBox;
	}

	/**
	 * excelDownloadFlgを取得します。
	 * @return excelDownloadFlg boolean
	 */
	public boolean isExcelDownloadFlg() {
		return excelDownloadFlg;
	}

	/**
	 * excelDownloadFlgを設定します。
	 * @param excelDownloadFlg boolean
	 */
	public void setExcelDownloadFlg(final boolean excelDownloadFlg) {
		this.excelDownloadFlg = excelDownloadFlg;
	}

	/**
	 * excelReportDownloadFlgを取得します。
	 * @return excelReportDownloadFlg boolean
	 */
	public boolean isExcelReportDownloadFlg() {
		return excelReportDownloadFlg;
	}

	/**
	 * excelReportDownloadFlgを設定します。
	 * @param excelReportDownloadFlg boolean
	 */
	public void setExcelReportDownloadFlg(final boolean excelReportDownloadFlg) {
		this.excelReportDownloadFlg = excelReportDownloadFlg;
	}

	/**
	 * reportConditionを取得します。
	 * @return reportCondition reportCondition
	 */
	public PurchaseOrderListConditionForRepor getReportCondition() {
		return reportCondition;
	}

	/**
	 * reportConditionを設定します。
	 * @param reportCondition reportCondition
	 */
	public void setReportCondition(
			final PurchaseOrderListConditionForRepor reportCondition) {
		this.reportCondition = reportCondition;
	}

	/**
	 * searchTypeAlarmを取得します。
	 * @return searchTypeAlarm
	 */
	public String getSearchType() {
		return searchType;
	}

	/**
	 * searchTypeAlarmを設定します。
	 * @param searchTypeAlarm searchTypeAlarm
	 */
	public void setSearchType(final String searchTypeAlarm) {
		this.searchType = searchTypeAlarm;
	}
}
