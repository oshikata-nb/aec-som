package com.asahikaseieng.app.inout;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.inoutmaterial.InoutMaterial;
import com.asahikaseieng.dao.nonentity.inoutmaterial.InoutMaterialCondition;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 原材料別入出庫一覧表 Actionクラス
 * @author t1344224
 */
public final class InoutMaterialAction extends AbstractSearchAction {

	/**
	 * コンストラクタ.
	 */
	public InoutMaterialAction() {
		super();
	}

	private InoutMaterialLogic inoutMaterialLogic;

	private InoutMaterialExcelDecorator inoutMaterialExcelDecorator;

	/**
	 * 初期処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	protected ActionForward initImpl(final ActionMapping mapping,
			final AbstractSearchForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}
		InoutMaterialForm frm = (InoutMaterialForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_INOUT_MATERIAL,
			Constants.TAB_ID_INOUT_MATERIAL_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		return mapping.findForward("success");
	}

	/**
	 * 検索処理(検索ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	protected ActionForward searchImpl(final ActionMapping mapping,
			final HttpServletRequest request, final AbstractSearchForm form)
			throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("search.");
		}
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
		InoutMaterialForm frm = (InoutMaterialForm) form;

		InoutMaterialCondition condition = new InoutMaterialCondition();
		FileDownloadInfo info = null;

		condition.setSrhBalanceType(frm.getSrhBalanceType());
		condition.setSrhDirectionDivision(frm.getSrhDirectionDivision());
		condition.setSrhShipperDivision(frm.getSrhShipperDivision());
		condition.setSrhStyleOfPacking(AecTextUtils.likeFilter(frm
				.getSrhStyleOfPacking(), true));

		String strDateFrom = frm.getSrhDateFrom().replace("/", "").substring(0,
			4)
				+ frm.getSrhDateFrom().replace("/", "").substring(4, 6);
		String strDateTo = frm.getSrhDateTo().replace("/", "").substring(0, 4)
				+ frm.getSrhDateTo().replace("/", "").substring(4, 6);

		condition.setSrhDateFrom(new BigDecimal(strDateFrom)); // 範囲開始月
		condition.setSrhDateTo(new BigDecimal(strDateTo)); // 範囲終了月

		int prevDate = 0;

		prevDate = new BigDecimal(strDateFrom).intValue() - 1;

		if ((prevDate % 10) == 0) {
			prevDate = prevDate - 100 + 12;
		}

		condition.setSrhDatePrev(new BigDecimal(prevDate)); // 前残用

		List<InoutMaterial> list = inoutMaterialLogic.getList(condition);

		if (!list.isEmpty()) {

			info = inoutMaterialExcelDecorator.createReport(frm
					.getSrhDateFrom().replace("/", "年")
					+ "月", frm.getSrhDateTo().replace("/", "年") + "月", list);

			request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
				info);
			frm.setExcelDownloadFlg(Boolean.TRUE);
		} else {
			// 月末在庫データが無い場合
			saveError(request, "errors.nodata");

		}
		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */
	/**
	 * inoutMaterialLogicを設定します。
	 * @param inoutMaterialLogic inoutMaterialLogic
	 */
	public void setInoutMaterialLogic(
			final InoutMaterialLogic inoutMaterialLogic) {
		this.inoutMaterialLogic = inoutMaterialLogic;
	}

	/**
	 * inoutMaterialExcelDecoratorを設定します。
	 * @param inoutMaterialExcelDecorator inoutMaterialExcelDecorator
	 */
	public void setInoutMaterialExcelDecorator(
			final InoutMaterialExcelDecorator inoutMaterialExcelDecorator) {
		this.inoutMaterialExcelDecorator = inoutMaterialExcelDecorator;
	}

}
