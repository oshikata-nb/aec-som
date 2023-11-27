/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.master.carry;

import java.util.List;

/**
 * CarryListForComboboxesDaoクラス
 * @author t0011036
 */
public interface CarryListForComboboxesDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.comboboxes.master.carry.CarryListForComboboxes.class;

	/** ARGSアノテーション getListForComboboxes */
	String getListForComboboxes_ARGS = "";

	/**
	 * Listメソッド
	 * @return List<CarryListForComboboxes>
	 */
	List<CarryListForComboboxes> getListForComboboxes();
}
