/*
 * Created on 2007/08/08
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.bumon;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.bumonlist.BumonList;
import com.asahikaseieng.dao.nonentity.master.bumonlist.BumonListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.bumonlistforreport.BumonListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * Actionクラス. 会計部門マスタ一覧
 * @author tanaka
 */
public final class BumonListAction extends AbstractSearchAction {

	private BumonListLogic bumonListLogic;

	private BumonListExcelDecorator bumonListExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public BumonListAction() {
		super();
	}

	/**
	 * 初期処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
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
	 * 検索処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	protected ActionForward searchImpl(final ActionMapping mapping,
			final HttpServletRequest request, final AbstractSearchForm form)
			throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("search.");
		}

		BumonListForm frm = (BumonListForm) form;

		/* クリア */
		frm.setSearchList(new ArrayList<BumonList>());

		/* 検索条件を取得 */
		BumonListPagerCondition condition = (BumonListPagerCondition) frm
				.getPager().getPagerCondition();

		/* 検索条件をセット */
		condition.setSrhSectionCd(frm.getSrhSectionCd());

		/* 帳票(Excel)用に検索条件を保持 */
		BumonListConditionForReport reportCondition = new BumonListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setCondition(reportCondition);

		/* 明細取得 */
		frm.setSearchList(bumonListLogic.getList(condition));

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

		BumonListForm frm = (BumonListForm) form;

		/* クリア */
		frm.clear();

		return mapping.findForward("success");
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionForward newPage(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("newPage.");
		}

		return mapping.findForward("newPage");
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

		BumonListForm frm = (BumonListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = bumonListExcelDecorator.createReport(frm
				.getCondition());

		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setExcelDownloadFlg(true);

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * bumonListLogicを設定します。
	 * @param bumonListLogic bumonListLogic
	 */
	public void setBumonListLogic(final BumonListLogic bumonListLogic) {
		this.bumonListLogic = bumonListLogic;
	}

	/**
	 * bumonListExcelDecoratorを設定します。
	 * @param bumonListExcelDecorator bumonListExcelDecorator
	 */
	public void setBumonListExcelDecorator(
			final BumonListExcelDecorator bumonListExcelDecorator) {
		this.bumonListExcelDecorator = bumonListExcelDecorator;
	}
}
