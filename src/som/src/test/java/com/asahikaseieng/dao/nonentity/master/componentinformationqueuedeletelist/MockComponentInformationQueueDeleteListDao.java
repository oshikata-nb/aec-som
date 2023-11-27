/*
 * Created on 2009/05/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.componentinformationqueuedeletelist;

import java.math.BigDecimal;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockComponentInformationQueueDeleteListDaoクラス
 * @author t0011036
 */
public class MockComponentInformationQueueDeleteListDao implements
		ComponentInformationQueueDeleteListDao {

	/**
	 * コンストラクタ.
	 */
	public MockComponentInformationQueueDeleteListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public int deleteList(final String itemCd, final BigDecimal version) {
		if (Constants.TEST_PARAMETER_NODATA.equals(itemCd)) {
			return 0;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(itemCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		ComponentInformationQueueDeleteList bean = new ComponentInformationQueueDeleteList();

		/* ComponentInformationQueueDeleteListを生成する */
		createBean(bean);

		return 1;
	}

	/**
	 * ComponentInformationQueueDeleteListを生成する
	 * @param i インデックス
	 * @return ComponentInformationQueueDeleteList
	 */
	private void createBean(final ComponentInformationQueueDeleteList bean) {
		bean.setItemCd("ITEM_CD001");
		bean.setVersion(new BigDecimal("1"));
	}
}
