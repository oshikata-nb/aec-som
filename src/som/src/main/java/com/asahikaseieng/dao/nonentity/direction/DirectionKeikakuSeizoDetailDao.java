/*
 * Created on Wed Feb 26 16:10:11 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.direction;


/**
 * 製造計画インターフェース.
 * @author tosco
 */
public interface DirectionKeikakuSeizoDetailDao {

	/** BEANアノテーション. */
	Class<DirectionKeikakuSeizoDetail> BEAN = DirectionKeikakuSeizoDetail.class;

	//
	// インスタンスメソッド
	//
	/** ARGSアノテーション getMaxJunban(). */
	String getMaxJunban_ARGS = "seizobi,chogoTankNo";

	/**
	 * 指定日の調合タンクの順番の最大値を求める。
	 * @param seizobi 指定日
	 * @param chogoTankNo 調合タンク
	 * @return DirectionKeikakuSeizoDetail
	 */
	DirectionKeikakuSeizoDetail getMaxJunban(String seizobi, String chogoTankNo);

	/** ARGSアノテーション deleteByDirectionNo(). */
	String deleteByDirectionNo_ARGS = "directionNo";
	/**
	 * 指図区分、指図番号で削除
	 * @param directionNo 指図番号
	 * @return Delete件数
	 */
	int deleteByDirectionNo(String directionNo);
}
