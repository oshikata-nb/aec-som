/*
 * Created on 2009/03/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.salestermsdetaillist;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockSalesTermsDetailListDaoクラス
 * @author t0011036
 */
public class MockSalesTermsDetailListDao implements SalesTermsDetailListDao {

	/**
	 * コンストラクタ.
	 */
	public MockSalesTermsDetailListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<SalesTermsDetailList> getList(final String deliveryCd,
			final String balanceCd) {
		List<SalesTermsDetailList> list = new ArrayList<SalesTermsDetailList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(deliveryCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(balanceCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(deliveryCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(balanceCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* SalesTermsDetailListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * SalesTermsDetailListを生成する
	 * @param i インデックス
	 * @return SalesTermsDetailList
	 */
	private SalesTermsDetailList createBean(final int i) {
		SalesTermsDetailList bean = new SalesTermsDetailList();
		bean.setBalanceCd("BALANCE_CD" + i);
		bean.setBalanceType(new BigDecimal(i));
		bean.setBalanceTypeName("BALANCE_TYPE_NAME" + i);
		bean.setDeliveryCd("DELIVERY_CD" + i);
		bean.setDeliveryName1("DELIVERY_NAME" + i);
		bean.setItemCd("ITEM_CD" + i);
		bean.setItemName("ITEM_NAME" + i);
		bean.setOtherCompanyCd1("OTHER_COMPANY_CD" + i);
		bean.setStyleOfPacking("STYLE_OF_PACKING" + i);
		bean.setTantoCd("TANTO_CD" + i);
		bean.setTantoNm("TANTO_NM" + i);
		bean.setVenderCd("VENDER_CD" + i);
		return bean;
	}
}
