/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.pkgdirection;

import java.math.BigDecimal;

/**
 * 包装指図－処方ヘッダーDaoインターフェース.
 *
 * @author tosco
 */
public interface PkgDirectionRecipeHeaderDetailDao {

	/** BEANアノテーション */
	Class<PkgDirectionRecipeHeaderDetail> BEAN = PkgDirectionRecipeHeaderDetail.class;

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "recipeCd,recipeVersion,itemCd";
	/**
	 * エンティティ取得.
	 * @param recipeCd レシピコード
	 * @param recipeVersion レシピバージョン
	 * @param itemCd 品目コード
	 * @return PkgDirectionRecipeHeaderDetail
	 */
	PkgDirectionRecipeHeaderDetail getEntity(String recipeCd, BigDecimal recipeVersion, String itemCd);
}
