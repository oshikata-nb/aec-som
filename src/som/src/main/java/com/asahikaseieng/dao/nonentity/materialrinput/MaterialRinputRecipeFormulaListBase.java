/*
 * Created on 2009/03/30
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.materialrinput;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 外注原材料投入実績入力 処方フォーミュラデータ格納クラス.
 *
 * @author tosco
 */
public class MaterialRinputRecipeFormulaListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public MaterialRinputRecipeFormulaListBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション.*/
	public static final String TABLE = "RECIPE_FORMULA";

	/** COLUMNアノテーション recipeId */
	public static final String recipeId_COLUMN = "RECIPE_ID";

	/** COLUMNアノテーション recipeCd. */
	public static final String recipeCd_COLUMN = "RECIPE_CD";

	/** COLUMNアノテーション recipeVersion. */
	public static final String recipeVersion_COLUMN = "RECIPE_VERSION";

	/** COLUMNアノテーション stepNo */
	public static final String stepNo_COLUMN = "STEP_NO";

	/** COLUMNアノテーション lineNo */
	public static final String lineNo_COLUMN = "LINE_NO";

	/** COLUMNアノテーション lineType */
	public static final String lineType_COLUMN = "LINE_TYPE";

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション qty */
	public static final String qty_COLUMN = "QTY";

	//
	// インスタンスフィールド
	//

	/** レシピインデックス */
	private BigDecimal recipeId;

	/** レシピコード */
	private String recipeCd;

	/** レシピバージョン */
	private BigDecimal recipeVersion;

	/** STEP_NO */
	private BigDecimal stepNo;

	/** LINE_NO */
	private BigDecimal lineNo;

	/** -1:原材料,1:中間品,2:回収品,3:製品,半製品,4:副生品,5:廃棄物 */
	private BigDecimal lineType;

	/** 品目コード */
	private String itemCd;

	/** 数量 */
	private BigDecimal qty;

	//
	// インスタンスメソッド
	//

	/**
	 * レシピインデックス取得
	 * @return BigDecimal レシピインデックス
	*/
	public BigDecimal getRecipeId() {
		return this.recipeId;
	}

	/**
	 * レシピインデックス設定
	 * @param recipeId レシピインデックス
	*/
	public void setRecipeId(final BigDecimal recipeId) {
		this.recipeId = recipeId;
	}

	/**
	 * レシピコード取得
	 * @return String レシピコード
	 */
	public String getRecipeCd() {
		return recipeCd;
	}

	/**
	 * レシピコード設定
	 * @param recipeCd レシピコード
	 */
	public void setRecipeCd(final String recipeCd) {
		this.recipeCd = recipeCd;
	}

	/**
	 * レシピバージョン取得
	 * @return BigDecimal レシピバージョン
	 */
	public BigDecimal getRecipeVersion() {
		return recipeVersion;
	}

	/**
	 * レシピバージョン設定
	 * @param recipeVersion レシピバージョン
	 */
	public void setRecipeVersion(final BigDecimal recipeVersion) {
		this.recipeVersion = recipeVersion;
	}

	/**
	 * STEP_NO取得
	 * @return BigDecimal STEP_NO
	*/
	public BigDecimal getStepNo() {
		return this.stepNo;
	}

	/**
	 * STEP_NO設定
	 * @param stepNo STEP_NO
	*/
	public void setStepNo(final BigDecimal stepNo) {
		this.stepNo = stepNo;
	}

	/**
	 * LINE_NO取得
	 * @return BigDecimal LINE_NO
	*/
	public BigDecimal getLineNo() {
		return this.lineNo;
	}

	/**
	 * LINE_NO設定
	 * @param lineNo LINE_NO
	*/
	public void setLineNo(final BigDecimal lineNo) {
		this.lineNo = lineNo;
	}

	/**
	 * -1:原材料,1:中間品,2:回収品,3:製品,半製品,4:副生品,5:廃棄物取得
	 * @return BigDecimal -1:原材料,1:中間品,2:回収品,3:製品,半製品,4:副生品,5:廃棄物
	*/
	public BigDecimal getLineType() {
		return this.lineType;
	}

	/**
	 * -1:原材料,1:中間品,2:回収品,3:製品,半製品,4:副生品,5:廃棄物設定
	 * @param lineType -1:原材料,1:中間品,2:回収品,3:製品,半製品,4:副生品,5:廃棄物
	*/
	public void setLineType(final BigDecimal lineType) {
		this.lineType = lineType;
	}

	/**
	 * 品目コード取得
	 * @return String 品目コード
	*/
	public String getItemCd() {
		return this.itemCd;
	}

	/**
	 * 品目コード設定
	 * @param itemCd 品目コード
	*/
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * 数量取得
	 * @return BigDecimal 数量
	*/
	public BigDecimal getQty() {
		return this.qty;
	}

	/**
	 * 数量設定
	 * @param qty 数量
	*/
	public void setQty(final BigDecimal qty) {
		this.qty = qty;
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
