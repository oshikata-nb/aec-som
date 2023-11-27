/*
 * Created on 2009/01/21
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.mgrecipe;

import java.math.BigDecimal;
import java.util.List;

/**
 * 処方フォーミュラ用Daoインターフェース.
 *
 * @author tosco
 */
public interface RecipeFormulaListDao {

	/** 用途-配合時のLINE_TYPE */
	String LINE_TYPE_COMBINE = "-1";
	/** 用途-製造時のLINE_TYPE */
	String LINE_TYPE_PRODUCTION = "2";
	/** 用途-包装時のLINE_TYPE */
	String LINE_TYPE_PACKING = "1";
	/** 処方フォーミュラ－ステップNO初期値 */
	String STEP_NO = "1";
	/** 処方フォーミュラ－ラインNO初期値 */
	String LINE_NO = "1001";
	/** 処方フォーミュラ－seq初期値 */
	String SEQ = "1";

	/** BEANアノテーション */
	Class<RecipeFormulaList> BEAN = com.asahikaseieng.dao.nonentity.mgrecipe.RecipeFormulaList.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Mgrecipe
	 * @return Insert件数
	 */
	int insert(RecipeFormulaList bean);

	/**
	 * Update.
	 * @param bean Mgrecipe
	 * @return Update件数
	 */
	int update(RecipeFormulaList bean);

	/**
	 * Delete.
	 * @param bean Mgrecipe
	 * @return Delete件数
	 */
	int delete(RecipeFormulaList bean);

	/** ARGSアノテーション findByRecipeId(). */
	String findByRecipeId_ARGS = "recipeId";
	/**
	 * レシピIDに紐づくデータをすべて取得
	 * @param recipeId レシピインデックス
	 * @return List<RecipeFormulaList>
	 */
	List<RecipeFormulaList> findByRecipeId(String recipeId);

	/** QUERYアノテーション findByRecipeIdStepNo(). */
	String findByRecipeIdStepNo_QUERY = "RECIPE_ID = ? AND STEP_NO = ? AND LINE_TYPE = -1";
	/**
	 * レシピインデックス、STEP_NOに紐づくデータをすべて取得
	 * @param recipeId レシピインデックス
	 * @param stepNo   STEP_NO
	 * @return List<RecipeFormulaList> データリスト
	 */
	List<RecipeFormulaList> findByRecipeIdStepNo(BigDecimal recipeId, BigDecimal stepNo);

	/** ARGSアノテーション deleteByRecipeId(). */
	String deleteByRecipeId_ARGS = "recipeId";
	/**
	 * レシピインデックスに紐づくデータをすべて削除
	 * @param recipeId レシピインデックス
	 * @return Delete件数
	 */
	int deleteByRecipeId(String recipeId);

	/** ARGSアノテーション getSumQty(). */
	String getSumQty_ARGS = "recipeId,procStepNo";
	/**
	 * 配合量の合計値取得.
	 * @param recipeId レシピインデックス
	 * @param procStepNo  工程順序
	 * @return RecipeFormulaList 配合量計データ
	 */
	RecipeFormulaList getSumQty(BigDecimal recipeId, BigDecimal procStepNo);

	/** ARGSアノテーション getSearchList(). */
	String getSearchList_ARGS = "recipeId,procStepNo";
	/**
	 * 配合タグ一覧の検索取得
	 * @param recipeId レシピインデックス
	 * @param procStepNo  工程順序
	 * @return List<RecipeFormulaList> データリスト
	 */
	List<RecipeFormulaList> getSearchList(BigDecimal recipeId, BigDecimal procStepNo);

	/** ARGSアノテーション getLastLineNo(). */
	String getLastLineNo_ARGS = "recipeId,stepNo";
	/**
	 * 最終LINE_NO 取得.
	 * @param recipeId レシピID
	 * @param stepNo   STEP_NO
	 * @return RecipeFormulaList 最終LINE_NO
	 */
	RecipeFormulaList getLastLineNo(BigDecimal recipeId, BigDecimal stepNo);

	/** ARGSアノテーション getLastSeq(). */
	String getLastSeq_ARGS = "recipeId,stepNo";
	/**
	 * 最終SEQ 取得.
	 * @param recipeId レシピID
	 * @param stepNo   STEP_NO
	 * @return RecipeFormulaList 最終SEQ
	 */
	RecipeFormulaList getLastSeq(BigDecimal recipeId, BigDecimal stepNo);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "recipeId,stepNo,lineNo";
	/**
	 * レシピインデックス、STEP_NO、LINE_NOで検索
	 * @param recipeId レシピインデックス
	 * @param stepNo   STEP_NO
	 * @param lineNo   LINE_NO
	 * @return RecipeFormulaList 検索結果
	 */
	RecipeFormulaList getEntity(BigDecimal recipeId, BigDecimal stepNo, BigDecimal lineNo);

	/** ARGSアノテーション getSearchFinishList(). */
	String getSearchFinishList_ARGS = "recipeId";
	/**
	 * 仕上タグ一覧の検索取得
	 * @param recipeId レシピインデックス
	 * @return List<RecipeFormulaList> データリスト
	 */
	List<RecipeFormulaList> getSearchFinishList(BigDecimal recipeId);

	/** QUERYアノテーション findByRecipeIdStepNo(). */
	String deleteFinishListByRecipeId_QUERY = "RECIPE_ID = ? AND LINE_TYPE != -1";
	/**
	 * レシピインデックスにLINE_TYPE紐づくデータをすべて削除
	 * @param recipeId レシピインデックス
	 * @return Delete件数
	 */
	int deleteFinishListByRecipeId(String recipeId);

	/** QUERYアノテーション findFinishByRecipeIdStepNo(). */
	String findFinishByRecipeIdStepNo_QUERY =
		"RECIPE_ID = ? AND STEP_NO = ? AND LINE_NO > 1001 AND LINE_TYPE != -1";
	/**
	 * レシピインデックス、STEP_NOに紐づくデータをすべて取得
	 * @param recipeId レシピインデックス
	 * @param stepNo   STEP_NO
	 * @return List<RecipeFormulaList> データリスト
	 */
	List<RecipeFormulaList> findFinishByRecipeIdStepNo(BigDecimal recipeId, BigDecimal stepNo);

}
