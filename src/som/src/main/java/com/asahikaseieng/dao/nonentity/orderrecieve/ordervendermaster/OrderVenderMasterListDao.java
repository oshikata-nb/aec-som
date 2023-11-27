/*
 * Created on 2020/07/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderrecieve.ordervendermaster;

import java.util.List;

/**
 * OrderVenderLinkListDaoクラス
 * @author 
 */
public interface OrderVenderMasterListDao {

	/** BEANアノテーション */
	Class<OrderVenderMasterList> BEAN = com.asahikaseieng.dao.nonentity.orderrecieve.ordervendermaster.OrderVenderMasterList.class;
	
	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<OrderVenderMasterList>
	 */
	List<OrderVenderMasterList> getList(final OrderVenderMasterListPagerCondition condition);
	
	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";

	/**
	 * Listメソッド
	 * 
	 * @param condition condition
	 * @return List<OrderVenderMasterList>
	 */
	List<OrderVenderMasterList> getSearchList(final OrderVenderMasterListPagerCondition condition);

}
