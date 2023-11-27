/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.reciperesoucegroup;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.reciperesoucegrouplist.RecipeResouceGroupList;
import com.asahikaseieng.dao.nonentity.master.reciperesoucegrouplist.RecipeResouceGroupListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.reciperesoucegrouplistforreport.RecipeResouceGroupListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 設備グループ一覧 Actionクラス.
 * @author t0011036
 */
public final class RecipeResouceGroupListAction extends AbstractSearchAction {

	private RecipeResouceGroupListLogic recipeResouceGroupListLogic;

	private RecipeResouceGroupListExcelDecorator recipeResouceGroupListExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public RecipeResouceGroupListAction() {
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

		RecipeResouceGroupListForm frm = (RecipeResouceGroupListForm) form;

		/* クリア */
		frm.setSearchList(new ArrayList<RecipeResouceGroupList>());

		/* 検索条件を取得 */
		RecipeResouceGroupListPagerCondition condition = (RecipeResouceGroupListPagerCondition) frm
				.getPager().getPagerCondition();

		/* 検索条件をセット */
		condition.setSrhResouceGroupCd(frm.getSrhResouceGroupCd());

		/* 帳票(Excel)用に検索条件を保持 */
		RecipeResouceGroupListConditionForReport reportCondition = new RecipeResouceGroupListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setCondition(reportCondition);

		/* 明細取得 */
		frm.setSearchList(recipeResouceGroupListLogic.getList(condition));

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

		RecipeResouceGroupListForm frm = (RecipeResouceGroupListForm) form;

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

		RecipeResouceGroupListForm frm = (RecipeResouceGroupListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = recipeResouceGroupListExcelDecorator
				.createReport(frm.getCondition());

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
	 * recipeResouceGroupListExcelDecoratorを設定します。
	 * @param recipeResouceGroupListExcelDecorator
	 *            recipeResouceGroupListExcelDecorator
	 */
	public void setRecipeResouceGroupListExcelDecorator(
			final RecipeResouceGroupListExcelDecorator recipeResouceGroupListExcelDecorator) {
		this.recipeResouceGroupListExcelDecorator = recipeResouceGroupListExcelDecorator;
	}

	/**
	 * recipeResouceGroupListLogicを設定します。
	 * @param recipeResouceGroupListLogic recipeResouceGroupListLogic
	 */
	public void setRecipeResouceGroupListLogic(
			final RecipeResouceGroupListLogic recipeResouceGroupListLogic) {
		this.recipeResouceGroupListLogic = recipeResouceGroupListLogic;
	}
}
