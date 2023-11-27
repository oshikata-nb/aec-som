/*
 * Created on 2009/02/17
 *
 * $copyright$
 * 
 *
 */
package com.asahikaseieng.app.carryshipping;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.carryshipping.CarryShippingList;
import com.asahikaseieng.dao.nonentity.carryshipping.CarryShippingPagerCondition;
import com.asahikaseieng.dao.nonentity.carryshippingforreport.CarryShippingListConditionForReport;
import com.asahikaseieng.dao.nonentity.repcarryshippingforreport.RepCarryShippingForReportCondition;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 運送店別出荷指図画面 Actionクラス.
 * @author tosco
 * 
 */
public final class CarryShippingListAction extends AbstractSearchAction {

	/** 運送店別出荷指図画面のロジッククラス */
	private CarryShippingListLogic carryShippingListLogic;

	/** 帳票Excel用 */
	private CarryShippingListExcelDecorator carryShippingListExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public CarryShippingListAction() {
		super();
	}

	/**
	 * 初期処理(メニューから遷移時)
	 * 
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

		// フォーム取得
		CarryShippingListForm frm = (CarryShippingListForm) form;

		// システム日時取得を取得し、出荷予定日のデフォルトとしてセットする
		Timestamp now = AecDateUtils.getCurrentTimestamp();
		frm.setSrhScheduledShippingDate(AecDateUtils.dateFormat(now,
			"yyyy/MM/dd")); // yyyy/MM/ddの形でセット

		// 初期検索無し
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

		// フォーム取得
		CarryShippingListForm frm = (CarryShippingListForm) form;

		// 検索結果のクリア
		frm.setSearchList(new ArrayList<CarryShippingList>());

		// 検索条件を取得
		CarryShippingPagerCondition condition = (CarryShippingPagerCondition) frm
				.getPager().getPagerCondition();

		// 出荷予定日セット
		condition.setSrhShippingInstructDate(frm.getSrhScheduledShippingDate());

		/* 帳票(Excel)用に検索条件を保持 */
		CarryShippingListConditionForReport reportCondition = new CarryShippingListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setReportCondition(reportCondition);

		// 検索
		frm.setSearchList(carryShippingListLogic.getSearchList(condition));
		return mapping.findForward("success");
	}

	/**
	 * 指図作成処理(指図作成ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward orderMake(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("orderMake.");
		}

		// フォーム取得
		CarryShippingListForm frm = (CarryShippingListForm) form;

		try {
			// 運送店別出荷指図送信処理
			carryShippingListLogic.shippingOrderMake(frm, getLoginInfo(request)
					.getTantoCd());

		} catch (LogicExceptionEx e) {
			carryShippingListLogic.outPutErrorLog(frm.getErrorCd(), frm
					.getErrorMsg(), getLoginInfo(request).getTantoCd());
			String errMsg = "errors.carryshipping.make.error";
			String errMsg2 = "errors.carryshipping.make.number.orver";
			String errMsg3 = "errors.carryshipping.make.flancd.error";
			if (errMsg.equals(e.getMessage())) {
				saveError(request, errMsg);
				return mapping.getInputForward();
			} else if (errMsg2.equals(e.getMessage())) {
				saveError(request, errMsg2);
				return mapping.getInputForward();
			} else if (errMsg3.equals(e.getMessage())) {
				saveError(request, errMsg3);
				return mapping.getInputForward();

			} else {
				throw e;
			}
		}

		return mapping.findForward("reSearch");
	}

	/**
	 * 指図送信処理(指図送信ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward directionSubmit(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("directionSubmit.");
		}

		// formを取得
		CarryShippingListForm frm = (CarryShippingListForm) form;

		ActionMessages messages = new ActionMessages();

		// ログインユーザー取得
		String loginUserId = getLoginInfo(request).getTantoCd();

		try {
			// 運送店別出荷指図 更新処理
			carryShippingListLogic.update(frm, loginUserId, messages);

		} catch (NoDataException e) {
			// エラーメッセージはプロシージャ側で出すので出さないよう修正
			// carryShippingListLogic.outPutErrorLog(frm.getErrorCd(), frm
			// .getErrorMsg(), getLoginInfo(request).getTantoCd());
			// addError(request, "errors.carryshipping.send",
			// frm.getErrorMsg());
			return mapping.getInputForward();
		}

		if (!messages.isEmpty()) {
			saveErrors(request, messages);
		}

		// 更新完了メッセージを登録
		saveMessage(request, "message.carryshipping.complete.update");

		return mapping.findForward("reSearch");
	}

	/**
	 * 帳票処理(検索画面の帳票ボタン押下時)
	 * 
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

		CarryShippingListForm frm = (CarryShippingListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = carryShippingListExcelDecorator
				.createReport(frm.getReportCondition());

		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setExcelDownloadFlg(true);

		return mapping.findForward("success");

	}

	/**
	 * 運送店別出荷一覧表 帳票処理(検索画面の帳票ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward carryReport(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("carryReport.");
		}

		CarryShippingListForm frm = (CarryShippingListForm) form;

		RepCarryShippingForReportCondition condition = new RepCarryShippingForReportCondition();

		condition.setSrhScheduledShippingDate(frm.getSrhScheduledShippingDate());

		/* Excel作成 */
		FileDownloadInfo info = carryShippingListExcelDecorator
				.createReportCarryShipping(condition);

		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setCarryExcelDownloadFlg(true);

		return mapping.findForward("success");

	}

	/**
	 * フォームに表示されている項目のクリア処理.
	 * 
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

		// フォームの取得
		CarryShippingListForm frm = (CarryShippingListForm) form;

		// クリア
		frm.clear();

		return mapping.findForward("success");
	}

	// /**
	// * エラーメッセージを追加する。
	// * @param request HttpServletRequest
	// * @param key メッセージキー
	// * @param objects 置換パラメータ
	// */
	// private void addError(final HttpServletRequest request, final String key,
	// final Object... objects) {
	// ActionMessages messages = new ActionMessages();
	// messages.add("", new ActionMessage(key, objects));
	// addErrors(request, messages);
	// }

	/* -------------------- setter -------------------- */

	/**
	 * 運送店別出荷指図画面ロジッククラスを設定します。
	 * @param carryShippingListLogic 運送店別出荷指図画面ロジッククラス
	 */
	public void setCarryShippingListLogic(
			final CarryShippingListLogic carryShippingListLogic) {
		this.carryShippingListLogic = carryShippingListLogic;
	}

	/**
	 * 帳票Excel用クラスを設定します。
	 * @param carryShippingListExcelDecorator 帳票Excel用
	 */
	public void setCarryShippingListExcelDecorator(
			final CarryShippingListExcelDecorator carryShippingListExcelDecorator) {
		this.carryShippingListExcelDecorator = carryShippingListExcelDecorator;
	}

}
