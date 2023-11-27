/*
 * Created on 2009/05/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.viewauthoritylist;

import java.math.BigDecimal;
import java.util.List;

/**
 * ViewAuthorityListDaoクラス
 * @author t0011036
 */
public interface ViewAuthorityListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.viewauthoritylist.ViewAuthorityList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "tantoCd, menuId, tabId";

	/**
	 * Listメソッド
	 * 
	 * @param tantoCd tantoCd
	 * @param menuId menuId
	 * @param tabId tabId
	 * @return List<ViewAuthorityList>
	 */
	List<ViewAuthorityList> getList(final String tantoCd,
			final BigDecimal menuId, final BigDecimal tabId);
}
