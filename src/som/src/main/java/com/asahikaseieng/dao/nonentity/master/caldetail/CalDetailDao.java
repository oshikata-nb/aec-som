/*
 * Created on 2009/05/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.caldetail;

/**
 * CalDetailDaoクラス
 * @author t0011036
 */
public interface CalDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.caldetail.CalDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "calCd";

	/**
	 * CalDetailメソッド
	 * 
	 * @param calCd calCd
	 * @return CalDetail
	 */
	CalDetail getEntity(final String calCd);
}
