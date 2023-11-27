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
 * 包装指図－処方プロシージャDaoインターフェース.
 *
 * @author tosco
 */
public interface PkgDirectionRecipeProcedureListDao {

	/** BEANアノテーション */
	Class<PkgDirectionRecipeProcedureList> BEAN
		= com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionRecipeProcedureList.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getList(). */
	String getList_ARGS = "recipeId";
	/**
	 * レシピインデックスで検索
	 * @param recipeId レシピインデックス
	 * @return List<RecipeProcedure> 検索結果リスト
	 */
	List<PkgDirectionRecipeProcedureList> getList(BigDecimal recipeId);
}
