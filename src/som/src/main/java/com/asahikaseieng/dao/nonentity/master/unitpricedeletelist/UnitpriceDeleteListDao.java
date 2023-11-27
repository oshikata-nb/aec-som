/*
 * Created on 2009/05/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.unitpricedeletelist;

import java.math.BigDecimal;

/**
 * UnitpriceDeleteListDaoクラス
 * @author t0011036
 */
public interface UnitpriceDeleteListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.unitpricedeletelist.UnitpriceDeleteList.class;

	/** ARGSアノテーション deleteList */
	String deleteList_ARGS = "venderDivision, venderCd, itemCd, version";

	/**
	 * deleteListメソッド
	 * 
	 * @param venderDivision venderDivision
	 * @param venderCd venderCd
	 * @param itemCd itemCd
	 * @param version version
	 * @return int
	 */
	int deleteList(final String venderDivision, final String venderCd,
			final String itemCd, final BigDecimal version);
}
