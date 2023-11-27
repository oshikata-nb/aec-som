/*
 * Created on 2009/03/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.grecipe;

import java.sql.Timestamp;
import java.util.List;

import com.asahikaseieng.dao.nonentity.comboboxes.grecipe.GrecipeRecipeHeaderForComboboxes;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeHeaderList;
import com.asahikaseieng.exception.NoDataException;

/**
 * ヘッダー情報 ロジッククラス interface.
 * @author tosco
 */
public interface GrecipeHeaderLogic {

	/**
	 * 工程パターン一覧取得
	 * @param use 用途
	 * @return List<GrecipeRecipeHeaderForComboboxes>
	 */
	List<GrecipeRecipeHeaderForComboboxes> getOperationPatternList(String use);

	/**
	 * 処方ヘッダにコード・バージョン・タイプが一致するデータが存在する倍取得する
	 * @param recipeCd レシピコード
	 * @param recipeVersion レシピバージョン
	 * @param type タイプ
	 * @return GrecipeRecipeHeaderList
	 * @throws NoDataException データが存在しない例外
	 */
	GrecipeRecipeHeaderList getEntity(String recipeCd, String recipeVersion, String type)
			throws NoDataException;

	/**
	 * 登録処理を行う.
	 * @param bean 登録対象ビーン
	 */
	void insert(GrecipeRecipeHeaderList bean);

	/**
	 * 更新処理を行う
	 * @param bean 登録対象ビーン
	 */
	void update(GrecipeRecipeHeaderList bean);

	/**
	 * 処方ヘッダにコード・バージョン・タイプが一致するデータが存在する場合取得する
	 * @param recipeId レシピインデックス
	 * @return GrecipeRecipeHeaderList
	 * @throws NoDataException データが存在しない例外
	 */
	GrecipeRecipeHeaderList findByPrimaryKey(String recipeId) throws NoDataException;

	/**
	 * 削除処理を行う.
	 * @param recipeId レシピインデックス
	 * @param updateDate 更新日
	 * @throws NoDataException データ無し例外
	 */
	void delete(String recipeId, Timestamp updateDate) throws NoDataException;

	/**
	 * 基本処方のORIGINAL_RECIPE_IDに設定されている件数を取得する
	 * @param recipeId レシピインデックス
	 * @return 件数
	 */
	int getOriginalRecipeIdCount(final String recipeId);

}
