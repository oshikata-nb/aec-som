package com.asahikaseieng.app.direction;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.directionmaterialusedforreport.RepDirectionMaterialUsed;
import com.asahikaseieng.dao.nonentity.directionmaterialusedforreport.RepDirectionMaterialUsedCondition;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 製品別原材料消費量リスト Actionクラス
 * @author t1344224
 */
public final class DirectionMaterialUsedAction extends AbstractSearchAction {

	private DirectionMaterialUsedLogic directionMaterialUsedLogic;

	/** 製品別原材料消費量リストＥＸＣＥＬファイル作成ロジッククラス */
	private DirectionMaterialUsedExcelDecorator directionMaterialUsedExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public DirectionMaterialUsedAction() {
		super();
	}

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

		DirectionMaterialUsedForm frm = (DirectionMaterialUsedForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm,
			Constants.MENU_ID_DIRECTION_MATERIAL_USED,
			Constants.TAB_ID_DIRECTION_MATERIAL_USED_DETAIL);

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

		DirectionMaterialUsedForm frm = (DirectionMaterialUsedForm) form;

		String tantoNm = getLoginInfo(request).getTantoNm();
		FileDownloadInfo info = null;

		RepDirectionMaterialUsedCondition condition = new RepDirectionMaterialUsedCondition();

		condition.setSrhItemCd(frm.getSrhItemCd());
		condition.setSrhOtherCompanyCd1(frm.getSrhOtherCompanyCd1());
		condition.setSrhBalanceType(frm.getSrhBalanceType());
		condition.setSrhDirectionDivision(frm.getSrhDirectionDivision());
		condition.setSrhShipperDivision(frm.getSrhShipperDivision());

		String strDateFrom = frm.getSrhDateFrom().replace("/", "").substring(0,
			4)
				+ frm.getSrhDateFrom().replace("/", "").substring(4, 6);
		String strDateTo = frm.getSrhDateTo().replace("/", "").substring(0, 4)
				+ frm.getSrhDateTo().replace("/", "").substring(4, 6);

		condition.setSrhDateFrom(new BigDecimal(strDateFrom)); // 範囲開始月
		condition.setSrhDateTo(new BigDecimal(strDateTo)); // 範囲終了月

		List<RepDirectionMaterialUsed> list = directionMaterialUsedLogic
				.getList(condition);

		if (!list.isEmpty()) {

			// 製品別原材料消費量リストを作成
			info = directionMaterialUsedExcelDecorator.createReport(list, frm
					.getSrhDateFrom(), frm.getSrhDateTo(), tantoNm,
				AecDateUtils.getCurrentTimestamp());

			request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
				info);
			frm.setExcelDownloadFlg(Boolean.TRUE);
		} else {
			// データが無い場合
			saveError(request, "errors.nodata");
		}

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * directionMaterialUsedLogicを設定します。
	 * @param directionMaterialUsedLogic directionMaterialUsedLogic
	 */
	public void setDirectionMaterialUsedLogic(
			final DirectionMaterialUsedLogic directionMaterialUsedLogic) {
		this.directionMaterialUsedLogic = directionMaterialUsedLogic;
	}

	/**
	 * 製品別原材料消費量リストＥＸＣＥＬファイル作成ロジッククラスを設定します。
	 * @param directionMaterialUsedExcelDecorator 製品別原材料消費量リストＥＸＣＥＬファイル作成ロジッククラス
	 */
	public void setDirectionMaterialUsedExcelDecorator(
			final DirectionMaterialUsedExcelDecorator directionMaterialUsedExcelDecorator) {
		this.directionMaterialUsedExcelDecorator = directionMaterialUsedExcelDecorator;
	}
}
