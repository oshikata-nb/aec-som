/*
 * Created on 2007/04/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.struts.validator;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.asahikaseieng.Constants;
import junit.framework.TestCase;

import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorException;
import org.apache.commons.validator.ValidatorResources;
import org.apache.commons.validator.ValidatorResult;
import org.apache.commons.validator.ValidatorResults;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.LabelValueBean;
import org.apache.struts.util.PropertyMessageResources;

import servletunit.HttpServletRequestSimulator;
import servletunit.ServletContextSimulator;

/**
 * Rulesテストケース.<br>
 * (実行時、webappにクラスパスを追加して下さい(拡張→フォルダの追加)).
 * @author jbd
 */
public class RulesTest extends TestCase {

	private static final int CNT = 4;

	/**
	 * Resources used for validation tests.
	 */
	private ValidatorResources resources;

	/**
	 * Constructor for RulesTest.
	 * 
	 * @param testname testname
	 */
	public RulesTest(final String testname) {
		super(testname);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUp() throws Exception {
		InputStream[] streams = new InputStream[2];
		resources = new ValidatorResources();

		try {
			streams[0] = this.getClass().getResourceAsStream(
				"validator-test.xml");
			streams[1] = this.getClass().getResourceAsStream(
				"/validator-rules.xml");

			resources = new ValidatorResources(streams);

		} catch (IOException e) {
			throw e;
		} finally {
			if (streams[1] != null) {
				streams[1].close();
			}
			if (streams[0] != null) {
				streams[0].close();
			}
		}
	}

	/**
	 * 結果検証を行う.
	 * 
	 * @param form validator-test.xmlに書いているform名
	 * @param action どのruleを検証するか
	 * @param resultKey どのプロパティについて結果検証をおこなうか
	 * @param info 対象となるBean
	 * @param passed 成功するのはどっちか true 成功する事を期待 false 失敗することを期待
	 * @throws ValidatorException
	 */
	private void testImpl(final String form, final String action,
			final String resultKey, final Object info, final boolean passed)
			throws ValidatorException {

		// ダミーのリクエストを作成
		HttpServletRequestSimulator request = new HttpServletRequestSimulator(
				new ServletContextSimulator()) {
			public String getParameter(final String name) {
				return "ho%00ge'\"\\\';)yama";
			}
		};
		// メッセージリソースをリクエストにセット(MASKIFで例外が投げられるのでセットしておく)
		request.setAttribute(Globals.MESSAGES_KEY,
			new PropertyMessageResources(null,
					Constants.APPLICATION_PROPERTIES, true));

		testImpl(request, form, action, resultKey, info, passed);
	}

	private void testImpl(final HttpServletRequest request, final String form,
			final String action, final String resultKey, final Object info,
			final boolean passed) throws ValidatorException {

		Validator validator = new Validator(resources, form);
		// errorsを渡す
		validator.setParameter("org.apache.struts.action.ActionMessages",
			new ActionMessages());

		validator.setParameter(Validator.BEAN_PARAM, info);

		// Resourcesクラス内の文字列と同じdeprecatedなので。。。
		validator
				.setParameter("javax.servlet.http.HttpServletRequest", request);

		ValidatorResults results = validator.validate();

		assertNotNull("Results are null.", results);

		ValidatorResult result = results.getValidatorResult(resultKey);

		assertNotNull(action + " value ValidatorResult should not be null.",
			result);

		assertTrue(action + " value ValidatorResult should contain the '"
				+ action + "' action.", result.containsAction(action));

		String passedString = "failed";
		if (passed) {
			passedString = "passed";
		}

		boolean res = false;
		if (passed) {
			res = result.isValid(action);
		} else {
			res = !result.isValid(action);
		}

		assertTrue(action + " value ValidatorResult for the '" + action
				+ "' action should have " + passedString + ".", res);
	}

	/* -------------------- カスタムチェック -------------------- */

	/**
	 * ValidateDecimalテスト.
	 * @throws Exception 例外
	 */
	public void testValidateDecimal() throws Exception {
		TestBean info = new TestBean();

		String formName = "decimalForm";
		String checkName = "decimal";

		info.setValue("1234567890");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("-1234567890");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("123.0");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("1,234,567,890.9");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("123.00");
		testImpl(formName, checkName, "value", info, false);

		info.setValue("aaa.00");
		testImpl(formName, checkName, "value", info, false);
	}

	/**
	 * ValidateSelectedテスト.
	 * @throws Exception 例外
	 */
	public void testValidateSelected() throws Exception {
		TestBean info = new TestBean();
		List<LabelValueBean> list = new ArrayList<LabelValueBean>();
		list.add(new LabelValueBean(Constants.UNSELECTED_LABEL,
				Constants.UNSELECTED_VALUE));
		list.add(new LabelValueBean("a", "1"));
		list.add(new LabelValueBean("b", "2"));
		info.setList(list);

		info.setValue("");
		testImpl("selectedForm", "selected", "value", info, true);

		info.setValue("1");
		testImpl("selectedForm", "selected", "value", info, true);

		info.setValue("99");
		testImpl("selectedForm", "selected", "value", info, false);
	}

	/* -------------------- カスタムルール(既存)チェック -------------------- */

	/**
	 * ValidateIntegerテスト.
	 * @throws Exception 例外
	 */
	public void testValidateInteger() throws Exception {
		TestBean info = new TestBean();

		String formName = "integerForm";
		String checkName = "integer";

		info.setValue("123");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("1234567890");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("-1234567890");
		testImpl(formName, checkName, "value", info, true);

		// 桁が多過ぎ
		info.setValue("91234567890");
		testImpl(formName, checkName, "value", info, false);

		info.setValue("-91234,567890");
		testImpl(formName, checkName, "value", info, false);

		// 小数桁がある
		info.setValue("1234.0");
		testImpl(formName, checkName, "value", info, false);

		// 数値ではない
		info.setValue("123si");
		testImpl(formName, checkName, "value", info, false);

		// 全角文字
		info.setValue("１２３");
		testImpl(formName, checkName, "value", info, false);
	}

	/**
	 * ValidateLongテスト.
	 * @throws Exception 例外
	 */
	public void testValidateLong() throws Exception {
		TestBean info = new TestBean();

		String formName = "longForm";
		String checkName = "long";

		info.setValue("123");
		testImpl(formName, checkName, "value", info, true);

		// Long.MAX_VALUE = 9223372036854775808 ← -1
		// Long.MIN_VALUE = -9223372036854775808 ← +1
		info.setValue("9223372036854775807");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("-9223372036854775808");
		testImpl(formName, checkName, "value", info, true);

		// 桁が多過ぎ
		info.setValue("9223372036854775808");
		testImpl(formName, checkName, "value", info, false);

		info.setValue("-9223372036854775809");
		testImpl(formName, checkName, "value", info, false);

		// 小数桁がある
		info.setValue("1234.0");
		testImpl(formName, checkName, "value", info, false);

		// 数値ではない
		info.setValue("123si");
		testImpl(formName, checkName, "value", info, false);

		// 全角文字
		info.setValue("１２３");
		testImpl(formName, checkName, "value", info, false);
	}

	/* -------------------- カンマ対応(Ex系)チェック -------------------- */

	/**
	 * ValidateIntegerExテスト.
	 * @throws Exception 例外
	 */
	public void testValidateIntegerEx() throws Exception {
		TestBean info = new TestBean();

		String formName = "integerExForm";
		String checkName = "integerEx";

		info.setValue("123");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("1,234,567,890");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("-1,234,567,890");
		testImpl(formName, checkName, "value", info, true);

		// 桁が多過ぎ
		info.setValue("91,234,567,890");
		testImpl(formName, checkName, "value", info, false);

		info.setValue("-91,234,567,890");
		testImpl(formName, checkName, "value", info, false);

		// 小数桁がある
		info.setValue("1,234.0");
		testImpl(formName, checkName, "value", info, false);

		// カンマがない
		info.setValue("1234");
		testImpl(formName, checkName, "value", info, false);

		// カンマの位置がおかしい
		info.setValue("1,23,4");
		testImpl(formName, checkName, "value", info, false);

		info.setValue(",1,234");
		testImpl(formName, checkName, "value", info, false);

		info.setValue("1,,234");
		testImpl(formName, checkName, "value", info, false);

		info.setValue("1,234,");
		testImpl(formName, checkName, "value", info, false);

		// 全角文字
		info.setValue("１２３");
		testImpl(formName, checkName, "value", info, false);
	}

	/**
	 * ValidateFloatExテスト.
	 * @throws Exception 例外
	 */
	public void testValidateFloatEx() throws Exception {

		TestBean info = new TestBean();

		String formName = "floatExForm";
		String checkName = "floatEx";

		info.setValue("123");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("1,234,567,890");
		testImpl(formName, checkName, "value", info, true);

		/* 最大値チェック？ */
		// System.err.println("" + new BigDecimal(Float.MAX_VALUE));
		info.setValue("340,282,346,638,528,859,811,704,183,484,516,925,440.00");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("1,234,567,890.01");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("-1,234,567,890.01");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("1,234,567,890.0123456789");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("-1,234,567,890.0123456789");
		testImpl(formName, checkName, "value", info, true);

		// カンマがない
		info.setValue("1234");
		testImpl(formName, checkName, "value", info, false);

		// ピリオドの後ろにカンマがある
		info.setValue("1,234.0,5");
		testImpl(formName, checkName, "value", info, false);

		info.setValue("1,234.5,");
		testImpl(formName, checkName, "value", info, false);

		info.setValue("1,234.,");
		testImpl(formName, checkName, "value", info, false);

		// ピリオドの位置がおかしい
		info.setValue("1,234.0.0");
		testImpl(formName, checkName, "value", info, false);

		info.setValue("1,234.0.");
		testImpl(formName, checkName, "value", info, false);

		info.setValue("1,234..");
		testImpl(formName, checkName, "value", info, false);

		info.setValue("1,234..0");
		testImpl(formName, checkName, "value", info, false);

		// 全角文字
		info.setValue("１２３");
		testImpl(formName, checkName, "value", info, false);
	}

	/**
	 * ValidateLongExテスト.
	 * @throws Exception 例外
	 */
	public void testValidateLongEx() throws Exception {
		TestBean info = new TestBean();

		String formName = "longExForm";
		String checkName = "longEx";

		info.setValue("123");
		testImpl(formName, checkName, "value", info, true);
		info.setValue("9,372,036,854,775,808");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("-9,372,036,854,775,808");
		testImpl(formName, checkName, "value", info, true);

		// 桁が多過ぎ
		info.setValue("9,223,372,036,854,775,809");
		testImpl(formName, checkName, "value", info, false);

		info.setValue("-9,223,372,036,854,775,809");
		testImpl(formName, checkName, "value", info, false);

		// 小数桁がある
		info.setValue("1,234.0");
		testImpl(formName, checkName, "value", info, false);

		// カンマがない
		info.setValue("1234");
		testImpl(formName, checkName, "value", info, false);

		// カンマの位置がおかしい
		info.setValue("1,23,4");
		testImpl(formName, checkName, "value", info, false);

		info.setValue(",1,234");
		testImpl(formName, checkName, "value", info, false);

		info.setValue("1,,234");
		testImpl(formName, checkName, "value", info, false);

		info.setValue("1,234,");
		testImpl(formName, checkName, "value", info, false);

		// 全角文字
		info.setValue("１２３");
		testImpl(formName, checkName, "value", info, false);
	}

	/**
	 * validateBigDecimalExテスト.
	 * @throws Exception 例外
	 */
	public void testValidateBigDecimalEx() throws Exception {
		TestBean info = new TestBean();

		String formName = "bigDecimalExForm";
		String checkName = "bigDecimalEx";

		info.setValue("123");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("12,345,678,901,234,567,890");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("12,345,678,901,234,567,890.999");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("-12,345,678,901,234,567,890.999");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("12,345,678,901,234,567,890.0123456789");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("-12,345,678,901,234,567,890.0123456789");
		testImpl(formName, checkName, "value", info, true);

		// カンマがない
		info.setValue("1234");
		testImpl(formName, checkName, "value", info, false);

		// ピリオドの後ろにカンマがある
		info.setValue("1,234.0,5");
		testImpl(formName, checkName, "value", info, false);

		info.setValue("1,234.5,");
		testImpl(formName, checkName, "value", info, false);

		info.setValue("1,234.,");
		testImpl(formName, checkName, "value", info, false);

		// ピリオドの位置がおかしい
		info.setValue("1,234.0.0");
		testImpl(formName, checkName, "value", info, false);

		info.setValue("1,234.0.");
		testImpl(formName, checkName, "value", info, false);

		info.setValue("1,234..");
		testImpl(formName, checkName, "value", info, false);

		info.setValue("1,234..0");
		testImpl(formName, checkName, "value", info, false);

		// 全角文字
		info.setValue("１２３");
		testImpl(formName, checkName, "value", info, false);
	}

	/**
	 * ValidateRangeEx(小数点無しクラス)テスト.
	 * @throws Exception 例外
	 */
	public void testValidateRangeEx() throws Exception {

		TestBean info = new TestBean();

		String[] formName = new String[] {"intRangeExForm", "longRangeExForm"};
		String[] checkName = new String[] {"intRangeEx", "longRangeEx"};

		for (int i = 0; i < 2; i++) {
			// min=1000・max=9999999

			info.setValue("1,001");
			testImpl(formName[i], checkName[i], "value", info, true);

			info.setValue("9,999,999");
			testImpl(formName[i], checkName[i], "value", info, true);

			info.setValue("55,555");
			testImpl(formName[i], checkName[i], "value", info, true);

			// 範囲外(min)
			info.setValue("999");
			testImpl(formName[i], checkName[i], "value", info, false);

			// 範囲外(max)
			info.setValue("10,000,000");
			testImpl(formName[i], checkName[i], "value", info, false);

			// カンマがおかしい
			info.setValue("9,9,99,999");
			testImpl(formName[i], checkName[i], "value", info, false);

			info.setValue(",999,999");
			testImpl(formName[i], checkName[i], "value", info, false);

			info.setValue("9,999,");
			testImpl(formName[i], checkName[i], "value", info, false);

			// ピリオドがある
			info.setValue("9,999.");
			testImpl(formName[i], checkName[i], "value", info, false);

			info.setValue("9,999.1");
			testImpl(formName[i], checkName[i], "value", info, false);

			// 全角数字
			info.setValue("９");
			testImpl(formName[i], checkName[i], "value", info, false);

			// 文字
			info.setValue("a");
			testImpl(formName[i], checkName[i], "value", info, false);

			// 全角文字
			info.setValue("あ");
			testImpl(formName[i], checkName[i], "value", info, false);

			// カラ文字
			info.setValue("");
			testImpl(formName[i], checkName[i], "value", info, true);

			// NULL
			info.setValue(null);
			testImpl(formName[i], checkName[i], "value", info, true);
		}
	}

	/**
	 * ValidateRangeEx(小数点有りクラス)テスト.
	 * @throws Exception 例外
	 */
	public void testValidateRangeEx2() throws Exception {

		TestBean info = new TestBean();

		String[] formName = new String[] {"doubleRangeExForm",
				"floatRangeExForm"};
		String[] checkName = new String[] {"doubleRangeEx", "floatRangeEx"};

		for (int i = 0; i < 2; i++) {
			// min=-1000.05・max=9999999.05

			info.setValue("-1,000.00");
			testImpl(formName[i], checkName[i], "value", info, true);

			info.setValue("9,999,999.05");
			testImpl(formName[i], checkName[i], "value", info, true);

			info.setValue("0.00");
			testImpl(formName[i], checkName[i], "value", info, true);

			// 範囲外(min)
			info.setValue("-1,000.06");
			testImpl(formName[i], checkName[i], "value", info, false);

			// 範囲外(max)
			info.setValue("10,000,000.01");
			testImpl(formName[i], checkName[i], "value", info, false);

			// カンマがおかしい
			info.setValue("9,9,99,999");
			testImpl(formName[i], checkName[i], "value", info, false);

			info.setValue(",999,999");
			testImpl(formName[i], checkName[i], "value", info, false);

			info.setValue("9,999,");
			testImpl(formName[i], checkName[i], "value", info, false);

			// ピリオドがおかしい
			info.setValue("9,999.");
			testImpl(formName[i], checkName[i], "value", info, false);

			// 全角数字
			info.setValue("９");
			testImpl(formName[i], checkName[i], "value", info, false);
		}
	}

	/**
	 * validateBigDecimalRangeExテスト.
	 * @throws Exception 例外
	 */
	public void testValidateBigDecimalRangeEx() throws Exception {

		TestBean info = new TestBean();

		String formName = "bigDecimalRangeExForm";
		String checkName = "bigDecimalRangeEx";

		info.setValue("-99,999,999,999,999,999,999.55");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("99,999,999,999,999,999,999.55");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("0.00");
		testImpl(formName, checkName, "value", info, true);

		// 範囲外(min)
		info.setValue("-99,999,999,999,999,999,999.56");
		testImpl(formName, checkName, "value", info, false);

		// 範囲外(max)
		info.setValue("99,999,999,999,999,999,999.56");
		testImpl(formName, checkName, "value", info, false);

		// カンマがおかしい
		info.setValue("9,9,99,999");
		testImpl(formName, checkName, "value", info, false);

		info.setValue(",999,999");
		testImpl(formName, checkName, "value", info, false);

		info.setValue("9,999,");
		testImpl(formName, checkName, "value", info, false);

		// ピリオドがおかしい
		info.setValue("9,999.");
		testImpl(formName, checkName, "value", info, false);

		// 全角数字
		info.setValue("９");
		testImpl(formName, checkName, "value", info, false);
	}

	/**
	 * Dateテスト.
	 * @throws Exception 例外
	 */
	public void testValidateDate() throws Exception {

		TestBean info = new TestBean();

		String formName = "dateForm";
		String checkName = "date";

		info.setValue1("2006/02/28");
		testImpl(formName, checkName, "value1", info, true);

		info.setValue1("06/02/28");
		testImpl(formName, checkName, "value1", info, false);

		info.setValue1("2006/02/29");
		testImpl(formName, checkName, "value1", info, false);

		info.setValue1("06/02/29");
		testImpl(formName, checkName, "value1", info, false);

		info.setValue1("20000/1/29");
		testImpl(formName, checkName, "value1", info, false);

		info.setValue1("2/1/9");
		testImpl(formName, checkName, "value1", info, false);

		info.setValue1("yyyy/MM/dd");
		testImpl(formName, checkName, "value1", info, false);

		info.setValue2("2006/02");
		testImpl(formName, checkName, "value2", info, true);

		info.setValue2("06/02");
		testImpl(formName, checkName, "value2", info, false);

		info.setValue2("2006/13");
		testImpl(formName, checkName, "value2", info, false);

		info.setValue2("06/2");
		testImpl(formName, checkName, "value2", info, false);

		info.setValue2("20000/1");
		testImpl(formName, checkName, "value2", info, false);

		info.setValue2("2/1");
		testImpl(formName, checkName, "value2", info, false);

		info.setValue2("yyyy/MM/dd");
		testImpl(formName, checkName, "value2", info, false);
	}

	/**
	 * ValidateIfExテスト.
	 * @throws Exception 例外
	 */
	public void testValidateIfEx() throws Exception {

		TestBean info = new TestBean();

		String[] formName = new String[] {"integerIfExForm", "floatIfExForm",
				"doubleIfExForm", "longIfExForm"};
		String[] checkName = new String[] {"integerifEx", "floatifEx",
				"doubleifEx", "longifEx"};

		for (int i = 0; i < CNT; i++) {
			// チェックしない
			info.setValue("notinteger");
			info.setValue1("notrunmode");
			info.setValue2("notcheckmode");
			testImpl(formName[i], checkName[i], "value", info, true);

			info.setValue("notinteger");
			info.setValue1("runmode");
			info.setValue2("notcheckmode");
			testImpl(formName[i], checkName[i], "value", info, true);

			info.setValue("notinteger");
			info.setValue1("notrunmode");
			info.setValue2("checkmode");
			testImpl(formName[i], checkName[i], "value", info, true);

			// チェックする
			info.setValue("notinteger");
			info.setValue1("runmode");
			info.setValue2("checkmode");
			testImpl(formName[i], checkName[i], "value", info, false);

			info.setValue("234,567,890");
			info.setValue1("runmode");
			info.setValue2("checkmode");
			testImpl(formName[i], checkName[i], "value", info, true);
		}
	}

	/* -------------------- validate if -------------------- */

	/**
	 * ValidateIntegerIfテスト.
	 * @throws Exception 例外
	 */
	public void testValidateRequiredIf() throws Exception {

		TestBean info = new TestBean();

		String formName = "requiredIfForm";
		String checkName = "requiredif";

		// チェックするのは、value1＝A・value2≠B

		// チェックしない
		info.setValue("");
		info.setValue1("notA");
		info.setValue2("B");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("");
		info.setValue1("notA");
		info.setValue2("notB");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("");
		info.setValue1("A");
		info.setValue2("B");
		testImpl(formName, checkName, "value", info, true);

		// チェックする
		info.setValue(null);
		info.setValue1("A");
		info.setValue2("notB");
		testImpl(formName, checkName, "value", info, false);

		info.setValue("");
		info.setValue1("A");
		info.setValue2("notB");
		testImpl(formName, checkName, "value", info, false);
	}

	/**
	 * ValidateMaskIfテスト.
	 * @throws Exception 例外
	 */
	public void testValidateMaskIf() throws Exception {

		TestBean info = new TestBean();

		String formName = "maskIfForm";
		String checkName = "maskif";

		// チェックするのは、value1＝A・maskは、MASKTEST

		// チェックしない
		info.setValue("MASKnotTEST");
		info.setValue1("notA");
		testImpl(formName, checkName, "value", info, true);

		info.setValue(null);
		info.setValue1("A");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("");
		info.setValue1("A");
		testImpl(formName, checkName, "value", info, true);

		// チェックする
		info.setValue("MASKTEST");
		info.setValue1("A");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("-MASKTEST-");
		info.setValue1("A");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("MASK");
		info.setValue1("A");
		testImpl(formName, checkName, "value", info, false);

		info.setValue("MASKnotTEST");
		info.setValue1("A");
		testImpl(formName, checkName, "value", info, false);
	}

	/**
	 * DateIfテスト.
	 * @throws Exception 例外
	 */
	public void testValidateDateIf() throws Exception {

		TestBean info = new TestBean();

		String formName = "dateIfForm";
		String checkName = "dateif";

		// チェックしない
		info.setValue("yyyy/MM/dd");
		info.setValue1("notA");
		testImpl(formName, checkName, "value", info, true);

		info.setValue(null);
		info.setValue1("A");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("");
		info.setValue1("A");
		testImpl(formName, checkName, "value", info, true);

		// チェックする
		info.setValue("2006/02/28");
		info.setValue1("A");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("06/02/28");
		info.setValue1("A");
		testImpl(formName, checkName, "value", info, false);

		info.setValue("2006/02/29");
		info.setValue1("A");
		testImpl(formName, checkName, "value", info, false);

		info.setValue("06/02/29");
		info.setValue1("A");
		testImpl(formName, checkName, "value", info, false);

		info.setValue("yyyy/MM/dd");
		info.setValue1("A");
		testImpl(formName, checkName, "value", info, false);
	}

	/**
	 * MmaxbytelengthIfテスト
	 * @throws Exception 例外
	 */
	public void testValidateStringIf() throws Exception {

		TestBean info = new TestBean();

		String formName = "stringIfForm";
		String checkName = "stringif";

		// チェックしない
		info.setValue("1234go");
		info.setValue1("notA");
		testImpl(formName, checkName, "value", info, true);

		info.setValue(null);
		info.setValue1("A");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("");
		info.setValue1("A");
		testImpl(formName, checkName, "value", info, true);

		// チェックする
		info.setValue("1234567890");
		info.setValue1("A");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("1234go");
		info.setValue1("A");
		testImpl(formName, checkName, "value", info, false);

		info.setValue("1234ｺﾞ");
		info.setValue1("A");
		testImpl(formName, checkName, "value", info, false);

		info.setValue("1234ゴ");
		info.setValue1("A");
		testImpl(formName, checkName, "value", info, false);
	}

	/**
	 * validateDecimalIfテスト.
	 * @throws Exception 例外
	 */
	public void testvalidateDecimalIf() throws Exception {

		TestBean info = new TestBean();

		String formName = "decimalifForm";
		String checkName = "decimalif";

		// チェックしない
		info.setValue("123456789.1");
		info.setValue1("notA");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("123456789.12");
		info.setValue1("notA");
		testImpl(formName, checkName, "value", info, true);

		info.setValue(null);
		info.setValue1("notA");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("");
		info.setValue1("notA");
		testImpl(formName, checkName, "value", info, true);

		// チェックする
		info.setValue("1234567890");
		info.setValue1("A");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("-1234567890");
		info.setValue1("A");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("123.0");
		info.setValue1("A");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("1,234,567,890.9");
		info.setValue1("A");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("123.00");
		info.setValue1("A");
		testImpl(formName, checkName, "value", info, false);

		info.setValue("aaa.00");
		info.setValue1("A");
		testImpl(formName, checkName, "value", info, false);

		info.setValue(null);
		info.setValue1("A");
		testImpl(formName, checkName, "value", info, false);

		info.setValue("");
		info.setValue1("A");
		testImpl(formName, checkName, "value", info, false);

	}

	/**
	 * ValidateStringテスト.
	 * @throws Exception 例外
	 */
	public void testValidateString1() throws Exception {

		TestBean info = new TestBean();

		// 全て半角数値だったらtrue

		String formName = "stringForm1";
		String checkName = "string";

		info.setValue("");
		testImpl(formName, checkName, "value", info, true);

		info.setValue(null);
		testImpl(formName, checkName, "value", info, true);

		info.setValue("1234567890");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("１２３４５６７８９０");
		testImpl(formName, checkName, "value", info, false);

		info.setValue("1234ｺﾞ");
		testImpl(formName, checkName, "value", info, false);

		info.setValue("1234ゴ");
		testImpl(formName, checkName, "value", info, false);

		info.setValue("1234go");
		testImpl(formName, checkName, "value", info, false);
	}

	/**
	 * ValidateStringテスト.
	 * @throws Exception 例外
	 */
	public void testValidateString2() throws Exception {

		TestBean info = new TestBean();

		// 全て半角数値だったらfalse

		String formName = "stringForm2";
		String checkName = "string";

		info.setValue("");
		testImpl(formName, checkName, "value", info, true);

		info.setValue(null);
		testImpl(formName, checkName, "value", info, true);

		info.setValue("1234567980");
		testImpl(formName, checkName, "value", info, false);

		info.setValue("1234ｺﾞ");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("1234ゴ");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("1234go");
		testImpl(formName, checkName, "value", info, true);
	}

	/**
	 * ValidateAalphabetテスト.
	 * @throws Exception 例外
	 */
	public void testValidateAlphabet() throws Exception {

		TestBean info = new TestBean();

		// 半角英字で入力して下さい。

		String formName = "alphabetForm";
		String checkName = "alphabet";

		info.setValue("");
		testImpl(formName, checkName, "value", info, true);

		info.setValue(null);
		testImpl(formName, checkName, "value", info, true);

		info.setValue("HankakuEiji");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("Hankaku Eiji");
		testImpl(formName, checkName, "value", info, false);

		info.setValue("半角Eiji");
		testImpl(formName, checkName, "value", info, false);

		info.setValue("8699Eiji");
		testImpl(formName, checkName, "value", info, false);

		info.setValue("ＨankakuＥiji");
		testImpl(formName, checkName, "value", info, false);
	}

	/**
	 * ValidateAlphanumericテスト.
	 * @throws Exception 例外
	 */
	public void testValidateAlphanumeric() throws Exception {

		TestBean info = new TestBean();

		// 半角英数字で入力して下さい。

		String formName = "alphanumericForm";
		String checkName = "alphanumeric";

		info.setValue("");
		testImpl(formName, checkName, "value", info, true);

		info.setValue(null);
		testImpl(formName, checkName, "value", info, true);

		info.setValue("AlphanNmeric2006");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("1234567890");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("abcdefghijklmnopqrstuvwxyz");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		testImpl(formName, checkName, "value", info, true);

		info.setValue("あlphanNmeric2006");
		testImpl(formName, checkName, "value", info, false);

		info.setValue("ｱlphanNmeric2006");
		testImpl(formName, checkName, "value", info, false);

		info.setValue("@lphanNmeric2006");
		testImpl(formName, checkName, "value", info, false);
	}

	/**
	 * validateCompare(Dateの場合).
	 * @throws Exception 例外
	 */
	public void testValidateCompareDate() throws Exception {
		boolean[][] pattern = { {true, false, false}, // Equal
				{false, true, true}, // NotEqual
				{false, true, false}, // GreaterThan
				{true, true, false}, // GreaterEqual
				{false, false, true}, // LessThan
				{true, false, true}, // LessEqual
				{true, true, true} // unknown
		};

		TestBean info = new TestBean();
		Calendar cal = Calendar.getInstance();
		cal.set(2004, 1, 30);
		info.setDate(cal.getTime());

		for (int i = 0; i < pattern.length; i++) {
			// date = date1
			cal.set(2004, 1, 30);
			info.setDate1(cal.getTime());
			testImpl("compareDateForm" + (i + 1), "compare", "date", info,
				pattern[i][0]);
			// date > date1
			cal.set(2004, 1, 29);
			info.setDate1(cal.getTime());
			testImpl("compareDateForm" + (i + 1), "compare", "date", info,
				pattern[i][1]);
			// date < date1
			cal.set(2004, 1, 31);
			info.setDate1(cal.getTime());
			testImpl("compareDateForm" + (i + 1), "compare", "date", info,
				pattern[i][2]);
		}
	}

	/**
	 * validateCompare(数値の場合).
	 * @throws Exception 例外
	 */
	public void testValidateCompareNumeric() throws Exception {
		boolean[][] pattern = { {true, false, false}, // Equal
				{false, true, true}, // NotEqual
				{false, true, false}, // GreaterThan
				{true, true, false}, // GreaterEqual
				{false, false, true}, // LessThan
				{true, false, true}, // LessEqual
				{true, true, true} // unknown
		};

		TestBean info = new TestBean();
		info.setValue("100");

		for (int i = 0; i < pattern.length; i++) {
			// value = value1
			info.setValue1("100");
			testImpl("compareOtherForm" + (i + 1), "compare", "value", info,
				pattern[i][0]);
			// value > value1
			info.setValue1("99");
			testImpl("compareOtherForm" + (i + 1), "compare", "value", info,
				pattern[i][1]);
			// value < value1
			info.setValue1("101");
			testImpl("compareOtherForm" + (i + 1), "compare", "value", info,
				pattern[i][2]);
		}
	}

	/**
	 * validateCompare(Date/数値以外の場合).
	 * @throws Exception 例外
	 */
	public void testValidateCompareOther() throws Exception {
		boolean[][] pattern = { {true, false, false}, // Equal
				{false, true, true}, // NotEqual
				{false, true, false}, // GreaterThan
				{true, true, false}, // GreaterEqual
				{false, false, true}, // LessThan
				{true, false, true}, // LessEqual
				{true, true, true} // unknown
		};

		TestBean info = new TestBean();
		info.setValue("b");

		for (int i = 0; i < pattern.length; i++) {
			// value = value1
			info.setValue1("b");
			testImpl("compareOtherForm" + (i + 1), "compare", "value", info,
				pattern[i][0]);
			// value > value1
			info.setValue1("a");
			testImpl("compareOtherForm" + (i + 1), "compare", "value", info,
				pattern[i][1]);
			// value < value1
			info.setValue1("c");
			testImpl("compareOtherForm" + (i + 1), "compare", "value", info,
				pattern[i][2]);
		}
	}

	/**
	 * validateCompareIfのテスト.
	 * @throws Exception 例外
	 */
	public void testValidateCompareIf() throws Exception {
		TestBean info = new TestBean();

		// EQUALになるケース
		info.setValue("20050110");
		info.setValue1("20050110");
		info.setValue2("notnull");
		info.setValue3("20050109");
		testImpl("compareIfForm1", "compareif", "value", info, true);

		info.setValue("20050110");
		info.setValue1("20050110");
		info.setValue2("notnull");
		info.setValue3("20050111");
		testImpl("compareIfForm1", "compareif", "value", info, false);

		// EQUALにならないケース
		info.setValue("20050110");
		info.setValue1("20050110");
		info.setValue2("");
		info.setValue3("20050109");
		testImpl("compareIfForm1", "compareif", "value", info, true);

		info.setValue("20050110");
		info.setValue1("20050101");
		info.setValue2("notnull");
		info.setValue3("20050111");
		testImpl("compareIfForm1", "compareif", "value", info, true);

	}

}
