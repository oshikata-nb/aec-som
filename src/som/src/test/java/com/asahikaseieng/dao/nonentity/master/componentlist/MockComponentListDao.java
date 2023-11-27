/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.componentlist;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockComponentListDaoクラス
 * @author t0011036
 */
public class MockComponentListDao implements ComponentListDao {

	/**
	 * コンストラクタ.
	 */
	public MockComponentListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ComponentList> getList(
			final ComponentListPagerCondition condition) {
		List<ComponentList> list = new ArrayList<ComponentList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhComponentCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhComponentCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ComponentListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * ComponentListを生成する
	 * @param i インデックス
	 * @return ComponentList
	 */
	private ComponentList createBean(final int i) {
		ComponentList bean = new ComponentList();
		bean.setComponentCd("COMPONENT" + i);
		bean.setComponentName("NAME" + i);
		return bean;
	}
}
