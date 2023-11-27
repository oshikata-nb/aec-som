/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.pkgdirection;

import java.math.BigDecimal;
import java.util.List;

/**
 * 包装指図－処方ASPROVADaoインターフェース.
 * @author kanri-user
 */
public interface PkgDirectionRecipeAsprovaListDao {

	/** BEANアノテーション. */
	Class BEAN = PkgDirectionRecipeAsprovaList.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getResouceList(). */
	String getResouceList_ARGS = "recipeId,resouceCd";

	/**
	 * 設備リスト取得.
	 * @param recipeId レシピインデックス
	 * @param resouceCd 設備コード
	 * @return List<PkgDirectionRecipeAsprovaList> 設備リスト
	 */
	List<PkgDirectionRecipeAsprovaList> getResouceList(BigDecimal recipeId, String resouceCd);
}
