/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.accept;

import java.math.BigDecimal;

import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 受入・仕入 仕入入力画面_表示用データ格納クラス.
 *
 * @author tosco
 */
public class AcceptBuyingDetailList extends AcceptBuyingDetailListBase
		implements PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション itemQueueName */
	public static final String itemQueueName_COLUMN = "ITEM_QUEUE_NAME";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション otherCompanyCd1 */
	public static final String otherCompanyCd1_COLUMN = "OTHER_COMPANY_CD1";

	/** COLUMNアノテーション kgOfFractionManagement */
	public static final String kgOfFractionManagement_COLUMN = "KG_OF_FRACTION_MANAGEMENT";

	/** COLUMNアノテーション venderName */
	public static final String venderName_COLUMN = "VENDER_NAME";

	/** COLUMNアノテーション venderShortedName */
	public static final String venderShortedName_COLUMN = "VENDER_SHORTED_NAME";

	/** COLUMNアノテーション locationName */
	public static final String locationName_COLUMN = "LOCATION_NAME";

	/** COLUMNアノテーション organizationName */
	public static final String organizationName_COLUMN = "ORGANIZATION_NAME";

	/** COLUMNアノテーション chargeOrganizationName */
	public static final String chargeOrganizationName_COLUMN = "CHARGE_ORGANIZATION_NAME";

	/** COLUMNアノテーション unit */
	public static final String unit_COLUMN = "UNIT";

	/** COLUMNアノテーション unitDiv */
	public static final String unitDiv_COLUMN = "UNIT_DIV";

	/** COLUMNアノテーション unitpriceDivision */
	public static final String unitpriceDivision_COLUMN = "UNITPRICE_DIVISION";

	/** COLUMNアノテーション unitpriceUnit */
	public static final String unitpriceUnit_COLUMN = "UNITPRICE_UNIT";

	/** COLUMNアノテーション accountDebitSectionName */
	public static final String accountDebitSectionName_COLUMN = "ACCOUNT_DEBIT_SECTION_NAME";

	/** COLUMNアノテーション accountCreditSectionName */
	public static final String accountCreditSectionName_COLUMN = "ACCOUNT_CREDIT_SECTION_NAME";

	/** COLUMNアノテーション debitTitleName */
	public static final String debitTitleName_COLUMN = "DEBIT_TITLE_NAME";

	/** COLUMNアノテーション creditTitleName */
	public static final String creditTitleName_COLUMN = "CREDIT_TITLE_NAME";

	/** COLUMNアノテーション taxDivision */
	public static final String taxDivision_COLUMN = "TAX_DIVISION";

	/** COLUMNアノテーション calcDivision */
	public static final String calcDivision_COLUMN = "CALC_DIVISION";

	/** COLUMNアノテーション compCalcDivision */
	public static final String compCalcDivision_COLUMN = "COMP_CALC_DIVISION";

	/** COLUMNアノテーション taxRatio */
	public static final String taxRatio_COLUMN = "TAX_RATIO";

	/** COLUMNアノテーション taxFreeCalcFlg */
	public static final String taxFreeCalcFlg_COLUMN = "REDUCED_TAX_TARGET_FLG";

	/** 品目名称 */
	private String itemQueueName;

	/** 荷姿 */
	private String styleOfPacking;

	/** 他社コード1 */
	private String otherCompanyCd1;

	/** Kg換算係数(在庫) */
	private BigDecimal kgOfFractionManagement;

	/** 仕入先 */
	private String venderName;

	/** 仕入先略称 */
	private String venderShortedName;

	/** 納入先 */
	private String locationName;

	/** 部署名称 */
	private String organizationName;

	/** 担当部署名称 */
	private String chargeOrganizationName;

	/** 単位 */
	private String unit;

	/** 運用管理単位(区分) */
	private String unitDiv;

	/** 単価区分 */
	private String unitpriceDivision;

	/** 単価単位 */
	private String unitpriceUnit;

	/** 会計部門借方名称 */
	private String accountDebitSectionName;

	/** 会計部門貸方名称 */
	private String accountCreditSectionName;

	/** 借方科目名称 */
	private String debitTitleName;

	/** 貸方科目名称 */
	private String creditTitleName;

	/** 消費税課税区分 */
	private BigDecimal taxDivision;

	/** 取引先マスタ.算出区分 */
	private BigDecimal calcDivision;

	/** 自社マスタ.消費税算出区分 */
	private BigDecimal compCalcDivision;

	/** 自社マスタ.消費税率 */
	private BigDecimal taxRatio;

	/** 行番号 */
	private String strRowNo;

	/** 発注日(スラッシュ編集) */
	private String strOrderDate;

	/** 納品希望日(スラッシュ編集) */
	private String strSuggestedDeliverlimitDate;

	/** 仕入単価 */
	private String strWherehousingUnitprice;

	/** 運賃 */
	private String strFareAmount;

	/** 仕入金額 */
	private String strStockingAmount;

	/** 仕入日付 */
	private String strStockingDate;

	/** 受入日付(スラッシュ編集) */
	private String strAcceptDate;

	/** 発注数量(カンマ編集) */
	private String strOrderQuantity;

	/** 重量(カンマ編集) */
	private String strOrderConvertQuantity;

	/** 受入数量(カンマ編集) */
	private String strAcceptQuantity;

	/** 増付数量(カンマ編集) */
	private String strIncreaseQuantity;

	/** 仕入数量(カンマ編集) */
	private String strStockingQuantity;

	/** 受入重量合計(カンマ編集) */
	private String strAcceptConvertQuantitySum;

	/** 免税計算対象フラグ */
	private String reducedTaxTargetFlg;

	/**
	 * コンストラクタ.
	 */
	public AcceptBuyingDetailList() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		// 日付のスラッシュ編集
		setStrOrderDate(AecDateUtils.dateFormat(getOrderDate(), "yyyy/MM/dd"));
		setStrSuggestedDeliverlimitDate(AecDateUtils.dateFormat(
			getSuggestedDeliverlimitDate(), "yyyy/MM/dd"));
		setStrStockingDate(AecDateUtils.dateFormat(getStockingDate(),
			"yyyy/MM/dd"));
		setStrAcceptDate(AecDateUtils.dateFormat(getAcceptDate(), "yyyy/MM/dd"));

		// 行番号
		if (getRowNo() != null && !getRowNo().equals(BigDecimal.ZERO)) {
			setStrRowNo(getRowNo().toString());
		} else {
			setRowNo(new BigDecimal(1));
			setStrRowNo("1");
		}

	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
		setIncreaseQuantity(AecNumberUtils
				.convertBigDecimal(getStrIncreaseQuantity()));
		setStockingQuantity(AecNumberUtils
				.convertBigDecimal(getStrStockingQuantity()));
	}

	/* ---------- getter ,setter ---------- */
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
	 * 荷姿取得
	 * @return String 荷姿
	 */
	public String getStyleOfPacking() {
		return styleOfPacking;
	}

	/**
	 * 荷姿設定
	 * @param styleOfPacking 荷姿
	 */
	public void setStyleOfPacking(final String styleOfPacking) {
		this.styleOfPacking = styleOfPacking;
	}

	/**
	 * 他社コード1取得
	 * @return String 他社コード1
	 */
	public String getOtherCompanyCd1() {
		return otherCompanyCd1;
	}

	/**
	 * 他社コード1設定
	 * @param otherCompanyCd1 他社コード1
	 */
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = otherCompanyCd1;
	}

	/**
	 * Kg換算係数(在庫)取得
	 * @return String Kg換算係数(在庫)
	 */
	public BigDecimal getKgOfFractionManagement() {
		return kgOfFractionManagement;
	}

	/**
	 * Kg換算係数(在庫)設定
	 * @param kgOfFractionManagement Kg換算係数(在庫)
	 */
	public void setKgOfFractionManagement(
			final BigDecimal kgOfFractionManagement) {
		this.kgOfFractionManagement = kgOfFractionManagement;
	}

	/**
	 * 仕入先取得
	 * @return String 仕入先
	 */
	public String getVenderName() {
		return venderName;
	}

	/**
	 * 仕入先設定
	 * @param venderName 仕入先
	 */
	public void setVenderName(final String venderName) {
		this.venderName = venderName;
	}

	/**
	 * 納入先取得
	 * @return String 納入先
	 */
	public String getLocationName() {
		return locationName;
	}

	/**
	 * 納入先設定
	 * @param locationName 納入先
	 */
	public void setLocationName(final String locationName) {
		this.locationName = locationName;
	}

	/**
	 * 部署名称取得
	 * @return String 部署名称
	 */
	public String getOrganizationName() {
		return organizationName;
	}

	/**
	 * 部署名称設定
	 * @param organizationName 部署名称
	 */
	public void setOrganizationName(final String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * 担当部署名称取得
	 * @return String 担当部署名称
	 */
	public String getChargeOrganizationName() {
		return chargeOrganizationName;
	}

	/**
	 * 担当部署名称設定
	 * @param chargeOrganizationName 担当部署名称
	 */
	public void setChargeOrganizationName(final String chargeOrganizationName) {
		this.chargeOrganizationName = chargeOrganizationName;
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
	 * 会計部門貸方名称取得
	 * @return String 会計部門貸方名称
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
	 * 会計部門借方名称取得
	 * @return String 会計部門借方名称
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
	 * 借方科目名称取得
	 * @return String 借方科目名称
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
	 * 貸方科目名称取得
	 * @return String 貸方科目名称
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
	 * 消費税課税区分取得
	 * @return BigDecimal 消費税課税区分
	 */
	public BigDecimal getTaxDivision() {
		return taxDivision;
	}

	/**
	 * 消費税課税区分設定
	 * @param taxDivision 消費税課税区分
	 */
	public void setTaxDivision(final BigDecimal taxDivision) {
		this.taxDivision = taxDivision;
	}

	/**
	 * 取引先マスタ.算出区分取得
	 * @return BigDecimal 取引先マスタ.算出区分
	 */
	public BigDecimal getCalcDivision() {
		return calcDivision;
	}

	/**
	 * 取引先マスタ.算出区分設定
	 * @param calcDivision 取引先マスタ.算出区分
	 */
	public void setCalcDivision(final BigDecimal calcDivision) {
		this.calcDivision = calcDivision;
	}

	/**
	 * 自社マスタ.消費税算出区分取得
	 * @return BigDecimal 自社マスタ.消費税算出区分
	 */
	public BigDecimal getCompCalcDivision() {
		return compCalcDivision;
	}

	/**
	 * 自社マスタ.消費税算出区分設定
	 * @param compCalcDivision 自社マスタ.消費税算出区分
	 */
	public void setCompCalcDivision(final BigDecimal compCalcDivision) {
		this.compCalcDivision = compCalcDivision;
	}

	/**
	 * 自社マスタ.消費税率取得
	 * @return BigDecimal 自社マスタ.消費税率
	 */
	public BigDecimal getTaxRatio() {
		return taxRatio;
	}

	/**
	 * 自社マスタ.消費税率設定
	 * @param taxRatio 自社マスタ.消費税率
	 */
	public void setTaxRatio(final BigDecimal taxRatio) {
		this.taxRatio = taxRatio;
	}

	/**
	 * 行番号を取得します。
	 * @return String 行番号
	 */
	public String getStrRowNo() {
		return strRowNo;
	}

	/**
	 * 行番号を設定します。
	 * @param strRowNo 行番号
	 */
	public void setStrRowNo(final String strRowNo) {
		this.strRowNo = strRowNo;
	}

	/**
	 * 発注日(スラッシュ編集)を取得します。
	 * @return String 発注日(スラッシュ編集)
	 */
	public String getStrOrderDate() {
		return strOrderDate;
	}

	/**
	 * 発注日(スラッシュ編集)を設定します。
	 * @param strOrderDate 発注日(スラッシュ編集)
	 */
	public void setStrOrderDate(final String strOrderDate) {
		this.strOrderDate = strOrderDate;
	}

	/**
	 * 納品希望日(スラッシュ編集)を取得します。
	 * @return String 納品希望日(スラッシュ編集)
	 */
	public String getStrSuggestedDeliverlimitDate() {
		return strSuggestedDeliverlimitDate;
	}

	/**
	 * 納品希望日(スラッシュ編集)を設定します。
	 * @param strSuggestedDeliverlimitDate 納品希望日(スラッシュ編集)
	 */
	public void setStrSuggestedDeliverlimitDate(
			final String strSuggestedDeliverlimitDate) {
		this.strSuggestedDeliverlimitDate = strSuggestedDeliverlimitDate;
	}

	/**
	 * 仕入単価取得
	 * @return String 仕入単価
	 */
	public String getStrWherehousingUnitprice() {
		return strWherehousingUnitprice;
	}

	/**
	 * 仕入単価設定
	 * @param strWherehousingUnitprice 仕入単価
	 */
	public void setStrWherehousingUnitprice(
			final String strWherehousingUnitprice) {
		this.strWherehousingUnitprice = strWherehousingUnitprice;
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
	 * 受入日付(スラッシュ編集)を取得します。
	 * @return String 受入日付(スラッシュ編集)
	 */
	public String getStrAcceptDate() {
		return strAcceptDate;
	}

	/**
	 * 受入日付(スラッシュ編集)を設定します。
	 * @param strAcceptDate 受入日付(スラッシュ編集)
	 */
	public void setStrAcceptDate(final String strAcceptDate) {
		this.strAcceptDate = strAcceptDate;
	}

	/**
	 * 発注数量(カンマ編集)を取得します。
	 * @return String 発注数量(カンマ編集)
	 */
	public String getStrOrderQuantity() {
		return strOrderQuantity;
	}

	/**
	 * 発注数量(カンマ編集)を設定します。
	 * @param strOrderQuantity 発注数量(カンマ編集)
	 */
	public void setStrOrderQuantity(final String strOrderQuantity) {
		this.strOrderQuantity = strOrderQuantity;
	}

	/**
	 * 重量(カンマ編集)を取得します。
	 * @return String 重量(カンマ編集)
	 */
	public String getStrOrderConvertQuantity() {
		return strOrderConvertQuantity;
	}

	/**
	 * 重量(カンマ編集)を設定します。
	 * @param strOrderConvertQuantity 重量(カンマ編集)
	 */
	public void setStrOrderConvertQuantity(final String strOrderConvertQuantity) {
		this.strOrderConvertQuantity = strOrderConvertQuantity;
	}

	/**
	 * 受入数量(カンマ編集)を取得します。
	 * @return String 受入数量(カンマ編集)
	 */
	public String getStrAcceptQuantity() {
		return strAcceptQuantity;
	}

	/**
	 * 受入数量(カンマ編集)を設定します。
	 * @param strAcceptQuantity 受入数量(カンマ編集)
	 */
	public void setStrAcceptQuantity(final String strAcceptQuantity) {
		this.strAcceptQuantity = strAcceptQuantity;
	}

	/**
	 * 受入重量合計(カンマ編集)を取得します。
	 * @return String 受入重量合計(カンマ編集)
	 */
	public String getStrAcceptConvertQuantitySum() {
		return strAcceptConvertQuantitySum;
	}

	/**
	 * 受入重量合計(カンマ編集)を設定します。
	 * @param strAcceptConvertQuantitySum 受入重量合計(カンマ編集)
	 */
	public void setStrAcceptConvertQuantitySum(
			final String strAcceptConvertQuantitySum) {
		this.strAcceptConvertQuantitySum = strAcceptConvertQuantitySum;
	}

	/**
	 * 増付数量(カンマ編集)を取得します。
	 * @return String 増付数量(カンマ編集)
	 */
	public String getStrIncreaseQuantity() {
		return strIncreaseQuantity;
	}

	/**
	 * 増付数量(カンマ編集)を設定します。
	 * @param strIncreaseQuantity 増付数量(カンマ編集)
	 */
	public void setStrIncreaseQuantity(final String strIncreaseQuantity) {
		this.strIncreaseQuantity = strIncreaseQuantity;
	}

	/**
	 * 仕入数量(カンマ編集)を取得します。
	 * @return String 仕入数量(カンマ編集)
	 */
	public String getStrStockingQuantity() {
		return strStockingQuantity;
	}

	/**
	 * 仕入数量(カンマ編集)を設定します。
	 * @param strStockingQuantity 仕入数量(カンマ編集)
	 */
	public void setStrStockingQuantity(final String strStockingQuantity) {
		this.strStockingQuantity = strStockingQuantity;
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
}
