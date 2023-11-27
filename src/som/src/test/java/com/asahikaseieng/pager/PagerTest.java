/*
 * Created on 2007/04/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.pager;

import javax.servlet.http.HttpServletRequest;

import junit.framework.TestCase;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;
import org.seasar.dao.pager.PagerCondition;
import org.seasar.dao.pager.ThresholdPagerCondition;
import org.seasar.framework.aop.interceptors.MockInterceptor;

/**
 * Pagerのテストケース.
 * @author jbd
 */
public class PagerTest extends TestCase {

	/**
	 * コンストラクタ.
	 * @param testname テスト名
	 */
	public PagerTest(final String testname) {
		super(testname);
	}

	private Pager target;

	private ThresholdPagerCondition getPagerCondition() {
		return target.getPagerCondition();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		target = new Pager(new DefaultThresholdPagerCondition());
	}

	/**
	 * resetのテストケース.
	 */
	public void testReset() {
		getPagerCondition().setCount(99);
		getPagerCondition().setOffset(99);
		getPagerCondition().setOverThreshold(true);

		assertEquals(99, target.getCount());

		target.reset();

		assertEquals(0, getPagerCondition().getCount());
		assertEquals(0, getPagerCondition().getOffset());
		assertFalse(getPagerCondition().isOverThreshold());
	}

	/**
	 * getPageのテストケース.
	 */
	public void testGetPage() {
		MockInterceptor mi = new MockInterceptor();

		// 空の場合 -> 1
		mi.setReturnValue("getParameter", "");
		HttpServletRequest request = (HttpServletRequest) mi
				.createProxy(HttpServletRequest.class);

		assertEquals(1, target.getPage(request));

		// ページ番号として不正 -> 1
		mi.setReturnValue("getParameter", "a");
		request = (HttpServletRequest) mi.createProxy(HttpServletRequest.class);

		assertEquals(1, target.getPage(request));

		// 正常時
		mi.setReturnValue("getParameter", "2");
		request = (HttpServletRequest) mi.createProxy(HttpServletRequest.class);

		assertEquals(2, target.getPage(request));
	}

	/**
	 * updateOffsetのテストケース.
	 */
	public void testUpdateOffset() {
		MockInterceptor mi = new MockInterceptor();

		// page -> 0 ==> offset -> 0
		mi.setReturnValue("getParameter", "0");
		HttpServletRequest request = (HttpServletRequest) mi
				.createProxy(HttpServletRequest.class);

		getPagerCondition().setLimit(10);
		target.updateOffset(request);
		assertEquals(0, getPagerCondition().getOffset());

		// limit -> 0==> offset -> 0
		mi.setReturnValue("getParameter", "10");
		request = (HttpServletRequest) mi.createProxy(HttpServletRequest.class);

		getPagerCondition().setLimit(0);
		target.updateOffset(request);
		assertEquals(0, getPagerCondition().getOffset());

		// 通常
		mi.setReturnValue("getParameter", "5");
		request = (HttpServletRequest) mi.createProxy(HttpServletRequest.class);

		getPagerCondition().setLimit(10);

		target.updateOffset(request);
		assertEquals(40, getPagerCondition().getOffset());

		// ついでにテスト
		assertEquals(10, target.getLimit());
		assertEquals(40, target.getCurrentFirstOffset());
		assertEquals(41, target.getCurrentFirstNo());
	}

	/**
	 * isPrev, isNextのテストケース.
	 */
	public void testIsPrevIsNext() {
		getPagerCondition().setLimit(PagerCondition.NONE_LIMIT);
		assertFalse(target.isNext());
		assertFalse(target.isPrev());

		getPagerCondition().setLimit(10);

		getPagerCondition().setOffset(0);
		assertFalse(target.isPrev());

		getPagerCondition().setOffset(10);
		assertTrue(target.isPrev());

		getPagerCondition().setCount(0);
		assertFalse(target.isNext());

		getPagerCondition().setCount(10);
		getPagerCondition().setOffset(0);
		assertFalse(target.isNext());

		getPagerCondition().setCount(11);
		assertTrue(target.isNext());
	}

	/**
	 * offset2pageのテストケース.
	 */
	public void testOffset2page() {

		getPagerCondition().setLimit(0);
		assertEquals(1, target.offset2page(1));

		getPagerCondition().setLimit(10);
		getPagerCondition().setCount(10);
		assertEquals(1, target.offset2page(10));

		getPagerCondition().setLimit(5);
		getPagerCondition().setCount(10);
		assertEquals(2, target.offset2page(5));

		getPagerCondition().setOffset(5);
		assertEquals(2, target.getCurrentPage());
	}

	/**
	 * getLastPageのテストケース.
	 */
	public void testGetLastPage() {
		getPagerCondition().setCount(0);
		assertEquals(1, target.getLastPage());

		getPagerCondition().setCount(10);
		getPagerCondition().setLimit(0);
		assertEquals(1, target.getLastPage());

		getPagerCondition().setLimit(5);
		assertEquals(2, target.getLastPage());

		getPagerCondition().setCount(11);
		getPagerCondition().setLimit(5);
		assertEquals(3, target.getLastPage());
	}

	/**
	 * prevNextOffsetのテストケース.
	 */
	public void testPrevNextOffset() {

		getPagerCondition().setLimit(PagerCondition.NONE_LIMIT);
		assertEquals(0, target.getPrevOffset());
		getPagerCondition().setOffset(10);
		assertEquals(10, target.getNextOffset());

		getPagerCondition().setLimit(10);
		getPagerCondition().setOffset(5);
		assertEquals(0, target.getPrevOffset());

		getPagerCondition().setOffset(20);
		assertEquals(10, target.getPrevOffset());

		getPagerCondition().setCount(30);
		assertEquals(2, target.getPrevPage());

		assertEquals(30, target.getNextOffset());
		assertEquals(1, target.getNextPage());
	}

	/**
	 * getCurrentLastOffsetのテストケース.
	 */
	public void testGetCurrentLastOffset() {
		getPagerCondition().setLimit(PagerCondition.NONE_LIMIT);
		getPagerCondition().setCount(10);

		assertEquals(9, target.getCurrentLastOffset());

		getPagerCondition().setLimit(10);
		getPagerCondition().setOffset(10);

		assertEquals(9, target.getCurrentLastOffset());

		getPagerCondition().setLimit(20);
		getPagerCondition().setCount(100);
		assertEquals(29, target.getCurrentLastOffset());

		assertEquals(30, target.getCurrentLastNo());
		assertEquals(20, target.getCurrentCount());
	}
}
