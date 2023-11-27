/*
 * Created on 2009/03/23
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.grecipe;

import java.math.BigDecimal;
import java.util.List;

/**
 * 処方検査Daoインターフェース.
 *
 * @author tosco
 */
public interface GrecipeRecipeInspectionListDao {

	/** BEANアノテーション */
	Class<GrecipeRecipeInspectionList> BEAN = GrecipeRecipeInspectionList.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean GrecipeRecipeInspectionList
	 * @return Insert件数
	 */
	int insert(GrecipeRecipeInspectionList bean);

	/**
	 * Update.
	 * @param bean GrecipeRecipeInspectionList
	 * @return Update件数
	 */
	int update(GrecipeRecipeInspectionList bean);

	/**
	 * Delete.
	 * @param bean GrecipeRecipeInspectionList
	 * @return Delete件数
	 */
	int delete(GrecipeRecipeInspectionList bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "recipeId,stepNo,lineNo";
	/**
	 * エンティティ取得.
	 * @param recipeId レシピインデックス
	 * @param stepNo ステップNO
	 * @param lineNo ラインNO
	 * @return GrecipeRecipeInspectionList
	 */
	GrecipeRecipeInspectionList getEntity(BigDecimal recipeId, BigDecimal stepNo, BigDecimal lineNo);

	/** ARGSアノテーション findByRecipeId(). */
	String findByRecipeId_ARGS = "recipeId,stepNo,lineNo";
	/**
	 * レシピインデックス、ステップNo、ラインNOで検索
	 * @param recipeId レシピインデックス
	 * @param stepNo ステップNO
	 * @param lineNo ラインNO
	 * @return List<GrecipeRecipeInspectionList>
	 */
	List<GrecipeRecipeInspectionList> findByRecipeId(String recipeId, String stepNo, String lineNo);

	/** QUERYアノテーション findByRecipeIdStepNo(). */
	String findByRecipeIdStepNo_QUERY = "RECIPE_ID = ? AND STEP_NO = ?";
	/**
	 * レシピインデックス、STEP_NOに紐づくデータをすべて取得
	 * @param recipeId レシピインデックス
	 * @param stepNo   STEP_NO
	 * @return List<GrecipeRecipeInspectionList> データリスト
	 */
	List<GrecipeRecipeInspectionList> findByRecipeIdStepNo(BigDecimal recipeId, BigDecimal stepNo);

	/** ARGSアノテーション deleteByRecipeId(). */
	String deleteByRecipeId_ARGS = "recipeId";
	/**
	 * レシピインデックスに紐づくデータをすべて削除
	 * @param recipeId レシピインデックス
	 * @return Delete件数
	 */
	int deleteByRecipeId(String recipeId);

	/** ARGSアノテーション getSearchList(). */
	String getSearchList_ARGS = "recipeId,stepNo";
	/**
	 * レシピインデックス、ステップNoで検索
	 * @param recipeId レシピインデックス
	 * @param stepNo STEP_NO
	 * @return List<GrecipeRecipeInspectionList>
	 */
	List<GrecipeRecipeInspectionList> getSearchList(BigDecimal recipeId, BigDecimal stepNo);

	/** ARGSアノテーション getLastLineNo(). */
	String getLastLineNo_ARGS = "recipeId,stepNo";
	/**
	 * 最終LINE_NO 取得
	 * @param recipeId レシピインデックス
	 * @param stepNo STEP_NO
	 * @return GrecipeRecipeInspectionList 最終LINE_NO
	 */
	GrecipeRecipeInspectionList getLastLineNo(BigDecimal recipeId, BigDecimal stepNo);

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
