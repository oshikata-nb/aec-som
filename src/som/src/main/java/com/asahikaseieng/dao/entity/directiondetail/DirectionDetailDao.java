/*
 * Created on Thu Feb 12 18:50:10 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.directiondetail;

/**
 * DirectionDetailDaoインターフェース.
 * @author kanri-user
 */
public interface DirectionDetailDao {

	/** BEANアノテーション. */
	Class BEAN = DirectionDetail.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean DirectionDetail
	 * @return Insert件数
	 */
	int insert(DirectionDetail bean);

	/**
	 * Update.
	 * @param bean DirectionDetail
	 * @return Update件数
	 */
	int update(DirectionDetail bean);

	/**
	 * Delete.
	 * @param bean DirectionDetail
	 * @return Delete件数
	 */
	int delete(DirectionDetail bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "PKG_DIRECTION_NO,ROW_NO";

	/**
	 * エンティティ取得.
	 * @param pkgDirectionNo pkgDirectionNo
	 * @param rowNo rowNo
	 * @return DirectionDetail
	 */
	DirectionDetail getEntity(String pkgDirectionNo, java.math.BigDecimal rowNo);
}
