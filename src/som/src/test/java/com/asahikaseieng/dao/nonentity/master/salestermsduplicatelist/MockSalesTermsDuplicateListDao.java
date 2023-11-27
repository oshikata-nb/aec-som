/*
 * Created on 2009/04/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.salestermsduplicatelist;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockSalesTermsDuplicateListDaoクラス
 * @author t0011036
 */
public class MockSalesTermsDuplicateListDao implements
		SalesTermsDuplicateListDao {

	/**
	 * コンストラクタ.
	 */
	public MockSalesTermsDuplicateListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<SalesTermsDuplicateList> getDuplicateList(
			final String deliveryCd, final String balanceCd, final String itemCd) {
		List<SalesTermsDuplicateList> list = new ArrayList<SalesTermsDuplicateList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(deliveryCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(balanceCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(itemCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(deliveryCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(balanceCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(itemCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* SalesTermsDetailListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * SalesTermsDuplicateListを生成する
	 * @param i インデックス
	 * @return SalesTermsDuplicateList
	 */
	private SalesTermsDuplicateList createBean(final int i) {
		SalesTermsDuplicateList bean = new SalesTermsDuplicateList();
		bean.setDeliveryCd("DELIVERY_CD" + i);
		bean.setBalanceCd("BALANCE_CD" + i);
		bean.setItemCd("ITEM_CD" + i);
		return bean;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<SalesTermsDuplicateList> getDuplicateAllList(
			final String deliveryCd, final String balanceCd, final String itemCd) {
		List<SalesTermsDuplicateList> list = new ArrayList<SalesTermsDuplicateList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(deliveryCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(balanceCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(itemCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(deliveryCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(balanceCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(itemCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* SalesTermsDetailListを生成する */
			list.add(createBean(i));
		}

		return list;
	}
}
