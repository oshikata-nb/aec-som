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
 * 処方プロシージャDaoインターフェース.
 *
 * @author tosco
 */
public interface GrecipeRecipeProcedureListDao {

	/** BEANアノテーション */
	Class<GrecipeRecipeProcedureList> BEAN = GrecipeRecipeProcedureList.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Grecipe
	 * @return Insert件数
	 */
	int insert(GrecipeRecipeProcedureList bean);

	/**
	 * Update.
	 * @param bean Grecipe
	 * @return Update件数
	 */
	int update(GrecipeRecipeProcedureList bean);

	/**
	 * Delete.
	 * @param bean Grecipe
	 * @return Delete件数
	 */
	int delete(GrecipeRecipeProcedureList bean);

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
	 * @return List<GrecipeRecipeProcedureList>
	 */
	List<GrecipeRecipeProcedureList> findByRecipeId(String recipeId, String stepNo);

	/** ARGSアノテーション getSearchList(). */
	String getSearchList_ARGS = "recipeId";
	/**
	 * レシピインデックスで検索
	 * @param recipeId レシピインデックス
	 * @return List<GrecipeRecipeProcedureList> 検索結果リスト
	 */
	List<GrecipeRecipeProcedureList> getSearchList(BigDecimal recipeId);

	/** ARGSアノテーション getLastStepNo(). */
	String getLastStepNo_ARGS = "recipeId";
	/**
	 * 最終STEP_NO 取得.
	 * @param recipeId レシピID
	 * @return GrecipeRecipeProcedureList 最終STEP_NO
	 */
	GrecipeRecipeProcedureList getLastStepNo(BigDecimal recipeId);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "recipeId,stepNo";
	/**
	 * レシピインデックス、STEP_NOで検索
	 * @param recipeId レシピインデックス
	 * @param stepNo   STEP_NO
	 * @return GrecipeRecipeProcedureList 検索結果
	 */
	GrecipeRecipeProcedureList getEntity(BigDecimal recipeId, BigDecimal stepNo);

	/** ARGSアノテーション getOperationName(). */
	String getOperationName_ARGS = "recipeId,stepNo";
	/**
	 * 工程コード、工程名称 取得.
	 * @param recipeId レシピインデックス
	 * @param stepNo STEP_NO
	 * @return GrecipeRecipeProcedureList 工程コード、工程名称
	 */
	GrecipeRecipeProcedureList getOperationName(BigDecimal recipeId, BigDecimal stepNo);
}
