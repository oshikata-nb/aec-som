/*
 * Created on Mon Jan 19 17:47:16 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.companysetting;


/**
 * CompanyDaoインターフェース.
 * @author t0011036
 */
public interface CompanySettingDao {

	/** BEANアノテーション. */
	Class<CompanySetting> BEAN = CompanySetting.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean CompanySetting
	 * @return Insert件数
	 */
	int insert(CompanySetting bean);

	/**
	 * Update.
	 * @param bean CompanySetting
	 * @return Update件数
	 */
	int update(CompanySetting bean);

	/**
	 * Delete.
	 * @param bean CompanySetting
	 * @return Delete件数
	 */
	int delete(CompanySetting bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "COMPANY_CD";

	/**
	 * エンティティ取得.
	 * @param companyCd companyCd
	 * @return CompanySetting
	 */
	CompanySetting getEntity(final String companyCd);
}
