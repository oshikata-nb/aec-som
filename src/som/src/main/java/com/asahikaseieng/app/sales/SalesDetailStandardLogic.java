/*
 * Created on 2009/03/02
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.sales;

import java.math.BigDecimal;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.dao.nonentity.sales.SalesDetailStandardEntity;
import com.asahikaseieng.dao.nonentity.salesinout.SalesGetInoutData;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.model.LoginInfo;

/**
 * 売上詳細(標準)画面 ロジッククラス interface.
 * @author tosco
 */
public interface SalesDetailStandardLogic {

	/**
	 * 売上トランザクションデータを取得する
	 * @param salesNo 売上番号
	 * @return SalesDetailStandardEntity
	 * @throws NoDataException データが存在しない例外
	 */
	SalesDetailStandardEntity getEntity(final String salesNo)
			throws NoDataException;

	/**
	 * 品目情報を取得する
	 * @param itemCd 品目コード
	 * @return SalesDetailStandardEntity
	 * @throws NoDataException データが存在しない例外
	 */
	SalesDetailStandardEntity getSalesByItem(final String itemCd)
			throws NoDataException;

	/**
	 * 取引先マスタチェック処理
	 * @param venderCd 取引先コード
	 * @return boolean チェック結果 true:OK false:NG
	 */
	boolean isExistsVender(final String venderCd);

	/**
	 * 数量の検証(autocompleteで取得していない場合の対応)
	 * @param frm 売上詳細(標準)画面FORM
	 * @param errors 検証エラー内容
	 * @return boolean チェック結果 true:OK false:エラー
	 */
	boolean checkDigitForSales(final SalesDetailStandardForm frm,
			final ActionMessages errors);

	/**
	 * 売上トランザクションデータの登録処理を行う.
	 * @param frm 売上詳細(標準)画面FORM
	 * @param loginInfo ログイン情報
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	void insert(final SalesDetailStandardForm frm, final LoginInfo loginInfo)
			throws NoDataException, Exception;

	/**
	 * 売上トランザクションデータの更新処理を行う.
	 * @param frm 売上詳細(標準)画面FORM
	 * @param loginInfo ログイン者コード
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	void update(final SalesDetailStandardForm frm, final LoginInfo loginInfo)
			throws NoDataException, Exception;

	/**
	 * 削除処理を行う.
	 * @param frm 売上詳細(標準)画面FORM
	 * @param loginInfo ログイン者コード
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	void delete(final SalesDetailStandardForm frm, final LoginInfo loginInfo)
			throws NoDataException, Exception;

	/**
	 * 売上取消処理を行う.
	 * @param frm 売上詳細(標準)画面FORM
	 * @param loginInfo ログイン情報
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	void cancel(final SalesDetailStandardForm frm, final LoginInfo loginInfo)
			throws NoDataException, Exception;

	/**
	 * 売上番号から受払データを取得する
	 * @param slipNo 売上番号
	 * @return SalesGetInoutData
	 */
	SalesGetInoutData getInoutData(final String slipNo);

	/**
	 * 受払数量を数値換算して取得
	 * @param inputQty 受払数量
	 * @param unitOfOperationManagement 運用管理単位
	 * @param venderCd 得意先コード
	 * @return String 受払数量
	 */
	String getExchangeQty(final BigDecimal inputQty,
			final String unitOfOperationManagement, final String venderCd);
}
