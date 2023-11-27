/*
 * Created on Wed Feb 04 09:58:03 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.organization;
import java.util.List;

/**
 * OrganizationDaoインターフェース.
 * @author kanri-user
 */
public interface OrganizationDao {

	/** BEANアノテーション. */
	Class BEAN = Organization.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Organization
	 * @return Insert件数
	 */
	int insert(Organization bean);

	/**
	 * Update.
	 * @param bean Organization
	 * @return Update件数
	 */
	int update(Organization bean);

	/**
	 * Delete.
	 * @param bean Organization
	 * @return Delete件数
	 */
	int delete(Organization bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "ORGANIZATION_CD";

	/**
	 * エンティティ取得.
	 * @param organizationCd organizationCd
	 * @return Organization
	 */
	Organization getEntity(String organizationCd);

	/**
	 * リスト取得.
	 * @return Organizationのリスト
	 */
	List<Organization> getList();

	//
	// 追加メソッドはこの下に記述して下さい
	//
}

