/*
 * Created on Mon Jan 19 18:36:08 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.component;

/**
 * ComponentDaoインターフェース.
 * @author t0011036
 */
public interface ComponentDao {

	/** BEANアノテーション. */
	Class BEAN = Component.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Component
	 * @return Insert件数
	 */
	int insert(Component bean);

	/**
	 * Update.
	 * @param bean Component
	 * @return Update件数
	 */
	int update(Component bean);

	/**
	 * Delete.
	 * @param bean Component
	 * @return Delete件数
	 */
	int delete(Component bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "COMPONENT_CD";

	/**
	 * エンティティ取得.
	 * @param componentCd componentCd
	 * @return Component
	 */
	Component getEntity(final String componentCd);
}
