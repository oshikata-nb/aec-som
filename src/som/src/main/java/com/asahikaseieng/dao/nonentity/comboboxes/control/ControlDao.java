/*
 * Created on 2008/05/07
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.control;

import java.util.List;

/**
 * 操作マスタDaoインターフェース.
 * @author tosco
 */
public interface ControlDao {

	/** BEANアノテーション. */
	Class BEAN = com.asahikaseieng.dao.nonentity.comboboxes.control.Control.class;

	/** QUERYアノテーション  */
	String getEntity_QUERY = "ORDER BY CONTROL_ID";

	//
	// インスタンスメソッド
	//

	/**
	 * 操作リスト取得.
	 * @return List<Control> 操作リスト
	 */
	List<Control> getEntity();

}
