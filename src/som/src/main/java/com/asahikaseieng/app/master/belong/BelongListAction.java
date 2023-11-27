/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.belong;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.belonglist.BelongList;
import com.asahikaseieng.dao.nonentity.master.belonglist.BelongListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.belonglistforreport.BelongListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 所属一覧 Actionクラス.
 * @author t0011036
 */
public final class BelongListAction extends AbstractSearchAction {

	private BelongListLogic belongListLogic;

	private BelongListExcelDecorator belongListExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public BelongListAction() {
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

		BelongListForm frm = (BelongListForm) form;

		/* クリア */
		frm.setSearchList(new ArrayList<BelongList>());

		/* 検索条件を取得 */
		BelongListPagerCondition condition = (BelongListPagerCondition) frm
				.getPager().getPagerCondition();

		/* 検索条件をセット */
		condition.setSrhOrganizationCd(frm.getSrhOrganizationCd());
		condition.setSrhTantoCd(frm.getSrhTantoCd());
		condition.setSrhPostId(frm.getSrhPostId());

		/* 帳票(Excel)用に検索条件を保持 */
		BelongListConditionForReport reportCondition = new BelongListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setCondition(reportCondition);

		/* 明細取得 */
		frm.setSearchList(belongListLogic.getList(condition));

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

		BelongListForm frm = (BelongListForm) form;

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

		BelongListForm frm = (BelongListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = belongListExcelDecorator.createReport(frm
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
	 * belongListExcelDecoratorを設定します。
	 * @param belongListExcelDecorator belongListExcelDecorator
	 */
	public void setBelongListExcelDecorator(
			final BelongListExcelDecorator belongListExcelDecorator) {
		this.belongListExcelDecorator = belongListExcelDecorator;
	}

	/**
	 * belongListLogicを設定します。
	 * @param belongListLogic belongListLogic
	 */
	public void setBelongListLogic(final BelongListLogic belongListLogic) {
		this.belongListLogic = belongListLogic;
	}
}
