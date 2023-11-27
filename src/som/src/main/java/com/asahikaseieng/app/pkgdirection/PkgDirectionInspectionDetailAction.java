/*
 * Created on 2009/02/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgdirection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;

import com.asahikaseieng.Constants;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 包装指図-検査詳細 Actionクラス.
 * @author tosco
 */
public final class PkgDirectionInspectionDetailAction extends AbstractAction {

	/** 包装指図検査詳細検索 ロジッククラス */
	private PkgDirectionInspectionDetailLogic pkgDirectionInspectionDetailLogic;

	/** 包装指図共通ロジッククラス */
	private PkgDirectionCommonsLogic pkgDirectionCommonsLogic;

	/**
	 * コンストラクタ.
	 */
	public PkgDirectionInspectionDetailAction() {
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
	public ActionForward init(final ActionMapping mapping, final ActionForm form,
			final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		PkgDirectionInspectionDetailForm detailForm = (PkgDirectionInspectionDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, detailForm,
			Constants.MENU_ID_PKGDIRECTION,
			Constants.TAB_ID_PKGDIRECTION_INSPECTION);

		if (!detailForm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

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
		pkgDirectionInspectionDetailLogic.setInspectionDetailForm(request, detailForm);

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
		PkgDirectionInspectionDetailForm frm = (PkgDirectionInspectionDetailForm) form;

		// 更新可否チェック
		ActionMessages errors = pkgDirectionInspectionDetailLogic.checkForUpdate(frm);
		if (!errors.isEmpty()) {

			//エラーがあった場合
			super.saveErrors(request, errors);
			return mapping.getInputForward();
		}

		try {
			// 包装指図検査更新処理
			pkgDirectionInspectionDetailLogic.update(frm, getLoginInfo(request).getTantoCd());

			// 包装計画削除
			pkgDirectionCommonsLogic.deleteHosoKeikaku(frm.getDirectionNo());

		} catch (PkgDirectionLogicException e) {
			String[] replacements = e.getReplacements();
			if (replacements != null) {
				int len = replacements.length;
				for (int i = 0; i < len; i++) {
					String buf = getMessageResource(request, replacements[i]);
					if (StringUtils.isNotEmpty(buf)) {
						replacements[i] = buf;
					}
				}
			}
			addError(request, e.getKey(), (Object[]) replacements);
			return mapping.getInputForward();
		}

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

	/**
	 * エラーメッセージを追加する。
	 * @param request HttpServletRequest
	 * @param key メッセージキー
	 * @param objects 置換パラメータ
	 */
	private void addError(final HttpServletRequest request, final String key, final Object... objects) {
		ActionMessages messages = new ActionMessages();
		messages.add("", new ActionMessage(key, objects));
		addErrors(request, messages);
	}

	/**
	 * メッセージプロパティファイルから指定したkeyに対応する文字列を取得する。
	 * @param request HttpServletRequest
	 * @param key メッセージキー
	 * @return メッセージキーに対応するメッセージ文字列
	 */
	private String getMessageResource(final HttpServletRequest request, final String key) {
		 MessageResources resource = getResources(request);
		 return resource.getMessage(key);
	}

	/* -------------------- setter -------------------- */

	/**
	 * 包装指図検査詳細検索 ロジッククラスを設定します。
	 * @param pkgDirectionInspectionDetailLogic 包装指図検査詳細検索 ロジッククラス
	 */
	public void setPkgDirectionInspectionDetailLogic(
			final PkgDirectionInspectionDetailLogic pkgDirectionInspectionDetailLogic) {
		this.pkgDirectionInspectionDetailLogic = pkgDirectionInspectionDetailLogic;
	}

	/**
	 * 包装指図共通ロジッククラスを設定します。
	 * @param pkgDirectionCommonsLogic 基本処方共通ロジッククラス
	 */
	public void setPkgDirectionCommonsLogic(final PkgDirectionCommonsLogic pkgDirectionCommonsLogic) {
		this.pkgDirectionCommonsLogic = pkgDirectionCommonsLogic;
	}
}
