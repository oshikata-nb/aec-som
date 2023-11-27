/*
 * Created on 2009/05/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemtechlabeldetail;

/**
 * ItemTechLabelDetailDaoクラス
 * @author t0011036
 */
public interface ItemTechLabelDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.itemtechlabeldetail.ItemTechLabelDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "labelCd, commonCd";

	/**
	 * ItemTechLabelDetailメソッド
	 * 
	 * @param labelCd labelCd
	 * @param commonCd commonCd
	 * @return ItemTechLabelDetail
	 */
	ItemTechLabelDetail getEntity(final String labelCd, final String commonCd);
}
