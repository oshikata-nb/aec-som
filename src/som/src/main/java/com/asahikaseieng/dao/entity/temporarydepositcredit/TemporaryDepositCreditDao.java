/*
 * Created on Tue Feb 17 11:46:26 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.temporarydepositcredit;

/**
 * TemporaryDepositCreditDaoインターフェース.
 * @author kanri-user
 */
public interface TemporaryDepositCreditDao {

	/** BEANアノテーション. */
	Class BEAN = TemporaryDepositCredit.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean TemporaryDepositCredit
	 * @return Insert件数
	 */
	int insert(TemporaryDepositCredit bean);

	/**
	 * Update.
	 * @param bean TemporaryDepositCredit
	 * @return Update件数
	 */
	int update(TemporaryDepositCredit bean);

	/**
	 * Delete.
	 * @param bean TemporaryDepositCredit
	 * @return Delete件数
	 */
	int delete(TemporaryDepositCredit bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "CREDIT_NO,ROW_NO";

	/**
	 * エンティティ取得.
	 * @param creditNo creditNo
	 * @param rowNo rowNo
	 * @return TemporaryDepositCredit
	 */
	TemporaryDepositCredit getEntity(String creditNo, java.math.BigDecimal rowNo);
}
