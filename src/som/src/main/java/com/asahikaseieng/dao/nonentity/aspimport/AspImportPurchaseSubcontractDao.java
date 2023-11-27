/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.aspimport;

import java.util.List;

/**
 * AspImportPurchaseSubcontractDaoクラス
 * @author t0011036
 */
public interface AspImportPurchaseSubcontractDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.aspimport.AspImportPurchaseSubcontract.class;

	/** ARGSアノテーション deleteAspData */
	String deleteAspData_ARGS = "";

	/**
	 * deleteAspDataメソッド
	 * 
	 * @return int
	 */
	int deleteAspData();

	/** ARGSアノテーション getDeleteAspData */
	String getDeleteAspData_ARGS = "";

	/**
	 * getDeleteAspDataメソッド
	 * 
	 * @return int
	 */
	List<AspImportPurchaseSubcontract> getDeleteAspData();

	/** ARGSアノテーション getPurchaseNo */
	String getPurchaseNo_ARGS = "buySubcontractOrderNo";
	/**
	 * 新規登録時、発注番号にて購買NOの検索を行う.
	 * @param buySubcontractOrderNo 発注番号
	 * @return AspImportPurchaseSubcontract
	 */
	AspImportPurchaseSubcontract getPurchaseNo(final String buySubcontractOrderNo);
}
