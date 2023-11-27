/*
 * Created on 2007/04/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.order;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.shipping.ShippingConst;
import com.asahikaseieng.dao.entity.master.balance.Balance;
import com.asahikaseieng.dao.entity.master.balance.BalanceDao;
import com.asahikaseieng.dao.nonentity.comboboxes.shipping.ShippingCarryForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.shipping.ShippingCarryForComboboxesDao;
import com.asahikaseieng.dao.nonentity.master.orderstatuslist.OrderStatusList;
import com.asahikaseieng.dao.nonentity.master.orderstatuslist.OrderStatusListDao;
import com.asahikaseieng.dao.nonentity.orderlist.OrderList;
import com.asahikaseieng.dao.nonentity.orderlist.OrderListDao;
import com.asahikaseieng.dao.nonentity.orderlist.OrderListPagerCondition;
import com.asahikaseieng.dao.nonentity.orderlistforreport.OrderDetailListForReport;
import com.asahikaseieng.dao.nonentity.orderlistforreport.OrderDetailListForReportDao;
import com.asahikaseieng.dao.nonentity.orderlistforreport.OrderListConditionForReport;
import com.asahikaseieng.dao.nonentity.orderlistforreport.OrderListForReport;
import com.asahikaseieng.dao.nonentity.orderlistforreport.OrderListForReportDao;
import com.asahikaseieng.dao.nonentity.ordernameslist.OrderNamesList;
import com.asahikaseieng.dao.nonentity.ordernameslist.OrderNamesListDao;
import com.asahikaseieng.dao.nonentity.orderslipforreport.RepOrderSlipConditionForReport;
import com.asahikaseieng.dao.nonentity.orderslipforreport.RepOrderSlipDetailForReport;
import com.asahikaseieng.dao.nonentity.orderslipforreport.RepOrderSlipDetailForReportDao;
import com.asahikaseieng.dao.nonentity.orderslipforreport.RepOrderSlipHeaderForReport;
import com.asahikaseieng.dao.nonentity.orderslipforreport.RepOrderSlipHeaderForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 受注ヘッダー LogicImplクラス
 * @author t1344224
 */
public class OrderListLogicImpl implements OrderListLogic {

	private OrderListDao orderListDao;

	private OrderNamesListDao orderNamesListDao;

	private OrderStatusListDao orderStatusListDao;

	private OrderListForReportDao orderListForReportDao;

	private OrderDetailListForReportDao orderDetailListForReportDao;

	private RepOrderSlipHeaderForReportDao repOrderSlipHeaderForReportDao;

	private RepOrderSlipDetailForReportDao repOrderSlipDetailForReportDao;

	private BalanceDao balanceDao;

	/** 出荷指図ー運送会社コンボボックス用DAO */
	private ShippingCarryForComboboxesDao shippingCarryForComboboxesDao;

	/**
	 * コンストラクタ.
	 */
	public OrderListLogicImpl() {
	}

	/**
	 * 受注一覧検索
	 * @param condition 検索条件
	 * @return List<OrderList>
	 * @throws NoDataException NoDataException
	 */
	public List<OrderList> getList(final OrderListPagerCondition condition,
			final String venderCd) throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);

		// 2015/08/19 2次店以下で検索できるよう追加
		condition.setSrhVenderList(getVenderList(venderCd));

		List<OrderList> list = orderListDao.getList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * 
	 * 上位の得意先コードを取得する
	 * @param venderCd
	 * @return
	 */
	private ArrayList<String> getVenderList(final String venderCd) {

		ArrayList<String> venderList = new ArrayList<String>();
		List<Balance> list = balanceDao.getList(venderCd);
		if (list == null) {
			return null;
		} else {
			venderList.add(venderCd);
		}

		String upperBalanceCd = "";

		for (Balance bean : list) {
			// 上位帳合コードある場合のみ処理を行う
			if (bean.getUpperBalanceCd() != null) {
				upperBalanceCd = bean.getUpperBalanceCd();
				while (true) {

					Balance balance = balanceDao.getEntity(upperBalanceCd);
					// 上位帳合コードがある場合
					if (balance != null) {
						// 得意先コードがある場合リストに追加
						if (balance.getVenderCd() != null) {
							venderList.add(balance.getVenderCd());
						}
						// 上位帳合コードがある場合、上位帳合コードで検索を行う
						if (balance.getUpperBalanceCd() != null) {
							upperBalanceCd = balance.getUpperBalanceCd();
						} else {
							break;
						}

					} else {
						break;
					}
				}
			}
		}
		return venderList;
	}

	/**
	 * 受注ヘッダ一覧（帳票用）
	 * @param condition 検索条件
	 * @return List<OrderListForReport>
	 */
	public List<OrderListForReport> getListForReport(
			final OrderListConditionForReport condition) {
		List<OrderListForReport> list = orderListForReportDao
				.getListForReport(condition);
		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/**
	 * 受注ヘッダ一覧（帳票用）
	 * @param condition 検索条件
	 * @return List<OrderListForReport>
	 */
	public List<OrderDetailListForReport> getDetailListForReport(
			final OrderListConditionForReport condition) {
		List<OrderDetailListForReport> list = orderDetailListForReportDao
				.getDetailListForReport(condition);
		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/**
	 * 受注ヘッダ一覧（受注一覧用）
	 * @param condition 検索条件
	 * @return List<RepOrderSlipHeaderForReport>
	 */
	public List<RepOrderSlipHeaderForReport> getSlipHeaderListForReport(
			final RepOrderSlipConditionForReport condition) {

		List<RepOrderSlipHeaderForReport> list = repOrderSlipHeaderForReportDao
				.getHeaderListForReport(condition);
		if (list.isEmpty()) {
			return null;
		}

		return list;

	}

	/**
	 * 受注詳細一覧（受注一覧用）
	 * @param condition 検索条件
	 * @return List<RepOrderSlipDetailForReport>
	 */
	public List<RepOrderSlipDetailForReport> getSlipDetailListForReport(
			final RepOrderSlipConditionForReport condition) {

		List<RepOrderSlipDetailForReport> list = repOrderSlipDetailForReportDao
				.getDetailListForReport(condition);
		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/**
	 * 運送会社コンボボックス作成
	 * @return List<ComboBoxItems>
	 */
	public List<ComboBoxItems> createCarryCombobox() {
		List<ComboBoxItems> res = new ArrayList<ComboBoxItems>();

		// 運送会社検索
		List<ShippingCarryForComboboxes> lineList = getCarryList();
		for (ShippingCarryForComboboxes bean : lineList) {
			ComboBoxItems item = new ComboBoxItems();
			item.setValues(bean.getCarryCd());
			// 運送会社名称は運送会社名称１_運送会社名称２とする
			StringBuffer nameBuf = new StringBuffer("");
			nameBuf.append(bean.getCarryName1());
			if (bean.getCarryName2() != null
					&& !bean.getCarryName2().equals("")) {
				nameBuf.append("_").append(bean.getCarryName2());
			}
			item.setLabales(nameBuf.toString());
			res.add(item);
		}
		return res;
	}

	/**
	 * 運送会社一覧取得
	 * @return List<ShippingCarryForComboboxes>
	 */
	public List<ShippingCarryForComboboxes> getCarryList() {
		return shippingCarryForComboboxesDao.getCarryList();
	}

	/**
	 * 運送会社コンボボックス(すべて有)作成
	 * @return List<ComboBoxItems>
	 */
	public List<ComboBoxItems> createCarryAllCombobox() {
		// 運送会社マスタからステータスコンボボックス用配列を取得
		List<ComboBoxItems> res = createCarryCombobox();
		// 全てを追加
		ComboBoxItems allItem = new ComboBoxItems();
		allItem.setValues(ShippingConst.COMBO_ALL_VALUE);
		allItem.setLabales(ShippingConst.COMBO_ALL_LABEL);
		res.add(0, allItem);

		return res;
	}

	/**
	 * 受注区分リスト取得
	 * @return List<OrderNamesList>
	 */
	public List<OrderNamesList> getOrderDivisionList() {
		List<OrderNamesList> list = orderNamesListDao.getList("ORDR");
		return list;
	}

	/**
	 * 受注ステータスリスト取得
	 * @return List<OrderStatusList>
	 */
	public List<OrderStatusList> getOrderStatusList() {
		List<OrderStatusList> list = orderStatusListDao.getList(null);
		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final OrderListPagerCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException(
					" Paramater is illegal (getList)");
		}
	}

	/**
	 * orderListDaoを設定します。
	 * @param orderListDao OrderListDao
	 */
	public final void setOrderListDao(final OrderListDao orderListDao) {
		this.orderListDao = orderListDao;
	}

	/**
	 * orderStatusListDaoを設定します。
	 * @param orderStatusListDao orderStatusListDao
	 */
	public final void setOrderStatusListDao(
			final OrderStatusListDao orderStatusListDao) {
		this.orderStatusListDao = orderStatusListDao;
	}

	/**
	 * orderNamesListDaoを設定します。
	 * @param orderNamesListDao orderNamesListDao
	 */
	public void setOrderNamesListDao(final OrderNamesListDao orderNamesListDao) {
		this.orderNamesListDao = orderNamesListDao;
	}

	/**
	 * orderListForReportDaoを設定します。
	 * @param orderListForReportDao orderListForReportDao
	 */
	public void setOrderListForReportDao(
			final OrderListForReportDao orderListForReportDao) {
		this.orderListForReportDao = orderListForReportDao;
	}

	/**
	 * orderDetailListForReportDaoを設定します。
	 * @param orderDetailListForReportDao orderDetailListForReportDao
	 */
	public void setOrderDetailListForReportDao(
			final OrderDetailListForReportDao orderDetailListForReportDao) {
		this.orderDetailListForReportDao = orderDetailListForReportDao;
	}

	/**
	 * repOrderSlipHeaderForReportDaoを設定します。
	 * @param repOrderSlipHeaderForReportDao repOrderSlipHeaderForReportDao
	 */
	public void setRepOrderSlipHeaderForReportDao(
			final RepOrderSlipHeaderForReportDao repOrderSlipHeaderForReportDao) {
		this.repOrderSlipHeaderForReportDao = repOrderSlipHeaderForReportDao;
	}

	/**
	 * repOrderSlipDetailForReportDaoを設定します。
	 * @param repOrderSlipDetailForReportDao repOrderSlipDetailForReportDao
	 */
	public void setRepOrderSlipDetailForReportDao(
			final RepOrderSlipDetailForReportDao repOrderSlipDetailForReportDao) {
		this.repOrderSlipDetailForReportDao = repOrderSlipDetailForReportDao;
	}

	/**
	 * shippingCarryForComboboxesDaoを設定します。
	 * @param shippingCarryForComboboxesDao shippingCarryForComboboxesDao
	 */
	public void setShippingCarryForComboboxesDao(
			final ShippingCarryForComboboxesDao shippingCarryForComboboxesDao) {
		this.shippingCarryForComboboxesDao = shippingCarryForComboboxesDao;
	}

	/**
	 * balanceDaoを設定します。
	 * @param balanceDao balanceDao
	 */
	public void setBalanceDao(final BalanceDao balanceDao) {
		this.balanceDao = balanceDao;
	}
}
