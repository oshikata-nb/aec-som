/*
 * Created on 2008/04/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.comboboxes.menu;

import java.util.List;

import com.asahikaseieng.dao.nonentity.comboboxes.menu.Menu;
import com.asahikaseieng.exception.NoDataException;

/**
 * メニューマスタ　検索ロジッククラス interface.
 * @author tosco
 */
public interface MenuComboLogic {

	/**
	 * メニューマスタ検索処理を行う
	 * @throws NoDataException NoDataException
	 * @return List<Menu> メニューリスト
	 */
	List<Menu> getSearchList() throws NoDataException;

}
