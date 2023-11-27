/*
 * Created on 2009/01/15
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.comboboxes.direction;

import java.util.List;

/**
 * 生産ラインメンテ 詳細画面用Daoインターフェース.
 *
 * @author tosco
 */
public interface DirectionLineForComboboxesDao {

	/** BEANアノテーション */
	Class<DirectionLineForComboboxes> BEAN = DirectionLineForComboboxes.class;

	/**
	 * 全件取得.
	 * @return Mgrecipeline
	 */
	List<DirectionLineForComboboxes> findAll();

}
