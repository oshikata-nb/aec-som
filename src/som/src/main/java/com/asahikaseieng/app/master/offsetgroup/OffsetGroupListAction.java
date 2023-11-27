/*
 * Created on 2008/06/18
 *
 * $copyright$
 * tosco:相殺グループマスタ
 */
package com.asahikaseieng.app.master.offsetgroup;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.offsetgrouplist.OffsetGroupList;
import com.asahikaseieng.dao.nonentity.master.offsetgrouplist.OffsetGroupListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.offsetgrouplistforreport.OffsetGroupListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 相殺グループマスタ一覧 Actionクラス.
 * @author tosco
 */
public final class OffsetGroupListAction extends AbstractSearchAction {

	private OffsetGroupListLogic offsetGroupListLogic;

	private OffsetGroupListExcelDecorator offsetGroupListExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public OffsetGroupListAction() {
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

		OffsetGroupListForm frm = (OffsetGroupListForm) form;

		/* クリア */
		frm.setSearchList(new ArrayList<OffsetGroupList>());

		/* 検索条件を取得 */
		OffsetGroupListPagerCondition condition = (OffsetGroupListPagerCondition) frm
				.getPager().getPagerCondition();

		/* 検索条件をセット */
		condition.setSrhOffsetGroupCd(frm.getSrhOffsetGroupCd());

		/* 帳票(Excel)用に検索条件を保持 */
		OffsetGroupListConditionForReport reportCondition = new OffsetGroupListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setCondition(reportCondition);

		frm.setSearchList(offsetGroupListLogic.getSearchList(condition));

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

		OffsetGroupListForm frm = (OffsetGroupListForm) form;

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

		OffsetGroupListForm frm = (OffsetGroupListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = offsetGroupListExcelDecorator.createReport(frm
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
	 * offsetGroupListLogicを設定します。
	 * 
	 * @param offsetGroupListLogic offsetGroupListLogic
	 */
	public void setOffsetGroupListLogic(
			final OffsetGroupListLogic offsetGroupListLogic) {
		this.offsetGroupListLogic = offsetGroupListLogic;
	}

	/**
	 * offsetGroupListExcelDecoratorを設定します。
	 * @param offsetGroupListExcelDecorator offsetGroupListExcelDecorator
	 */
	public void setOffsetGroupListExcelDecorator(
			final OffsetGroupListExcelDecorator offsetGroupListExcelDecorator) {
		this.offsetGroupListExcelDecorator = offsetGroupListExcelDecorator;
	}
}
