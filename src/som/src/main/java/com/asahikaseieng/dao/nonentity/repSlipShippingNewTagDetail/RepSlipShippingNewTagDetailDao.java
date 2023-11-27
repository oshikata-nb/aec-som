/*
 * Created on 2009/07/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repSlipShippingNewTagDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * RepSlipShippingNewTagDetailDaoクラス
 * @author kanri-user
 */
public interface RepSlipShippingNewTagDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.repSlipShippingNewTagDetail.RepSlipShippingNewTagDetail.class;

	/** ARGSアノテーション getSlipShippingList */
	String getSlipShippingList_ARGS = "shippingNo";

	/**
	 * Listメソッド
	 * 
	 * @param shippingNo shippingNo
	 * @return List
	 */
	List<RepSlipShippingNewTagDetail> getSlipShippingList(
			final ArrayList<String> shippingNo);
}
