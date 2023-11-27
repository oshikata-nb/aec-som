/*
 * Created on 2009/07/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repSlipShippingReqDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * RepSlipShippingReqDetailDaoクラス
 * @author kanri-user
 */
public interface RepSlipShippingReqDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.repSlipShippingReqDetail.RepSlipShippingReqDetail.class;

	/** ARGSアノテーション getSlipShippingList */
	String getSlipShippingList_ARGS = "shippingNo";

	/**
	 * Listメソッド
	 * 
	 * @param shippingNo shippingNo
	 * @return List
	 */
	List<RepSlipShippingReqDetail> getSlipShippingList(
			final ArrayList<String> shippingNo);
}
