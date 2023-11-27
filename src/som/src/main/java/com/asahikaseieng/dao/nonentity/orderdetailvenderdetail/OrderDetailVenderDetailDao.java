/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderdetailvenderdetail;


/**
 * OrderDetailVenderDetailDaoクラス
 * @author
 */
public interface OrderDetailVenderDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.orderdetailvenderdetail.OrderDetailVenderDetail.class;

	/** ARGSアノテーション getEntity */
	String getList_ARGS = "venderCd";

	/**
	 * Listメソッド
	 * 
	 * @param venderCd venderCd
	 * @return OrderDetailVenderDetail
	 */
	OrderDetailVenderDetail getEntity(final String venderCd);
}
