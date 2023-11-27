/*
 * Created on 2009/11/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.monthlyvenderforreport;

import java.util.List;

/**
 * RepMonthlyVenderDaoクラス
 * @author kanri-user
 */
public interface RepMonthlyVenderDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.monthlyvenderforreport.RepMonthlyVender.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "organizationCd, dateFrom, dateTo";

	/**
	 * RepMonthlyVenderメソッド
	 * 
	 * @param organizationCd organizationCd
	 * @param dateFrom dateFrom
	 * @param dateTo dateTo
	 * @return RepMonthlyVender
	 */
	List<RepMonthlyVender> getListForReport(final String organizationCd,
			final String dateFrom, final String dateTo);
}
