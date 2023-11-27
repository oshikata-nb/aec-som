/*
 * Created on Thu Jan 29 08:48:41 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.procedurecall.zaictrl;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.app.common.stockinout.Stockinout;
import com.asahikaseieng.app.common.stockinout.StockinoutForDirection;
import com.asahikaseieng.app.common.stockinout.StockinoutForOrder;
import com.asahikaseieng.app.common.stockinout.StockinoutForPurchase;
import com.asahikaseieng.app.common.stockinout.StockinoutForShipping;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.test.AbstractS2DaoTestCase;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * SalesDaoクラスのテストケース
 * @author kanri-user
 */
public final class ZaiCtrlDaoTest extends AbstractS2DaoTestCase {

	/** Log */
	private static Log log = LogFactory.getLog(ZaiCtrlDaoTest.class);

	/** Daoオブジェクト */
	private ZaiCtrlDao dao;

	/**
	 * コンストラクター.
	 * @param testname テスト名
	 */
	public ZaiCtrlDaoTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		// 独自の初期化処理が必要なら実装して下さい。
	}

	// TODO testEntryOrder
	/**
	 * 受注入力（全）
	 */
	// 有りあえないケース
	// 有りあえないケース
	// TODO 成功するケースのテスト
	// TODO 受注番号が存在しないケース
	// TODO パラメータ不足のケース
	// TODO testCancelOrder
	// TODO completeOrder
	// TODO deCompleteOrder
	// TODO testEntryPurchase
	/**
	 * 発注関連Ｄａｏ
	 */
	public void testEntryPurchaseTx() {
		StrCodeDto dto = new StrCodeDto();

		// 有り得ないケース
		dto.setLngFlg(new BigDecimal(1));
		dto.setStrCode("ARIENAI");
		dto.setStrLoginUser("som");
		dto.setOutPara("");

		dao.entryPurchase(dto);
		log.debug(dto.getOutPara());
		if (dto.getOutPara().equals("COMPLETE")) {
			assertTrue(false);
		} else {
			assertTrue(true);
		}
		dto.setLngFlg(new BigDecimal(5));
		dto.setStrCode("37");
		dto.setStrLoginUser("som");
		dto.setOutPara("");

		dao.entryPurchase(dto);
		log.debug(dto.getOutPara());
		if (dto.getOutPara().equals("COMPLETE")) {
			assertTrue(false);
		} else {
			assertTrue(true);
		}
		// 登録
		dto.setLngFlg(new BigDecimal(1));
		dto.setStrLoginUser("som");
		dto.setOutPara("");
		dto.setStrCode("37");
		dao.entryPurchase(dto);
		log.debug(dto.getOutPara());
		assertEquals(dto.getOutPara(), "COMPLETE");

		// 削除
		dto.setLngFlg(new BigDecimal(2));
		dto.setStrLoginUser("som");
		dto.setOutPara("");
		dto.setStrCode("37");
		dao.entryPurchase(dto);
		log.debug(dto.getOutPara());
		assertEquals(dto.getOutPara(), "COMPLETE");

		// 登録
		dto.setLngFlg(new BigDecimal(1));
		dto.setStrLoginUser("som");
		dto.setOutPara("");
		dto.setStrCode("37");
		dao.entryPurchase(dto);
		log.debug(dto.getOutPara());
		assertEquals(dto.getOutPara(), "COMPLETE");

		// 伝票発行
		dto.setLngFlg(new BigDecimal(3));
		dto.setStrLoginUser("som");
		dto.setOutPara("");
		dto.setStrCode("37");
		dao.entryPurchase(dto);
		log.debug(dto.getOutPara());
		assertEquals(dto.getOutPara(), "COMPLETE");

		// 伝票発行取消し
		dto.setLngFlg(new BigDecimal(4));
		dto.setStrLoginUser("som");
		dto.setOutPara("");
		dto.setStrCode("37");
		dao.entryPurchase(dto);
		log.debug(dto.getOutPara());
		assertEquals(dto.getOutPara(), "COMPLETE");

		// 伝票発行
		dto.setLngFlg(new BigDecimal(3));
		dto.setStrLoginUser("som");
		dto.setOutPara("");
		dto.setStrCode("37");
		dao.entryPurchase(dto);
		log.debug(dto.getOutPara());
		assertEquals(dto.getOutPara(), "COMPLETE");

		// 有り得ないケース
		dto.setLngFlg(new BigDecimal(1));
		dto.setStrCode("ARIENAI");
		dto.setStrLoginUser("som");
		dto.setOutPara("");

		dao.receivePurchase(dto);
		log.debug(dto.getOutPara());
		if (dto.getOutPara().equals("COMPLETE")) {
			assertTrue(false);
		} else {
			assertTrue(true);
		}
		dto.setLngFlg(new BigDecimal(3));
		dto.setStrCode("37");
		dto.setStrLoginUser("som");
		dto.setOutPara("");
		dao.receivePurchase(dto);
		log.debug(dto.getOutPara());
		if (dto.getOutPara().equals("COMPLETE")) {
			assertTrue(false);
		} else {
			assertTrue(true);
		}
		// 受入
		dto.setLngFlg(new BigDecimal(1));
		dto.setStrLoginUser("som");
		dto.setOutPara("");
		dto.setStrCode("37");
		dao.receivePurchase(dto);
		log.debug(dto.getOutPara());
		assertEquals(dto.getOutPara(), "COMPLETE");

		// 受入取消
		dto.setLngFlg(new BigDecimal(2));
		dto.setStrLoginUser("som");
		dto.setOutPara("");
		dto.setStrCode("37");
		dao.receivePurchase(dto);
		log.debug(dto.getOutPara());
		assertEquals(dto.getOutPara(), "COMPLETE");

		// 受入
		dto.setLngFlg(new BigDecimal(1));
		dto.setStrLoginUser("som");
		dto.setOutPara("");
		dto.setStrCode("37");
		dao.receivePurchase(dto);
		log.debug(dto.getOutPara());
		assertEquals(dto.getOutPara(), "COMPLETE");

		// 完了
		dto.setLngFlg(new BigDecimal(5));
		dto.setStrLoginUser("som");
		dto.setOutPara("");
		dto.setStrCode("TESTDATA000");
		dao.completePurchase(dto);
		log.debug(dto.getOutPara());
		assertEquals(dto.getOutPara(), "COMPLETE");
	}

	/**
	 * 発注関連
	 * 
	 */
	public void testPurchaseTx() {
		StockinoutForPurchase inout = new StockinoutForPurchase(dao);
		try {
			assertFalse(inout.entryPurchase("ARIENAI", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(true);
		}
		try {
			assertTrue(inout.entryPurchase("37", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertFalse(inout.canselPurchase("ARIENAI", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(true);
		}
		try {
			assertTrue(inout.canselPurchase("37", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertTrue(inout.entryPurchase("37", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertFalse(inout.fixPurchase("ARIENAI", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(true);
		}
		try {
			assertTrue(inout.fixPurchase("37", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertFalse(inout.defixPurchase("ARIENAI", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(true);
		}
		try {
			assertTrue(inout.defixPurchase("37", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertTrue(inout.fixPurchase("37", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertFalse(inout.receivePurchase("ARIENAI", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(true);
		}
		try {
			assertTrue(inout.receivePurchase("37", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertFalse(inout.dereceivePurchase("ARIENAI", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(true);
		}
		try {
			assertTrue(inout.dereceivePurchase("37", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertTrue(inout.receivePurchase("37", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertFalse(inout.completePurchase("37", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(true);
		}
		try {
			assertTrue(inout.completePurchase("TESTDATA000", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
	}

	/**
	 * 製造関連Dao
	 */
	public void testDirectionDaoTx() {
		StrCodeAndNumberDto dto = new StrCodeAndNumberDto();
		// ありえないケース
		dto.setLngFlg(new BigDecimal(1));
		dto.setLngRowNo(new BigDecimal(1));
		dto.setStrCode("ARIENAI");
		dto.setStrLoginUser("som");
		dto.setOutPara("");

		dao.entryDirection(dto);
		log.debug(dto.getOutPara());
		if (dto.getOutPara().equals("COMPLETE")) {
			assertTrue(false);
		} else {
			assertTrue(true);
		}
		dto.setOutPara("");
		dao.resultDirection(dto);
		log.debug(dto.getOutPara());
		if (dto.getOutPara().equals("COMPLETE")) {
			assertTrue(false);
		} else {
			assertTrue(true);
		}
		dto.setLngFlg(new BigDecimal(5));
		dto.setStrCode("TESTDIRECTION001");
		dto.setLngRowNo(new BigDecimal(1));
		dto.setStrLoginUser("som");
		dto.setOutPara("");

		dao.entryDirection(dto);
		log.debug(dto.getOutPara());
		if (dto.getOutPara().equals("COMPLETE")) {
			assertTrue(false);
		} else {
			assertTrue(true);
		}
		dto.setLngFlg(new BigDecimal(3));
		dto.setOutPara("");
		dao.resultDirection(dto);
		log.debug(dto.getOutPara());
		if (dto.getOutPara().equals("COMPLETE")) {
			assertTrue(false);
		} else {
			assertTrue(true);
		}
		// 登録
		dto.setLngFlg(new BigDecimal(1));
		dto.setStrCode("TESTDIRECTION001");
		dto.setLngRowNo(new BigDecimal(1));
		dto.setStrLoginUser("som");
		dto.setOutPara("");
		dao.entryDirection(dto);
		log.debug(dto.getOutPara());
		assertEquals(dto.getOutPara(), "COMPLETE");

		// 削除
		dto.setLngFlg(new BigDecimal(2));
		dto.setOutPara("");
		dao.entryDirection(dto);
		log.debug(dto.getOutPara());
		assertEquals(dto.getOutPara(), "COMPLETE");

		// 登録
		dto.setLngFlg(new BigDecimal(1));
		dto.setOutPara("");
		dao.entryDirection(dto);
		log.debug(dto.getOutPara());
		assertEquals(dto.getOutPara(), "COMPLETE");

		// 確定
		dto.setLngFlg(new BigDecimal(3));
		dto.setOutPara("");
		dao.entryDirection(dto);
		log.debug(dto.getOutPara());
		assertEquals(dto.getOutPara(), "COMPLETE");

		// 確定取消
		dto.setLngFlg(new BigDecimal(4));
		dto.setOutPara("");
		dao.entryDirection(dto);
		log.debug(dto.getOutPara());
		assertEquals(dto.getOutPara(), "COMPLETE");

		// 確定
		dto.setLngFlg(new BigDecimal(3));
		dto.setOutPara("");
		dao.entryDirection(dto);
		log.debug(dto.getOutPara());
		assertEquals(dto.getOutPara(), "COMPLETE");

		// 登録
		dto.setLngFlg(new BigDecimal(1));
		dto.setOutPara("");
		dao.resultDirection(dto);
		log.debug(dto.getOutPara());
		assertEquals(dto.getOutPara(), "COMPLETE");

		// 削除
		dto.setLngFlg(new BigDecimal(2));
		dto.setOutPara("");
		dao.resultDirection(dto);
		log.debug(dto.getOutPara());
		assertEquals(dto.getOutPara(), "COMPLETE");

		// 登録
		dto.setLngFlg(new BigDecimal(1));
		dto.setOutPara("");
		dao.resultDirection(dto);
		log.debug(dto.getOutPara());
		assertEquals(dto.getOutPara(), "COMPLETE");

		// 完了
		dto.setLngFlg(new BigDecimal(5));
		dto.setOutPara("");
		dao.resultDirection(dto);
		log.debug(dto.getOutPara());
		assertEquals(dto.getOutPara(), "COMPLETE");

		// 登録
		dto.setLngFlg(new BigDecimal(1));
		dto.setStrCode("TESTDIRECTION002");
		dto.setLngRowNo(new BigDecimal(2));
		dto.setStrLoginUser("som");
		dto.setOutPara("");
		dao.entryDirection(dto);
		log.debug(dto.getOutPara());
		assertEquals(dto.getOutPara(), "COMPLETE");

		// 確定
		dto.setLngFlg(new BigDecimal(3));
		dto.setOutPara("");
		dao.entryDirection(dto);
		log.debug(dto.getOutPara());
		assertEquals(dto.getOutPara(), "COMPLETE");

		// 登録
		dto.setLngFlg(new BigDecimal(1));
		dto.setOutPara("");
		dao.resultDirection(dto);
		log.debug(dto.getOutPara());
		assertEquals(dto.getOutPara(), "COMPLETE");

		// 検査待
		dto.setLngFlg(new BigDecimal(3));
		dto.setOutPara("");
		dao.resultDirection(dto);
		log.debug(dto.getOutPara());
		assertEquals(dto.getOutPara(), "COMPLETE");

		// 検査待取消し
		dto.setLngFlg(new BigDecimal(4));
		dto.setOutPara("");
		dao.resultDirection(dto);
		log.debug(dto.getOutPara());
		assertEquals(dto.getOutPara(), "COMPLETE");

		// 検査待
		dto.setLngFlg(new BigDecimal(3));
		dto.setOutPara("");
		dao.resultDirection(dto);
		log.debug(dto.getOutPara());
		assertEquals(dto.getOutPara(), "COMPLETE");

		// 格付け
		dto.setLngFlg(new BigDecimal(6));
		dto.setOutPara("");
		dao.resultDirection(dto);
		log.debug(dto.getOutPara());
		assertEquals(dto.getOutPara(), "COMPLETE");

		// 格付け取消し
		dto.setLngFlg(new BigDecimal(7));
		dto.setOutPara("");
		dao.resultDirection(dto);
		log.debug(dto.getOutPara());
		assertEquals(dto.getOutPara(), "COMPLETE");

		// 格付け
		dto.setLngFlg(new BigDecimal(6));
		dto.setOutPara("");
		dao.resultDirection(dto);
		log.debug(dto.getOutPara());
		assertEquals(dto.getOutPara(), "COMPLETE");

		// 完了
		dto.setLngFlg(new BigDecimal(5));
		dto.setOutPara("");
		dao.resultDirection(dto);
		log.debug(dto.getOutPara());
		assertEquals(dto.getOutPara(), "COMPLETE");

	}

	/**
	 * 製造関連Dao
	 */
	public void testFormulaDaoTx() {
		StrCodeAnd3NumberDto dto = new StrCodeAnd3NumberDto();
		// ありえないケース
		dto.setLngFlg(new BigDecimal(1));
		dto.setLngRowNo(new BigDecimal(1));
		dto.setLngStepNo(null);
		dto.setLngLineNo(null);
		dto.setStrCode("ARIENAI");
		dto.setStrLoginUser("som");
		dto.setOutPara("");

		dao.entryFormula(dto);
		log.debug(dto.getOutPara());
		if (dto.getOutPara().equals("COMPLETE")) {
			assertTrue(false);
		} else {
			assertTrue(true);
		}
		dto.setOutPara("");
		dao.resultFormula(dto);
		log.debug(dto.getOutPara());
		if (dto.getOutPara().equals("COMPLETE")) {
			assertTrue(false);
		} else {
			assertTrue(true);
		}
		dto.setLngFlg(new BigDecimal(5));
		dto.setStrCode("TESTDIRECTION001");
		dto.setLngRowNo(new BigDecimal(1));
		dto.setLngStepNo(null);
		dto.setLngLineNo(null);
		dto.setStrLoginUser("som");
		dto.setOutPara("");

		dao.entryFormula(dto);
		log.debug(dto.getOutPara());
		if (dto.getOutPara().equals("COMPLETE")) {
			assertTrue(false);
		} else {
			assertTrue(true);
		}
		dto.setLngFlg(new BigDecimal(3));
		dto.setOutPara("");
		dao.resultFormula(dto);
		log.debug(dto.getOutPara());
		if (dto.getOutPara().equals("COMPLETE")) {
			assertTrue(false);
		} else {
			assertTrue(true);
		}
		// 登録
		dto.setLngFlg(new BigDecimal(1));
		dto.setStrCode("TESTDIRECTION001");
		dto.setLngRowNo(new BigDecimal(1));
		dto.setLngStepNo(null);
		dto.setLngLineNo(null);
		dto.setStrLoginUser("som");
		dto.setOutPara("");
		dao.entryFormula(dto);
		log.debug(dto.getOutPara());
		assertEquals(dto.getOutPara(), "COMPLETE");

		// 削除
		dto.setLngFlg(new BigDecimal(2));
		dto.setOutPara("");
		dao.entryFormula(dto);
		log.debug(dto.getOutPara());
		assertEquals(dto.getOutPara(), "COMPLETE");

		// 登録
		dto.setLngFlg(new BigDecimal(1));
		dto.setOutPara("");
		dao.entryFormula(dto);
		log.debug(dto.getOutPara());
		assertEquals(dto.getOutPara(), "COMPLETE");

		// 確定
		dto.setLngFlg(new BigDecimal(3));
		dto.setOutPara("");
		dao.entryFormula(dto);
		log.debug(dto.getOutPara());
		// assertEquals(dto.getOutPara(), "COMPLETE");

		// 確定取消
		dto.setLngFlg(new BigDecimal(4));
		dto.setOutPara("");
		dao.entryFormula(dto);
		log.debug(dto.getOutPara());
		assertEquals(dto.getOutPara(), "COMPLETE");

		// 確定
		dto.setLngFlg(new BigDecimal(3));
		dto.setOutPara("");
		dao.entryFormula(dto);
		log.debug(dto.getOutPara());
		// assertEquals(dto.getOutPara(), "COMPLETE");

		// 登録
		dto.setLngFlg(new BigDecimal(1));
		dto.setOutPara("");
		dao.resultFormula(dto);
		log.debug(dto.getOutPara());
		assertEquals(dto.getOutPara(), "COMPLETE");

		// 削除
		dto.setLngFlg(new BigDecimal(2));
		dto.setOutPara("");
		dao.resultFormula(dto);
		log.debug(dto.getOutPara());
		assertEquals(dto.getOutPara(), "COMPLETE");

		// 登録
		dto.setLngFlg(new BigDecimal(1));
		dto.setOutPara("");
		dao.resultFormula(dto);
		log.debug(dto.getOutPara());
		assertEquals(dto.getOutPara(), "COMPLETE");

		// 完了
		dto.setLngFlg(new BigDecimal(5));
		dto.setOutPara("");
		dao.resultFormula(dto);
		log.debug(dto.getOutPara());
		assertEquals(dto.getOutPara(), "COMPLETE");

	}

	/**
	 * 外注投入実績
	 */
	public void testInjectionTx() {
		StrCodeAnd3NumberDto dto = new StrCodeAnd3NumberDto();
		dto.setLngFlg(new BigDecimal(1));
		dto.setLngRowNo(new BigDecimal(2249));
		dto.setLngStepNo(new BigDecimal(6));
		dto.setLngLineNo(new BigDecimal(1));
		dto.setStrCode("HT000001060");
		dto.setStrLoginUser("som");
		dto.setOutPara("");
		dao.resultInjection(dto);
		log.debug(dto.getOutPara());
		assertEquals(dto.getOutPara(), "COMPLETE");

		dto.setLngFlg(new BigDecimal(2));
		dto.setOutPara("");
		dao.resultInjection(dto);
		log.debug(dto.getOutPara());
		assertEquals(dto.getOutPara(), "COMPLETE");

		StockinoutForDirection inout = new StockinoutForDirection(dao);
		// try {
		// assertTrue(inout.resultInjection(new BigDecimal(2249),
		// "HT000001060", "som"));
		// } catch (LogicExceptionEx e) {
		// log.debug(e.getMessage());
		// assertTrue(false);
		// }
		// try {
		// assertTrue(inout.deResultInjection(new BigDecimal(2249),
		// "HT000001060", "som"));
		// } catch (LogicExceptionEx e) {
		// log.debug(e.getMessage());
		// assertTrue(true);
		// }
		try {
			assertTrue(inout.resultInjection(new BigDecimal(6), new BigDecimal(
					1), new BigDecimal(2249), "HT000001060", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(true);
		}
		try {
			assertTrue(inout.deResultInjection(new BigDecimal(6),
				new BigDecimal(1), new BigDecimal(2249), "HT000001060", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(true);
		}
	}

	/**
	 * 製造指図
	 * 
	 */
	public void textDirectionTx() {
		StockinoutForDirection inout = new StockinoutForDirection(dao);
		try {
			assertFalse(inout.entryDirection(new BigDecimal(1), "ARIENAI",
				"som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(true);
		}
		try {
			assertTrue(inout.entryDirection(new BigDecimal(1),
				"TESTDIRECTION001", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertFalse(inout.cancelDirection(new BigDecimal(1), "ARIENAI",
				"som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(true);
		}
		try {
			assertTrue(inout.cancelDirection(new BigDecimal(1),
				"TESTDIRECTION001", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertTrue(inout.entryDirection(new BigDecimal(1),
				"TESTDIRECTION001", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertFalse(inout.fixDirection(new BigDecimal(1), "ARIENAI", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(true);
		}
		try {
			assertTrue(inout.fixDirection(new BigDecimal(1),
				"TESTDIRECTION001", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertFalse(inout.deFixDirection(new BigDecimal(1), "ARIENAI",
				"som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(true);
		}
		try {
			assertTrue(inout.deFixDirection(new BigDecimal(1),
				"TESTDIRECTION001", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertTrue(inout.fixDirection(new BigDecimal(1),
				"TESTDIRECTION001", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertFalse(inout.finishDirection(new BigDecimal(1), "ARIENAI",
				"som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(true);
		}
		try {
			assertTrue(inout.finishDirection(new BigDecimal(1),
				"TESTDIRECTION001", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertFalse(inout.deFinishDirection(new BigDecimal(1), "ARIENAI",
				"som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(true);
		}
		try {
			assertTrue(inout.deFinishDirection(new BigDecimal(1),
				"TESTDIRECTION001", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertTrue(inout.finishDirection(new BigDecimal(1),
				"TESTDIRECTION001", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertFalse(inout.inspectionDirection(new BigDecimal(1),
				"TESTDIRECTION001", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(true);
		}
		try {
			assertFalse(inout.gradeDirection(new BigDecimal(1),
				"TESTDIRECTION001", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(true);
		}
		try {
			assertFalse(inout.completeDirection(new BigDecimal(1), "ARIENAI",
				"som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(true);
		}
		try {
			assertTrue(inout.completeDirection(new BigDecimal(1),
				"TESTDIRECTION001", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertTrue(inout.entryDirection(new BigDecimal(2),
				"TESTDIRECTION002", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertTrue(inout.fixDirection(new BigDecimal(2),
				"TESTDIRECTION002", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertTrue(inout.finishDirection(new BigDecimal(2),
				"TESTDIRECTION002", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertTrue(inout.deFinishDirection(new BigDecimal(2),
				"TESTDIRECTION002", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertTrue(inout.finishDirection(new BigDecimal(2),
				"TESTDIRECTION002", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertFalse(inout.inspectionDirection(new BigDecimal(1), "ARIENAI",
				"som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(true);
		}
		try {
			assertTrue(inout.inspectionDirection(new BigDecimal(2),
				"TESTDIRECTION002", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertFalse(inout.gradeDirection(new BigDecimal(2), "ARIENAI",
				"som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(true);
		}
		try {
			assertTrue(inout.gradeDirection(new BigDecimal(2),
				"TESTDIRECTION002", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}

	}

	/**
	 * 製造指図
	 * 
	 */
	public void textFormulaTx() {
		StockinoutForDirection inout = new StockinoutForDirection(dao);
		try {
			assertFalse(inout.entryFormula(new BigDecimal(901), new BigDecimal(
					11), new BigDecimal(1), "ARIENAI", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(true);
		}
		try {
			assertFalse(inout.entryFormula(new BigDecimal(901), new BigDecimal(
					11), new BigDecimal(1), "TESTDIRECTION001", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertFalse(inout.cancelFormula(new BigDecimal(901),
				new BigDecimal(11), new BigDecimal(1), "ARIENAI", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(true);
		}
		try {
			assertFalse(inout.cancelFormula(new BigDecimal(901),
				new BigDecimal(11), new BigDecimal(1), "TESTDIRECTION001",
				"som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertFalse(inout.entryFormula(new BigDecimal(1), "ARIENAI", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(true);
		}
		try {
			assertFalse(inout.entryFormula(new BigDecimal(1),
				"TESTDIRECTION001", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertFalse(inout
					.cancelFormula(new BigDecimal(1), "ARIENAI", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(true);
		}
		try {
			assertFalse(inout.cancelFormula(new BigDecimal(1),
				"TESTDIRECTION001", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertFalse(inout.entryFormula(new BigDecimal(1),
				"TESTDIRECTION001", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}

		try {
			assertFalse(inout.fixFormula(new BigDecimal(901),
				new BigDecimal(11), new BigDecimal(1), "ARIENAI", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(true);
		}
		try {
			assertFalse(inout.fixFormula(new BigDecimal(901),
				new BigDecimal(11), new BigDecimal(1), "TESTDIRECTION001",
				"som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertFalse(inout.deFixFormula(new BigDecimal(901), new BigDecimal(
					11), new BigDecimal(1), "ARIENAI", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(true);
		}
		try {
			assertFalse(inout.deFixFormula(new BigDecimal(901), new BigDecimal(
					11), new BigDecimal(1), "TESTDIRECTION001", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertFalse(inout.fixFormula(new BigDecimal(1), "ARIENAI", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(true);
		}
		try {
			assertFalse(inout.fixFormula(new BigDecimal(1), "TESTDIRECTION001",
				"som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertFalse(inout.deFixFormula(new BigDecimal(1), "ARIENAI", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(true);
		}
		try {
			assertFalse(inout.deFixFormula(new BigDecimal(1),
				"TESTDIRECTION001", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertFalse(inout.fixFormula(new BigDecimal(1), "TESTDIRECTION001",
				"som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}

		try {
			assertFalse(inout.resultFormula(new BigDecimal(901),
				new BigDecimal(11), new BigDecimal(1), "ARIENAI", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(true);
		}
		try {
			assertFalse(inout.resultFormula(new BigDecimal(901),
				new BigDecimal(11), new BigDecimal(1), "TESTDIRECTION001",
				"som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertFalse(inout.deResultFormula(new BigDecimal(901),
				new BigDecimal(11), new BigDecimal(1), "ARIENAI", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(true);
		}
		try {
			assertFalse(inout.deResultFormula(new BigDecimal(901),
				new BigDecimal(11), new BigDecimal(1), "TESTDIRECTION001",
				"som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertFalse(inout
					.resultFormula(new BigDecimal(1), "ARIENAI", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(true);
		}
		try {
			assertFalse(inout.resultFormula(new BigDecimal(1),
				"TESTDIRECTION001", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertFalse(inout.deResultFormula(new BigDecimal(1), "ARIENAI",
				"som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(true);
		}
		try {
			assertFalse(inout.deResultFormula(new BigDecimal(1),
				"TESTDIRECTION001", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertFalse(inout.resultFormula(new BigDecimal(1),
				"TESTDIRECTION001", "som"));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}

	}

	/**
	 * 在庫入出庫
	 * 
	 */
	public void testInoutTx() {
		Stockinout inout = new Stockinout(dao);
		String itemCd = "00000783";
		BigDecimal qty = new BigDecimal(100);
		Timestamp inoutDate = AecDateUtils.getCurrentTimestamp();
		String locationCd = "B77777777";
		String lotNo = "TESTLOT";
		String loginUser = "som";
		try {
			assertTrue(inout.adjustInventory(itemCd, qty, inoutDate,
				locationCd, lotNo, loginUser));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertTrue(inout.deAdjustInventory(itemCd, qty, inoutDate,
				locationCd, lotNo, loginUser));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertTrue(inout.deDelivery(itemCd, qty, inoutDate, locationCd,
				lotNo, null, null, null, loginUser));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertTrue(inout.delivery(itemCd, qty, inoutDate, locationCd,
				lotNo, null, null, null, loginUser));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		String slipNo = "UR000000175";
		try {
			assertTrue(inout.returnDelivery(slipNo, loginUser));
		} catch (LogicExceptionEx e) {
			assertTrue(false);
		}
		try {
			assertTrue(inout.deReturnDelivery(slipNo, loginUser));
		} catch (LogicExceptionEx e) {
			assertTrue(false);
		}
		String afterLocationCd = "B77777777";
		String aliasLotNo = "alias";
		BigDecimal cost = new BigDecimal(10);
		try {
			assertTrue(inout.stock(itemCd, qty, inoutDate, afterLocationCd,
				lotNo, null, null, null, aliasLotNo, cost, loginUser));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertTrue(inout
					.deSalesWithoutDelivery(itemCd, qty, inoutDate, locationCd,
						afterLocationCd, lotNo, null, null, null, loginUser));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertTrue(inout.deStock(itemCd, qty, inoutDate, locationCd, lotNo,
				null, null, null, aliasLotNo, cost, loginUser));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertTrue(inout.stock(itemCd, qty, inoutDate, locationCd, lotNo,
				null, null, null, aliasLotNo, cost, loginUser));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		itemCd = "09046778";
		qty = new BigDecimal(10);
		locationCd = "B10327000";
		afterLocationCd = "B1032700V";
		lotNo = "P0005220003";
		try {
			assertTrue(inout
					.salesWithoutDelivery(itemCd, qty, inoutDate, locationCd,
						afterLocationCd, lotNo, null, null, null, loginUser));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
	}

	/**
	 * 受注出荷
	 */
	public void testOrderTx() {
		StrCodeAndNumberDto dto = new StrCodeAndNumberDto();
		dto.setLngFlg(new BigDecimal(1));
		dto.setStrCode("TEST001");
		dto.setLngRowNo(null);
		dto.setOutPara("");
		dto.setStrLoginUser("som");
		dao.entryOrder(dto);

		log.debug(dto.getOutPara());
		assertEquals(dto.getOutPara(), "COMPLETE");

		StockinoutForOrder inout = new StockinoutForOrder(dao);
		String orderNo = "TEST002";
		String loginUser = "som";
		try {
			assertTrue(inout.entryOrder(orderNo, loginUser));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertTrue(inout.cancelOrder(orderNo, loginUser));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		BigDecimal rowNo = new BigDecimal(1);
		try {
			assertTrue(inout.entryOrder(orderNo, rowNo, loginUser));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertTrue(inout.cancelOrder(orderNo, rowNo, loginUser));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertTrue(inout.entryOrder(orderNo, rowNo, loginUser));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertTrue(inout.completeOrder(orderNo, loginUser));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		orderNo = "TEST001";
		try {
			assertTrue(inout.entryOrder(orderNo, loginUser));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertTrue(inout.cancelOrder(orderNo, loginUser));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertTrue(inout.completeOrder(orderNo, loginUser));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		StockinoutForShipping inouts = new StockinoutForShipping(dao);
		String slipNo = "TESTSHIP002";
		try {
			assertTrue(inouts.entryShipping(slipNo, loginUser));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertTrue(inouts.cancelShipping(slipNo, loginUser));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		BigDecimal slipRowNo = new BigDecimal(1);
		try {
			assertTrue(inouts.entryShipping(slipNo, slipRowNo, loginUser));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertTrue(inouts.cancelShipping(slipNo, slipRowNo, loginUser));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		slipNo = "TESTSHIP001";
		try {
			assertTrue(inouts.entryShipping(slipNo, loginUser));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertTrue(inouts.entryResult(slipNo, loginUser));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertTrue(inouts.cancelResult(slipNo, loginUser));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}
		try {
			assertTrue(inouts.completeShipping(slipNo, loginUser));
		} catch (LogicExceptionEx e) {
			log.debug(e.getMessage());
			assertTrue(false);
		}

	}
}
