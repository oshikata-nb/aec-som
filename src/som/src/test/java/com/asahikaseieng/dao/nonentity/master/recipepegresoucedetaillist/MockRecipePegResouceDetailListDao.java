/*
 * Created on 2009/05/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.recipepegresoucedetaillist;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockRecipePegResouceDetailListDaoクラス
 * @author t0011036
 */
public class MockRecipePegResouceDetailListDao implements
		RecipePegResouceDetailListDao {

	/**
	 * コンストラクタ.
	 */
	public MockRecipePegResouceDetailListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<RecipePegResouceDetailList> getList(final String resouceCd) {
		List<RecipePegResouceDetailList> list = new ArrayList<RecipePegResouceDetailList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(resouceCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(resouceCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* RecipeResouceListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * RecipePegResouceDetailListを生成する
	 * @param i インデックス
	 * @return RecipePegResouceDetailList
	 */
	private RecipePegResouceDetailList createBean(final int i) {
		RecipePegResouceDetailList bean = new RecipePegResouceDetailList();
		bean.setResouceCd("RESOURCE" + i);
		return bean;
	}
}
