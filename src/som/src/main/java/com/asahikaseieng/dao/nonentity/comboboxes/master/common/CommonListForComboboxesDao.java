/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.master.common;

import java.util.List;

/**
 * CommonListForComboboxesDaoクラス
 * @author t0011036
 */
public interface CommonListForComboboxesDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.comboboxes.master.common.CommonListForComboboxes.class;

	/** ARGSアノテーション getListForComboboxes */
	String getListForComboboxes_ARGS = "";

	/**
	 * CommonListForComboboxesメソッド
	 * 
	 * @return List<CommonListForComboboxes>
	 */
	List<CommonListForComboboxes> getListForComboboxes();
}
