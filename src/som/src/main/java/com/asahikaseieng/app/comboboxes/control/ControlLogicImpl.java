/*
 * Created on 2008/05/07
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.comboboxes.control;

import java.util.List;

import com.asahikaseieng.dao.nonentity.comboboxes.control.Control;
import com.asahikaseieng.dao.nonentity.comboboxes.control.ControlDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 操作マスタ ロジック実装クラス
 * @author TanakaSatoru
 */
public class ControlLogicImpl implements ControlLogic {

	private ControlDao controlDao;

	/**
	 * コンストラクタ.
	 */
	public ControlLogicImpl() {
	}

	/**
	 * 操作マスタ検索処理を行う
	 * @throws NoDataException NoDataException
	 * @return List<Menu> 操作リスト
	 */
	public List<Control> getEntity() throws NoDataException {
		List<Control> bean = controlDao.getEntity();

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/* -------------------- setter -------------------- */

	/**
	 * controlDaoを設定します。
	 * @param controlDao ControlDao
	 */
	public void setControlDao(final ControlDao controlDao) {
		this.controlDao = controlDao;
	}

}
