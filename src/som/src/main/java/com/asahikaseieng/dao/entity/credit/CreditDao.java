/*
 * Created on Fri Jun 19 11:01:35 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.credit;

/**
 * CreditDaoインターフェース.
 * @author t0011036
 */
public interface CreditDao {

	/** BEANアノテーション. */
	Class BEAN = Credit.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Credit
	 * @return Insert件数
	 */
	int insert(Credit bean);

	/**
	 * Update.
	 * @param bean Credit
	 * @return Update件数
	 */
	int update(Credit bean);

	/**
	 * Delete.
	 * @param bean Credit
	 * @return Delete件数
	 */
	int delete(Credit bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "CREDIT_NO,ROW_NO";

	/**
	 * エンティティ取得.
	 * @param creditNo creditNo
	 * @param rowNo rowNo
	 * @return Credit
	 */
	Credit getEntity(String creditNo, java.math.BigDecimal rowNo);
}
