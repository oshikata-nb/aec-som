/*
 * Created on 2009/01/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.balancedetail;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockBalanceDetailDaoクラス
 * @author t0011036
 */
public class MockBalanceDetailDao implements BalanceDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockBalanceDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public BalanceDetail getEntity(final String balanceCd) {
		if (Constants.TEST_PARAMETER_NODATA.equals(balanceCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(balanceCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		BalanceDetail bean = new BalanceDetail();

		/* BalanceDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * BalanceDetailを生成する
	 * @param bean bean
	 * @return BalanceDetail
	 */
	private void createBean(final BalanceDetail bean) {
		bean.setBalanceCd("BALANCE001");
		bean.setVenderCd("VENDER001");
	}

	/**
	 * 先付受注ヘッダ存在チェック
	 * @param balanceCd 帳合コード
	 * @return int 検索結果件数(0：マスターになし)
	 */
	public int getCountFrstOrderHead(final String balanceCd){
		return 0;
	}
}
