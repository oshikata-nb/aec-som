/*
 * Created on 2009/06/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repMaterialDirectionHeader;

import java.util.ArrayList;
import java.util.List;

/**
 * RepMaterialDirectionHeaderDaoクラス
 * @author kanri-user
 */
public interface RepMaterialDirectionHeaderDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.repMaterialDirectionHeader.RepMaterialDirectionHeader.class;

	/** ARGSアノテーション getMaterialDirectionHeaderList */
	String getMaterialDirectionHeaderList_ARGS = "directionNo";

	/**
	 * Listメソッド
	 * 
	 * @param directionno directionno
	 * @return List
	 */
	List<RepMaterialDirectionHeader> getMaterialDirectionHeaderList(
			final ArrayList<String> directionno);
}
