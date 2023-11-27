/*
 * Created on 2009/08/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.ordernameslist;

import java.util.List;

/**
 * OrderNamesListDaoクラス
 * @author t0011036
 */
public interface OrderNamesListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.ordernameslist.OrderNamesList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "nameDivision";

	/**
	 * Listメソッド
	 * 
	 * @param nameDivision nameDivision
	 * @return List<OrderNamesList>
	 */
	List<OrderNamesList> getList(final String nameDivision);
}
