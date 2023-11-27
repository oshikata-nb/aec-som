/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.itemcategory;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.itemcategorylist.ItemCategoryList;
import com.asahikaseieng.dao.nonentity.master.itemcategorylist.ItemCategoryListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.itemcategorylistforreport.ItemCategoryListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 品目分類一覧 Actionクラス.
 * @author t0011036
 */
public final class ItemCategoryListAction extends AbstractSearchAction {

	private ItemCategoryListLogic itemCategoryListLogic;

	private ItemCategoryListExcelDecorator itemCategoryListExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public ItemCategoryListAction() {
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

		ItemCategoryListForm frm = (ItemCategoryListForm) form;

		/* クリア */
		frm.setSearchList(new ArrayList<ItemCategoryList>());

		/* 検索条件を取得 */
		ItemCategoryListPagerCondition condition = (ItemCategoryListPagerCondition) frm
				.getPager().getPagerCondition();

		/* 検索条件をセット */
		condition.setSrhItemCategory(frm.getSrhItemCategory());

		/* 帳票(Excel)用に検索条件を保持 */
		ItemCategoryListConditionForReport reportCondition = new ItemCategoryListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setCondition(reportCondition);

		/* 明細取得 */
		frm.setSearchList(itemCategoryListLogic.getList(condition));

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

		ItemCategoryListForm frm = (ItemCategoryListForm) form;

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

		ItemCategoryListForm frm = (ItemCategoryListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = itemCategoryListExcelDecorator.createReport(frm
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
	 * itemCategoryListExcelDecoratorを設定します。
	 * @param itemCategoryListExcelDecorator itemCategoryListExcelDecorator
	 */
	public void setItemCategoryListExcelDecorator(
			final ItemCategoryListExcelDecorator itemCategoryListExcelDecorator) {
		this.itemCategoryListExcelDecorator = itemCategoryListExcelDecorator;
	}

	/**
	 * itemCategoryListLogicを設定します。
	 * @param itemCategoryListLogic itemCategoryListLogic
	 */
	public void setItemCategoryListLogic(
			final ItemCategoryListLogic itemCategoryListLogic) {
		this.itemCategoryListLogic = itemCategoryListLogic;
	}
}
