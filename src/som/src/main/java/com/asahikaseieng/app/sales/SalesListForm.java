/*
 * Created on 2009/02/27
 *
 * $copyright$
 * 売上トランザクション
 */
package com.asahikaseieng.app.sales;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.sales.SalesList;
import com.asahikaseieng.dao.nonentity.sales.SalesListPagerCondition;
import com.asahikaseieng.dao.nonentity.saleslistforreport.SalesListReportCondition;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 売上トランザクション一覧 Formクラス.
 * @author tosco
 * 
 */
public final class SalesListForm extends AbstractSearchForm {

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
		DATA_ROW = Integer.parseInt(rb.getString("salesListForm.search.common"));
	}

	//
	// 検索用.売上トランザクション
	//

	/** 検索入力：売上番号 */
	private String srhSalesNo;

	/** 検索入力：売上区分（分類コード） */
	private String srhCategoryDivision;

	/** 検索入力：取消フラグ */
	private boolean srhCancelFlg;

	/** 検索入力：売上日付FROM */
	private String srhSalesDateFrom;

	/** 検索入力：売上日付TO */
	private String srhSalesDateTo;

	/** 検索入力：受注番号FROM */
	private String srhOrderNoFrom;

	/** 検索入力：受注番号TO */
	private String srhOrderNoTo;

	/** 検索入力：勘定年月 */
	private String srhAccountYears;
	
	/** 検索入力：勘定年月To */
	private String srhAccountYearsTo;

	/** 検索入力：仮単価FLG */
	private boolean srhTmpUnitpriceFlg;

	/** 検索入力：預り品フラグ */
	private boolean srhKeepFlag;

	/** 検索入力：月次更新済みフラグ */
	private boolean srhMonthlyUpdateFlag;

	/** 検索入力：得意先コード */
	private String srhVenderCd;

	/** 検索入力：取引先名称 */
	private String srhVenderName1;

	/** 検索入力：担当部署コード */
	private String srhChargeOrganizationCd;

	/** 検索入力：担当部署名称 */
	private String srhChargeOrganizationName;

	/** 検索入力：品目コード */
	private String srhItemCd;

	/** 検索入力：他社コード1 */
	private String srhOtherCompanyCd1;

	/** 検索入力：品目名称 */
	private String srhItemName;

	/** 検索入力：納品先コード */
	private String srhDeliveryCd;

	/** 検索入力：納品先名称 */
	private String srhDeliveryName;

	/** 検索入力：受注客先注文番号 */
	private String srhCustomerOrderNo;

	/** 売上区分コンボボックス */
	private List<ComboBoxItems> categoryCombo;

	/** ログ出力用エラーコード */
	private String errorCd;

	/** ログ出力用エラーメッセージ */
	private String errorMsg;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/** 帳票用検索条件 */
	private SalesListReportCondition reportCondition;

	//
	// インスタンスフィールド.売上トランザクション
	//

	/** リスト */
	private List<SalesList> searchList;

	/**
	 * コンストラクタ.役職マスタ
	 */
	public SalesListForm() {
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
		return SalesListPagerCondition.class;
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			clear();
		} else if ("search".equals(getOp())) {
			clearCheck();
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
			setSearchList(new ArrayList<SalesList>());
		}

		if ("search".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}
		return errors;
	}

	/**
	 * 初期化.売上トランザクション
	 */
	public void clear() {

		/** 検索リストのクリア */
		setSearchList(new ArrayList<SalesList>());

		/** 検索入力：売上番号 */
		setSrhSalesNo(null);
		/** 検索入力：売上区分（分類コード） */
		setSrhCategoryDivision(SalesConst.COMBO_ALL_VALUE);
		/** 検索入力：取消フラグ */
		setSrhCancelFlg(false);
		/** 検索入力：受注番号FROM */
		setSrhOrderNoFrom(null);
		/** 検索入力：受注番号TO */
		setSrhOrderNoTo(null);
		/** 検索入力：売上日付FROM */
		setSrhSalesDateFrom(null);
		/** 検索入力：売上日付TO */
		setSrhSalesDateTo(null);
		/** 検索入力：勘定年月 */
		setSrhAccountYears(null);
		/** 検索入力：勘定年月 */
		setSrhAccountYearsTo(null);
		/** 検索入力：仮単価FLG */
		setSrhTmpUnitpriceFlg(false);
		/** 検索入力：預り品フラグ */
		setSrhKeepFlag(false);
		/** 検索入力：月次更新済みフラグ */
		setSrhMonthlyUpdateFlag(false);
		/** 検索入力：得意先コード */
		setSrhVenderCd(null);
		/** 検索入力：得意先名称 */
		setSrhVenderName1(null);
		/** 検索入力：担当部署コード */
		setSrhChargeOrganizationCd(null);
		/** 検索入力：担当部署名称 */
		setSrhChargeOrganizationName(null);
		/** 検索入力：品目コード */
		setSrhItemCd(null);
		/** 検索入力：品目名称 */
		setSrhItemName(null);
		/** 検索入力：他社コード1 */
		setSrhOtherCompanyCd1(null);
		/** エラーコード */
		setErrorCd(null);
		/** エラーメッセージ */
		setErrorMsg(null);
		/** 帳票出力 */
		setReportCondition(null);
		/** ダウンロードフラグを倒す */
		setExcelDownloadFlg(false);
		/** 検索入力：納入先名称 */
		setSrhDeliveryName(null);
		/** 検索入力：納入先コード */
		setSrhDeliveryCd(null);
		/** 検索入力：客先注文番号 */
		setSrhCustomerOrderNo(null);

	}

	/**
	 * 売上トランザクション：searchListを取得します。
	 * @return searchList
	 */
	public List<SalesList> getSearchList() {
		return searchList;
	}

	/**
	 * 売上トランザクション： searchListを設定します。
	 * 
	 * @param searchList searchList
	 */
	public void setSearchList(final List<SalesList> searchList) {
		this.searchList = searchList;
	}

	//
	// 検索入力.売上トランザクション
	//

	/**
	 * 検索入力：売上番号取得.
	 * @return String 売上番号
	 */
	public String getSrhSalesNo() {
		return this.srhSalesNo;
	}

	/**
	 * 検索入力：売上番号設定.
	 * @param srhSalesNo 売上番号
	 */
	public void setSrhSalesNo(final String srhSalesNo) {
		this.srhSalesNo = srhSalesNo;
	}

	/**
	 * 検索入力：売上区分（分類コード）取得.
	 * @return String 売上区分（分類コード）
	 */
	public String getSrhCategoryDivision() {
		return this.srhCategoryDivision;
	}

	/**
	 * 検索入力：売上区分（分類コード）設定.
	 * @param srhCategoryDivision 売上区分（分類コード）
	 */
	public void setSrhCategoryDivision(final String srhCategoryDivision) {
		this.srhCategoryDivision = srhCategoryDivision;
	}

	/**
	 * 検索入力：取消フラグ取得.
	 * @return String 取消フラグ
	 */
	public boolean getSrhCancelFlg() {
		return this.srhCancelFlg;
	}

	/**
	 * 検索入力：取消フラグ設定.
	 * @param srhCancelFlg 取消フラグ
	 */
	public void setSrhCancelFlg(final boolean srhCancelFlg) {
		this.srhCancelFlg = srhCancelFlg;
	}

	/**
	 * 検索入力：受注番号FROM取得.
	 * @return String 受注番号FROM
	 */
	public String getSrhOrderNoFrom() {
		return this.srhOrderNoFrom;
	}

	/**
	 * 検索入力：受注番号FROM設定.
	 * @param srhOrderNoFrom 受注番号FROM
	 */
	public void setSrhOrderNoFrom(final String srhOrderNoFrom) {
		this.srhOrderNoFrom = srhOrderNoFrom;
	}

	/**
	 * 検索入力：受注番号TO取得.
	 * @return String 受注番号TO
	 */
	public String getSrhOrderNoTo() {
		return this.srhOrderNoTo;
	}

	/**
	 * 検索入力：受注番号TO設定.
	 * @param srhOrderNoTo 受注番号TO
	 */
	public void setSrhOrderNoTo(final String srhOrderNoTo) {
		this.srhOrderNoTo = srhOrderNoTo;
	}

	/**
	 * 検索入力：売上日付FROM取得.
	 * @return String 売上日付FROM
	 */
	public String getSrhSalesDateFrom() {
		return this.srhSalesDateFrom;
	}

	/**
	 * 検索入力：売上日付FROM設定.
	 * @param srhSalesDateFrom 売上日付FROM
	 */
	public void setSrhSalesDateFrom(final String srhSalesDateFrom) {
		this.srhSalesDateFrom = srhSalesDateFrom;
	}

	/**
	 * 検索入力：売上日付TO取得.
	 * @return String 売上日付TO
	 */
	public String getSrhSalesDateTo() {
		return this.srhSalesDateTo;
	}

	/**
	 * 検索入力：売上日付TO設定.
	 * @param srhSalesDateTo 売上日付TO
	 */
	public void setSrhSalesDateTo(final String srhSalesDateTo) {
		this.srhSalesDateTo = srhSalesDateTo;
	}

	/**
	 * 検索入力：勘定年月取得.
	 * @return String 勘定年月
	 */
	public String getSrhAccountYears() {
		return this.srhAccountYears;
	}

	/**
	 * 検索入力：勘定年月設定.
	 * @param srhAccountYears 勘定年月
	 */
	public void setSrhAccountYears(final String srhAccountYears) {
		this.srhAccountYears = srhAccountYears;
	}
	
	/**
	 * 検索入力：勘定年月取得.
	 * @return String 勘定年月
	 */
	public String getSrhAccountYearsTo() {
		return this.srhAccountYearsTo;
	}

	/**
	 * 検索入力：勘定年月設定.
	 * @param srhAccountYearsTo 勘定年月
	 */
	public void setSrhAccountYearsTo(final String srhAccountYearsTo) {
		this.srhAccountYearsTo = srhAccountYearsTo;
	}


	/**
	 * 検索入力：仮単価FLG取得.
	 * @return boolean 仮単価FLG
	 */
	public boolean getSrhTmpUnitpriceFlg() {
		return this.srhTmpUnitpriceFlg;
	}

	/**
	 * 検索入力：仮単価FLG設定.
	 * @param srhTmpUnitpriceFlg 仮単価FLG
	 */
	public void setSrhTmpUnitpriceFlg(final boolean srhTmpUnitpriceFlg) {
		this.srhTmpUnitpriceFlg = srhTmpUnitpriceFlg;
	}

	/**
	 * 検索入力：預り品フラグ取得.
	 * @return boolean 預り品フラグ
	 */
	public boolean getSrhKeepFlag() {
		return this.srhKeepFlag;
	}

	/**
	 * 検索入力：預り品フラグ設定.
	 * @param srhKeepFlag 預り品フラグ
	 */
	public void setSrhKeepFlag(final boolean srhKeepFlag) {
		this.srhKeepFlag = srhKeepFlag;
	}

	/**
	 * 検索入力：月次更新済みフラグ取得.
	 * @return boolean 月次更新済みフラグ
	 */
	public boolean getSrhMonthlyUpdateFlag() {
		return this.srhMonthlyUpdateFlag;
	}

	/**
	 * 検索入力：月次更新済みフラグ設定.
	 * @param srhMonthlyUpdateFlag 月次更新済みフラグ
	 */
	public void setSrhMonthlyUpdateFlag(final boolean srhMonthlyUpdateFlag) {
		this.srhMonthlyUpdateFlag = srhMonthlyUpdateFlag;
	}

	/**
	 * 検索入力：得意先コード取得.
	 * @return String 得意先コード
	 */
	public String getSrhVenderCd() {
		return this.srhVenderCd;
	}

	/**
	 * 検索入力：得意先コード設定.
	 * @param srhVenderCd 得意先コード
	 */
	public void setSrhVenderCd(final String srhVenderCd) {
		this.srhVenderCd = srhVenderCd;
	}

	/**
	 * 検索入力：取引先名称取得.
	 * @return String 取引先名称
	 */
	public String getSrhVenderName1() {
		return this.srhVenderName1;
	}

	/**
	 * 検索入力：取引先名称設定.
	 * @param srhVenderName1 取引先名称
	 */
	public void setSrhVenderName1(final String srhVenderName1) {
		this.srhVenderName1 = srhVenderName1;
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
	 * 検索入力：担当部署名称取得.
	 * @return String 担当部署名称
	 */
	public String getSrhChargeOrganizationName() {
		return this.srhChargeOrganizationName;
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
		return this.srhItemName;
	}

	/**
	 * 検索入力：品目名称設定.
	 * @param srhItemName 品目名称
	 */
	public void setSrhItemName(final String srhItemName) {
		this.srhItemName = srhItemName;
	}

	/**
	 * 検索入力：他社コード1取得.
	 * @return String 他社コード1
	 */
	public String getSrhOtherCompanyCd1() {
		return this.srhOtherCompanyCd1;
	}

	/**
	 * 検索入力：他社コード1設定.
	 * @param srhOtherCompanyCd1 他社コード1
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
	 * 売上区分コンボボックスを取得します。
	 * @return 売上区分コンボボックス
	 */
	public List<ComboBoxItems> getCategoryCombo() {
		return categoryCombo;
	}

	/**
	 * 売上区分コンボボックスを設定します。
	 * @param categoryCombo 売上区分コンボボックス
	 */
	public void setCategoryCombo(final List<ComboBoxItems> categoryCombo) {
		this.categoryCombo = categoryCombo;
	}

	/**
	 * エラーコードの取得.
	 * @return String エラーコード
	 */
	public String getErrorCd() {
		return errorCd;
	}

	/**
	 * エラーコードの設定.
	 * @param errorCd エラーコード
	 */
	public void setErrorCd(final String errorCd) {
		this.errorCd = errorCd;
	}

	/**
	 * エラーメッセージ取得.
	 * @return String エラーメッセージ
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * エラーメッセージ設定
	 * @param errorMsg エラーメッセージ
	 */
	public void setErrorMsg(final String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * チェックボックス用クリア処理.
	 */
	private void clearCheck() {
		setSrhCancelFlg(Boolean.FALSE);
		setSrhTmpUnitpriceFlg(Boolean.FALSE);
		setSrhKeepFlag(Boolean.FALSE);
		setSrhMonthlyUpdateFlag(Boolean.FALSE);
	}

	/**
	 * reportConditionを取得します。
	 * @return reportCondition
	 */
	public SalesListReportCondition getReportCondition() {
		return reportCondition;
	}

	/**
	 * reportConditionを設定します。
	 * @param reportCondition reportCondition
	 */
	public void setReportCondition(
			final SalesListReportCondition reportCondition) {
		this.reportCondition = reportCondition;
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
	 * srhCustomerOrderNoを取得します。
	 * @return srhCustomerOrderNo
	 */
	public String getSrhCustomerOrderNo() {
		return srhCustomerOrderNo;
	}

	/**
	 * srhCustomerOrderNoを設定します。
	 * @param srhCustomerOrderNo srhCustomerOrderNo
	 */
	public void setSrhCustomerOrderNo(final String srhCustomerOrderNo) {
		this.srhCustomerOrderNo = srhCustomerOrderNo;
	}

	/**
	 * srhDeliveryCdを取得します。
	 * @return srhDeliveryCd
	 */
	public String getSrhDeliveryCd() {
		return srhDeliveryCd;
	}

	/**
	 * srhDeliveryCdを設定します。
	 * @param srhDeliveryCd srhDeliveryCd
	 */
	public void setSrhDeliveryCd(final String srhDeliveryCd) {
		this.srhDeliveryCd = srhDeliveryCd;
	}

	/**
	 * srhDeliveryNameを取得します。
	 * @return srhDeliveryName
	 */
	public String getSrhDeliveryName() {
		return srhDeliveryName;
	}

	/**
	 * srhDeliveryNameを設定します。
	 * @param srhDeliveryName srhDeliveryName
	 */
	public void setSrhDeliveryName(final String srhDeliveryName) {
		this.srhDeliveryName = srhDeliveryName;
	}
}
