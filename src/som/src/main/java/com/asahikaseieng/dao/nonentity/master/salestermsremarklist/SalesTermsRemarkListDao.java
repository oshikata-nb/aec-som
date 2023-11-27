/*
 * Created on 2009/12/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.salestermsremarklist;

import java.util.List;

/**
 * SalesTermsRemarkListDaoクラス
 * @author kanri-user
 */
public interface SalesTermsRemarkListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.salestermsremarklist.SalesTermsRemarkList.class;

	/** ARGSアノテーション getRemarkListWithItem */
	String getRemarkListWithItem_ARGS = "itemCd, venderCd, deliveryCd";

	/**
	 * SalesTermsRemarkListメソッド
	 * 
	 * @param itemCd itemCd
	 * @param venderCd venderCd
	 * @param deliveryCd deliveryCd
	 * @return SalesTermsRemarkList
	 */
	List<SalesTermsRemarkList> getRemarkListWithItem(final String itemCd,
			final String venderCd, final String deliveryCd);

	/** ARGSアノテーション getRemarkListWithItem */
	String getRemarkListNoItem_ARGS = "venderCd, deliveryCd";

	/**
	 * SalesTermsRemarkListメソッド
	 * 
	 * @param venderCd venderCd
	 * @param deliveryCd deliveryCd
	 * @return SalesTermsRemarkList
	 */
	List<SalesTermsRemarkList> getRemarkListNoItem(final String venderCd,
			final String deliveryCd);

}
