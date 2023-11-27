/*
 * Created on 2009/04/08
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.item;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockItemForAutoCompleteDaoクラス
 * @author t0011036
 */
public class MockItemForAutoCompleteDao implements ItemForAutoCompleteDao {

	/**
	 * コンストラクタ.
	 */
	public MockItemForAutoCompleteDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ItemForAutoComplete> getDetailDigitPriceList(
			final String itemCd, final String rowlimit) {
		List<ItemForAutoComplete> list = new ArrayList<ItemForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(itemCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(itemCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ItemListForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * ItemForAutoCompleteを生成する
	 * @param i インデックス
	 * @return ItemListForAutoComplete
	 */
	private ItemForAutoComplete createBean(final int i) {
		ItemForAutoComplete bean = new ItemForAutoComplete();
		bean.setItemCd("ITEM_CD" + i);
		bean.setItemName("NAME" + i);
		return bean;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ItemForAutoComplete> getDetailDigitList(final String itemCd,
			final String rowlimit) {
		List<ItemForAutoComplete> list = new ArrayList<ItemForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(itemCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(itemCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ItemListForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ItemForAutoComplete> getDetailList(final String itemCd,
			final String rowlimit) {
		List<ItemForAutoComplete> list = new ArrayList<ItemForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(itemCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(itemCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ItemListForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ItemForAutoComplete> getOtherCompany1DetailList(
			final String otherCompany1, final String rowlimit) {
		List<ItemForAutoComplete> list = new ArrayList<ItemForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(otherCompany1)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(otherCompany1)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ItemListForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ItemForAutoComplete> getOtherCompany1SearchList(
			final String otherCompany1, final String rowlimit) {
		List<ItemForAutoComplete> list = new ArrayList<ItemForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(otherCompany1)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(otherCompany1)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ItemListForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ItemForAutoComplete> getOtherCompany1DetailDigitPriceList(
			final String otherCompany1, final String rowlimit) {
		List<ItemForAutoComplete> list = new ArrayList<ItemForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(otherCompany1)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(otherCompany1)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ItemListForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ItemForAutoComplete> getOtherCompany1DetailDigitList(
			final String otherCompany1, final String rowlimit) {
		List<ItemForAutoComplete> list = new ArrayList<ItemForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(otherCompany1)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(otherCompany1)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ItemListForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ItemForAutoComplete> getSearchList(final String itemCd,
			final String rowlimit) {
		List<ItemForAutoComplete> list = new ArrayList<ItemForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(itemCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(itemCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ItemListForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}
}
