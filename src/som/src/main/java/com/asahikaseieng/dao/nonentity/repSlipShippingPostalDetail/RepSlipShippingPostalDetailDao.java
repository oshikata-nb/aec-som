/*
 * Created on 2009/07/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repSlipShippingPostalDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * RepSlipShippingPostalDetailDaoクラス
 * @author kanri-user
 */
public interface RepSlipShippingPostalDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.repSlipShippingPostalDetail.RepSlipShippingPostalDetail.class;

	/** ARGSアノテーション getSlipShippingList */
	String getSlipShippingList_ARGS = "shippingNo,postalClientCd";

	/**
	 * Listメソッド
	 * 
	 * @param shippingNo shippingNo
	 * @param postalClientCd 郵政依頼主
	 * @return List
	 */
	List<RepSlipShippingPostalDetail> getSlipShippingList(
			final ArrayList<String> shippingNo, final String postalClientCd);
}
