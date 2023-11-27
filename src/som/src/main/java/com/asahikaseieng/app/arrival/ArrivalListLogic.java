/*
 * Created on 2009/01/13
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.arrival;

import java.util.List;

import com.asahikaseieng.dao.nonentity.arrival.ArrivalList;
import com.asahikaseieng.dao.nonentity.arrival.ArrivalListPagerCondition;
import com.asahikaseieng.dao.nonentity.arrivalforreport.ArrivalListConditionForReport;
import com.asahikaseieng.dao.nonentity.arrivalforreport.ArrivalListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 入荷一覧 ロジッククラス interface.
 * @author tosco
 */
public interface ArrivalListLogic {

	/**
	 * 購買オーダーテーブルの検索処理を行う.全検索
	 * @param condition 検索条件
	 * @return List<ArrivalList> データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	List<ArrivalList> getSearchList(final ArrivalListPagerCondition condition)
			throws NoDataException;

	/**
	 * 入荷帳票Excel検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<ArrivalListForReport> 検索結果リスト
	 */
	List<ArrivalListForReport> getReportList(
			final ArrivalListConditionForReport condition);

	/**
	 * 購買オーダーテーブルの更新処理を行う.
	 * @param searchList 一覧データ
	 * @param tantoCd 更新者
	 * @throws NoDataException 更新対象無しエラー
	 * @throws Exception 例外
	 */
	void update(final List<ArrivalList> searchList, final String tantoCd)
			throws NoDataException, Exception;

	/**
	 * ラベル発行処理（購買オーダーテーブル更新）を行う
	 * @param bean 入荷一覧データ
	 * @param tantoCd 更新者
	 * @throws NoDataException 更新対象無しエラー
	 * @throws Exception 例外
	 */
	void issue(final ArrivalList bean, final String tantoCd)
			throws NoDataException, Exception;

	/**
	 * 計装I/Fテーブルの登録処理を行う. ・原材料の場合 : 入荷ロット原材料コード対応,原材料ラベル発行実績テーブル ・外注製品,仕入在庫品の場合 :
	 * 製品入荷データテーブル
	 * @param bean 入荷一覧データ
	 * @throws Exception 例外
	 */
	void insertIfTable(final ArrivalList bean) throws Exception;
}
