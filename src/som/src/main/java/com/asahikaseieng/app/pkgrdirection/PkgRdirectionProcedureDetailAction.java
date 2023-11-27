/*
 * Created on 2009/03/10
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
 * 包装実績-工程詳細画面 Actionクラス.
 * @author tosco
 */
public final class PkgRdirectionProcedureDetailAction extends AbstractAction {

	/** 包装実績－工程詳細画面 ロジッククラス */
	private PkgRdirectionProcedureDetailLogic pkgRdirectionProcedureDetailLogic;

	/**
	 * コンストラクタ.
	 */
	public PkgRdirectionProcedureDetailAction() {
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

		PkgRdirectionProcedureDetailForm detailForm = (PkgRdirectionProcedureDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, detailForm,
			Constants.MENU_ID_PKGRDIRECTION,
			Constants.TAB_ID_PKGRDIRECTION_PROCEDURE);
		// 検索キーを退避
		String directionDivision = detailForm.getDirectionDivision();
		String directionNo = detailForm.getDirectionNo();
		String stepNo = detailForm.getStepNo();

		// 画面を初期化
		detailForm.clear();
		detailForm.setDirectionDivision(directionDivision);
		detailForm.setDirectionNo(directionNo);
		detailForm.setStepNo(stepNo);

		// 工程詳細画面にデータを設定する
		pkgRdirectionProcedureDetailLogic.setProcedureDetailForm(request,
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
		PkgRdirectionProcedureDetailForm frm = (PkgRdirectionProcedureDetailForm) form;

		// 製造指図プロシージャ更新処理
		pkgRdirectionProcedureDetailLogic.update(frm, getLoginInfo(request)
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
	 * 包装実績－工程詳細画面 ロジッククラス設定
	 * @param pkgRdirectionProcedureDetailLogic 包装実績－工程詳細画面 ロジッククラス
	 */
	public void setPkgRdirectionProcedureDetailLogic(
			final PkgRdirectionProcedureDetailLogic pkgRdirectionProcedureDetailLogic) {
		this.pkgRdirectionProcedureDetailLogic = pkgRdirectionProcedureDetailLogic;
	}
}
