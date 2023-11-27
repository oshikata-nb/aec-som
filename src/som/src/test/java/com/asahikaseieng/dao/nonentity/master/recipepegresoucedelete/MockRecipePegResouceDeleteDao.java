/*
 * Created on 2009/05/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.recipepegresoucedelete;

import java.math.BigDecimal;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockRecipePegResouceDeleteDaoクラス
 * @author t0011036
 */
public class MockRecipePegResouceDeleteDao implements RecipePegResouceDeleteDao {

	/**
	 * コンストラクタ.
	 */
	public MockRecipePegResouceDeleteDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public int deleteList(
		final String resouceCd
	) {
		if (Constants.TEST_PARAMETER_NODATA.equals(resouceCd)) {
			return 0;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(resouceCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		RecipePegResouceDelete bean = new RecipePegResouceDelete();

		/* RecipePegResouceDeleteを生成する */
		createBean(bean);

		return 1;
	}

	/**
	 * RecipePegResouceDeleteを生成する
	 * @param bean bean
	 * @return RecipePegResouceDelete
	 */
	private void createBean(final RecipePegResouceDelete bean) {
		bean.setResouceCd("CD001");
		bean.setSeq(new BigDecimal("1"));
	}
}
