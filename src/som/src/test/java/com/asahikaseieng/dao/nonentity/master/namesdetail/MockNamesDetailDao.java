/*
 * Created on 2009/02/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.namesdetail;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockNamesDetailDaoクラス
 * @author t0011036
 */
public class MockNamesDetailDao implements NamesDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockNamesDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public NamesDetail getEntity(final String nameDivision, final String nameCd) {
		if (Constants.TEST_PARAMETER_NODATA.equals(nameCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(nameCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		NamesDetail bean = new NamesDetail();

		/* NamesDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * NamesDetailを生成する
	 * @param bean bean
	 * @return NamesDetail
	 */
	private void createBean(final NamesDetail bean) {
		bean.setNameDivision("DIVISION01");
		bean.setNameCd("NAME_CD01");
		bean.setName01("NAME01");
	}

	@Override
	public NamesDetail getTaxFreeRatio(String name01) {
		// TODO 自動生成したメソッド・スタブ
		return null;
	}
}
