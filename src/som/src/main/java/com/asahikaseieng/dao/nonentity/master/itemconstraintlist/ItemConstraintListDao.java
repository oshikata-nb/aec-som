/*
 * Created on 2007/10/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemconstraintlist;

import java.util.List;

/**
 * ItemConstraintListDaoクラス
 * @author t1344224
 */
public interface ItemConstraintListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.itemconstraintlist.ItemConstraintList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "itemCd, version";

	/**
	 * Listメソッド
	 * 
	 * @param itemcd itemcd
	 * @param version version
	 * @return ItemConstraintListリスト
	 */
	List<ItemConstraintList> getList(final Object itemcd, final Object version);
}
