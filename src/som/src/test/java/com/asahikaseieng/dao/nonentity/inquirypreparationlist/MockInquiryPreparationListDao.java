/*
 * Created on 2009/04/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inquirypreparationlist;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockInquiryPreparationListDaoクラス
 * @author t0011036
 */
public class MockInquiryPreparationListDao implements InquiryPreparationListDao {

	/**
	 * コンストラクタ.
	 */
	public MockInquiryPreparationListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<InquiryPreparationList> getList(final Timestamp countDate,
			final String countDivision) {
		List<InquiryPreparationList> list = new ArrayList<InquiryPreparationList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(countDivision)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(countDivision)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* InquiryPreparationListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * InquiryPreparationListを生成する
	 * @param i インデックス
	 * @return InquiryPreparationList
	 */
	private InquiryPreparationList createBean(final int i) {
		InquiryPreparationList bean = new InquiryPreparationList();
		bean.setItemCd("ITEM_CD" + i);
		bean.setLocationCd("LOCATION_CD" + i);
		bean.setAliasLotNo("ALIAS_LOT_NO" + i);
		bean.setInventoryQty(new BigDecimal(i));
		return bean;
	}
}
