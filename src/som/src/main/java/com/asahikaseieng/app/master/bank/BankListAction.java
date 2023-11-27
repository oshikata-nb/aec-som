/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.bank;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.banklist.BankList;
import com.asahikaseieng.dao.nonentity.master.banklist.BankListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.banklistforreport.BankListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 銀行一覧 Actionクラス.
 * @author t0011036
 */
public final class BankListAction extends AbstractSearchAction {

	private BankListLogic bankListLogic;

	private BankListExcelDecorator bankListExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public BankListAction() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	protected ActionForward initImpl(final ActionMapping mapping,
			final AbstractSearchForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		/* 初期検索無し */
		return mapping.findForward("success");
	}

	/**
	 * {@inheritDoc}
	 */
	protected ActionForward searchImpl(final ActionMapping mapping,
			final HttpServletRequest request, final AbstractSearchForm form)
			throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("search.");
		}

		BankListForm frm = (BankListForm) form;

		/* クリア */
		frm.setSearchList(new ArrayList<BankList>());

		/* 検索条件を取得 */
		BankListPagerCondition condition = (BankListPagerCondition) frm
				.getPager().getPagerCondition();

		/* 検索条件をセット */
		condition.setSrhBankCd(frm.getSrhBankCd());
		condition.setSrhBranchCd(frm.getSrhBranchCd());
		condition.setSrhBankMasterCd(frm.getSrhBankMasterCd());

		/* 帳票(Excel)用に検索条件を保持 */
		BankListConditionForReport reportCondition = new BankListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setCondition(reportCondition);

		/* 明細取得 */
		frm.setSearchList(bankListLogic.getList(condition));

		return mapping.findForward("success");
	}

	/**
	 * クリア処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward clear(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("clear.");
		}

		BankListForm frm = (BankListForm) form;

		/* クリア */
		frm.clear();

		return mapping.findForward("success");
	}

	/**
	 * EXCEL作成処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward report(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("report.");
		}

		BankListForm frm = (BankListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = bankListExcelDecorator.createReport(frm
				.getCondition());

		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setExcelDownloadFlg(true);

		return mapping.findForward("success");
	}

	/**
	 * 新規処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward newPage(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("newPage.");
		}

		return mapping.findForward("newPage");
	}

	/* -------------------- setter -------------------- */

	/**
	 * bankListExcelDecoratorを設定します。
	 * @param bankListExcelDecorator bankListExcelDecorator
	 */
	public void setBankListExcelDecorator(
			final BankListExcelDecorator bankListExcelDecorator) {
		this.bankListExcelDecorator = bankListExcelDecorator;
	}

	/**
	 * bankListLogicを設定します。
	 * @param bankListLogic bankListLogic
	 */
	public void setBankListLogic(final BankListLogic bankListLogic) {
		this.bankListLogic = bankListLogic;
	}
}
