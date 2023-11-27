/*
 * Created on 2009/03/19
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.shippingresult;

import java.util.List;

import com.asahikaseieng.dao.nonentity.shippingresult.ShippingResultDetailEntity;
import com.asahikaseieng.dao.nonentity.shippingresult.ShippingResultDetailList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 出荷実績詳細（花王・その他）画面 ロジッククラス interface.
 * @author tosco
 */
public interface ShippingResultDetailLogic {

	/**
	 * 出荷実績ヘッダデータを取得する
	 * @param shippingNo 出荷番号
	 * @return ShippingDetail
	 * @throws NoDataException データが存在しない例外
	 */
	ShippingResultDetailEntity getEntity(final String shippingNo)
			throws NoDataException;

	/**
	 * 出荷実績詳細データを取得する
	 * @param shippingNo 出荷番号
	 * @param unitDivision 単位区分
	 * @param venderDivision 取引先区分
	 * @param venderCd 取引先コード
	 * @return List<ShippingResultDetailList>
	 * @throws NoDataException データが存在しない例外
	 */
	List<ShippingResultDetailList> getDetailList(final String shippingNo,
			final String unitDivision, final String venderDivision,
			final String venderCd) throws NoDataException;

	/**
	 * 出荷実績データの更新処理を行う.
	 * @param frm 出荷実績詳細画面FORM
	 * @param tantoCd ログイン者コード
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	void update(final ShippingResultDetailForm frm, final String tantoCd)
			throws NoDataException, Exception;

	/**
	 * エラーログ出力処理
	 * @param strErrorCd エラーコード
	 * @param strErrorMsg エラーメッセージ
	 * @param tantoCd 担当者コード
	 * @throws Exception 例外
	 */
	void outPutErrorLog(final String strErrorCd, final String strErrorMsg,
			final String tantoCd) throws Exception;

	/**
	 * エラーログ出力処理
	 * @param strModuleCd モジュールコード
	 * @param strErrorCd エラーコード
	 * @param strErrorMsg エラーメッセージ
	 * @param tantoCd 担当者コード
	 * @throws Exception 例外
	 */
	void outPutErrorLog(final String strModuleCd, final String strErrorCd,
			final String strErrorMsg, final String tantoCd) throws Exception;

}
