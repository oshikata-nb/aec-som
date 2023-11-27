/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.unitprice;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.unitpriceiistforreport.UnitpriceListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.unitpricelist.UnitpriceList;
import com.asahikaseieng.dao.nonentity.master.unitpricelist.UnitpriceListPagerCondition;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 仕入先別単価一覧 Actionクラス.
 * @author t0011036
 */
public final class UnitpriceListAction extends AbstractSearchAction {

	private UnitpriceListLogic unitpriceListLogic;

	private UnitpriceListExcelDecorator unitpriceListExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public UnitpriceListAction() {
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

		UnitpriceListForm frm = (UnitpriceListForm) form;

		/* クリア */
		frm.setSearchList(new ArrayList<UnitpriceList>());

		/* 検索条件を取得 */
		UnitpriceListPagerCondition condition = (UnitpriceListPagerCondition) frm
				.getPager().getPagerCondition();

		/* 検索条件をセット */
		condition.setSrhVenderDivision(frm.getSrhVenderDivision());
		condition.setSrhVenderCd(frm.getSrhVenderCd());
		condition.setSrhItemCd(frm.getSrhItemCd());
		condition.setSrhOtherCompanyCd1(frm.getSrhOtherCompanyCd1());

		/* 帳票(Excel)用に検索条件を保持 */
		UnitpriceListConditionForReport reportCondition = new UnitpriceListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setCondition(reportCondition);

		/* 初期検索 */
		List<UnitpriceList> list = unitpriceListLogic.getList(condition);

		for (int i = 0; i < list.size(); i++) {
			/* 数値文字列に変換 */
			list.get(i).setStrVersion(list.get(i).getVersion().toString());

			/* 日付文字列に変換 */
			list.get(i).setStrValidDate(
				AecDateUtils.dateFormat(list.get(i).getValidDate(),
					"yyyy/MM/dd"));
		}

		/* 明細取得 */
		frm.setSearchList(list);

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

		UnitpriceListForm frm = (UnitpriceListForm) form;

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

		UnitpriceListForm frm = (UnitpriceListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = unitpriceListExcelDecorator.createReport(frm
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
	 * unitpriceListExcelDecoratorを設定します。
	 * @param unitpriceListExcelDecorator unitpriceListExcelDecorator
	 */
	public void setUnitpriceListExcelDecorator(
			final UnitpriceListExcelDecorator unitpriceListExcelDecorator) {
		this.unitpriceListExcelDecorator = unitpriceListExcelDecorator;
	}

	/**
	 * unitpriceListLogicを設定します。
	 * @param unitpriceListLogic unitpriceListLogic
	 */
	public void setUnitpriceListLogic(
			final UnitpriceListLogic unitpriceListLogic) {
		this.unitpriceListLogic = unitpriceListLogic;
	}
}
