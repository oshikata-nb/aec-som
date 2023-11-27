/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.unitpricedetaillist;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockUnitpriceDetailListDaoクラス
 * @author kanri-user
 */
public class MockUnitpriceDetailListDao implements UnitpriceDetailListDao {

	/**
	 * コンストラクタ.
	 */
	public MockUnitpriceDetailListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<UnitpriceDetailList> getList(final String venderDivision,
			final String venderCd, final String itemCd, final BigDecimal version) {
		List<UnitpriceDetailList> list = new ArrayList<UnitpriceDetailList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(venderDivision)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(venderCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(itemCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(venderDivision)) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(venderCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(itemCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* UnitpriceDetailListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * UnitpriceDetailListを生成する
	 * @param i インデックス
	 * @return UnitpriceDetailList
	 */
	private UnitpriceDetailList createBean(final int i) {
		UnitpriceDetailList bean = new UnitpriceDetailList();
		bean.setVenderDivision("SI");
		bean.setVenderCd("VENDER" + i);
		bean.setItemCd("ITEM" + i);
		bean.setConsecutiveNo(new BigDecimal(i));
		bean.setItemName("NAME" + i);
		bean.setVenderName1("NAME" + i);
		bean.setVersion(new BigDecimal(i));
		return bean;
	}
}
