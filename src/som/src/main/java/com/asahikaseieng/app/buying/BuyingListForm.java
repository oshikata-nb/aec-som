/*
 * Created on 2009/01/13
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.buying;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.buying.BuyingList;
import com.asahikaseieng.dao.nonentity.buying.BuyingPagerCondition;
import com.asahikaseieng.dao.nonentity.buyingforreport.BuyingListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 仕入一覧 Formクラス.
 * @author tosco
 * 
 */
public final class BuyingListForm extends AbstractSearchForm {

	/** serialVersion */
	private static final long serialVersionUID = 1L;

	/** 変更フラグ */
	private String dirtyFlg;

	/** ページの明細行数 */
	private static final int PAGE_ROW;

	/** 最大データ数 */
	private static final int DATA_ROW;

	static {
		// SYSTEM_PROPERTIESの取得
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.SYSTEM_PROPERTIES);

		// 明細行数をSYSTEM_PROPERTIESから取得
		PAGE_ROW = Integer.parseInt(rb.getString("linage.slipbuying.list"));
		// 最大データ数をSYSTEM_PROPERTIESから取得
		DATA_ROW = Integer.parseInt(rb.getString("threshold.slipbuying.list"));
	}

	//
	// 検索用.
	//

	/** 検索入力：担当部署 */
	private String srhChargeOrganizationCd;

	/** 検索入力：担当部署名称 */
	private String srhChargeOrganizationName;

	/** 検索入力：部署コード */
	private String srhOrganizationCd;

	/** 検索入力：部署名称 */
	private String srhOrganizationName;

	/** 検索入力：担当者コード */
	private String srhTantoCd;

	/** 検索入力：担当者名称 */
	private String srhTantoName;

	/** 検索入力：仕入先コード */
	private String srhVenderCd;

	/** 検索入力：仕入先名称 */
	private String srhVenderName;

	/** 検索入力：品目コード */
	private String srhItemCd;

	/** 検索入力：品目名称 */
	private String srhItemName;

	/** 検索入力：分類コード */
	private String srhCategoryDivision;

	/** 検索入力：仕入日(from) */
	private String srhStockingDateFrom;

	/** 検索入力：仕入日(to) */
	private String srhStockingDateTo;

	/** 検索入力：仕入番号 */
	private String srhSlipNo;

	/** 検索入力：他社コード１ */
	private String srhOtherCompanyCd1;

	/** 検索入力：取消チェックボックス */
	private boolean srhCancelCheck;

	/** 仕入区分コンボボックス */
	private List<ComboBoxItems> stockinDivisionCombo;

	/** 検索入力：仕入ステータス */
	private String srhStockingStatus;

	/** 検索入力：月次更新済チェックボックス */
	private boolean srhMonthlyCheck;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/** 帳票EXCELダウンロードフラグ */
	private boolean excelReportDownloadFlg;

	/** 帳票Excel検索用 */
	private BuyingListConditionForReport reportCondition;

	/** 伝票発行区分 */
	private Boolean srhSlipIssueDivision;

	//
	// インスタンスフィールド.
	//

	/** 検索結果格納用リスト */
	private List<BuyingList> searchList;

	/**
	 * コンストラクタ
	 */
	public BuyingListForm() {
	}

	/**
	 * ページの明細行数取得
	 * @return int ページの明細行数
	 */
	protected int getLimit() {
		return PAGE_ROW;
	}

	/**
	 * 最大データ数取得
	 * @return int 最大データ数
	 */
	protected int getThreshold() {
		return DATA_ROW;
	}

	/**
	 * PagerConditionClass取得
	 * @return BuyingPagerCondition BuyingPagerCondition
	 */
	protected Class getPagerConditionClass() {
		return BuyingPagerCondition.class;
	}

	/**
	 * Beanの全てのプロパティをデフォルトの状態にリセット
	 * 
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		if ("search".equals(getOp())) {
			// 検索入力：取消チェックボックスをクリア
			setSrhCancelCheck(false);
			// 検索入力：月次更新済チェックボックスをクリア
			setSrhMonthlyCheck(false);
			// 仕入伝票発行フラグ
			setSrhSlipIssueDivision(false);
		}

		if ("init".equals(getOp())) {
			clear();
		}
		// エクセルダウンロードフラグ
		this.setExcelDownloadFlg(Boolean.FALSE);
		// 帳票Excelダウンロードフラグ
		this.setExcelReportDownloadFlg(false);
	}

	/**
	 * validate
	 * 
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
	 * @return ActionErrors
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("search".equals(getOp())) {
			// リストのクリア
			setSearchList(new ArrayList<BuyingList>());
			// Validatorによる入力チェック
			errors = super.validate(mapping, request);
		}

		if ("reSearch".equals(getOp())) {
			// リストのクリア
			setSearchList(new ArrayList<BuyingList>());
			// Validatorによる入力チェック
			errors = super.validate(mapping, request);
		}
		return errors;
	}

	/**
	 * 初期化.
	 */
	public void clear() {
		// 仕入一覧検索結果リストのクリア
		setSearchList(new ArrayList<BuyingList>());

		// 検索入力：担当部署
		setSrhChargeOrganizationCd(null);
		// 検索入力：部署コード
		setSrhOrganizationCd(null);
		// 検索入力：担当者コード
		setSrhTantoCd(null);
		// 検索入力：仕入先コード
		setSrhVenderCd(null);
		// 検索入力：品目コード
		setSrhItemCd(null);
		// 検索入力：仕入区分
		setSrhCategoryDivision(null);
		// 検索入力：仕入日(from)
		setSrhStockingDateFrom(null);
		// 検索入力：仕入日(to)
		setSrhStockingDateTo(null);
		// 検索入力：仕入番号
		setSrhSlipNo(null);
		// 検索入力：他社コード1
		setSrhOtherCompanyCd1(null);
		// 検索入力：品目名称
		setSrhItemName(null);
		// 検索入力：部署名称
		setSrhOrganizationName(null);
		// 検索入力：仕入先名称
		setSrhVenderName(null);
		// 検索入力：担当者名
		setSrhTantoName(null);
		// 検索入力：担当部署名称
		setSrhChargeOrganizationName(null);
		// 検索入力：取消チェックボックス
		setSrhCancelCheck(false);
		// 仕入区分
		setStockinDivisionCombo(null);
		// 検索入力：仕入ステータス
		setSrhStockingStatus(null);
		// 検索入力：月次更新済チェックボックス
		setSrhMonthlyCheck(false);
		// 帳票Excel検索条件
		setReportCondition(null);
		// 帳票Excelダウンロードフラグ
		setExcelReportDownloadFlg(false);
		// 仕入伝票発行フラグ
		setSrhSlipIssueDivision(false);
	}

	/**
	 * 検索結果数を取得する。
	 * @return 検索結果数
	 */
	public int getCount() {
		int res = 0;
		if (searchList != null) {
			res = searchList.size();
		}
		return res;
	}

	/**
	 * 仕入一覧検索結果を取得します。
	 * @return searchList 仕入一覧検索結果
	 */
	public List<BuyingList> getSearchList() {
		return searchList;
	}

	/**
	 * 仕入一覧検索結果を設定します。
	 * 
	 * @param searchList 仕入一覧検索結果
	 */
	public void setSearchList(final List<BuyingList> searchList) {
		this.searchList = searchList;
	}

	//
	// 検索入力.
	//

	/**
	 * 検索入力：担当部署取得.
	 * @return String 担当部署
	 */
	public String getSrhChargeOrganizationCd() {
		return this.srhChargeOrganizationCd;
	}

	/**
	 * 検索入力：担当部署設定.
	 * @param srhChargeOrganizationCd 担当部署
	 */
	public void setSrhChargeOrganizationCd(final String srhChargeOrganizationCd) {
		this.srhChargeOrganizationCd = srhChargeOrganizationCd;
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
	 * 検索入力：担当者コード取得.
	 * @return String 担当者コード
	 */
	public String getSrhTantoCd() {
		return this.srhTantoCd;
	}

	/**
	 * 検索入力：担当者コード設定.
	 * @param srhTantoCd 担当者コード
	 */
	public void setSrhTantoCd(final String srhTantoCd) {
		this.srhTantoCd = srhTantoCd;
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
	 * 検索入力：仕入日(from)取得.
	 * @return String 仕入日(from)
	 */
	public String getSrhStockingDateFrom() {
		return this.srhStockingDateFrom;
	}

	/**
	 * 検索入力：仕入日(from)設定.
	 * @param srhStockingDateFrom 仕入日(from)
	 */
	public void setSrhStockingDateFrom(final String srhStockingDateFrom) {
		this.srhStockingDateFrom = srhStockingDateFrom;
	}

	/**
	 * 検索入力：仕入日(to)取得.
	 * @return String 仕入日(to)
	 */
	public String getSrhStockingDateTo() {
		return this.srhStockingDateTo;
	}

	/**
	 * 検索入力：仕入日(to)設定.
	 * @param srhStockingDateTo 仕入日(to)
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
	 * 検索入力：品目名称取得.
	 * @return String 品目名称
	 */
	public String getSrhItemName() {
		return srhItemName;
	}

	/**
	 * 検索入力：品目名称設定.
	 * @param srhItemName 仕入番号
	 */
	public void setSrhItemName(final String srhItemName) {
		this.srhItemName = srhItemName;
	}

	/**
	 * 検索入力：部署名称取得.
	 * @return String 部署名称
	 */
	public String getSrhOrganizationName() {
		return srhOrganizationName;
	}

	/**
	 * 検索入力：部署名称設定.
	 * @param srhOrganizationName 部署名称
	 */
	public void setSrhOrganizationName(final String srhOrganizationName) {
		this.srhOrganizationName = srhOrganizationName;
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
	 * 検索入力：担当者名称取得.
	 * @return String 担当者名称
	 */
	public String getSrhTantoName() {
		return srhTantoName;
	}

	/**
	 * 検索入力：担当者名称設定.
	 * @param srhTantoName 担当者名称
	 */
	public void setSrhTantoName(final String srhTantoName) {
		this.srhTantoName = srhTantoName;
	}

	/**
	 * 検索入力：担当部署名称取得.
	 * @return String 担当部署名称
	 */
	public String getSrhChargeOrganizationName() {
		return srhChargeOrganizationName;
	}

	/**
	 * 検索入力：担当部署名称設定.
	 * @param srhChargeOrganizationName 担当部署名称
	 */
	public void setSrhChargeOrganizationName(
			final String srhChargeOrganizationName) {
		this.srhChargeOrganizationName = srhChargeOrganizationName;
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
	 * 検索入力：仕入ステータス
	 * @return 仕入ステータス
	 */
	public String getSrhStockingStatus() {
		return this.srhStockingStatus;
	}

	/**
	 * 検索入力：仕入ステータス
	 * @param srhStockingStatus 仕入ステータス
	 */
	public void setSrhStockingStatus(final String srhStockingStatus) {
		this.srhStockingStatus = srhStockingStatus;
	}

	/**
	 * 月次更新済チェックボックス取得
	 * @return srhMonthlyCheck
	 */
	public boolean isSrhMonthlyCheck() {
		return this.srhMonthlyCheck;
	}

	/**
	 * 月次更新済チェックボックス設定
	 * @param srhMonthlyCheck 月次更新済チェックボックス
	 */
	public void setSrhMonthlyCheck(final boolean srhMonthlyCheck) {
		this.srhMonthlyCheck = srhMonthlyCheck;
	}

	/**
	 * slipIssueDivision取得
	 * @return slipIssueDivision
	 */
	public Boolean getSrhSlipIssueDivision() {
		return srhSlipIssueDivision;
	}

	/**
	 * srhSlipIssueDivision設定
	 * @param srhSlipIssueDivision srhSlipIssueDivision
	 */
	public void setSrhSlipIssueDivision(final Boolean srhSlipIssueDivision) {
		this.srhSlipIssueDivision = srhSlipIssueDivision;
	}

	/**
	 * EXCELダウンロードフラグを取得します。
	 * @return boolean EXCELダウンロードフラグ
	 */
	public boolean isExcelDownloadFlg() {
		return excelDownloadFlg;
	}

	/**
	 * EXCELダウンロードフラグを設定します。
	 * @param excelDownloadFlg EXCELダウンロードフラグ
	 */
	public void setExcelDownloadFlg(final boolean excelDownloadFlg) {
		this.excelDownloadFlg = excelDownloadFlg;
	}

	/**
	 * EXCELダウンロードフラグを取得します。
	 * @return excelReportDownloadFlg EXCELダウンロードフラグ
	 */
	public boolean isExcelReportDownloadFlg() {
		return excelReportDownloadFlg;
	}

	/**
	 * EXCELダウンロードフラグを設定します。
	 * @param excelReportDownloadFlg EXCELダウンロードフラグ
	 */
	public void setExcelReportDownloadFlg(final boolean excelReportDownloadFlg) {
		this.excelReportDownloadFlg = excelReportDownloadFlg;
	}

	/**
	 * reportConditionを取得します。
	 * @return reportCondition reportCondition
	 */
	public BuyingListConditionForReport getReportCondition() {
		return reportCondition;
	}

	/**
	 * reportConditionを設定します。
	 * @param reportCondition reportConditionフラグ
	 */
	public void setReportCondition(
			final BuyingListConditionForReport reportCondition) {
		this.reportCondition = reportCondition;
	}
}
