/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.slipshipping;

import java.util.List;

/**
 * SlipShippingCarryListForComboboxesDaoクラス
 * @author 
 */
public interface SlipShippingCarryListForComboboxesDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.comboboxes.slipshipping.SlipShippingCarryListForComboboxes.class;

	/** ARGSアノテーション getListForComboboxes */
	String getListForComboboxes_ARGS = "";

	/**
	 * Listメソッド
	 * @return List<SlipShippingCarryListForComboboxes>
	 */
	List<SlipShippingCarryListForComboboxes> getListForComboboxes();
}
