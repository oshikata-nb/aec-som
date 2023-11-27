/*
 * Created on 2009/05/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemremarkdelete;

/**
 * ItemRemarkDeleteDaoクラス
 * @author t0011036
 */
public interface ItemRemarkDeleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.itemremarkdelete.ItemRemarkDelete.class;

	/** ARGSアノテーション delete */
	String delete_ARGS = "itemCd, version";

	/**
	 * ItemRemarkDeleteメソッド
	 * 
	 * @param itemCd itemCd
	 * @param version version
	 * @return int
	 */
	int delete(final String itemCd, final java.math.BigDecimal version);
}
