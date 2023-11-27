/*
 * Created on 2007/12/26
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
 * 在庫出庫リスト Formクラス
 * @author FPC
 */
public final class InventoryShippingoutDetailForm extends AbstractForm {

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

	/* 出庫ロケーションコード */
	private String locationCd;

	/* 出庫ロケーション名称 */
	private String locationName;

	/* 入荷ロット番号/包装指図番号 */
	private String lotNo;

	/* 原料ロット番号/製品ロット番号 */
	private String aliasLotNo;

	/* 荷姿在庫数 */
	private java.math.BigDecimal dispPackQty;

	private String strDispPackQty;

	/* 荷姿単位コード */
	private String unitOfOperationManagement;

	/* 荷姿単位 */
	private String packUnit;

	/* 荷姿数 */
	private java.math.BigDecimal packQty;

	private String strPackQty;

	/* 端数在庫数 */
	private java.math.BigDecimal dispFraction;

	private String strDispFraction;

	/* 端数単位コード */
	private String unitOfFractionManagement;

	/* 端数単位 */
	private String fractionUnit;

	/* 端数 */
	private java.math.BigDecimal fraction;

	private String strFraction;

	/* 総量 */
	private java.math.BigDecimal inventoryQty;

	private String strInventoryQty;

	/* 理由コード */
	private String ryCd;

	/* 理由 */
	private String ryDescription;

	/* 摘要 */
	private String remark;

	/* 区分 */
	private String unitDivisionQty;

	/* カーソル位置 */
	private String cursor;

	/* 変更フラグ */
	private Boolean dirtyFlg;

	/* KG換算係数(在庫) */
	private BigDecimal kgOfFractionManagement;

	/* KG換算係数(端数) */
	private BigDecimal kgConversionCoefficient;

	/* 小数点以下桁数 */
	private BigDecimal smallnumLength;

	/* 端数区分 */
	private BigDecimal roundDivision;

	/**
	 * コンストラクタ
	 */
	public InventoryShippingoutDetailForm() {
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
		setLastInDate(AecDateUtils.getCurrentTimestamp());
		setStrLastInDate(AecDateUtils.dateFormat(AecDateUtils
				.getCurrentTimestamp(), "yyyy/MM/dd"));
		setItemCd(null);
		setItemName(null);
		setOtherCompanyCd1(null);
		setStyleOfPacking(null);
		setLocationCd(null);
		setLocationName(null);
		setLotNo(null);
		setAliasLotNo(null);
		setDispPackQty(null);
		setStrDispPackQty(null);
		setPackUnit(null);
		setInventoryQty(null);
		setStrInventoryQty(null);
		setUnitOfOperationManagement(null);
		setDispFraction(null);
		setStrDispFraction(null);
		setFraction(null);
		setStrFraction(null);
		setUnitOfFractionManagement(null);
		setFractionUnit(null);
		setInventoryQty(null);
		setStrInventoryQty(null);
		setRyCd(null);
		setRyDescription(null);
		setRemark(null);
		setUnitDivisionQty(null);
		setCursor(null);
		setDirtyFlg(false);
		setKgOfFractionManagement(BigDecimal.ZERO);
		setKgConversionCoefficient(BigDecimal.ZERO);
		setSmallnumLength(BigDecimal.ZERO);
		setRoundDivision(BigDecimal.ZERO);
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
	 * fractionUnitを取得します。
	 * @return fractionUnit
	 */
	public String getFractionUnit() {
		return fractionUnit;
	}

	/**
	 * fractionUnitを設定します。
	 * @param fractionUnit fractionUnit
	 */
	public void setFractionUnit(final String fractionUnit) {
		this.fractionUnit = fractionUnit;
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
	 * dispFractionを取得します。
	 * @return dispFraction
	 */
	public java.math.BigDecimal getDispFraction() {
		return dispFraction;
	}

	/**
	 * dispFractionを設定します。
	 * @param dispFraction dispFraction
	 */
	public void setDispFraction(final java.math.BigDecimal dispFraction) {
		this.dispFraction = dispFraction;
	}

	/**
	 * strDispFractionを取得します。
	 * @return strDispFraction
	 */
	public String getStrDispFraction() {
		return strDispFraction;
	}

	/**
	 * strDispFractionを設定します。
	 * @param strDispFraction strDispFraction
	 */
	public void setStrDispFraction(final String strDispFraction) {
		this.strDispFraction = strDispFraction;
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
	 * unitDivisionQtyを取得します。
	 * @return unitDivisionQty
	 */
	public String getUnitDivisionQty() {
		return unitDivisionQty;
	}

	/**
	 * unitDivisionQtyを設定します。
	 * @param unitDivisionQty unitDivisionQty
	 */
	public void setUnitDivisionQty(final String unitDivisionQty) {
		this.unitDivisionQty = unitDivisionQty;
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
	 * dispPackQtyを取得します。
	 * @return dispPackQty
	 */
	public java.math.BigDecimal getDispPackQty() {
		return dispPackQty;
	}

	/**
	 * dispPackQtyを設定します。
	 * @param dispPackQty dispPackQty
	 */
	public void setDispPackQty(final java.math.BigDecimal dispPackQty) {
		this.dispPackQty = dispPackQty;
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
	 * packUnitを取得します。
	 * @return packUnit
	 */
	public String getPackUnit() {
		return packUnit;
	}

	/**
	 * packUnitを設定します。
	 * @param packUnit packUnit
	 */
	public void setPackUnit(final String packUnit) {
		this.packUnit = packUnit;
	}

	/**
	 * strDispPackQtyを取得します。
	 * @return strDispPackQty
	 */
	public String getStrDispPackQty() {
		return strDispPackQty;
	}

	/**
	 * strDispPackQtyを設定します。
	 * @param strDispPackQty strDispPackQty
	 */
	public void setStrDispPackQty(final String strDispPackQty) {
		this.strDispPackQty = strDispPackQty;
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
}
