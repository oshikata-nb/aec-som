/*
 * Created on 2009/01/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.carryfilelist;

import java.util.List;

/**
 * CarryListDaoクラス
 * @author kiguchi
 */
public interface CarryFileListDao {

	/** BEANアノテーション */
	@SuppressWarnings("rawtypes")
	Class BEAN = com.asahikaseieng.dao.nonentity.master.carryfilelist.CarryFileList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<CarryList>
	 */
	List<CarryFileList> getList(final CarryFileListPagerCondition condition);
}
