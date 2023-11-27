/*
 * Created on 2009/03/30
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.materialrinput;

import java.math.BigDecimal;
import java.util.List;

/**
 * 外注原材料投入実績入力 処方フォーミュラ用Daoインターフェース.
 *
 * @author tosco
 */
public interface MaterialRinputRecipeFormulaListDao {

	/** 処方フォーミュラ－ラインNO初期値 */
	int LINE_NO_INT = 1001;

	/** BEANアノテーション */
	Class<MaterialRinputRecipeFormulaList> BEAN = MaterialRinputRecipeFormulaList.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getRecipeHeader(). */
	String getRecipeHeader_ARGS = "recipeCd,recipeVersion,itemCd";
	/**
	 * レシピIDに紐づくデータをすべて取得
	 * @param recipeCd      レシピコード
	 * @param recipeVersion レシピバージョン
	 * @param itemCd        主要製品コード(品目コード）
	 * @return MaterialRinputRecipeFormulaList 処方ヘッダーデータ
	 */
	MaterialRinputRecipeFormulaList getRecipeHeader(String recipeCd, BigDecimal recipeVersion, String itemCd);

	/** ARGSアノテーション getRecipeId(). */
	String getRecipeId_ARGS = "recipeId";
	/**
	 * レシピIDに紐づくデータをすべて取得
	 * @param recipeId レシピインデックス
	 * @return List<MaterialRinputRecipeFormulaList> 検索結果リスト
	 */
	List<MaterialRinputRecipeFormulaList> getFormulaList(BigDecimal recipeId);

}
