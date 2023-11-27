/*
 * Created on 2009/06/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repLabelProductDirection;

import java.util.List;

/**
 * RepLabelProductDirectionDaoクラス
 * @author kanri-user
 */
public interface RepLabelProductDirectionDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.repLabelProductDirection.RepLabelProductDirection.class;

	/** ARGSアノテーション getLabelProductDirectionList */
	String getLabelProductDirectionList_ARGS = "directionNo";

	/**
	 * Listメソッド
	 * 
	 * @param directionno directionno
	 * @return List
	 */
	List<RepLabelProductDirection> getLabelProductDirectionList(
			final String[] directionno);
}
