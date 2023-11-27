/*
 * Created on 2009/03/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.varidunitprice;

import java.math.BigDecimal;

/**
 * VaridUnitpriceDaoクラス
 * @author kanri-user
 */
public interface VaridUnitpriceDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.varidunitprice.VaridUnitprice.class;

	/** ARGSアノテーション getUnitPrice */
	String getUnitPrice_ARGS = "balanceCd,itemCd,procDate,status";

	/**
	 * VaridUnitpriceメソッド
	 * 
	 * @param itemCd itemCd
	 * @param balanceCd balanceCd
	 * @param procDate procDate
	 * @param status status
	 * @return VaridUnitprice
	 */
	VaridUnitprice getUnitPrice(final String balanceCd, final String itemCd,
			final String procDate, final BigDecimal status);
}
