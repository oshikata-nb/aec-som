/*
 * Created on 2009/01/14
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.mgrecipe;

import java.math.BigDecimal;
import java.util.List;

/**
 * 処方ヘッダーメンテ 詳細画面用Daoインターフェース.
 *
 * @author tosco
 */
public interface RecipeHeaderListDao {
	/** レシピタイプ－原処方 */
	String RECIPE_TYPE_GENERAL = "1";
	/** レシピタイプ－基本処方 */
	String RECIPE_TYPE_MASTER = "3";

	/** BEANアノテーション */
	Class<RecipeHeaderList> BEAN = com.asahikaseieng.dao.nonentity.mgrecipe.RecipeHeaderList.class;

	/**
	 * Insert.
	 * @param bean Mgrecipe
	 * @return Insert件数
	 */
	int insert(RecipeHeaderList bean);

	/**
	 * Update.
	 * @param bean Mgrecipe
	 * @return Update件数
	 */
	int update(RecipeHeaderList bean);

	/**
	 * Delete.
	 * @param bean Mgrecipe
	 * @return Delete件数
	 */
	int delete(RecipeHeaderList bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "RECIPE_CD,RECIPE_VERSION,RECIPE_TYPE";
	/**
	 * エンティティ取得.
	 * @param recipeCd レシピコード
	 * @param version バージョン
	 * @param type タイプ
	 * @return RecipeHeaderList
	 */
	RecipeHeaderList getEntity(String recipeCd, String version, String type);

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";
	/**
	 * 一覧検索メソッド
	 * @param condition 検索条件
	 * @return List<RecipeHeaderList> 検索結果リスト
	 */
	List<RecipeHeaderList> getSearchList(MgrecipeListPagerCondition condition);

	/** ARGSアノテーション getSearchTranslateList */
	String getSearchTranslateList_ARGS = "condition";
	/**
	 * 翻訳検索－一覧検索メソッド
	 * @param condition 検索条件
	 * @return List<RecipeHeaderList> 検索結果リスト
	 */
	List<RecipeHeaderList> getSearchTranslateList(MgrecipeTranslateListPagerCondition condition);


	/** ARGSアノテーション findByPrimaryKey */
	String findByPrimaryKey_ARGS = "recipeId,recipeType";
	/**
	 * 主キーで処方ヘッダから取得
	 * @param recipeId レシピインデックス
	 * @param recipeType レシピタイプ
	 * @return RecipeHeaderList
	 */
	RecipeHeaderList findByPrimaryKey(String recipeId, String recipeType);

	/** ARGSアノテーション getCommonHeader */
	String getCommonHeader_ARGS = "recipeId,recipeType";
	/**
	 * 各タブの共通ヘッダーデータを主キーで処方ヘッダから取得
	 * @param recipeId レシピインデックス
	 * @param recipeType レシピタイプ
	 * @return RecipeHeaderList
	 */
	RecipeHeaderList getCommonHeader(String recipeId, String recipeType);

	/** ARGSアノテーション getProcedureHeader */
	String getProcedureHeader_ARGS = "recipeId,recipeType,stepNo";
	/**
	 * 工程詳細のヘッダーデータを主キーで処方ヘッダから取得
	 * @param recipeId レシピインデックス
	 * @param recipeType レシピタイプ
	 * @param stepNo     STEP_NO
	 * @return RecipeHeaderList
	 */
	RecipeHeaderList getProcedureHeader(BigDecimal recipeId, BigDecimal recipeType, BigDecimal stepNo);

	/** ARGSアノテーション getFormulaHeader */
	String getFormulaHeader_ARGS = "recipeId,recipeType,stepNo";
	/**
	 * 配合詳細のヘッダーデータを主キーで処方ヘッダから取得
	 * @param recipeId レシピインデックス
	 * @param recipeType レシピタイプ
	 * @param stepNo     STEP_NO
	 * @return RecipeHeaderList
	 */
	RecipeHeaderList getFormulaHeader(BigDecimal recipeId, BigDecimal recipeType, BigDecimal stepNo);

	/** ARGSアノテーション getAsprovaHeader */
	String getAsprovaHeader_ARGS = "recipeId,recipeType";
	/**
	 * Asprova詳細のヘッダーデータを主キーで処方ヘッダから取得
	 * @param recipeId レシピインデックス
	 * @param recipeType レシピタイプ
	 * @return RecipeHeaderList
	 */
	RecipeHeaderList getAsprovaHeader(BigDecimal recipeId, BigDecimal recipeType);

	/** ARGSアノテーション getInspectionHeader */
	String getInspectionHeader_ARGS = "recipeId,recipeType,stepNo";
	/**
	 * 検査詳細のヘッダーデータを主キーで処方ヘッダから取得
	 * @param recipeId レシピインデックス
	 * @param recipeType レシピタイプ
	 * @param stepNo     STEP_NO
	 * @return RecipeHeaderList
	 */
	RecipeHeaderList getInspectionHeader(BigDecimal recipeId, BigDecimal recipeType, BigDecimal stepNo);

}
