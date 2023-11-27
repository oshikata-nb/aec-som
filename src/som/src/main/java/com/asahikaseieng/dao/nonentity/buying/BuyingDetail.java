/*
 * Created on 2009/01/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.buying;

import java.math.BigDecimal;

import com.asahikaseieng.app.comboboxes.SelectStockingDivision;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 仕入詳細画面_表示用データ格納クラス.
 *
 * @author tosco
 */
public class BuyingDetail extends BuyingDetailBase implements
		PropertyTransferCallbacker {
	/** serialVersion */
	private static final long serialVersionUID = 1L;

	/** 仕入日 */
	private String strStockingDate;

	/** 数量 */
	private String strStockingQuantity;

	/** 単価 */
	private String strHousingUnitprice;

	/** 金額 */
	private String strStockingAmount;

	/** 会計部門借方名称 */
	private String accountDebitSectionName;

	/** 会計部門貸方名称 */
	private String accountCreditSectionName;

	/** 借方科目名称 */
	private String debitTitleName;

	/** 貸方科目名称 */
	private String creditTitleName;

	/** 入庫ロケーション名称 */
	private String locationName;

	/** 担当者名称 */
	private String tantoNm;

	/** 品目名称 */
	private String itemMasterName;

	/** 担当部署名称 */
	private String tantoSectionName;

	/** 部署名称 */
	private String sectionName;

	/** 取引先名称(仕入先名称1) */
	private String venderName1;

	/** 取引先略称 */
	private String venderShortedName;

	/** 他社コード１ */
	private String otherCompanyCd1;

	/** 運用管理単位 */
	private String unitOfOperationManagement;

	/** 購入数量単位名称(画面表示) */
	private String stockingQuantityUnit;

	/** 単価単位区分 (個あたり、Kgあたり 金額計算で使用) */
	private String unitpriceDivision;

	/** 単価単位名称(画面表示) */
	private String housingUnitpriceUnit;

	/** スポット区分 (品目名称入力可／不可の設定) */
	private BigDecimal spotDivision;

	/** 在庫管理区分(仕入返品時の受払選択有無の判断） */
	private BigDecimal stockDivision;

	/** Kg換算係数(在庫) (金額計算で使用) */
	private String kgOfFractionManagement;

	/** 仕入区分、分類名称 */
	private String categoryName;

	/** 消費税課税区分 */
	private BigDecimal taxDivision;

	/** 取引先マスタ.算出区分 */
	private BigDecimal calcDivision;

	/** 自社マスタ.消費税算出区分 */
	private BigDecimal compCalcDivision;

	/** 自社マスタ.消費税率 */
	private BigDecimal taxRatio;

	/** 免税計算対象フラグ */
	private String reducedTaxTargetFlg;

	/** 軽減措置金額 */
	private String strInvoiceAmount;

	/** 税額控除割合 */
	private String strInvoiceTaxRatio;

	/** 軽減措置消費税金額 */
	private String strInvoiceTaxAmount;

	/**
	 * コンストラクタ.
	 */
	public BuyingDetail() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		// 取得した日付をyyyy/MM/ddに変換し、セット
		setStrStockingDate(AecDateUtils.dateFormat(getStockingDate(),
			"yyyy/MM/dd"));
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/* ---------- getter ,setter ---------- */
	/**
	 * 分類コード名称取得
	 * @return String 分類コード名称
	 */
	public String getStrCategoryDivision() {
		return SelectStockingDivision.getLabelName(getCategoryDivision()
				.toString());
	}

	/**
	 * 仕入日取得(yyyy/MM/dd)
	 * @return strStockingDate
	 */
	public String getStrStockingDate() {
		return strStockingDate;
	}

	/**
	 * 仕入日設定
	 * @param strStockingDate 仕入日
	 */
	public void setStrStockingDate(final String strStockingDate) {
		this.strStockingDate = strStockingDate;
	}

	/**
	 * 数量取得
	 * @return strStockingQuantity
	 */
	public String getStrStockingQuantity() {
		return strStockingQuantity;
	}

	/**
	 * 数量設定
	 * @param strStockingQuantity 数量
	 */
	public void setStrStockingQuantity(final String strStockingQuantity) {
		this.strStockingQuantity = strStockingQuantity;
	}

	/**
	 * 数量取得
	 * @return strStockingQuantity
	 */
	public String getStrStockingAmount() {
		return strStockingAmount;
	}

	/**
	 * 単価設定
	 * @param strStockingAmount 単価
	 */
	public void setStrStockingAmount(final String strStockingAmount) {
		this.strStockingAmount = strStockingAmount;
	}

	/**
	 * 金額取得
	 * @return strHousingUnitprice
	 */
	public String getStrHousingUnitprice() {
		return strHousingUnitprice;
	}

	/**
	 * 金額設定
	 * @param strHousingUnitprice 金額
	 */
	public void setStrHousingUnitprice(final String strHousingUnitprice) {
		this.strHousingUnitprice = strHousingUnitprice;
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
	 * 取引先名称取得(仕入先名称)
	 * @return venderName1 取引先名称
	 */
	public String getVenderName1() {
		return venderName1;
	}

	/**
	 * 取引先名称設定(仕入先名称)
	 * @param venderName1 取引先名称
	 */
	public void setVenderName1(final String venderName1) {
		this.venderName1 = venderName1;
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
	 * 担当者名称取得
	 * @return tantoNm 担当者名称
	 */
	public String getTantoNm() {
		return tantoNm;
	}

	/**
	 * 担当者名称設定
	 * @param tantoNm 担当者名称
	 */
	public void setTantoNm(final String tantoNm) {
		this.tantoNm = tantoNm;
	}

	/**
	 * 入庫ロケーション名称取得
	 * @return locationName 入庫ロケーション名称
	 */
	public String getLocationName() {
		return locationName;
	}

	/**
	 * 入庫ロケーション名称設定
	 * @param locationName 入庫ロケーション名称
	 */
	public void setLocationName(final String locationName) {
		this.locationName = locationName;
	}

	/**
	 * 品目名称取得
	 * @return itemMasterName 品目名称
	 */
	public String getItemMasterName() {
		return itemMasterName;
	}

	/**
	 * 品目名称設定
	 * @param itemMasterName 品目名称
	 */
	public void setItemMasterName(final String itemMasterName) {
		this.itemMasterName = itemMasterName;
	}

	/**
	 * 他社コード１取得
	 * @return String 他社コード１
	 */
	public String getOtherCompanyCd1() {
		return this.otherCompanyCd1;
	}

	/**
	 * 他社コード１.設定
	 * @param otherCompanyCd1 他社コード１
	 */
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = otherCompanyCd1;
	}

	/**
	 * 運用管理単位取得
	 * @return unitOfOperationManagement
	 */
	public String getUnitOfOperationManagement() {
		return unitOfOperationManagement;
	}

	/**
	 * 運用管理単位設定
	 * @param unitOfOperationManagement 運用管理単位
	 */
	public void setUnitOfOperationManagement(
			final String unitOfOperationManagement) {
		this.unitOfOperationManagement = unitOfOperationManagement;
	}

	/**
	 * 購入数量の単位名称取得(画面表示)
	 * @return stockingQuantityUnit
	 */
	public String getStockingQuantityUnit() {
		return stockingQuantityUnit;
	}

	/**
	 * 購入数量の単位名称設定(画面表示)
	 * @param stockingQuantityUnit 購入数量の単位名称
	 */
	public void setStockingQuantityUnit(final String stockingQuantityUnit) {
		this.stockingQuantityUnit = stockingQuantityUnit;
	}

	/**
	 * 単価単位区分取得
	 * @return unitpriceDivision
	 */
	public String getUnitpriceDivision() {
		return unitpriceDivision;
	}

	/**
	 * 単価単位区分設定
	 * @param unitpriceDivision 単価単位区分
	 */
	public void setUnitpriceDivision(final String unitpriceDivision) {
		this.unitpriceDivision = unitpriceDivision;
	}

	/**
	 * 単価の単位名称取得(画面表示)
	 * @return housingUnitpriceUnit
	 */
	public String getHousingUnitpriceUnit() {
		return housingUnitpriceUnit;
	}

	/**
	 * 単価の単位名称設定(画面表示)
	 * @param housingUnitpriceUnit 単価の単位名称
	 */
	public void setHousingUnitpriceUnit(final String housingUnitpriceUnit) {
		this.housingUnitpriceUnit = housingUnitpriceUnit;
	}

	/**
	 * 部署名称取得
	 * @return sectionName
	 */
	public String getSectionName() {
		return sectionName;
	}

	/**
	 * 部署名称設定
	 * @param sectionName 部署名称
	 */
	public void setSectionName(final String sectionName) {
		this.sectionName = sectionName;
	}

	/**
	 * 担当部署名称取得
	 * @return tantoSectionName
	 */
	public String getTantoSectionName() {
		return tantoSectionName;
	}

	/**
	 * 担当部署名称設定
	 * @param tantoSectionName 担当部署名称
	 */
	public void setTantoSectionName(final String tantoSectionName) {
		this.tantoSectionName = tantoSectionName;
	}

	/**
	 * スポット区分を取得します。
	 * @return spotDivision
	 */
	public BigDecimal getSpotDivision() {
		return spotDivision;
	}

	/**
	 * スポット区分を設定します。
	 * @param spotDivision スポット区分
	 */
	public void setSpotDivision(final BigDecimal spotDivision) {
		this.spotDivision = spotDivision;
	}

	/**
	 * Kg換算係数(在庫)を取得します。
	 * @return kgOfFractionManagement
	 */
	public String getKgOfFractionManagement() {
		return kgOfFractionManagement;
	}

	/**
	 * Kg換算係数(在庫)を設定します。
	 * @param kgOfFractionManagement Kg換算係数(在庫)
	 */
	public void setKgOfFractionManagement(final String kgOfFractionManagement) {
		this.kgOfFractionManagement = kgOfFractionManagement;
	}

	/**
	 * 仕入区分、分類名称を取得します。
	 * @return categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * 仕入区分、分類名称を設定します。
	 * @param categoryName 仕入区分、分類名称
	 */
	public void setCategoryName(final String categoryName) {
		this.categoryName = categoryName;
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
	 * stockDivision
	 * @return stockDivision stockDivision
	 */
	public BigDecimal getStockDivision() {
		return stockDivision;
	}

	/**
	 * stockDivision
	 * @param stockDivision stockDivision
	 */
	public void setStockDivision(final BigDecimal stockDivision) {
		this.stockDivision = stockDivision;
	}

	public String getReducedTaxTargetFlg() {
		return reducedTaxTargetFlg;
	}

	public void setReducedTaxTargetFlg(String reducedTaxTargetFlg) {
		this.reducedTaxTargetFlg = reducedTaxTargetFlg;
	}

	public String getStrInvoiceAmount() {
		return strInvoiceAmount;
	}

	public void setStrInvoiceAmount(String strInvoiceAmount) {
		this.strInvoiceAmount = strInvoiceAmount;
	}

	public String getStrInvoiceTaxRatio() {
		return strInvoiceTaxRatio;
	}

	public void setStrInvoiceTaxRatio(String strInvoiceTaxRatio) {
		this.strInvoiceTaxRatio = strInvoiceTaxRatio;
	}

	public String getStrInvoiceTaxAmount() {
		return strInvoiceTaxAmount;
	}

	public void setStrInvoiceTaxAmount(String strInvoiceTaxAmount) {
		this.strInvoiceTaxAmount = strInvoiceTaxAmount;
	}

}
