/*
 * Created on 2009/07/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemupdate;

import java.math.BigDecimal;

/**
 * ItemUpdateDaoクラス
 * @author t0011036
 */
public interface ItemUpdateDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.itemupdate.ItemUpdate.class;

	/** ARGSアノテーション update */
	String update_ARGS = "itemCd, version";

	/**
	 * ItemUpdateメソッド
	 * 
	 * @param itemCd itemCd
	 * @param version version
	 * @return int
	 */
	int update(final String itemCd, final BigDecimal version);
}
