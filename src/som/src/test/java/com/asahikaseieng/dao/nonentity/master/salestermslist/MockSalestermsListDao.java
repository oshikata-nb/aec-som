/*
 * Created on 2009/03/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.salestermslist;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockSalestermsListDaoクラス
 * @author t0011036
 */
public class MockSalestermsListDao implements SalesTermsListDao {

	/**
	 * コンストラクタ.
	 */
	public MockSalestermsListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<SalesTermsList> getList(
			final SalesTermsListPagerCondition condition) {
		List<SalesTermsList> list = new ArrayList<SalesTermsList>();

		if (Constants.TEST_PARAMETER_NODATA
				.equals(condition.getSrhDeliveryCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhItemCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhVenderCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhDeliveryCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhItemCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhVenderCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* SalesTermsListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * SalesTermsListを生成する
	 * @param i インデックス
	 * @return SalesTermsList
	 */
	private SalesTermsList createBean(final int i) {
		SalesTermsList bean = new SalesTermsList();
		bean.setBalanceCd("BALANCE_CD" + i);
		bean.setDeliveryCd("DELIVERY_CD" + i);
		bean.setDeliveryName1("DELIVERY_NAME" + i);
		bean.setItemCd("ITEM_CD" + i);
		bean.setItemName("ITEM_NAME" + i);
		bean.setStyleOfPacking("STYLE_OF_PACKING" + i);
		bean.setVenderCd("VENDER_CD" + i);
		bean.setVenderName1("VENDER_NAME" + i);
		return bean;
	}
}
