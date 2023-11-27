/*
 * Created on 2009/01/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.componentinformationqueuelist;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockComponentInformationQueueListDaoクラス
 * @author t0011036
 */
public class MockComponentInformationQueueListDao implements
		ComponentInformationQueueListDao {

	/**
	 * コンストラクタ.
	 */
	public MockComponentInformationQueueListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ComponentInformationQueueList> getList(final String itemCd,
			final BigDecimal version) {
		List<ComponentInformationQueueList> list = new ArrayList<ComponentInformationQueueList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(itemCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(itemCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ComponentInformationQueueListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * ComponentInformationQueueListを生成する
	 * @param i インデックス
	 * @return ComponentInformationQueueList
	 */
	private ComponentInformationQueueList createBean(final int i) {
		ComponentInformationQueueList bean = new ComponentInformationQueueList();
		bean.setItemCd("ITEM" + i);
		bean.setVersion(new BigDecimal(i));
		return bean;
	}
}
