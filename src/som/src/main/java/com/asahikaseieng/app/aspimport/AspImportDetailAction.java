/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.aspimport;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.struts.AbstractAction;

/**
 * ASP取り込み Actionクラス.
 * @author
 */
public final class AspImportDetailAction extends AbstractAction {

	private AspImportDetailLogic aspImportDetailLogic;

	/**
	 * コンストラクタ.
	 */
	public AspImportDetailAction() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionForward init(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		return mapping.findForward("success");
	}

	/**
	 * 取込処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward aspImport(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("aspImport");
		}
		//ログインユーザー取得
		String loginUserId = getLoginInfo(request).getTantoCd();
		//ログインユーザー部署取得
		String loginUserOrganizationCd = getLoginInfo(request).getOrganizationCd();


		// ************************************************
		// データの取込処理
		// 購買外注、製造/包装とで、別々にコミット
		// ************************************************

		//購買外注作成処理
		int purchaseNum = aspImportDetailLogic.createPurchaseSubcontract(loginUserId, loginUserOrganizationCd);

		//製造/包装指図作成処理
		List<Integer> numList =
			aspImportDetailLogic.createDirection(loginUserId, loginUserOrganizationCd);

		//取込処理完了メッセージの登録
		ActionMessages messages = new ActionMessages();
		messages.add("", new ActionMessage
			("message.asp.import.complete",
					purchaseNum, numList.get(0), numList.get(1)));
		saveMessages(request, messages);

		return mapping.findForward("success");
	}



	/* -------------------- setter -------------------- */

	/**
	 * aspImportDetailLogicを設定します。
	 * @param aspImportDetailLogic aspImportDetailLogic
	 */
	public void setAspImportDetailLogic(final AspImportDetailLogic aspImportDetailLogic) {
		this.aspImportDetailLogic = aspImportDetailLogic;
	}

}
