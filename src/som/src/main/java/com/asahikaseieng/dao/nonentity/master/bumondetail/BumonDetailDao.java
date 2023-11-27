/*
 * Created on 2009/01/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.bumondetail;

/**
 * BumonDetailDaoクラス
 * @author t0011036
 */
public interface BumonDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.bumondetail.BumonDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "sectionCd";

	/**
	 * BumonDetailメソッド
	 * 
	 * @param sectionCd sectionCd
	 * @return BumonDetail
	 */
	BumonDetail getEntity(final String sectionCd);
}
