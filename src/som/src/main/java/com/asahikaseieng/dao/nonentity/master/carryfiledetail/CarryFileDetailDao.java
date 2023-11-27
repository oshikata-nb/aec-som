/*
 * Created on 2009/01/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.carryfiledetail;

import java.util.List;

/**
 * CarryDetailDaoクラス
 * @author t0011036
 */
public interface CarryFileDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.carryfiledetail.CarryFileDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "carryCd";

	/**
	 * CarryDetailメソッド
	 * 
	 * @param carryCd carryCd
	 * @return CarryDetail
	 */
	List<CarryFileDetail> getEntity(final String carryCd);


}
