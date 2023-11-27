/*
 * Created on 2009/07/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repSlipShippingReqHeader;

import java.util.ArrayList;
import java.util.List;

/**
 * RepSlipShippingReqHeaderDaoクラス
 * @author kanri-user
 */
public interface RepSlipShippingReqHeaderDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.repSlipShippingReqHeader.RepSlipShippingReqHeader.class;

	/** ARGSアノテーション getSlipShippingList */
	String getSlipShippingList_ARGS = "shippingNo";

	/**
	 * Listメソッド
	 * 
	 * @param shippingNo shippingNo
	 * @return List
	 */
	List<RepSlipShippingReqHeader> getSlipShippingList(
			final ArrayList<String> shippingNo);
}
