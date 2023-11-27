/*
 * Created on 2009/03/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.grecipe;

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
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeProcedureList;

/**
 * 原処方-工程一覧 Actionクラス.
 * @author tosco
 */
public final class GrecipeProcedureListAction extends AbstractGrecipeAction {

	/** 原処方-工程 ロジッククラス */
	private GrecipeProcedureListLogic grecipeProcedureListLogic;

	/**
	 * コンストラクタ.
	 */
	public GrecipeProcedureListAction() {
		super();
	}

	/**
	 * タブID（GrecipeConstクラスで定義)を各子クラスで実装
	 * @return タブID
	 */
	@Override
	protected String getTabId() {
		// タブID-工程一覧
		return GrecipeConst.PROCEDURELIST;
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

		GrecipeProcedureListForm listForm = (GrecipeProcedureListForm) form;

		/* 権限取得 */
		getControlAuthority(request, listForm, Constants.MENU_ID_GRECIPE,
			Constants.TAB_ID_GRECIPE_PROCEDURE);

		if (!listForm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		listForm.setDirtyFlg(null);

		// 処方プロシージャ検索
		List<GrecipeRecipeProcedureList> searchList = grecipeProcedureListLogic
				.getSearchList(new BigDecimal(listForm.getRecipeId()));
		listForm.setSearchProcList(searchList);

		if (StringUtils.isEmpty(listForm.getSrhLink())) {
			listForm.setSrhLink("0");
		}

		/* リンクフラグセット */
		for (int i = 0; i < searchList.size(); i++) {
			searchList.get(i).setSrhLink(listForm.getSrhLink());
		}

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

		GrecipeProcedureListForm frm = (GrecipeProcedureListForm) form;
		List<GrecipeRecipeProcedureList> searchProcList = frm
				.getSearchProcList();

		GrecipeRecipeProcedureList bean = new GrecipeRecipeProcedureList();
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
				GrecipeRecipeProcedureList recipeBean = searchProcList.get(i);
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
			GrecipeRecipeProcedureList recipeBean = searchProcList.get(i);
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

		GrecipeProcedureListForm frm = (GrecipeProcedureListForm) form;
		List<GrecipeRecipeProcedureList> searchProcList = frm
				.getSearchProcList();

		// 配合・検査・仕上存在チェック
		ActionMessages errors = grecipeProcedureListLogic
				.checkForDelList(searchProcList);
		if (!errors.isEmpty()) {
			// エラーがあった場合
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		// 削除処理
		for (int i = searchProcList.size() - 1; i >= 0; i--) {
			GrecipeRecipeProcedureList recipeBean = searchProcList.get(i);

			if (!recipeBean.isCheckFlg()) {
				// チェック無は読み飛ばし
				continue;
			}

			searchProcList.remove(i);
		}

		// 追加データの後の工程順序を設定
		for (int i = 0; i < searchProcList.size(); i++) {
			GrecipeRecipeProcedureList recipeBean = searchProcList.get(i);
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

		GrecipeProcedureListForm frm = (GrecipeProcedureListForm) form;

		// 更新前チェックを行う
		ActionMessages errors = grecipeProcedureListLogic.checkForRegist(frm
				.getSearchProcList());
		if (!errors.isEmpty()) {
			// エラーがあった場合
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		// 処方プロシージャ更新処理
		grecipeProcedureListLogic.regist(frm, getLoginInfo(request)
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

		GrecipeProcedureListForm frm = (GrecipeProcedureListForm) form;

		// 検索結果リストクリア
		frm.setSearchProcList(new ArrayList<GrecipeRecipeProcedureList>());

		return init(mapping, form, request, response);
	}

	/* -------------------- setter -------------------- */

	/**
	 * 原処方-工程 ロジッククラスを設定します。
	 * @param grecipeProcedureListLogic 原処方-工程 ロジッククラス
	 */
	public void setGrecipeProcedureListLogic(
			final GrecipeProcedureListLogic grecipeProcedureListLogic) {
		this.grecipeProcedureListLogic = grecipeProcedureListLogic;
	}

}
