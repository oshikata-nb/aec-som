/*
 * Created on 2009/03/09
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.comboboxes.rdirection;

import java.util.List;

/**
 * 生産ラインメンテ 詳細画面用Daoインターフェース.
 *
 * @author tosco
 */
public interface RdirectionLineForComboboxesDao {

	/** BEANアノテーション */
	Class<RdirectionLineForComboboxes> BEAN = RdirectionLineForComboboxes.class;

	/**
	 * 全件取得.
	 * @return Mgrecipeline
	 */
	List<RdirectionLineForComboboxes> findAll();

}
