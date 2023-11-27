/*
 * Created on 2009/05/08
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.financialclassdetail;

/**
 * FinancialClassDetailDaoクラス
 * @author t0011036
 */
public interface FinancialClassDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.financialclassdetail.FinancialClassDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "financialClassCd";

	/**
	 * FinancialClassDetailメソッド
	 * 
	 * @param financialClassCd financialClassCd
	 * @return FinancialClassDetail
	 */
	FinancialClassDetail getEntity(final String financialClassCd);
}
