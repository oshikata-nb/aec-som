/*
 * Created on Mon Mar 09 08:51:33 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.financialclass;

/**
 * FinancialClassDaoインターフェース.
 * @author t0011036
 */
public interface FinancialClassDao {

	/** BEANアノテーション. */
	Class BEAN = FinancialClass.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean FinancialClass
	 * @return Insert件数
	 */
	int insert(FinancialClass bean);

	/**
	 * Update.
	 * @param bean FinancialClass
	 * @return Update件数
	 */
	int update(FinancialClass bean);

	/**
	 * Delete.
	 * @param bean FinancialClass
	 * @return Delete件数
	 */
	int delete(FinancialClass bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "FINANCIAL_CLASS_CD";

	/**
	 * エンティティ取得.
	 * @param financialClassCd financialClassCd
	 * @return FinancialClass
	 */
	FinancialClass getEntity(String financialClassCd);
}
