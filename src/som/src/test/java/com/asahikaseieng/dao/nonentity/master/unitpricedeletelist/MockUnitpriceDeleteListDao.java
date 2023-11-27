/*
 * Created on 2009/05/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.unitpricedeletelist;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockUnitpriceDeleteListDaoクラス
 * @author t0011036
 */
public class MockUnitpriceDeleteListDao implements UnitpriceDeleteListDao {

	/**
	 * コンストラクタ.
	 */
	public MockUnitpriceDeleteListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public int deleteList(final String venderDivision, final String venderCd,
			final String itemCd, final BigDecimal version) {
		List<UnitpriceDeleteList> list = new ArrayList<UnitpriceDeleteList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(venderDivision)) {
			return 0;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(venderDivision)) {
			return 0;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(venderCd)) {
			return 0;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(itemCd)) {
			return 0;
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
			/* UnitpriceDeleteListを生成する */
			list.add(createBean(i));
		}

		return 1;
	}

	/**
	 * UnitpriceDeleteListを生成する
	 * @param i インデックス
	 * @return UnitpriceDeleteList
	 */
	private UnitpriceDeleteList createBean(final int i) {
		UnitpriceDeleteList bean = new UnitpriceDeleteList();
		bean.setConsecutiveNo(new BigDecimal(i));
		bean.setItemCd("ITEM" + i);
		bean.setQuantityFrom(new BigDecimal(i));
		bean.setQuantityTo(new BigDecimal(i));
		bean.setRemarks("REMARKS" + i);
		bean.setUnitprice(new BigDecimal(i));
		bean.setUnitpriceDivision("DIVISION" + i);
		bean.setVenderCd("VENDER" + i);
		bean.setVenderDivision("SI");
		bean.setVersion(new BigDecimal(i));
		return bean;
	}
}
