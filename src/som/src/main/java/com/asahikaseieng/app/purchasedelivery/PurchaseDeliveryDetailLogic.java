/*
 * Created on 2009/03/12
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.purchasedelivery;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseSubcontract;
import com.asahikaseieng.dao.nonentity.purchasedelivery.PurchaseDeliveryDetail;
import com.asahikaseieng.exception.NoDataException;

/**
 * 納期回答入力詳細 ロジッククラス interface. 
 * @author tosco
 */
public interface PurchaseDeliveryDetailLogic {

	/**
	 * 購買オーダーテーブル検索処理を行う.
	 * @param purchaseNo 購買NO
	 * @param check      数値項目用表示ロジッククラス
	 * @return List<PurchaseDeliveryDetail> 納期回答入力データ
	 * @throws NoDataException データが存在しない例外
	 */
	PurchaseDeliveryDetail getEntity(final String purchaseNo
									, final CheckDigitUtilsLogic check)
									throws NoDataException;

	/**
	 * 購買外注データを購買NO(KEY)で全項目取得する.
	 * @param purchaseNo 購買NO
	 * @return PurchaseSubcontract 購買外注データBean
	 * @throws NoDataException 対象データ無しエラー
	 */
	PurchaseSubcontract getEntity(final String purchaseNo) throws NoDataException;

	/**
	 * 購買オーダーテーブル更新処理を行う.
	 * @param frm     納期回答入力画面FORM
	 * @param tantoCd ログイン者コード
	 * @param bean    更新前検索データBean
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	void update(final PurchaseDeliveryDetailForm frm, final String tantoCd, final PurchaseSubcontract bean)
			throws NoDataException, Exception;

}
