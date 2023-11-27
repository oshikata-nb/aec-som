/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.master.names;

import java.util.List;

/**
 * NamesListDaoクラス
 * @author t0011036
 */
public interface NamesListForComboboxesDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.comboboxes.master.names.NamesListForComboboxes.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "nameDivision";

	/**
	 * Listメソッド
	 * 
	 * @param nameDivision nameDivision
	 * @return List<NamesListForComboboxes>
	 */
	List<NamesListForComboboxes> getListForComboboxes(final String nameDivision);
}
