/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.remarkdetail;

/**
 * RemarkDetailDaoクラス
 * @author kanri-user
 */
public interface RemarkDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.remarkdetail.RemarkDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "remarkNo";

	/**
	 * RemarkDetailメソッド
	 * 
	 * @param remarkNo remarkNo
	 * @return RemarkDetail
	 */
	RemarkDetail getEntity(final java.math.BigDecimal remarkNo);
}
