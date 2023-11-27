/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.itemqueue;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockItemQueueListForAutoCompleteDaoクラス
 * @author t0011036
 */
public class MockItemQueueListForAutoCompleteDao implements
		ItemQueueForAutoCompleteDao {

	/**
	 * コンストラクタ.
	 */
	public MockItemQueueListForAutoCompleteDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ItemQueueForAutoComplete> getListForAutoComplete(
			final String itemCd, final String rowlimit) {
		List<ItemQueueForAutoComplete> list = new ArrayList<ItemQueueForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(itemCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(itemCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ItemQueueListForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * ItemQueueListForAutoCompleteを生成する
	 * @param i インデックス
	 * @return ItemQueueListForAutoComplete
	 */
	private ItemQueueForAutoComplete createBean(final int i) {
		ItemQueueForAutoComplete bean = new ItemQueueForAutoComplete();
		bean.setItemCd("ITEM" + i);
		bean.setItemName("NAME" + i);
		return bean;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ItemQueueForAutoComplete> getDetailDigitList(
			final String itemCd, final String rowlimit) {
		List<ItemQueueForAutoComplete> list = new ArrayList<ItemQueueForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(itemCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(itemCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ItemQueueListForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ItemQueueForAutoComplete> getDetailList(final String itemCd,
			final String rowlimit) {
		List<ItemQueueForAutoComplete> list = new ArrayList<ItemQueueForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(itemCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(itemCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ItemQueueListForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ItemQueueForAutoComplete> getOtherCompany1DetailList(
			final String otherCompany1, final String rowlimit) {
		List<ItemQueueForAutoComplete> list = new ArrayList<ItemQueueForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(otherCompany1)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(otherCompany1)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ItemQueueListForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ItemQueueForAutoComplete> getOtherCompany1SearchList(
			final String otherCompany1, final String rowlimit) {
		List<ItemQueueForAutoComplete> list = new ArrayList<ItemQueueForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(otherCompany1)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(otherCompany1)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ItemQueueListForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ItemQueueForAutoComplete> getSearchList(final String itemCd,
			final String rowlimit) {
		List<ItemQueueForAutoComplete> list = new ArrayList<ItemQueueForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(itemCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(itemCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ItemQueueListForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ItemQueueForAutoComplete> getOtherCompany1DetailDigitList(
			final String otherCompany1, final String rowlimit) {
		List<ItemQueueForAutoComplete> list = new ArrayList<ItemQueueForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(otherCompany1)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(otherCompany1)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ItemQueueListForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}
}
