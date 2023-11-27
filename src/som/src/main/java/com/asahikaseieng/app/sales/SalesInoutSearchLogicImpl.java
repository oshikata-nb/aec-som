package com.asahikaseieng.app.sales;

import java.util.List;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.entity.master.item.Item;
import com.asahikaseieng.dao.entity.master.item.ItemDao;
import com.asahikaseieng.dao.nonentity.salesinout.SalesInoutSearchList;
import com.asahikaseieng.dao.nonentity.salesinout.SalesInoutSearchListDao;
import com.asahikaseieng.dao.nonentity.salesinout.SalesInoutSearchListPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 受払検索(ポップアップ)ロジック 実装クラス.
 * @author t1344224
 */
public class SalesInoutSearchLogicImpl implements SalesInoutSearchLogic {

	private ItemDao itemDao;

	private SalesInoutSearchListDao salesInoutSearchListDao;

	/** 数値桁数チェック ロジッククラス */
	private CheckDigitUtilsLogic checker;

	/**
	 * コンストラクタ.
	 */
	public SalesInoutSearchLogicImpl() {
	}

	/**
	 * 品目データを取得する
	 * @param itemCd 品目コード
	 * @return ITEM 品目マスタデータ
	 */
	public Item getItemData(final String itemCd) {

		Item bean = itemDao.getEntity(itemCd);

		return bean;

	}

	/**
	 * 受払検索処理を行う.
	 * @param condition 検索条件
	 * @param venderCd 仕入先コード
	 * @param unitOfOperationManagement 運用管理単位
	 * @return List<SalesInoutSearchList> 検索結果リスト
	 * @throws NoDataException NoDataException
	 */
	public List<SalesInoutSearchList> getList(
			final SalesInoutSearchListPagerCondition condition,
			final String venderCd, final String unitOfOperationManagement)
			throws NoDataException {

		checkParams(condition);

		List<SalesInoutSearchList> list = salesInoutSearchListDao
				.getSearchList(condition);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		for (SalesInoutSearchList bean : list) {

			bean.setStrInoutQty(checker.format(unitOfOperationManagement, "TS",
				venderCd, bean.getInoutQty()));

			bean.setStrInoutWeight(checker.format(unitOfOperationManagement,
				"TS", venderCd, bean.getInoutWeight()));

		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final SalesInoutSearchListPagerCondition condition) {
		if (null == condition) {
			throw new IllegalArgumentException("null == condition");
		}
	}

	/* -------------------- setter -------------------- */
	/**
	 * 受払検索(ポップアップ)Daoを設定します。
	 * @param salesInoutSearchListDao 受払検索(ポップアップ)Dao
	 */
	public void setSalesInoutSearchListDao(
			final SalesInoutSearchListDao salesInoutSearchListDao) {
		this.salesInoutSearchListDao = salesInoutSearchListDao;
	}

	/**
	 * 数値桁数チェック ロジッククラスを設定します。
	 * @param checker checker
	 */
	public void setChecker(final CheckDigitUtilsLogic checker) {
		this.checker = checker;
	}

	/**
	 * itemDao
	 * @param itemDao itemDao
	 */
	public void setItemDao(final ItemDao itemDao) {
		this.itemDao = itemDao;
	}
}
