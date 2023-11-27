/*
 * Created on 2007/04/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mypage;

import com.asahikaseieng.Constants;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.test.AbstractS2StrutsTestCase;

/**
 * MyPageActionテストクラス.
 * @author jbd
 */
public class MyPageActionTest extends AbstractS2StrutsTestCase {

	/**
	 * コンストラクタ.
	 * 
	 * @param testName テスト名
	 */
	public MyPageActionTest(final String testName) {
		super(testName, "/MyPage.do");
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		LoginInfo li = new LoginInfo();
		li.setTantoCd("0123");
		getSession().setAttribute(Constants.LOGIN_KEY, li);
	}

	/**
	 * init実行後の状態を作り出す.
	 */
	private void setupInit() {
		super.reset();
		addRequestParameter("op", "init");
		actionPerform();
		verifyNoActionErrors();
		verifyForwardPath("/jsp/mypage/mypage.jsp");
	}

	/**
	 * initテスト.
	 */
	public void testInit() {

		/* 正常に遷移できる場合 */
		super.reset();
		addRequestParameter("op", "init");
		actionPerform();
		verifyNoActionErrors();
		verifyForwardPath("/jsp/mypage/mypage.jsp");
	}

	/**
	 * registのテスト.
	 */
	public void testRegistWithError() {

		/* 担当コードが空の場合 */
		super.reset();
		setupInit();
		getSession().setAttribute(Constants.LOGIN_KEY, new LoginInfo());
		addRequestParameter("op", "regist");
		actionPerform();
		verifyNoActionErrors();
		verifyForwardPath("/jsp/common/gadget_move_error.jsp");
	}

	/**
	 * registのテスト.
	 */
	public void testRegist() {

		/* 正常な場合 */
		super.reset();
		setupInit();
		addRequestParameter("op", "regist");
		actionPerform();
		verifyNoActionErrors();
		verifyForwardPath("/jsp/common/empty.jsp");
	}

}
