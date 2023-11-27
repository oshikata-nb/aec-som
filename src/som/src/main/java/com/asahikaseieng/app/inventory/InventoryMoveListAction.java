/*
 * Created on 2007/12/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inventory;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.inventorymovelist.InventoryMoveList;
import com.asahikaseieng.dao.nonentity.inventorymovelist.InventoryMoveListPagerCondition;
import com.asahikaseieng.dao.nonentity.inventorymovelistforreport.InventoryMoveListConditionForReport;
import com.asahikaseieng.dao.nonentity.inventorymovelistforreport.InventoryMoveListForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 在庫移動入力一覧 Actionクラス.
 * @author tanaka
 */
public final class InventoryMoveListAction extends AbstractSearchAction {

	private InventoryMoveListLogic inventoryMoveListLogic;

	private InventoryMoveListExcelDecorator inventoryMoveListExcelDecorator;

	private static final String KG_CD = "1";

	/**
	 * コンストラクタ
	 */
	public InventoryMoveListAction() {
		super();
	}

	/**
	 * 初期処理.
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
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
	 * 検索処理.
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	protected ActionForward searchImpl(final ActionMapping mapping,
			final HttpServletRequest request, final AbstractSearchForm form)
			throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("search.");
		}

		InventoryMoveListForm frm = (InventoryMoveListForm) form;

		/* クリア */
		frm.setSearchList(new ArrayList<InventoryMoveList>());

		/* 検索条件を取得 */
		InventoryMoveListPagerCondition condition = (InventoryMoveListPagerCondition) frm
				.getPager().getPagerCondition();

		/* 検索条件をセット */
		condition.setSrhItemCd(frm.getSrhItemCd());
		condition.setSrhLocationCd(frm.getSrhLocationCd());

		/* 帳票(Excel)用に検索条件を保持 */
		InventoryMoveListConditionForReport reportCondition = new InventoryMoveListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setReportCondition(reportCondition);

		/* 明細取得 */
		frm.setSearchList(inventoryMoveListLogic.getList(condition));

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		for (int i = 0; i < frm.getSearchList().size(); i++) {
			/* 数値文字列に変換 */
			frm.getSearchList().get(i).setStrPackQty(
				checker.format(frm.getSearchList().get(i)
						.getUnitOfOperationManagement(), frm.getSearchList()
						.get(i).getPackQty()));
			frm.getSearchList().get(i).setStrFraction(
				checker.format(frm.getSearchList().get(i)
						.getUnitOfFractionManagement(), frm.getSearchList()
						.get(i).getFraction()));

			/* 総量の単位はKgなのでKg桁数設定をする */
			// frm.getSearchList().get(i).setStrInventoryQty(
			// checker.format(frm.getSearchList().get(i)
			// .getUnitOfOperationManagement(), frm.getSearchList()
			// .get(i).getInventoryQty()));
			frm.getSearchList().get(i).setStrInventoryQty(
				checker.format(KG_CD, frm.getSearchList().get(i)
						.getInventoryQty()));
		}

		return mapping.findForward("success");
	}

	/**
	 * 戻る処理.
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward back(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("back.");
		}

		InventoryMoveListForm frm = (InventoryMoveListForm) form;

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

		InventoryMoveListForm frm = (InventoryMoveListForm) form;

		/* 明細取得 */
		List<InventoryMoveListForReport> list = inventoryMoveListLogic
				.getListForReport(frm.getReportCondition());

		/* Excel作成 */
		FileDownloadInfo info = inventoryMoveListExcelDecorator.createReport(
			list, getLoginInfo(request).getTantoNm(), AecDateUtils
					.getCurrentTimestamp());

		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setExcelDownloadFlg(true);

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * inventoryMoveListLogicを設定します。
	 * @param inventoryMoveListLogic inventoryMoveListLogic
	 */
	public void setInventoryMoveListLogic(
			final InventoryMoveListLogic inventoryMoveListLogic) {
		this.inventoryMoveListLogic = inventoryMoveListLogic;
	}

	/**
	 * inventoryMoveListExcelDecoratorを設定します。
	 * @param inventoryMoveListExcelDecorator inventoryMoveListExcelDecorator
	 */
	public void setInventoryMoveListExcelDecorator(
			final InventoryMoveListExcelDecorator inventoryMoveListExcelDecorator) {
		this.inventoryMoveListExcelDecorator = inventoryMoveListExcelDecorator;
	}
}
