/*
 * Created on 2009/03/16
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.grecipe;

import java.math.BigDecimal;
import java.util.List;
/**
 * 処方ヘッダーメンテ 詳細画面用Daoインターフェース.
 *
 * @author tosco
 */
public interface GrecipeRecipeHeaderListDao {
	/** レシピタイプ－原処方 */
	String RECIPE_TYPE_GENERAL = "1";
	/** レシピタイプ－基本処方 */
	String RECIPE_TYPE_MASTER = "3";

	/** BEANアノテーション */
	Class<GrecipeRecipeHeaderList> BEAN = GrecipeRecipeHeaderList.class;

	/**
	 * Insert.
	 * @param bean Mgrecipe
	 * @return Insert件数
	 */
	int insert(GrecipeRecipeHeaderList bean);

	/**
	 * Update.
	 * @param bean Mgrecipe
	 * @return Update件数
	 */
	int update(GrecipeRecipeHeaderList bean);

	/**
	 * Delete.
	 * @param bean Mgrecipe
	 * @return Delete件数
	 */
	int delete(GrecipeRecipeHeaderList bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "RECIPE_CD,RECIPE_VERSION,RECIPE_TYPE";
	/**
	 * エンティティ取得.
	 * @param recipeCd レシピコード
	 * @param version バージョン
	 * @param type タイプ
	 * @return GrecipeRecipeHeaderList
	 */
	GrecipeRecipeHeaderList getEntity(String recipeCd, String version, String type);

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";
	/**
	 * 一覧検索メソッド
	 * @param condition 検索条件
	 * @return List<GrecipeRecipeHeaderList> 検索結果リスト
	 */
	List<GrecipeRecipeHeaderList> getSearchList(GrecipeListPagerCondition condition);

	/** ARGSアノテーション getRecipeId */
	String getRecipeId_ARGS = "recipeId";
	/**
	 * 主キーで処方ヘッダから取得
	 * @param recipeId レシピインデックス
	 * @return GrecipeRecipeHeaderList
	 */
	GrecipeRecipeHeaderList getRecipeId(String recipeId);

	/** ARGSアノテーション getCommonHeader */
	String getCommonHeader_ARGS = "recipeId";
	/**
	 * 各タブの共通ヘッダーデータを主キーで処方ヘッダから取得
	 * @param recipeId レシピインデックス
	 * @return GrecipeRecipeHeaderList
	 */
	GrecipeRecipeHeaderList getCommonHeader(String recipeId);

	/** ARGSアノテーション getProcedureHeader */
	String getProcedureHeader_ARGS = "recipeId,recipeType,stepNo";
	/**
	 * 工程詳細のヘッダーデータを主キーで処方ヘッダから取得
	 * @param recipeId レシピインデックス
	 * @param recipeType レシピタイプ
	 * @param stepNo     STEP_NO
	 * @return RecipeHeaderList
	 */
	GrecipeRecipeHeaderList getProcedureHeader(BigDecimal recipeId, BigDecimal recipeType, BigDecimal stepNo);

	/** ARGSアノテーション getFormulaHeader */
	String getFormulaHeader_ARGS = "recipeId,stepNo";
	/**
	 * 配合詳細のヘッダーデータを主キーで処方ヘッダから取得
	 * @param recipeId レシピインデックス
	 * @param stepNo     STEP_NO
	 * @return RecipeHeaderList
	 */
	GrecipeRecipeHeaderList getFormulaHeader(BigDecimal recipeId, BigDecimal stepNo);

	/** ARGSアノテーション getInspectionHeader */
	String getInspectionHeader_ARGS = "recipeId,recipeType,stepNo";
	/**
	 * 検査詳細のヘッダーデータを主キーで処方ヘッダから取得
	 * @param recipeId レシピインデックス
	 * @param recipeType レシピタイプ
	 * @param stepNo     STEP_NO
	 * @return GrecipeRecipeHeaderList
	 */
	GrecipeRecipeHeaderList getInspectionHeader(BigDecimal recipeId, BigDecimal recipeType, BigDecimal stepNo);

	/**
	 * 基本処方の原処方レシピインデックスに使用されている件数を取得する
	 * @param recipeId レシピインデックス
	 * @return GrecipeRecipeHeaderList
	 */
	GrecipeRecipeHeaderList getOriginalRecipeCount(String recipeId);
}
