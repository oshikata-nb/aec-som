/*
 * Created on 2009/02/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.shipping;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.entity.master.item.Item;
import com.asahikaseieng.dao.entity.master.item.ItemDao;
import com.asahikaseieng.dao.nonentity.shipping.ShippingOtherLotSearchList;
import com.asahikaseieng.dao.nonentity.shipping.ShippingOtherLotSearchListDao;
import com.asahikaseieng.dao.nonentity.shipping.ShippingOtherLotSearchListPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * ロット検索(ポップアップ)ロジック 実装クラス.
 * @author tosco
 */
public class ShippingOtherLotSearchLogicImpl implements
		ShippingOtherLotSearchLogic {

	/** 品目マスタ用Dao */
	private ItemDao itemDao;

	/** ロット検索(ポップアップ)Dao */
	private ShippingOtherLotSearchListDao shippingOtherLotSearchDao;

	/** 数値桁数チェック ロジッククラス */
	private CheckDigitUtilsLogic checker;

	/**
	 * コンストラクタ.
	 */
	public ShippingOtherLotSearchLogicImpl() {
	}

	/**
	 * 品目情報検索処理を行う.
	 * @param itemCd 品目コード
	 * @return ShippingOtherLotSearch 検索結果
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
	 * @return List<ShippingOtherLotSearch> 検索結果リスト
	 * @throws NoDataException NoDataException
	 */
	public List<ShippingOtherLotSearchList> getList(
			final ShippingOtherLotSearchListPagerCondition condition,
			final String unitDivision, final String venderDivision,
			final String venderCd) throws NoDataException {

		checkParams(condition);

		List<ShippingOtherLotSearchList> list = shippingOtherLotSearchDao
				.getSearchList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}
		// 数値項目フォーマット
		for (ShippingOtherLotSearchList bean : list) {
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
	private void checkParams(
			final ShippingOtherLotSearchListPagerCondition condition) {
		if (null == condition) {
			throw new IllegalArgumentException("null == condition");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 品目マスタ用Daoを設定します。
	 * @param itemDao 品目マスタ用Dao
	 */
	public void setItemDao(final ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	/**
	 * ロット検索(ポップアップ)Daoを設定します。
	 * @param shippingOtherLotSearchDao ロットマスタ検索(ポップアップ)Dao
	 */
	public void setShippingOtherLotSearchDao(
			final ShippingOtherLotSearchListDao shippingOtherLotSearchDao) {
		this.shippingOtherLotSearchDao = shippingOtherLotSearchDao;
	}

	/**
	 * 数値桁数チェック ロジッククラスを設定します。
	 * @param checker checker
	 */
	public void setChecker(final CheckDigitUtilsLogic checker) {
		this.checker = checker;
	}
}
