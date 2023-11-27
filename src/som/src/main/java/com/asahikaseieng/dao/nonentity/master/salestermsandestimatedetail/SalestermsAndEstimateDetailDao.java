
/*
 * Created on 2009/03/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.salestermsandestimatedetail;

import java.math.BigDecimal;

/**
 * SalestermsAndEstimateDetailDaoクラス
 * @author t0011036
 */
public interface SalestermsAndEstimateDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.salestermsandestimatedetail.SalestermsAndEstimateDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "pkNo";

	/**
	 * SalestermsAndEstimateDetailメソッド
	 * 
	 * @param pkNo pkNo
	 * @return SalestermsAndEstimateDetail
	 */
	SalestermsAndEstimateDetail getEntity(final String pkNo);
}
