/*
 * Created on 2009/03/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.order;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.entity.iteminventory.ItemInventory;
import com.asahikaseieng.dao.entity.iteminventory.ItemInventoryDao;
import com.asahikaseieng.dao.entity.master.item.Item;
import com.asahikaseieng.dao.entity.master.item.ItemDao;
import com.asahikaseieng.dao.nonentity.orderlotsearchlist.OrderLotSearchList;
import com.asahikaseieng.dao.nonentity.orderlotsearchlist.OrderLotSearchListDao;
import com.asahikaseieng.dao.nonentity.orderlotsearchlist.OrderLotSearchListPagerCondition;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.dao.nonentity.itempreorderqty.ItemPreOrderQtyBase;
import com.asahikaseieng.dao.nonentity.itempreorderqty.ItemPreOrderQtyDao;

/**
 * ロット検索(ポップアップ)ロジック 実装クラス.
 * @author tosco
 */
public class OrderLotSearchLogicImpl implements OrderLotSearchLogic {

	/** ロット検索(ポップアップ)Dao */
	private OrderLotSearchListDao orderLotSearchDao;

	private ItemInventoryDao itemInventoryDao;

	private ItemPreOrderQtyDao itemPreOrderQtyDao;

	
	/** 品目マスタ用Dao */
	private ItemDao itemDao;

	/** 数値桁数チェック ロジッククラス */
	private CheckDigitUtilsLogic checker;

	/**
	 * コンストラクタ.
	 */
	public OrderLotSearchLogicImpl() {
	}

	/**
	 * 品目在庫の販売引当残を取得処理を行う.
	 * @param itemCd 検索条件
	 * @return String 販売引当残
	 */
	public String getItemInventory(final String itemCd) {

		ItemInventory bean = itemInventoryDao.getEntity(itemCd);

		if (bean == null ) {
			return "0";
		} else {
			return bean.getSalesAssignQty().toString();
		}

	}

	/**
	 * 先付け受注数を取得する。
	 * @param itemCd 検索条件
	 * @return String 先付け受注数残
	 */
	public String getPreOrderQty(final String itemCd) {

		ItemPreOrderQtyBase bean = this.itemPreOrderQtyDao.getPreOrderQty(itemCd);

		if (bean == null || bean.getPreOrderQty() == null) {
			return "0";
		} else {
			return bean.getPreOrderQty().toString();
		}

	}
	
	/**
	 * 品目情報検索処理を行う.
	 * @param itemCd 品目コード
	 * @return Item 品目データ
	 * @throws NoDataException NoDataException
	 */
	public Item getItem(final String itemCd) throws NoDataException {
		if (StringUtils.isEmpty(itemCd)) {
			throw new IllegalArgumentException("itemCd is empty");
		}

		Item item = itemDao.getEntity(itemCd);

		if (item == null) {
			throw new NoDataException();
		}

		return item;
	}

	/**
	 * ロット検索処理を行う.
	 * @param condition 検索条件
	 * @param unitDivision 区分
	 * @param venderDivision 取引先区分
	 * @param venderCd 取引先コード
	 * @return List<OrderLotSearch> 検索結果リスト
	 * @throws NoDataException NoDataException
	 */
	public List<OrderLotSearchList> getList(
			final OrderLotSearchListPagerCondition condition,
			final String unitDivision, final String venderDivision,
			final String venderCd) throws NoDataException {

		checkParams(condition);

		List<OrderLotSearchList> list = orderLotSearchDao
				.getSearchList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}
		// 数値項目フォーマット
		for (OrderLotSearchList bean : list) {
			bean.setStrInventoryQty(checker.format(unitDivision,
				venderDivision, venderCd, bean.getInventoryQty())); // 在庫量
			bean.setStrBackorderQty(checker.format(unitDivision,
				venderDivision, venderCd, bean.getBackorderQty())); // 発注残
			bean.setStrInspectionQty(checker.format(unitDivision,
				venderDivision, venderCd, bean.getInspectionQty())); // 検査待
			bean.setStrAssignQty(checker.format(unitDivision, venderDivision,
				venderCd, bean.getAssignQty())); // 引当残
			bean.setStrValidInventory(checker.format(unitDivision,
				venderDivision, venderCd, bean.getValidInventory())); // 有効在庫
		}
		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final OrderLotSearchListPagerCondition condition) {
		if (null == condition) {
			throw new IllegalArgumentException("null == condition");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * ロット検索(ポップアップ)Daoを設定します。
	 * @param orderLotSearchDao ロットマスタ検索(ポップアップ)Dao
	 */
	public void setOrderLotSearchDao(
			final OrderLotSearchListDao orderLotSearchDao) {
		this.orderLotSearchDao = orderLotSearchDao;
	}

	/**
	 * 品目マスタ用Daoを設定します。
	 * @param itemDao 品目マスタ用Dao
	 */
	public void setItemDao(final ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	/**
	 * 数値桁数チェック ロジッククラスを設定します。
	 * @param checker checker
	 */
	public void setChecker(final CheckDigitUtilsLogic checker) {
		this.checker = checker;
	}

	/**
	 * 品目在庫用Daoを設定します。
	 * @param itemInventoryDao 品目在庫用Dao
	 */
	public void setItemInventoryDao(final ItemInventoryDao itemInventoryDao) {
		this.itemInventoryDao = itemInventoryDao;
	}
	
	/**
	 * itemPointingOrderQtyDaoを設定します。
	 * @param itemPointingOrderQtyDao itemPointingOrderQtyDao
	 */
	public void setItemPreOrderQtyDao(ItemPreOrderQtyDao itemPreOrderQtyDao) {
		this.itemPreOrderQtyDao = itemPreOrderQtyDao;
	}
}
