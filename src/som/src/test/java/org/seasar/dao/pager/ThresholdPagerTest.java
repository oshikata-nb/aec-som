package org.seasar.dao.pager;

import com.asahikaseieng.exception.LargeAmountDataRuntimeException;
import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * 閾値付きページャーのテストケース. (s2daoのソースを改造している為コーディング規約に沿っていません).
 * @author jbd
 */
public class ThresholdPagerTest extends AbstractS2DaoTestCase {

//	private JbdTestTableDao dao;

	/**
	 * コンストラクタ.
	 * 
	 * @param name name
	 */
	public ThresholdPagerTest(final String name) {
		super(name);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
	}

	/**
	 * @throws Exception 例外
	 */
	public void testPager1Tx() throws Exception {

		// 31件のデータ投入
		readXlsReplaceDb("ThresholdPagerTest_init.xls");

		for (int i = 0; i < 2; i++) {

			ThresholdPagerResultSetFactoryWrapper rsf = (ThresholdPagerResultSetFactoryWrapper) getComponent("resultSetFactory");
			rsf.setUseScrollCursor(i % 2 == 0);

			DefaultThresholdPagerCondition dtpc = new DefaultThresholdPagerCondition();

			dtpc.setThreshold(32);
			dtpc.setOffset(10);
			dtpc.setLimit(10);

//			List list = dao.getList(dtpc);
//			assertEquals(10, list.size());
			assertEquals(31, dtpc.getCount());
			assertFalse(dtpc.isOverThreshold());
		}
	}

	/**
	 * @throws Exception 例外
	 */
	public void testPager2Tx() throws Exception {

		// 31件のデータ投入
		readXlsReplaceDb("ThresholdPagerTest_init.xls");

		for (int i = 0; i < 2; i++) {

			ThresholdPagerResultSetFactoryWrapper rsf = (ThresholdPagerResultSetFactoryWrapper) getComponent("resultSetFactory");
			rsf.setUseScrollCursor(i % 2 == 0);

			DefaultThresholdPagerCondition dtpc = new DefaultThresholdPagerCondition();

			dtpc.setThreshold(30);
			dtpc.setOffset(10);
			dtpc.setLimit(10);

			try {
//				dao.getList(dtpc);
				fail("Should raise a " + LargeAmountDataRuntimeException.class);
			} catch (LargeAmountDataRuntimeException e) {
				;
			}
		}
	}

	/**
	 * @throws Exception 例外
	 */
	public void testPager3Tx() throws Exception {

		// 31件のデータ投入
		readXlsReplaceDb("ThresholdPagerTest_init.xls");

		for (int i = 0; i < 2; i++) {

			ThresholdPagerResultSetFactoryWrapper rsf = (ThresholdPagerResultSetFactoryWrapper) getComponent("resultSetFactory");
			rsf.setUseScrollCursor(i % 2 == 0);

			DefaultThresholdPagerCondition dtpc = new DefaultThresholdPagerCondition();

			dtpc.setThreshold(PagerCondition.NONE_LIMIT);
			dtpc.setOffset(10);
			dtpc.setLimit(10);

//			List list = dao.getList(dtpc);
//			assertEquals(10, list.size());
			assertEquals(31, dtpc.getCount());
			assertFalse(dtpc.isOverThreshold());
		}
	}
}
