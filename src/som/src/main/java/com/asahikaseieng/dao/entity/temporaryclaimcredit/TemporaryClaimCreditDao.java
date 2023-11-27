/*
 * Created on Tue Feb 17 14:03:22 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.temporaryclaimcredit;

/**
 * TemporaryClaimCreditDaoインターフェース.
 * @author kanri-user
 */
public interface TemporaryClaimCreditDao {

	/** BEANアノテーション. */
	Class BEAN = TemporaryClaimCredit.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean TemporaryClaimCredit
	 * @return Insert件数
	 */
	int insert(TemporaryClaimCredit bean);

	/**
	 * Update.
	 * @param bean TemporaryClaimCredit
	 * @return Update件数
	 */
	int update(TemporaryClaimCredit bean);

	/**
	 * Delete.
	 * @param bean TemporaryClaimCredit
	 * @return Delete件数
	 */
	int delete(TemporaryClaimCredit bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "CREDIT_NO,ROW_NO";

	/**
	 * エンティティ取得.
	 * @param creditNo creditNo
	 * @param rowNo rowNo
	 * @return TemporaryClaimCredit
	 */
	TemporaryClaimCredit getEntity(String creditNo, java.math.BigDecimal rowNo);
}
