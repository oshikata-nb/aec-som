/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.locationlowerlist;

import java.util.List;

/**
 * LocationLowerListDaoクラス
 * @author t0011036
 */
public interface LocationLowerListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.locationlowerlist.LocationLowerList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "upperLocationCd";

	/**
	 * Listメソッド
	 * 
	 * @param upperLocationCd upperLocationCd
	 * @return List<LocationLowerList>
	 */
	List<LocationLowerList> getList(final String upperLocationCd);
}
