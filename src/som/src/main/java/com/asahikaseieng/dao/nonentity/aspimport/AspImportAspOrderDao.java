/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.aspimport;

import java.util.List;

/**
 * AspImportAspOrderDaoクラス
 * @author t0011036
 */
public interface AspImportAspOrderDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.aspimport.AspImportAspOrder.class;

	/** ARGSアノテーション getPurchaseData */
	String getPurchaseData_ARGS = "";

	/**
	 * getPurchaseDataメソッド
	 * 
	 * @return int
	 */
	List<AspImportAspOrder> getPurchaseData();


	/** ARGSアノテーション getSubcontractData */
	String getSubcontractData_ARGS = "";

	/**
	 * getSubcontractDataメソッド
	 * 
	 * @return int
	 */
	List<AspImportAspOrder> getSubcontractData();


	/** ARGSアノテーション getDirectionData */
	String getDirectionData_ARGS = "";

	/**
	 * getDirectionDataメソッド
	 * 
	 * @return int
	 */
	List<AspImportAspOrder> getDirectionData();


	/** ARGSアノテーション getPkgDirectionData */
	String getPkgDirectionData_ARGS = "";

	/**
	 * getPkgDirectionDataメソッド
	 * 
	 * @return int
	 */
	List<AspImportAspOrder> getPkgDirectionData();
}
