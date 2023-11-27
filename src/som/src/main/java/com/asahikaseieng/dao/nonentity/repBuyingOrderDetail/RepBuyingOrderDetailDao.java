/*
 * Created on 2009/06/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repBuyingOrderDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * RepBuyingOrderDetailDaoクラス
 * @author kanri-user
 */
public interface RepBuyingOrderDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.repBuyingOrderDetail.RepBuyingOrderDetail.class;

	/** ARGSアノテーション getBuyingOrderDetailList */
	String getBuyingOrderDetailList_ARGS = "buyingNo";

	/**
	 * Listメソッド
	 * 
	 * @param buyingNo buyingNo
	 * @return List
	 */
	List<RepBuyingOrderDetail> getBuyingOrderDetailList(
			final ArrayList<String> buyingNo);
}
