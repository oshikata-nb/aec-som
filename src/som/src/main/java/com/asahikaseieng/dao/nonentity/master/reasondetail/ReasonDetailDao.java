/*
 * Created on 2009/03/31
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.reasondetail;

/**
 * ReasonDetailDaoクラス
 * @author t0011036
 */
public interface ReasonDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.reasondetail.ReasonDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "ryCd";

	/**
	 * ReasonDetailメソッド
	 * 
	 * @param ryCd ryCd
	 * @return ReasonDetail
	 */
	ReasonDetail getEntity(final String ryCd);
}
