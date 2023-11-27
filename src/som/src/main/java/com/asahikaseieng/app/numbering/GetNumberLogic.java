/*
 * Created on 2008/01/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.numbering;

import java.sql.Timestamp;

import com.asahikaseieng.exception.NoDataException;

/**
 * 発番処理 ロジッククラス interface.
 * @author tosco
 */
public interface GetNumberLogic {

	/**
	 * 発注書NOを取得
	 * 
	 * @return String 発注書NO
	 * @throws NoDataException データが存在しない例外
	 */
	String getOrderSheetNo() throws NoDataException;

	/**
	 * 出荷伝票番号を取得
	 * 
	 * @return String 出荷伝票番号
	 * @throws NoDataException データが存在しない例外
	 */
	String getSlipShippingNo() throws NoDataException;

	/**
	 * 売上伝票番号を取得
	 * 
	 * @return String 売上伝票番号
	 * @throws NoDataException データが存在しない例外
	 */
	String getSlipSalesNo() throws NoDataException;

	/**
	 * 見積番号を取得
	 * 
	 * @return String 見積番号
	 * @throws NoDataException データが存在しない例外
	 */
	String getEstimateNo() throws NoDataException;

	/**
	 * 発注番号を取得
	 * 
	 * @return String 発注番号
	 * @throws NoDataException データが存在しない例外
	 */
	String getBuySubcontractOrderNo() throws NoDataException;

	/**
	 * 仕入番号を取得
	 * 
	 * @return String 仕入番号
	 * @throws NoDataException データが存在しない例外
	 */
	String getSiSlipNo() throws NoDataException;

	/**
	 * 受注番号を取得
	 * 
	 * @return String 受注番号
	 * @throws NoDataException データが存在しない例外
	 */
	String getSupplierOrderNo() throws NoDataException;

	/**
	 * 出荷番号を取得
	 * 
	 * @return String 出荷番号
	 * @throws NoDataException データが存在しない例外
	 */
	String getShipping() throws NoDataException;

	/**
	 * 売上番号を取得
	 * 
	 * @return String 売上番号
	 * @throws NoDataException データが存在しない例外
	 */
	String getUrSlipNo() throws NoDataException;
	
	/**
	 * 販売条件・見積単価　コピー・削除テーブル用のpkNoを取得
	 * 
	 * @return String pkNo
	 * @throws NoDataException データが存在しない例外
	 */	
	String getSalestermsAndEstimatePkNo() throws NoDataException;

	/**
	 * 原材料用入荷ロット番号を発番
	 * @param timestamp 発番日
	 * @return String 原材料用入荷ロット番号<br>
	 *         発番失敗時はNULLを返却
	 */
	String getNLotNo(final Timestamp timestamp);

	/**
	 * 外注製品用入荷ロット番号を発番
	 * @param timestamp 発番日
	 * @return String 外注製品用入荷ロット番号<br>
	 *         発番失敗時はNULLを返却
	 */
	String getPLotNo(final Timestamp timestamp);

	/**
	 * 製造指図番号を発番
	 * @param timestamp 発番日
	 * @return String 製造指図番号<br>
	 *         発番失敗時はNULLを返却
	 */
	String getDirectionNo(final Timestamp timestamp);

	/**
	 * 包装指図番号を発番する
	 * @param timestamp 発番日（発番の編集される日付）
	 * @return String 包装指図番号<br>
	 *         発番失敗時はNULLを返却
	 */
	String getPkgDirectionNo(final Timestamp timestamp);

	/**
	 * 予備溶解バーコードを発番
	 * @param timestamp 発番日
	 * @return String 予備溶解バーコード<br>
	 *         発番失敗時はNULLを返却
	 */
	String getYobiYokaiBcr(final Timestamp timestamp);
	
	/**
	 * 取込番号を取得
	 * 
	 * @return String 取込番号
	 * @throws NoDataException データが存在しない例外
	 */
	String getFrstOrderNo() throws NoDataException;
	
	/**
	 * 一時取込番号を取得
	 * 
	 * @return String 取込番号
	 * @throws NoDataException データが存在しない例外
	 */
	String getTempNo() throws NoDataException;
}
