/*
 * Created on 2010/06/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.buyinginout;

/**
 * BuyingGetInoutDataDaoクラス
 * @author t1344224
 */
public interface BuyingGetInoutDataDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.buyinginout.BuyingGetInoutData.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "slipNo";

	/**
	 * BuyingGetInoutDataメソッド
	 * 
	 * @param slipNo slipNo
	 * @return BuyingGetInoutData
	 */
	BuyingGetInoutData getEntity(final String slipNo);
}
