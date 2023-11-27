/*
 * Created on 2009/12/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.cost.costaccounting;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 原価計算データ送信 Actionクラス.
 * @author t0011036
 */
public final class CostAccountingAction extends AbstractAction {

	private CostAccountingLogic costAccountingLogic;

	/**
	 * コンストラクタ.
	 */
	public CostAccountingAction() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionForward init(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		CostAccountingForm frm = (CostAccountingForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_COST_ACCOUNTING,
			Constants.TAB_ID_COST_ACCOUNTING_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("mypage");
		}

		return mapping.findForward("success");
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionForward update(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("update.");
		}

		CostAccountingForm frm = (CostAccountingForm) form;

		String tantoCd = getLoginInfo(request).getTantoCd();

		/* 実行 */
		costAccountingLogic.execute(frm, tantoCd);

		for (CostAccountingBean bean : frm.getExecuteList()) {
			/* 数値 ---> 文字列数値 */
			convNumToStr(request, bean);
		}

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("success");
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionForward importData(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("importData.");
		}

		CostAccountingForm frm = (CostAccountingForm) form;

		String tantoCd = getLoginInfo(request).getTantoCd();

		/* 実行 */
		costAccountingLogic.importData(frm, tantoCd);

		for (CostAccountingBean bean : frm.getImportList()) {
			/* 数値 ---> 文字列数値 */
			convNumToStr(request, bean);
		}

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("success");
	}

	/**
	 * 数値 ---> 文字列数値
	 * @param request request
	 * @param frm 画面データ
	 * @param i インデックス
	 */
	private void convNumToStr(final HttpServletRequest request,
			final CostAccountingBean bean) {
		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		/* 一覧初期化 */
		bean.setStrCnt(checker.format("SONOTA", bean.getCnt()));
	}

	/* -------------------- setter -------------------- */

	/**
	 * costAccountingLogicを設定します。
	 * @param costAccountingLogic costAccountingLogic
	 */
	public void setCostAccountingLogic(
			final CostAccountingLogic costAccountingLogic) {
		this.costAccountingLogic = costAccountingLogic;
	}
}
