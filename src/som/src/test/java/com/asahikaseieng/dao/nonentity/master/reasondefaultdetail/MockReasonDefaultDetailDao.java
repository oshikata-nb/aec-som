/*
 * Created on 2009/04/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.reasondefaultdetail;

/**
 * MockReasonDefaultDetailDaoクラス
 * @author t0011036
 */
public class MockReasonDefaultDetailDao implements ReasonDefaultDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockReasonDefaultDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public ReasonDefaultDetail getEntity() {
		ReasonDefaultDetail bean = new ReasonDefaultDetail();

		/* ReasonDefaultDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * ReasonDefaultDetailを生成する
	 * @param bean bean
	 * @return ReasonDefaultDetail
	 */
	private void createBean(final ReasonDefaultDetail bean) {
		bean.setRyCd("RY_CD001");
		bean.setRyDescription("RY_DESCRIPTION001");
	}
}
