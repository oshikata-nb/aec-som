/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.login;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.loginlist.LoginList;
import com.asahikaseieng.dao.nonentity.master.loginlist.LoginListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.loginlistforreport.LoginListConditionForReport;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 担当者マスタ一覧 Actionクラス.
 * @author t0011036
 */
public final class LoginListAction extends AbstractSearchAction {

	private LoginListLogic tantoListLogic;

	private LoginListExcelDecorator tantoListExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public LoginListAction() {
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

		LoginListForm frm = (LoginListForm) form;

		HttpSession session = request.getSession(false);

		if (session != null) {
			/* 一般ユーザーの場合は自分のみ表示する */
			LoginInfo loginInfo = (LoginInfo) session
					.getAttribute(Constants.LOGIN_KEY);

			frm.setLoginTantoCd(loginInfo.getTantoCd());
			frm.setLoginAdminFlg(loginInfo.getAdminFlg());
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

		LoginListForm frm = (LoginListForm) form;

		/* クリア */
		frm.setSearchList(new ArrayList<LoginList>());

		/* 検索条件を取得 */
		LoginListPagerCondition condition = (LoginListPagerCondition) frm
				.getPager().getPagerCondition();

		/* 検索条件をセット */
		condition.setSrhOrganizationCd(frm.getSrhOrganizationCd());
		condition.setSrhTantoCd(frm.getSrhTantoCd());
		condition.setSrhPostId(frm.getSrhPostId());
		condition.setSrhLoginTantoCd(frm.getLoginTantoCd());
		condition.setSrhLoginAdminFlg(frm.getLoginAdminFlg());

		if (!frm.getTantoSelCondition().equals("1")) {
			condition.setSrhNoBelong(null);
		} else {
			/* 未所属のみ検索の場合 */
			condition.setSrhNoBelong("未所属");
		}

		/* 帳票(Excel)用に検索条件を保持 */
		LoginListConditionForReport reportCondition = new LoginListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setCondition(reportCondition);

		/* 明細取得 */
		frm.setSearchList(tantoListLogic.getList(condition));

		return mapping.findForward("success");
	}

	/**
	 * フォームに表示されている項目のクリア処理.
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

		LoginListForm frm = (LoginListForm) form;

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

		LoginListForm frm = (LoginListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = tantoListExcelDecorator.createReport(frm
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
	 * tantoListLogicを設定します。
	 * @param tantoListLogic tantoListLogic
	 */
	public void setTantoListLogic(final LoginListLogic tantoListLogic) {
		this.tantoListLogic = tantoListLogic;
	}

	/**
	 * tantoListExcelDecoratorを設定します。
	 * @param tantoListExcelDecorator tantoListExcelDecorator
	 */
	public void setTantoListExcelDecorator(
			final LoginListExcelDecorator tantoListExcelDecorator) {
		this.tantoListExcelDecorator = tantoListExcelDecorator;
	}
}
