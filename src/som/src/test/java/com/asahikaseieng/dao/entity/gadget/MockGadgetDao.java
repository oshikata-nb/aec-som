/*
 * Created on 2007/04/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.gadget;

import java.math.BigDecimal;

import com.asahikaseieng.Constants;

/**
 * GadgetDaoのMock
 * @author jbd
 */
public class MockGadgetDao implements GadgetDao {

	/**
	 * コンストラクタ
	 */
	public MockGadgetDao() {
	}

	/**
	 * {@inheritDoc}
	 */
	public Gadget getEntity(final String gadgetId) {

		if (Constants.TEST_PARAMETER_NODATA.equals(gadgetId)) {
			return null;
		}

		Gadget bean = new Gadget();
		bean.setGadgetId("id");
		bean.setTitle("title");
		bean.setTitleUrl("hoge.do");
		bean.setActionUrl("hoge.do");

		return bean;
	}

	/**
	 * エンティティ取得.
	 * @param gadgetId gadgetId
	 * @param menuId menuId
	 * @param tabId tabId
	 * @param controlId controlId
	 * @return sqlString
	 */
	public Gadget getSqlString(final String gadgetId, final BigDecimal menuId,
			final BigDecimal tabId, final BigDecimal controlId) {

		if (Constants.TEST_PARAMETER_NODATA.equals(gadgetId)) {
			return null;
		}

		Gadget bean = new Gadget();
		bean.setGadgetId("id");
		bean.setTitle("title");
		bean.setTitleUrl("hoge.do");
		bean.setActionUrl("hoge.do");

		return bean;
	}
}
