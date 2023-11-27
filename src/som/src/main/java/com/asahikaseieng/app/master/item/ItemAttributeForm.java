/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.item;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.struts.AbstractForm;

/**
 * 品目在庫・単価 Formクラス.
 * @author t0011036
 */
public final class ItemAttributeForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/* 品目コード */
	private String headItemCd;

	private String itemCd;

	/* バージョン */
	private BigDecimal headVersion;

	private BigDecimal version;

	/* 品目名称 */
	private String headDispItemName;

	private String headItemName;

	private String itemName;

	/* 有効日時 */
	private Timestamp headActiveDate;

	private String strHeadActiveDate;

	private Timestamp activeDate;

	private String strActiveDate;

	/* ステータス */
	private BigDecimal status;

	private BigDecimal headerStatus;

	private BigDecimal productStatus;

	private BigDecimal articleStatus;

	private BigDecimal purchaseStatus;

	/* 有効 */
	private String headActivate;

	/* ステータス名称 */
	private String headDetailStatusName;

	private String headAttributeStatusName;

	/* 使用期限 */
	private java.math.BigDecimal expireMonths;

	private String strExpireMonths;

	/* 取引可能期限 */
	private java.math.BigDecimal contractMonths;

	private String strContractMonths;

	/* 発注情報 */
	private String orderInfo;

	/* 備考 */
	private String remarks;

	/* 適用法令 */
	private String applicationLaw;

	/* 更新日時 */
	private java.sql.Timestamp commonUpdateDate;

	/* 指図書パターン */
	private java.math.BigDecimal orderPattern;

	private String strOrderPattern;

	/* 生産工場№ */
	private String productionLine;

	/* 生産工場名称 */
	private String productionLineName;

	/* 検査時間 */
	private BigDecimal inspectionDays;

	private String strInspectionDays;

	/* 発注点 */
	private java.math.BigDecimal productOrderPoint;

	private String strProductOrderPoint;

	/* 原価部門コード */
	private String productSectionCd;

	/* 原価部門名称 */
	private String productSectionName;

	/* 更新日時 */
	private java.sql.Timestamp productUpdateDate;

	/* 単価決定単位区分 */
	private BigDecimal articlePriceCalcDivision;

	/* 標準販売単価 */
	private java.math.BigDecimal sellingPrice;

	private String strSellingPrice;

	/* パレット上製品数 */
	private BigDecimal paletteProducts;

	private String strPaletteProducts;

	/* JANコード */
	private String janCd;

	/* ITFコード */
	private String itfCd;

	/* リードタイム */
	private java.math.BigDecimal safetyLeadTime;

	private String strSafetyLeadTime;

	/* 売上会計部門コード */
	private String articleSectionCd;

	/* 売上会計部門名称 */
	private String articleSectionName;

	/* 売上勘定科目コード */
	private String articleAccountsCd;

	/* 売上勘定科目名称 */
	private String articleAccountsName;

	/* 売上消費税課税区分 */
	private java.math.BigDecimal articleTaxDivision;

	/* 財務分類コード */
	private String financialClassCd;

	/* 財務分類名称 */
	private String financialClassName;

	/* 預かり品区分 */
	private java.math.BigDecimal keepDivision;

	/* 更新日時 */
	private java.sql.Timestamp articleUpdateDate;

	/* メーカー名 */
	private String materialMakerName;

	/* 基準仕入先コード */
	private String venderCd;

	/* 取引先コード */
	private String venderDivision;

	/* 基準仕入先名称 */
	private String venderName1;

	/* 納入先コード */
	private String deliveryCd;

	/* 納入先名称 */
	private String deliveryName1;

	/* リードタイム */
	private java.math.BigDecimal purchaseLeadTime;

	private String strPurchaseLeadTime;

	/* 標準仕入単価 */
	private java.math.BigDecimal purchasePrice;

	private String strPurchasePrice;

	/* 購買消費税課税区分 */
	private java.math.BigDecimal purchaseTaxDivision;

	/* 発注基準 */
	private java.math.BigDecimal purchaseTrigger;

	/* 複数社発注区分 */
	private java.math.BigDecimal multiSupplierDivision;

	/* 発注点 */
	private java.math.BigDecimal purchaseOrderPoint;

	private String strPurchaseOrderPoint;

	/* 標準発注数 */
	private java.math.BigDecimal orderQty;

	private String strOrderQty;

	/* 基準検査方法 */
	private java.math.BigDecimal inspectionType;

	/* 単価決定単位区分 */
	private java.math.BigDecimal purchasePriceCalcDivision;

	/* 支給品区分 */
	private java.math.BigDecimal suppliedGoodsDivision;

	/* リースドラムフラグ */
	private java.math.BigDecimal leaseDrumFlag;

	/* ローリー区分 */
	private java.math.BigDecimal lorryDivision;

	/* 仕入会計部門コード */
	private String purchaseSectionCd;

	/* 仕入会計部門名称 */
	private String purchaseSectionName;

	/* 仕入勘定科目コード */
	private String purchaseAccountsCd;

	/* 仕入勘定科目名称 */
	private String purchaseAccountsName;

	/* 更新日時 */
	private java.sql.Timestamp purchaseUpdateDate;

	/* 数値区分 */
	private String unitDivisionOther;

	private String unitDivisionUrtanka;

	private String unitDivisionSitanka;

	/* 製造品区分 */
	private java.math.BigDecimal productDivision;

	/* 販売品区分 */
	private java.math.BigDecimal articleDivision;

	/* 購入品区分 */
	private java.math.BigDecimal purchaseDivision;

	/* 在庫管理単位 */
	private String unitOfStockControl;

	/* 更新理由 */
	private String reason;

	/* 更新フラグ */
	private String registFlg;

	/* 変更フラグ */
	private Boolean dirtyFlg;

	/* 見積変更開始基準日 */
	private String strInputDate;

	private Timestamp inputDate;

	/* 見積変更開始基準日決定フラグ */
	private String inputFlg;

	/**
	 * コンストラクタ.
	 */
	public ItemAttributeForm() {
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {
		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			/* クリア処理 */
			clear();
		}
	}

	/**
	 * {@inheritDoc}
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
	public void clear() {
		setHeadItemCd(null);
		setItemCd(null);
		setHeadVersion(new BigDecimal("0"));
		setVersion(new BigDecimal("0"));
		setHeadDispItemName(null);
		setItemName(null);
		setHeadActiveDate(null);
		setStrHeadActiveDate(null);
		setActiveDate(null);
		setStrActiveDate(null);
		setStatus(new BigDecimal("0"));
		setHeaderStatus(new BigDecimal("0"));
		setProductStatus(new BigDecimal("0"));
		setArticleStatus(new BigDecimal("0"));
		setPurchaseStatus(new BigDecimal("0"));
		setHeadActivate(null);
		setHeadDetailStatusName(null);
		setHeadAttributeStatusName(null);
		setExpireMonths(new BigDecimal("0"));
		setStrExpireMonths(null);
		setContractMonths(new BigDecimal("0"));
		setStrContractMonths(null);
		setOrderInfo(null);
		setRemarks(null);
		setApplicationLaw(null);
		setCommonUpdateDate(null);
		setOrderPattern(new BigDecimal("0"));
		setStrOrderPattern(null);
		setProductionLine(null);
		setProductionLineName(null);
		setInspectionDays(new BigDecimal("0"));
		setStrInspectionDays(null);
		setProductOrderPoint(new BigDecimal("0"));
		setStrProductOrderPoint(null);
		setProductSectionCd(null);
		setProductSectionName(null);
		setProductUpdateDate(null);
		setArticlePriceCalcDivision(null);
		setSellingPrice(new BigDecimal("0"));
		setStrSellingPrice(null);
		setPaletteProducts(new BigDecimal("0"));
		setStrPaletteProducts(null);
		setJanCd(null);
		setItfCd(null);
		setSafetyLeadTime(new BigDecimal("0"));
		setStrSafetyLeadTime(null);
		setArticleSectionCd(null);
		setArticleSectionName(null);
		setArticleAccountsCd(null);
		setArticleAccountsName(null);
		setArticleTaxDivision(null);
		setFinancialClassCd(null);
		setFinancialClassName(null);
		setKeepDivision(null);
		setArticleUpdateDate(null);
		setMaterialMakerName(null);
		setVenderCd(null);
		setVenderDivision("SI");
		setVenderName1(null);
		setDeliveryCd(null);
		setDeliveryName1(null);
		setPurchaseLeadTime(new BigDecimal("0"));
		setStrPurchaseLeadTime(null);
		setPurchasePrice(new BigDecimal("0"));
		setStrPurchasePrice(null);
		setPurchaseTaxDivision(new BigDecimal("0"));
		setPurchaseTrigger(new BigDecimal("0"));
		setMultiSupplierDivision(new BigDecimal("0"));
		setPurchaseOrderPoint(new BigDecimal("0"));
		setStrPurchaseOrderPoint(null);
		setOrderQty(new BigDecimal("0"));
		setStrOrderQty(null);
		setInspectionType(new BigDecimal("0"));
		setPurchasePriceCalcDivision(new BigDecimal("0"));
		setSuppliedGoodsDivision(new BigDecimal("0"));
		setLeaseDrumFlag(new BigDecimal("0"));
		setLorryDivision(new BigDecimal("0"));
		setPurchaseSectionCd(null);
		setPurchaseSectionName(null);
		setPurchaseAccountsCd(null);
		setPurchaseAccountsName(null);
		setPurchaseUpdateDate(null);
		setUnitDivisionOther("SONOTA");
		setUnitDivisionUrtanka("URTANKA");
		setUnitDivisionSitanka("SITANKA");
		setProductDivision(new BigDecimal("0"));
		setArticleDivision(new BigDecimal("0"));
		setPurchaseDivision(new BigDecimal("0"));
		setUnitOfStockControl(null);
		setHeadItemName(null);
		setReason(null);
		setRegistFlg(null);
		setDirtyFlg(null);
		setStrInputDate(null);
		setInputDate(null);
		setInputFlg(null);
	}

	/**
	 * activeDateを取得します。
	 * @return activeDate
	 */
	public Timestamp getActiveDate() {
		return activeDate;
	}

	/**
	 * activeDateを設定します。
	 * @param activeDate activeDate
	 */
	public void setActiveDate(final Timestamp activeDate) {
		this.activeDate = activeDate;
	}

	/**
	 * applicationLawを取得します。
	 * @return applicationLaw
	 */
	public String getApplicationLaw() {
		return applicationLaw;
	}

	/**
	 * applicationLawを設定します。
	 * @param applicationLaw applicationLaw
	 */
	public void setApplicationLaw(final String applicationLaw) {
		this.applicationLaw = applicationLaw;
	}

	/**
	 * articleAccountsCdを取得します。
	 * @return articleAccountsCd
	 */
	public String getArticleAccountsCd() {
		return articleAccountsCd;
	}

	/**
	 * articleAccountsCdを設定します。
	 * @param articleAccountsCd articleAccountsCd
	 */
	public void setArticleAccountsCd(final String articleAccountsCd) {
		this.articleAccountsCd = articleAccountsCd;
	}

	/**
	 * articleAccountsNameを取得します。
	 * @return articleAccountsName
	 */
	public String getArticleAccountsName() {
		return articleAccountsName;
	}

	/**
	 * articleAccountsNameを設定します。
	 * @param articleAccountsName articleAccountsName
	 */
	public void setArticleAccountsName(final String articleAccountsName) {
		this.articleAccountsName = articleAccountsName;
	}

	/**
	 * articleDivisionを取得します。
	 * @return articleDivision
	 */
	public java.math.BigDecimal getArticleDivision() {
		return articleDivision;
	}

	/**
	 * articleDivisionを設定します。
	 * @param articleDivision articleDivision
	 */
	public void setArticleDivision(final java.math.BigDecimal articleDivision) {
		this.articleDivision = articleDivision;
	}

	/**
	 * articleSectionCdを取得します。
	 * @return articleSectionCd
	 */
	public String getArticleSectionCd() {
		return articleSectionCd;
	}

	/**
	 * articleSectionCdを設定します。
	 * @param articleSectionCd articleSectionCd
	 */
	public void setArticleSectionCd(final String articleSectionCd) {
		this.articleSectionCd = articleSectionCd;
	}

	/**
	 * articleSectionNameを取得します。
	 * @return articleSectionName
	 */
	public String getArticleSectionName() {
		return articleSectionName;
	}

	/**
	 * articleSectionNameを設定します。
	 * @param articleSectionName articleSectionName
	 */
	public void setArticleSectionName(final String articleSectionName) {
		this.articleSectionName = articleSectionName;
	}

	/**
	 * articleTaxDivisionを取得します。
	 * @return articleTaxDivision
	 */
	public java.math.BigDecimal getArticleTaxDivision() {
		return articleTaxDivision;
	}

	/**
	 * articleTaxDivisionを設定します。
	 * @param articleTaxDivision articleTaxDivision
	 */
	public void setArticleTaxDivision(
			final java.math.BigDecimal articleTaxDivision) {
		this.articleTaxDivision = articleTaxDivision;
	}

	/**
	 * articleUpdateDateを取得します。
	 * @return articleUpdateDate
	 */
	public java.sql.Timestamp getArticleUpdateDate() {
		return articleUpdateDate;
	}

	/**
	 * articleUpdateDateを設定します。
	 * @param articleUpdateDate articleUpdateDate
	 */
	public void setArticleUpdateDate(final java.sql.Timestamp articleUpdateDate) {
		this.articleUpdateDate = articleUpdateDate;
	}

	/**
	 * commonUpdateDateを取得します。
	 * @return commonUpdateDate
	 */
	public java.sql.Timestamp getCommonUpdateDate() {
		return commonUpdateDate;
	}

	/**
	 * commonUpdateDateを設定します。
	 * @param commonUpdateDate commonUpdateDate
	 */
	public void setCommonUpdateDate(final java.sql.Timestamp commonUpdateDate) {
		this.commonUpdateDate = commonUpdateDate;
	}

	/**
	 * contractMonthsを取得します。
	 * @return contractMonths
	 */
	public java.math.BigDecimal getContractMonths() {
		return contractMonths;
	}

	/**
	 * contractMonthsを設定します。
	 * @param contractMonths contractMonths
	 */
	public void setContractMonths(final java.math.BigDecimal contractMonths) {
		this.contractMonths = contractMonths;
	}

	/**
	 * deliveryCdを取得します。
	 * @return deliveryCd
	 */
	public String getDeliveryCd() {
		return deliveryCd;
	}

	/**
	 * deliveryCdを設定します。
	 * @param deliveryCd deliveryCd
	 */
	public void setDeliveryCd(final String deliveryCd) {
		this.deliveryCd = deliveryCd;
	}

	/**
	 * dirtyFlgを取得します。
	 * @return dirtyFlg
	 */
	public Boolean getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * dirtyFlgを設定します。
	 * @param dirtyFlg dirtyFlg
	 */
	public void setDirtyFlg(final Boolean dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
	}

	/**
	 * expireMonthsを取得します。
	 * @return expireMonths
	 */
	public java.math.BigDecimal getExpireMonths() {
		return expireMonths;
	}

	/**
	 * expireMonthsを設定します。
	 * @param expireMonths expireMonths
	 */
	public void setExpireMonths(final java.math.BigDecimal expireMonths) {
		this.expireMonths = expireMonths;
	}

	/**
	 * financialClassCdを取得します。
	 * @return financialClassCd
	 */
	public String getFinancialClassCd() {
		return financialClassCd;
	}

	/**
	 * financialClassCdを設定します。
	 * @param financialClassCd financialClassCd
	 */
	public void setFinancialClassCd(final String financialClassCd) {
		this.financialClassCd = financialClassCd;
	}

	/**
	 * financialClassNameを取得します。
	 * @return financialClassName
	 */
	public String getFinancialClassName() {
		return financialClassName;
	}

	/**
	 * financialClassNameを設定します。
	 * @param financialClassName financialClassName
	 */
	public void setFinancialClassName(final String financialClassName) {
		this.financialClassName = financialClassName;
	}

	/**
	 * inspectionDaysを取得します。
	 * @return inspectionDays
	 */
	public BigDecimal getInspectionDays() {
		return inspectionDays;
	}

	/**
	 * inspectionDaysを設定します。
	 * @param inspectionDays inspectionDays
	 */
	public void setInspectionDays(final BigDecimal inspectionDays) {
		this.inspectionDays = inspectionDays;
	}

	/**
	 * inspectionTypeを取得します。
	 * @return inspectionType
	 */
	public java.math.BigDecimal getInspectionType() {
		return inspectionType;
	}

	/**
	 * inspectionTypeを設定します。
	 * @param inspectionType inspectionType
	 */
	public void setInspectionType(final java.math.BigDecimal inspectionType) {
		this.inspectionType = inspectionType;
	}

	/**
	 * itemCdを取得します。
	 * @return itemCd
	 */
	public String getItemCd() {
		return itemCd;
	}

	/**
	 * itemCdを設定します。
	 * @param itemCd itemCd
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * itemNameを取得します。
	 * @return itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * itemNameを設定します。
	 * @param itemName itemName
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * itfCdを取得します。
	 * @return itfCd
	 */
	public String getItfCd() {
		return itfCd;
	}

	/**
	 * itfCdを設定します。
	 * @param itfCd itfCd
	 */
	public void setItfCd(final String itfCd) {
		this.itfCd = itfCd;
	}

	/**
	 * janCdを取得します。
	 * @return janCd
	 */
	public String getJanCd() {
		return janCd;
	}

	/**
	 * janCdを設定します。
	 * @param janCd janCd
	 */
	public void setJanCd(final String janCd) {
		this.janCd = janCd;
	}

	/**
	 * keepDivisionを取得します。
	 * @return keepDivision
	 */
	public java.math.BigDecimal getKeepDivision() {
		return keepDivision;
	}

	/**
	 * keepDivisionを設定します。
	 * @param keepDivision keepDivision
	 */
	public void setKeepDivision(final java.math.BigDecimal keepDivision) {
		this.keepDivision = keepDivision;
	}

	/**
	 * leaseDrumFlagを取得します。
	 * @return leaseDrumFlag
	 */
	public java.math.BigDecimal getLeaseDrumFlag() {
		return leaseDrumFlag;
	}

	/**
	 * leaseDrumFlagを設定します。
	 * @param leaseDrumFlag leaseDrumFlag
	 */
	public void setLeaseDrumFlag(final java.math.BigDecimal leaseDrumFlag) {
		this.leaseDrumFlag = leaseDrumFlag;
	}

	/**
	 * lorryDivisionを取得します。
	 * @return lorryDivision
	 */
	public java.math.BigDecimal getLorryDivision() {
		return lorryDivision;
	}

	/**
	 * lorryDivisionを設定します。
	 * @param lorryDivision lorryDivision
	 */
	public void setLorryDivision(final java.math.BigDecimal lorryDivision) {
		this.lorryDivision = lorryDivision;
	}

	/**
	 * materialMakerNameを取得します。
	 * @return materialMakerName
	 */
	public String getMaterialMakerName() {
		return materialMakerName;
	}

	/**
	 * materialMakerNameを設定します。
	 * @param materialMakerName materialMakerName
	 */
	public void setMaterialMakerName(final String materialMakerName) {
		this.materialMakerName = materialMakerName;
	}

	/**
	 * multiSupplierDivisionを取得します。
	 * @return multiSupplierDivision
	 */
	public java.math.BigDecimal getMultiSupplierDivision() {
		return multiSupplierDivision;
	}

	/**
	 * multiSupplierDivisionを設定します。
	 * @param multiSupplierDivision multiSupplierDivision
	 */
	public void setMultiSupplierDivision(
			final java.math.BigDecimal multiSupplierDivision) {
		this.multiSupplierDivision = multiSupplierDivision;
	}

	/**
	 * orderInfoを取得します。
	 * @return orderInfo
	 */
	public String getOrderInfo() {
		return orderInfo;
	}

	/**
	 * orderInfoを設定します。
	 * @param orderInfo orderInfo
	 */
	public void setOrderInfo(final String orderInfo) {
		this.orderInfo = orderInfo;
	}

	/**
	 * orderPatternを取得します。
	 * @return orderPattern
	 */
	public java.math.BigDecimal getOrderPattern() {
		return orderPattern;
	}

	/**
	 * orderPatternを設定します。
	 * @param orderPattern orderPattern
	 */
	public void setOrderPattern(final java.math.BigDecimal orderPattern) {
		this.orderPattern = orderPattern;
	}

	/**
	 * orderQtyを取得します。
	 * @return orderQty
	 */
	public java.math.BigDecimal getOrderQty() {
		return orderQty;
	}

	/**
	 * orderQtyを設定します。
	 * @param orderQty orderQty
	 */
	public void setOrderQty(final java.math.BigDecimal orderQty) {
		this.orderQty = orderQty;
	}

	/**
	 * paletteProductsを取得します。
	 * @return paletteProducts
	 */
	public BigDecimal getPaletteProducts() {
		return paletteProducts;
	}

	/**
	 * paletteProductsを設定します。
	 * @param paletteProducts paletteProducts
	 */
	public void setPaletteProducts(final BigDecimal paletteProducts) {
		this.paletteProducts = paletteProducts;
	}

	/**
	 * productDivisionを取得します。
	 * @return productDivision
	 */
	public java.math.BigDecimal getProductDivision() {
		return productDivision;
	}

	/**
	 * productDivisionを設定します。
	 * @param productDivision productDivision
	 */
	public void setProductDivision(final java.math.BigDecimal productDivision) {
		this.productDivision = productDivision;
	}

	/**
	 * productionLineを取得します。
	 * @return productionLine
	 */
	public String getProductionLine() {
		return productionLine;
	}

	/**
	 * productionLineを設定します。
	 * @param productionLine productionLine
	 */
	public void setProductionLine(final String productionLine) {
		this.productionLine = productionLine;
	}

	/**
	 * productionLineNameを取得します。
	 * @return productionLineName
	 */
	public String getProductionLineName() {
		return productionLineName;
	}

	/**
	 * productionLineNameを設定します。
	 * @param productionLineName productionLineName
	 */
	public void setProductionLineName(final String productionLineName) {
		this.productionLineName = productionLineName;
	}

	/**
	 * productOrderPointを取得します。
	 * @return productOrderPoint
	 */
	public java.math.BigDecimal getProductOrderPoint() {
		return productOrderPoint;
	}

	/**
	 * productOrderPointを設定します。
	 * @param productOrderPoint productOrderPoint
	 */
	public void setProductOrderPoint(
			final java.math.BigDecimal productOrderPoint) {
		this.productOrderPoint = productOrderPoint;
	}

	/**
	 * productUpdateDateを取得します。
	 * @return productUpdateDate
	 */
	public java.sql.Timestamp getProductUpdateDate() {
		return productUpdateDate;
	}

	/**
	 * productUpdateDateを設定します。
	 * @param productUpdateDate productUpdateDate
	 */
	public void setProductUpdateDate(final java.sql.Timestamp productUpdateDate) {
		this.productUpdateDate = productUpdateDate;
	}

	/**
	 * purchaseAccountsCdを取得します。
	 * @return purchaseAccountsCd
	 */
	public String getPurchaseAccountsCd() {
		return purchaseAccountsCd;
	}

	/**
	 * purchaseAccountsCdを設定します。
	 * @param purchaseAccountsCd purchaseAccountsCd
	 */
	public void setPurchaseAccountsCd(final String purchaseAccountsCd) {
		this.purchaseAccountsCd = purchaseAccountsCd;
	}

	/**
	 * purchaseAccountsNameを取得します。
	 * @return purchaseAccountsName
	 */
	public String getPurchaseAccountsName() {
		return purchaseAccountsName;
	}

	/**
	 * purchaseAccountsNameを設定します。
	 * @param purchaseAccountsName purchaseAccountsName
	 */
	public void setPurchaseAccountsName(final String purchaseAccountsName) {
		this.purchaseAccountsName = purchaseAccountsName;
	}

	/**
	 * purchaseDivisionを取得します。
	 * @return purchaseDivision
	 */
	public java.math.BigDecimal getPurchaseDivision() {
		return purchaseDivision;
	}

	/**
	 * purchaseDivisionを設定します。
	 * @param purchaseDivision purchaseDivision
	 */
	public void setPurchaseDivision(final java.math.BigDecimal purchaseDivision) {
		this.purchaseDivision = purchaseDivision;
	}

	/**
	 * purchaseLeadTimeを取得します。
	 * @return purchaseLeadTime
	 */
	public java.math.BigDecimal getPurchaseLeadTime() {
		return purchaseLeadTime;
	}

	/**
	 * purchaseLeadTimeを設定します。
	 * @param purchaseLeadTime purchaseLeadTime
	 */
	public void setPurchaseLeadTime(final java.math.BigDecimal purchaseLeadTime) {
		this.purchaseLeadTime = purchaseLeadTime;
	}

	/**
	 * purchaseOrderPointを取得します。
	 * @return purchaseOrderPoint
	 */
	public java.math.BigDecimal getPurchaseOrderPoint() {
		return purchaseOrderPoint;
	}

	/**
	 * purchaseOrderPointを設定します。
	 * @param purchaseOrderPoint purchaseOrderPoint
	 */
	public void setPurchaseOrderPoint(
			final java.math.BigDecimal purchaseOrderPoint) {
		this.purchaseOrderPoint = purchaseOrderPoint;
	}

	/**
	 * purchasePriceを取得します。
	 * @return purchasePrice
	 */
	public java.math.BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	/**
	 * purchasePriceを設定します。
	 * @param purchasePrice purchasePrice
	 */
	public void setPurchasePrice(final java.math.BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	/**
	 * purchasePriceCalcDivisionを取得します。
	 * @return purchasePriceCalcDivision
	 */
	public java.math.BigDecimal getPurchasePriceCalcDivision() {
		return purchasePriceCalcDivision;
	}

	/**
	 * purchasePriceCalcDivisionを設定します。
	 * @param purchasePriceCalcDivision purchasePriceCalcDivision
	 */
	public void setPurchasePriceCalcDivision(
			final java.math.BigDecimal purchasePriceCalcDivision) {
		this.purchasePriceCalcDivision = purchasePriceCalcDivision;
	}

	/**
	 * purchaseSectionCdを取得します。
	 * @return purchaseSectionCd
	 */
	public String getPurchaseSectionCd() {
		return purchaseSectionCd;
	}

	/**
	 * purchaseSectionCdを設定します。
	 * @param purchaseSectionCd purchaseSectionCd
	 */
	public void setPurchaseSectionCd(final String purchaseSectionCd) {
		this.purchaseSectionCd = purchaseSectionCd;
	}

	/**
	 * purchaseSectionNameを取得します。
	 * @return purchaseSectionName
	 */
	public String getPurchaseSectionName() {
		return purchaseSectionName;
	}

	/**
	 * purchaseSectionNameを設定します。
	 * @param purchaseSectionName purchaseSectionName
	 */
	public void setPurchaseSectionName(final String purchaseSectionName) {
		this.purchaseSectionName = purchaseSectionName;
	}

	/**
	 * purchaseTaxDivisionを取得します。
	 * @return purchaseTaxDivision
	 */
	public java.math.BigDecimal getPurchaseTaxDivision() {
		return purchaseTaxDivision;
	}

	/**
	 * purchaseTaxDivisionを設定します。
	 * @param purchaseTaxDivision purchaseTaxDivision
	 */
	public void setPurchaseTaxDivision(
			final java.math.BigDecimal purchaseTaxDivision) {
		this.purchaseTaxDivision = purchaseTaxDivision;
	}

	/**
	 * purchaseTriggerを取得します。
	 * @return purchaseTrigger
	 */
	public java.math.BigDecimal getPurchaseTrigger() {
		return purchaseTrigger;
	}

	/**
	 * purchaseTriggerを設定します。
	 * @param purchaseTrigger purchaseTrigger
	 */
	public void setPurchaseTrigger(final java.math.BigDecimal purchaseTrigger) {
		this.purchaseTrigger = purchaseTrigger;
	}

	/**
	 * purchaseUpdateDateを取得します。
	 * @return purchaseUpdateDate
	 */
	public java.sql.Timestamp getPurchaseUpdateDate() {
		return purchaseUpdateDate;
	}

	/**
	 * purchaseUpdateDateを設定します。
	 * @param purchaseUpdateDate purchaseUpdateDate
	 */
	public void setPurchaseUpdateDate(
			final java.sql.Timestamp purchaseUpdateDate) {
		this.purchaseUpdateDate = purchaseUpdateDate;
	}

	/**
	 * remarksを取得します。
	 * @return remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * remarksを設定します。
	 * @param remarks remarks
	 */
	public void setRemarks(final String remarks) {
		this.remarks = remarks;
	}

	/**
	 * safetyLeadTimeを取得します。
	 * @return safetyLeadTime
	 */
	public java.math.BigDecimal getSafetyLeadTime() {
		return safetyLeadTime;
	}

	/**
	 * safetyLeadTimeを設定します。
	 * @param safetyLeadTime safetyLeadTime
	 */
	public void setSafetyLeadTime(final java.math.BigDecimal safetyLeadTime) {
		this.safetyLeadTime = safetyLeadTime;
	}

	/**
	 * sellingPriceを取得します。
	 * @return sellingPrice
	 */
	public java.math.BigDecimal getSellingPrice() {
		return sellingPrice;
	}

	/**
	 * sellingPriceを設定します。
	 * @param sellingPrice sellingPrice
	 */
	public void setSellingPrice(final java.math.BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	/**
	 * statusを取得します。
	 * @return status
	 */
	public BigDecimal getStatus() {
		return status;
	}

	/**
	 * statusを設定します。
	 * @param status status
	 */
	public void setStatus(final BigDecimal status) {
		this.status = status;
	}

	/**
	 * strActiveDateを取得します。
	 * @return strActiveDate
	 */
	public String getStrActiveDate() {
		return strActiveDate;
	}

	/**
	 * strActiveDateを設定します。
	 * @param strActiveDate strActiveDate
	 */
	public void setStrActiveDate(final String strActiveDate) {
		this.strActiveDate = strActiveDate;
	}

	/**
	 * strContractMonthsを取得します。
	 * @return strContractMonths
	 */
	public String getStrContractMonths() {
		return strContractMonths;
	}

	/**
	 * strContractMonthsを設定します。
	 * @param strContractMonths strContractMonths
	 */
	public void setStrContractMonths(final String strContractMonths) {
		this.strContractMonths = strContractMonths;
	}

	/**
	 * strExpireMonthsを取得します。
	 * @return strExpireMonths
	 */
	public String getStrExpireMonths() {
		return strExpireMonths;
	}

	/**
	 * strExpireMonthsを設定します。
	 * @param strExpireMonths strExpireMonths
	 */
	public void setStrExpireMonths(final String strExpireMonths) {
		this.strExpireMonths = strExpireMonths;
	}

	/**
	 * strInspectionDaysを取得します。
	 * @return strInspectionDays
	 */
	public String getStrInspectionDays() {
		return strInspectionDays;
	}

	/**
	 * strInspectionDaysを設定します。
	 * @param strInspectionDays strInspectionDays
	 */
	public void setStrInspectionDays(final String strInspectionDays) {
		this.strInspectionDays = strInspectionDays;
	}

	/**
	 * strOrderPatternを取得します。
	 * @return strOrderPattern
	 */
	public String getStrOrderPattern() {
		return strOrderPattern;
	}

	/**
	 * strOrderPatternを設定します。
	 * @param strOrderPattern strOrderPattern
	 */
	public void setStrOrderPattern(final String strOrderPattern) {
		this.strOrderPattern = strOrderPattern;
	}

	/**
	 * strOrderQtyを取得します。
	 * @return strOrderQty
	 */
	public String getStrOrderQty() {
		return strOrderQty;
	}

	/**
	 * strOrderQtyを設定します。
	 * @param strOrderQty strOrderQty
	 */
	public void setStrOrderQty(final String strOrderQty) {
		this.strOrderQty = strOrderQty;
	}

	/**
	 * strPaletteProductsを取得します。
	 * @return strPaletteProducts
	 */
	public String getStrPaletteProducts() {
		return strPaletteProducts;
	}

	/**
	 * strPaletteProductsを設定します。
	 * @param strPaletteProducts strPaletteProducts
	 */
	public void setStrPaletteProducts(final String strPaletteProducts) {
		this.strPaletteProducts = strPaletteProducts;
	}

	/**
	 * strProductOrderPointを取得します。
	 * @return strProductOrderPoint
	 */
	public String getStrProductOrderPoint() {
		return strProductOrderPoint;
	}

	/**
	 * strProductOrderPointを設定します。
	 * @param strProductOrderPoint strProductOrderPoint
	 */
	public void setStrProductOrderPoint(final String strProductOrderPoint) {
		this.strProductOrderPoint = strProductOrderPoint;
	}

	/**
	 * strPurchaseLeadTimeを取得します。
	 * @return strPurchaseLeadTime
	 */
	public String getStrPurchaseLeadTime() {
		return strPurchaseLeadTime;
	}

	/**
	 * strPurchaseLeadTimeを設定します。
	 * @param strPurchaseLeadTime strPurchaseLeadTime
	 */
	public void setStrPurchaseLeadTime(final String strPurchaseLeadTime) {
		this.strPurchaseLeadTime = strPurchaseLeadTime;
	}

	/**
	 * strPurchaseOrderPointを取得します。
	 * @return strPurchaseOrderPoint
	 */
	public String getStrPurchaseOrderPoint() {
		return strPurchaseOrderPoint;
	}

	/**
	 * strPurchaseOrderPointを設定します。
	 * @param strPurchaseOrderPoint strPurchaseOrderPoint
	 */
	public void setStrPurchaseOrderPoint(final String strPurchaseOrderPoint) {
		this.strPurchaseOrderPoint = strPurchaseOrderPoint;
	}

	/**
	 * strPurchasePriceを取得します。
	 * @return strPurchasePrice
	 */
	public String getStrPurchasePrice() {
		return strPurchasePrice;
	}

	/**
	 * strPurchasePriceを設定します。
	 * @param strPurchasePrice strPurchasePrice
	 */
	public void setStrPurchasePrice(final String strPurchasePrice) {
		this.strPurchasePrice = strPurchasePrice;
	}

	/**
	 * strSafetyLeadTimeを取得します。
	 * @return strSafetyLeadTime
	 */
	public String getStrSafetyLeadTime() {
		return strSafetyLeadTime;
	}

	/**
	 * strSafetyLeadTimeを設定します。
	 * @param strSafetyLeadTime strSafetyLeadTime
	 */
	public void setStrSafetyLeadTime(final String strSafetyLeadTime) {
		this.strSafetyLeadTime = strSafetyLeadTime;
	}

	/**
	 * strSellingPriceを取得します。
	 * @return strSellingPrice
	 */
	public String getStrSellingPrice() {
		return strSellingPrice;
	}

	/**
	 * strSellingPriceを設定します。
	 * @param strSellingPrice strSellingPrice
	 */
	public void setStrSellingPrice(final String strSellingPrice) {
		this.strSellingPrice = strSellingPrice;
	}

	/**
	 * suppliedGoodsDivisionを取得します。
	 * @return suppliedGoodsDivision
	 */
	public java.math.BigDecimal getSuppliedGoodsDivision() {
		return suppliedGoodsDivision;
	}

	/**
	 * suppliedGoodsDivisionを設定します。
	 * @param suppliedGoodsDivision suppliedGoodsDivision
	 */
	public void setSuppliedGoodsDivision(
			final java.math.BigDecimal suppliedGoodsDivision) {
		this.suppliedGoodsDivision = suppliedGoodsDivision;
	}

	/**
	 * unitDivisionOtherを取得します。
	 * @return unitDivisionOther
	 */
	public String getUnitDivisionOther() {
		return unitDivisionOther;
	}

	/**
	 * unitDivisionOtherを設定します。
	 * @param unitDivisionOther unitDivisionOther
	 */
	public void setUnitDivisionOther(final String unitDivisionOther) {
		this.unitDivisionOther = unitDivisionOther;
	}

	/**
	 * unitDivisionSitankaを取得します。
	 * @return unitDivisionSitanka
	 */
	public String getUnitDivisionSitanka() {
		return unitDivisionSitanka;
	}

	/**
	 * unitDivisionSitankaを設定します。
	 * @param unitDivisionSitanka unitDivisionSitanka
	 */
	public void setUnitDivisionSitanka(final String unitDivisionSitanka) {
		this.unitDivisionSitanka = unitDivisionSitanka;
	}

	/**
	 * unitDivisionUrtankaを取得します。
	 * @return unitDivisionUrtanka
	 */
	public String getUnitDivisionUrtanka() {
		return unitDivisionUrtanka;
	}

	/**
	 * unitDivisionUrtankaを設定します。
	 * @param unitDivisionUrtanka unitDivisionUrtanka
	 */
	public void setUnitDivisionUrtanka(final String unitDivisionUrtanka) {
		this.unitDivisionUrtanka = unitDivisionUrtanka;
	}

	/**
	 * venderCdを取得します。
	 * @return venderCd
	 */
	public String getVenderCd() {
		return venderCd;
	}

	/**
	 * venderCdを設定します。
	 * @param venderCd venderCd
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * versionを取得します。
	 * @return version
	 */
	public BigDecimal getVersion() {
		return version;
	}

	/**
	 * versionを設定します。
	 * @param version version
	 */
	public void setVersion(final BigDecimal version) {
		this.version = version;
	}

	/**
	 * unitOfStockControlを取得します。
	 * @return unitOfStockControl
	 */
	public String getUnitOfStockControl() {
		return unitOfStockControl;
	}

	/**
	 * unitOfStockControlを設定します。
	 * @param unitOfStockControl unitOfStockControl
	 */
	public void setUnitOfStockControl(final String unitOfStockControl) {
		this.unitOfStockControl = unitOfStockControl;
	}

	/**
	 * reasonを取得します。
	 * @return reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * reasonを設定します。
	 * @param reason reason
	 */
	public void setReason(final String reason) {
		this.reason = reason;
	}

	/**
	 * registFlgを取得します。
	 * @return registFlg
	 */
	public String getRegistFlg() {
		return registFlg;
	}

	/**
	 * registFlgを設定します。
	 * @param registFlg registFlg
	 */
	public void setRegistFlg(final String registFlg) {
		this.registFlg = registFlg;
	}

	/**
	 * articleStatusを取得します。
	 * @return articleStatus
	 */
	public BigDecimal getArticleStatus() {
		return articleStatus;
	}

	/**
	 * articleStatusを設定します。
	 * @param articleStatus articleStatus
	 */
	public void setArticleStatus(final BigDecimal articleStatus) {
		this.articleStatus = articleStatus;
	}

	/**
	 * productStatusを取得します。
	 * @return productStatus
	 */
	public BigDecimal getProductStatus() {
		return productStatus;
	}

	/**
	 * productStatusを設定します。
	 * @param productStatus productStatus
	 */
	public void setProductStatus(final BigDecimal productStatus) {
		this.productStatus = productStatus;
	}

	/**
	 * purchaseStatusを取得します。
	 * @return purchaseStatus
	 */
	public BigDecimal getPurchaseStatus() {
		return purchaseStatus;
	}

	/**
	 * purchaseStatusを設定します。
	 * @param purchaseStatus purchaseStatus
	 */
	public void setPurchaseStatus(final BigDecimal purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}

	/**
	 * deliveryName1を取得します。
	 * @return deliveryName1
	 */
	public String getDeliveryName1() {
		return deliveryName1;
	}

	/**
	 * deliveryName1を設定します。
	 * @param deliveryName1 deliveryName1
	 */
	public void setDeliveryName1(final String deliveryName1) {
		this.deliveryName1 = deliveryName1;
	}

	/**
	 * venderName1を取得します。
	 * @return venderName1
	 */
	public String getVenderName1() {
		return venderName1;
	}

	/**
	 * venderName1を設定します。
	 * @param venderName1 venderName1
	 */
	public void setVenderName1(final String venderName1) {
		this.venderName1 = venderName1;
	}

	/**
	 * articlePriceCalcDivisionを取得します。
	 * @return articlePriceCalcDivision
	 */
	public BigDecimal getArticlePriceCalcDivision() {
		return articlePriceCalcDivision;
	}

	/**
	 * articlePriceCalcDivisionを設定します。
	 * @param articlePriceCalcDivision articlePriceCalcDivision
	 */
	public void setArticlePriceCalcDivision(
			final BigDecimal articlePriceCalcDivision) {
		this.articlePriceCalcDivision = articlePriceCalcDivision;
	}

	/**
	 * venderDivisionを取得します。
	 * @return venderDivision
	 */
	public String getVenderDivision() {
		return venderDivision;
	}

	/**
	 * venderDivisionを設定します。
	 * @param venderDivision venderDivision
	 */
	public void setVenderDivision(final String venderDivision) {
		this.venderDivision = venderDivision;
	}

	/**
	 * headActivateを取得します。
	 * @return headActivate
	 */
	public String getHeadActivate() {
		return headActivate;
	}

	/**
	 * headActivateを設定します。
	 * @param headActivate headActivate
	 */
	public void setHeadActivate(final String headActivate) {
		this.headActivate = headActivate;
	}

	/**
	 * headActiveDateを取得します。
	 * @return headActiveDate
	 */
	public Timestamp getHeadActiveDate() {
		return headActiveDate;
	}

	/**
	 * headActiveDateを設定します。
	 * @param headActiveDate headActiveDate
	 */
	public void setHeadActiveDate(final Timestamp headActiveDate) {
		this.headActiveDate = headActiveDate;
	}

	/**
	 * headAttributeStatusNameを取得します。
	 * @return headAttributeStatusName
	 */
	public String getHeadAttributeStatusName() {
		return headAttributeStatusName;
	}

	/**
	 * headAttributeStatusNameを設定します。
	 * @param headAttributeStatusName headAttributeStatusName
	 */
	public void setHeadAttributeStatusName(final String headAttributeStatusName) {
		this.headAttributeStatusName = headAttributeStatusName;
	}

	/**
	 * headDetailStatusNameを取得します。
	 * @return headDetailStatusName
	 */
	public String getHeadDetailStatusName() {
		return headDetailStatusName;
	}

	/**
	 * headDetailStatusNameを設定します。
	 * @param headDetailStatusName headDetailStatusName
	 */
	public void setHeadDetailStatusName(final String headDetailStatusName) {
		this.headDetailStatusName = headDetailStatusName;
	}

	/**
	 * headDispItemNameを取得します。
	 * @return headDispItemName
	 */
	public String getHeadDispItemName() {
		return headDispItemName;
	}

	/**
	 * headDispItemNameを設定します。
	 * @param headDispItemName headDispItemName
	 */
	public void setHeadDispItemName(final String headDispItemName) {
		this.headDispItemName = headDispItemName;
	}

	/**
	 * headItemCdを取得します。
	 * @return headItemCd
	 */
	public String getHeadItemCd() {
		return headItemCd;
	}

	/**
	 * headItemCdを設定します。
	 * @param headItemCd headItemCd
	 */
	public void setHeadItemCd(final String headItemCd) {
		this.headItemCd = headItemCd;
	}

	/**
	 * headVersionを取得します。
	 * @return headVersion
	 */
	public BigDecimal getHeadVersion() {
		return headVersion;
	}

	/**
	 * headVersionを設定します。
	 * @param headVersion headVersion
	 */
	public void setHeadVersion(final BigDecimal headVersion) {
		this.headVersion = headVersion;
	}

	/**
	 * strHeadActiveDateを取得します。
	 * @return strHeadActiveDate
	 */
	public String getStrHeadActiveDate() {
		return strHeadActiveDate;
	}

	/**
	 * strHeadActiveDateを設定します。
	 * @param strHeadActiveDate strHeadActiveDate
	 */
	public void setStrHeadActiveDate(final String strHeadActiveDate) {
		this.strHeadActiveDate = strHeadActiveDate;
	}

	/**
	 * headItemNameを取得します。
	 * @return headItemName
	 */
	public String getHeadItemName() {
		return headItemName;
	}

	/**
	 * headItemNameを設定します。
	 * @param headItemName headItemName
	 */
	public void setHeadItemName(final String headItemName) {
		this.headItemName = headItemName;
	}

	/**
	 * headerStatusを取得します。
	 * @return headerStatus
	 */
	public BigDecimal getHeaderStatus() {
		return headerStatus;
	}

	/**
	 * headerStatusを設定します。
	 * @param headerStatus headerStatus
	 */
	public void setHeaderStatus(final BigDecimal headerStatus) {
		this.headerStatus = headerStatus;
	}

	/**
	 * inputDateを取得します。
	 * @return inputDate
	 */
	public Timestamp getInputDate() {
		return inputDate;
	}

	/**
	 * inputDateを設定します。
	 * @param inputDate inputDate
	 */
	public void setInputDate(final Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * inputFlgを取得します。
	 * @return inputFlg
	 */
	public String getInputFlg() {
		return inputFlg;
	}

	/**
	 * inputFlgを設定します。
	 * @param inputFlg inputFlg
	 */
	public void setInputFlg(final String inputFlg) {
		this.inputFlg = inputFlg;
	}

	/**
	 * strInputDateを取得します。
	 * @return strInputDate
	 */
	public String getStrInputDate() {
		return strInputDate;
	}

	/**
	 * strInputDateを設定します。
	 * @param strInputDate strInputDate
	 */
	public void setStrInputDate(final String strInputDate) {
		this.strInputDate = strInputDate;
	}

	/**
	 * productSectionCdを取得します。
	 * @return productSectionCd
	 */
	public String getProductSectionCd() {
		return productSectionCd;
	}

	/**
	 * productSectionCdを設定します。
	 * @param productSectionCd productSectionCd
	 */
	public void setProductSectionCd(final String productSectionCd) {
		this.productSectionCd = productSectionCd;
	}

	/**
	 * productSectionNameを取得します。
	 * @return productSectionName
	 */
	public String getProductSectionName() {
		return productSectionName;
	}

	/**
	 * productSectionNameを設定します。
	 * @param productSectionName productSectionName
	 */
	public void setProductSectionName(final String productSectionName) {
		this.productSectionName = productSectionName;
	}
}
