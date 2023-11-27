/*
 * Created on 2009/01/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.mgrecipe;

import java.math.BigDecimal;
import java.util.List;

/**
 * 処方ASPROVA_Daoインターフェース.
 * 
 * @author tosco
 */
public interface RecipeAsprovaListDao {

	/** BEANアノテーション */
	Class<RecipeAsprovaList> BEAN = com.asahikaseieng.dao.nonentity.mgrecipe.RecipeAsprovaList.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Asprova
	 * @return Insert件数
	 */
	int insert(RecipeAsprovaList bean);

	/**
	 * Update.
	 * @param bean Asprova
	 * @return Update件数
	 */
	int update(RecipeAsprovaList bean);

	/**
	 * Delete.
	 * @param bean Asprova
	 * @return Delete件数
	 */
	int delete(RecipeAsprovaList bean);

	/** ARGSアノテーション findByRecipeId(). */
	String findByRecipeId_ARGS = "recipeId,groupCd,resourceCd";

	/**
	 * レシピIDに紐づくデータをすべて取得
	 * @param recipeId レシピインデックス
	 * @param groupCd 設備グループコード
	 * @param resourceCd 設備コード
	 * @return List<RecipeAsprovaList>
	 */
	List<RecipeAsprovaList> findByRecipeId(String recipeId, String groupCd,
			String resourceCd);

	/** ARGSアノテーション deleteByRecipeId(). */
	String deleteByRecipeId_ARGS = "recipeId";

	/**
	 * レシピインデックスに紐づくデータをすべて削除
	 * @param recipeId レシピインデックス
	 * @return Delete件数
	 */
	int deleteByRecipeId(String recipeId);

	/** ARGSアノテーション getSearchListByPrimaryKey(). */
	String getSearchListByPrimaryKey_ARGS = "recipeId,resouceGroupCd,operationGroupCd,resouceCd";

	/**
	 * 主キーで複数検索.
	 * @param recipeId レシピインデックス
	 * @param resouceGroupCd 設備グループコード
	 * @param operationGroupCd 工程グループコード
	 * @param resouceCd 設備コード
	 * @return List<RecipeAsprovaList> データリスト
	 */
	List<RecipeAsprovaList> getSearchListByPrimaryKey(BigDecimal recipeId,
			String resouceGroupCd, String operationGroupCd, String resouceCd);

	/** ARGSアノテーション getResouceList(). */
	String getResouceList_ARGS = "resouceGroupCd,productionLine";

	/**
	 * asprovaタグ一覧の検索取得
	 * @param resouceGroupCd 設備グループコード
	 * @param productionLine 生産ラインコード
	 * @return List<RecipeAsprovaList> データリスト
	 */
	List<RecipeAsprovaList> getResouceList(String resouceGroupCd,
			String productionLine);

	/** ARGSアノテーション deleteByRecipeIdOperationGroupCd(). */
	String deleteByRecipeIdOperationGroupCd_ARGS = "recipeId,operationGroupCd";

	/**
	 * レシピインデックス、工程グループコードに紐づくデータをすべて削除
	 * @param recipeId レシピインデックス
	 * @param operationGroupCd 工程グループコード
	 * @return Delete件数
	 */
	int deleteByRecipeIdOperationGroupCd(String recipeId,
			String operationGroupCd);

	/** ARGSアノテーション findByPrimaryKey(). */
	String findByPrimaryKey_ARGS = "recipeId,resouceGroupCd,operationGroupCd,resouceCd";

	/**
	 * 主キーで1件検索.
	 * @param recipeId レシピインデックス
	 * @param resouceGroupCd 設備グループコード
	 * @param operationGroupCd 工程グループコード
	 * @param resouceCd 設備コード
	 * @return List<RecipeAsprovaList> データリスト
	 */
	RecipeAsprovaList findByPrimaryKey(BigDecimal recipeId,
			String resouceGroupCd, String operationGroupCd, String resouceCd);

	// /** ARGSアノテーション getRecipeAssprovaList(). */
	// String getRecipeAssprovaList_ARGS =
	// "recipeId,resouceGroupCd,operationGroupCd,resouceCd,productionLine";
	//
	// /**
	// * RECIPE_ASPROVAとRECIPE_RESOUCEでUNION検索.
	// * @param recipeId レシピインデックス
	// * @param resouceGroupCd 設備グループコード
	// * @param operationGroupCd 工程グループコード
	// * @param resouceCd 設備コード
	// * @param productionLine 生産ラインコード
	// * @return List<RecipeAsprovaList> データリスト
	// */
	// List<RecipeAsprovaList> getRecipeAssprovaList(BigDecimal recipeId,
	// String resouceGroupCd, String operationGroupCd, String resouceCd,
	// String productionLine);

	/** ARGSアノテーション getResouceGroupCd(). */
	String getResouceGroupCd_ARGS = "recipeId, operationGroupCd";

	/**
	 * 設備グループコード取得
	 * @param recipeId レシピインデックス
	 * @param operationGroupCd 工程グループコード
	 * @return RecipeAsprovaList
	 */
	RecipeAsprovaList getResouceGroupCd(BigDecimal recipeId,
			String operationGroupCd);
}
