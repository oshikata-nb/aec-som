/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.salestermsdelete;

/**
 * SalesTermsDeleteDaoクラス
 * @author t0011036
 */
public interface SalesTermsDeleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.salestermsdelete.SalesTermsDelete.class;

	/** ARGSアノテーション deleteList */
	String deleteList_ARGS = "deliveryCd,balanceCd";

	/**
	 * deleteListメソッド
	 * 
	 * @param deliveryCd deliveryCd
	 * @param balanceCd balanceCd
	 * @return int
	 */
	int deleteList(final String deliveryCd, final String balanceCd);
}
