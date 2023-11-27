/*
 * Created on 2009/01/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.accountsdetail;

/**
 * AccountsDetailDaoクラス
 * @author t0011036
 */
public interface AccountsDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.accountsdetail.AccountsDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "accountsCd";

	/**
	 * AccountsDetailメソッド
	 * 
	 * @param accountsCd accountsCd
	 * @return AccountsDetail
	 */
	AccountsDetail getEntity(final String accountsCd);
}
