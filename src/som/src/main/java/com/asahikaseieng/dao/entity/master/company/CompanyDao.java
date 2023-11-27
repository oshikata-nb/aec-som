/*
 * Created on Mon Jan 19 17:47:16 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.company;

/**
 * CompanyDaoインターフェース.
 * @author t0011036
 */
public interface CompanyDao {

	/** BEANアノテーション. */
	Class BEAN = Company.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Company
	 * @return Insert件数
	 */
	int insert(Company bean);

	/**
	 * Update.
	 * @param bean Company
	 * @return Update件数
	 */
	int update(Company bean);

	/**
	 * Delete.
	 * @param bean Company
	 * @return Delete件数
	 */
	int delete(Company bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "COMPANY_CD";

	/**
	 * エンティティ取得.
	 * @param companyCd companyCd
	 * @return Company
	 */
	Company getEntity(final String companyCd);
}
