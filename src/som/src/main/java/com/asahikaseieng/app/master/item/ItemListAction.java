/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.item;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.itemqueuelist.ItemQueueList;
import com.asahikaseieng.dao.nonentity.master.itemqueuelist.ItemQueueListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.itemqueuelistforreport.ItemQueueListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 品目一覧 Actionクラス.
 * @author t0011036
 */
public final class ItemListAction extends AbstractSearchAction {

	private ItemListLogic itemListLogic;

	private ItemListExcelDecorator itemListExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public ItemListAction() {
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

		ItemListForm frm = (ItemListForm) form;

		/* クリア */
		frm.setSearchList(new ArrayList<ItemQueueList>());

		/* 検索条件を取得 */
		ItemQueueListPagerCondition condition = (ItemQueueListPagerCondition) frm
				.getPager().getPagerCondition();

		/* 文字列日付 ---> 日付 */
		frm.setSrhActiveDateFrom(AecDateUtils.getTimestampYmdFormat(frm
				.getStrSrhActiveDateFrom()));
		frm.setSrhActiveDateTo(AecDateUtils.getTimestampYmdFormat(frm
				.getStrSrhActiveDateTo()));

		/* 検索条件をセット */
		condition.setSrhItemCd(frm.getSrhItemCd());
		condition.setSrhParentItemCd(frm.getSrhParentItemCd());
		condition.setSrhOtherCompanyCd1(frm.getSrhOtherCompanyCd1());
		condition.setSrhStatus(frm.getSrhStatus());
		condition.setSrhDetailStatus(frm.getSrhDetailStatus());
		condition.setStrSrhActiveDateFrom(frm.getStrSrhActiveDateFrom());
		condition.setStrSrhActiveDateTo(frm.getStrSrhActiveDateTo());

		/* 帳票(Excel)用に検索条件を保持 */
		ItemQueueListConditionForReport reportCondition = new ItemQueueListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setCondition(reportCondition);

		/* 明細取得 */
		frm.setSearchList(itemListLogic.getList(condition));

		for (int i = 0; i < frm.getSearchList().size(); i++) {
			/* 日付 ---> 文字列日付 */
			frm.getSearchList().get(i).setStrActiveDate(
				AecDateUtils.dateFormat(frm.getSearchList().get(i)
						.getActiveDate(), "yyyy/MM/dd"));
		}

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

		ItemListForm frm = (ItemListForm) form;

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

		ItemListForm frm = (ItemListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = itemListExcelDecorator.createReport(frm
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
	 * itemListExcelDecoratorを設定します。
	 * @param itemListExcelDecorator itemListExcelDecorator
	 */
	public void setItemListExcelDecorator(
			final ItemListExcelDecorator itemListExcelDecorator) {
		this.itemListExcelDecorator = itemListExcelDecorator;
	}

	/**
	 * itemListLogicを設定します。
	 * @param itemListLogic itemListLogic
	 */
	public void setItemListLogic(final ItemListLogic itemListLogic) {
		this.itemListLogic = itemListLogic;
	}
}
