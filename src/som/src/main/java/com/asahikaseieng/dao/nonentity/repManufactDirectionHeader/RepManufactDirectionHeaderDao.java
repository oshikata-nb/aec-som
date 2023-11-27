/*
 * Created on 2009/05/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repManufactDirectionHeader;

import java.util.List;

/**
 * RepManufactDirectionHeaderDaoクラス
 * @author kanri-user
 */
public interface RepManufactDirectionHeaderDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.repManufactDirectionHeader.RepManufactDirectionHeader.class;

	/** ARGSアノテーション getDirectionList */
	String getDirectionList_ARGS = "directionNo";

	/**
	 * Listメソッド
	 * 
	 * @param directionNo directionNo
	 * @return List<RepManufactDirectionHeader>
	 */
	List<RepManufactDirectionHeader> getDirectionList(final String[] directionNo);
}
