/*
 * Created on 2009/02/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.aspimport;

import java.util.List;

/**
 * AspImportRemarkListDaoインターフェース.
 * @author tosco
 */
public interface AspImportRemarkListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.aspimport.AspImportRemarkList.class;

	//取引先区分、取引先コード・名称で検索-----------------------------------------------------
	/** ARGSアノテーション getRemarkList */
	String getRemarkList_ARGS = "venderCd, deliveryCd, itemCd";
	/**
	 * getRemarkListメソッド
	 *
	 * @param venderCd venderCd
	 * @param deliveryCd deliveryCd
	 * @param itemCd itemCd
	 * @return AspImportRemarkList
	 */
	List<AspImportRemarkList> getRemarkList(
			final String venderCd, final String deliveryCd, final String itemCd);

}
