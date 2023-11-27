/*
 * Created on 2009/01/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.carryfiledetail;

import java.util.List;

/**
 * CarryDetailDaoクラス
 * @author t0011036
 */
public interface CarryFileComboboxItemsDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.carryfiledetail.CarryFileComboboxItems.class;

	/**
	 * CarryDetailメソッド
	 * 
	 * @param carryCd carryCd
	 * @return CarryDetail
	 */
	List<CarryFileComboboxItems> getComboboxItems();

}
