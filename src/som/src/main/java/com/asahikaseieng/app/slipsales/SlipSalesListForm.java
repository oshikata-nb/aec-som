/*
 * Created on 2009/02/27
 *
 * $copyright$
 * 売上トランザクション
 */
package com.asahikaseieng.app.slipsales;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.slipsales.SlipSalesList;
import com.asahikaseieng.dao.nonentity.slipsales.SlipSalesListPagerCondition;
import com.asahikaseieng.dao.nonentity.slipsalesforreport.SlipSalesListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 売上伝票出力画面 Formクラス.
 * @author tosco
 * 
 */
public final class SlipSalesListForm extends AbstractSearchForm {

	private static final long serialVersionUID = 1L;

	private String dirtyFlg; /* 変更フラグ */

	/* ページの明細行数 */
	private static final int PAGE_ROW;

	/* 最大データ数 */
	private static final int DATA_ROW;

	static {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.SYSTEM_PROPERTIES);

		PAGE_ROW = Integer.parseInt(rb.getString("linage.slipsales.list"));
		DATA_ROW = Integer.parseInt(rb.getString("threshold.slipsales.list"));
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

	/** 検索入力：売上伝票NO */
	private String srhSalesSlipNo;

	/** 検索入力：出荷予定日 */
	private String srhScheduledShippingDate;

	/** 検索入力：納入予定日 */
	private String srhDeliveryExpectedDate;

	/** 検索入力：注文番号 */
	private String srhCustomerOrderNo;

	/** 検索入力：運送店コード */
	private String srhCarryCd;

	/** 検索入力：運送店名称 */
	private String srhCarryName;

	/** 検索入力：ﾛｹｰｼｮﾝコード */
	private String srhLocationCd;

	/** 検索入力：ﾛｹｰｼｮﾝ名称 */
	private String srhLocationName;

	/** 検索入力：納入先コード */
	private String srhDeliveryCd;

	/** 検索入力：納入先名称 */
	private String srhDeliveryName;

	/** 売上区分コンボボックス */
	private List<ComboBoxItems> categoryCombo;

	/** 運送店コンボボックス */
	private List<ComboBoxItems> carryCombo;

	/** エラーメッセージ */
	private String errMsg;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/** 検索入力：出荷伝票発行済 */
	private Boolean srhSlipPublishComp;

	/** 帳票EXCELダウンロードフラグ */
	private boolean excelReportDownloadFlg;

	/** 帳票Excel検索用 */
	private SlipSalesListConditionForReport reportCondition;

	// 2015/9/7 自動FAX対応
	/** 検索入力：伝票発行日FROM */
	private String srhSlipDateFrom;

	/** 検索入力：伝票発行日TO */
	private String srhSlipDateTo;

	/** 検索入力：売上伝票送信済 */
	private Boolean srhSlipSendComp;

	/* 送信区分 */
	private BigDecimal srhFaxOutput;

	/* 売上FAX送信区分 */
	private BigDecimal srhSalesFaxOutput;
	/* 売上メール送信区分 */
	private BigDecimal srhSalesMailOutput;

	//
	// インスタンスフィールド.売上トランザクション
	//

	/** リスト */
	private List<SlipSalesList> searchList;

	/**
	 * コンストラクタ.役職マスタ
	 */
	public SlipSalesListForm() {
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
		return SlipSalesListPagerCondition.class;
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
			setSearchList(new ArrayList<SlipSalesList>());
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

		// 検索リストのクリア
		setSearchList(new ArrayList<SlipSalesList>());

		// 検索入力：売上番号
		setSrhSalesNo(null);
		// 検索入力：売上区分（分類コード）
		setSrhCategoryDivision("0");
		// 検索入力：取消フラグ
		setSrhCancelFlg(false);
		// 検索入力：受注番号FROM
		setSrhOrderNoFrom(null);
		// 検索入力：受注番号TO
		setSrhOrderNoTo(null);
		// 検索入力：売上日付FROM
		setSrhSalesDateFrom(null);
		// 検索入力：売上日付TO
		setSrhSalesDateTo(null);
		// 検索入力：勘定年月
		setSrhAccountYears(null);
		// 検索入力：仮単価FLG
		setSrhTmpUnitpriceFlg(false);
		// 検索入力：預り品フラグ
		setSrhKeepFlag(false);
		// 検索入力：月次更新済みフラグ
		setSrhMonthlyUpdateFlag(false);
		// 検索入力：得意先コード
		setSrhVenderCd(null);
		// 検索入力：得意先名称
		setSrhVenderName1(null);
		// 検索入力：担当部署コード
		setSrhChargeOrganizationCd(null);
		// 検索入力：担当部署名称
		setSrhChargeOrganizationName(null);
		// 検索入力：品目コード
		setSrhItemCd(null);
		// 検索入力：品目名称
		setSrhItemName(null);
		// 検索入力：他社コード1
		setSrhOtherCompanyCd1(null);

		// 検索入力：売上伝票NO
		setSrhSalesSlipNo(null);
		// 検索入力：出荷予定日
		setSrhScheduledShippingDate(null);
		// 検索入力：納入予定日
		setSrhDeliveryExpectedDate(null);
		// 検索入力：注文番号
		setSrhCustomerOrderNo(null);
		// 検索入力：運送店コード
		setSrhCarryCd(null);
		// 検索入力：運送店名称
		setSrhCarryName(null);
		// 検索入力：ﾛｹｰｼｮﾝコード
		setSrhLocationCd(null);
		// 検索入力：ﾛｹｰｼｮﾝ名称
		setSrhLocationName(null);
		// 検索入力：納入先コード
		setSrhDeliveryCd(null);
		// 検索入力：納入先名称
		setSrhDeliveryName(null);

		setSrhSlipPublishComp(Boolean.FALSE); // 売上伝票発行済

		// Excelダウンロードフラグ
		setExcelDownloadFlg(false);

		// 帳票Excelダウンロードフラグ
		setExcelReportDownloadFlg(false);

		// 帳票Excel検索条件
		setReportCondition(null);

		// 2015/9/7 自動FAX対応
		setSrhSlipDateFrom(null);
		setSrhSlipDateTo(null);
		setSrhSlipSendComp(Boolean.FALSE);
		setSrhFaxOutput(BigDecimal.ZERO);
		setSrhSalesFaxOutput(new BigDecimal(0));
		setSrhSalesMailOutput(new BigDecimal(0));
	}

	/**
	 * 売上トランザクション：searchListを取得します。
	 * @return searchList
	 */
	public List<SlipSalesList> getSearchList() {
		return searchList;
	}

	/**
	 * 売上トランザクション： searchListを設定します。
	 * 
	 * @param searchList searchList
	 */
	public void setSearchList(final List<SlipSalesList> searchList) {
		this.searchList = searchList;
	}

	/**
	 * 検索結果件数を取得
	 * @return int 件数
	 */
	public int getSearchListLength() {
		return searchList.size();
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
	 * チェックボックス用クリア処理.
	 */
	private void clearCheck() {
		setSrhCancelFlg(Boolean.FALSE);
		setSrhTmpUnitpriceFlg(Boolean.FALSE);
		setSrhKeepFlag(Boolean.FALSE);
		setSrhMonthlyUpdateFlag(Boolean.FALSE);
		setSrhSlipPublishComp(Boolean.FALSE); // 売上伝票発行済
		setSrhSlipSendComp(Boolean.FALSE); // 売上伝票送信済
	}

	/**
	 * 運送店コードを取得します。
	 * @return srhCarryCd
	 */
	public String getSrhCarryCd() {
		return srhCarryCd;
	}

	/**
	 * 運送店コードを設定します。
	 * @param srhCarryCd 運送店コード
	 */
	public void setSrhCarryCd(final String srhCarryCd) {
		this.srhCarryCd = srhCarryCd;
	}

	/**
	 * 運送店名称を取得します。
	 * @return srhCarryName
	 */
	public String getSrhCarryName() {
		return srhCarryName;
	}

	/**
	 * 運送店名称を設定します。
	 * @param srhCarryName 運送店名称
	 */
	public void setSrhCarryName(final String srhCarryName) {
		this.srhCarryName = srhCarryName;
	}

	/**
	 * 注文番号を取得します。
	 * @return srhCustomerOrderNo
	 */
	public String getSrhCustomerOrderNo() {
		return srhCustomerOrderNo;
	}

	/**
	 * 注文番号を設定します。
	 * @param srhCustomerOrderNo 注文番号
	 */
	public void setSrhCustomerOrderNo(final String srhCustomerOrderNo) {
		this.srhCustomerOrderNo = srhCustomerOrderNo;
	}

	/**
	 * 納入先コードを取得します。
	 * @return srhDeliveryCd
	 */
	public String getSrhDeliveryCd() {
		return srhDeliveryCd;
	}

	/**
	 * 納入先コードを設定します。
	 * @param srhDeliveryCd 納入先コード
	 */
	public void setSrhDeliveryCd(final String srhDeliveryCd) {
		this.srhDeliveryCd = srhDeliveryCd;
	}

	/**
	 * 納入予定日を取得します。
	 * @return srhDeliveryExpectedDate
	 */
	public String getSrhDeliveryExpectedDate() {
		return srhDeliveryExpectedDate;
	}

	/**
	 * 納入予定日を設定します。
	 * @param srhDeliveryExpectedDate 納入予定日
	 */
	public void setSrhDeliveryExpectedDate(final String srhDeliveryExpectedDate) {
		this.srhDeliveryExpectedDate = srhDeliveryExpectedDate;
	}

	/**
	 * 納入先名称を取得します。
	 * @return srhDeliveryName
	 */
	public String getSrhDeliveryName() {
		return srhDeliveryName;
	}

	/**
	 * 納入先名称を設定します。
	 * @param srhDeliveryName 納入先名称
	 */
	public void setSrhDeliveryName(final String srhDeliveryName) {
		this.srhDeliveryName = srhDeliveryName;
	}

	/**
	 * ロケーションコードを取得します。
	 * @return srhLocationCd
	 */
	public String getSrhLocationCd() {
		return srhLocationCd;
	}

	/**
	 * ロケーションコードを設定します。
	 * @param srhLocationCd ロケーションコード
	 */
	public void setSrhLocationCd(final String srhLocationCd) {
		this.srhLocationCd = srhLocationCd;
	}

	/**
	 * ロケーション名称を取得します。
	 * @return srhLocationName
	 */
	public String getSrhLocationName() {
		return srhLocationName;
	}

	/**
	 * ロケーション名称を設定します。
	 * @param srhLocationName ロケーション名称
	 */
	public void setSrhLocationName(final String srhLocationName) {
		this.srhLocationName = srhLocationName;
	}

	/**
	 * 売上伝票NOを取得します。
	 * @return srhSalesSlipNo
	 */
	public String getSrhSalesSlipNo() {
		return srhSalesSlipNo;
	}

	/**
	 * 売上伝票NOを設定します。
	 * @param srhSalesSlipNo 売上伝票NO
	 */
	public void setSrhSalesSlipNo(final String srhSalesSlipNo) {
		this.srhSalesSlipNo = srhSalesSlipNo;
	}

	/**
	 * 出荷予定日を取得します。
	 * @return srhScheduledShippingDate
	 */
	public String getSrhScheduledShippingDate() {
		return srhScheduledShippingDate;
	}

	/**
	 * 出荷予定日を設定します。
	 * @param srhScheduledShippingDate 出荷予定日
	 */
	public void setSrhScheduledShippingDate(
			final String srhScheduledShippingDate) {
		this.srhScheduledShippingDate = srhScheduledShippingDate;
	}

	/**
	 * 運送店コンボボックスを取得します。
	 * @return carryCombo
	 */
	public List<ComboBoxItems> getCarryCombo() {
		return carryCombo;
	}

	/**
	 * 運送店コンボボックスを設定します。
	 * @param carryCombo 運送店コンボボックス
	 */
	public void setCarryCombo(final List<ComboBoxItems> carryCombo) {
		this.carryCombo = carryCombo;
	}

	/**
	 * エラーメッセージを取得します。
	 * @return エラーメッセージ
	 */
	public String getErrMsg() {
		return errMsg;
	}

	/**
	 * エラーメッセージを設定します。
	 * @param errMsg エラーメッセージ
	 */
	public void setErrMsg(final String errMsg) {
		this.errMsg = errMsg;
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
	 * srhSlipPublishCompを取得します。
	 * @return srhSlipPublishComp
	 */
	public Boolean getSrhSlipPublishComp() {
		return srhSlipPublishComp;
	}

	/**
	 * srhSlipPublishCompを設定します。
	 * @param srhSlipPublishComp srhSlipPublishComp
	 */
	public void setSrhSlipPublishComp(final Boolean srhSlipPublishComp) {
		this.srhSlipPublishComp = srhSlipPublishComp;
	}

	/**
	 * excelReportDownloadFlgを取得します。
	 * @return excelReportDownloadFlg
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
	 * slipSalesListConditionForReportを取得します。
	 * @return slipSalesListConditionForReport
	 */
	public SlipSalesListConditionForReport getReportCondition() {
		return reportCondition;
	}

	/**
	 * slipSalesListConditionForReportを設定します。
	 * @param slipSalesListConditionForReport slipSalesListConditionForReport
	 */
	public void setReportCondition(
			final SlipSalesListConditionForReport slipSalesListConditionForReport) {
		this.reportCondition = slipSalesListConditionForReport;
	}

	/**
	 * srhSlipDateFromを取得します。
	 * @return srhSlipDateFrom
	 */
	public String getSrhSlipDateFrom() {
		return srhSlipDateFrom;
	}

	/**
	 * srhSlipDateFromを設定します。
	 * @param srhSlipDateFrom srhSlipDateFrom
	 */
	public void setSrhSlipDateFrom(final String srhSlipDateFrom) {
		this.srhSlipDateFrom = srhSlipDateFrom;
	}

	/**
	 * srhSlipDateToを取得します。
	 * @return srhSlipDateTo
	 */
	public String getSrhSlipDateTo() {
		return srhSlipDateTo;
	}

	/**
	 * srhSlipDateToを設定します。
	 * @param srhSlipDateTo srhSlipDateTo
	 */
	public void setSrhSlipDateTo(final String srhSlipDateTo) {
		this.srhSlipDateTo = srhSlipDateTo;
	}

	/**
	 * srhSlipSendCompを取得します。
	 * @return srhSlipSendComp
	 */
	public Boolean getSrhSlipSendComp() {
		return srhSlipSendComp;
	}

	/**
	 * srhSlipSendCompを設定します。
	 * @param srhSlipSendComp srhSlipSendComp
	 */
	public void setSrhSlipSendComp(final Boolean srhSlipSendComp) {
		this.srhSlipSendComp = srhSlipSendComp;
	}

	/**
	 * srhFaxOutputを取得します。
	 * @return srhFaxOutput
	 */
	public BigDecimal getSrhFaxOutput() {
		return srhFaxOutput;
	}

	/**
	 * srhFaxOutputを設定します。
	 * @param srhFaxOutput srhFaxOutput
	 */
	public void setSrhFaxOutput(final BigDecimal srhFaxOutput) {
		this.srhFaxOutput = srhFaxOutput;
	}

	/**
	 * srhSalesFaxOutputを取得します。
	 * @return srhSalesFaxOutput
	 */
	public BigDecimal getSrhSalesFaxOutput() {
		return srhSalesFaxOutput;
	}

	/**
	 * srhSalesFaxOutputを設定します。
	 * @param srhSalesFaxOutput srhSalesFaxOutput
	 */
	public void setSrhSalesFaxOutput(BigDecimal srhSalesFaxOutput) {
		this.srhSalesFaxOutput = srhSalesFaxOutput;
	}

	/**
	 * srhSalesMailOutputを取得します。
	 * @return srhSalesMailOutput
	 */
	public BigDecimal getSrhSalesMailOutput() {
		return srhSalesMailOutput;
	}

	/**
	 * srhSalesMailOutputを設定します。
	 * @param srhSalesMailOutput srhSalesMailOutput
	 */
	public void setSrhSalesMailOutput(BigDecimal srhSalesMailOutput) {
		this.srhSalesMailOutput = srhSalesMailOutput;
	}
}
