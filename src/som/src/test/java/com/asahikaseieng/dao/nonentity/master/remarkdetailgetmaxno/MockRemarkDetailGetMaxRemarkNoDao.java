/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.remarkdetailgetmaxno;

import java.math.BigDecimal;

/**
 * MockRemarkDetailGetMaxRemarkNoDaoクラス
 * @author kanri-user
 */
public class MockRemarkDetailGetMaxRemarkNoDao implements
		RemarkDetailGetMaxRemarkNoDao {

	/**
	 * コンストラクタ.
	 */
	public MockRemarkDetailGetMaxRemarkNoDao() {
		super();
	}

	/**
	 * /** {@inheritDoc}
	 */
	public RemarkDetailGetMaxRemarkNo getMaxRemarkNo() {
		RemarkDetailGetMaxRemarkNo bean = new RemarkDetailGetMaxRemarkNo();

		/* RemarkDetailGetMaxRemarkNoを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * RemarkDetailGetMaxRemarkNoを生成する
	 * @param bean RemarkDetailGetMaxRemarkNo
	 * @return RemarkDetailGetMaxRemarkNo
	 */
	private void createBean(final RemarkDetailGetMaxRemarkNo bean) {
		bean.setMax(new BigDecimal(1));
	}
}
