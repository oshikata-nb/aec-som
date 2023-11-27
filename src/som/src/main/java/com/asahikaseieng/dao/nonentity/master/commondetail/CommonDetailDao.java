/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.commondetail;

/**
 * CommonDetailDaoクラス
 * @author t0011036
 */
public interface CommonDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.commondetail.CommonDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "commonCd";

	/**
	 * CommonDetailメソッド
	 * 
	 * @param commoncd commoncd
	 * @return CommonDetail
	 */
	CommonDetail getEntity(final String commoncd);
}
