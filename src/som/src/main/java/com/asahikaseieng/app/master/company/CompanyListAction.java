/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.company;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.companylist.CompanyList;
import com.asahikaseieng.dao.nonentity.master.companylist.CompanyListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.companylistforreport.CompanyListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 自社一覧 Actionクラス.
 * @author t0011036
 */
public final class CompanyListAction extends AbstractSearchAction {

	private CompanyListLogic companyListLogic;

	private CompanyListExcelDecorator companyListExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public CompanyListAction() {
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

		CompanyListForm frm = (CompanyListForm) form;

		/* クリア */
		frm.setSearchList(new ArrayList<CompanyList>());

		/* 検索条件を取得 */
		CompanyListPagerCondition condition = (CompanyListPagerCondition) frm
				.getPager().getPagerCondition();

		/* 検索条件をセット */
		condition.setSrhHomeName1(frm.getSrhHomeName1());

		/* 帳票(Excel)用に検索条件を保持 */
		CompanyListConditionForReport reportCondition = new CompanyListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setCondition(reportCondition);

		/* 明細取得 */
		frm.setSearchList(companyListLogic.getList(condition));

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

		CompanyListForm frm = (CompanyListForm) form;

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

		CompanyListForm frm = (CompanyListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = companyListExcelDecorator.createReport(frm
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

		/* 登録チェック */
		if (companyListLogic.checkUpdateData()) {
			/* エラーメッセージ */
			saveError(request, "errors.company.duplicate.data");
			return mapping.findForward("success");
		}

		return mapping.findForward("newPage");
	}

	/* -------------------- setter -------------------- */

	/**
	 * companyListExcelDecoratorを設定します。
	 * @param companyListExcelDecorator companyListExcelDecorator
	 */
	public void setCompanyListExcelDecorator(
			final CompanyListExcelDecorator companyListExcelDecorator) {
		this.companyListExcelDecorator = companyListExcelDecorator;
	}

	/**
	 * companyListLogicを設定します。
	 * @param companyListLogic companyListLogic
	 */
	public void setCompanyListLogic(final CompanyListLogic companyListLogic) {
		this.companyListLogic = companyListLogic;
	}
}
