/*
 * Created on 2009/01/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.carrydetail;

/**
 * CarryDetailDaoクラス
 * @author t0011036
 */
public interface CarryDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.carrydetail.CarryDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "carryCd";

	/**
	 * CarryDetailメソッド
	 * 
	 * @param carryCd carryCd
	 * @return CarryDetail
	 */
	CarryDetail getEntity(final String carryCd);
}
