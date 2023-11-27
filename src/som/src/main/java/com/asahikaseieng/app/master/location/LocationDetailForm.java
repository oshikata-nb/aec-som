/*
 * Created on 2009/01/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.location;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.struts.AbstractForm;

/**
 * ロケーション詳細 Formクラス.
 * @author t0011036
 */
public final class LocationDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/* ロケーションコード */
	private String locationCd;

	/* ロケーション名称 */
	private String locationName;

	/* 上位ロケーションコード */
	private String upperLocationCd;

	/* 上位ロケーション名称 */
	private String upperLocationName;

	/* ロケーションレベル */
	private java.math.BigDecimal locationLevel;

	/* 可能重量 */
	private java.math.BigDecimal possibleWeight;

	private String strPossibleWeight;

	/* MRP対象フラグ */
	private java.math.BigDecimal mrpTargetFlg;

	/* 在庫可能フラグ */
	private java.math.BigDecimal availableFlg;

	/* 営業担当者コード */
	private String tantoCd;

	/* 営業担当者名 */
	private String tantoNm;

	/* 会計部門コード */
	private String sectionCd;

	/* 会計部門名称 */
	private String sectionName;

	/* 棚卸区分 */
	private String countDivision;

	/* 在庫区分 */
	private java.math.BigDecimal stockDivision;

	/* ローリー原料品目コード */
	private String itemCd;

	/* ローリー原料品目名称 */
	private String itemName;

	/* 更新日時 */
	private java.sql.Timestamp updateDate;

	/* 区分 */
	private String unitDivision;

	/* 変更フラグ */
	private Boolean dirtyFlg;

	/* 新規フラグ */
	private String newFlg;

	/* 循環棚卸区分名称 */
	private String[] countDivisionValues;

	private String[] countDivisionLabels;

	/**
	 * コンストラクタ.
	 */
	public LocationDetailForm() {
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
		setLocationCd(null);
		setLocationName(null);
		setUpperLocationCd(null);
		setUpperLocationName(null);
		setLocationLevel(null);
		setPossibleWeight(null);
		setStrPossibleWeight(null);
		setMrpTargetFlg(null);
		setAvailableFlg(null);
		setTantoCd(null);
		setTantoNm(null);
		setSectionCd(null);
		setSectionName(null);
		setCountDivision(null);
		setStockDivision(null);
		setItemCd(null);
		setItemName(null);
		setUpdateDate(null);
		setUnitDivision("SONOTA");
		setDirtyFlg(null);
		setNewFlg(null);
		setCountDivisionValues(null);
		setCountDivisionLabels(null);
	}

	/**
	 * availableFlgを取得します。
	 * @return availableFlg
	 */
	public java.math.BigDecimal getAvailableFlg() {
		return availableFlg;
	}

	/**
	 * availableFlgを設定します。
	 * @param availableFlg availableFlg
	 */
	public void setAvailableFlg(final java.math.BigDecimal availableFlg) {
		this.availableFlg = availableFlg;
	}

	/**
	 * countDivisionを取得します。
	 * @return countDivision
	 */
	public String getCountDivision() {
		return countDivision;
	}

	/**
	 * countDivisionを設定します。
	 * @param countDivision countDivision
	 */
	public void setCountDivision(final String countDivision) {
		this.countDivision = countDivision;
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
	 * locationLevelを取得します。
	 * @return locationLevel
	 */
	public java.math.BigDecimal getLocationLevel() {
		return locationLevel;
	}

	/**
	 * locationLevelを設定します。
	 * @param locationLevel locationLevel
	 */
	public void setLocationLevel(final java.math.BigDecimal locationLevel) {
		this.locationLevel = locationLevel;
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
	 * mrpTargetFlgを取得します。
	 * @return mrpTargetFlg
	 */
	public java.math.BigDecimal getMrpTargetFlg() {
		return mrpTargetFlg;
	}

	/**
	 * mrpTargetFlgを設定します。
	 * @param mrpTargetFlg mrpTargetFlg
	 */
	public void setMrpTargetFlg(final java.math.BigDecimal mrpTargetFlg) {
		this.mrpTargetFlg = mrpTargetFlg;
	}

	/**
	 * newFlgを取得します。
	 * @return newFlg
	 */
	public String getNewFlg() {
		return newFlg;
	}

	/**
	 * newFlgを設定します。
	 * @param newFlg newFlg
	 */
	public void setNewFlg(final String newFlg) {
		this.newFlg = newFlg;
	}

	/**
	 * possibleWeightを取得します。
	 * @return possibleWeight
	 */
	public java.math.BigDecimal getPossibleWeight() {
		return possibleWeight;
	}

	/**
	 * possibleWeightを設定します。
	 * @param possibleWeight possibleWeight
	 */
	public void setPossibleWeight(final java.math.BigDecimal possibleWeight) {
		this.possibleWeight = possibleWeight;
	}

	/**
	 * tantoCdを取得します。
	 * @return tantoCd
	 */
	public String getTantoCd() {
		return tantoCd;
	}

	/**
	 * tantoCdを設定します。
	 * @param tantoCd tantoCd
	 */
	public void setTantoCd(final String tantoCd) {
		this.tantoCd = tantoCd;
	}

	/**
	 * sectionCdを取得します。
	 * @return sectionCd
	 */
	public String getSectionCd() {
		return sectionCd;
	}

	/**
	 * sectionCdを設定します。
	 * @param sectionCd sectionCd
	 */
	public void setSectionCd(final String sectionCd) {
		this.sectionCd = sectionCd;
	}

	/**
	 * sectionNameを取得します。
	 * @return sectionName
	 */
	public String getSectionName() {
		return sectionName;
	}

	/**
	 * sectionNameを設定します。
	 * @param sectionName sectionName
	 */
	public void setSectionName(final String sectionName) {
		this.sectionName = sectionName;
	}

	/**
	 * stockDivisionを取得します。
	 * @return stockDivision
	 */
	public java.math.BigDecimal getStockDivision() {
		return stockDivision;
	}

	/**
	 * stockDivisionを設定します。
	 * @param stockDivision stockDivision
	 */
	public void setStockDivision(final java.math.BigDecimal stockDivision) {
		this.stockDivision = stockDivision;
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
	 * upperLocationCdを取得します。
	 * @return upperLocationCd
	 */
	public String getUpperLocationCd() {
		return upperLocationCd;
	}

	/**
	 * upperLocationCdを設定します。
	 * @param upperLocationCd upperLocationCd
	 */
	public void setUpperLocationCd(final String upperLocationCd) {
		this.upperLocationCd = upperLocationCd;
	}

	/**
	 * upperLocationNameを取得します。
	 * @return upperLocationName
	 */
	public String getUpperLocationName() {
		return upperLocationName;
	}

	/**
	 * upperLocationNameを設定します。
	 * @param upperLocationName upperLocationName
	 */
	public void setUpperLocationName(final String upperLocationName) {
		this.upperLocationName = upperLocationName;
	}

	/**
	 * countDivisionLabelsを取得します。
	 * @return countDivisionLabels
	 */
	public String[] getCountDivisionLabels() {
		return countDivisionLabels;
	}

	/**
	 * countDivisionLabelsを設定します。
	 * @param countDivisionLabels countDivisionLabels
	 */
	public void setCountDivisionLabels(final String[] countDivisionLabels) {
		this.countDivisionLabels = countDivisionLabels;
	}

	/**
	 * countDivisionValuesを取得します。
	 * @return countDivisionValues
	 */
	public String[] getCountDivisionValues() {
		return countDivisionValues;
	}

	/**
	 * countDivisionValuesを設定します。
	 * @param countDivisionValues countDivisionValues
	 */
	public void setCountDivisionValues(final String[] countDivisionValues) {
		this.countDivisionValues = countDivisionValues;
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
	 * strPossibleWeightを取得します。
	 * @return strPossibleWeight
	 */
	public String getStrPossibleWeight() {
		return strPossibleWeight;
	}

	/**
	 * strPossibleWeightを設定します。
	 * @param strPossibleWeight strPossibleWeight
	 */
	public void setStrPossibleWeight(final String strPossibleWeight) {
		this.strPossibleWeight = strPossibleWeight;
	}

	/**
	 * tantoNmを取得します。
	 * @return tantoNm
	 */
	public String getTantoNm() {
		return tantoNm;
	}

	/**
	 * tantoNmを設定します。
	 * @param tantoNm tantoNm
	 */
	public void setTantoNm(final String tantoNm) {
		this.tantoNm = tantoNm;
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
}
