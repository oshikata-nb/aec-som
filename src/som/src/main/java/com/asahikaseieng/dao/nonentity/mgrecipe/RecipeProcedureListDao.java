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
 * 処方プロシージャDaoインターフェース.
 *
 * @author tosco
 */
public interface RecipeProcedureListDao {

	/** BEANアノテーション */
	Class<RecipeProcedureList> BEAN = com.asahikaseieng.dao.nonentity.mgrecipe.RecipeProcedureList.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Mgrecipe
	 * @return Insert件数
	 */
	int insert(RecipeProcedureList bean);

	/**
	 * Update.
	 * @param bean Mgrecipe
	 * @return Update件数
	 */
	int update(RecipeProcedureList bean);

	/**
	 * Delete.
	 * @param bean Mgrecipe
	 * @return Delete件数
	 */
	int delete(RecipeProcedureList bean);

	/** ARGSアノテーション deleteByRecipeId(). */
	String deleteByRecipeId_ARGS = "recipeId";
	/**
	 * レシピインデックスに紐づくデータをすべて削除
	 * @param recipeId レシピインデックス
	 * @return Delete件数
	 */
	int deleteByRecipeId(String recipeId);


	/** ARGSアノテーション getEntity(). */
	String findByRecipeId_ARGS = "recipeId,stepNo";
	/**
	 * レシピインデックス、ステップNoで検索
	 * @param recipeId レシピインデックス
	 * @param stepNo レシピインデックス
	 * @return List<RecipeProcedureList>
	 */
	List<RecipeProcedureList> findByRecipeId(String recipeId, String stepNo);

	/** ARGSアノテーション getSearchList(). */
	String getSearchList_ARGS = "recipeId";
	/**
	 * レシピインデックスで検索
	 * @param recipeId レシピインデックス
	 * @return List<RecipeProcedureList> 検索結果リスト
	 */
	List<RecipeProcedureList> getSearchList(BigDecimal recipeId);

	/** ARGSアノテーション getLastStepNo(). */
	String getLastStepNo_ARGS = "recipeId";
	/**
	 * 最終STEP_NO 取得.
	 * @param recipeId レシピID
	 * @return RecipeProcedureList 最終STEP_NO
	 */
	RecipeProcedureList getLastStepNo(BigDecimal recipeId);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "recipeId,stepNo";
	/**
	 * レシピインデックス、STEP_NOで検索
	 * @param recipeId レシピインデックス
	 * @param stepNo   STEP_NO
	 * @return RecipeProcedureList 検索結果
	 */
	RecipeProcedureList getEntity(BigDecimal recipeId, BigDecimal stepNo);

	/** ARGSアノテーション getOperationName(). */
	String getOperationName_ARGS = "recipeId,stepNo";
	/**
	 * 工程コード、工程名称 取得.
	 * @param recipeId レシピインデックス
	 * @param stepNo STEP_NO
	 * @return RecipeProcedureList 工程コード、工程名称
	 */
	RecipeProcedureList getOperationName(BigDecimal recipeId, BigDecimal stepNo);
}
