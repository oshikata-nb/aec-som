/*
 * Created on 2009/05/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.master.line;

import java.util.List;

/**
 * LineListForComboboxesDaoクラス
 * @author t0011036
 */
public interface LineListForComboboxesDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.comboboxes.master.line.LineListForComboboxes.class;

	/** ARGSアノテーション getListForComboboxes */
	String getListForComboboxes_ARGS = "";

	/**
	 * LineListForComboboxesメソッド
	 * 
	 * @return List<LineListForComboboxes>
	 */
	List<LineListForComboboxes> getListForComboboxes();
}
