/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.direction;


/**
 * 処方ASPROVA_Daoインターフェース.
 *
 * @author tosco
 */
public interface DirectionRecipeAsprovaListDao {

	/** BEANアノテーション */
	Class<DirectionRecipeAsprovaList> BEAN = DirectionRecipeAsprovaList.class;

	//method---------------------------------------------------------------
	/** ARGSアノテーション getResouceCd(). */
	String getResouceCd_ARGS = "recipeId,resourceCd";
	/**
	 * レシピID・設備コードに紐づくデータをすべて取得
	 * @param recipeId レシピインデックス
	 * @param resourceCd 設備コード
	 * @return List<DirectionRecipeAsprovaList>
	 */
	DirectionRecipeAsprovaList getResouceCd(String recipeId, String resourceCd);

}
