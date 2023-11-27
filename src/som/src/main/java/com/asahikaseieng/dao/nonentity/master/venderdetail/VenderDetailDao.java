/*
 * Created on 2009/01/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.venderdetail;

/**
 * VenderDetailDaoクラス
 * @author kanri-user
 */
public interface VenderDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.venderdetail.VenderDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "venderDivison, venderCd";

	/**
	 * VenderDetailメソッド
	 * 
	 * @param venderDivison venderDivison
	 * @param venderCd venderCd
	 * @return VenderDetail
	 */
	VenderDetail getEntity(final String venderDivison, final String venderCd);
}
