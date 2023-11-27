/*
 * Created on 2009/02/03
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
import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.Constants;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 包装実績-検査詳細 Actionクラス.
 * @author tosco
 */
public final class PkgRdirectionInspectionDetailAction extends AbstractAction {

	/** 包装実績検査-詳細検索 ロジッククラス */
	private PkgRdirectionInspectionDetailLogic pkgRdirectionInspectionDetailLogic;

	/**
	 * コンストラクタ.
	 */
	public PkgRdirectionInspectionDetailAction() {
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

		PkgRdirectionInspectionDetailForm detailForm = (PkgRdirectionInspectionDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, detailForm,
			Constants.MENU_ID_PKGRDIRECTION,
			Constants.TAB_ID_PKGRDIRECTION_INSPECTION);
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

		// 検査詳細画面にデータを設定する
		pkgRdirectionInspectionDetailLogic.setInspectionDetailForm(request,
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
		PkgRdirectionInspectionDetailForm frm = (PkgRdirectionInspectionDetailForm) form;

		// 更新可否チェック
		ActionMessages errors = pkgRdirectionInspectionDetailLogic
				.checkForUpdate(frm);
		if (!errors.isEmpty()) {

			// エラーがあった場合
			super.saveErrors(request, errors);
			return mapping.getInputForward();
		}

		// 製造指図検査更新処理
		pkgRdirectionInspectionDetailLogic.update(frm, getLoginInfo(request)
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
	 * 包装実績検査-詳細検索 ロジッククラスを設定します。
	 * @param pkgRdirectionInspectionDetailLogic 包装実績検査-詳細検索 ロジッククラス
	 */
	public void setPkgRdirectionInspectionDetailLogic(
			final PkgRdirectionInspectionDetailLogic pkgRdirectionInspectionDetailLogic) {
		this.pkgRdirectionInspectionDetailLogic = pkgRdirectionInspectionDetailLogic;
	}

}
