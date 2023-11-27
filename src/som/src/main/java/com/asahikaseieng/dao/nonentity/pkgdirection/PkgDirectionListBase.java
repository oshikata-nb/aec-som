/*
 * Created on 2009/02/10
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.pkgdirection;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 包装指図－検索画面データ格納クラス.
 *
 * @author tosco
 */
public class PkgDirectionListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public PkgDirectionListBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "DIRECTION_HEADER";

	/** COLUMNアノテーション directionDivision. */
	public static final String directionDivision_COLUMN = "DIRECTION_DIVISION";

	/** COLUMNアノテーション directionNo. */
	public static final String directionNo_COLUMN = "DIRECTION_NO";

	/** COLUMNアノテーション directionStatus. */
	public static final String directionStatus_COLUMN = "DIRECTION_STATUS";

	/** COLUMNアノテーション recipeId. */
	public static final String recipeId_COLUMN = "RECIPE_ID";

	/** COLUMNアノテーション recipeCd. */
	public static final String recipeCd_COLUMN = "RECIPE_CD";

	/** COLUMNアノテーション recipeVersion. */
	public static final String recipeVersion_COLUMN = "RECIPE_VERSION";

	/** COLUMNアノテーション productionLine. */
	public static final String productionLine_COLUMN = "PRODUCTION_LINE";

	/** COLUMNアノテーション filltankNo. */
	public static final String filltankNo_COLUMN = "FILLTANK_NO";

	/** COLUMNアノテーション packageLine. */
	public static final String packageLine_COLUMN = "PACKAGE_LINE";

	/** COLUMNアノテーション itemCd. */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション planedQty. */
	public static final String planedQty_COLUMN = "PLANED_QTY";

	/** COLUMNアノテーション lotNo. */
	public static final String lotNo_COLUMN = "LOT_NO";

	/** COLUMNアノテーション planedSdate. */
	public static final String planedSdate_COLUMN = "PLANED_SDATE";

	/** COLUMNアノテーション planedEdate. */
	public static final String planedEdate_COLUMN = "PLANED_EDATE";

	/** COLUMNアノテーション productLabelFlag. */
	public static final String productLabelFlag_COLUMN = "PRODUCT_LABEL_FLAG";

	/** COLUMNアノテーション updateDate. */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd. */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	//
	// インスタンスフィールド
	//

	/** 指図区分 */
	private BigDecimal directionDivision;

	/** 指図番号 */
	private String directionNo;

	/** 指図ステータス */
	private BigDecimal directionStatus;

	/** レシピインデックス */
	private BigDecimal recipeId;

	/** レシピコード */
	private String recipeCd;

	/** レシピバージョン */
	private BigDecimal recipeVersion;

	/** 生産ライン */
	private String productionLine;

	/** 充填タンクNO */
	private String filltankNo;

	/** 包装ライン */
	private String packageLine;

	/** 主要製品コード */
	private String itemCd;

	/** 予定生産量 */
	private BigDecimal planedQty;

	/** ロットNo */
	private String lotNo;

	/** 開始予定日時 */
	private Timestamp planedSdate;

	/** 終了予定日時 */
	private Timestamp planedEdate;

	/** 製品ラベル発行フラグ|0:未発行,1:発行 */
	private BigDecimal productLabelFlag;

	/** 更新者ID */
	private String updatorCd;

	/** 更新日時 */
	private Timestamp updateDate;

	//
	// インスタンスメソッド
	//

	/**
	 * 指図区分取得.
	 * @return BigDecimal 指図区分
	 */
	public BigDecimal getDirectionDivision() {
		return this.directionDivision;
	}

	/**
	 * 指図区分設定.
	 * @param directionDivision 指図区分
	 */
	public void setDirectionDivision(final BigDecimal directionDivision) {
		this.directionDivision = directionDivision;
	}

	/**
	 * 指図番号取得.
	 * @return String 指図番号
	 */
	public String getDirectionNo() {
		return this.directionNo;
	}

	/**
	 * 指図番号設定.
	 * @param directionNo 指図番号
	 */
	public void setDirectionNo(final String directionNo) {
		this.directionNo = directionNo;
	}

	/**
	 * 指図ステータス取得.
	 * @return BigDecimal 指図ステータス
	 */
	public BigDecimal getDirectionStatus() {
		return this.directionStatus;
	}

	/**
	 * 指図ステータス設定.
	 * @param directionStatus 指図ステータス
	 */
	public void setDirectionStatus(final BigDecimal directionStatus) {
		this.directionStatus = directionStatus;
	}

	/**
	 * レシピインデックス取得.
	 * @return BigDecimal レシピインデックス
	*/
	public BigDecimal getRecipeId() {
		return this.recipeId;
	}

	/**
	 * レシピインデックス設定.
	 * @param recipeId レシピインデックス
	*/
	public void setRecipeId(final BigDecimal recipeId) {
		this.recipeId = recipeId;
	}

	/**
	 * レシピコード取得.
	 * @return String レシピコード
	*/
	public String getRecipeCd() {
		return this.recipeCd;
	}

	/**
	 * レシピコード設定.
	 * @param recipeCd レシピコード
	*/
	public void setRecipeCd(final String recipeCd) {
		this.recipeCd = recipeCd;
	}

	/**
	 * レシピバージョン取得.
	 * @return BigDecimal レシピバージョン
	*/
	public BigDecimal getRecipeVersion() {
		return this.recipeVersion;
	}

	/**
	 * レシピバージョン設定.
	 * @param recipeVersion レシピバージョン
	*/
	public void setRecipeVersion(final BigDecimal recipeVersion) {
		this.recipeVersion = recipeVersion;
	}

	/**
	 * 生産ライン取得.
	 * @return String PR生産ライン
	*/
	public String getProductionLine() {
		return this.productionLine;
	}

	/**
	 * 生産ライン設定.
	 * @param productionLine PR生産ライン
	*/
	public void setProductionLine(final String productionLine) {
		this.productionLine = productionLine;
	}

	/**
	 * 充填タンクNO取得.
	 * @return String 充填タンクNO
	 */
	public String getFilltankNo() {
		return this.filltankNo;
	}

	/**
	 * 充填タンクNO設定.
	 * @param filltankNo 充填タンクNO
	 */
	public void setFilltankNo(final String filltankNo) {
		this.filltankNo = filltankNo;
	}

	/**
	 * 包装ライン取得.
	 * @return String 包装ライン
	 */
	public String getPackageLine() {
		return this.packageLine;
	}

	/**
	 * 包装ライン設定.
	 * @param packageLine 包装ライン
	 */
	public void setPackageLine(final String packageLine) {
		this.packageLine = packageLine;
	}

	/**
	 * 主要製品コード取得.
	 * @return String 主要製品コード
	 */
	public String getItemCd() {
		return this.itemCd;
	}

	/**
	 * 主要製品コード設定.
	 * @param itemCd 主要製品コード
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * 予定生産量取得.
	 * @return BigDecimal 予定生産量
	 */
	public BigDecimal getPlanedQty() {
		return this.planedQty;
	}

	/**
	 * 予定生産量設定.
	 * @param planedQty 予定生産量
	 */
	public void setPlanedQty(final java.math.BigDecimal planedQty) {
		this.planedQty = planedQty;
	}

	/**
	 * ロットNo取得.
	 * @return String ロットNo
	 */
	public String getLotNo() {
		return this.lotNo;
	}

	/**
	 * ロットNo設定.
	 * @param lotNo ロットNo
	 */
	public void setLotNo(final String lotNo) {
		this.lotNo = lotNo;
	}

	/**
	 * 開始予定日時取得.
	 * @return Timestamp 開始予定日時
	 */
	public java.sql.Timestamp getPlanedSdate() {
		return this.planedSdate;
	}

	/**
	 * 開始予定日時設定.
	 * @param planedSdate 開始予定日時
	 */
	public void setPlanedSdate(final java.sql.Timestamp planedSdate) {
		this.planedSdate = planedSdate;
	}

	/**
	 * 終了予定日時取得.
	 * @return Timestamp 終了予定日時
	 */
	public java.sql.Timestamp getPlanedEdate() {
		return this.planedEdate;
	}

	/**
	 * 終了予定日時設定.
	 * @param planedEdate 終了予定日時
	 */
	public void setPlanedEdate(final java.sql.Timestamp planedEdate) {
		this.planedEdate = planedEdate;
	}

	/**
	 * 製品ラベル発行フラグ取得.
	 * @return BigDecimal 製品ラベル発行フラグ
	 */
	public BigDecimal getProductLabelFlag() {
		return this.productLabelFlag;
	}

	/**
	 * 製品ラベル発行フラグ設定.
	 * @param productLabelFlag 製品ラベル発行フラグ
	 */
	public void setProductLabelFlag(final java.math.BigDecimal productLabelFlag) {
		this.productLabelFlag = productLabelFlag;
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
