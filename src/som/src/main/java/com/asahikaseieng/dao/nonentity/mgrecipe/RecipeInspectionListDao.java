/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.mgrecipe;

import java.math.BigDecimal;
import java.util.List;

/**
 * 処方検査Daoインターフェース.
 *
 * @author tosco
 */
public interface RecipeInspectionListDao {

	/** BEANアノテーション */
	Class<RecipeInspectionList> BEAN = RecipeInspectionList.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean RecipeInspectionList
	 * @return Insert件数
	 */
	int insert(RecipeInspectionList bean);

	/**
	 * Update.
	 * @param bean RecipeInspectionList
	 * @return Update件数
	 */
	int update(RecipeInspectionList bean);

	/**
	 * Delete.
	 * @param bean RecipeInspectionList
	 * @return Delete件数
	 */
	int delete(RecipeInspectionList bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "recipeId,stepNo,lineNo";
	/**
	 * エンティティ取得.
	 * @param recipeId レシピインデックス
	 * @param stepNo ステップNO
	 * @param lineNo ラインNO
	 * @return RecipeInspectionList
	 */
//	RecipeInspectionList getEntity(String recipeId, String stepNo, String lineNo);
	RecipeInspectionList getEntity(BigDecimal recipeId, BigDecimal stepNo, BigDecimal lineNo);

	/** ARGSアノテーション findByRecipeId(). */
	String findByRecipeId_ARGS = "recipeId,stepNo,lineNo";
	/**
	 * レシピインデックス、ステップNo、ラインNOで検索
	 * @param recipeId レシピインデックス
	 * @param stepNo ステップNO
	 * @param lineNo ラインNO
	 * @return List<RecipeInspectionList>
	 */
	List<RecipeInspectionList> findByRecipeId(String recipeId, String stepNo, String lineNo);

	/** QUERYアノテーション findByRecipeIdStepNo(). */
	String findByRecipeIdStepNo_QUERY = "RECIPE_ID = ? AND STEP_NO = ?";
	/**
	 * レシピインデックス、STEP_NOに紐づくデータをすべて取得
	 * @param recipeId レシピインデックス
	 * @param stepNo   STEP_NO
	 * @return List<RecipeInspectionList> データリスト
	 */
	List<RecipeInspectionList> findByRecipeIdStepNo(BigDecimal recipeId, BigDecimal stepNo);

	/** ARGSアノテーション deleteByRecipeId(). */
	String deleteByRecipeId_ARGS = "recipeId";
	/**
	 * レシピインデックスに紐づくデータをすべて削除
	 * @param recipeId レシピインデックス
	 * @return Delete件数
	 */
	int deleteByRecipeId(String recipeId);

	/** ARGSアノテーション getEntity(). */
	String getSearchList_ARGS = "recipeId,stepNo";
	/**
	 * レシピインデックス、ステップNoで検索
	 * @param recipeId レシピインデックス
	 * @param stepNo STEP_NO
	 * @return List<RecipeInspectionList>
	 */
	List<RecipeInspectionList> getSearchList(BigDecimal recipeId, BigDecimal stepNo);

	/** ARGSアノテーション getLastLineNo(). */
	String getLastLineNo_ARGS = "recipeId,stepNo";
	/**
	 * 最終LINE_NO 取得
	 * @param recipeId レシピインデックス
	 * @param stepNo STEP_NO
	 * @return RecipeInspectionList 最終LINE_NO
	 */
	RecipeInspectionList getLastLineNo(BigDecimal recipeId, BigDecimal stepNo);

	/** ARGSアノテーション deleteByRecipeId(). */
	String deleteByRecipeIdStepNo_ARGS = "recipeId,stepNo";
	/**
	 * レシピインデックス、STEP_NOに紐づくデータをすべて削除
	 * @param recipeId レシピインデックス
	 * @param stepNo STEP_NO
	 * @return Delete件数
	 */
	int deleteByRecipeIdStepNo(BigDecimal recipeId, BigDecimal stepNo);
}
