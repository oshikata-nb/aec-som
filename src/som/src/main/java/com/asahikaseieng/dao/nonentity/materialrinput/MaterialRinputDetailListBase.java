/*
 * Created on 2009/03/26
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
 * 外注原材料投入実績入力画面_明細部表示用データ格納クラス.
 * 
 * @author tosco
 */
public class MaterialRinputDetailListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public MaterialRinputDetailListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション buySubcontractOrderNo. */
	public static final String buySubcontractOrderNo_COLUMN = "BUY_SUBCONTRACT_ORDER_NO";

	/** COLUMNアノテーション recipeId. */
	public static final String recipeId_COLUMN = "RECIPE_ID";

	/** COLUMNアノテーション recipeCd. */
	public static final String recipeCd_COLUMN = "RECIPE_CD";

	/** COLUMNアノテーション recipeVersion. */
	public static final String recipeVersion_COLUMN = "RECIPE_VERSION";

	/** COLUMNアノテーション stepNo. */
	public static final String stepNo_COLUMN = "STEP_NO";

	/** COLUMNアノテーション lineNo. */
	public static final String lineNo_COLUMN = "LINE_NO";

	/** COLUMNアノテーション seq. */
	public static final String seq_COLUMN = "SEQ";

	/** COLUMNアノテーション lotNo. */
	public static final String lotNo_COLUMN = "LOT_NO";

	/** COLUMNアノテーション locationCd. */
	public static final String locationCd_COLUMN = "LOCATION_CD";

	/** COLUMNアノテーション manufacturerLotNo. */
	public static final String manufacturerLotNo_COLUMN = "MANUFACTURER_LOT_NO";

	/** COLUMNアノテーション itemCd. */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション qty. */
	public static final String qty_COLUMN = "QTY";

	/** COLUMNアノテーション resultQty. */
	public static final String resultQty_COLUMN = "RESULT_QTY";

	/** COLUMNアノテーション sampleQty. */
	public static final String sampleQty_COLUMN = "SAMPLE_QTY";

	/** COLUMNアノテーション lossQty. */
	public static final String lossQty_COLUMN = "LOSS_QTY";

	/** COLUMNアノテーション defectQty. */
	public static final String defectQty_COLUMN = "DEFECT_QTY";

	/** COLUMNアノテーション adjustQty. */
	public static final String adjustQty_COLUMN = "ADJUST_QTY";

	//
	// インスタンスフィールド
	//

	/** 発注番号 */
	private String buySubcontractOrderNo;

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

	/** SEQ */
	private BigDecimal seq;

	/** ロット番号 */
	private String lotNo;

	/** ロケーションコード */
	private String locationCd;

	/** メーカーロット番号 */
	private String manufacturerLotNo;

	/** 品目コード */
	private String itemCd;

	/** 数量 */
	private BigDecimal qty;

	/** 実績数量 */
	private BigDecimal resultQty;

	/** サンプル */
	private BigDecimal sampleQty;

	/** ロス数量 */
	private BigDecimal lossQty;

	/** 不良数量 */
	private BigDecimal defectQty;

	/** 調整数量 */
	private BigDecimal adjustQty;

	//
	// インスタンスメソッド
	//

	/**
	 * 発注番号取得
	 * @return String 発注番号
	 */
	public String getBuySubcontractOrderNo() {
		return this.buySubcontractOrderNo;
	}

	/**
	 * 発注番号設定
	 * @param buySubcontractOrderNo 発注番号
	 */
	public void setBuySubcontractOrderNo(final String buySubcontractOrderNo) {
		this.buySubcontractOrderNo = buySubcontractOrderNo;
	}

	/**
	 * レシピインデックス取得
	 * @return BigDecimal レシピインデックス
	 */
	public BigDecimal getRecipeId() {
		return recipeId;
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
		return stepNo;
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
		return lineNo;
	}

	/**
	 * LINE_NO設定
	 * @param lineNo LINE_NO
	 */
	public void setLineNo(final BigDecimal lineNo) {
		this.lineNo = lineNo;
	}

	/**
	 * SEQ取得.
	 * @return BigDecimal SEQ
	 */
	public BigDecimal getSeq() {
		return seq;
	}

	/**
	 * SEQ設定.
	 * @param seq SEQ
	 */
	public void setSeq(final BigDecimal seq) {
		this.seq = seq;
	}

	/**
	 * ロット番号取得.
	 * @return String ロット番号
	 */
	public String getLotNo() {
		return lotNo;
	}

	/**
	 * ロット番号設定.
	 * @param lotNo ロット番号
	 */
	public void setLotNo(final String lotNo) {
		this.lotNo = lotNo;
	}

	/**
	 * ロケーションコード取得.
	 * @return String ロケーションコード
	 */
	public String getLocationCd() {
		return locationCd;
	}

	/**
	 * ロケーションコード設定.
	 * @param locationCd ロケーションコード
	 */
	public void setLocationCd(final String locationCd) {
		this.locationCd = locationCd;
	}

	/**
	 * メーカーロット番号取得.
	 * @return String メーカーロット番号
	 */
	public String getManufacturerLotNo() {
		return manufacturerLotNo;
	}

	/**
	 * メーカーロット番号設定.
	 * @param manufacturerLotNo メーカーロット番号
	 */
	public void setManufacturerLotNo(final String manufacturerLotNo) {
		this.manufacturerLotNo = manufacturerLotNo;
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
	 * 数量取得.
	 * @return BigDecimal 数量
	 */
	public BigDecimal getQty() {
		return qty;
	}

	/**
	 * 数量設定.
	 * @param qty 数量
	 */
	public void setQty(final BigDecimal qty) {
		this.qty = qty;
	}

	/**
	 * 実績数量取得.
	 * @return BigDecimal 実績数量
	 */
	public BigDecimal getResultQty() {
		return this.resultQty;
	}

	/**
	 * 実績数量設定.
	 * @param resultQty 実績数量
	 */
	public void setResultQty(final BigDecimal resultQty) {
		this.resultQty = resultQty;
	}

	/**
	 * サンプル取得.
	 * @return BigDecimal サンプル
	 */
	public BigDecimal getSampleQty() {
		return this.sampleQty;
	}

	/**
	 * サンプル設定.
	 * @param sampleQty サンプル
	 */
	public void setSampleQty(final BigDecimal sampleQty) {
		this.sampleQty = sampleQty;
	}

	/**
	 * ロス数量取得.
	 * @return BigDecimal ロス数量
	 */
	public BigDecimal getLossQty() {
		return this.lossQty;
	}

	/**
	 * ロス数量設定.
	 * @param lossQty ロス数量
	 */
	public void setLossQty(final BigDecimal lossQty) {
		this.lossQty = lossQty;
	}

	/**
	 * 不良数量取得.
	 * @return BigDecimal 不良数量
	 */
	public BigDecimal getDefectQty() {
		return this.defectQty;
	}

	/**
	 * 不良数量設定.
	 * @param defectQty 不良数量
	 */
	public void setDefectQty(final BigDecimal defectQty) {
		this.defectQty = defectQty;
	}

	/**
	 * 調整数量取得.
	 * @return BigDecimal 調整数量
	 */
	public BigDecimal getAdjustQty() {
		return this.adjustQty;
	}

	/**
	 * 調整数量設定.
	 * @param adjustQty 調整数量
	 */
	public void setAdjustQty(final BigDecimal adjustQty) {
		this.adjustQty = adjustQty;
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
