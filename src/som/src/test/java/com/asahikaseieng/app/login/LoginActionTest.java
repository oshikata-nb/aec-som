/*
 * Created on 2007/03/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.login;

import com.asahikaseieng.Constants;
import com.asahikaseieng.test.AbstractS2StrutsTestCase;
import com.asahikaseieng.test.TcHelper;

/**
 * ログインActionテストクラス.
 * @author jbd
 */
public class LoginActionTest extends AbstractS2StrutsTestCase {

	private static final int USERID_MAX = 10;

	private static final int PASSWORD_MAX = 10;

	/**
	 * コンストラクタ.
	 * 
	 * @param testName テスト名
	 */
	public LoginActionTest(final String testName) {
		super(testName, "/PasswordCheck.do");
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		;
	}

	/**
	 * init実行後の状態を作り出す.
	 */
	private void setupInit() {
		super.reset();
		addRequestParameter("op", "init");
		actionPerform();
	}

	/**
	 * initテスト.
	 */
	public void testUnspecified() {

		/** 正常に遷移できる場合 */
		super.reset();
		// addRequestParameter("op", "unspecified");
		actionPerform();
		verifyNoActionErrors();
		verifyForwardPath("/jsp/login/login.jsp");
	}

	/**
	 * ログイン失敗時のテストケース.
	 */
	public void testNologin() {

		setupInit();
		addRequestParameter("op", "login");
		addRequestParameter("userId", Constants.TEST_PARAMETER_NODATA);
		addRequestParameter("password", TcHelper.getString(PASSWORD_MAX));
		actionPerform();
		verifyActionErrors(new String[] {"errors.nodata"});
		verifyInputForward();
	}

	/**
	 * loginテスト.
	 */
	public void testLogin() {

		/** 正常に遷移できる場合(MAX桁) */
		setupInit();
		addRequestParameter("op", "login");
		addRequestParameter("userId", TcHelper.getString(USERID_MAX));
		addRequestParameter("password", TcHelper.getString(PASSWORD_MAX));
		actionPerform();
		verifyNoActionErrors();
		verifyForward("success"); // 一度ログイン画面に戻る

		/* フォームの値はクリアされている */
		LoginForm frm = (LoginForm) getActionForm();
		assertNull(frm.getUserId());
		assertNull(frm.getPassword());

		/** 必須入力チェック */
		setupInit();
		addRequestParameter("op", "login");
		actionPerform();
		verifyActionErrors(new String[] {"errors.required", "errors.required"});
		verifyInputForward();

		/** MAX桁チェック */
		setupInit();
		addRequestParameter("op", "login");
		addRequestParameter("userId", TcHelper.getString(USERID_MAX + 1));
		addRequestParameter("password", TcHelper.getString(PASSWORD_MAX + 1));
		actionPerform();
		verifyActionErrors(new String[] {"errors.maxlength", "errors.maxlength"});
		verifyInputForward();

		/** 妥当性チェック */
		setupInit();
		addRequestParameter("op", "login");
		addRequestParameter("userId", "abcディー");
		addRequestParameter("password", "あiueo");
		actionPerform();
		verifyActionErrors(new String[] {"errors.alphameric.code",
				"errors.alphameric.code"});
		verifyInputForward();
	}

	/**
	 * popupテスト.
	 */
	public void testPopup() {

		/** 正常に遷移できる場合 */
		super.reset();
		addRequestParameter("op", "popup");
		actionPerform();
		verifyNoActionErrors();
		verifyForwardPath("/jsp/common/base.jsp");
	}

}
