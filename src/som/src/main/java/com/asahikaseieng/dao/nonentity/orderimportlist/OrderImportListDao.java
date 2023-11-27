/*
 * Created on 2020/09/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderimportlist;

import java.math.BigDecimal;
import java.util.List;

/**
 * OrderImportListDaoクラス
 * @author 
 */
public interface OrderImportListDao {

	/** BEANアノテーション */
	Class<OrderImportList> BEAN = com.asahikaseieng.dao.nonentity.orderimportlist.OrderImportList.class;

	/** ARGSアノテーション geSearchtList */
	String getSearchList_ARGS = "condition";

	/**
	 * Listメソッド
	 * 
	 * @param condition condition
	 * @return List
	 */
	List<OrderImportList> getSearchList(OrderImportListPagerCondition condition);

	
}
