/*
 * Created on 2008/04/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.comboboxes.menu;

import java.util.List;

import com.asahikaseieng.dao.nonentity.comboboxes.menu.Menu;
import com.asahikaseieng.dao.nonentity.comboboxes.menu.MenuComboDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * メニューマスタ ロジック実装クラス
 * @author tosco
 */
public class MenuComboLogicImpl implements MenuComboLogic {

	private MenuComboDao menuComboDao;

	/**
	 * コンストラクタ.
	 */
	public MenuComboLogicImpl() {
	}

	/**
	 * メニューマスタ検索処理を行う
	 * @throws NoDataException NoDataException
	 * @return List<Menu> ロールリスト
	 */
	public List<Menu> getSearchList() throws NoDataException {
		List<Menu> bean = menuComboDao.getSearchList();

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/* -------------------- setter -------------------- */

	/**
	 * menuDaoを設定します。
	 * @param menuComboDao MenuComboDao
	 */
	public void setMenuDao(final MenuComboDao menuComboDao) {
		this.menuComboDao = menuComboDao;
	}

}
