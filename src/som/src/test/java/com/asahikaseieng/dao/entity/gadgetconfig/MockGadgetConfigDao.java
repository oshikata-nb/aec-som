/*
 * Created on 2007/04/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.gadgetconfig;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * GadgetConfigDaoのMock
 * @author jbd
 */
public class MockGadgetConfigDao implements GadgetConfigDao {

	/**
	 * コンストラクタ
	 */
	public MockGadgetConfigDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public int insert(final GadgetConfig bean) {
		return 1;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<GadgetConfig> getListByTantoCd(final String loginId) {

		List<GadgetConfig> list = new ArrayList<GadgetConfig>();

		for (int i = 1; i <= 10; i++) {
			list.add(createBean(i, loginId));
		}
		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public void deleteByTantoCd(final String tantoCd) {
	}

	/**
	 * GadgetConfig生成
	 * @param index インデックス
	 * @return GadgetConfig
	 */
	private GadgetConfig createBean(final int index, final String loginId) {
		GadgetConfig bean = new GadgetConfig();
		bean.setTantoCd(loginId);
		bean.setGadgetId("00" + index);
		bean.setLaneNo(new BigDecimal(index));
		bean.setVerticalOrder(new BigDecimal(index));
		return bean;
	}

}
