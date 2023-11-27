/*
 * Created on 2009/06/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.controlauthoritylist;

import java.math.BigDecimal;
import java.util.List;

/**
 * ControlAuthorityListDaoクラス
 * @author t0011036
 */
public interface ControlAuthorityListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.controlauthoritylist.ControlAuthorityList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "tantoCd, organizationCd, postId, menuId, tabId, controlId";

	/**
	 * Listメソッド
	 * 
	 * @param tantoCd tantoCd
	 * @param menuId menuId
	 * @param organizationCd organizationCd
	 * @param postId postId
	 * @param tabId tabId
	 * @param controlId controlId
	 * @return List<ControlAuthorityList>
	 */
	List<ControlAuthorityList> getList(final String tantoCd,
			final String organizationCd, final BigDecimal postId,
			final BigDecimal menuId, final BigDecimal tabId,
			final BigDecimal controlId);
}
