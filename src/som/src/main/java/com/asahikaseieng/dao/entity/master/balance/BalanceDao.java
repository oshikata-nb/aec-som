/*
 * Created on Fri Feb 27 15:56:27 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.balance;

import java.util.List;

/**
 * BalanceDaoインターフェース.
 * @author t0011036
 */
public interface BalanceDao {

	/** BEANアノテーション. */
	Class BEAN = Balance.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Balance
	 * @return Insert件数
	 */
	int insert(Balance bean);

	/**
	 * Update.
	 * @param bean Balance
	 * @return Update件数
	 */
	int update(Balance bean);

	/**
	 * Delete.
	 * @param bean Balance
	 * @return Delete件数
	 */
	int delete(Balance bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "BALANCE_CD";

	/**
	 * エンティティ取得.
	 * @param balanceCd balanceCd
	 * @return Balance
	 */
	Balance getEntity(final String balanceCd);
	
	/** ARGSアノテーション getList(). */
	String getList_ARGS = "VENDER_CD";

	/**
	 * エンティティ取得.
	 * @param balanceCd balanceCd
	 * @return Balance
	 */
	List<Balance> getList(final String venderCd);
}
