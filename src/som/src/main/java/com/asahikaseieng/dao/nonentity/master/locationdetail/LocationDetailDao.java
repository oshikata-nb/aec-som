/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.locationdetail;

/**
 * LocationDetailDaoクラス
 * @author t0011036
 */
public interface LocationDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.locationdetail.LocationDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "locationCd";

	/**
	 * LocationDetailメソッド
	 * 
	 * @param locationCd locationCd
	 * @return LocationDetail
	 */
	LocationDetail getEntity(final String locationCd);
}
