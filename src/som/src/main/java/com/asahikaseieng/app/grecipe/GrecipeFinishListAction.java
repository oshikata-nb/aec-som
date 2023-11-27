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

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeFormulaList;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;

/**
 * 原処方-仕上げ一覧 Actionクラス.
 * @author tosco
 */
public final class GrecipeFinishListAction extends AbstractGrecipeAction {

	/** 処方フォーミュラ検索 ロジッククラス */
	private GrecipeFinishListLogic grecipeFinishListLogic;

	/**
	 * コンストラクタ.原処方-仕上げ一覧 Actionクラス
	 */
	public GrecipeFinishListAction() {
		super();
	}

	/**
	 * タブID（GrecipeConstクラスで定義)を各子クラスで実装
	 * @return タブID
	 */
	@Override
	protected String getTabId() {
		// タブID-仕上げ一覧
		return GrecipeConst.FINISHLIST;
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

		GrecipeFinishListForm listForm = (GrecipeFinishListForm) form;
		listForm.setDirtyFlg(null);

		/* 権限取得 */
		getControlAuthority(request, listForm, Constants.MENU_ID_GRECIPE,
			Constants.TAB_ID_GRECIPE_FINISH);

		if (!listForm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 検索処理
		List<GrecipeRecipeFormulaList> searchList = grecipeFinishListLogic
				.getSearchList(new BigDecimal(listForm.getRecipeId()));
		listForm.setSearchFinishList(searchList);

		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);
		for (GrecipeRecipeFormulaList bean : searchList) {
			// 'HAIGO_RITU'でフォーマット
			bean.setStrQty(checker.format(GrecipeConst.UNIT_DIVISION_HAIGO,
				bean.getQty()));
		}

		// 数値フォーマット情報の設定
		setCheckDetail(checker, listForm);

		// 工程順序コンボボックスの作成
		listForm.setStepNoCombo(grecipeCommonsLogic
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

		GrecipeFinishListForm frm = (GrecipeFinishListForm) form;
		List<GrecipeRecipeFormulaList> searchList = frm.getSearchFinishList();

		// 工程データ未登録の場合エラー
		if (frm.getStepNoCombo().size() == 0) {
			ActionMessages errors = new ActionMessages();
			errors = GrecipeUtil
					.addError(errors, "errors.grecipe.no.procedure");
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		GrecipeRecipeFormulaList bean = new GrecipeRecipeFormulaList();
		bean.setRecipeId(new BigDecimal(frm.getRecipeId())); // レシピインデックス

		// 要素がない場合
		if (searchList.size() == 0) {
			frm.getSearchFinishList().add(bean);
		} else {
			// リスト追加ループ
			for (int i = 0; i < searchList.size(); i++) {
				GrecipeRecipeFormulaList recipeBean = searchList.get(i);
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
		for (GrecipeRecipeFormulaList formulaBeanint : searchList) {
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

		GrecipeFinishListForm frm = (GrecipeFinishListForm) form;
		List<GrecipeRecipeFormulaList> searchList = frm.getSearchFinishList();

		// 削除処理
		for (int i = searchList.size() - 1; i >= 0; i--) {
			GrecipeRecipeFormulaList recipeBean = searchList.get(i);

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

		GrecipeFinishListForm frm = (GrecipeFinishListForm) form;
		List<GrecipeRecipeFormulaList> searchList = frm.getSearchFinishList();
		ActionMessages errors = new ActionMessages();

		// 品目マスタ存在チェック
		errors = grecipeFinishListLogic.checkForRegist(searchList);
		if (!errors.isEmpty()) {
			// エラーがあった場合
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		// 処方プロシージャ更新処理
		grecipeFinishListLogic.regist(frm, getLoginInfo(request).getTantoCd());

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

		GrecipeFinishListForm frm = (GrecipeFinishListForm) form;

		// 検索結果リストクリア
		frm.setSearchFinishList(new ArrayList<GrecipeRecipeFormulaList>());

		return init(mapping, form, request, response);
	}

	/**
	 * 数値フォーマット情報の設定
	 * @param checker 数値項目用表示・チェックユーティリティ
	 * @param listForm 仕上げタブForm
	 */
	private void setCheckDetail(final CheckDigitUtilsLogic checker,
			final GrecipeFinishListForm listForm) {
		NumberChkDisitDetail checkDetail = checker.getCheckDigit(
			GrecipeConst.UNIT_DIVISION_HAIGO, null, null);
		// 少数点桁数
		listForm.setDecimalPoint(getBigDecimalString(checkDetail
				.getSmallnumLength()));
		// 端数区分
		listForm.setRoundDivision(getBigDecimalString(checkDetail
				.getRoundDivision()));
	}

	/* -------------------- setter -------------------- */

	/**
	 * 処方フォーミュラ検索 ロジッククラスを設定します。
	 * @param grecipeFinishListLogic 処方フォーミュラ検索 ロジッククラス
	 */
	public void setGrecipeFinishListLogic(
			final GrecipeFinishListLogic grecipeFinishListLogic) {
		this.grecipeFinishListLogic = grecipeFinishListLogic;
	}
}
