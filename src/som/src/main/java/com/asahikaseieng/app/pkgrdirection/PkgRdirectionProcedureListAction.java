/*
 * Created on 2009/03/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgrdirection;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionProcedureList;

/**
 * 包装実績－工程一覧画面 Actionクラス
 * @author tosco
 */
public class PkgRdirectionProcedureListAction extends
		AbstractPkgRdirectionAction {

	/** 包装実績－製造指図プロシージャ検索 ロジッククラス */
	private PkgRdirectionProcedureListLogic pkgRdirectionProcedureListLogic;

	/**
	 * コンストラクタ
	 */
	public PkgRdirectionProcedureListAction() {
	}

	/**
	 * タブID（PkgRdirectionConstクラスで定義)を各子クラスで実装
	 * @return タブID
	 */
	@Override
	protected String getTabId() {
		return PkgRdirectionConst.PROCEDURELIST;
	}

	/**
	 * 各子クラスの画面初期表示処理
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
			getLog().debug("init.");
		}
		PkgRdirectionProcedureListForm frm = (PkgRdirectionProcedureListForm) form;
		frm.setDirtyFlg(null);

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_PKGRDIRECTION,
			Constants.TAB_ID_PKGRDIRECTION_PROCEDURE);

		// 包装実績－製造指図プロシージャ検索
		List<PkgRdirectionDirectionProcedureList> searchList = pkgRdirectionProcedureListLogic
				.getSearchList(frm);
		frm.setSearchProcList(searchList);

		return mapping.findForward("success");
	}

	/**
	 * 行追加処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward addlist(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("addlist.");
		}

		PkgRdirectionProcedureListForm frm = (PkgRdirectionProcedureListForm) form;
		List<PkgRdirectionDirectionProcedureList> searchProcList = frm
				.getSearchProcList();

		PkgRdirectionDirectionProcedureList bean = new PkgRdirectionDirectionProcedureList();
		bean.setDirectionDivision(new BigDecimal(frm.getDirectionDivision()));
		bean.setDirectionNo(frm.getDirectionNo());
		bean.setFormulaMark("×"); // 配合

		// 要素がない場合
		if (searchProcList.size() == 0) {
			frm.getSearchProcList().add(bean);
		} else {
			// リスト追加ループ
			for (int i = 0; i < searchProcList.size(); i++) {
				PkgRdirectionDirectionProcedureList directionBean = searchProcList
						.get(i);
				// チェックボックスがチェックされていた場合
				if (directionBean.isCheckFlg()) {
					// 新しい要素を追加
					searchProcList.add(i, bean);
					break;
				}

				// チェックがない場合最後尾に追加
				if (i == searchProcList.size() - 1) {
					// 新しい要素を追加
					frm.getSearchProcList().add(bean);
					break;
				}
			}
		}

		// 追加データの後の工程順序を設定
		for (int i = 0; i < searchProcList.size(); i++) {
			PkgRdirectionDirectionProcedureList directionBean = searchProcList
					.get(i);
			directionBean.setCheckFlg(Boolean.FALSE);
			directionBean.setSeq(new BigDecimal(i + 1));
		}

		return mapping.findForward("success");
	}

	/**
	 * 行削除処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward dellist(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("dellist.");
		}

		PkgRdirectionProcedureListForm frm = (PkgRdirectionProcedureListForm) form;
		List<PkgRdirectionDirectionProcedureList> searchProcList = frm
				.getSearchProcList();

		// 配合・検査存在チェック
		ActionMessages errors = pkgRdirectionProcedureListLogic
				.checkForDelList(searchProcList);
		if (!errors.isEmpty()) {
			// エラーがあった場合
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		// 削除処理
		for (int i = searchProcList.size() - 1; i >= 0; i--) {
			PkgRdirectionDirectionProcedureList directionBean = searchProcList
					.get(i);

			if (!directionBean.isCheckFlg()) {
				// チェック無は読み飛ばし
				continue;
			}

			searchProcList.remove(i);
		}

		// 追加データの後の工程順序を設定
		for (int i = 0; i < searchProcList.size(); i++) {
			PkgRdirectionDirectionProcedureList directionBean = searchProcList
					.get(i);
			directionBean.setSeq(new BigDecimal(i + 1));
		}

		return mapping.findForward("success");
	}

	/**
	 * 登録処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward regist(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("regist");
		}

		PkgRdirectionProcedureListForm frm = (PkgRdirectionProcedureListForm) form;

		// 更新前チェックを行う
		ActionMessages errors = pkgRdirectionProcedureListLogic
				.checkForRegist(frm);
		if (!errors.isEmpty()) {
			// エラーがあった場合
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		// 包装実績－工程 更新処理
		pkgRdirectionProcedureListLogic.regist(frm, getLoginInfo(request)
				.getTantoCd());

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("init");
	}

	/**
	 * 再表示処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward reInit(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		PkgRdirectionProcedureListForm frm = (PkgRdirectionProcedureListForm) form;

		// 検索結果リストクリア
		frm
				.setSearchProcList(new ArrayList<PkgRdirectionDirectionProcedureList>());

		return init(mapping, form, request, response);
	}

	/* -------------------- setter -------------------- */

	/**
	 * 包装実績－工程一覧画面 ロジック設定.
	 * @param pkgRdirectionProcedureListLogic 包装実績－工程一覧画面 ロジック
	 */
	public void setPkgRdirectionProcedureListLogic(
			final PkgRdirectionProcedureListLogic pkgRdirectionProcedureListLogic) {
		this.pkgRdirectionProcedureListLogic = pkgRdirectionProcedureListLogic;
	}

}
