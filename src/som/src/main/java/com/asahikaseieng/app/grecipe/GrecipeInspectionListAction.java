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
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeHeaderList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeInspectionList;

/**
 * 原処方 検査一覧タブ Actionクラス
 * @author tosco
 */
public final class GrecipeInspectionListAction extends AbstractGrecipeAction {

	/** 原処方検査タブ ロジッククラス */
	private GrecipeInspectionListLogic grecipeInspectionListLogic;

	/**
	 * コンストラクタ.
	 */
	public GrecipeInspectionListAction() {
		super();
	}

	/**
	 * タブID（MgrecipeConstクラスで定義)を各子クラスで実装
	 * @return タブID
	 */
	@Override
	protected String getTabId() {
		// タブID-検査一覧
		return GrecipeConst.INSPECTIONLIST;
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
		GrecipeInspectionListForm listForm = (GrecipeInspectionListForm) form;
		listForm.setDirtyFlg(null);

		/* 権限取得 */
		getControlAuthority(request, listForm, Constants.MENU_ID_GRECIPE,
			Constants.TAB_ID_GRECIPE_INSPECTION);

		if (!listForm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 工程順序コンボボックス設定
		listForm.setProcedureStepNoCombo(grecipeCommonsLogic
				.createProcedureSetpNoCombobox(listForm.getRecipeId(), false));

		if (getLog().isDebugEnabled()) {
			getLog().debug("△△△　initProcess END　△△△");
		}

		if (StringUtils.isEmpty(listForm.getSrhLink())) {
			listForm.setSrhLink("0");
		}

		return mapping.findForward("success");
	}

	/**
	 * 検索処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward search(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("▽▽▽　search START　▽▽▽");
		}
		GrecipeInspectionListForm listForm = (GrecipeInspectionListForm) form;
		listForm.setSearchInspectionList(null);
		listForm.setSaveSearchInspectionList(null);
		listForm.setOperationCd(null);
		listForm.setOperationName(null);
		listForm.setDirtyFlg(null);
		listForm.setSelectKeyStepNo(listForm.getSelectProcedureStepNo());

		// 工程コード、工程名称を取得する
		String[] res = grecipeInspectionListLogic.getOperationName(listForm);
		listForm.setOperationCd(res[0]);
		listForm.setOperationName(res[1]);

		// 処方検査情報検索
		List<GrecipeRecipeInspectionList> searchList = grecipeInspectionListLogic
				.getSearchList(listForm);
		listForm.setSearchInspectionList(searchList);
		listForm.setSaveSearchInspectionList(searchList);

		/* リンクフラグセット */
		for (int i = 0; i < searchList.size(); i++) {
			searchList.get(i).setSrhLink(listForm.getSrhLink());
		}

		if (getLog().isDebugEnabled()) {
			getLog().debug("△△△　search END　△△△");
		}
		return mapping.findForward("success");
	}

	/**
	 * 行追加処理
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
			getLog().debug("▽▽▽　addlist START　▽▽▽");
		}
		GrecipeInspectionListForm listForm = (GrecipeInspectionListForm) form;
		ActionMessages errors = grecipeInspectionListLogic
				.checkForAddList(listForm);

		// エラーがあった場合
		if (!errors.isEmpty()) {
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		List<GrecipeRecipeInspectionList> list = listForm
				.getSearchInspectionList();
		if (list == null) {
			list = new ArrayList<GrecipeRecipeInspectionList>();
		}
		GrecipeRecipeInspectionList addBean = new GrecipeRecipeInspectionList();
		addBean.setRecipeId(new BigDecimal(listForm.getRecipeId())); // レシピインデックス
		addBean.setStepNo(new BigDecimal(listForm.getSelectKeyStepNo())); // STEP_NO
		addBean.setStrLineNo(null); // LINE_NO

		int idx = 0;
		for (idx = 0; idx < list.size(); idx++) {
			GrecipeRecipeInspectionList bean = list.get(idx);

			// チェック有りの場合
			if (bean.isCheckFlg()) {

				// 新しい要素を追加
				list.add(idx, addBean);
				break;
			}
		}

		// 新規、チェックボックスにチェックなしの場合、最後尾に追加
		if (idx == list.size()) {
			addBean.setSeq(new BigDecimal(list.size() + 1));
			list.add(addBean);
		} else {
			// リストに挿入した場合は、以降Seqを振り直す
			for (int i = idx; i < list.size(); i++) {
				GrecipeRecipeInspectionList bean = list.get(i);
				bean.setCheckFlg(Boolean.FALSE);
				bean.setSeq(new BigDecimal(i + 1));
			}
		}
		listForm.setSearchInspectionList(list);

		if (getLog().isDebugEnabled()) {
			getLog().debug("△△△　addlist END　△△△");
		}
		return mapping.findForward("success");
	}

	/**
	 * 行削除処理
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
			getLog().debug("▽▽▽　dellist START　▽▽▽");
		}
		GrecipeInspectionListForm listForm = (GrecipeInspectionListForm) form;
		ActionMessages errors = grecipeInspectionListLogic
				.checkForDelList(listForm);

		// エラーがあった場合
		if (!errors.isEmpty()) {
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		List<GrecipeRecipeInspectionList> list = listForm
				.getSearchInspectionList();
		for (int i = list.size() - 1; i >= 0; i--) {
			GrecipeRecipeInspectionList bean = list.get(i);

			// チェック無しの場合は削除対象外
			if (!bean.isCheckFlg()) {
				continue;
			}
			list.remove(i);
		}

		// Seqを振り直す
		for (int i = 0; i < list.size(); i++) {
			GrecipeRecipeInspectionList bean = list.get(i);
			bean.setSeq(new BigDecimal(i + 1));
		}
		listForm.setSearchInspectionList(list);

		if (getLog().isDebugEnabled()) {
			getLog().debug("△△△　dellist END　△△△");
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
			getLog().debug("▽▽▽　regist START　▽▽▽");
		}
		GrecipeInspectionListForm listForm = (GrecipeInspectionListForm) form;
		ActionMessages errors = grecipeInspectionListLogic
				.checkForRegist(listForm);

		// エラーがあった場合
		if (!errors.isEmpty()) {
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		// 更新の必要なし
		if (listForm.getSaveSearchInspectionList() == null
				&& listForm.getSearchInspectionList().size() == 0) {
			return mapping.findForward("success");
		}

		// 原処方検査情報更新処理
		grecipeInspectionListLogic.regist(listForm, getLoginInfo(request)
				.getTantoCd());

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		if (getLog().isDebugEnabled()) {
			getLog().debug("△△△　regist END　△△△");
		}
		return mapping.findForward("reSearch");
	}

	/**
	 * 再検索処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward reSearch(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("▽▽▽　reSearch START　▽▽▽");
		}

		// 共通部分を再設定する
		AbstractGrecipeForm commonForm = (AbstractGrecipeForm) form;
		String strRecipeId = commonForm.getRecipeId();
		commonForm.clear();
		commonForm.setRecipeId(strRecipeId); // レシピインデック
		commonForm.setTabId(getTabId()); // タブID

		// 共通情報再検索処理
		GrecipeRecipeHeaderList header = grecipeCommonsLogic
				.getCommonHeader(strRecipeId);
		setCommonHeaderInfo(commonForm, header);

		GrecipeInspectionListForm listForm = (GrecipeInspectionListForm) form;
		listForm.setSearchInspectionList(null);
		listForm.setSaveSearchInspectionList(null);
		listForm.setOperationCd(null);
		listForm.setOperationName(null);
		listForm.setDirtyFlg(null);
		listForm.setSelectProcedureStepNo(listForm.getSelectKeyStepNo());

		// 工程コード、工程名称を取得する
		String[] res = grecipeInspectionListLogic.getOperationName(listForm);
		listForm.setOperationCd(res[0]);
		listForm.setOperationName(res[1]);

		// 処方検査情報検索
		List<GrecipeRecipeInspectionList> searchList = grecipeInspectionListLogic
				.getSearchList(listForm);
		listForm.setSearchInspectionList(searchList);
		listForm.setSaveSearchInspectionList(searchList);

		/* リンクフラグセット */
		for (int i = 0; i < searchList.size(); i++) {
			searchList.get(i).setSrhLink(listForm.getSrhLink());
		}

		if (getLog().isDebugEnabled()) {
			getLog().debug("△△△　reSearch END　△△△");
		}
		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * 原処方検査タブ ロジッククラスを設定します。
	 * @param grecipeInspectionListLogic 原処方検査タブ ロジッククラス
	 */
	public void setGrecipeInspectionListLogic(
			final GrecipeInspectionListLogic grecipeInspectionListLogic) {
		this.grecipeInspectionListLogic = grecipeInspectionListLogic;
	}
}
