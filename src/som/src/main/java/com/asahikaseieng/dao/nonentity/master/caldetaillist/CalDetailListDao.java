/*
 * Created on 2009/04/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.caldetaillist;

import java.math.BigDecimal;
import java.util.List;

/**
 * CalDetailListDaoクラス
 * @author t0011036
 */
public interface CalDetailListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.caldetaillist.CalDetailList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "calCd, calYear";

	/**
	 * Listメソッド
	 * 
	 * @param calCd calCd
	 * @param calYear calYear
	 * @return List<CalDetailList>
	 */
	List<CalDetailList> getList(final String calCd, final BigDecimal calYear);
}
