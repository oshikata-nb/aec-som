/*
 * Created on Thu Jan 22 13:17:39 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.eraserheaderdetail;

/**
 * EraserHeaderDetailDaoインターフェース.
 * @author t0011036
 */
public interface EraserHeaderDetailDao {

	/** BEANアノテーション. */
	Class BEAN = EraserHeaderDetail.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean EraserHeaderDetail
	 * @return Insert件数
	 */
	int insert(EraserHeaderDetail bean);

	/**
	 * Update.
	 * @param bean EraserHeaderDetail
	 * @return Update件数
	 */
	int update(EraserHeaderDetail bean);

	/**
	 * Delete.
	 * @param bean EraserHeaderDetail
	 * @return Delete件数
	 */
	int delete(EraserHeaderDetail bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "CREDIT_NO,ERASER_NO,ROW_NO";

	/**
	 * エンティティ取得.
	 * @param creditNo creditNo
	 * @param eraserNo eraserNo
	 * @param rowNo rowNo
	 * @return EraserHeaderDetail
	 */
	EraserHeaderDetail getEntity(final String creditNo, final String eraserNo, final java.math.BigDecimal rowNo);
}
