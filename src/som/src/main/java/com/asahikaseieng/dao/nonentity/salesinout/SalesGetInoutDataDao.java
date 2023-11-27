/*
 * Created on 2010/09/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.salesinout;

/**
 * SalesGetInoutDataDaoクラス
 * @author t1344224
 */
public interface SalesGetInoutDataDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.salesinout.SalesGetInoutData.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "slipNo";

	/**
	 * SalesGetInoutDataメソッド
	 * 
	 * @param slipNo slipNo
	 * @return SalesGetInoutData
	 */
	SalesGetInoutData getEntity(final String slipNo);
}
