/*
 * Created on 2008/10/10
 *
 * $copyright$
 */
package com.asahikaseieng.app.claim.eraser;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserCsmList;
import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserCsmListPagerCondition;
import com.asahikaseieng.dao.nonentity.claim.eraserlistforreport.ClaimEraserCsmListConditionForReport;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 消込入力一覧処理 Actionクラス.(カスタマイズ)
 * @author tosco
 */
public final class EraserCsmListAction extends AbstractSearchAction {

	private EraserCsmListLogic eraserCsmListLogic;

	private EraserCsmListExcelDecorator eraserCsmListExcelDecorator;

	/* 売上金額 */
	private static final String URKINGAKU = "URKINGAKU";

	/**
	 * コンストラクタ.
	 */
	public EraserCsmListAction() {
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

		EraserCsmListForm frm = (EraserCsmListForm) form;

		/* 初期表示：出力区分 */
		frm.setSrhCreditEraserAmountDivi("1");

		HttpSession session = request.getSession(false);
		if (session != null) {
			// セッションからログイン情報取得
			LoginInfo loginInfo = (LoginInfo) (session
					.getAttribute(Constants.LOGIN_KEY));
			// 部署コード設定
			frm.setSrhOrganizationCd(loginInfo.getOrganizationCd());
			// 部署名称設定
			frm.setSrhOrganizationName(loginInfo.getOrganizationName());
			// 担当者コード設定
			frm.setSrhTantoCd(loginInfo.getTantoCd());
			// 担当者名設定
			frm.setSrhTantoNm(loginInfo.getTantoNm());
		}

		frm.setSrhStatusNew(true); // 消込状態(新規消込)チェック
		frm.setSrhStatusApprove(true); // 消込状態(承認処理)チェック

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

		EraserCsmListForm frm = (EraserCsmListForm) form;

		// クリア
		frm.setSearchList(new ArrayList<ClaimEraserCsmList>());

		// 検索条件を取得
		ClaimEraserCsmListPagerCondition condition = (ClaimEraserCsmListPagerCondition) frm
				.getPager().getPagerCondition();

		// 検索条件をセット
		condition.setSrhOrganizationCd(frm.getSrhOrganizationCd()); // 部署コード
		condition.setSrhTantoCd(frm.getSrhTantoCd()); // 担当者コード
		condition.setSrhVenderCd(frm.getSrhVenderCd()); // 請求先コード
		condition.setSrhStatusNew(frm.isSrhStatusNew()); // 新規消込チェック
		condition.setSrhStatusApprove(frm.isSrhStatusApprove()); // 承認処理チェック
		condition.setSrhStatusEraser(frm.isSrhStatusEraser()); // 消込完了チェック
		condition.setSrhEraserCompleteDateFrom(frm
				.getSrhEraserCompleteDateFrom()); // 消込完了日付FROM
		condition.setSrhEraserCompleteDateTo(frm.getSrhEraserCompleteDateTo()); // 消込完了日付TO
		condition.setSrhOutputDivision(frm.getSrhCreditEraserAmountDivi()); // 出力区分

		/* 帳票(Excel)用に検索条件を保持 */
		ClaimEraserCsmListConditionForReport reportCondition = new ClaimEraserCsmListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setCondition(reportCondition);

		frm.setSearchList(eraserCsmListLogic.getSearchList(condition));

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		for (int i = 0; i < frm.getSearchList().size(); i++) {
			/* 数値文字列に変換 */
			frm.getSearchList().get(i).setStrEraserObjectAmount(
				checker.format(URKINGAKU, frm.getSearchList().get(i)
						.getEraserObjectAmount())); /* 請求金額 */
			frm.getSearchList().get(i).setStrEraserAmount(
				checker.format(URKINGAKU, frm.getSearchList().get(i)
						.getEraserAmount())); /* 消込金額 */
			frm.getSearchList().get(i).setStrCreditEraserAmount(
				checker.format(URKINGAKU, frm.getSearchList().get(i)
						.getCreditEraserAmount())); /* 入金消込額合計 */
		}

		return mapping.findForward("success");
	}

	/**
	 * フォームに表示されている項目のクリア処理.
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

		EraserCsmListForm frm = (EraserCsmListForm) form;

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

		EraserCsmListForm frm = (EraserCsmListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = eraserCsmListExcelDecorator.createReport(frm
				.getCondition());

		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setExcelDownloadFlg(true);

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * eraserCsmListLogicを設定します。
	 * 
	 * @param eraserCsmListLogic EraserCsmListLogic
	 */
	public void setEraserCsmListLogic(
			final EraserCsmListLogic eraserCsmListLogic) {
		this.eraserCsmListLogic = eraserCsmListLogic;
	}

	/**
	 * eraserCsmListExcelDecoratorを設定します。
	 * @param eraserCsmListExcelDecorator eraserCsmListExcelDecorator
	 */
	public void setEraserCsmListExcelDecorator(
			final EraserCsmListExcelDecorator eraserCsmListExcelDecorator) {
		this.eraserCsmListExcelDecorator = eraserCsmListExcelDecorator;
	}
}
