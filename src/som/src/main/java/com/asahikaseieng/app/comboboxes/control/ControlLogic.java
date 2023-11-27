/*
 * Created on 2008/05/07
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.comboboxes.control;

import java.util.List;

import com.asahikaseieng.dao.nonentity.comboboxes.control.Control;
import com.asahikaseieng.exception.NoDataException;

/**
 * 操作マスタ　検索ロジッククラス interface.
 * @author tosco
 */
public interface ControlLogic {

	/**
	 * 操作マスタ検索処理を行う
	 * @throws NoDataException NoDataException
	 * @return List<Control> 操作リスト
	 */
	List<Control> getEntity() throws NoDataException;

}
