/* 注意：table2daoで作成されました。
 *　     編集しないでください。table2daoで上書されます。
 * Created on Tue May 26 16:27:35 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.location;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * LocationBaseクラス.
 * @author t0011036
 */
public class LocationBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public LocationBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "LOCATION";


//	/** IDアノテーション locationCd. */
//	public static final String locationCd_ID = "assigned";

	/** COLUMNアノテーション locationCd. */
	public static final String locationCd_COLUMN = "LOCATION_CD";

	/** COLUMNアノテーション locationName. */
	public static final String locationName_COLUMN = "LOCATION_NAME";

	/** COLUMNアノテーション upperLocationCd. */
	public static final String upperLocationCd_COLUMN = "UPPER_LOCATION_CD";

	/** COLUMNアノテーション locationLevel. */
	public static final String locationLevel_COLUMN = "LOCATION_LEVEL";

	/** COLUMNアノテーション possibleWeight. */
	public static final String possibleWeight_COLUMN = "POSSIBLE_WEIGHT";

	/** COLUMNアノテーション currentWeight. */
	public static final String currentWeight_COLUMN = "CURRENT_WEIGHT";

	/** COLUMNアノテーション mrpTargetFlg. */
	public static final String mrpTargetFlg_COLUMN = "MRP_TARGET_FLG";

	/** COLUMNアノテーション sectionCd. */
	public static final String sectionCd_COLUMN = "SECTION_CD";

	/** COLUMNアノテーション tantoCd. */
	public static final String tantoCd_COLUMN = "TANTO_CD";

	/** COLUMNアノテーション stockDivision. */
	public static final String stockDivision_COLUMN = "STOCK_DIVISION";

	/** COLUMNアノテーション itemCd. */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション inputDate. */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd. */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate. */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd. */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション availableFlg. */
	public static final String availableFlg_COLUMN = "AVAILABLE_FLG";

	/** COLUMNアノテーション countDivision. */
	public static final String countDivision_COLUMN = "COUNT_DIVISION";

	//
	// インスタンスフィールド
	//

	private String locationCd;

	private String locationName;

	private String upperLocationCd;

	private java.math.BigDecimal locationLevel;

	private java.math.BigDecimal possibleWeight;

	private java.math.BigDecimal currentWeight;

	private java.math.BigDecimal mrpTargetFlg;

	private String sectionCd;

	private String tantoCd;

	private java.math.BigDecimal stockDivision;

	private String itemCd;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	private java.math.BigDecimal availableFlg;

	private String countDivision;

	//
	// インスタンスメソッド
	//

	/**
	 * locationCd取得.
	 * @return locationCd
	 */
	public String getLocationCd() {
		return this.locationCd;
	}

	/**
	 * locationCd設定.
	 * @param locationCd locationCd
	 */
	public void setLocationCd(final String locationCd) {
		this.locationCd = locationCd;
	}

	/**
	 * locationName取得.
	 * @return locationName
	 */
	public String getLocationName() {
		return this.locationName;
	}

	/**
	 * locationName設定.
	 * @param locationName locationName
	 */
	public void setLocationName(final String locationName) {
		this.locationName = locationName;
	}

	/**
	 * upperLocationCd取得.
	 * @return upperLocationCd
	 */
	public String getUpperLocationCd() {
		return this.upperLocationCd;
	}

	/**
	 * upperLocationCd設定.
	 * @param upperLocationCd upperLocationCd
	 */
	public void setUpperLocationCd(final String upperLocationCd) {
		this.upperLocationCd = upperLocationCd;
	}

	/**
	 * locationLevel取得.
	 * @return locationLevel
	 */
	public java.math.BigDecimal getLocationLevel() {
		return this.locationLevel;
	}

	/**
	 * locationLevel設定.
	 * @param locationLevel locationLevel
	 */
	public void setLocationLevel(final java.math.BigDecimal locationLevel) {
		this.locationLevel = locationLevel;
	}

	/**
	 * possibleWeight取得.
	 * @return possibleWeight
	 */
	public java.math.BigDecimal getPossibleWeight() {
		return this.possibleWeight;
	}

	/**
	 * possibleWeight設定.
	 * @param possibleWeight possibleWeight
	 */
	public void setPossibleWeight(final java.math.BigDecimal possibleWeight) {
		this.possibleWeight = possibleWeight;
	}

	/**
	 * currentWeight取得.
	 * @return currentWeight
	 */
	public java.math.BigDecimal getCurrentWeight() {
		return this.currentWeight;
	}

	/**
	 * currentWeight設定.
	 * @param currentWeight currentWeight
	 */
	public void setCurrentWeight(final java.math.BigDecimal currentWeight) {
		this.currentWeight = currentWeight;
	}

	/**
	 * mrpTargetFlg取得.
	 * @return mrpTargetFlg
	 */
	public java.math.BigDecimal getMrpTargetFlg() {
		return this.mrpTargetFlg;
	}

	/**
	 * mrpTargetFlg設定.
	 * @param mrpTargetFlg mrpTargetFlg
	 */
	public void setMrpTargetFlg(final java.math.BigDecimal mrpTargetFlg) {
		this.mrpTargetFlg = mrpTargetFlg;
	}

	/**
	 * sectionCd取得.
	 * @return sectionCd
	 */
	public String getSectionCd() {
		return this.sectionCd;
	}

	/**
	 * sectionCd設定.
	 * @param sectionCd sectionCd
	 */
	public void setSectionCd(final String sectionCd) {
		this.sectionCd = sectionCd;
	}

	/**
	 * tantoCd取得.
	 * @return tantoCd
	 */
	public String getTantoCd() {
		return this.tantoCd;
	}

	/**
	 * tantoCd設定.
	 * @param tantoCd tantoCd
	 */
	public void setTantoCd(final String tantoCd) {
		this.tantoCd = tantoCd;
	}

	/**
	 * stockDivision取得.
	 * @return stockDivision
	 */
	public java.math.BigDecimal getStockDivision() {
		return this.stockDivision;
	}

	/**
	 * stockDivision設定.
	 * @param stockDivision stockDivision
	 */
	public void setStockDivision(final java.math.BigDecimal stockDivision) {
		this.stockDivision = stockDivision;
	}

	/**
	 * itemCd取得.
	 * @return itemCd
	 */
	public String getItemCd() {
		return this.itemCd;
	}

	/**
	 * itemCd設定.
	 * @param itemCd itemCd
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * inputDate取得.
	 * @return inputDate
	 */
	public java.sql.Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * inputDate設定.
	 * @param inputDate inputDate
	 */
	public void setInputDate(final java.sql.Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * inputorCd取得.
	 * @return inputorCd
	 */
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * inputorCd設定.
	 * @param inputorCd inputorCd
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * updateDate取得.
	 * @return updateDate
	 */
	public java.sql.Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * updateDate設定.
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * updatorCd取得.
	 * @return updatorCd
	 */
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * updatorCd設定.
	 * @param updatorCd updatorCd
	 */
	public void setUpdatorCd(final String updatorCd) {
		this.updatorCd = updatorCd;
	}

	/**
	 * availableFlg取得.
	 * @return availableFlg
	 */
	public java.math.BigDecimal getAvailableFlg() {
		return this.availableFlg;
	}

	/**
	 * availableFlg設定.
	 * @param availableFlg availableFlg
	 */
	public void setAvailableFlg(final java.math.BigDecimal availableFlg) {
		this.availableFlg = availableFlg;
	}

	/**
	 * countDivision取得.
	 * @return countDivision
	 */
	public String getCountDivision() {
		return this.countDivision;
	}

	/**
	 * countDivision設定.
	 * @param countDivision countDivision
	 */
	public void setCountDivision(final String countDivision) {
		this.countDivision = countDivision;
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
