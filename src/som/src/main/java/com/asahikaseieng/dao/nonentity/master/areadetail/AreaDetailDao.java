/*
 * Created on 2009/01/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.areadetail;

/**
 * AreaDetailDaoクラス
 * @author t0011036
 */
public interface AreaDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.areadetail.AreaDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "areaCd";

	/**
	 * AreaDetailメソッド
	 * 
	 * @param areaCd areaCd
	 * @return AreaDetail
	 */
	AreaDetail getEntity(final String areaCd);
}
