/*
 * Created on 2009/03/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.direction;

import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionHeaderList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 製造指図ヘッダ画面ロジックインターフェイス
 * @author tosco
 */
public interface DirectionHeaderLogic {
	/**
	 * ホールドタンクの存在チェック
	 * @param compoundTankNo 調合タンクNO
	 * @param holdTankNo ホールドタンクNO
	 * @return ホールドタンク名称
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	String checkExistsHoldTank(String compoundTankNo, String holdTankNo) throws NoDataException;
	/**
	 * ホールドタンク名称を取得する
	 * @param holdTankNo ホールドタンクNO
	 * @return ホールドタンク名称
	 */
	String getHoldTankName(String holdTankNo);
	/**
	 * 指図ヘッダ削除処理を行う
	 * @param bean 削除対象ビーン
	 * @throws NoDataException データ無し例外
	 */
	void delete(DirectionDirectionHeaderList bean) throws NoDataException;

}
