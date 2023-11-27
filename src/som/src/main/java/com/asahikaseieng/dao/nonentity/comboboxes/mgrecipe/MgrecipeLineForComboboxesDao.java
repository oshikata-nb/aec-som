/*
 * Created on 2009/01/15
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.comboboxes.mgrecipe;

import java.util.List;

/**
 * 生産ラインメンテ 詳細画面用Daoインターフェース.
 *
 * @author tosco
 */
public interface MgrecipeLineForComboboxesDao {

	/** BEANアノテーション */
	Class<MgrecipeLineForComboboxes> BEAN = MgrecipeLineForComboboxes.class;

	/**
	 * 全件取得.
	 * @return Mgrecipeline
	 */
	List<MgrecipeLineForComboboxes> findAll();

}
