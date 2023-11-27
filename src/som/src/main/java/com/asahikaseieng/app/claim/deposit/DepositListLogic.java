/*
 * Created on 2008/08/07
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.claim.deposit;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.dao.nonentity.claim.deposit.DepositCredit;
import com.asahikaseieng.dao.nonentity.claim.deposit.DepositCreditListPagerCondition;
import com.asahikaseieng.dao.nonentity.claim.depositlistforreport.DepositCreditListConditionForReport;
import com.asahikaseieng.dao.nonentity.claim.depositlistforreport.DepositCreditListForReport;
import com.asahikaseieng.dao.nonentity.comboboxes.master.classification.ClassificationListForComboboxes;
import com.asahikaseieng.exception.NoDataException;

/**
 * 
 * DepositListLogicクラス．入金入力（リスト)
 * @author tosco
 */
public interface DepositListLogic {
	/**
	 * 検索処理を行う.入金入力検索処理
	 * @param condition condition
	 * @return List 検索結果
	 * @throws NoDataException 検索結果が存在しない場合発生
	 */
	List<DepositCredit> getSearchList(
			final DepositCreditListPagerCondition condition)
			throws NoDataException;

	/**
	 * ステータス更新
	 * @param frm 更新対象データ
	 * @param status ステータス
	 * @param statusMode ステータスモード
	 * @param tantoCd 担当者コード
	 * @throws NoDataException NoDataException
	 */
	void statusUpdate(final DepositListForm frm, final BigDecimal status,
			final BigDecimal statusMode, final String tantoCd)
			throws NoDataException;

	/**
	 * 帳票用検索処理を行う.
	 * @param condition 検索条件
	 * @return List<DepositCreditListForReport>
	 */
	List<DepositCreditListForReport> getListForReport(
			final DepositCreditListConditionForReport condition);

	/**
	 * 分類取得
	 * @param dataType サイトデータ種別
	 * @return List<ClassificationListForComboboxes>
	 */
	List<ClassificationListForComboboxes> getClassificationList(
			final BigDecimal dataType);

	/**
	 * 伝票発行フラグ更新
	 * @param creditNoList 更新対象入金番号一覧
	 * @param tantoCd 担当者コード
	 */
	void updateIssuedDivision(final ArrayList<String> creditNoList,
			final String tantoCd);
}
