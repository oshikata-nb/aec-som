/*
 * Created on 2009/07/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repSlipShippingNewTagHeader;

import java.util.ArrayList;
import java.util.List;

/**
 * RepSlipShippingNewTagHeaderDaoクラス
 * @author kanri-user
 */
public interface RepSlipShippingNewTagHeaderDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.repSlipShippingNewTagHeader.RepSlipShippingNewTagHeader.class;

	/** ARGSアノテーション getSlipShippingList */
	String getSlipShippingList_ARGS = "shippingNo";

	/**
	 * Listメソッド
	 * 
	 * @param shippingNo shippingNo
	 * @return List
	 */
	List<RepSlipShippingNewTagHeader> getSlipShippingList(
			final ArrayList<String> shippingNo);
}
