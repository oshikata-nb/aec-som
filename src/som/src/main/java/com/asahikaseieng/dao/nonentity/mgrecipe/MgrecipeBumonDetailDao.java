/*
 * Created on 2009/06/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.mgrecipe;

/**
 * MgrecipeBumonDetailDaoクラス
 * @author t0011036
 */
public interface MgrecipeBumonDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeBumonDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "sectionCd";

	/**
	 * MgrecipeBumonDetailメソッド
	 * 
	 * @param sectionCd sectionCd
	 * @return MgrecipeBumonDetail
	 */
	MgrecipeBumonDetail getEntity(final String sectionCd);
}
