/*
 * Created on 2009/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.offsetgroupdetail;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockOffsetGroupDetailDaoクラス
 * @author t0011036
 */
public class MockOffsetGroupDetailDao implements OffsetGroupDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockOffsetGroupDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<OffsetGroupDetail> getEntity(final String offsetGroupCd) {
		List<OffsetGroupDetail> list = new ArrayList<OffsetGroupDetail>();

		if (Constants.TEST_PARAMETER_NODATA.equals(offsetGroupCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(offsetGroupCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* OffsetGroupDetailを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * OffsetGroupDetailを生成する
	 * @param i インデックス
	 * @return OffsetGroupDetail
	 */
	private OffsetGroupDetail createBean(final int i) {
		OffsetGroupDetail bean = new OffsetGroupDetail();
		bean.setOffsetGroupCd("OFFSET_GROUP_CD" + i);
		bean.setOffsetGroupName("OFFSET_GROUP_NAME" + i);
		bean.setVenderCd("VENDER_CD" + i);
		bean.setVenderDivision("TS");
		bean.setVenderName1("VENDER_NAME" + i);
		return bean;
	}
}
