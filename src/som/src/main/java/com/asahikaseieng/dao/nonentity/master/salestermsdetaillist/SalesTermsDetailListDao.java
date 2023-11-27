/*
 * Created on 2009/03/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.salestermsdetaillist;

import java.util.List;

/**
 * SalesTermsDetailListDaoクラス
 * @author t0011036
 */
public interface SalesTermsDetailListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.salestermsdetaillist.SalesTermsDetailList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "deliveryCd, balanceCd";

	/**
	 * Listメソッド
	 * 
	 * @param deliveryCd deliveryCd
	 * @param balanceCd balanceCd
	 * @return List<SalesTermsDetailList>
	 */
	List<SalesTermsDetailList> getList(final String deliveryCd,
			final String balanceCd);
}
