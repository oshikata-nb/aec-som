/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.linedetail;

/**
 * LineDetailDaoクラス
 * @author t0011036
 */
public interface LineDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.linedetail.LineDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "productionLine";

	/**
	 * LineDetailメソッド
	 * 
	 * @param productionLine productionLine
	 * @return LineDetail
	 */
	LineDetail getEntity(final String productionLine);
}
