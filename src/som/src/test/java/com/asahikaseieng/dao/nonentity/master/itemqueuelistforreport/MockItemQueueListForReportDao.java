/*
 * Created on 2009/05/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemqueuelistforreport;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockItemQueueListForReportDaoクラス
 * @author t0011036
 */
public class MockItemQueueListForReportDao implements ItemQueueListForReportDao {

	/**
	 * コンストラクタ.
	 */
	public MockItemQueueListForReportDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ItemQueueListForReport> getActivateListForReport(
			final ItemQueueListConditionForReport condition) {
		List<ItemQueueListForReport> list = new ArrayList<ItemQueueListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhItemCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhItemCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhParentItemCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhParentItemCd())) {
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

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getStrSrhActiveDateFrom())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getStrSrhActiveDateFrom())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getStrSrhActiveDateTo())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getStrSrhActiveDateTo())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ItemQueueListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ItemQueueListForReport> getInactivateListForReport(
			final ItemQueueListConditionForReport condition) {
		List<ItemQueueListForReport> list = new ArrayList<ItemQueueListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhItemCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhItemCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhParentItemCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhParentItemCd())) {
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

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getStrSrhActiveDateFrom())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getStrSrhActiveDateFrom())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getStrSrhActiveDateTo())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getStrSrhActiveDateTo())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ItemQueueListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * 品目一覧帳票用検索
	 * @param condition 検索条件
	 * @return List(ItemQueueListForReport>
	 */
	public List<ItemQueueListForReport> getListForReport(
			final ItemQueueListConditionForReport condition) {
		List<ItemQueueListForReport> list = new ArrayList<ItemQueueListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhItemCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhItemCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhParentItemCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhParentItemCd())) {
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

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getStrSrhActiveDateFrom())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getStrSrhActiveDateFrom())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getStrSrhActiveDateTo())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getStrSrhActiveDateTo())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ItemQueueListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * ItemQueueListForReportを生成する
	 * @param i インデックス
	 * @return ItemQueueListForReport
	 */
	private ItemQueueListForReport createBean(final int i) {
		ItemQueueListForReport bean = new ItemQueueListForReport();
		bean.setItemCd("ITEM" + i);
		bean.setItemName("NAME" + i);
		bean.setStatusName("STATUS_NAME" + i);
		return bean;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ItemQueueListForReport> getArticleAttributeQueueListForReport(
			final ItemQueueListConditionForReport condition) {
		List<ItemQueueListForReport> list = new ArrayList<ItemQueueListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhItemCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhItemCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ItemQueueListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ItemQueueListForReport> getChangeHistoryListForReport(
			final ItemQueueListConditionForReport condition) {
		List<ItemQueueListForReport> list = new ArrayList<ItemQueueListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhItemCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhItemCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ItemQueueListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ItemQueueListForReport> getCommonAttributeQueueListForReport(
			final ItemQueueListConditionForReport condition) {
		List<ItemQueueListForReport> list = new ArrayList<ItemQueueListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhItemCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhItemCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ItemQueueListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ItemQueueListForReport> getComponentInformationQueueListForReport(
			final ItemQueueListConditionForReport condition) {
		List<ItemQueueListForReport> list = new ArrayList<ItemQueueListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhItemCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhItemCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ItemQueueListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ItemQueueListForReport> getItemRemarkListForReport(
			final ItemQueueListConditionForReport condition) {
		List<ItemQueueListForReport> list = new ArrayList<ItemQueueListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhItemCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhItemCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ItemQueueListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ItemQueueListForReport> getLabelListForReport(
			final ItemQueueListConditionForReport condition) {
		List<ItemQueueListForReport> list = new ArrayList<ItemQueueListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhItemCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhItemCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ItemQueueListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ItemQueueListForReport> getProductAttributeQueueListForReport(
			final ItemQueueListConditionForReport condition) {
		List<ItemQueueListForReport> list = new ArrayList<ItemQueueListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhItemCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhItemCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ItemQueueListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ItemQueueListForReport> getPurchaseAttributeQueueListForReport(
			final ItemQueueListConditionForReport condition) {
		List<ItemQueueListForReport> list = new ArrayList<ItemQueueListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhItemCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhItemCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ItemQueueListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ItemQueueListForReport> getRecipeHeaderListForReport(
			final ItemQueueListConditionForReport condition) {
		List<ItemQueueListForReport> list = new ArrayList<ItemQueueListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhItemCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhItemCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ItemQueueListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}
}
