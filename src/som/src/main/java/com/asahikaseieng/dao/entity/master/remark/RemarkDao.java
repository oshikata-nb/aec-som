/*
 * Created on Wed Feb 06 19:01:45 JST 2008
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.remark;

/**
 * RemarkDaoインターフェース.
 * @author a1020630
 */
public interface RemarkDao {

	/** BEANアノテーション. */
	Class BEAN = Remark.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Remark
	 * @return Insert件数
	 */
	int insert(Remark bean);

	/**
	 * Update.
	 * @param bean Remark
	 * @return Update件数
	 */
	int update(Remark bean);

	/**
	 * Delete.
	 * @param bean Remark
	 * @return Delete件数
	 */
	int delete(Remark bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "REMARK_NO";

	/**
	 * エンティティ取得.
	 * @param remarkNo remarkNo
	 * @return Remark
	 */
	Remark getEntity(final java.math.BigDecimal remarkNo);
}
