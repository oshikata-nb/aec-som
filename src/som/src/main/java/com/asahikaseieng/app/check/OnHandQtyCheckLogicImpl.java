/*
 * Created on 2009/06/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.check;

import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.dao.entity.iteminventory.ItemInventory;
import com.asahikaseieng.dao.entity.iteminventory.ItemInventoryDao;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionFormulaList;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionFormulaListDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 製造・包装指図用 手持ち在庫チェック
 * @author a7710658
 */
public class OnHandQtyCheckLogicImpl implements OnHandQtyCheckLogic {

	private ItemInventoryDao itemInventoryDao;

	private DirectionDirectionFormulaListDao directionDirectionFormulaListDao;

	/**
	 * コンストラクタ
	 */
	public OnHandQtyCheckLogicImpl() {
	}

	/**
	 * {@inheritDoc}
	 */
	public String onHandQtyCheck(final BigDecimal directionDivision,
			final String directionNo) throws NoDataException {
		String mes = "";
		List<DirectionDirectionFormulaList> list = directionDirectionFormulaListDao
				.getSearchAllList(directionNo, directionDivision);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		for (DirectionDirectionFormulaList bn : list) {
			ItemInventory bean = itemInventoryDao.getEntity(bn.getItemCd());
			if (bean == null) {
				throw new NoDataException();
			}
			if (bean.getInventoryQty().compareTo(bn.getQty()) < 0) {
				mes = mes + "在庫が不足しています。(" + bn.getItemName() + ")<BR>";
			}
		}
		return mes;
	}

	/**
	 * itemInventoryDaoを設定します。
	 * @param itemInventoryDao itemInventoryDao
	 */
	public void setItemInventoryDao(final ItemInventoryDao itemInventoryDao) {
		this.itemInventoryDao = itemInventoryDao;
	}

	/**
	 * directionDirectionFormulaListDaoを設定します。
	 * @param directionDirectionFormulaListDao directionDirectionFormulaListDao
	 */
	public void setDirectionDirectionFormulaListDao(
			final DirectionDirectionFormulaListDao directionDirectionFormulaListDao) {
		this.directionDirectionFormulaListDao = directionDirectionFormulaListDao;
	}

}
