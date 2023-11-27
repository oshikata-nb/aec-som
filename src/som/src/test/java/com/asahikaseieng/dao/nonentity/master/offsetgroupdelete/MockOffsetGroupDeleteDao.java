/*
 * Created on 2009/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.offsetgroupdelete;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockOffsetGroupDeleteDaoクラス
 * @author t0011036
 */
public class MockOffsetGroupDeleteDao implements OffsetGroupDeleteDao {

	/**
	 * コンストラクタ.
	 */
	public MockOffsetGroupDeleteDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public int delete(final String offsetGroupCd) {
		if (Constants.TEST_PARAMETER_NODATA.equals(offsetGroupCd)) {
			return 0;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(offsetGroupCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		OffsetGroupDelete bean = new OffsetGroupDelete();

		/* OffsetGroupDeleteを生成する */
		createBean(bean);

		return 1;
	}

	/**
	 * OffsetGroupDeleteを生成する
	 * @param bean bean
	 * @return OffsetGroupDelete
	 */
	private void createBean(final OffsetGroupDelete bean) {
		bean.setOffsetGroupCd("OFFSET_GROUP_CD01");
	}
}
