/*
 * Created on 2009/02/25
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.shipping;

import java.util.List;

import com.asahikaseieng.dao.nonentity.shipping.ShippingAutoMake;
import com.asahikaseieng.exception.NoDataException;

/**
 * 出荷指図指図自動作成画面 ロジッククラス interface.
 * @author tosco
 */
public interface ShippingAutoMakeLogic {

	/**
	 * 出荷指図自動作成対象となるデータを返す
	 * @param frm 出荷指図自動作成画面
	 * @return List<ShippingAutoMake> 処理対象
	 * @throws NoDataException 例外
	 */
	List<ShippingAutoMake> getShippingAutoMakeList(
			final ShippingAutoMakeForm frm) throws NoDataException;

	/**
	 * 出荷指図データの登録処理を行う.
	 * @param auto 受注
	 * @param tantoCd ログイン者コード
	 * @param scheduledShippingDateFrom 開始日
	 * @param scheduledShippingDateTo 終了日
	 * @return boolean boolean
	 * @throws ShippingLogicException 例外
	 */
	boolean shippingAutoMake(final ShippingAutoMake auto,
			final String scheduledShippingDateFrom,
			final String scheduledShippingDateTo, final String tantoCd)
			throws ShippingLogicException;

	/**
	 * エラーログ出力処理
	 * @param strErrorCd エラーコード
	 * @param strErrorMsg エラーメッセージ
	 * @param tantoCd 担当者コード
	 * @throws Exception 例外
	 */
	void outPutErrorLog(final String strErrorCd, final String strErrorMsg,
			final String tantoCd) throws Exception;
}
