/*
 * Created on 2009/05/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repShippingSlipOrderList;

import java.util.ArrayList;
import java.util.List;

/**
 * RepShippingSlipOrderListDaoクラス
 * @author kanri-user
 */
public interface RepShippingSlipOrderListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.repShippingSlipOrderList.RepShippingSlipOrderList.class;

	/** ARGSアノテーション getShippingList */
	String getShippingList_ARGS = "shippingNo";

	/**
	 * Listメソッド
	 * 
	 * @param shippingNo shippingNo
	 * @return List<RepShippingSlipOrderList>
	 */
	List<RepShippingSlipOrderList> getShippingList(
			final ArrayList<String> shippingNo);
}
