/*
 * Created on 2009/01/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.unitpriceList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.unitpricelist.UnitpriceList;
import com.asahikaseieng.dao.nonentity.master.unitpricelist.UnitpriceListDao;
import com.asahikaseieng.dao.nonentity.master.unitpricelist.UnitpriceListPagerCondition;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockUnitpriceListDaoクラス
 * @author kanri-user
 */
public class MockUnitpriceListDao implements UnitpriceListDao {

	/**
	 * コンストラクタ.
	 */
	public MockUnitpriceListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<UnitpriceList> getList(
			final UnitpriceListPagerCondition condition) {
		List<UnitpriceList> list = new ArrayList<UnitpriceList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhVenderCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhVenderCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* UnitpriceListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * UnitpriceListを生成する
	 * @param i インデックス
	 * @return UnitpriceList
	 */
	private UnitpriceList createBean(final int i) {
		UnitpriceList bean = new UnitpriceList();
		bean.setVenderDivision("TS");
		bean.setVenderCd("VENDER001");
		bean.setItemCd("ITEM001");
		bean.setConsecutiveNo(new BigDecimal(i));
		bean.setItemName("ダイバークリーン");
		bean.setVenderName1("AEC");
		bean.setVersion(new BigDecimal(i));
		return bean;
	}
}
