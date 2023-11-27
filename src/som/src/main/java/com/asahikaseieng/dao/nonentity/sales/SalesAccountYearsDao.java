/*
 * Created on 2009/07/07
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.sales;

/**
 * SalesAccountYearsDaoクラス
 * @author kanri-user
 */
public interface SalesAccountYearsDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.sales.SalesAccountYears.class;

	/** ARGSアノテーション getAccountYearsData */
	String getAccountYearsData_ARGS = "orderNo, venderCd, salesDate";

	/**
	 * SalesAccountYearsメソッド
	 * 
	 * @param orderNo orderNo
	 * @param venderCd venderCd
	 * @param salesDate salesDate
	 * @return SalesAccountYears
	 */
	SalesAccountYears getAccountYearsData(final String orderNo,
			final String venderCd, final String salesDate);
}
