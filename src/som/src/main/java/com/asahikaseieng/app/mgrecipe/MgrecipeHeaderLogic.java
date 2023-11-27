/*
 * Created on 2009/01/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mgrecipe;

import java.sql.Timestamp;
import java.util.List;

import com.asahikaseieng.dao.nonentity.comboboxes.mgrecipe.RecipeHeaderForComboboxes;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeHeaderList;
import com.asahikaseieng.exception.NoDataException;

/**
 * ヘッダー情報 ロジッククラス interface.
 * @author tosco
 */
public interface MgrecipeHeaderLogic {

	/**
	 * 工程パターン一覧取得
	 * @param use 用途
	 * @return List<RecipeHeaderForComboboxes>
	 */
	List<RecipeHeaderForComboboxes> getOperationPatternList(String use);

	/**
	 * 処方ヘッダにコード・バージョン・タイプが一致するデータが存在する倍取得する
	 * @param recipeCd レシピコード
	 * @param recipeVersion レシピバージョン
	 * @param type タイプ
	 * @return RecipeHeaderList
	 * @throws NoDataException データが存在しない例外
	 */
	RecipeHeaderList getEntity(String recipeCd, String recipeVersion, String type)
			throws NoDataException;

	/**
	 * 登録処理を行う.
	 * @param bean 登録対象ビーン
	 */
	void insert(RecipeHeaderList bean);

	/**
	 * 更新処理を行う
	 * @param bean 登録対象ビーン
	 */
	void update(RecipeHeaderList bean);

	/**
	 * 処方ヘッダにコード・バージョン・タイプが一致するデータが存在する場合取得する
	 * @param recipeId レシピインデックス
	 * @param recipeType レシピタイプ
	 * @return RecipeHeaderList
	 * @throws NoDataException データが存在しない例外
	 */
	RecipeHeaderList findByPrimaryKey(final String recipeId, final String recipeType) throws NoDataException;

	/**
	 * 削除処理を行う.
	 * @param recipeId レシピインデックス
	 * @param updateDate 更新日
	 * @throws NoDataException データ無し例外
	 */
	void delete(String recipeId, Timestamp updateDate) throws NoDataException;

}
