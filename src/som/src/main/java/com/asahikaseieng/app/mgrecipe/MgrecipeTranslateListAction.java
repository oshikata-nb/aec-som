/*
 * Created on 2009/01/15
 *
 * $copyright$
 * 翻訳検索
 *
 */
package com.asahikaseieng.app.mgrecipe;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeTranslateListPagerCondition;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeHeaderList;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 基本処方-翻訳検索 Actionクラス.
 * @author tosco
 * 
 */
public final class MgrecipeTranslateListAction extends AbstractSearchAction {

	/** 翻訳検索のロジッククラス */
	private MgrecipeTranslateListLogic mgrecipeTranslateListLogic;

	/** 基本処方共通ロジッククラス */
	private MgrecipeCommonsLogic mgrecipeCommonsLogic;

	/**
	 * コンストラクタ.
	 */
	public MgrecipeTranslateListAction() {
		super();
	}

	/**
	 * 初期処理(メニューから遷移時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	protected ActionForward initImpl(final ActionMapping mapping,
			final AbstractSearchForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		MgrecipeTranslateListForm listForm = (MgrecipeTranslateListForm) form;

		/* 権限取得 */
		getControlAuthority(request, listForm, Constants.MENU_ID_MRECIPE,
			Constants.TAB_ID_MRECIPE_HEADER);

		if (!listForm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// ステータスコンボボックス
		listForm.setStatusCombo(mgrecipeCommonsLogic
				.createStatusAllCombobox(true));

		return mapping.findForward("success");
	}

	/**
	 * 翻訳検索－検索処理(検索ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	protected ActionForward searchImpl(final ActionMapping mapping,
			final HttpServletRequest request, final AbstractSearchForm form)
			throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("search.");
		}
		MgrecipeTranslateListForm listForm = (MgrecipeTranslateListForm) form;
		// クリア
		listForm.setSearchList(new ArrayList<RecipeHeaderList>());

		// 検索条件を取得
		MgrecipeTranslateListPagerCondition condition = (MgrecipeTranslateListPagerCondition) listForm
				.getPager().getPagerCondition();

		// 検索条件をセット
		condition.setRecipeCd(listForm.getRecipeCd()); // 基本処方コード
		condition.setRecipeVersion(listForm.getRecipeVersion()); // バージョン
		condition.setRecipeUse(listForm.getRecipeUse()); // 用途
		condition.setProduct(listForm.getProduct()); // 品目コード
		condition.setOtherCompanyCd1(listForm.getOtherCompanyCd1()); // 他社コード１
		String status = listForm.getRecipeStatus(); // ステータス
		if (MgrecipeConst.COMBO_ALL_VALUE.equals(status)) {
			// ステータス=0:すべての場合
			condition.setRecipeStatus(null);
		} else {
			// ステータス=0:すべて以外の場合
			condition.setRecipeStatus(status);
		}

		// String approvalStatus = listForm.getApprovalStatus(); // 承認ステータス
		String approvalStatus = "3"; // 承認ステータス

		if (MgrecipeConst.COMBO_ALL_VALUE.equals(approvalStatus)) {
			// 承認ステータス=0:すべての場合
			condition.setApprovalStatus(null);
		} else {
			// 承認ステータス=0:すべて以外の場合
			condition.setApprovalStatus(approvalStatus);
		}

		condition
				.setLatestVersion(Boolean.toString(listForm.isLatestVersion())); // 最新バージョン
		condition.setExplicate(Boolean.toString(listForm.isExplicate())); // 未展開

		// 処方ヘッダを検索
		List<RecipeHeaderList> result = mgrecipeTranslateListLogic
				.getSearchList(condition);
		listForm.setSearchList(result);

		return mapping.findForward("success");
	}

	/**
	 * フォームに表示されている項目のクリア処理.
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward clear(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("clear.");
		}
		MgrecipeTranslateListForm listForm = (MgrecipeTranslateListForm) form;
		// クリア
		listForm.clear();
		return mapping.findForward("success");
	}

	/**
	 * キャンセルボタン押下処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward cancel(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("cancel.");
		}
		return mapping.findForward("cancel");
	}

	/**
	 * 空処方作成ボタン押下処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward cleanNew(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("cleanNew.");
		}
		return mapping.findForward("cleanNew");
	}

	/* -------------------- setter -------------------- */

	/**
	 * 翻訳検索のロジッククラスを設定します。
	 * @param mgrecipeTranslateListLogic 翻訳検索のロジッククラス
	 */
	public void setMgrecipeTranslateListLogic(
			final MgrecipeTranslateListLogic mgrecipeTranslateListLogic) {
		this.mgrecipeTranslateListLogic = mgrecipeTranslateListLogic;
	}

	/**
	 * 基本処方共通ロジッククラスを設定します。
	 * @param mgrecipeCommonsLogic 基本処方共通ロジッククラス
	 */
	public void setMgrecipeCommonLogic(
			final MgrecipeCommonsLogic mgrecipeCommonsLogic) {
		this.mgrecipeCommonsLogic = mgrecipeCommonsLogic;
	}

}
