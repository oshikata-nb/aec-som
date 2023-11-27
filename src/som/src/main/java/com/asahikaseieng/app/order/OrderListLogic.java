/*
 * Created on 2007/12/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.order;

import java.util.List;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.master.orderstatuslist.OrderStatusList;
import com.asahikaseieng.dao.nonentity.orderlist.OrderList;
import com.asahikaseieng.dao.nonentity.orderlist.OrderListPagerCondition;
import com.asahikaseieng.dao.nonentity.orderlistforreport.OrderDetailListForReport;
import com.asahikaseieng.dao.nonentity.orderlistforreport.OrderListConditionForReport;
import com.asahikaseieng.dao.nonentity.orderlistforreport.OrderListForReport;
import com.asahikaseieng.dao.nonentity.ordernameslist.OrderNamesList;
import com.asahikaseieng.dao.nonentity.orderslipforreport.RepOrderSlipConditionForReport;
import com.asahikaseieng.dao.nonentity.orderslipforreport.RepOrderSlipDetailForReport;
import com.asahikaseieng.dao.nonentity.orderslipforreport.RepOrderSlipHeaderForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 受注ヘッダー Logicクラス
 * @author t1344224
 */
public interface OrderListLogic {
	/**
	 * 検索処理を行う.
	 * @param condition 検索条件
	 * @param venderCd 得意先コード
	 * @throws NoDataException NoDataException
	 * @return List<OrderList>
	 */
	List<OrderList> getList(final OrderListPagerCondition condition,
			final String venderCd) throws NoDataException;

	/**
	 * 受注ヘッダ一覧（帳票用）
	 * @param condition 検索条件
	 * @return List<OrderListForReport>
	 */
	List<OrderListForReport> getListForReport(
			final OrderListConditionForReport condition);

	/**
	 * 受注詳細一覧（帳票用）
	 * @param condition 検索条件
	 * @return List<OrderDetailListForReport>
	 */
	List<OrderDetailListForReport> getDetailListForReport(
			final OrderListConditionForReport condition);

	/**
	 * 受注ヘッダ一覧（受注一覧用）
	 * @param condition 検索条件
	 * @return List<RepOrderSlipHeaderForReport>
	 */
	List<RepOrderSlipHeaderForReport> getSlipHeaderListForReport(
			final RepOrderSlipConditionForReport condition);

	/**
	 * 受注詳細一覧（受注一覧用）
	 * @param condition 検索条件
	 * @return List<RepOrderSlipDetailForReport>
	 */
	List<RepOrderSlipDetailForReport> getSlipDetailListForReport(
			final RepOrderSlipConditionForReport condition);

	/**
	 * 受注区分リスト取得
	 * @return List<OrderNamesList>
	 */
	List<OrderNamesList> getOrderDivisionList();

	/**
	 * 受注ステータスリスト取得
	 * @return List<NamesList>
	 */
	List<OrderStatusList> getOrderStatusList();

	/**
	 * 運送会社コンボボックス(すべて有)作成
	 * @return List<ComboBoxItems>
	 */
	List<ComboBoxItems> createCarryAllCombobox();
}
