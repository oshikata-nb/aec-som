/*
 * Created on 2009/01/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.venderlist;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockVenderListDaoクラス
 * @author kanri-user
 */
public class MockVenderListDao implements VenderListDao {

	/**
	 * コンストラクタ.
	 */
	public MockVenderListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<VenderList> getList(final VenderListPagerCondition condition) {
		List<VenderList> list = new ArrayList<VenderList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhVenderCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhVenderCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* VenderListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * VenderListを生成する
	 * @param i インデックス
	 * @return VenderList
	 */
	private VenderList createBean(final int i) {
		VenderList bean = new VenderList();
		bean.setVenderDivision("TS");
		bean.setVenderCd("VENDER" + i);
		bean.setVenderName1("AEC" + i);
		return bean;
	}

	/**
	 * {@inheritDoc}
	 */

	public VenderList checkVenderCd(final String venderDivision,
			final String venderCd) {
		// TODO 自動生成したメソッド・スタブ
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<VenderList> getSearchList(
			final VenderListPagerCondition condition) {
		// TODO 自動生成したメソッド・スタブ
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<VenderList> getSearchNameList(final String venderName) {
		// TODO 自動生成したメソッド・スタブ
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<VenderList> getVenderList() {
		// TODO 自動生成したメソッド・スタブ
		return null;
	}
}
