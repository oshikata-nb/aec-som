/*
 * Created on 2008/04/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.menu;

import java.util.List;

/**
 * メニューマスタDaoインターフェース.
 * @author tosco
 */
public interface MenuComboDao {

	/** BEANアノテーション. */
	Class BEAN = com.asahikaseieng.dao.nonentity.comboboxes.menu.Menu.class;

	//
	// インスタンスメソッド
	//

	/**
	 * ロールリスト取得.
	 * @return List<Menu> ロールのリスト
	 */
	List<Menu> getSearchList();

}
