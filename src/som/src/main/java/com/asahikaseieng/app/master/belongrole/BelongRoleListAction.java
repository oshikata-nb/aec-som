/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.belongrole;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.belongrolelist.BelongRoleList;
import com.asahikaseieng.dao.nonentity.master.belongrolelist.BelongRoleListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.belongrolelistforreport.BelongRoleListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 所属・ロール組合せ一覧 Actionクラス.
 * @author t0011036
 */
public final class BelongRoleListAction extends AbstractSearchAction {

	private BelongRoleListLogic belongRoleListLogic;

	private BelongRoleListExcelDecorator belongRoleListExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public BelongRoleListAction() {
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

		BelongRoleListForm frm = (BelongRoleListForm) form;

		/* クリア */
		frm.setSearchList(new ArrayList<BelongRoleList>());

		/* 検索条件を取得 */
		BelongRoleListPagerCondition condition = (BelongRoleListPagerCondition) frm
				.getPager().getPagerCondition();

		/* 検索条件をセット */
		condition.setSrhOrganizationCd(frm.getSrhOrganizationCd());
		condition.setSrhPostId(frm.getSrhPostId());
		condition.setSrhRoleId(frm.getSrhRoleId());

		/* 帳票(Excel)用に検索条件を保持 */
		BelongRoleListConditionForReport reportCondition = new BelongRoleListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setCondition(reportCondition);

		/* 明細取得 */
		frm.setSearchList(belongRoleListLogic.getList(condition));

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

		BelongRoleListForm frm = (BelongRoleListForm) form;

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

		BelongRoleListForm frm = (BelongRoleListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = belongRoleListExcelDecorator.createReport(frm
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
	 * belongRoleListExcelDecoratorを設定します。
	 * @param belongRoleListExcelDecorator belongRoleListExcelDecorator
	 */
	public void setBelongRoleListExcelDecorator(
			final BelongRoleListExcelDecorator belongRoleListExcelDecorator) {
		this.belongRoleListExcelDecorator = belongRoleListExcelDecorator;
	}

	/**
	 * belongRoleListLogicを設定します。
	 * @param belongRoleListLogic belongRoleListLogic
	 */
	public void setBelongRoleListLogic(
			final BelongRoleListLogic belongRoleListLogic) {
		this.belongRoleListLogic = belongRoleListLogic;
	}
}
