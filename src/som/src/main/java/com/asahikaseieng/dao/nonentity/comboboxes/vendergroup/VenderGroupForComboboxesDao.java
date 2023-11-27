/*
 * Created on 2020/07/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.vendergroup;

import java.util.List;

/**
 * VenderGroupForComboboxesDaoクラス
 * @author 
 */
public interface VenderGroupForComboboxesDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.comboboxes.vendergroup.VenderGroupForComboboxes.class;

	/** ARGSアノテーション getListForComboboxes */
	String getListForComboboxes_ARGS = "";

	/**
	 * Listメソッド
	 * @return List<VenderGroupForComboboxes>
	 */
	List<VenderGroupForComboboxes> getListForComboboxes();
}
