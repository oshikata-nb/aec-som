/*
 * Created on 2009/05/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repPurchaseOrderDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * RepPurchaseOrderDetailDaoクラス
 * @author kanri-user
 */
public interface RepPurchaseOrderDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.repPurchaseOrderDetail.RepPurchaseOrderDetail.class;

	/** ARGSアノテーション getPurchaseOrderList */
	String getPurchaseOrderDetailList_ARGS = "directionNo, buySubcontractOrderNo";

	/**
	 * Listメソッド
	 * 
	 * @param directionno directionno
	 * @param buySubcontractOrderNo buySubcontractOrderNo
	 * @return List
	 */
	List<RepPurchaseOrderDetail> getPurchaseOrderDetailList(
			final Object directionno,
			final ArrayList<String> buySubcontractOrderNo);
}
