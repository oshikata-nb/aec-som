/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.carryfile;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.carryfilelist.CarryFileList;
import com.asahikaseieng.dao.nonentity.master.carryfilelist.CarryFileListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.carryfilelistforreport.CarryFileListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 運送会社一覧 Actionクラス.
 * @author t0011036
 */
public final class CarryFileListAction extends AbstractSearchAction {

	private CarryFileListLogic carryListLogic;

	private CarryFileListExcelDecorator carryFileListExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public CarryFileListAction() {
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
		
		CarryFileListForm frm = (CarryFileListForm) form;
		
		/* 検索条件を取得 */
		CarryFileListPagerCondition condition = (CarryFileListPagerCondition) frm
				.getPager().getPagerCondition();

		/* 帳票(Excel)用に検索条件を保持 */
		CarryFileListConditionForReport reportCondition = new CarryFileListConditionForReport();
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

		CarryFileListForm frm = (CarryFileListForm) form;

		/* クリア */
		frm.setSearchList(new ArrayList<CarryFileList>());

		/* 検索条件を取得 */
		CarryFileListPagerCondition condition = (CarryFileListPagerCondition) frm
				.getPager().getPagerCondition();

		/* 検索条件をセット */
		condition.setSrhCarryCd(frm.getSrhCarryCd());
		condition.setSrhExistsSetting(frm.getSrhExistsSetting());
		
		/* 帳票(Excel)用に検索条件を保持 */
		CarryFileListConditionForReport reportCondition = new CarryFileListConditionForReport();
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

		CarryFileListForm frm = (CarryFileListForm) form;

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

		CarryFileListForm frm = (CarryFileListForm) form;


		/* Excel作成 */
		FileDownloadInfo info = this.carryFileListExcelDecorator.createReport(frm
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
	 * carryListLogicを設定します。
	 * @param carryListLogic carryListLogic
	 */
	public void setCarryListLogic(final CarryFileListLogic carryListLogic) {
		this.carryListLogic = carryListLogic;
	}

	/**
	 * carryFileListExcelDecoratorを設定します。
	 * @param carryFileListExcelDecorator carryFileListExcelDecorator
	 */
	public void setCarryFileListExcelDecorator(
			CarryFileListExcelDecorator carryFileListExcelDecorator) {
		this.carryFileListExcelDecorator = carryFileListExcelDecorator;
	}
}
