/*
 * Created on 2009/03/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.direction;

/**
 * 製造指示インターフェース.
 * @author tosco
 */
public interface DirectionSijiSeizoDetailDao {

	/** BEANアノテーション. */
	Class<DirectionSijiSeizoDetail> BEAN = DirectionSijiSeizoDetail.class;

	//メソッド

	/** ARGSアノテーション deleteByDirectionNo(). */
	String deleteByDirectionNo_ARGS = "directionNo";
	/**
	 * 指図番号で削除
	 * @param directionNo 指図番号
	 * @return Delete件数
	 */
	int deleteByDirectionNo(String directionNo);

}
