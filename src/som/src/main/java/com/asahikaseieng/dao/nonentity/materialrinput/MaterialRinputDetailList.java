/*
 * Created on 2009/03/26
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.materialrinput;

import java.math.BigDecimal;

import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 外注原材料投入実績入力画面_明細部表示用データ格納クラス.
 *
 * @author tosco
 */
public class MaterialRinputDetailList extends MaterialRinputDetailListBase implements
		PropertyTransferCallbacker {


	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション unit */
	public static final String unit_COLUMN = "UNIT";

	/** COLUMNアノテーション recipeName */
	public static final String recipeName_COLUMN = "RECIPE_NAME";

	/** COLUMNアノテーション recipeUse */
	public static final String recipeUse_COLUMN = "RECIPE_USE";

	/** COLUMNアノテーション stockpdFlg */
	public static final String stockpdFlg_COLUMN = "STOCKPD_FLG";

	/** 品目名称 */
	private String itemName;

	/** 単位 */
	private String unit;

	/** 基本処方名称 */
	private String recipeName;

	/** 用途(3:製造,4:包装) */
	private BigDecimal recipeUse;

	/** 使用数(カンマ編集) */
	private String strQty;

	/** 使用量合計(カンマ編集) */
	private String strSumUseQty;

	/** 使用量合計デフォルト値(カンマ編集) */
	private String strSumUseQtyDefault;

	/** 実績数量(カンマ編集) */
	private String strResultQty;

	/** サンプル(カンマ編集) */
	private String strSampleQty;

	/** ロス数量(カンマ編集) */
	private String strLossQty;

	/** 不良(カンマ編集) */
	private String strDefectQty;

	/** 調整数量(カンマ編集) */
	private String strAdjustQty;

	/** 仕入先コード */
	private String venderCd;

	/** 在庫引落フラグ */
	private String stockpdFlg;

	/**
	 * コンストラクタ.
	 */
	public MaterialRinputDetailList() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
		setQty(AecNumberUtils.convertBigDecimal(getStrQty()));
		setResultQty(AecNumberUtils.convertBigDecimal(getStrResultQty()));
		setSampleQty(AecNumberUtils.convertBigDecimal(getStrSampleQty()));
		setLossQty(AecNumberUtils.convertBigDecimal(getStrLossQty()));
		setDefectQty(AecNumberUtils.convertBigDecimal(getStrDefectQty()));
		setAdjustQty(AecNumberUtils.convertBigDecimal(getStrAdjustQty()));
	}

	/* ---------- getter ,setter ---------- */

	/**
	 * 品目名称取得
	 * @return String 品目名称
	*/
	public String getItemName() {
		return itemName;
	}

	/**
	 * 品目名称設定
	 * @param itemName 品目名称
	*/
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 単位取得
	 * @return String 単位
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * 単位設定
	 * @param unit 単位
	 */
	public void setUnit(final String unit) {
		this.unit = unit;
	}

	/**
	 * 基本処方名称取得
	 * @return String 基本処方名称
	 */
	public String getRecipeName() {
		return recipeName;
	}

	/**
	 * 基本処方名称設定
	 * @param recipeName 基本処方名称
	 */
	public void setRecipeName(final String recipeName) {
		this.recipeName = recipeName;
	}

	/**
	 * 用途(3:製造,4:包装)取得
	 * @return BigDecimal 用途(3:製造,4:包装)
	 */
	public BigDecimal getRecipeUse() {
		return recipeUse;
	}

	/**
	 * 用途(3:製造,4:包装)設定
	 * @param recipeUse 用途(3:製造,4:包装)
	 */
	public void setRecipeUse(final BigDecimal recipeUse) {
		this.recipeUse = recipeUse;
	}

	/**
	 * 使用数(カンマ編集)取得
	 * @return String 使用数(カンマ編集)
	 */
	public String getStrQty() {
		return strQty;
	}

	/**
	 * 使用数(カンマ編集)設定
	 * @param strQty 使用数(カンマ編集)
	 */
	public void setStrQty(final String strQty) {
		this.strQty = strQty;
	}

	/**
	 * 使用量合計(カンマ編集)取得
	 * @return String 使用量合計(カンマ編集)
	 */
	public String getStrSumUseQty() {
		return strSumUseQty;
	}

	/**
	 * 使用量合計(カンマ編集)設定
	 * @param strSumUseQty 使用量合計(カンマ編集)
	 */
	public void setStrSumUseQty(final String strSumUseQty) {
		this.strSumUseQty = strSumUseQty;
	}

	/**
	 * 使用量合計デフォルト値(カンマ編集)取得
	 * @return String 使用量合計デフォルト値(カンマ編集)
	 */
	public String getStrSumUseQtyDefault() {
		return strSumUseQtyDefault;
	}

	/**
	 * 使用量合計デフォルト値(カンマ編集)設定
	 * @param strSumUseQtyDefault 使用量合計デフォルト値(カンマ編集)
	 */
	public void setStrSumUseQtyDefault(final String strSumUseQtyDefault) {
		this.strSumUseQtyDefault = strSumUseQtyDefault;
	}

	/**
	 * 実績数量(カンマ編集)取得
	 * @return String 実績数量(カンマ編集)
	 */
	public String getStrResultQty() {
		return strResultQty;
	}

	/**
	 * 実績数量(カンマ編集)設定
	 * @param strResultQty 実績数量(カンマ編集)
	 */
	public void setStrResultQty(final String strResultQty) {
		this.strResultQty = strResultQty;
	}

	/**
	 * サンプル(カンマ編集)取得
	 * @return String サンプル(カンマ編集)
	 */
	public String getStrSampleQty() {
		return strSampleQty;
	}

	/**
	 * サンプル(カンマ編集)設定
	 * @param strSampleQty サンプル(カンマ編集)
	 */
	public void setStrSampleQty(final String strSampleQty) {
		this.strSampleQty = strSampleQty;
	}

	/**
	 * ロス数量(カンマ編集)取得
	 * @return String ロス数量(カンマ編集)
	 */
	public String getStrLossQty() {
		return strLossQty;
	}

	/**
	 * ロス数量(カンマ編集)設定
	 * @param strLossQty ロス数量(カンマ編集)
	 */
	public void setStrLossQty(final String strLossQty) {
		this.strLossQty = strLossQty;
	}

	/**
	 * 不良(カンマ編集)取得
	 * @return String 不良(カンマ編集)
	 */
	public String getStrDefectQty() {
		return strDefectQty;
	}

	/**
	 * 不良(カンマ編集)設定
	 * @param strDefectQty 不良(カンマ編集)
	 */
	public void setStrDefectQty(final String strDefectQty) {
		this.strDefectQty = strDefectQty;
	}

	/**
	 * 調整数量(カンマ編集)取得
	 * @return String 調整数量(カンマ編集)
	 */
	public String getStrAdjustQty() {
		return strAdjustQty;
	}

	/**
	 * 調整数量(カンマ編集)設定
	 * @param strAdjustQty 調整数量(カンマ編集)
	 */
	public void setStrAdjustQty(final String strAdjustQty) {
		this.strAdjustQty = strAdjustQty;
	}

	/**
	 * 仕入先コード取得
	 * @return String 仕入先コード
	 */
	public String getVenderCd() {
		return venderCd;
	}

	/**
	 * 仕入先コード設定
	 * @param venderCd 仕入先コード
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * 在庫引落フラグ取得
	 * @return String 在庫引落フラグ
	 */
	public String getStockpdFlg() {
		return stockpdFlg;
	}

	/**
	 * 在庫引落フラグ設定
	 * @param stockpdFlg 在庫引落フラグ
	 */
	public void setStockpdFlg(final String stockpdFlg) {
		this.stockpdFlg = stockpdFlg;
	}
}
