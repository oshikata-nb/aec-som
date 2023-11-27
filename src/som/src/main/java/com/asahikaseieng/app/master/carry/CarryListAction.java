/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.carry;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.carrylist.CarryList;
import com.asahikaseieng.dao.nonentity.master.carrylist.CarryListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.carrylistforreport.CarryListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 運送会社一覧 Actionクラス.
 * @author t0011036
 */
public final class CarryListAction extends AbstractSearchAction {

	private CarryListLogic carryListLogic;

	private CarryListExcelDecorator carryListExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public CarryListAction() {
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
		
		CarryListForm frm = (CarryListForm) form;
		
		/* 検索条件を取得 */
		CarryListPagerCondition condition = (CarryListPagerCondition) frm
				.getPager().getPagerCondition();

		/* 帳票(Excel)用に検索条件を保持 */
		CarryListConditionForReport reportCondition = new CarryListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setCondition(reportCondition);

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

		CarryListForm frm = (CarryListForm) form;

		/* クリア */
		frm.setSearchList(new ArrayList<CarryList>());

		/* 検索条件を取得 */
		CarryListPagerCondition condition = (CarryListPagerCondition) frm
				.getPager().getPagerCondition();

		/* 検索条件をセット */
		condition.setSrhCarryCd(frm.getSrhCarryCd());

		/* 帳票(Excel)用に検索条件を保持 */
		CarryListConditionForReport reportCondition = new CarryListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setCondition(reportCondition);

		/* 明細取得 */
		frm.setSearchList(carryListLogic.getList(condition));

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

		CarryListForm frm = (CarryListForm) form;

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

		CarryListForm frm = (CarryListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = carryListExcelDecorator.createReport(frm
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
	 * carryListExcelDecoratorを設定します。
	 * @param carryListExcelDecorator carryListExcelDecorator
	 */
	public void setCarryListExcelDecorator(
			final CarryListExcelDecorator carryListExcelDecorator) {
		this.carryListExcelDecorator = carryListExcelDecorator;
	}

	/**
	 * carryListLogicを設定します。
	 * @param carryListLogic carryListLogic
	 */
	public void setCarryListLogic(final CarryListLogic carryListLogic) {
		this.carryListLogic = carryListLogic;
	}
}
