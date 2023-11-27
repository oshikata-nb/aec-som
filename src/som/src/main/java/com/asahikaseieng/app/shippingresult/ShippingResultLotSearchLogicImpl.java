/*
 * Created on 2009/03/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.shippingresult;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.entity.master.item.Item;
import com.asahikaseieng.dao.entity.master.item.ItemDao;
import com.asahikaseieng.dao.nonentity.shippingresult.ShippingResultLotSearchList;
import com.asahikaseieng.dao.nonentity.shippingresult.ShippingResultLotSearchListDao;
import com.asahikaseieng.dao.nonentity.shippingresult.ShippingResultLotSearchListPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * ロット検索(ポップアップ)ロジック 実装クラス.
 * @author tosco
 */
public class ShippingResultLotSearchLogicImpl implements ShippingResultLotSearchLogic {

	/** 品目マスタ用Dao */
	private ItemDao itemDao;

	/** ロット検索(ポップアップ)Dao */
	private ShippingResultLotSearchListDao shippingResultLotSearchDao;

	/** 数値桁数チェック ロジッククラス */
	private CheckDigitUtilsLogic checker;

	/**
	 * コンストラクタ.
	 */
	public ShippingResultLotSearchLogicImpl() {
	}

	/**
	 * 品目情報検索処理を行う.
	 * @param itemCd 品目コード
	 * @return ShippingResultLotSearch 検索結果
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
	 * @return List<ShippingResultLotSearch> 検索結果リスト
	 * @throws NoDataException NoDataException
	 */
	public List<ShippingResultLotSearchList> getList(final ShippingResultLotSearchListPagerCondition condition,
		final String unitDivision, final String venderDivision, final String venderCd) throws NoDataException {

		checkParams(condition);

		List<ShippingResultLotSearchList> list = shippingResultLotSearchDao.getSearchList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}
		//数値項目フォーマット
		for (ShippingResultLotSearchList bean : list) {
			bean.setStrInventoryQty(checker.format(unitDivision,
				venderDivision, venderCd, bean.getInventoryQty()));			//在庫量
			bean.setStrBackorderQty(checker.format(unitDivision,
				venderDivision, venderCd, bean.getBackorderQty()));			//発注残
			bean.setStrInspectionQty(checker.format(unitDivision,
				venderDivision, venderCd, bean.getInspectionQty()));		//検査待
			bean.setStrAssignQty(checker.format(unitDivision,
				venderDivision, venderCd, bean.getAssignQty()));			//引当残
			bean.setStrValidInventory(checker.format(unitDivision,
				venderDivision, venderCd, bean.getValidInventory()));		//有効在庫
		}
		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final ShippingResultLotSearchListPagerCondition condition) {
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
	 * @param shippingResultLotSearchDao ロットマスタ検索(ポップアップ)Dao
	 */
	public void setShippingResultLotSearchDao(final ShippingResultLotSearchListDao shippingResultLotSearchDao) {
		this.shippingResultLotSearchDao = shippingResultLotSearchDao;
	}

	/**
	 * 数値桁数チェック ロジッククラスを設定します。
	 * @param checker checker
	 */
	public void setChecker(final CheckDigitUtilsLogic checker) {
		this.checker = checker;
	}
}
