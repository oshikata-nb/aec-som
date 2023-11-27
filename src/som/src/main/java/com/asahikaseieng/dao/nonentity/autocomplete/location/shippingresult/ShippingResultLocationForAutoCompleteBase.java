/*
 * Created on 2009/03/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.location.shippingresult;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * ShippingResultLocationListForAutoCompleteクラス.
 * @author tosco
 */
public class ShippingResultLocationForAutoCompleteBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ShippingResultLocationForAutoCompleteBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション.*/
	public static final String TABLE = "LOCATION";

	/** COLUMNアノテーション locationCd */
	public static final String locationCd_COLUMN = "LOCATION_CD";

	/** COLUMNアノテーション locationName */
	public static final String locationName_COLUMN = "LOCATION_NAME";

	/** COLUMNアノテーション upperLocationCd */
	public static final String upperLocationCd_COLUMN = "UPPER_LOCATION_CD";

	/** COLUMNアノテーション locationLevel */
	public static final String locationLevel_COLUMN = "LOCATION_LEVEL";

	/** COLUMNアノテーション possibleWeight */
	public static final String possibleWeight_COLUMN = "POSSIBLE_WEIGHT";

	/** COLUMNアノテーション currentWeight */
	public static final String currentWeight_COLUMN = "CURRENT_WEIGHT";

	/** COLUMNアノテーション mrpTargetFlg */
	public static final String mrpTargetFlg_COLUMN = "MRP_TARGET_FLG";

	/** COLUMNアノテーション sectionCd */
	public static final String sectionCd_COLUMN = "SECTION_CD";

	/** COLUMNアノテーション tantoCd */
	public static final String tantoCd_COLUMN = "TANTO_CD";

	/** COLUMNアノテーション stockDivision */
	public static final String stockDivision_COLUMN = "STOCK_DIVISION";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション availableFlg */
	public static final String availableFlg_COLUMN = "AVAILABLE_FLG";

	/** COLUMNアノテーション countDivision */
	public static final String countDivision_COLUMN = "COUNT_DIVISION";

	//
	// インスタンスフィールド
	//

	/** ロケーションコード */
	private String locationCd;

	/** ロケーション名称 */
	private String locationName;

	/** 上位ロケーションコード */
	private String upperLocationCd;

	/** レベル */
	private BigDecimal locationLevel;

	/** 可能重量 */
	private BigDecimal possibleWeight;

	/** 現在重量 */
	private BigDecimal currentWeight;

	/** ＭＲＰ対象ＦＬＧ */
	private BigDecimal mrpTargetFlg;

	/** 部門コード */
	private String sectionCd;

	/** 営業担当者コード */
	private String tantoCd;

	/** 在庫区分 */
	private BigDecimal stockDivision;

	/** 登録日時 */
	private Timestamp inputDate;

	/** 登録者ID */
	private String inputorCd;

	/** 更新日時 */
	private Timestamp updateDate;

	/** 更新者ID */
	private String updatorCd;

	/** 在庫可能ＦＬＧ */
	private BigDecimal availableFlg;

	/** 棚卸区分 */
	private String countDivision;

	//
	// インスタンスメソッド
	//

	/**
	 * ロケーションコード取得
	 * @return String ロケーションコード
	*/
	public String getLocationCd() {
		return this.locationCd;
	}

	/**
	 * ロケーションコード設定
	 * @param locationCd ロケーションコード
	*/
	public void setLocationCd(final String locationCd) {
		this.locationCd = locationCd;
	}

	/**
	 * ロケーション名称取得
	 * @return String ロケーション名称
	*/
	public String getLocationName() {
		return this.locationName;
	}

	/**
	 * ロケーション名称設定
	 * @param locationName ロケーション名称
	*/
	public void setLocationName(final String locationName) {
		this.locationName = locationName;
	}

	/**
	 * 上位ロケーションコード取得
	 * @return String 上位ロケーションコード
	*/
	public String getUpperLocationCd() {
		return this.upperLocationCd;
	}

	/**
	 * 上位ロケーションコード設定
	 * @param upperLocationCd 上位ロケーションコード
	*/
	public void setUpperLocationCd(final String upperLocationCd) {
		this.upperLocationCd = upperLocationCd;
	}

	/**
	 * レベル取得
	 * @return BigDecimal レベル
	*/
	public BigDecimal getLocationLevel() {
		return this.locationLevel;
	}

	/**
	 * レベル設定
	 * @param locationLevel レベル
	*/
	public void setLocationLevel(final BigDecimal locationLevel) {
		this.locationLevel = locationLevel;
	}

	/**
	 * 可能重量取得
	 * @return BigDecimal 可能重量
	*/
	public BigDecimal getPossibleWeight() {
		return this.possibleWeight;
	}

	/**
	 * 可能重量設定
	 * @param possibleWeight 可能重量
	*/
	public void setPossibleWeight(final BigDecimal possibleWeight) {
		this.possibleWeight = possibleWeight;
	}

	/**
	 * 現在重量取得
	 * @return BigDecimal 現在重量
	*/
	public BigDecimal getCurrentWeight() {
		return this.currentWeight;
	}

	/**
	 * 現在重量設定
	 * @param currentWeight 現在重量
	*/
	public void setCurrentWeight(final BigDecimal currentWeight) {
		this.currentWeight = currentWeight;
	}

	/**
	 * ＭＲＰ対象ＦＬＧ取得
	 * @return BigDecimal ＭＲＰ対象ＦＬＧ
	*/
	public BigDecimal getMrpTargetFlg() {
		return this.mrpTargetFlg;
	}

	/**
	 * ＭＲＰ対象ＦＬＧ設定
	 * @param mrpTargetFlg ＭＲＰ対象ＦＬＧ
	*/
	public void setMrpTargetFlg(final BigDecimal mrpTargetFlg) {
		this.mrpTargetFlg = mrpTargetFlg;
	}

	/**
	 * 部門コード取得
	 * @return String 部門コード
	*/
	public String getSectionCd() {
		return this.sectionCd;
	}

	/**
	 * 部門コード設定
	 * @param sectionCd 部門コード
	*/
	public void setSectionCd(final String sectionCd) {
		this.sectionCd = sectionCd;
	}

	/**
	 * 営業担当者コード取得
	 * @return String 営業担当者コード
	*/
	public String getTantoCd() {
		return this.tantoCd;
	}

	/**
	 * 営業担当者コード設定
	 * @param tantoCd 営業担当者コード
	*/
	public void setTantoCd(final String tantoCd) {
		this.tantoCd = tantoCd;
	}

	/**
	 * 在庫区分取得
	 * @return BigDecimal 在庫区分
	*/
	public BigDecimal getStockDivision() {
		return this.stockDivision;
	}

	/**
	 * 在庫区分設定
	 * @param stockDivision 在庫区分
	*/
	public void setStockDivision(final BigDecimal stockDivision) {
		this.stockDivision = stockDivision;
	}

	/**
	 * 登録日時取得
	 * @return Timestamp 登録日時
	*/
	public Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * 登録日時設定
	 * @param inputDate 登録日時
	*/
	public void setInputDate(final Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * 登録者ID取得
	 * @return String 登録者ID
	*/
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * 登録者ID設定
	 * @param inputorCd 登録者ID
	*/
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * 更新日時取得
	 * @return Timestamp 更新日時
	*/
	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * 更新日時設定
	 * @param updateDate 更新日時
	*/
	public void setUpdateDate(final Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * 更新者ID取得
	 * @return String 更新者ID
	*/
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * 更新者ID設定
	 * @param updatorCd 更新者ID
	*/
	public void setUpdatorCd(final String updatorCd) {
		this.updatorCd = updatorCd;
	}

	/**
	 * 在庫可能ＦＬＧ取得
	 * @return BigDecimal 在庫可能ＦＬＧ
	*/
	public BigDecimal getAvailableFlg() {
		return this.availableFlg;
	}

	/**
	 * 在庫可能ＦＬＧ設定
	 * @param availableFlg 在庫可能ＦＬＧ
	*/
	public void setAvailableFlg(final BigDecimal availableFlg) {
		this.availableFlg = availableFlg;
	}

	/**
	 * 棚卸区分取得
	 * @return String 棚卸区分
	*/
	public String getCountDivision() {
		return this.countDivision;
	}

	/**
	 * 棚卸区分設定
	 * @param countDivision 棚卸区分
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

