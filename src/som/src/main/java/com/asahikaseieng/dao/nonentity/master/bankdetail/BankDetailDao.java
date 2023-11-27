/*
 * Created on 2009/01/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.bankdetail;

/**
 * BankDetailDaoクラス
 * @author t0011036
 */
public interface BankDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.bankdetail.BankDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "bankMasterCd";

	/**
	 * BankDetailメソッド
	 * 
	 * @param bankMasterCd bankMasterCd
	 * @return BankDetail
	 */
	BankDetail getEntity(final String bankMasterCd);
}
