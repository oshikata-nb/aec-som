/*
 * Created on 2009/01/13
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.arrival;

import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.arrival.ArrivalDetailList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 入荷入力詳細 ロジッククラス interface. 
 * @author tosco
 */
public interface ArrivalDetailLogic {

	/**
	 * 購買オーダーテーブル購買データ検索処理を行う.
	 * @param purchaseNo 購買NO
	 * @param slipNo     仕入番号
	 * @param check      数値項目用表示ロジッククラス
	 * @return List<ArrivalDetailList> 詳細データ
	 * @throws NoDataException データが存在しない例外
	 */
	List<ArrivalDetailList> getEntity(final String purchaseNo
									, final String slipNo
									, final CheckDigitUtilsLogic check)
									throws NoDataException;

	/**
	 * 発注番号にひもづくデータの予定入荷数量の合計値を取得する.
	 * @param buySubcontractOrderNo 発注番号
	 * @param slipNo     仕入番号
	 * @return BigDecimal 予定入荷数量の合計値
	 */
	BigDecimal getSumArrivalQty(final String buySubcontractOrderNo, final String slipNo);

	/**
	 * 購買オーダーテーブル更新処理を行う.
	 * @param frm 入荷入力画面FORM
	 * @param tantoCd ログイン者コード
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	void update(final ArrivalDetailForm frm, final String tantoCd)
			throws NoDataException, Exception;

	/**
	 * 購買オーダーテーブルのラベル発行処理を行う.
	 * @param detailList ロット分割データ
	 * @param tantoCd    更新者
	 * @param check      数値項目用表示ロジッククラス
	 * @throws NoDataException 更新対象無しエラー
	 * @throws Exception 例外
	 */
	void issue(final List<ArrivalDetailList> detailList
				, final String tantoCd
				, final CheckDigitUtilsLogic check)
			throws NoDataException, Exception;

	/**
	 * 計装I/Fテーブルの登録処理を行う.
	 * 　・原材料の場合　　　　　　　: 入荷ロット原材料コード対応,原材料ラベル発行実績テーブル
	 * 　・外注製品,仕入在庫品の場合 : 製品入荷データテーブル
	 * @param detailList ロット分割データ
	 * @param check      数値項目用表示ロジッククラス
	 * @throws Exception 例外
	 */
	void insertIfTable(final List<ArrivalDetailList> detailList
							, final CheckDigitUtilsLogic check) throws Exception;

}
