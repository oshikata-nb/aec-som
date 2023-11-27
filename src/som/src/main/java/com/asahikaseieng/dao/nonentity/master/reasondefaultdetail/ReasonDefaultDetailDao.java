/*
 * Created on 2009/04/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.reasondefaultdetail;


/**
 * ReasonDefaultDetailDaoクラス
 * @author t0011036
 */
public interface ReasonDefaultDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.reasondefaultdetail.ReasonDefaultDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "";

	/**
	 * ReasonDefaultDetailメソッド
	 *
	 * @return ReasonDefaultDetail
	 */
	ReasonDefaultDetail getEntity(
	);
}
