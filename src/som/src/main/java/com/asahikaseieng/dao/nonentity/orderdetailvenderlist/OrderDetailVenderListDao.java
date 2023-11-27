/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderdetailvenderlist;

import java.util.List;

/**
 * OrderDetailVenderListDaoクラス
 * @author
 */
public interface OrderDetailVenderListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.orderdetailvenderlist.OrderDetailVenderList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "balanceCd";

	/**
	 * Listメソッド
	 * 
	 * @param balanceCd balanceCd
	 * @return List<OrderDetailVenderList>
	 */
	List<OrderDetailVenderList> getList(final String balanceCd);
}
