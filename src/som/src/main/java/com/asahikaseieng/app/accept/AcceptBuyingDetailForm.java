/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.accept;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.accept.AcceptBuyingDetailList;
import com.asahikaseieng.struts.AbstractForm;

/**
 * 受入・仕入 仕入入力詳細 Formクラス.
 * @author tosco
 */
public final class AcceptBuyingDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	private String dirtyFlg; /* 変更フラグ */

	/** 税コード変更フラグ */
	private String taxCdChangeFlg;

	//
	// インスタンスフィールド

	/** 購買NO */
	private String purchaseNo;

	/** 発注番号 */
	private String buySubcontractOrderNo;

	/** 発注日 */
	private String strOrderDate;

	/** 仕入先受注番号 */
	private String siOrderNo;

	/** 品目コード */
	private String itemCd;

	/** 品目名称 */
	private String itemQueueName;

	/** 他社コード１ */
	private String otherCompanyCd1;

	/** 発注数量 */
	private String orderQuantity;

	/** 発注数量 */
	private String strOrderQuantity;

	/** 荷姿 */
	private String styleOfPacking;

	/** 重量 */
	private String strOrderConvertQuantity;

	/** 受入重量合計 */
	private String strAcceptConvertQuantitySum;

	/** 仕入番号 */
	private String slipNo;

	/** 仕入先コード */
	private String venderCd;

	/** 仕入先名称 */
	private String venderName;

	/** 仕入先略称 */
	private String venderShortedName;

	/** 納入ロケーションコード */
	private String locationCd;

	/** 納入先名称 */
	private String locationName;

	/** 納品希望日 */
	private String strSuggestedDeliverlimitDate;

	/** 分類コード */
	private String categoryDivision;

	/** 仕入区分コンボボックス */
	private List<ComboBoxItems> stockinDivisionCombo;

	/** 分類コードリスト */
	private String[] categoryDivisionList;

	/** 仕訳反転フラグリスト */
	private String[] reversalFlgList;

	/** 仕訳反転フラグ前回値 */
	private String preReversalFlg;

	/** 仕入単価 */
	private String strHousingUnitprice;

	/** 運賃 */
	private String strFareAmount;

	/** 仕入金額 */
	private String strStockingAmount;

	/** 仕入日付 */
	private String strStockingDate;

	/** 会計部門借方コード */
	private String accountDebitSectionCd;

	/** 会計部門借方名称 */
	private String accountDebitSectionName;

	/** 会計部門貸方コード */
	private String accountCreditSectionCd;

	/** 会計部門貸方名称 */
	private String accountCreditSectionName;

	/** 借方科目コード */
	private String debitTitleCd;

	/** 借方科目名称 */
	private String debitTitleName;

	/** 貸方科目コード */
	private String creditTitleCd;

	/** 貸方科目名称 */
	private String creditTitleName;

	/** 発注書NO */
	private String orderSheetNo;

	/** 担当部署名称 */
	private String chargeOrganizationName;

	/** 発注書備考（入荷以降） */
	private String orderSheetRemark2;

	/** 備考（入荷以降） */
	private String remark2;

	/** リスト */
	private List<AcceptBuyingDetailList> detailList;

	/** 単位 */
	private String unit;

	/** 単価単位 */
	private String unitpriceUnit;

	/** 発注番号分納枝番 */
	private String orderDivideNo;

	/** 小数点桁数(仕入金額) */
	private String decimalPoint;

	/** 端数区分(仕入金額) */
	private String round;

	/** 小数点桁数(仕入数量) */
	private String decimalPointQty;

	/** 端数区分(仕入数量) */
	private String roundQty;

	/** 小数点桁数(仕入単価) */
	private String decimalPointTanka;

	/** 端数区分(仕入単価) */
	private String roundTanka;

	/** 小数点桁数(運賃) */
	private String decimalPointUntin;

	/** 端数区分(運賃) */
	private String roundUntin;

	/** 運用管理単位(区分) */
	private String unitDiv;

	/** 単価区分 */
	private String unitpriceDivision;

	/** Kg換算係数(在庫) */
	private String kgOfFractionManagement;

	/** 仕入数量合計 */
	private String sumStockingQuantity;

	/** 仕入ステータス */
	private String status2;

	/** 消費税課税区分 */
	private String taxDivision;

	/** 取引先マスタ.算出区分 */
	private String calcDivision;

	/** 自社マスタ.消費税算出区分 */
	private String compCalcDivision;

	/** 自社マスタ.消費税率 */
	private String taxRatio;

	/** フォーカス位置保存 (hidden) */
	private String focusPosition;

	// 2014/1/28 新消費税対応 ->>
	private String strTaxAmount;

	private String strTaxRatio;

	/** 消費税コンボボックス */
	private List<ComboBoxItems> taxCombo;

	// 2014/1/28 新消費税対応 <--
	//20190821 軽減税率対応
	/** 消費税 */
	private String taxCd;
	private String[] taxLabels;
	private String[] taxValues;
	private String[] taxKeys;

	/** 免税計算対象フラグ */
	private String reducedTaxTargetFlg;

	/** 軽減措置金額 */
	private String strInvoiceAmount;

	/** 税額控除割合 */
	private BigDecimal invoiceTaxRatio;

	/** 軽減措置消費税金額 */
	private String strInvoiceTaxAmount;

	/** 小数点桁数(消費税) */
	private String decimalPointTax;

	/** 端数区分(消費税) */
	private String roundTax;

	/**
	 * コンストラクタ.詳細
	 */
	public AcceptBuyingDetailForm() {
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

		if ("init".equals(getOp())) {
			clear();
		}
	}

	/**
	 * 入力データの検証
	 *
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
	 * @return ActionErrors 検証エラー内容
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("update".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}

		return errors;
	}

	/**
	 * クリア処理.
	 */
	private void clear() {

		// 購買NO
		setPurchaseNo(null);
		// 発注番号
		setBuySubcontractOrderNo(null);
		// 発注日
		setStrOrderDate(null);
		// 仕入先受注番
		setSiOrderNo(null);
		// 品目コード
		setItemCd(null);
		// 品目名称
		setItemQueueName(null);
		// 他社コード１
		setOtherCompanyCd1(null);
		// 発注数量
		setOrderQuantity(null);
		setStrOrderQuantity(null);
		// 荷姿
		setStyleOfPacking(null);
		// 重量
		setStrOrderConvertQuantity(null);
		// 受入重量合計
		setStrAcceptConvertQuantitySum(null);
		// 仕入番号
		setSlipNo(null);
		// 仕入先コード
		setVenderCd(null);
		// 仕入先名称
		setVenderName(null);
		// 仕入先略称
		setVenderShortedName(null);
		// 納入ロケーションコード
		setLocationCd(null);
		// 納入先名称
		setLocationName(null);
		// 納品希望日
		setStrSuggestedDeliverlimitDate(null);
		// 分類コード
		setCategoryDivision(null);
		// 仕入区分コンボボックス
		setStockinDivisionCombo(null);
		// 分類コードリスト
		setCategoryDivisionList(null);
		// 仕訳反転フラグリスト
		setReversalFlgList(null);
		// 仕訳反転フラグ前回値
		setPreReversalFlg(null);
		// 仕入単価
		setStrHousingUnitprice(null);
		// 運賃
		setStrFareAmount(null);
		// 仕入金額
		setStrStockingAmount(null);
		// 仕入日付
		setStrStockingDate(null);
		// 会計部門借方コード
		setAccountDebitSectionCd(null);
		// 会計部門借方名称
		setAccountDebitSectionName(null);
		// 会計部門貸方コード
		setAccountCreditSectionCd(null);
		// 会計部門貸方名称
		setAccountCreditSectionName(null);
		// 借方科目コード
		setDebitTitleCd(null);
		// 借方科目名称
		setDebitTitleName(null);
		// 貸方科目コード
		setCreditTitleCd(null);
		// 貸方科目名称
		setCreditTitleName(null);
		// 発注書NO
		setOrderSheetNo(null);
		// 発注書備考（入荷以降）
		setOrderSheetRemark2(null);
		// 備考（入荷以降）
		setRemark2(null);
		// 検索リストのクリア
		setDetailList(new ArrayList<AcceptBuyingDetailList>());
		// 単位
		setUnit(null);
		// 単価単位
		setUnitpriceUnit(null);
		// 発注番号分納枝番
		setOrderDivideNo(null);
		// 小数点桁数(仕入金額)
		setDecimalPoint(null);
		// 小数点桁数(仕入数量)
		setDecimalPointQty(null);
		// 小数点桁数(仕入単価)
		setDecimalPointTanka(null);
		// 小数点桁数(運賃)
		setDecimalPointUntin(null);
		// 端数区分(仕入金額)
		setRound(null);
		// 端数区分(仕入数量)
		setRoundQty(null);
		// 端数区分(仕入単価)
		setRoundTanka(null);
		// 端数区分(運賃)
		setRoundUntin(null);
		// 運用管理単位(区分)
		setUnitDiv(null);
		// 単価区分
		setUnitpriceDivision(null);
		// Kg換算係数(在庫)
		setKgOfFractionManagement(null);
		// 仕入数量合計
		setSumStockingQuantity(null);
		// 仕入ステータス
		setStatus2(null);
		// 消費税課税区分
		setTaxDivision(null);
		// 取引先マスタ.算出区分
		setCalcDivision(null);
		// 自社マスタ.消費税算出区分
		setCompCalcDivision(null);
		// フォーカス位置保存 (hidden)
		setFocusPosition(null);

		// 2014/1/28 新消費税対応 ->>
		/** 消費税額 */
		setStrTaxAmount(null);
		/** 消費税率 */
		setStrTaxRatio(null);
		// 2014/1/28 新消費税対応 <--
		//軽減税率対応
		/** 消費税CD */
		setTaxCd(null);
		/** 消費税率LABLE */
		setTaxLabels(null);
		/** 消費税率名称 */
		setTaxValues(null);
		/** 消費税率CD */
		setTaxKeys(null);
		/** 税コード変更フラグ */
		setTaxCdChangeFlg("false");
		/** 免税計算対象フラグ */
		setReducedTaxTargetFlg(null);
		/** 軽減措置金額 */
		setStrInvoiceAmount(null);
		/** 税額控除割合 */
		setInvoiceTaxRatio(null);
		/** 軽減措置消費税金額 */
		setStrInvoiceTaxAmount(null);
	}

	//
	// インスタンスメソッド
	//

	/**
	 * 購買NO取得
	 * @return String 購買NO
	 */
	public String getPurchaseNo() {
		return this.purchaseNo;
	}

	/**
	 * 購買NO設定
	 * @param purchaseNo 購買NO
	 */
	public void setPurchaseNo(final String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}

	/**
	 * 発注番号取得.
	 * @return String 発注番号
	 */
	public String getBuySubcontractOrderNo() {
		return this.buySubcontractOrderNo;
	}

	/**
	 * 発注番号設定.
	 * @param buySubcontractOrderNo 発注番号
	 */
	public void setBuySubcontractOrderNo(final String buySubcontractOrderNo) {
		this.buySubcontractOrderNo = buySubcontractOrderNo;
	}

	/**
	 * 発注日取得.
	 * @return String 発注日
	 */
	public String getStrOrderDate() {
		return this.strOrderDate;
	}

	/**
	 * 発注日設定.
	 * @param strOrderDate 発注日
	 */
	public void setStrOrderDate(final String strOrderDate) {
		this.strOrderDate = strOrderDate;
	}

	/**
	 * 仕入先受注番取得.
	 * @return String 仕入先受注番
	 */
	public String getSiOrderNo() {
		return this.siOrderNo;
	}

	/**
	 * 仕入先受注番設定.
	 * @param siOrderNo 仕入先受注番
	 */
	public void setSiOrderNo(final String siOrderNo) {
		this.siOrderNo = siOrderNo;
	}

	/**
	 * 品目コード取得.
	 * @return String 品目コード
	 */
	public String getItemCd() {
		return this.itemCd;
	}

	/**
	 * 品目コード設定.
	 * @param itemCd 品目コード
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * 品目名称取得
	 * @return String 品目名称
	 */
	public String getItemQueueName() {
		return itemQueueName;
	}

	/**
	 * 品目名称設定
	 * @param itemQueueName 品目名称
	 */
	public void setItemQueueName(final String itemQueueName) {
		this.itemQueueName = itemQueueName;
	}

	/**
	 * 他社コード１を取得します。
	 * @return String 他社コード１
	 */
	public String getOtherCompanyCd1() {
		return otherCompanyCd1;
	}

	/**
	 * 他社コード１を設定します。
	 * @param otherCompanyCd1 他社コード１
	 */
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = otherCompanyCd1;
	}

	/**
	 * 発注数量取得.
	 * @return String 発注数量
	 */
	public String getOrderQuantity() {
		return this.orderQuantity;
	}

	/**
	 * 発注数量設定.
	 * @param orderQuantity 発注数量
	 */
	public void setOrderQuantity(final String orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	/**
	 * 発注数量取得.
	 * @return String 発注数量
	 */
	public String getStrOrderQuantity() {
		return this.strOrderQuantity;
	}

	/**
	 * 発注数量設定.
	 * @param strOrderQuantity 発注数量
	 */
	public void setStrOrderQuantity(final String strOrderQuantity) {
		this.strOrderQuantity = strOrderQuantity;
	}

	/**
	 * 荷姿を取得します。
	 * @return String 荷姿
	 */
	public String getStyleOfPacking() {
		return styleOfPacking;
	}

	/**
	 * 荷姿を設定します。
	 * @param styleOfPacking 荷姿
	 */
	public void setStyleOfPacking(final String styleOfPacking) {
		this.styleOfPacking = styleOfPacking;
	}

	/**
	 * 重量取得.
	 * @return String 重量
	 */
	public String getStrOrderConvertQuantity() {
		return this.strOrderConvertQuantity;
	}

	/**
	 * 重量設定.
	 * @param strOrderConvertQuantity 重量
	 */
	public void setStrOrderConvertQuantity(final String strOrderConvertQuantity) {
		this.strOrderConvertQuantity = strOrderConvertQuantity;
	}

	/**
	 * 受入重量合計取得.
	 * @return String 受入重量合計
	 */
	public String getStrAcceptConvertQuantitySum() {
		return this.strAcceptConvertQuantitySum;
	}

	/**
	 * 受入重量合計設定.
	 * @param strAcceptConvertQuantitySum 受入重量合計
	 */
	public void setStrAcceptConvertQuantitySum(
			final String strAcceptConvertQuantitySum) {
		this.strAcceptConvertQuantitySum = strAcceptConvertQuantitySum;
	}

	/**
	 * 仕入番号取得.
	 * @return String 仕入番号
	 */
	public String getSlipNo() {
		return this.slipNo;
	}

	/**
	 * 仕入番号設定.
	 * @param slipNo 仕入番号
	 */
	public void setSlipNo(final String slipNo) {
		this.slipNo = slipNo;
	}

	/**
	 * 仕入先コード取得.
	 * @return String 仕入先コード
	 */
	public String getVenderCd() {
		return this.venderCd;
	}

	/**
	 * 仕入先コード設定.
	 * @param venderCd 仕入先コード
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * 仕入先名称を取得します。
	 * @return String 仕入先名称
	 */
	public String getVenderName() {
		return venderName;
	}

	/**
	 * 仕入先名称を設定します。
	 * @param venderName 仕入先名称
	 */
	public void setVenderName(final String venderName) {
		this.venderName = venderName;
	}

	/**
	 * 納入ロケーションコード取得.
	 * @return String 納入ロケーションコード
	 */
	public String getLocationCd() {
		return this.locationCd;
	}

	/**
	 * 納入ロケーションコード設定.
	 * @param locationCd 納入ロケーションコード
	 */
	public void setLocationCd(final String locationCd) {
		this.locationCd = locationCd;
	}

	/**
	 * 納入先名称を取得します。
	 * @return String 納入先名称
	 */
	public String getLocationName() {
		return locationName;
	}

	/**
	 * 納入先名称を設定します。
	 * @param locationName 納入先名称
	 */
	public void setLocationName(final String locationName) {
		this.locationName = locationName;
	}

	/**
	 * 納品希望日取得.
	 * @return String 納品希望日
	 */
	public String getStrSuggestedDeliverlimitDate() {
		return this.strSuggestedDeliverlimitDate;
	}

	/**
	 * 納品希望日設定.
	 * @param strSuggestedDeliverlimitDate 納品希望日
	 */
	public void setStrSuggestedDeliverlimitDate(
			final String strSuggestedDeliverlimitDate) {
		this.strSuggestedDeliverlimitDate = strSuggestedDeliverlimitDate;
	}

	/**
	 * 分類コードを取得します。
	 * @return String 分類コード
	 */
	public String getCategoryDivision() {
		return categoryDivision;
	}

	/**
	 * 分類コードを設定します。
	 * @param categoryDivision 分類コード
	 */
	public void setCategoryDivision(final String categoryDivision) {
		this.categoryDivision = categoryDivision;
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
	 * 分類コードリスト取得
	 * @return String[] 分類コードリスト
	 */
	public String[] getCategoryDivisionList() {
		return categoryDivisionList;
	}

	/**
	 * 分類コードリスト設定
	 * @param categoryDivisionList 分類コードリスト
	 */
	public void setCategoryDivisionList(final String[] categoryDivisionList) {
		this.categoryDivisionList = categoryDivisionList;
	}

	/**
	 * 仕訳反転フラグリスト取得
	 * @return String[] 仕訳反転フラグリスト
	 */
	public String[] getReversalFlgList() {
		return reversalFlgList;
	}

	/**
	 * 仕訳反転フラグリスト設定
	 * @param reversalFlgList 仕訳反転フラグリスト
	 */
	public void setReversalFlgList(final String[] reversalFlgList) {
		this.reversalFlgList = reversalFlgList;
	}

	/**
	 * 仕訳反転フラグ前回値取得
	 * @return String 仕訳反転フラグ前回値
	 */
	public String getPreReversalFlg() {
		return preReversalFlg;
	}

	/**
	 * 仕訳反転フラグ前回値設定
	 * @param preReversalFlg 仕訳反転フラグ前回値
	 */
	public void setPreReversalFlg(final String preReversalFlg) {
		this.preReversalFlg = preReversalFlg;
	}

	/**
	 * 仕入単価取得
	 * @return String 仕入単価
	 */
	public String getStrHousingUnitprice() {
		return strHousingUnitprice;
	}

	/**
	 * 仕入単価設定
	 * @param strHousingUnitprice 仕入単価
	 */
	public void setStrHousingUnitprice(final String strHousingUnitprice) {
		this.strHousingUnitprice = strHousingUnitprice;
	}

	/**
	 * 運賃取得
	 * @return String 運賃
	 */
	public String getStrFareAmount() {
		return strFareAmount;
	}

	/**
	 * 運賃設定
	 * @param strFareAmount 運賃
	 */
	public void setStrFareAmount(final String strFareAmount) {
		this.strFareAmount = strFareAmount;
	}

	/**
	 * 仕入金額取得
	 * @return String 仕入金額
	 */
	public String getStrStockingAmount() {
		return strStockingAmount;
	}

	/**
	 * 仕入金額設定
	 * @param strStockingAmount 仕入金額
	 */
	public void setStrStockingAmount(final String strStockingAmount) {
		this.strStockingAmount = strStockingAmount;
	}

	/**
	 * 仕入日付取得
	 * @return String 仕入日付
	 */
	public String getStrStockingDate() {
		return strStockingDate;
	}

	/**
	 * 仕入日付設定
	 * @param strStockingDate 仕入日付
	 */
	public void setStrStockingDate(final String strStockingDate) {
		this.strStockingDate = strStockingDate;
	}

	/**
	 * 会計部門借方コード取得
	 * @return accountDebitSectionCd
	 */
	public String getAccountDebitSectionCd() {
		return accountDebitSectionCd;
	}

	/**
	 * 会計部門借方コード設定
	 * @param accountDebitSectionCd 会計部門借方コード
	 */
	public void setAccountDebitSectionCd(final String accountDebitSectionCd) {
		this.accountDebitSectionCd = accountDebitSectionCd;
	}

	/**
	 * 会計部門借方名称取得
	 * @return accountDebitSectionName
	 */
	public String getAccountDebitSectionName() {
		return accountDebitSectionName;
	}

	/**
	 * 会計部門借方名称設定
	 * @param accountDebitSectionName 会計部門借方名称
	 */
	public void setAccountDebitSectionName(final String accountDebitSectionName) {
		this.accountDebitSectionName = accountDebitSectionName;
	}

	/**
	 * 会計部門貸方コード取得
	 * @return accountCreditSectionCd
	 */
	public String getAccountCreditSectionCd() {
		return accountCreditSectionCd;
	}

	/**
	 * 会計部門貸方コード設定
	 * @param accountCreditSectionCd 会計部門貸方コード
	 */
	public void setAccountCreditSectionCd(final String accountCreditSectionCd) {
		this.accountCreditSectionCd = accountCreditSectionCd;
	}

	/**
	 * 会計部門貸方名称取得
	 * @return accountCreditSectionName
	 */
	public String getAccountCreditSectionName() {
		return accountCreditSectionName;
	}

	/**
	 * 会計部門貸方名称設定
	 * @param accountCreditSectionName 会計部門貸方名称
	 */
	public void setAccountCreditSectionName(
			final String accountCreditSectionName) {
		this.accountCreditSectionName = accountCreditSectionName;
	}

	/**
	 * 借方科目コード取得
	 * @return debitTitleCd
	 */
	public String getDebitTitleCd() {
		return debitTitleCd;
	}

	/**
	 * 借方科目コード設定
	 * @param debitTitleCd 借方科目コード
	 */
	public void setDebitTitleCd(final String debitTitleCd) {
		this.debitTitleCd = debitTitleCd;
	}

	/**
	 * 借方科目名称取得
	 * @return debitTitleName
	 */
	public String getDebitTitleName() {
		return debitTitleName;
	}

	/**
	 * 借方科目名称設定
	 * @param debitTitleName 借方科目名称
	 */
	public void setDebitTitleName(final String debitTitleName) {
		this.debitTitleName = debitTitleName;
	}

	/**
	 * 貸方科目コード取得
	 * @return creditTitleCd
	 */
	public String getCreditTitleCd() {
		return creditTitleCd;
	}

	/**
	 * 貸方科目コード設定
	 * @param creditTitleCd 貸方科目コード
	 */
	public void setCreditTitleCd(final String creditTitleCd) {
		this.creditTitleCd = creditTitleCd;
	}

	/**
	 * 貸方科目名称取得
	 * @return creditTitleName
	 */
	public String getCreditTitleName() {
		return creditTitleName;
	}

	/**
	 * 貸方科目名称設定
	 * @param creditTitleName 貸方科目名称
	 */
	public void setCreditTitleName(final String creditTitleName) {
		this.creditTitleName = creditTitleName;
	}

	/**
	 * 発注書NO取得.
	 * @return String 発注書NO
	 */
	public String getOrderSheetNo() {
		return this.orderSheetNo;
	}

	/**
	 * 発注書NO設定.
	 * @param orderSheetNo 発注書NO
	 */
	public void setOrderSheetNo(final String orderSheetNo) {
		this.orderSheetNo = orderSheetNo;
	}

	/**
	 * 担当部署名称取得.
	 * @return String 担当部署名称
	 */
	public String getChargeOrganizationName() {
		return chargeOrganizationName;
	}

	/**
	 * 担当部署名称設定.
	 * @param chargeOrganizationName 担当部署名称
	 */
	public void setChargeOrganizationName(final String chargeOrganizationName) {
		this.chargeOrganizationName = chargeOrganizationName;
	}

	/**
	 * 発注書備考（入荷以降）取得.
	 * @return String 発注書備考（入荷以降）
	 */
	public String getOrderSheetRemark2() {
		return this.orderSheetRemark2;
	}

	/**
	 * 発注書備考（入荷以降）設定.
	 * @param orderSheetRemark2 発注書備考（入荷以降）
	 */
	public void setOrderSheetRemark2(final String orderSheetRemark2) {
		this.orderSheetRemark2 = orderSheetRemark2;
	}

	/**
	 * 備考（入荷以降）取得.
	 * @return String 備考（入荷以降）
	 */
	public String getRemark2() {
		return this.remark2;
	}

	/**
	 * 備考（入荷以降）設定.
	 * @param remark2 備考（入荷以降）
	 */
	public void setRemark2(final String remark2) {
		this.remark2 = remark2;
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
	 * 詳細リスト(ロット分割)を取得します。
	 * @return String 詳細リスト(ロット分割)
	 */
	public List<AcceptBuyingDetailList> getDetailList() {
		return detailList;
	}

	/**
	 * 詳細リスト(ロット分割)を設定します。
	 * @param detailList 詳細リスト(ロット分割)
	 */
	public void setDetailList(final List<AcceptBuyingDetailList> detailList) {
		this.detailList = detailList;
	}

	/**
	 * 単位取得
	 * @return String 単位
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * 単位設定
	 * @param unit 単位
	 */
	public void setUnit(final String unit) {
		this.unit = unit;
	}

	/**
	 * 単価単位取得
	 * @return String 単価単位
	 */
	public String getUnitpriceUnit() {
		return unitpriceUnit;
	}

	/**
	 * 単価単位設定
	 * @param unitpriceUnit 単価単位
	 */
	public void setUnitpriceUnit(final String unitpriceUnit) {
		this.unitpriceUnit = unitpriceUnit;
	}

	/**
	 * 発注番号分納枝番取得
	 * @return String 発注番号分納枝番
	 */
	public String getOrderDivideNo() {
		return this.orderDivideNo;
	}

	/**
	 * 発注番号分納枝番設定
	 * @param orderDivideNo 発注番号分納枝番
	 */
	public void setOrderDivideNo(final String orderDivideNo) {
		this.orderDivideNo = orderDivideNo;
	}

	/**
	 * 小数点桁数(仕入金額)取得
	 * @return String 小数点桁数(仕入金額)
	 */
	public String getDecimalPoint() {
		return decimalPoint;
	}

	/**
	 * 小数点桁数(仕入金額)設定
	 * @param decimalPoint 小数点桁数(仕入金額)
	 */
	public void setDecimalPoint(final String decimalPoint) {
		this.decimalPoint = decimalPoint;
	}

	/**
	 * 端数区分(仕入金額)取得
	 * @return String 端数区分(仕入金額)
	 */
	public String getRound() {
		return round;
	}

	/**
	 * 端数区分(仕入金額)設定
	 * @param round 端数区分(仕入金額)
	 */
	public void setRound(final String round) {
		this.round = round;
	}

	/**
	 * 小数点桁数(仕入数量)取得
	 * @return String 小数点桁数(仕入数量)
	 */
	public String getDecimalPointQty() {
		return decimalPointQty;
	}

	/**
	 * 小数点桁数(仕入数量)設定
	 * @param decimalPointQty 小数点桁数(仕入数量)
	 */
	public void setDecimalPointQty(final String decimalPointQty) {
		this.decimalPointQty = decimalPointQty;
	}

	/**
	 * 端数区分(仕入数量)取得
	 * @return String 端数区分(仕入数量)
	 */
	public String getRoundQty() {
		return roundQty;
	}

	/**
	 * 端数区分(仕入数量)設定
	 * @param roundQty 端数区分(仕入数量)
	 */
	public void setRoundQty(final String roundQty) {
		this.roundQty = roundQty;
	}

	/**
	 * 小数点桁数(仕入単価)取得
	 * @return String 小数点桁数(仕入単価)
	 */
	public String getDecimalPointTanka() {
		return decimalPointTanka;
	}

	/**
	 * 小数点桁数(仕入単価)設定
	 * @param decimalPointTanka 小数点桁数(仕入単価)
	 */
	public void setDecimalPointTanka(final String decimalPointTanka) {
		this.decimalPointTanka = decimalPointTanka;
	}

	/**
	 * 端数区分(仕入単価)取得
	 * @return String 端数区分(仕入単価)
	 */
	public String getRoundTanka() {
		return roundTanka;
	}

	/**
	 * 端数区分(仕入単価)設定
	 * @param roundTanka 端数区分(仕入単価)
	 */
	public void setRoundTanka(final String roundTanka) {
		this.roundTanka = roundTanka;
	}

	/**
	 * 小数点桁数(運賃)取得
	 * @return String 小数点桁数(運賃)
	 */
	public String getDecimalPointUntin() {
		return decimalPointUntin;
	}

	/**
	 * 小数点桁数(運賃)設定
	 * @param decimalPointUntin 小数点桁数(運賃)
	 */
	public void setDecimalPointUntin(final String decimalPointUntin) {
		this.decimalPointUntin = decimalPointUntin;
	}

	/**
	 * 端数区分(運賃)取得
	 * @return String 端数区分(運賃)
	 */
	public String getRoundUntin() {
		return roundUntin;
	}

	/**
	 * 端数区分(運賃)設定
	 * @param roundUntin 端数区分(運賃)
	 */
	public void setRoundUntin(final String roundUntin) {
		this.roundUntin = roundUntin;
	}

	/**
	 * 運用管理単位(区分)取得
	 * @return String 運用管理単位(区分)
	 */
	public String getUnitDiv() {
		return unitDiv;
	}

	/**
	 * 運用管理単位(区分)設定
	 * @param unitDiv 運用管理単位(区分)
	 */
	public void setUnitDiv(final String unitDiv) {
		this.unitDiv = unitDiv;
	}

	/**
	 * 単価区分取得
	 * @return String 単価区分
	 */
	public String getUnitpriceDivision() {
		return unitpriceDivision;
	}

	/**
	 * 単価区分設定
	 * @param unitpriceDivision 単価区分
	 */
	public void setUnitpriceDivision(final String unitpriceDivision) {
		this.unitpriceDivision = unitpriceDivision;
	}

	/**
	 * Kg換算係数(在庫)取得
	 * @return String Kg換算係数(在庫)
	 */
	public String getKgOfFractionManagement() {
		return kgOfFractionManagement;
	}

	/**
	 * Kg換算係数(在庫)設定
	 * @param kgOfFractionManagement Kg換算係数(在庫)
	 */
	public void setKgOfFractionManagement(final String kgOfFractionManagement) {
		this.kgOfFractionManagement = kgOfFractionManagement;
	}

	/**
	 * 仕入数量合計取得
	 * @return String 仕入数量合計
	 */
	public String getSumStockingQuantity() {
		return sumStockingQuantity;
	}

	/**
	 * 仕入数量合計設定
	 * @param sumStockingQuantity 仕入数量合計
	 */
	public void setSumStockingQuantity(final String sumStockingQuantity) {
		this.sumStockingQuantity = sumStockingQuantity;
	}

	/**
	 * 仕入ステータス取得
	 * @return String 仕入ステータス
	 */
	public String getStatus2() {
		return status2;
	}

	/**
	 * 仕入ステータス設定
	 * @param status2 仕入ステータス
	 */
	public void setStatus2(final String status2) {
		this.status2 = status2;
	}

	/**
	 * 消費税課税区分取得
	 * @return BigDecimal 消費税課税区分
	 */
	public String getTaxDivision() {
		return taxDivision;
	}

	/**
	 * 消費税課税区分設定
	 * @param taxDivision 消費税課税区分
	 */
	public void setTaxDivision(final String taxDivision) {
		this.taxDivision = taxDivision;
	}

	/**
	 * venderShortedNameを取得します。
	 * @return venderShortedName
	 */
	public String getVenderShortedName() {
		return venderShortedName;
	}

	/**
	 * venderShortedNameを設定します。
	 * @param venderShortedName venderShortedName
	 */
	public void setVenderShortedName(final String venderShortedName) {
		this.venderShortedName = venderShortedName;
	}

	/**
	 * 取引先マスタ.算出区分取得
	 * @return BigDecimal 取引先マスタ.算出区分
	 */
	public String getCalcDivision() {
		return calcDivision;
	}

	/**
	 * 取引先マスタ.算出区分設定
	 * @param calcDivision 取引先マスタ.算出区分
	 */
	public void setCalcDivision(final String calcDivision) {
		this.calcDivision = calcDivision;
	}

	/**
	 * 自社マスタ.消費税算出区分取得
	 * @return BigDecimal 自社マスタ.消費税算出区分
	 */
	public String getCompCalcDivision() {
		return compCalcDivision;
	}

	/**
	 * 自社マスタ.消費税算出区分設定
	 * @param compCalcDivision 自社マスタ.消費税算出区分
	 */
	public void setCompCalcDivision(final String compCalcDivision) {
		this.compCalcDivision = compCalcDivision;
	}

	/**
	 * 自社マスタ.消費税率取得
	 * @return BigDecimal 自社マスタ.消費税率
	 */
	public String getTaxRatio() {
		return taxRatio;
	}

	/**
	 * 自社マスタ.消費税率設定
	 * @param taxRatio 自社マスタ.消費税率
	 */
	public void setTaxRatio(final String taxRatio) {
		this.taxRatio = taxRatio;
	}

	/**
	 * フォーカス位置保存を取得します。
	 * @return focusPosition
	 */
	public String getFocusPosition() {
		return focusPosition;
	}

	/**
	 * フォーカス位置保存を設定します。
	 * @param focusPosition フォーカス位置保存
	 */
	public void setFocusPosition(final String focusPosition) {
		this.focusPosition = focusPosition;
	}

	/**
	 * 明細行数を取得する。
	 * @return 明細行数
	 */
	public int getDetailCount() {
		int count = 0;
		if (detailList != null) {
			count = detailList.size();
		}
		return count;
	}

	// 2014/3/5 新消費税対応->
	/**
	 * 消費税率コンボボックスを取得します。
	 * @return carryCombo
	 */
	public List<ComboBoxItems> getTaxCombo() {
		return taxCombo;
	}

	/**
	 * 消費税率コンボボックスを設定します。
	 * @param carryCombo 消費税率コンボボックス
	 */
	public void setTaxCombo(final List<ComboBoxItems> carryCombo) {
		this.taxCombo = carryCombo;
	}

	/**
	 * 購買トランザクション消費税額取得.
	 * @return String 購買トランザクション消費税額
	 */
	public String getStrTaxAmount() {
		return strTaxAmount;
	}

	/**
	 * 購買トランザクション消費税額設定.
	 * @param strTaxAmount 購買トランザクション消費税額
	 */
	public void setStrTaxAmount(final String strTaxAmount) {
		this.strTaxAmount = strTaxAmount;
	}

	/**
	 * 購買トランザクション消費税率取得.
	 * @return String 消費税率
	 */
	public String getStrTaxRatio() {
		return strTaxRatio;
	}

	/**
	 * 購買トランザクション消費税率設定.
	 * @param strTaxRatio 消費税率
	 */
	public void setStrTaxRatio(final String strTaxRatio) {
		this.strTaxRatio = strTaxRatio;
	}
	// 2014/3/5 新消費税対応<-

	/**
	 * taxCdを取得します。
	 * @return taxCd
	 */
	public String getTaxCd() {
		return taxCd;
	}

	/**
	 * taxCdを設定します。
	 * @param taxCd taxCd
	 */
	public void setTaxCd(String taxCd) {
		this.taxCd = taxCd;
	}

	/**
	 * taxLabelsを取得します。
	 * @return taxLabels
	 */
	public String[] getTaxLabels() {
		return taxLabels;
	}

	/**
	 * taxLabelsを設定します。
	 * @param taxLabels taxLabels
	 */
	public void setTaxLabels(String[] taxLabels) {
		this.taxLabels = taxLabels;
	}

	/**
	 * taxValuesを取得します。
	 * @return taxValues
	 */
	public String[] getTaxValues() {
		return taxValues;
	}

	/**
	 * taxValuesを設定します。
	 * @param taxValues taxValues
	 */
	public void setTaxValues(String[] taxValues) {
		this.taxValues = taxValues;
	}

	/**
	 * taxKeysを取得します。
	 * @return taxKeys
	 */
	public String[] getTaxKeys() {
		return taxKeys;
	}

	/**
	 * taxKeysを設定します。
	 * @param taxKeys taxKeys
	 */
	public void setTaxKeys(String[] taxKeys) {
		this.taxKeys = taxKeys;
	}

	/**
	 * taxCdChangeFlgを取得します。
	 * @return taxCdChangeFlg
	 */
	public String getTaxCdChangeFlg() {
		return taxCdChangeFlg;
	}

	/**
	 * taxCdChangeFlgを設定します。
	 * @param taxCdChangeFlg taxCdChangeFlg
	 */
	public void setTaxCdChangeFlg(String taxCdChangeFlg) {
		this.taxCdChangeFlg = taxCdChangeFlg;
	}

	/**
	 * reducedTaxTargetFlgを取得します。
	 * @return reducedTaxTargetFlg
	 */
	public String getReducedTaxTargetFlg() {
		return reducedTaxTargetFlg;
	}

	/**
	 * reducedTaxTargetFlgを設定します。
	 * @param reducedTaxTargetFlg reducedTaxTargetFlg
	 */
	public void setReducedTaxTargetFlg(String reducedTaxTargetFlg) {
		this.reducedTaxTargetFlg = reducedTaxTargetFlg;
	}

	/**
	 * strInvoiceAmountを取得します。
	 * @return strInvoiceAmount
	 */
	public String getStrInvoiceAmount() {
		return strInvoiceAmount;
	}

	/**
	 * strInvoiceAmountを設定します。
	 * @param strInvoiceAmount strInvoiceAmount
	 */
	public void setStrInvoiceAmount(String strInvoiceAmount) {
		this.strInvoiceAmount = strInvoiceAmount;
	}

	/**
	 * invoiceTaxRatioを取得します。
	 * @return invoiceTaxRatio
	 */
	public BigDecimal getInvoiceTaxRatio() {
		return invoiceTaxRatio;
	}

	/**
	 * invoiceTaxRatioを設定します。
	 * @param invoiceTaxRatio invoiceTaxRatio
	 */
	public void setInvoiceTaxRatio(BigDecimal invoiceTaxRatio) {
		this.invoiceTaxRatio = invoiceTaxRatio;
	}

	/**
	 * strInvoiceTaxAmountを取得します。
	 * @return strInvoiceTaxAmount
	 */
	public String getStrInvoiceTaxAmount() {
		return strInvoiceTaxAmount;
	}

	/**
	 * strInvoiceTaxAmountを設定します。
	 * @param strInvoiceTaxAmount strInvoiceTaxAmount
	 */
	public void setStrInvoiceTaxAmount(String strInvoiceTaxAmount) {
		this.strInvoiceTaxAmount = strInvoiceTaxAmount;
	}

	public String getDecimalPointTax() {
		return decimalPointTax;
	}

	public void setDecimalPointTax(String decimalPointTax) {
		this.decimalPointTax = decimalPointTax;
	}

	public String getRoundTax() {
		return roundTax;
	}

	public void setRoundTax(String roundTax) {
		this.roundTax = roundTax;
	}
}
