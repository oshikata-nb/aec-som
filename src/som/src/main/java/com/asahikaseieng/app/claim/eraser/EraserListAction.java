/*
 * Created on 2008/07/01
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
import com.asahikaseieng.app.master.organization.OrganizationDetailLogic;
import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserList;
import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.organizationdetail.OrganizationDetail;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 消込入力一覧処理 Actionクラス.
 * @author tosco
 */
public final class EraserListAction extends AbstractSearchAction {

	private OrganizationDetailLogic organizationDetailLogic;

	private EraserListLogic eraserListLogic;

	/**
	 * コンストラクタ.
	 */
	public EraserListAction() {
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

		EraserListForm frm = (EraserListForm) form;

		HttpSession session = request.getSession(false);
		if (session != null) {
			// セッションからログイン情報取得

			LoginInfo loginInfo = (LoginInfo) (session
					.getAttribute(Constants.LOGIN_KEY));
			// 部門コード設定

			frm.setSrhSectionCd(loginInfo.getOrganizationCd());
			// 担当者コード設定

			frm.setTantoCd(loginInfo.getTantoCd());

			// 部門名称取得

			OrganizationDetail bumonBean = organizationDetailLogic
					.getDetailEntity(frm.getSrhSectionCd());
			if (bumonBean != null) {
				frm.setSrhSectionName(bumonBean.getOrganizationName());
			} else {
				// 名称が取得できない場合でもエラーにしない
				frm.setSrhSectionName("");
			}
		}

		frm.setSrhDataDivNew(true); // 新規消込チェック
		frm.setSrhDataDivEraser(true); // 消込結果チェック
		frm.setSrhOutputDivision("1"); // 出力区分

		// frm.setSrhBalanceCheck(true); // 消込残有のみチェック

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

		EraserListForm frm = (EraserListForm) form;

		// クリア
		frm.setSearchList(new ArrayList<ClaimEraserList>());

		// 検索条件を取得

		ClaimEraserListPagerCondition condition = (ClaimEraserListPagerCondition) frm
				.getPager().getPagerCondition();

		// 検索条件をセット
		condition.setSrhSectionCd(frm.getSrhSectionCd()); // 部門コード
		condition.setSrhSectionName(frm.getSrhSectionName()); // 部門名称
		condition.setSrhTantoCd(frm.getSrhTantoCd()); // 担当者コード
		condition.setSrhTantoNm(frm.getSrhTantoNm()); // 担当者名称
		condition.setSrhVenderCd(frm.getSrhVenderCd()); // 請求先コード
		condition.setSrhVenderName(frm.getSrhVenderName()); // 請求先名称
		condition.setSrhDataCheckNew(frm.isSrhDataDivNew()); // 新規消込チェック
		condition.setSrhDataCheckEraser(frm.isSrhDataDivEraser()); // 消込結果チェック
		condition.setSrhEraserDateFrom(frm.getSrhEraserDateFrom()); // 消込日付FROM
		condition.setSrhEraserDateTo(frm.getSrhEraserDateTo()); // 消込日付TO
		condition.setSrhOutputDivision(frm.getSrhOutputDivision()); // 出力区分

		frm.setSearchList(eraserListLogic.getSearchList(condition));

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

		EraserListForm frm = (EraserListForm) form;

		/* クリア */
		frm.clear();

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * organizationDetailLogicを設定します。
	 * 
	 * @param organizationDetailLogic OrganizationDetailLogic
	 */
	public void setOrganizationDetailLogic(
			final OrganizationDetailLogic organizationDetailLogic) {
		this.organizationDetailLogic = organizationDetailLogic;
	}

	/**
	 * eraserListLogicを設定します。
	 * 
	 * @param eraserListLogic EraserListLogic
	 */
	public void setEraserListLogic(final EraserListLogic eraserListLogic) {
		this.eraserListLogic = eraserListLogic;
	}

}
