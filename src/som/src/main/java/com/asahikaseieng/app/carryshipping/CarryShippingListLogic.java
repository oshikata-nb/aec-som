/*
 * Created on 2009/02/17
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.carryshipping;

import java.util.List;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.dao.nonentity.carryshipping.CarryShippingList;
import com.asahikaseieng.dao.nonentity.carryshipping.CarryShippingPagerCondition;
import com.asahikaseieng.dao.nonentity.carryshippingforreport.CarryShippingListConditionForReport;
import com.asahikaseieng.dao.nonentity.carryshippingforreport.CarryShippingListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 運送店別出荷指図画面 ロジッククラス interface.
 * @author tosco
 */
public interface CarryShippingListLogic {

	/**
	 * 運送店別出荷指図画面 検索処理を行う
	 * @param condition 検索条件
	 * @return List<CarryShippingList> データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	List<CarryShippingList> getSearchList(
			final CarryShippingPagerCondition condition) throws NoDataException;

	/**
	 * 運送店別出荷指図の登録処理を行う
	 * @param frm 運送店別出荷指図画面 Formクラス
	 * @param loginUserId ログインユーザ
	 * @param errors errors
	 * @throws Exception 例外
	 */
	void update(final CarryShippingListForm frm, final String loginUserId,
			final ActionMessages errors) throws Exception;

	/**
	 * 運送店別出荷指図指図作成を行う.
	 * @param frm 運送店別出荷指図画面 Formクラス
	 * @param tantoCd ログイン者コード
	 */
	void shippingOrderMake(final CarryShippingListForm frm, final String tantoCd);

	// /**
	// * 運送店別出荷指図指図送信を行う.
	// * @param frm 運送店別出荷指図画面 Formクラス
	// * @param tantoCd ログイン者コード
	// */
	// void shippingOrderSend(final CarryShippingListForm frm, final String
	// tantoCd);
	//
	/**
	 * 帳票Excel 一覧検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<CarryShippingListForReport> 検索結果リスト
	 */
	List<CarryShippingListForReport> getReportList(
			final CarryShippingListConditionForReport condition);

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
