/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.balance;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.balancelist.BalanceList;
import com.asahikaseieng.dao.nonentity.master.balancelist.BalanceListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.balancelistdetail.BalanceListDetail;
import com.asahikaseieng.dao.nonentity.master.balancelistforreport.BalanceListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 帳合一覧 Actionクラス.
 * @author t0011036
 */
public final class BalanceListAction extends AbstractSearchAction {

	private BalanceListLogic balanceListLogic;

	private BalanceListExcelDecorator balanceListExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public BalanceListAction() {
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

		BalanceListForm frm = (BalanceListForm) form;

		/* クリア */
		frm.setSearchList(new ArrayList<BalanceList>());

		/* 検索条件を取得 */
		BalanceListPagerCondition condition = (BalanceListPagerCondition) frm
				.getPager().getPagerCondition();

		/* 検索条件をセット */
		condition.setSrhBalanceType(frm.getSrhBalanceType());
		condition.setSrhVenderCd(frm.getSrhVenderCd());

		/* 帳票(Excel)用に検索条件を保持 */
		BalanceListConditionForReport reportCondition = new BalanceListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setCondition(reportCondition);

		/* 明細取得 */
		frm.setSearchList(balanceListLogic.getList(condition));

		BalanceListDetail bean = new BalanceListDetail();

		/* 明細詳細取得 */
		for (int i = 0; i < frm.getSearchList().size(); i++) {
			/* 帳合一覧詳細取得 */
			bean = balanceListLogic.getEntity(frm.getSearchList().get(i)
					.getBalanceCd(), frm.getSearchList().get(i)
					.getBalanceType());

			if (bean == null) {
				frm.getSearchList().get(i).setBalanceTypeName("未設定");
			} else {
				frm.getSearchList().get(i).setBalanceTypeName(
					bean.getBalanceTypeName());
				frm.getSearchList().get(i)
						.setVenderName1(bean.getVenderName1());
				frm.getSearchList().get(i).setDispVenderName1(
					bean.getDispVenderName1());
				frm.getSearchList().get(i)
						.setVenderName2(bean.getVenderName2());
				frm.getSearchList().get(i).setDispVenderName2(
					bean.getDispVenderName2());
				frm.getSearchList().get(i)
						.setVenderName3(bean.getVenderName3());
				frm.getSearchList().get(i).setDispVenderName3(
					bean.getDispVenderName3());
				frm.getSearchList().get(i)
						.setVenderName4(bean.getVenderName4());
				frm.getSearchList().get(i).setDispVenderName4(
					bean.getDispVenderName4());
				frm.getSearchList().get(i)
						.setVenderName5(bean.getVenderName5());
				frm.getSearchList().get(i).setDispVenderName5(
					bean.getDispVenderName5());
			}
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

		BalanceListForm frm = (BalanceListForm) form;

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

		BalanceListForm frm = (BalanceListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = balanceListExcelDecorator.createReport(frm
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
	 * balanceListExcelDecoratorを設定します。
	 * @param balanceListExcelDecorator balanceListExcelDecorator
	 */
	public void setBalanceListExcelDecorator(
			final BalanceListExcelDecorator balanceListExcelDecorator) {
		this.balanceListExcelDecorator = balanceListExcelDecorator;
	}

	/**
	 * balanceListLogicを設定します。
	 * @param balanceListLogic balanceListLogic
	 */
	public void setBalanceListLogic(final BalanceListLogic balanceListLogic) {
		this.balanceListLogic = balanceListLogic;
	}
}
