/*
 * Created on 2007/11/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.develop;

import java.util.ArrayList;
import java.util.List;

/**
 * MockAecDevelopListDaoクラス
 * @author zen
 */
public class MockAecDevelopListDao implements DevelopListDao {

	/**
	 * コンストラクタ.
	 */
	public MockAecDevelopListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<DevelopList> getList(final DevelopListPagerCondition condition
	) {
		List<DevelopList> list = new ArrayList<DevelopList>();
		//TODO 実装
		return list;
	}
	/**
	 * {@inheritDoc}
	 */
	public List<DevelopList> getListExcelReport(final DevelopListPagerCondition condition) {
		List<DevelopList> list = new ArrayList<DevelopList>();
		//TODO 実装
		return list;
	}
}

