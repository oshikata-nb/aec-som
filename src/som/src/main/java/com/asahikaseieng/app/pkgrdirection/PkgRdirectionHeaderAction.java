/*
 * Created on 2009/03/06
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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionHeaderDetail;
import com.asahikaseieng.exception.LogicExceptionEx;

/**
 * 包装実績ヘッダー Actionクラス.
 * @author tosco
 */
public final class PkgRdirectionHeaderAction extends
		AbstractPkgRdirectionAction {

	private PkgRdirectionHeaderLogic pkgRdirectionHeaderLogic;

	/**
	 * コンストラクタ.
	 */
	public PkgRdirectionHeaderAction() {
		super();
	}

	/**
	 * タブID（PkgRdirectionConstクラスで定義)を各子クラスで実装
	 * @return タブID
	 */
	@Override
	protected String getTabId() {

		// タブID－ヘッダー情報タブ
		return PkgRdirectionConst.HEADER;
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
	@Override
	protected ActionForward initProcess(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("▽▽▽　initProcess START　▽▽▽");
		}
		PkgRdirectionHeaderForm frm = (PkgRdirectionHeaderForm) form;
		frm.setDirtyFlg(null);

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_PKGRDIRECTION,
			Constants.TAB_ID_PKGRDIRECTION_HEADER);

		// ヘッダー情報画面にデータを設定
		pkgRdirectionHeaderLogic.setPkgRdirectionHeaderForm(request, frm);

		if (getLog().isDebugEnabled()) {
			getLog().debug("△△△　initProcess END　△△△");
		}
		return mapping.findForward("success");
	}

	/**
	 * 包装実績ヘッダー更新処理
	 * 
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
			getLog().debug("update.");
		}
		PkgRdirectionHeaderForm frm = (PkgRdirectionHeaderForm) form;
		String tantoCd = getLoginInfo(request).getTantoCd();

		if (frm.isDivideFlag()) {

			// 分納処理時のチェックを行う
			ActionMessages errors = pkgRdirectionHeaderLogic
					.checkFordivide(frm);

			// エラーがあった場合
			if (!errors.isEmpty()) {
				super.saveErrors(request, errors);
				return mapping.findForward("success");
			}

			String pkgDirectionNo = null;
			try {
				// 分納処理
				pkgDirectionNo = pkgRdirectionHeaderLogic.divide(frm, tantoCd);
			} catch (LogicExceptionEx e) {
				if ("errors.pkgrdirection.stock.update".equals(e.getMessage())) {
					// 在庫更新に失敗
					saveError(request, e.getMessage());
					return mapping.getInputForward();
				} else {
					throw e;
				}
			}

			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"message.pkgrdirection.divide.complete", pkgDirectionNo));

			// メッセージだと消えてしまうので、エラーメッセージに出力
			super.saveErrors(request, messages);
		} else {
			try {
				// 更新処理
				pkgRdirectionHeaderLogic.update(frm, tantoCd);
			} catch (LogicExceptionEx e) {
				if ("errors.pkgrdirection.stock.update".equals(e.getMessage())) {
					// 在庫更新に失敗
					saveError(request, e.getMessage());
					return mapping.getInputForward();
				} else if ("errors.pkgrdirection.status.complete".equals(e
						.getMessage())) {
					// 在庫更新に失敗
					saveError(request, e.getMessage());
					return mapping.getInputForward();
				} else {
					throw e;
				}
			}

			// メッセージ
			saveMessage(request, "message.complete.update");
		}
		return mapping.findForward("retrieve");
	}

	/**
	 * 戻る処理
	 * 
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
	 * 検索処理
	 * 
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward retrieve(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		PkgRdirectionHeaderForm frm = (PkgRdirectionHeaderForm) form;

		// クリアするので、渡された指図区分、包装指図番号を退避
		String directionDivision = frm.getDirectionDivision();
		String pkgDirectionNo = frm.getDirectionNo();

		// クリア
		frm.clear();
		frm.setDirtyFlg(null);
		frm.setDirectionDivision(directionDivision);
		frm.setDirectionNo(pkgDirectionNo);

		// タブIDを設定
		frm.setTabId(this.getTabId());

		// 共通情報検索処理
		PkgRdirectionDirectionHeaderDetail header = null;
		header = pkgRdirectionCommonsLogic.getEntity(directionDivision,
			pkgDirectionNo);
		setCommonHeaderInfo(frm, header, request);

		// ヘッダー情報画面にデータを設定
		pkgRdirectionHeaderLogic.setPkgRdirectionHeaderForm(request, frm);

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * 包装指図－包装指図ヘッダー ロジック設定.
	 * @param pkgRdirectionHeaderLogic 包装指図－包装指図ヘッダー ロジック
	 */
	public void setPkgRdirectionHeaderLogic(
			final PkgRdirectionHeaderLogic pkgRdirectionHeaderLogic) {
		this.pkgRdirectionHeaderLogic = pkgRdirectionHeaderLogic;
	}
}
