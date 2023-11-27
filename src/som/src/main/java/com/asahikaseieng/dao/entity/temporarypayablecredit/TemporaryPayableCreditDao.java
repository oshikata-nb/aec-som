/*
 * Created on Fri Jan 08 08:29:04 JST 2010
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.temporarypayablecredit;

/**
 * TemporaryPayableCreditDaoインターフェース.
 * @author t0011036
 */
public interface TemporaryPayableCreditDao {

	/** BEANアノテーション. */
	Class BEAN = TemporaryPayableCredit.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean TemporaryPayableCredit
	 * @return Insert件数
	 */
	int insert(TemporaryPayableCredit bean);

	/**
	 * Update.
	 * @param bean TemporaryPayableCredit
	 * @return Update件数
	 */
	int update(TemporaryPayableCredit bean);

	/**
	 * Delete.
	 * @param bean TemporaryPayableCredit
	 * @return Delete件数
	 */
	int delete(TemporaryPayableCredit bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "CREDIT_NO,ROW_NO";

	/**
	 * エンティティ取得.
	 * @param creditNo creditNo
	 * @param rowNo rowNo
	 * @return TemporaryPayableCredit
	 */
	TemporaryPayableCredit getEntity(String creditNo, java.math.BigDecimal rowNo);
}
