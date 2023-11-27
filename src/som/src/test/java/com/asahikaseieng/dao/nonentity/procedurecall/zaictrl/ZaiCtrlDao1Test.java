/*
 * Created on Thu Jan 29 08:48:41 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.procedurecall.zaictrl;

import java.math.BigDecimal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.app.common.stockinout.StockinoutForDirection;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.test.AbstractS2DaoTestCase;

/**
 * SalesDaoクラスのテストケース
 * @author kanri-user
 */
public final class ZaiCtrlDao1Test extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(ZaiCtrlDao1Test.class);

	/** Daoオブジェクト */
	private ZaiCtrlDao dao;

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public ZaiCtrlDao1Test(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	/**
	 * 発注関連Ｄａｏ
	 */
	public void testEntryTx() {
		StockinoutForDirection inout = new StockinoutForDirection(dao);
		BigDecimal directionDivision = new BigDecimal(2);
		String directionNo = "H0906040001";
		String loginUser = "som";
		try {
			assertTrue(inout.inspectionDirection(directionDivision,
				directionNo, loginUser));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}

		// Stockinout inouts = new Stockinout(dao);
		// String itemCd = "00000083";
		// BigDecimal qty = new BigDecimal(100);
		// Timestamp inoutDate = AecDateUtils.getCurrentTimestamp();
		// String beforeLocationCd = "B10119000";
		// String afterLocationCd = "B10143000";
		// String lotNo = "P0905300001";
		// String remark = "";
		// String reason = "";
		//
		// String loginUser = "som";
		// try {
		// assertTrue(inouts.deSalesWithoutDelivery(itemCd, qty, inoutDate,
		// beforeLocationCd, afterLocationCd, lotNo, remark, reason,
		// loginUser));
		// } catch (LogicExceptionEx e) {
		// log.debug(e.getMessage());
		// assertTrue(false);
		// }
		// BigDecimal qty1 = new BigDecimal(100);
		// try {
		// assertTrue(inouts.deSalesWithoutDelivery(itemCd, qty1, inoutDate,
		// beforeLocationCd, afterLocationCd, lotNo, remark, reason,
		// loginUser));
		// } catch (LogicExceptionEx e) {
		// log.debug(e.getMessage());
		// assertTrue(false);
		// }

	}
}
