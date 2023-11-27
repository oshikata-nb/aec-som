/*
 * Created on 2008/10/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.payment.payment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.dao.nonentity.comboboxes.master.classification.ClassificationListForComboboxes;
import com.asahikaseieng.dao.nonentity.payment.payment.AltPayment;
import com.asahikaseieng.dao.nonentity.payment.payment.AltPaymentPagerCondition;
import com.asahikaseieng.dao.nonentity.payment.paymentlistforreport.PaymentListConditionForReport;
import com.asahikaseieng.dao.nonentity.payment.paymentlistforreport.PaymentListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 
 * PaymentCsmListLogicクラス．支払入力（リスト)(カスタマイズ)
 * @author tosco
 */
public interface PaymentCsmListLogic {
	/**
	 * 検索処理を行う.支払入力検索処理
	 * @param condition condition
	 * @return List 検索結果
	 * @throws NoDataException 検索結果が存在しない場合発生
	 */
	List<AltPayment> getSearchList(final AltPaymentPagerCondition condition)
			throws NoDataException;

	/**
	 * ステータス更新
	 * @param frm 更新対象データ
	 * @param status ステータス
	 * @param statusMode ステータスモード
	 * @param tantoCd 担当者コード
	 * @throws NoDataException NoDataException
	 */
	void statusUpdate(final PaymentCsmListForm frm, final BigDecimal status,
			final BigDecimal statusMode, final String tantoCd)
			throws NoDataException;

	/**
	 * 帳票用検索処理を行う.
	 * @param condition 検索条件
	 * @return List<PaymentCsmListForReport>
	 */
	List<PaymentListForReport> getListForReport(
			final PaymentListConditionForReport condition);

	/**
	 * 分類取得
	 * @param dataType サイトデータ種別
	 * @return List<ClassificationListForComboboxes>
	 */
	List<ClassificationListForComboboxes> getClassificationList(
			final BigDecimal dataType);

	/**
	 * 伝票発行フラグ更新
	 * @param slipNoList 更新対象伝票番号一覧
	 * @param tantoCd 担当者コード
	 */
	void updateIssuedDivision(final ArrayList<String> slipNoList,
			final String tantoCd);
}
