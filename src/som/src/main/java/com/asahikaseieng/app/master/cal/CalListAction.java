/*
 * Created on 2008/07/17
 *
 * $copyright$
 */
package com.asahikaseieng.app.master.cal;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.callist.CalList;
import com.asahikaseieng.dao.nonentity.master.callist.CalListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.callistforreport.CalListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * カレンダー一覧 Actionクラス.
 * @author tosco
 */
public final class CalListAction extends AbstractSearchAction {

	private CalListLogic calendarListLogic;

	private CalListExcelDecorator calendarListExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public CalListAction() {
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

		CalListForm frm = (CalListForm) form;

		/* クリア */
		frm.setSearchList(new ArrayList<CalList>());

		/* 検索条件を取得 */
		CalListPagerCondition condition = (CalListPagerCondition) frm
				.getPager().getPagerCondition();

		/* 検索条件をセット */
		condition.setSrhCalCd(frm.getSrhCalCd());
		condition.setSrhCalYear(frm.getSrhCalYear());

		/* 帳票(Excel)用に検索条件を保持 */
		CalListConditionForReport reportCondition = new CalListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setCondition(reportCondition);

		/* 明細取得 */
		frm.setSearchList(calendarListLogic.getList(condition));

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

		CalListForm frm = (CalListForm) form;

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

		CalListForm frm = (CalListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = calendarListExcelDecorator.createReport(frm
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
	 * calendarListExcelDecoratorを設定します。
	 * @param calendarListExcelDecorator calendarListExcelDecorator
	 */
	public void setCalendarListExcelDecorator(
			final CalListExcelDecorator calendarListExcelDecorator) {
		this.calendarListExcelDecorator = calendarListExcelDecorator;
	}

	/**
	 * calendarListLogicを設定します。
	 * @param calendarListLogic calendarListLogic
	 */
	public void setCalendarListLogic(final CalListLogic calendarListLogic) {
		this.calendarListLogic = calendarListLogic;
	}
}
