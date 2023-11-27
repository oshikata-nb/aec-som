/*
 * Created on 2009/01/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mgrecipe;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeProcedureList;

/**
 * 基本処方-工程一覧 Actionクラス.
 * @author tosco
 */
public final class MgrecipeProcedureListAction extends AbstractMgrecipeAction {

	/** 処方プロシージャ検索 ロジッククラス */
	private MgrecipeProcedureListLogic mgrecipeProcedureListLogic;

	/**
	 * コンストラクタ.
	 */
	public MgrecipeProcedureListAction() {
		super();
	}

	/**
	 * タブID（MgrecipeConstクラスで定義)を各子クラスで実装
	 * @return タブID
	 */
	@Override
	protected String getTabId() {
		// タブID-工程一覧
		return MgrecipeConst.PROCEDURELIST;
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

		MgrecipeProcedureListForm listForm = (MgrecipeProcedureListForm) form;

		/* 権限取得 */
		getControlAuthority(request, listForm, Constants.MENU_ID_MRECIPE,
			Constants.TAB_ID_MRECIPE_PROCEDURE);

		if (!listForm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		listForm.setDirtyFlg(null);

		if (StringUtils.isEmpty(listForm.getSrhLink())) {
			listForm.setSrhLink("0");
		}

		// 処方プロシージャ検索
		List<RecipeProcedureList> searchList = mgrecipeProcedureListLogic
				.getSearchList(new BigDecimal(listForm.getRecipeId()));

		/* リンクフラグセット */
		for (int i = 0; i < searchList.size(); i++) {
			searchList.get(i).setSrhLink(listForm.getSrhLink());
		}

		listForm.setSearchProcList(searchList);

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

		MgrecipeProcedureListForm frm = (MgrecipeProcedureListForm) form;
		List<RecipeProcedureList> searchProcList = frm.getSearchProcList();

		RecipeProcedureList bean = new RecipeProcedureList();
		bean.setRecipeId(new BigDecimal(frm.getRecipeId())); // レシピインデックス
		bean.setFormulaMark("×"); // 配合
		bean.setStrMainStream("0");
		bean.setMainStream(BigDecimal.ZERO);

		// 要素がない場合
		if (searchProcList.size() == 0) {
			frm.getSearchProcList().add(bean);
		} else {
			// リスト追加ループ
			for (int i = 0; i < searchProcList.size(); i++) {
				RecipeProcedureList recipeBean = searchProcList.get(i);
				// チェックボックスがチェックされていた場合
				if (recipeBean.isCheckFlg()) {
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
			RecipeProcedureList recipeBean = searchProcList.get(i);
			recipeBean.setCheckFlg(Boolean.FALSE);
			recipeBean.setSeq(new BigDecimal(i + 1));
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

		MgrecipeProcedureListForm frm = (MgrecipeProcedureListForm) form;
		List<RecipeProcedureList> searchProcList = frm.getSearchProcList();

		// 配合・検査存在チェック
		ActionMessages errors = mgrecipeProcedureListLogic
				.checkForDelList(searchProcList);
		if (!errors.isEmpty()) {
			// エラーがあった場合
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		// 削除処理
		for (int i = searchProcList.size() - 1; i >= 0; i--) {
			RecipeProcedureList recipeBean = searchProcList.get(i);

			if (!recipeBean.isCheckFlg()) {
				// チェック無は読み飛ばし
				continue;
			}

			searchProcList.remove(i);
		}

		// 追加データの後の工程順序を設定
		for (int i = 0; i < searchProcList.size(); i++) {
			RecipeProcedureList recipeBean = searchProcList.get(i);
			recipeBean.setSeq(new BigDecimal(i + 1));
		}

		return mapping.findForward("success");
	}

	/**
	 * 登録処理処理.
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

		MgrecipeProcedureListForm frm = (MgrecipeProcedureListForm) form;

		// 更新前チェックを行う
		ActionMessages errors = mgrecipeProcedureListLogic.checkForRegist(frm
				.getSearchProcList());
		if (!errors.isEmpty()) {
			// エラーがあった場合
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		// 処方プロシージャ更新処理
		mgrecipeProcedureListLogic.regist(frm, getLoginInfo(request)
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

		MgrecipeProcedureListForm frm = (MgrecipeProcedureListForm) form;

		// 検索結果リストクリア
		frm.setSearchProcList(new ArrayList<RecipeProcedureList>());

		return init(mapping, form, request, response);
	}

	/* -------------------- setter -------------------- */

	/**
	 * 処方プロシージャ検索 ロジッククラスを設定します。
	 * @param mgrecipeProcedureListLogic 処方プロシージャ検索 ロジッククラス
	 */
	public void setMgrecipeProcedureListLogic(
			final MgrecipeProcedureListLogic mgrecipeProcedureListLogic) {
		this.mgrecipeProcedureListLogic = mgrecipeProcedureListLogic;
	}

}
