/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.slipsales;

import java.util.List;

/**
 * SlipSalesCarryListForComboboxesDaoクラス
 * @author 
 */
public interface SlipSalesCarryListForComboboxesDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.comboboxes.slipsales.SlipSalesCarryListForComboboxes.class;

	/** ARGSアノテーション getListForComboboxes */
	String getListForComboboxes_ARGS = "";

	/**
	 * Listメソッド
	 * @return List<SlipSalesCarryListForComboboxes>
	 */
	List<SlipSalesCarryListForComboboxes> getListForComboboxes();
}
