/*
 * Created on 2009/03/03
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.comboboxes.master.search.recipeheader;

import java.util.List;

/**
 * 製造指図－新規入力画面_生産工場コンボボックスDaoインターフェース.
 *
 * @author tosco
 */
public interface SearchRecipeHeaderLineForComboboxesDao {

	/** BEANアノテーション */
	Class<SearchRecipeHeaderLineForComboboxes> BEAN = SearchRecipeHeaderLineForComboboxes.class;

	/**
	 * 全件取得.
	 * @return Mgrecipeline
	 */
	List<SearchRecipeHeaderLineForComboboxes> findAll();

}
