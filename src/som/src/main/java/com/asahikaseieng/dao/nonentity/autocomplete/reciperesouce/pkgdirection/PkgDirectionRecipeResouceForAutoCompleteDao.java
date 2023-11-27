/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.reciperesouce.pkgdirection;

import java.util.List;

/**
 * 包装指図－包装ラインAuto Complete用Daoインターフェース.
 * 
 * @author tosco
 */
public interface PkgDirectionRecipeResouceForAutoCompleteDao {

	/** BEANアノテーション */
	Class<PkgDirectionRecipeResouceForAutoComplete> BEAN = PkgDirectionRecipeResouceForAutoComplete.class;

	// method-----------------------------------------------------------------

	/** ARGSアノテーション getSearchList(). */
	String getSearchList_ARGS = "productionLine,packageLine,rowlimit";

	/**
	 * 包装ライン一覧取得
	 * @param productionLine 生産ライン
	 * @param packageLine 包装ライン
	 * @param rowlimit 行上限
	 * @return List<PkgDirectionRecipeResouceForAutoComplete>
	 */
	List<PkgDirectionRecipeResouceForAutoComplete> getSearchList(
			String productionLine, String packageLine, final String rowlimit);

}
