/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.taxmasterlist;

import java.math.BigDecimal;


/**
 * LocationListDaoクラス
 * @author t0011036
 */
public interface TaxMasterListDao {

	/** BEANアノテーション */
	Class<TaxMasterList> BEAN = com.asahikaseieng.dao.nonentity.master.taxmasterlist.TaxMasterList.class;

	/** ARGSアノテーション getTaxRatio */
	String getTaxRatio_ARGS = "taxCd";
	
	/**
	 * メソッド
	 * 
	 * @param taxCd
	 * @return taxRatio
	 */
	BigDecimal getTaxRatio(final String taxCd);
	
	/** ARGSアノテーション getDisplayReport */
	String getDisplayReport_ARGS = "taxCd";
	
	/**
	 * メソッド
	 * 
	 * @param taxCd
	 * @return displayReport
	 */
	String getDisplayReport(final String taxCd);
}
