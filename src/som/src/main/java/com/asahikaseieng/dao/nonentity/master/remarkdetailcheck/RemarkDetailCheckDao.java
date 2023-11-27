/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.remarkdetailcheck;

/**
 * RemarkDetailCheckDaoクラス
 * @author kanri-user
 */
public interface RemarkDetailCheckDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.remarkdetailcheck.RemarkDetailCheck.class;

	/** ARGSアノテーション getRemarkDetailCheck */
	String getRemarkDetailCheck_ARGS = "venderDivision,venderCd, deliveryCd, itemCd";

	/**
	 * RemarkDetailCheckメソッド
	 * 
	 * @param venderDivision venderDivision
	 * @param venderCd venderCd
	 * @param deliveryCd deliveryCd
	 * @param itemCd itemCd
	 * @return RemarkDetailCheck
	 */
	RemarkDetailCheck getRemarkDetailCheck(final String venderDivision,
			final String venderCd, final String deliveryCd, final String itemCd);
}
