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

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeFormulaList;

/**
 * 基本処方-仕上げ一覧 Actionクラス.
 * @author tosco
 */
public final class MgrecipeFinishListAction extends AbstractMgrecipeAction {

	/** 処方フォーミュラ検索 ロジッククラス */
	private MgrecipeFinishListLogic mgrecipeFinishListLogic;

	/** 基本処方検索 ロジッククラス */
	private MgrecipeCommonsLogic mgrecipeCommonsLogic;

	/** 単位区分 "HAIGO" */
	private static final String UNIT_DIVISION_HAIGO = "HAIGO";

	/**
	 * コンストラクタ.
	 */
	public MgrecipeFinishListAction() {
		super();
	}

	/**
	 * タブID（MgrecipeConstクラスで定義)を各子クラスで実装
	 * @return タブID
	 */
	@Override
	protected String getTabId() {
		// タブID-仕上げ一覧
		return MgrecipeConst.FINISHLIST;
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

		MgrecipeFinishListForm listForm = (MgrecipeFinishListForm) form;
		listForm.setDirtyFlg(null);

		/* 権限取得 */
		getControlAuthority(request, listForm, Constants.MENU_ID_MRECIPE,
			Constants.TAB_ID_MRECIPE_FINISH);

		if (!listForm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 検索処理
		List<RecipeFormulaList> searchList = mgrecipeFinishListLogic
				.getSearchList(new BigDecimal(listForm.getRecipeId()));
		listForm.setSearchFinishList(searchList);

		for (RecipeFormulaList bean : searchList) {
			// 発生予定数量のフォーマット
			setFormatDiv(request, listForm, bean);
		}

		// 工程順序コンボボックスの作成
		listForm.setStepNoCombo(mgrecipeCommonsLogic
				.createProcedureSetpNoCombobox(listForm.getRecipeId(), false));

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

		MgrecipeFinishListForm frm = (MgrecipeFinishListForm) form;
		List<RecipeFormulaList> searchList = frm.getSearchFinishList();

		// 工程データ未登録の場合エラー
		if (frm.getStepNoCombo().size() == 0) {
			ActionMessages errors = new ActionMessages();
			errors = MgrecipeUtil.addError(errors,
				"errors.mgrecipe.no.procedure");
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		RecipeFormulaList bean = new RecipeFormulaList();
		bean.setRecipeId(new BigDecimal(frm.getRecipeId())); // レシピインデックス
		// 発生予定数量のフォーマット
		setFormatDiv(request, frm, bean);

		// 要素がない場合
		if (searchList.size() == 0) {
			frm.getSearchFinishList().add(bean);
		} else {
			// リスト追加ループ
			for (int i = 0; i < searchList.size(); i++) {
				RecipeFormulaList recipeBean = searchList.get(i);
				// チェックボックスがチェックされていた場合
				if (recipeBean.isCheckFlg()) {
					// 新しい要素を追加
					searchList.add(i, bean);
					break;
				}

				// チェックがない場合最後尾に追加
				if (i == searchList.size() - 1) {
					// 新しい要素を追加
					frm.getSearchFinishList().add(bean);
					break;
				}
			}
		}

		// チェックボックスクリア処理
		for (RecipeFormulaList formulaBeanint : searchList) {
			formulaBeanint.setCheckFlg(Boolean.FALSE);
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

		MgrecipeFinishListForm frm = (MgrecipeFinishListForm) form;
		List<RecipeFormulaList> searchList = frm.getSearchFinishList();

		// 削除処理
		for (int i = searchList.size() - 1; i >= 0; i--) {
			RecipeFormulaList recipeBean = searchList.get(i);

			if (!recipeBean.isCheckFlg()) {
				// チェック無は読み飛ばし
				continue;
			}

			// 行削除
			searchList.remove(i);
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

		MgrecipeFinishListForm frm = (MgrecipeFinishListForm) form;
		List<RecipeFormulaList> searchList = frm.getSearchFinishList();
		ActionMessages errors = new ActionMessages();

		// 品目マスタ存在チェック
		errors = mgrecipeFinishListLogic.checkForRegist(searchList);
		if (!errors.isEmpty()) {
			// エラーがあった場合
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		// 処方プロシージャ更新処理
		mgrecipeFinishListLogic.regist(frm, getLoginInfo(request).getTantoCd());

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("reInit");
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
	public ActionForward reInit(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("reInit");
		}

		MgrecipeFinishListForm frm = (MgrecipeFinishListForm) form;

		// 検索結果リストクリア
		frm.setSearchFinishList(new ArrayList<RecipeFormulaList>());

		return init(mapping, form, request, response);
	}

	/**
	 * 発生予定数量のフォーマット
	 * @param request HttpServletRequest
	 * @param listForm 仕上げデータ
	 * @param bean 処方フォーミュラ
	 */
	private void setFormatDiv(final HttpServletRequest request,
			final MgrecipeFinishListForm listForm, final RecipeFormulaList bean) {
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);
		NumberChkDisitDetail checkDetail = new NumberChkDisitDetail();

		// 'HAIGO'でフォーマット
		bean.setStrQty(checker.format(UNIT_DIVISION_HAIGO, bean.getQty()));
		checkDetail = checker.getCheckDigit(UNIT_DIVISION_HAIGO, null, null);
		// 少数点桁数
		bean.setDecimalPoint(getBigDecimalString(checkDetail
				.getSmallnumLength()));
		// 端数区分
		bean.setRoundDivision(getBigDecimalString(checkDetail
				.getRoundDivision()));
	}

	/* -------------------- setter -------------------- */

	/**
	 * 処方フォーミュラ検索 ロジッククラスを設定します。
	 * @param mgrecipeFinishListLogic 処方フォーミュラ検索 ロジッククラス
	 */
	public void setMgrecipeFinishListLogic(
			final MgrecipeFinishListLogic mgrecipeFinishListLogic) {
		this.mgrecipeFinishListLogic = mgrecipeFinishListLogic;
	}

	/**
	 * 基本処方検索 ロジッククラスを設定します。
	 * @param mgrecipeCommonsLogic 基本処方検索 ロジッククラス
	 */
	public void setMgrecipeCommonsLogic(
			final MgrecipeCommonsLogic mgrecipeCommonsLogic) {
		this.mgrecipeCommonsLogic = mgrecipeCommonsLogic;
	}

}
