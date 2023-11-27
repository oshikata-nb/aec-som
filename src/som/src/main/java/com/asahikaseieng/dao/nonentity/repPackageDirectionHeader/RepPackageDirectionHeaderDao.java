/*
 * Created on 2009/05/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repPackageDirectionHeader;

import java.util.List;

/**
 * RepPackageDirectionHeaderDaoクラス
 * @author kanri-user
 */
public interface RepPackageDirectionHeaderDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.repPackageDirectionHeader.RepPackageDirectionHeader.class;

	/** ARGSアノテーション getHeaderList */
	String getHeaderList_ARGS = "directionNo";

	/**
	 * Listメソッド
	 * 
	 * @param directionno directionno
	 * @return List
	 */
	List<RepPackageDirectionHeader> getHeaderList(final Object directionno);
}
