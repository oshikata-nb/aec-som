/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.componentdetail;

/**
 * ComponentDetailDaoクラス
 * @author t0011036
 */
public interface ComponentDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.componentdetail.ComponentDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "componentCd";

	/**
	 * ComponentDetailメソッド
	 * 
	 * @param componentCd componentCd
	 * @return ComponentDetail
	 */
	ComponentDetail getEntity(final String componentCd);
}
