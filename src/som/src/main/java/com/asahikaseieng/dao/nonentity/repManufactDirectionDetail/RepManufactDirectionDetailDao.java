/*
 * Created on 2009/05/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repManufactDirectionDetail;

import java.util.List;

/**
 * RepManufactDirectionDetailDaoクラス
 * @author kanri-user
 */
public interface RepManufactDirectionDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.repManufactDirectionDetail.RepManufactDirectionDetail.class;

	/** ARGSアノテーション getDirectionDetailList */
	String getDirectionDetailList_ARGS = "directionNo";

	/**
	 * Listメソッド
	 * 
	 * @param directionNo directionNo
	 * @return List<RepManufactDirectionDetail>
	 */
	List<RepManufactDirectionDetail> getDirectionDetailList(
			final String[] directionNo);
}
