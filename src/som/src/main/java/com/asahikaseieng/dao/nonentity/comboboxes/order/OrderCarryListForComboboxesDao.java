/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.order;

import java.util.List;

/**
 * OrderCarryListForComboboxesDaoクラス
 * @author 
 */
public interface OrderCarryListForComboboxesDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.comboboxes.order.OrderCarryListForComboboxes.class;

	/** ARGSアノテーション getListForComboboxes */
	String getListForComboboxes_ARGS = "";

	/**
	 * Listメソッド
	 * @return List<OrderCarryListForComboboxes>
	 */
	List<OrderCarryListForComboboxes> getListForComboboxes();
}
