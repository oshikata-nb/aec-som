/*
 * Created on 2009/02/26
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.pkgdirection;

import java.math.BigDecimal;
import java.util.List;

/**
 * 包装指図－処方フォーミュラ用Daoインターフェース.
 *
 * @author tosco
 */
public interface PkgDirectionRecipeFormulaListDao {

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
	/** 処方フォーミュラ－seq初期値 */
	String SEQ = "1";

	/** BEANアノテーション */
	Class<PkgDirectionRecipeFormulaList> BEAN
		= com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionRecipeFormulaList.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getList(). */
	String getList_ARGS = "recipeId";
	/**
	 * レシピIDに紐づくデータをすべて取得
	 * @param recipeId レシピインデックス
	 * @return List<PkgDirectionRecipeFormulaList>
	 */
	List<PkgDirectionRecipeFormulaList> getList(BigDecimal recipeId);

}
