package com.asahikaseieng.app.buying;

import java.util.List;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.entity.master.item.Item;
import com.asahikaseieng.dao.entity.master.item.ItemDao;
import com.asahikaseieng.dao.nonentity.buyinginout.BuyingInoutSearchList;
import com.asahikaseieng.dao.nonentity.buyinginout.BuyingInoutSearchListDao;
import com.asahikaseieng.dao.nonentity.buyinginout.BuyingInoutSearchListPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 受払検索(ポップアップ)ロジック 実装クラス.
 * @author t1344224
 */
public class BuyingInoutSearchLogicImpl implements BuyingInoutSearchLogic {

	private ItemDao itemDao;

	private BuyingInoutSearchListDao buyingInoutSearchListDao;

	/** 数値桁数チェック ロジッククラス */
	private CheckDigitUtilsLogic checker;

	/**
	 * コンストラクタ.
	 */
	public BuyingInoutSearchLogicImpl() {
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
	 * @return List<BuyingInoutSearchList> 検索結果リスト
	 * @throws NoDataException NoDataException
	 */
	public List<BuyingInoutSearchList> getList(
			final BuyingInoutSearchListPagerCondition condition,
			final String venderCd, final String unitOfOperationManagement)
			throws NoDataException {

		checkParams(condition);

		List<BuyingInoutSearchList> list = buyingInoutSearchListDao
				.getSearchList(condition);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		for (BuyingInoutSearchList bean : list) {

			bean.setStrInoutQty(checker.format(unitOfOperationManagement, "SI",
				venderCd, bean.getInoutQty()));

			bean.setStrInoutWeight(checker.format(unitOfOperationManagement,
				"SI", venderCd, bean.getInoutWeight()));

		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final BuyingInoutSearchListPagerCondition condition) {
		if (null == condition) {
			throw new IllegalArgumentException("null == condition");
		}
	}

	/* -------------------- setter -------------------- */
	/**
	 * 受払検索(ポップアップ)Daoを設定します。
	 * @param buyingInoutSearchListDao 受払検索(ポップアップ)Dao
	 */
	public void setBuyingInoutSearchListDao(
			final BuyingInoutSearchListDao buyingInoutSearchListDao) {
		this.buyingInoutSearchListDao = buyingInoutSearchListDao;
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
