/*
 * Created on 2009/05/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.changehistorylist;

import java.math.BigDecimal;
import java.util.List;

/**
 * ChangeHistoryListDaoクラス
 * @author t0011036
 */
public interface ChangeHistoryListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.changehistorylist.ChangeHistoryList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "menuId, itemCd";

	/**
	 * Listメソッド
	 * 
	 * @param menuId menuId
	 * @param itemCd itemCd
	 * @return List<ChangeHistoryList>
	 */
	List<ChangeHistoryList> getList(final BigDecimal menuId, final String itemCd);
}
