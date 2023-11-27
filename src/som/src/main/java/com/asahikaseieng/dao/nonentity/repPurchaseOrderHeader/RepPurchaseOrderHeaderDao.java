/*
 * Created on 2009/05/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repPurchaseOrderHeader;

import java.util.ArrayList;
import java.util.List;

/**
 * RepPurchaseOrderHeaderDaoクラス
 * @author kanri-user
 */
public interface RepPurchaseOrderHeaderDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.repPurchaseOrderHeader.RepPurchaseOrderHeader.class;

	/** ARGSアノテーション getPurchaseOrderList */
	String getPurchaseOrderList_ARGS = "directionNo, buySubcontractOrderNo";

	/**
	 * Listメソッド
	 * 
	 * @param directionno directionno
	 * @param buySubcontractOrderNo buySubcontractOrderNo
	 * @return List
	 */
	List<RepPurchaseOrderHeader> getPurchaseOrderList(final Object directionno,
			final ArrayList<String> buySubcontractOrderNo);
}
