/*
 * Created on 2009/03/10
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.purchasedelivery;

import java.util.List;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.purchasedelivery.PurchaseDeliverySumDetail;
import com.asahikaseieng.dao.nonentity.purchasedelivery.PurchaseDeliverySumDetailList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 納期回答まとめ入力 ロジッククラス interface. 
 * @author tosco
 */
public interface PurchaseDeliverySumDetailLogic {

	/**
	 * 入力された発注書NOに該当するまとめ入力対象データの件数を取得する.
	 * @param orderSheetNo 発注書NO
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForExist(final String orderSheetNo);

	/**
	 * 選択された発注書NOに該当するデータの各件数を取得する.（ヘッダ部）
	 * @param orderSheetNo 発注書NO
	 * @return PurchaseDeliverySumDetail ヘッダデータ
	 * @throws NoDataException 対象データなしエラー
	 */
	PurchaseDeliverySumDetail getHeader(final String orderSheetNo) throws NoDataException;

	/**
	 * 選択された発注書NOに該当するデータの納入先を取得する.（ヘッダ部）
	 * @param orderSheetNo 発注書NO
	 * @return String 納入先
	 * @throws NoDataException 対象データなしエラー
	 */
	String getLocation(final String orderSheetNo) throws NoDataException;

	/**
	 * 選択された発注書NOに該当するデータを取得する.（明細部）
	 * @param orderSheetNo 発注書NO
	 * @param check      数値項目用表示ロジッククラス
	 * @return List<PurchaseDeliverySumDetailList> 明細部データ
	 * @throws NoDataException データが存在しない例外
	 */
	List<PurchaseDeliverySumDetailList> getEntity(final String orderSheetNo, final CheckDigitUtilsLogic check)
									throws NoDataException;

	/**
	 * 購買外注オーダーテーブル更新処理を行う.
	 * @param list 納期回答まとめ入力画面明細リスト
	 * @param tantoCd ログイン者コード
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	void update(final List<PurchaseDeliverySumDetailList> list, final String tantoCd)
			throws NoDataException, Exception;

}
