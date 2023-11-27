/*
 * Created on 2009/02/02
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.buyingapproval;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.buyingapproval.BuyingApprovalList;
import com.asahikaseieng.dao.nonentity.buyingapproval.BuyingApprovalPagerCondition;
import com.asahikaseieng.dao.nonentity.buyingapprovalforreport.BuyingApprovalListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 仕入承認画面 Formクラス.
 * @author tosco
 * 
 */
public final class BuyingApprovalListForm extends AbstractSearchForm {

	/** serialVersion */
	private static final long serialVersionUID = 1L;

	/** 変更フラグ */
	private String dirtyFlg;

	/** ページの明細行数 */
	private static final int PAGE_ROW;

	/** 最大データ数 */
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

	/** リスト */
	private List<BuyingApprovalList> searchList;

	/** 検索入力：発注番号 */
	private String srhBuySubcontractOrderNo;

	/** 検索入力：担当部署コード */
	private String srhChargeOrganizationCd;

	/** 検索入力：担当部署名 */
	private String srhChargeOrganizationName;

	/** 検索入力：部署コード */
	private String srhOrganizationCd;

	/** 検索入力：部署名 */
	private String srhOrganizationName;

	/** 検索入力：発注担当者コード */
	private String srhTantoCd;

	/** 検索入力：発注担当者名 */
	private String srhTantoName;

	/** 検索入力：仕入先コード */
	private String srhVenderCd;

	/** 検索入力：仕入先名 */
	private String srhVenderName;

	/** 検索入力：品目コード */
	private String srhItemCd;

	/** 検索入力：品目名 */
	private String srhItemName;

	/** 検索入力：オーダー区分 */
	private String srhOrderDivision;

	/** 検索入力：納入ロケーションコード */
	private String srhLocationCd;

	/** 検索入力：納入先名 */
	private String srhLocationName;

	/** 検索入力：発注書NO */
	private String srhOrderSheetNo;

	/** 検索入力：分類コード */
	private String srhCategoryDivision;

	/** 検索入力：仕入日付(From) */
	private String srhStockingDateFrom;

	/** 検索入力：仕入日付(To) */
	private String srhStockingDateTo;

	/** 検索入力：仕入番号 */
	private String srhSlipNo;

	/** 検索入力：受入日付(From) */
	private String srhAcceptDateFrom;

	/** 検索入力：受入日付(To) */
	private String srhAcceptDateTo;

	/** 検索入力：他社コード１ */
	private String srhOtherCompanyCd1;

	/** 検索入力：取消チェックボックス */
	private boolean srhCancelCheck;

	/** 仕入区分コンボボックス */
	private List<ComboBoxItems> stockinDivisionCombo;

	/** 帳票EXCELダウンロードフラグ */
	private boolean excelReportDownloadFlg;

	/** 帳票Excel検索用 */
	private BuyingApprovalListConditionForReport reportCondition;

	//
	// インスタンスフィールド.
	//

	/**
	 * コンストラクタ.役職マスタ
	 */
	public BuyingApprovalListForm() {
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
		return BuyingApprovalPagerCondition.class;
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		if ("search".equals(getOp())) {
			// 検索入力：取消チェックボックスをクリア
			setSrhCancelCheck(false);
		}

		if ("init".equals(getOp())) {
			clear();
		}

		if ("approval".equals(getOp())) {
			clearCheck();
		}

		if ("deny".equals(getOp())) {
			clearCheck();
		}
		// 帳票Excelダウンロードフラグ
		this.setExcelReportDownloadFlg(false);
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("search".equals(getOp())) {
			// イレギュラーだけど、ここでリストをクリア
			setSearchList(new ArrayList<BuyingApprovalList>());
		}

		if ("search".equals(getOp())) {
			// Validatorによる判定
			errors = super.validate(mapping, request);
		}
		return errors;
	}

	/**
	 * チェックボックス用クリア処理.
	 */
	private void clearCheck() {
		if (getSearchList() != null) {
			for (BuyingApprovalList bean : getSearchList()) {
				bean.setApprovalCheckBox(Boolean.FALSE);
			}
		}
	}

	/**
	 * 初期化.
	 */
	public void clear() {

		// 検索リストのクリア
		setSearchList(new ArrayList<BuyingApprovalList>());

		// 検索入力：発注番号
		setSrhBuySubcontractOrderNo(null);
		// 検索入力：担当部署コード
		setSrhChargeOrganizationCd(null);
		// 検索入力：担当部署名
		setSrhChargeOrganizationName(null);
		// 検索入力：部署コード
		setSrhOrganizationCd(null);
		// 検索入力：部署名
		setSrhOrganizationName(null);
		// 検索入力：発注担当者コード
		setSrhTantoCd(null);
		// 検索入力：発注担当者名
		setSrhTantoName(null);
		// 検索入力：仕入先コード
		setSrhVenderCd(null);
		// 検索入力：仕入先名
		setSrhVenderName(null);
		// 検索入力：品目コード
		setSrhItemCd(null);
		// 検索入力：品目名
		setSrhItemName(null);
		// 検索入力：オーダー区分
		setSrhOrderDivision(null);
		// 検索入力：納入ロケーションコード
		setSrhLocationCd(null);
		// 検索入力：発注書NO
		setSrhOrderSheetNo(null);
		// 検索入力：仕入区分
		setSrhCategoryDivision(null);
		// 検索入力：仕入日付(From)
		setSrhStockingDateFrom(null);
		// 検索入力：仕入日付(To)
		setSrhStockingDateTo(null);
		// 検索入力：仕入番号
		setSrhSlipNo(null);
		// 検索入力：受入日付(From)
		setSrhAcceptDateFrom(null);
		// 検索入力：受入日付(To)
		setSrhAcceptDateTo(null);
		// 検索入力：他社コード1
		setSrhOtherCompanyCd1(null);
		// 取消チェックボックス
		setSrhCancelCheck(false);
		// 仕入区分
		setStockinDivisionCombo(null);
		// 帳票Excel検索条件
		setReportCondition(null);
		// 帳票Excelダウンロードフラグ
		setExcelReportDownloadFlg(false);
	}

	/**
	 * searchListを取得します。
	 * @return searchList
	 */
	public List<BuyingApprovalList> getSearchList() {
		return searchList;
	}

	/**
	 * searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(final List<BuyingApprovalList> searchList) {
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
	 * 検索入力：担当部署コード取得.
	 * @return String 担当部署コード
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
	 * 検索入力：担当部署名取得.
	 * @return String 担当部署名
	 */
	public String getSrhChargeOrganizationName() {
		return this.srhChargeOrganizationName;
	}

	/**
	 * 検索入力：担当部署名設定.
	 * @param srhChargeOrganizationName 担当部署名
	 */
	public void setSrhChargeOrganizationName(
			final String srhChargeOrganizationName) {
		this.srhChargeOrganizationName = srhChargeOrganizationName;
	}

	/**
	 * 検索入力：部署コード取得.
	 * @return String 部署コード
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
	 * 検索入力：部署名取得.
	 * @return String 部署名
	 */
	public String getSrhOrganizationName() {
		return this.srhOrganizationName;
	}

	/**
	 * 検索入力：部署名設定.
	 * @param srhOrganizationName 部署名
	 */
	public void setSrhOrganizationName(final String srhOrganizationName) {
		this.srhOrganizationName = srhOrganizationName;
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
	 * 検索入力：発注担当者名取得.
	 * @return String 発注担当者名
	 */
	public String getSrhTantoName() {
		return this.srhTantoName;
	}

	/**
	 * 検索入力：発注担当者名設定.
	 * @param srhTantoName 発注担当者名
	 */
	public void setSrhTantoName(final String srhTantoName) {
		this.srhTantoName = srhTantoName;
	}

	/**
	 * 検索入力：仕入先コード取得.
	 * @return String 仕入先コード
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
	 * 検索入力：仕入先名取得.
	 * @return String 仕入先名
	 */
	public String getSrhVenderName() {
		return this.srhVenderName;
	}

	/**
	 * 検索入力：仕入先名設定.
	 * @param srhVenderName 仕入先名
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
	 * 検索入力：品目名取得.
	 * @return String 品目名
	 */
	public String getSrhItemName() {
		return this.srhItemName;
	}

	/**
	 * 検索入力：品目名設定.
	 * @param srhItemName 品目名
	 */
	public void setSrhItemName(final String srhItemName) {
		this.srhItemName = srhItemName;
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
	 * 検索入力：納入先名取得.
	 * @return String 納入名
	 */
	public String getSrhLocationName() {
		return this.srhLocationName;
	}

	/**
	 * 検索入力：納入先名設定.
	 * @param srhLocationName 納入先名
	 */
	public void setSrhLocationName(final String srhLocationName) {
		this.srhLocationName = srhLocationName;
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
	 * 検索入力：仕入区分取得.
	 * @return String 仕入区分
	 */
	public String getSrhCategoryDivision() {
		return this.srhCategoryDivision;
	}

	/**
	 * 検索入力：仕入区分設定.
	 * @param srhCategoryDivision 仕入区分
	 */
	public void setSrhCategoryDivision(final String srhCategoryDivision) {
		this.srhCategoryDivision = srhCategoryDivision;
	}

	/**
	 * 検索入力：仕入日付(From)取得
	 * @return String 仕入日付(From)
	 */
	public String getSrhStockingDateFrom() {
		return this.srhStockingDateFrom;
	}

	/**
	 * 検索入力：仕入日付(From)設定.
	 * @param srhStockingDateFrom 仕入日付(From)
	 */
	public void setSrhStockingDateFrom(final String srhStockingDateFrom) {
		this.srhStockingDateFrom = srhStockingDateFrom;
	}

	/**
	 * 検索入力：仕入日付(To)取得.
	 * @return String 仕入日付(To)
	 */
	public String getSrhStockingDateTo() {
		return this.srhStockingDateTo;
	}

	/**
	 * 検索入力：仕入日付(To)設定.
	 * @param srhStockingDateTo 仕入日付(To)
	 */
	public void setSrhStockingDateTo(final String srhStockingDateTo) {
		this.srhStockingDateTo = srhStockingDateTo;
	}

	/**
	 * 検索入力：仕入番号取得.
	 * @return String 仕入番号
	 */
	public String getSrhSlipNo() {
		return this.srhSlipNo;
	}

	/**
	 * 検索入力：仕入番号設定.
	 * @param srhSlipNo 仕入番号
	 */
	public void setSrhSlipNo(final String srhSlipNo) {
		this.srhSlipNo = srhSlipNo;
	}

	/**
	 * 検索入力：受入日付(From)取得.
	 * @return String 受入日付(From)
	 */
	public String getSrhAcceptDateFrom() {
		return this.srhAcceptDateFrom;
	}

	/**
	 * 検索入力：受入日付(From)設定.
	 * @param srhAcceptDateFrom 受入日付(From)
	 */
	public void setSrhAcceptDateFrom(final String srhAcceptDateFrom) {
		this.srhAcceptDateFrom = srhAcceptDateFrom;
	}

	/**
	 * 検索入力：受入日付(To)取得.
	 * @return String 受入日付(To)
	 */
	public String getSrhAcceptDateTo() {
		return this.srhAcceptDateTo;
	}

	/**
	 * 検索入力：受入日付(To)設定.
	 * @param srhAcceptDateTo 受入日付(To)
	 */
	public void setSrhAcceptDateTo(final String srhAcceptDateTo) {
		this.srhAcceptDateTo = srhAcceptDateTo;
	}

	/**
	 * 検索入力：他社コード１
	 * @return String 他社コード１
	 */
	public String getSrhOtherCompanyCd1() {
		return this.srhOtherCompanyCd1;
	}

	/**
	 * 検索入力：他社コード１.
	 * @param srhOtherCompanyCd1 他社コード１
	 */
	public void setSrhOtherCompanyCd1(final String srhOtherCompanyCd1) {
		this.srhOtherCompanyCd1 = srhOtherCompanyCd1;
	}

	/**
	 * 変更フラグ取得
	 * @return String 変更フラグ
	 */
	public String getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * 変更フラグ設定
	 * @param dirtyFlg 変更フラグ
	 */
	public void setDirtyFlg(final String dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
	}

	/**
	 * 検索結果件数取得
	 * @return buyingApprovalListLength
	 */
	public int getBuyingApprovalListLength() {
		return searchList.size();
	}

	/**
	 * 取消チェックボックス取得
	 * @return srhCancelCheck
	 */
	public boolean isSrhCancelCheck() {
		return srhCancelCheck;
	}

	/**
	 * 取消チェックボックス設定
	 * @param srhCancelCheck 取消チェックボックス
	 */
	public void setSrhCancelCheck(final boolean srhCancelCheck) {
		this.srhCancelCheck = srhCancelCheck;
	}

	/**
	 * 仕入区分コンボボックス取得
	 * @return stockinDivisionCombo
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
	 * excelReportDownloadFlg取得
	 * @return excelReportDownloadFlg
	 */
	public boolean isExcelReportDownloadFlg() {
		return excelReportDownloadFlg;
	}

	/**
	 * excelReportDownloadFlg設定
	 * @param excelReportDownloadFlg 仕入区分コンボボックス
	 */
	public void setExcelReportDownloadFlg(final boolean excelReportDownloadFlg) {
		this.excelReportDownloadFlg = excelReportDownloadFlg;
	}

	/**
	 * reportCondition取得
	 * @return stockinDivisionCombo
	 */
	public BuyingApprovalListConditionForReport getReportCondition() {
		return reportCondition;
	}

	/**
	 * reportCondition設定
	 * @param reportCondition 仕入区分コンボボックス
	 */
	public void setReportCondition(
			final BuyingApprovalListConditionForReport reportCondition) {
		this.reportCondition = reportCondition;
	}

}
