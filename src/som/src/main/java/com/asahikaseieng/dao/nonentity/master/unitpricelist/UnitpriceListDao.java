/*
 * Created on 2009/01/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.unitpricelist;

import java.util.List;

/**
 * UnitpriceListDaoクラス
 * @author kanri-user
 */
public interface UnitpriceListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.unitpricelist.UnitpriceList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * 
	 * @param condition condition
	 * @return List<UnitpriceList>
	 */
	List<UnitpriceList> getList(final UnitpriceListPagerCondition condition);

}
