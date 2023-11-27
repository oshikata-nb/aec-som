/*
 * Created on 2009/03/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgrdirection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 包装実績－配合詳細画面 Actionクラス.
 * @author tosco
 */
public final class PkgRdirectionFormulaDetailAction extends AbstractAction {

	/** 包装実績－フォーミュラ配合詳細検索 ロジッククラス */
	private PkgRdirectionFormulaDetailLogic pkgRdirectionFormulaDetailLogic;

	/**
	 * コンストラクタ.
	 */
	public PkgRdirectionFormulaDetailAction() {
		super();
	}

	/**
	 * 画面初期表示処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward init(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		PkgRdirectionFormulaDetailForm detailForm = (PkgRdirectionFormulaDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, detailForm,
			Constants.MENU_ID_PKGRDIRECTION,
			Constants.TAB_ID_PKGRDIRECTION_FORMULA);
		// 検索キーを退避
		String directionDivision = detailForm.getDirectionDivision();
		String directionNo = detailForm.getDirectionNo();
		String stepNo = detailForm.getStepNo();
		String lineNo = detailForm.getLineNo();

		// 画面を初期化
		detailForm.clear();
		detailForm.setDirectionDivision(directionDivision);
		detailForm.setDirectionNo(directionNo);
		detailForm.setStepNo(stepNo);
		detailForm.setLineNo(lineNo);

		// 配合詳細画面にデータを設定する
		pkgRdirectionFormulaDetailLogic.setFormulaDetailForm(request,
			detailForm);

		return mapping.findForward("success");
	}

	/**
	 * 更新処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward update(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("update");
		}

		PkgRdirectionFormulaDetailForm frm = (PkgRdirectionFormulaDetailForm) form;

		// 製造指図フォーミュラ更新処理
		pkgRdirectionFormulaDetailLogic.update(frm, getLoginInfo(request)
				.getTantoCd());

		/* メッセージ */
		saveMessage(request, "message.complete.update");

		return mapping.findForward("back");
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

		return mapping.findForward("back");
	}

	/* -------------------- setter -------------------- */

	/**
	 * 包装実績－配合詳細画面 ロジッククラス設定
	 * @param pkgRdirectionFormulaDetailLogic 包装実績－配合詳細画面 ロジッククラス
	 */
	public void setPkgRdirectionFormulaDetailLogic(
			final PkgRdirectionFormulaDetailLogic pkgRdirectionFormulaDetailLogic) {
		this.pkgRdirectionFormulaDetailLogic = pkgRdirectionFormulaDetailLogic;
	}
}
