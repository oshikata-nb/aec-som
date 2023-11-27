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
 * 品目基本 Formクラス.
 * @author t0011036
 */
public final class ItemDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/* 品目コード */
	private String headItemCd;

	private String itemCd;

	private String copyItemCd;

	/* バージョン */
	private BigDecimal headVersion;

	private BigDecimal version;

	private BigDecimal copyVersion;

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

	/* 有効 */
	private String headActivate;

	/* ステータス名称 */
	private String headDetailStatusName;

	private String headAttributeStatusName;

	/* 品目サブ名称 */
	private String itemSubName;

	/* 研究用品目フラグ */
	private BigDecimal researchItem;

	/* 荷主 */
	private BigDecimal shipperDivision;

	/* 種別 */
	private BigDecimal typeDivision;
	
	/* 売上課税区分 */
	private BigDecimal salesTaxCategory;

	private String[] salesTaxCategoryValues;

	private String[] salesTaxCategoryLabels;
	
	/* 仕入課税区分 */
	private BigDecimal purchaseTaxCategory;

	private String[] purchaseTaxCategoryValues;

	private String[] purchaseTaxCategoryLabels;
	
	/* 品目運賃区分 */
	private BigDecimal itemDivision;

	/* 他社コード1 */
	private String otherCompanyCd1;

	/* 荷姿 */
	private String styleOfPacking;

	/* 入数 */
	private BigDecimal numberOfInsertions;

	private String strNumberOfInsertions;

	/* 総重量 */
	private BigDecimal allUpWeight;

	private String strAllUpWeight;

	/* 運用管理単位 */
	private String unitOfOperationManagement;

	private String[] unitOfOperationManagementValues;

	private String[] unitOfOperationManagementLabels;

	/* 在庫管理単位 */
	private String unitOfStockControl;

	private String[] unitOfStockControlValues;

	private String[] unitOfStockControlLabels;

	/* Kg換算係数（在庫） */
	private BigDecimal kgOfFractionManagement;

	private String strKgOfFractionManagement;

	/* 端数管理単位 */
	private String unitOfFractionManagement;

	private String[] unitOfFractionManagementValues;

	private String[] unitOfFractionManagementLabels;

	/* Kg換算係数（端数） */
	private BigDecimal kgConversionCoefficient;

	private String strKgConversionCoefficient;

	/* 品目分類コード */
	private String itemCategory;

	private String[] itemCategoryValues;

	private String[] itemCategoryLabels;

	/* 親品目コード */
	private String parentItemCd;

	/* 親品目名称 */
	private String parentItemName;

	/* スポット区分 */
	private BigDecimal spotDivision;

	/* 在庫管理区分 */
	private BigDecimal stockDivision;

	/* 基準保管場所 */
	private String defaultLocation;

	/* 基準保管場所名称 */
	private String defaultLocationName;

	/* 発注まとめ場所 */
	private String orderLocation;

	/* 発注まとめ場所名称 */
	private String orderLocationName;

	/* ロット管理区分 */
	private BigDecimal lotDivision;

	/* 水区分 */
	private BigDecimal waterDivision;

	/* 区分 */
	private String unitDivision;

	/* 製造品区分 */
	private BigDecimal productDivision;

	/* 販売品区分 */
	private BigDecimal articleDivision;

	/* 購入品区分 */
	private BigDecimal purchaseDivision;

	/* 更新理由 */
	private String reason;

	/* 更新フラグ */
	private String registFlg;

	/* 更新日時 */
	private java.sql.Timestamp updateDate;

	/* 変更フラグ */
	private Boolean dirtyFlg;

	/* 新規フラグ */
	private String insertFlg;

	/* コピーフラグ */
	private String copyFlg;
	
	/* 課税区分表示フラグ */
	private String taxCategoryFlg;

	/**
	 * コンストラクタ.
	 */
	public ItemDetailForm() {
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
		setCopyItemCd(null);
		setHeadVersion(new BigDecimal("0"));
		setVersion(new BigDecimal("0"));
		setCopyVersion(new BigDecimal("0"));
		setHeadDispItemName(null);
		setHeadItemName(null);
		setItemName(null);
		setHeadActiveDate(null);
		setStrHeadActiveDate(null);
		setActiveDate(null);
		setStrActiveDate(null);
		setStatus(new BigDecimal("0"));
		setHeadActivate(null);
		setHeadDetailStatusName(null);
		setHeadAttributeStatusName(null);
		setItemSubName(null);
		setResearchItem(null);
		setShipperDivision(new BigDecimal("0"));
		setTypeDivision(new BigDecimal("0"));
		setSalesTaxCategory(new BigDecimal("1"));
		setSalesTaxCategoryValues(null);
		setSalesTaxCategoryLabels(null);
		setPurchaseTaxCategory(new BigDecimal("1"));
		setPurchaseTaxCategoryValues(null);
		setPurchaseTaxCategoryLabels(null);
		setItemDivision(new BigDecimal("3"));
		setOtherCompanyCd1(null);
		setStyleOfPacking(null);
		setNumberOfInsertions(new BigDecimal("0"));
		setStrNumberOfInsertions(null);
		setAllUpWeight(new BigDecimal("0"));
		setStrAllUpWeight(null);
		setUnitOfOperationManagement(null);
		setUnitOfOperationManagementValues(null);
		setUnitOfOperationManagementLabels(null);
		setUnitOfStockControl(null);
		setUnitOfStockControlValues(null);
		setUnitOfStockControlLabels(null);
		setKgOfFractionManagement(new BigDecimal("0"));
		setStrKgOfFractionManagement(null);
		setUnitOfFractionManagement(null);
		setUnitOfFractionManagementValues(null);
		setUnitOfFractionManagementLabels(null);
		setKgConversionCoefficient(new BigDecimal("0"));
		setStrKgConversionCoefficient(null);
		setItemCategory(null);
		setItemCategoryValues(null);
		setItemCategoryLabels(null);
		setParentItemCd(null);
		setParentItemName(null);
		setSpotDivision(new BigDecimal("0"));
		setStockDivision(new BigDecimal("0"));
		setDefaultLocation(null);
		setDefaultLocationName(null);
		setOrderLocation(null);
		setOrderLocationName(null);
		setLotDivision(new BigDecimal("2"));
		setWaterDivision(new BigDecimal("0"));
		setUnitDivision("SONOTA3");
		setProductDivision(new BigDecimal("0"));
		setArticleDivision(new BigDecimal("0"));
		setPurchaseDivision(new BigDecimal("0"));
		setReason(null);
		setRegistFlg(null);
		setUpdateDate(null);
		setDirtyFlg(null);
		setInsertFlg(null);
		setCopyFlg(null);
		setTaxCategoryFlg(null);
	}

	/**
	 * updateDateを取得します。
	 * @return updateDate
	 */
	public java.sql.Timestamp getUpdateDate() {
		return updateDate;
	}

	/**
	 * updateDateを設定します。
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
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
	 * allUpWeightを取得します。
	 * @return allUpWeight
	 */
	public BigDecimal getAllUpWeight() {
		return allUpWeight;
	}

	/**
	 * allUpWeightを設定します。
	 * @param allUpWeight allUpWeight
	 */
	public void setAllUpWeight(final BigDecimal allUpWeight) {
		this.allUpWeight = allUpWeight;
	}

	/**
	 * defaultLocationを取得します。
	 * @return defaultLocation
	 */
	public String getDefaultLocation() {
		return defaultLocation;
	}

	/**
	 * defaultLocationを設定します。
	 * @param defaultLocation defaultLocation
	 */
	public void setDefaultLocation(final String defaultLocation) {
		this.defaultLocation = defaultLocation;
	}

	/**
	 * defaultLocationNameを取得します。
	 * @return defaultLocationName
	 */
	public String getDefaultLocationName() {
		return defaultLocationName;
	}

	/**
	 * defaultLocationNameを設定します。
	 * @param defaultLocationName defaultLocationName
	 */
	public void setDefaultLocationName(final String defaultLocationName) {
		this.defaultLocationName = defaultLocationName;
	}

	/**
	 * itemCategoryを取得します。
	 * @return itemCategory
	 */
	public String getItemCategory() {
		return itemCategory;
	}

	/**
	 * itemCategoryを設定します。
	 * @param itemCategory itemCategory
	 */
	public void setItemCategory(final String itemCategory) {
		this.itemCategory = itemCategory;
	}

	/**
	 * itemSubNameを取得します。
	 * @return itemSubName
	 */
	public String getItemSubName() {
		return itemSubName;
	}

	/**
	 * itemSubNameを設定します。
	 * @param itemSubName itemSubName
	 */
	public void setItemSubName(final String itemSubName) {
		this.itemSubName = itemSubName;
	}

	/**
	 * kgConversionCoefficientを取得します。
	 * @return kgConversionCoefficient
	 */
	public BigDecimal getKgConversionCoefficient() {
		return kgConversionCoefficient;
	}

	/**
	 * kgConversionCoefficientを設定します。
	 * @param kgConversionCoefficient kgConversionCoefficient
	 */
	public void setKgConversionCoefficient(
			final BigDecimal kgConversionCoefficient) {
		this.kgConversionCoefficient = kgConversionCoefficient;
	}

	/**
	 * kgOfFractionManagementを取得します。
	 * @return kgOfFractionManagement
	 */
	public BigDecimal getKgOfFractionManagement() {
		return kgOfFractionManagement;
	}

	/**
	 * kgOfFractionManagementを設定します。
	 * @param kgOfFractionManagement kgOfFractionManagement
	 */
	public void setKgOfFractionManagement(
			final BigDecimal kgOfFractionManagement) {
		this.kgOfFractionManagement = kgOfFractionManagement;
	}

	/**
	 * lotDivisionを取得します。
	 * @return lotDivision
	 */
	public BigDecimal getLotDivision() {
		return lotDivision;
	}

	/**
	 * lotDivisionを設定します。
	 * @param lotDivision lotDivision
	 */
	public void setLotDivision(final BigDecimal lotDivision) {
		this.lotDivision = lotDivision;
	}

	/**
	 * numberOfInsertionsを取得します。
	 * @return numberOfInsertions
	 */
	public BigDecimal getNumberOfInsertions() {
		return numberOfInsertions;
	}

	/**
	 * numberOfInsertionsを設定します。
	 * @param numberOfInsertions numberOfInsertions
	 */
	public void setNumberOfInsertions(final BigDecimal numberOfInsertions) {
		this.numberOfInsertions = numberOfInsertions;
	}

	/**
	 * orderLocationを取得します。
	 * @return orderLocation
	 */
	public String getOrderLocation() {
		return orderLocation;
	}

	/**
	 * orderLocationを設定します。
	 * @param orderLocation orderLocation
	 */
	public void setOrderLocation(final String orderLocation) {
		this.orderLocation = orderLocation;
	}

	/**
	 * itemDivisionを取得します。
	 * @return itemDivision
	 */
	public BigDecimal getItemDivision() {
		return itemDivision;
	}

	/**
	 * itemDivisionを設定します。
	 * @param itemDivision itemDivision
	 */
	public void setItemDivision(BigDecimal itemDivision) {
		this.itemDivision = itemDivision;
	}

	/**
	 * otherCompanyCd1を取得します。
	 * @return otherCompanyCd1
	 */
	public String getOtherCompanyCd1() {
		return otherCompanyCd1;
	}

	/**
	 * otherCompanyCd1を設定します。
	 * @param otherCompanyCd1 otherCompanyCd1
	 */
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = otherCompanyCd1;
	}

	/**
	 * parentItemCdを取得します。
	 * @return parentItemCd
	 */
	public String getParentItemCd() {
		return parentItemCd;
	}

	/**
	 * parentItemCdを設定します。
	 * @param parentItemCd parentItemCd
	 */
	public void setParentItemCd(final String parentItemCd) {
		this.parentItemCd = parentItemCd;
	}

	/**
	 * parentItemNameを取得します。
	 * @return parentItemName
	 */
	public String getParentItemName() {
		return parentItemName;
	}

	/**
	 * parentItemNameを設定します。
	 * @param parentItemName parentItemName
	 */
	public void setParentItemName(final String parentItemName) {
		this.parentItemName = parentItemName;
	}

	/**
	 * researchItemを取得します。
	 * @return researchItem
	 */
	public BigDecimal getResearchItem() {
		return researchItem;
	}

	/**
	 * researchItemを設定します。
	 * @param researchItem researchItem
	 */
	public void setResearchItem(final BigDecimal researchItem) {
		this.researchItem = researchItem;
	}

	/**
	 * shipperDivisionを取得します。
	 * @return shipperDivision
	 */
	public BigDecimal getShipperDivision() {
		return shipperDivision;
	}

	/**
	 * shipperDivisionを設定します。
	 * @param shipperDivision shipperDivision
	 */
	public void setShipperDivision(final BigDecimal shipperDivision) {
		this.shipperDivision = shipperDivision;
	}

	/**
	 * spotDivisionを取得します。
	 * @return spotDivision
	 */
	public BigDecimal getSpotDivision() {
		return spotDivision;
	}

	/**
	 * spotDivisionを設定します。
	 * @param spotDivision spotDivision
	 */
	public void setSpotDivision(final BigDecimal spotDivision) {
		this.spotDivision = spotDivision;
	}

	/**
	 * stockDivisionを取得します。
	 * @return stockDivision
	 */
	public BigDecimal getStockDivision() {
		return stockDivision;
	}

	/**
	 * stockDivisionを設定します。
	 * @param stockDivision stockDivision
	 */
	public void setStockDivision(final BigDecimal stockDivision) {
		this.stockDivision = stockDivision;
	}

	/**
	 * strAllUpWeightを取得します。
	 * @return strAllUpWeight
	 */
	public String getStrAllUpWeight() {
		return strAllUpWeight;
	}

	/**
	 * strAllUpWeightを設定します。
	 * @param strAllUpWeight strAllUpWeight
	 */
	public void setStrAllUpWeight(final String strAllUpWeight) {
		this.strAllUpWeight = strAllUpWeight;
	}

	/**
	 * strKgConversionCoefficientを取得します。
	 * @return strKgConversionCoefficient
	 */
	public String getStrKgConversionCoefficient() {
		return strKgConversionCoefficient;
	}

	/**
	 * strKgConversionCoefficientを設定します。
	 * @param strKgConversionCoefficient strKgConversionCoefficient
	 */
	public void setStrKgConversionCoefficient(
			final String strKgConversionCoefficient) {
		this.strKgConversionCoefficient = strKgConversionCoefficient;
	}

	/**
	 * strKgOfFractionManagementを取得します。
	 * @return strKgOfFractionManagement
	 */
	public String getStrKgOfFractionManagement() {
		return strKgOfFractionManagement;
	}

	/**
	 * strKgOfFractionManagementを設定します。
	 * @param strKgOfFractionManagement strKgOfFractionManagement
	 */
	public void setStrKgOfFractionManagement(
			final String strKgOfFractionManagement) {
		this.strKgOfFractionManagement = strKgOfFractionManagement;
	}

	/**
	 * strNumberOfInsertionsを取得します。
	 * @return strNumberOfInsertions
	 */
	public String getStrNumberOfInsertions() {
		return strNumberOfInsertions;
	}

	/**
	 * strNumberOfInsertionsを設定します。
	 * @param strNumberOfInsertions strNumberOfInsertions
	 */
	public void setStrNumberOfInsertions(final String strNumberOfInsertions) {
		this.strNumberOfInsertions = strNumberOfInsertions;
	}

	/**
	 * styleOfPackingを取得します。
	 * @return styleOfPacking
	 */
	public String getStyleOfPacking() {
		return styleOfPacking;
	}

	/**
	 * styleOfPackingを設定します。
	 * @param styleOfPacking styleOfPacking
	 */
	public void setStyleOfPacking(final String styleOfPacking) {
		this.styleOfPacking = styleOfPacking;
	}

	/**
	 * typeDivisionを取得します。
	 * @return typeDivision
	 */
	public BigDecimal getTypeDivision() {
		return typeDivision;
	}

	/**
	 * typeDivisionを設定します。
	 * @param typeDivision typeDivision
	 */
	public void setTypeDivision(final BigDecimal typeDivision) {
		this.typeDivision = typeDivision;
	}
	
	/**
	 * salesTaxCategoryを取得します。
	 * @return salesTaxCategory
	 */
	public BigDecimal getSalesTaxCategory() {
		return salesTaxCategory;
	}

	/**
	 * salesTaxCategoryを設定します。
	 * @param salesTaxCategory salesTaxCategory
	 */
	public void setSalesTaxCategory(final BigDecimal salesTaxCategory) {
		this.salesTaxCategory = salesTaxCategory;
	}
	
	/**
	 * salesTaxCategoryLabelsを取得します。
	 * @return salesTaxCategoryLabels
	 */
	public String[] getSalesTaxCategoryLabels() {
		return salesTaxCategoryLabels;
	}

	/**
	 * salesTaxCategoryLabelsを設定します。
	 * @param salesTaxCategoryLabels salesTaxCategoryLabels
	 */
	public void setSalesTaxCategoryLabels(
			final String[] salesTaxCategoryLabels) {
		this.salesTaxCategoryLabels = salesTaxCategoryLabels;
	}

	/**
	 * salesTaxCategoryValuesを取得します。
	 * @return salesTaxCategoryValues
	 */
	public String[] getSalesTaxCategoryValues() {
		return salesTaxCategoryValues;
	}
	
	/**
	 * salesTaxCategoryLabelsを設定します。
	 * @param salesTaxCategoryLabels salesTaxCategoryLabels
	 */
	public void setSalesTaxCategoryValues(
			final String[] salesTaxCategoryValues) {
		this.salesTaxCategoryValues = salesTaxCategoryValues;
	}
	
	/**
	 * purchaseTaxCategoryを取得します。
	 * @return purchaseTaxCategory
	 */
	public BigDecimal getPurchaseTaxCategory() {
		return purchaseTaxCategory;
	}

	/**
	 * purchaseTaxCategoryを設定します。
	 * @param purchaseTaxCategory purchaseTaxCategory
	 */
	public void setPurchaseTaxCategory(final BigDecimal purchaseTaxCategory) {
		this.purchaseTaxCategory = purchaseTaxCategory;
	}
	
	/**
	 * purchaseTaxCategoryLabelsを取得します。
	 * @return purchaseTaxCategoryLabels
	 */
	public String[] getPurchaseTaxCategoryLabels() {
		return purchaseTaxCategoryLabels;
	}

	/**
	 * purchaseTaxCategoryLabelsを設定します。
	 * @param purchaseTaxCategoryLabels purchaseTaxCategoryLabels
	 */
	public void setPurchaseTaxCategoryLabels(
			final String[] purchaseTaxCategoryLabels) {
		this.purchaseTaxCategoryLabels = purchaseTaxCategoryLabels;
	}

	/**
	 * purchaseTaxCategoryValuesを取得します。
	 * @return purchaseTaxCategoryValues
	 */
	public String[] getPurchaseTaxCategoryValues() {
		return purchaseTaxCategoryValues;
	}
	
	/**
	 * purchaseTaxCategoryLabelsを設定します。
	 * @param purchaseTaxCategoryLabels purchaseTaxCategoryLabels
	 */
	public void setPurchaseTaxCategoryValues(
			final String[] purchaseTaxCategoryValues) {
		this.purchaseTaxCategoryValues = purchaseTaxCategoryValues;
	}
	/**
	 * unitOfFractionManagementを取得します。
	 * @return unitOfFractionManagement
	 */
	public String getUnitOfFractionManagement() {
		return unitOfFractionManagement;
	}

	/**
	 * unitOfFractionManagementを設定します。
	 * @param unitOfFractionManagement unitOfFractionManagement
	 */
	public void setUnitOfFractionManagement(
			final String unitOfFractionManagement) {
		this.unitOfFractionManagement = unitOfFractionManagement;
	}

	/**
	 * unitOfOperationManagementを取得します。
	 * @return unitOfOperationManagement
	 */
	public String getUnitOfOperationManagement() {
		return unitOfOperationManagement;
	}

	/**
	 * unitOfOperationManagementを設定します。
	 * @param unitOfOperationManagement unitOfOperationManagement
	 */
	public void setUnitOfOperationManagement(
			final String unitOfOperationManagement) {
		this.unitOfOperationManagement = unitOfOperationManagement;
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
	 * waterDivisionを取得します。
	 * @return waterDivision
	 */
	public BigDecimal getWaterDivision() {
		return waterDivision;
	}

	/**
	 * waterDivisionを設定します。
	 * @param waterDivision waterDivision
	 */
	public void setWaterDivision(final BigDecimal waterDivision) {
		this.waterDivision = waterDivision;
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
	 * unitDivisionを取得します。
	 * @return unitDivision
	 */
	public String getUnitDivision() {
		return unitDivision;
	}

	/**
	 * unitDivisionを設定します。
	 * @param unitDivision unitDivision
	 */
	public void setUnitDivision(final String unitDivision) {
		this.unitDivision = unitDivision;
	}

	/**
	 * itemCategoryLabelsを取得します。
	 * @return itemCategoryLabels
	 */
	public String[] getItemCategoryLabels() {
		return itemCategoryLabels;
	}

	/**
	 * itemCategoryLabelsを設定します。
	 * @param itemCategoryLabels itemCategoryLabels
	 */
	public void setItemCategoryLabels(final String[] itemCategoryLabels) {
		this.itemCategoryLabels = itemCategoryLabels;
	}

	/**
	 * itemCategoryValuesを取得します。
	 * @return itemCategoryValues
	 */
	public String[] getItemCategoryValues() {
		return itemCategoryValues;
	}

	/**
	 * itemCategoryValuesを設定します。
	 * @param itemCategoryValues itemCategoryValues
	 */
	public void setItemCategoryValues(final String[] itemCategoryValues) {
		this.itemCategoryValues = itemCategoryValues;
	}

	/**
	 * unitOfFractionManagementLabelsを取得します。
	 * @return unitOfFractionManagementLabels
	 */
	public String[] getUnitOfFractionManagementLabels() {
		return unitOfFractionManagementLabels;
	}

	/**
	 * unitOfFractionManagementLabelsを設定します。
	 * @param unitOfFractionManagementLabels unitOfFractionManagementLabels
	 */
	public void setUnitOfFractionManagementLabels(
			final String[] unitOfFractionManagementLabels) {
		this.unitOfFractionManagementLabels = unitOfFractionManagementLabels;
	}

	/**
	 * unitOfFractionManagementValuesを取得します。
	 * @return unitOfFractionManagementValues
	 */
	public String[] getUnitOfFractionManagementValues() {
		return unitOfFractionManagementValues;
	}

	/**
	 * unitOfFractionManagementValuesを設定します。
	 * @param unitOfFractionManagementValues unitOfFractionManagementValues
	 */
	public void setUnitOfFractionManagementValues(
			final String[] unitOfFractionManagementValues) {
		this.unitOfFractionManagementValues = unitOfFractionManagementValues;
	}

	/**
	 * unitOfOperationManagementLabelsを取得します。
	 * @return unitOfOperationManagementLabels
	 */
	public String[] getUnitOfOperationManagementLabels() {
		return unitOfOperationManagementLabels;
	}

	/**
	 * unitOfOperationManagementLabelsを設定します。
	 * @param unitOfOperationManagementLabels unitOfOperationManagementLabels
	 */
	public void setUnitOfOperationManagementLabels(
			final String[] unitOfOperationManagementLabels) {
		this.unitOfOperationManagementLabels = unitOfOperationManagementLabels;
	}

	/**
	 * unitOfOperationManagementValuesを取得します。
	 * @return unitOfOperationManagementValues
	 */
	public String[] getUnitOfOperationManagementValues() {
		return unitOfOperationManagementValues;
	}

	/**
	 * unitOfOperationManagementValuesを設定します。
	 * @param unitOfOperationManagementValues unitOfOperationManagementValues
	 */
	public void setUnitOfOperationManagementValues(
			final String[] unitOfOperationManagementValues) {
		this.unitOfOperationManagementValues = unitOfOperationManagementValues;
	}

	/**
	 * unitOfStockControlLabelsを取得します。
	 * @return unitOfStockControlLabels
	 */
	public String[] getUnitOfStockControlLabels() {
		return unitOfStockControlLabels;
	}

	/**
	 * unitOfStockControlLabelsを設定します。
	 * @param unitOfStockControlLabels unitOfStockControlLabels
	 */
	public void setUnitOfStockControlLabels(
			final String[] unitOfStockControlLabels) {
		this.unitOfStockControlLabels = unitOfStockControlLabels;
	}

	/**
	 * unitOfStockControlValuesを取得します。
	 * @return unitOfStockControlValues
	 */
	public String[] getUnitOfStockControlValues() {
		return unitOfStockControlValues;
	}

	/**
	 * unitOfStockControlValuesを設定します。
	 * @param unitOfStockControlValues unitOfStockControlValues
	 */
	public void setUnitOfStockControlValues(
			final String[] unitOfStockControlValues) {
		this.unitOfStockControlValues = unitOfStockControlValues;
	}

	/**
	 * orderLocationNameを取得します。
	 * @return orderLocationName
	 */
	public String getOrderLocationName() {
		return orderLocationName;
	}

	/**
	 * orderLocationNameを設定します。
	 * @param orderLocationName orderLocationName
	 */
	public void setOrderLocationName(final String orderLocationName) {
		this.orderLocationName = orderLocationName;
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
	 * articleDivisionを取得します。
	 * @return articleDivision
	 */
	public BigDecimal getArticleDivision() {
		return articleDivision;
	}

	/**
	 * articleDivisionを設定します。
	 * @param articleDivision articleDivision
	 */
	public void setArticleDivision(final BigDecimal articleDivision) {
		this.articleDivision = articleDivision;
	}

	/**
	 * productDivisionを取得します。
	 * @return productDivision
	 */
	public BigDecimal getProductDivision() {
		return productDivision;
	}

	/**
	 * productDivisionを設定します。
	 * @param productDivision productDivision
	 */
	public void setProductDivision(final BigDecimal productDivision) {
		this.productDivision = productDivision;
	}

	/**
	 * purchaseDivisionを取得します。
	 * @return purchaseDivision
	 */
	public BigDecimal getPurchaseDivision() {
		return purchaseDivision;
	}

	/**
	 * purchaseDivisionを設定します。
	 * @param purchaseDivision purchaseDivision
	 */
	public void setPurchaseDivision(final BigDecimal purchaseDivision) {
		this.purchaseDivision = purchaseDivision;
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
	 * copyFlgを取得します。
	 * @return copyFlg
	 */
	public String getCopyFlg() {
		return copyFlg;
	}

	/**
	 * copyFlgを設定します。
	 * @param copyFlg copyFlg
	 */
	public void setCopyFlg(final String copyFlg) {
		this.copyFlg = copyFlg;
	}
	
	/**
	 * taxCategoryFlgを取得します。
	 * @return taxCategoryFlg
	 */
	public String getTaxCategoryFlg() {
		return taxCategoryFlg;
	}

	/**
	 * taxCategoryFlgを設定します。
	 * @param taxCategoryFlg taxCategoryFlg
	 */
	public void setTaxCategoryFlg(final String taxCategoryFlg) {
		this.taxCategoryFlg = taxCategoryFlg;
	}

	/**
	 * copyItemCdを取得します。
	 * @return copyItemCd
	 */
	public String getCopyItemCd() {
		return copyItemCd;
	}

	/**
	 * copyItemCdを設定します。
	 * @param copyItemCd copyItemCd
	 */
	public void setCopyItemCd(final String copyItemCd) {
		this.copyItemCd = copyItemCd;
	}

	/**
	 * copyVersionを取得します。
	 * @return copyVersion
	 */
	public BigDecimal getCopyVersion() {
		return copyVersion;
	}

	/**
	 * copyVersionを設定します。
	 * @param copyVersion copyVersion
	 */
	public void setCopyVersion(final BigDecimal copyVersion) {
		this.copyVersion = copyVersion;
	}

	/**
	 * insertFlgを取得します。
	 * @return insertFlg
	 */
	public String getInsertFlg() {
		return insertFlg;
	}

	/**
	 * insertFlgを設定します。
	 * @param insertFlg insertFlg
	 */
	public void setInsertFlg(final String insertFlg) {
		this.insertFlg = insertFlg;
	}
}
