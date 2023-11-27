/*
 * Created on 2009/03/02
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.direction;

import java.util.List;

/**
 * 処方フォーミュラ用Daoインターフェース.
 *
 * @author tosco
 */
public interface DirectionRecipeFormulaListDao {

	/** 用途-配合時のLINE_TYPE */
	String LINE_TYPE_COMBINE = "-1";
	/** 用途-製造時のLINE_TYPE */
	String LINE_TYPE_PRODUCTION = "1";
	/** 用途-包装時のLINE_TYPE */
	String LINE_TYPE_PACKING = "3";
	/** 処方フォーミュラ－ステップNO初期値 */
	String STEP_NO = "1";
	/** 処方フォーミュラ－ラインNO初期値 */
	String LINE_NO = "1001";
	/** 処方フォーミュラ－ラインNO初期値 */
	int LINE_NO_INT = 1001;
	/** 処方フォーミュラ－seq初期値 */
	String SEQ = "1";

	/** BEANアノテーション */
	Class<DirectionRecipeFormulaList> BEAN = DirectionRecipeFormulaList.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getRecipeId(). */
	String getRecipeId_ARGS = "recipeId";
	/**
	 * レシピIDに紐づくデータをすべて取得
	 * @param recipeId レシピインデックス
	 * @return List<RecipeFormula>
	 */
	List<DirectionRecipeFormulaList> getRecipeId(String recipeId);


}
