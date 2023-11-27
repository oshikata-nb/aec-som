/*
 * Created on 2009/02/02
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.buyingapproval;

import java.util.List;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.buyingapproval.BuyingApprovalList;
import com.asahikaseieng.dao.nonentity.buyingapproval.BuyingApprovalPagerCondition;
import com.asahikaseieng.dao.nonentity.buyingapprovalforreport.BuyingApprovalListConditionForReport;
import com.asahikaseieng.dao.nonentity.buyingapprovalforreport.BuyingApprovalListForReport;
import com.asahikaseieng.dao.nonentity.comboboxes.buying.BuyingStockingDivisionComboboxes;
import com.asahikaseieng.exception.NoDataException;

/**
 * 仕入承認画面 ロジッククラス interface.
 * @author tosco
 */
public interface BuyingApprovalListLogic {

	/**
	 * 仕入承認対象の検索を行う
	 * @param condition 検索条件
	 * @return List<BuyingApprovalList> データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	List<BuyingApprovalList> getSearchList(
			final BuyingApprovalPagerCondition condition)
			throws NoDataException;

	/**
	 * 承認処理を行う
	 * @param searchList 一覧検索結果
	 * @param loginUserId ログインユーザ
	 * @throws Exception Exception
	 */
	void updateApproval(final List<BuyingApprovalList> searchList,
			final String loginUserId) throws Exception;

	/**
	 * 否認処理を行う
	 * @param searchList 一覧検索結果
	 * @param loginUserId ログインユーザ
	 * @throws Exception Exception
	 */
	void updateDeny(final List<BuyingApprovalList> searchList,
			final String loginUserId) throws Exception;

	/**
	 * 仕入区分コンボボックスを取得する
	 * @return List<BuyingStockingDivisionComboboxes>
	 */
	List<BuyingStockingDivisionComboboxes> getBuyingStockingDivisionComboboxes();

	/**
	 * 仕入区分コンボボックス作成
	 * @param zero すべての設定可否(true:すべてを設定する false:すべてを設定しない)
	 * @return List<ComboBoxItems>
	 */
	List<ComboBoxItems> createBuyingApprovalStockingDivisionCombobox(
			final boolean zero);

	/**
	 * 仕入承認検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<BuyingApprovalListForReport> 検索結果リスト
	 */
	List<BuyingApprovalListForReport> getReportList(
			final BuyingApprovalListConditionForReport condition);
}
