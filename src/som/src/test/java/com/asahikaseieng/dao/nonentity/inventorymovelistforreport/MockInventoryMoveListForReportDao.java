/*
 * Created on 2009/04/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorymovelistforreport;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockInventoryMoveListForReportDaoクラス
 * @author t0011036
 */
public class MockInventoryMoveListForReportDao implements
		InventoryMoveListForReportDao {

	/**
	 * コンストラクタ.
	 */
	public MockInventoryMoveListForReportDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<InventoryMoveListForReport> getListForReport(
			final InventoryMoveListConditionForReport condition) {
		List<InventoryMoveListForReport> list = new ArrayList<InventoryMoveListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhItemCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhItemCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhOtherCompanyCd1())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhOtherCompanyCd1())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA
				.equals(condition.getSrhLocationCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhLocationCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* InventoryMoveListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * InventoryMoveListForReportを生成する
	 * @param i インデックス
	 * @return InventoryMoveListForReport
	 */
	private InventoryMoveListForReport createBean(final int i) {
		InventoryMoveListForReport bean = new InventoryMoveListForReport();
		bean.setItemCd("ITEM_CD" + i);
		bean.setLocationCd("LOCATION_CD" + i);
		bean.setAliasLotNo("ALIAS_LOT_NO" + i);
		bean.setFraction(new BigDecimal(i));
		bean.setInventoryQty(new BigDecimal(i));
		return bean;
	}
}
