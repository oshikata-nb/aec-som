/*
 * Created on 2009/03/02
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.direction;


/**
 * 処方ヘッダーメンテ 詳細画面用Daoインターフェース.
 *
 * @author tosco
 */
public interface DirectionRecipeHeaderListDao {
	/** レシピタイプ－原処方 */
	String RECIPE_TYPE_GENERAL = "1";
	/** レシピタイプ－基本処方 */
	String RECIPE_TYPE_MASTER = "3";

	/** BEANアノテーション */
	Class<DirectionRecipeHeaderList> BEAN = DirectionRecipeHeaderList.class;


	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "RECIPE_CD,RECIPE_VERSION,RECIPE_TYPE";
	/**
	 * エンティティ取得.
	 * @param recipeCd レシピコード
	 * @param version バージョン
	 * @param type タイプ
	 * @return DirectionRecipeHeaderList
	 */
	DirectionRecipeHeaderList getEntity(String recipeCd, String version, String type);

}
