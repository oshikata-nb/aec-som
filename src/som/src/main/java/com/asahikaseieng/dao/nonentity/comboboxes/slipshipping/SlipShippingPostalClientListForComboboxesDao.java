/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.slipshipping;

import java.util.List;

/**
 * SlipShippingPostalClientListForComboboxesDaoクラス
 * @author 
 */
public interface SlipShippingPostalClientListForComboboxesDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.comboboxes.slipshipping.SlipShippingPostalClientListForComboboxes.class;

	/** ARGSアノテーション getListForComboboxes */
	String getListForComboboxes_ARGS = "";

	/**
	 * Listメソッド
	 * @return List<SlipShippingCarryListForComboboxes>
	 */
	List<SlipShippingPostalClientListForComboboxes> getListForComboboxes();
}
