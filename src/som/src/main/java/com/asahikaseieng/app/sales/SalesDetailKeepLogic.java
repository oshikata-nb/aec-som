/*
 * Created on 2009/03/09
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.sales;

import java.util.List;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.dao.nonentity.sales.SalesDetailKeepEntity;
import com.asahikaseieng.dao.nonentity.sales.SalesDetailKeepSalesInoutRecordList;
import com.asahikaseieng.dao.nonentity.sales.SalesDetailKeepVenderList;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.model.LoginInfo;

/**
 * 売上詳細(預り品)画面 ロジッククラス interface. 
 * @author tosco
 */
public interface SalesDetailKeepLogic {

	/**
	 * 売上トランザクションデータを取得する
	 * @param salesNo 売上番号
	 * @return SalesDetailKeepEntity
	 * @throws NoDataException データが存在しない例外
	 */
	SalesDetailKeepEntity getEntity(final String salesNo)
		throws NoDataException;

	/**
	 * 品目情報を取得する
	 * @param itemCd 品目コード
	 * @return SalesDetailKeepEntity
	 * @throws NoDataException データが存在しない例外
	 */
	SalesDetailKeepEntity getSalesByItem(final String itemCd)
		throws NoDataException;

	/**
	 * 売上区分名称を取得する
	 * @param categoryDivision 売上区分
	 * @return String
	 * @throws NoDataException データが存在しない例外
	 */
	String getCategoryName(final String categoryDivision) throws NoDataException;

	/**
	 * 納入先コード、品目コードより帳合コードを取得する
	 * @param deliveryCd 納入先コード
	 * @param itemCd 品目コード
	 * @return String 帳合コード
	 * @throws NoDataException データが存在しない例外
	 */
	String getBalanceCdByDeliveryCdItemCd(final String deliveryCd, final String itemCd)
		throws NoDataException;

	/**
	 * 帳合コードより得意先一覧を取得する
	 * @param balanceCd 帳合
	 * @return List<SalesDetailKeepVenderList> 得意先一覧
	 * @throws NoDataException データが存在しない例外
	 */
	List<SalesDetailKeepVenderList> getVenderList(final String balanceCd)
		throws NoDataException;

	/**
	 * 売上受払履歴より出庫ロケーション一覧を取得する
	 * @param frm 売上詳細(預り品)画面FORM
	 * @return List<SalesDetailKeepLocationList> 検索結果一覧
	 * @throws NoDataException データが存在しない例外
	 */
	List<SalesDetailKeepSalesInoutRecordList> getLocationList(final SalesDetailKeepForm frm)
		throws NoDataException;

	/**
	 * 売上トランザクションデータの登録処理を行う.
	 * @param frm 売上詳細(預り品)画面FORM
	 * @param loginInfo ログイン情報
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	void insert(final SalesDetailKeepForm frm, LoginInfo loginInfo)
		throws NoDataException, Exception;

	/**
	 * 売上トランザクションデータの更新処理を行う.
	 * @param frm 売上詳細(預り品)画面FORM
	 * @param loginInfo ログイン情報
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	void update(final SalesDetailKeepForm frm, final LoginInfo loginInfo)
		throws NoDataException, Exception;

	/**
	 * 売上トランザクションデータの削除処理を行う.
	 * @param frm 売上詳細(預り品)画面FORM
	 * @param loginInfo ログイン情報
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	void delete(final SalesDetailKeepForm frm, final LoginInfo loginInfo) throws
		NoDataException, Exception;

	/**
	 * 売上の仮単価フラグの更新処理を行う
	 * @param frm 売上詳細(預り品)画面FORM
	 * @param loginInfo ログイン情報
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	void cancel(final SalesDetailKeepForm frm, final LoginInfo loginInfo)
		throws NoDataException, Exception;
	
	/**
	 * 売上取消処理を行う.
	 * @param frm 売上詳細(預り品)画面FORM
	 * @param loginInfo ログイン情報
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	public void tmpUnitflagUpdate(final SalesDetailKeepForm frm, final LoginInfo loginInfo)
			throws NoDataException, Exception;
	
	/**
	 * 入庫ロケーションの存在をチェックする
	 * @param frm 売上詳細(預り品)画面FORM
	 * @param errors ActionMessage
	 */
	void checkInLocation(final SalesDetailKeepForm frm, final ActionMessages errors);
}
