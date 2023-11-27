/*
 * Created on 2009/03/30
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.materialrinput;



/**
 * 外注原材料投入実績入力 処方フォーミュラデータ格納クラス.
 *
 * @author tosco
 */
public class MaterialRinputRecipeFormulaList extends MaterialRinputRecipeFormulaListBase {


	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション unit */
	public static final String unit_COLUMN = "UNIT";

	/** COLUMNアノテーション recipeName */
	public static final String recipeName_COLUMN = "RECIPE_NAME";

	/** 品目名称 */
	private String itemName;

	/** 単位 */
	private String unit;

	/** 基本処方名称 */
	private String recipeName;

	/**
	 * コンストラクタ.
	 */
	public MaterialRinputRecipeFormulaList() {
		super();
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


}
