/*
 * Created on 2009/04/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.master.operationgroup;

import java.util.List;

/**
 * OperationGroupListForComboboxesDaoクラス
 * @author t0011036
 */
public interface OperationGroupListForComboboxesDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.comboboxes.master.operationgroup.
		OperationGroupListForComboboxes.class;

	/** ARGSアノテーション getListForComboboxes */
	String getListForComboboxes_ARGS = "";

	/**
	 * OperationGroupListForComboboxesメソッド
	 * 
	 * @return OperationGroupListForComboboxes
	 */
	List<OperationGroupListForComboboxes> getListForComboboxes();
}
