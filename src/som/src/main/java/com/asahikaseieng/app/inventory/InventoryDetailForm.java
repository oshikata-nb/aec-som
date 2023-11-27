/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inventory;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.struts.AbstractForm;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 在庫入庫入力 Formクラス.
 * @author t0011036
 */
public final class InventoryDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/* 日付 */
	private java.sql.Timestamp lastInDate;

	private String strLastInDate;

	/* 品目コード */
	private String itemCd;

	/* 品目名称 */
	private String itemName;

	/* 他社コード1 */
	private String otherCompanyCd1;

	/* 荷姿 */
	private String styleOfPacking;

	/* 種別 */
	private BigDecimal typeDivision;

	/* 入庫ロケーションコード */
	private String locationCd;

	/* 入庫ロケーション名称 */
	private String locationName;

	/* 入荷ロット番号/包装指図番号 */
	private String lotNo;

	/* 原料ロット番号/製品ロット番号 */
	private String aliasLotNo;

	/* 荷姿数 */
	private java.math.BigDecimal packQty;

	private String strPackQty;

	/* 端数 */
	private java.math.BigDecimal fraction;

	private String strFraction;

	/* 総量 */
	private java.math.BigDecimal inventoryQty;

	private String strInventoryQty;

	/* 単価 */
	private java.math.BigDecimal inventoryCost;

	private String strInventoryCost;

	/* ラベル枚数 */
	private java.math.BigDecimal labelCount;

	private String strLabelCount;

	/* 理由コード */
	private String ryCd;

	/* 理由内容 */
	private String ryDescription;

	/* 適用 */
	private String remark;

	/* 区分 */
	private String unitOfOperationManagement;

	private String unitOfFractionManagement;

	private String unitDivisionUnit;

	private String unitDivisionOther;

	/* ロット管理区分 */
	private BigDecimal lotDivision;

	/* カーソル位置 */
	private String cursor;

	/* 変更フラグ */
	private Boolean dirtyFlg;

	/* KG換算係数（在庫） */
	private BigDecimal kgOfFractionManagement;

	/* Kg換算係数（端数） */
	private BigDecimal kgConversionCoefficient;

	/* 小数点以下桁数 */
	private BigDecimal smallnumLength;

	/* 丸め区分 */
	private BigDecimal roundDivision;

	/* EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/**
	 * コンストラクタ.
	 */
	public InventoryDetailForm() {
		super();
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

		/* ダウンロードフラグを倒す */
		setExcelDownloadFlg(false);
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
		lastInDate = AecDateUtils.getCurrentTimestamp();
		setStrLastInDate(AecDateUtils.dateFormat(getLastInDate(), "yyyy/MM/dd"));
		setItemCd(null);
		setItemName(null);
		setOtherCompanyCd1(null);
		setStyleOfPacking(null);
		setTypeDivision(new BigDecimal("0"));
		setLocationCd(null);
		setLocationName(null);
		setLotNo(null);
		setAliasLotNo(null);
		setPackQty(new BigDecimal("0"));
		setStrPackQty(null);
		setFraction(new BigDecimal("0"));
		setStrFraction(null);
		setInventoryQty(new BigDecimal("0"));
		setStrInventoryQty(null);
		setInventoryCost(new BigDecimal("0"));
		setStrInventoryCost(null);
		setLabelCount(new BigDecimal("0"));
		setStrLabelCount(null);
		setRyCd(null);
		setRyDescription(null);
		setRemark(null);
		setUnitOfOperationManagement(null);
		setUnitOfFractionManagement(null);
		setUnitDivisionUnit("SITANKA");
		setUnitDivisionOther("SONOTA");
		setLotDivision(new BigDecimal("0"));
		setCursor(null);
		setDirtyFlg(null);
	}

	/**
	 * aliasLotNoを取得します。
	 * @return aliasLotNo
	 */
	public String getAliasLotNo() {
		return aliasLotNo;
	}

	/**
	 * aliasLotNoを設定します。
	 * @param aliasLotNo aliasLotNo
	 */
	public void setAliasLotNo(final String aliasLotNo) {
		this.aliasLotNo = aliasLotNo;
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
	 * labelCountを取得します。
	 * @return labelCount
	 */
	public java.math.BigDecimal getLabelCount() {
		return labelCount;
	}

	/**
	 * labelCountを設定します。
	 * @param labelCount labelCount
	 */
	public void setLabelCount(final java.math.BigDecimal labelCount) {
		this.labelCount = labelCount;
	}

	/**
	 * lastInDateを取得します。
	 * @return lastInDate
	 */
	public java.sql.Timestamp getLastInDate() {
		return lastInDate;
	}

	/**
	 * lastInDateを設定します。
	 * @param lastInDate lastInDate
	 */
	public void setLastInDate(final java.sql.Timestamp lastInDate) {
		this.lastInDate = lastInDate;
	}

	/**
	 * locationCdを取得します。
	 * @return locationCd
	 */
	public String getLocationCd() {
		return locationCd;
	}

	/**
	 * locationCdを設定します。
	 * @param locationCd locationCd
	 */
	public void setLocationCd(final String locationCd) {
		this.locationCd = locationCd;
	}

	/**
	 * locationNameを取得します。
	 * @return locationName
	 */
	public String getLocationName() {
		return locationName;
	}

	/**
	 * locationNameを設定します。
	 * @param locationName locationName
	 */
	public void setLocationName(final String locationName) {
		this.locationName = locationName;
	}

	/**
	 * lotNoを取得します。
	 * @return lotNo
	 */
	public String getLotNo() {
		return lotNo;
	}

	/**
	 * lotNoを設定します。
	 * @param lotNo lotNo
	 */
	public void setLotNo(final String lotNo) {
		this.lotNo = lotNo;
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
	 * remarkを取得します。
	 * @return remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * remarkを設定します。
	 * @param remark remark
	 */
	public void setRemark(final String remark) {
		this.remark = remark;
	}

	/**
	 * ryCdを取得します。
	 * @return ryCd
	 */
	public String getRyCd() {
		return ryCd;
	}

	/**
	 * ryCdを設定します。
	 * @param ryCd ryCd
	 */
	public void setRyCd(final String ryCd) {
		this.ryCd = ryCd;
	}

	/**
	 * ryDescriptionを取得します。
	 * @return ryDescription
	 */
	public String getRyDescription() {
		return ryDescription;
	}

	/**
	 * ryDescriptionを設定します。
	 * @param ryDescription ryDescription
	 */
	public void setRyDescription(final String ryDescription) {
		this.ryDescription = ryDescription;
	}

	/**
	 * strLabelCountを取得します。
	 * @return strLabelCount
	 */
	public String getStrLabelCount() {
		return strLabelCount;
	}

	/**
	 * strLabelCountを設定します。
	 * @param strLabelCount strLabelCount
	 */
	public void setStrLabelCount(final String strLabelCount) {
		this.strLabelCount = strLabelCount;
	}

	/**
	 * strLastInDateを取得します。
	 * @return strLastInDate
	 */
	public String getStrLastInDate() {
		return strLastInDate;
	}

	/**
	 * strLastInDateを設定します。
	 * @param strLastInDate strLastInDate
	 */
	public void setStrLastInDate(final String strLastInDate) {
		this.strLastInDate = strLastInDate;
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
	 * unitDivisionUnitを取得します。
	 * @return unitDivisionUnit
	 */
	public String getUnitDivisionUnit() {
		return unitDivisionUnit;
	}

	/**
	 * unitDivisionUnitを設定します。
	 * @param unitDivisionUnit unitDivisionUnit
	 */
	public void setUnitDivisionUnit(final String unitDivisionUnit) {
		this.unitDivisionUnit = unitDivisionUnit;
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
	 * fractionを取得します。
	 * @return fraction
	 */
	public java.math.BigDecimal getFraction() {
		return fraction;
	}

	/**
	 * fractionを設定します。
	 * @param fraction fraction
	 */
	public void setFraction(final java.math.BigDecimal fraction) {
		this.fraction = fraction;
	}

	/**
	 * inventoryCostを取得します。
	 * @return inventoryCost
	 */
	public java.math.BigDecimal getInventoryCost() {
		return inventoryCost;
	}

	/**
	 * inventoryCostを設定します。
	 * @param inventoryCost inventoryCost
	 */
	public void setInventoryCost(final java.math.BigDecimal inventoryCost) {
		this.inventoryCost = inventoryCost;
	}

	/**
	 * inventoryQtyを取得します。
	 * @return inventoryQty
	 */
	public java.math.BigDecimal getInventoryQty() {
		return inventoryQty;
	}

	/**
	 * inventoryQtyを設定します。
	 * @param inventoryQty inventoryQty
	 */
	public void setInventoryQty(final java.math.BigDecimal inventoryQty) {
		this.inventoryQty = inventoryQty;
	}

	/**
	 * strFractionを取得します。
	 * @return strFraction
	 */
	public String getStrFraction() {
		return strFraction;
	}

	/**
	 * strFractionを設定します。
	 * @param strFraction strFraction
	 */
	public void setStrFraction(final String strFraction) {
		this.strFraction = strFraction;
	}

	/**
	 * strInventoryCostを取得します。
	 * @return strInventoryCost
	 */
	public String getStrInventoryCost() {
		return strInventoryCost;
	}

	/**
	 * strInventoryCostを設定します。
	 * @param strInventoryCost strInventoryCost
	 */
	public void setStrInventoryCost(final String strInventoryCost) {
		this.strInventoryCost = strInventoryCost;
	}

	/**
	 * strInventoryQtyを取得します。
	 * @return strInventoryQty
	 */
	public String getStrInventoryQty() {
		return strInventoryQty;
	}

	/**
	 * strInventoryQtyを設定します。
	 * @param strInventoryQty strInventoryQty
	 */
	public void setStrInventoryQty(final String strInventoryQty) {
		this.strInventoryQty = strInventoryQty;
	}

	/**
	 * cursorを取得します。
	 * @return cursor
	 */
	public String getCursor() {
		return cursor;
	}

	/**
	 * cursorを設定します。
	 * @param cursor cursor
	 */
	public void setCursor(final String cursor) {
		this.cursor = cursor;
	}

	/**
	 * packQtyを取得します。
	 * @return packQty
	 */
	public java.math.BigDecimal getPackQty() {
		return packQty;
	}

	/**
	 * packQtyを設定します。
	 * @param packQty packQty
	 */
	public void setPackQty(final java.math.BigDecimal packQty) {
		this.packQty = packQty;
	}

	/**
	 * strPackQtyを取得します。
	 * @return strPackQty
	 */
	public String getStrPackQty() {
		return strPackQty;
	}

	/**
	 * strPackQtyを設定します。
	 * @param strPackQty strPackQty
	 */
	public void setStrPackQty(final String strPackQty) {
		this.strPackQty = strPackQty;
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
	 * roundDivisionを取得します。
	 * @return roundDivision
	 */
	public BigDecimal getRoundDivision() {
		return roundDivision;
	}

	/**
	 * roundDivisionを設定します。
	 * @param roundDivision roundDivision
	 */
	public void setRoundDivision(final BigDecimal roundDivision) {
		this.roundDivision = roundDivision;
	}

	/**
	 * smallnumLengthを取得します。
	 * @return smallnumLength
	 */
	public BigDecimal getSmallnumLength() {
		return smallnumLength;
	}

	/**
	 * smallnumLengthを設定します。
	 * @param smallnumLength smallnumLength
	 */
	public void setSmallnumLength(final BigDecimal smallnumLength) {
		this.smallnumLength = smallnumLength;
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
}
