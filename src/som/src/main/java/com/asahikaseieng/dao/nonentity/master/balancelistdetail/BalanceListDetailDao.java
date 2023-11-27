/*
 * Created on 2009/03/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.balancelistdetail;

import java.math.BigDecimal;

/**
 * BalanceListDetailDaoクラス
 * @author t0011036
 */
public interface BalanceListDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.balancelistdetail.BalanceListDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "balanceCd, balanceType";

	/**
	 * BalanceListDetailメソッド
	 * 
	 * @param balanceCd balanceCd
	 * @param balanceType balanceType
	 * @return BalanceListDetail
	 */
	BalanceListDetail getEntity(final String balanceCd,
			final BigDecimal balanceType);
}
