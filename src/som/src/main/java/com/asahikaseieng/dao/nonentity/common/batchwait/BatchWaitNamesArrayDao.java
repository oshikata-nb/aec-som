/*
 * Created on 2014/03/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.common.batchwait;

import java.util.List;

/**
 * BatchWaitNamesArrayDaoクラス
 * @author atts
 */
public interface BatchWaitNamesArrayDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.common.batchwait.BatchWaitNamesArray.class;

	/** ARGSアノテーション getEntityList */
	String getEntityList_ARGS = "menuId,num,namesdivision";

	/**
	 * Listメソッド
	 * 
	 * @param menuId メニューID
	 * @param num 枝番
	 * @param namesdivision 名称区分
	 * @return List<BatchWaitNamesArray>
	 */
	List<BatchWaitNamesArray> getEntityList(final String menuId,
			final String num, final String namesdivision);
}
