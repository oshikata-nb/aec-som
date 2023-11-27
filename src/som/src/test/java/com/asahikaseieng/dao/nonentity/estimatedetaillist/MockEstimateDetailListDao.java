/*
 * Created on 2009/03/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.estimatedetaillist;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockEstimateDetailListDaoクラス
 * @author t0011036
 */
public class MockEstimateDetailListDao implements EstimateDetailListDao {

	/**
	 * コンストラクタ.
	 */
	public MockEstimateDetailListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<EstimateDetailList> getList(final String estimateNo) {
		List<EstimateDetailList> list = new ArrayList<EstimateDetailList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(estimateNo)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(estimateNo)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* EstimateDetailListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * EstimateDetailListを生成する
	 * @param i インデックス
	 * @return EstimateDetailList
	 */
	private EstimateDetailList createBean(final int i) {
		EstimateDetailList bean = new EstimateDetailList();
		bean.setBalanceCd("BALANCE_CD" + i);
		bean.setEstimateNo("ESTIMATE_NO" + i);
		bean.setItemCd("ITEM_CD" + i);
		bean.setItemName("ITEM_NAME" + i);
		bean.setVenderCd("VENDER_CD" + i);
		return bean;
	}
}
