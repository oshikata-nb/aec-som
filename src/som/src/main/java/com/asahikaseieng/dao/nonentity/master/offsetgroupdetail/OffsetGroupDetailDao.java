/*
 * Created on 2009/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.offsetgroupdetail;

import java.util.List;

/**
 * OffsetGroupDetailDaoクラス
 * @author t0011036
 */
public interface OffsetGroupDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.offsetgroupdetail.OffsetGroupDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "offsetGroupCd";

	/**
	 * OffsetGroupDetailメソッド
	 * 
	 * @param offsetGroupCd offsetGroupCd
	 * @return List<offsetGroupDetail>
	 */
	List<OffsetGroupDetail> getEntity(final String offsetGroupCd);
}
