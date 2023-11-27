/*
 * Created on Wed Feb 04 09:41:39 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.belong;

import java.math.BigDecimal;

/**
 * BelongDaoインターフェース.
 * @author kanri-user
 */
public interface BelongDao {

	/** BEANアノテーション. */
	Class BEAN = Belong.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Belong
	 * @return Insert件数
	 */
	int insert(Belong bean);

	/**
	 * Update.
	 * @param bean Belong
	 * @return Update件数
	 */
	int update(Belong bean);

	/**
	 * Delete.
	 * @param bean Belong
	 * @return Delete件数
	 */
	int delete(Belong bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "ORGANIZATION_CD,TANTO_CD,POST_ID";

	/**
	 * エンティティ取得.
	 * @param organizationCd organizationCd
	 * @param tantoCd tantoCd
	 * @param postId postId
	 * @return Belong
	 */
	Belong getEntity(final String organizationCd, final String tantoCd,
			final BigDecimal postId);
}
