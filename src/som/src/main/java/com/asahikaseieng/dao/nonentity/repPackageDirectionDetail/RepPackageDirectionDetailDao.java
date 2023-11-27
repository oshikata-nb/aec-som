/*
 * Created on 2009/05/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repPackageDirectionDetail;

import java.util.List;

/**
 * RepPackageDirectionDetailDaoクラス
 * @author kanri-user
 */
public interface RepPackageDirectionDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.repPackageDirectionDetail.RepPackageDirectionDetail.class;

	/** ARGSアノテーション getPackageDetailList */
	String getPackageDetailList_ARGS = "directionNo";

	/**
	 * Listメソッド
	 * 
	 * @param directionno directionno
	 * @return List
	 */
	List<RepPackageDirectionDetail> getPackageDetailList(
			final Object directionno);
}
