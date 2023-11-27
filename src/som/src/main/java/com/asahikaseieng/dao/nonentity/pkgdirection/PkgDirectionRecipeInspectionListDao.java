/*
 * Created on 2009/02/26
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.pkgdirection;

import java.math.BigDecimal;
import java.util.List;

/**
 * 包装指図－処方検査Daoインターフェース.
 *
 * @author tosco
 */
public interface PkgDirectionRecipeInspectionListDao {

	/** BEANアノテーション */
	Class<PkgDirectionRecipeInspectionList> BEAN
		= com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionRecipeInspectionList.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getList(). */
	String getList_ARGS = "recipeId";
	/**
	 * レシピインデックスで検索
	 * @param recipeId レシピインデックス
	 * @return List<PkgDirectionRecipeInspectionList>
	 */
	List<PkgDirectionRecipeInspectionList> getList(BigDecimal recipeId);
}
