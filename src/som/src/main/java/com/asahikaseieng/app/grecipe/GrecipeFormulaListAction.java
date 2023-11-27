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
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.comboboxes.SelectRecipeUse;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeFormulaList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeHeaderList;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;

/**
 * 原処方-配合一覧 Actionクラス.
 * @author tosco
 */
public final class GrecipeFormulaListAction extends AbstractGrecipeAction {

	/** 処方フォーミュラ検索 ロジッククラス */
	private GrecipeFormulaListLogic grecipeFormulaListLogic;

	/**
	 * コンストラクタ.
	 */
	public GrecipeFormulaListAction() {
		super();
	}

	/**
	 * タブID（MgrecipeConstクラスで定義)を各子クラスで実装
	 * @return タブID
	 */
	@Override
	protected String getTabId() {
		// タブID-配合一覧
		return GrecipeConst.FORMULALIST;
	}

	/**
	 * 共通ヘッダー情報を画面のFormに設定する。
	 * @param form 画面のForm
	 * @param header 共通ヘッダー情報
	 * @return AbstractGrecipeForm
	 */
	@Override
	protected AbstractGrecipeForm setCommonHeaderInfo(
			final AbstractGrecipeForm form, final GrecipeRecipeHeaderList header) {
		GrecipeFormulaListForm listForm = (GrecipeFormulaListForm) super
				.setCommonHeaderInfo(form, header);
		// 表示時処方ヘッダ情報を取得、設定
		listForm.setHeaderBean(header);
		return form;
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
		GrecipeFormulaListForm listForm = (GrecipeFormulaListForm) form;
		listForm.setDirtyFlg(null);

		/* 権限取得 */
		getControlAuthority(request, listForm, Constants.MENU_ID_GRECIPE,
			Constants.TAB_ID_GRECIPE_FORMULA);

		if (!listForm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 工程順序コンボボックスの作成
		listForm.setSeqCombo(grecipeCommonsLogic.createProcedureSetpNoCombobox(
			listForm.getRecipeId(), true));

		// 配合量計の設定
		setTotalQty(request, listForm);

		// 画面上の自動計算用に小数点位置、端数区分を設定
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);
		NumberChkDisitDetail checkDetail = checker.getCheckDigit(
			GrecipeConst.UNIT_DIVISION_HAIGO, null, null);
		// 少数点桁数
		listForm.setDecimalPoint(getBigDecimalString(checkDetail
				.getSmallnumLength()));
		// 端数区分
		listForm.setRoundDivision(getBigDecimalString(checkDetail
				.getRoundDivision()));

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
			getLog().debug("search.");
		}

		GrecipeFormulaListForm listForm = (GrecipeFormulaListForm) form;
		listForm.setDirtyFlg(null);

		// 工程順序を保存する
		listForm.setTempProcStepNo(listForm.getProcStepNo());

		// 配合量計のデフォルト
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);
		listForm.setTotalQty(checker.format(GrecipeConst.UNIT_DIVISION_HAIGO,
			BigDecimal.ZERO));

		// 検索処理
		List<GrecipeRecipeFormulaList> searchList = grecipeFormulaListLogic
				.getSearchList(new BigDecimal(listForm.getRecipeId()),
					new BigDecimal(listForm.getProcStepNo()));

		for (GrecipeRecipeFormulaList bean : searchList) {
			// 配合量のフォーマット
			bean.setStrQty(checker.format(GrecipeConst.UNIT_DIVISION_HAIGO,
				bean.getQty()));
		}

		/* リンクフラグセット */
		for (int i = 0; i < searchList.size(); i++) {
			searchList.get(i).setSrhLink(listForm.getSrhLink());
		}

		listForm.setSearchFormList(searchList);

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

		GrecipeFormulaListForm frm = (GrecipeFormulaListForm) form;
		List<GrecipeRecipeFormulaList> searchFormList = frm.getSearchFormList();

		ActionMessages errors = grecipeFormulaListLogic.checkForAddDelList(frm);
		// エラーがあった場合
		if (!errors.isEmpty()) {
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		GrecipeRecipeFormulaList bean = new GrecipeRecipeFormulaList();
		bean.setRecipeId(new BigDecimal(frm.getRecipeId())); // レシピインデックス
		bean.setRcpUse(frm.getRecipeUse()); // 用途
		bean.setProcSeqCombo(grecipeCommonsLogic.createProcedureSetpNoCombobox(
			frm.getRecipeId(), false));
		bean.setTonyu(BigDecimal.ONE);
		bean.setDataread(BigDecimal.ONE);

		// 要素がない場合
		if (searchFormList.isEmpty()) {
			frm.getSearchFormList().add(bean);
		} else {
			// リスト追加ループ
			int num = searchFormList.size();
			boolean isAdd = false;
			for (int i = 0; i < num; i++) {
				GrecipeRecipeFormulaList recipeBean = searchFormList.get(i);
				// チェックボックスがチェックされていた場合は、チェックされていた行の前に追加する
				if (recipeBean.isCheckFlg()) {
					// 新しい要素を追加
					searchFormList.add(i, bean);
					isAdd = true;
					break;
				}
			}
			// チェックがない場合、最後尾に追加
			if (!isAdd) {
				// 新しい要素を追加
				searchFormList.add(bean);
			}
		}

		// チェックボックスクリア処理
		for (GrecipeRecipeFormulaList formulaBeanint : searchFormList) {
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

		GrecipeFormulaListForm frm = (GrecipeFormulaListForm) form;
		ActionMessages errors = grecipeFormulaListLogic.checkForAddDelList(frm);

		// エラーがあった場合
		if (!errors.isEmpty()) {
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		List<GrecipeRecipeFormulaList> delList = frm.getDelFormList();
		// 削除処理
		for (int i = frm.getSearchFormList().size() - 1; i >= 0; i--) {
			GrecipeRecipeFormulaList recipeBean = frm.getSearchFormList()
					.get(i);
			// チェックが付いている行を削除する
			if (recipeBean.isCheckFlg()) {
				// 行削除
				frm.getSearchFormList().remove(i);
				// 行削除データ追加
				delList.add(recipeBean);
			}
		}
		// 行削除データ設定
		frm.setDelFormList(delList);

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

		GrecipeFormulaListForm frm = (GrecipeFormulaListForm) form;
		List<GrecipeRecipeFormulaList> searchFormList = frm.getSearchFormList();
		ActionMessages errors = new ActionMessages();

		// 品目マスタ存在チェック
		errors = grecipeFormulaListLogic.checkForRegist(searchFormList);
		if (!errors.isEmpty()) {
			// エラーがあった場合
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		// 処方フォーミュラ更新処理
		grecipeFormulaListLogic.regist(frm, getLoginInfo(request).getTantoCd());

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

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
			getLog().debug("reSearch");
		}

		// 固有部分を取得
		GrecipeFormulaListForm listForm = (GrecipeFormulaListForm) form;
		String strProcStepNo = listForm.getProcStepNo();

		// 初期表示処理
		init(mapping, form, request, response);

		// 固有部分再設定
		listForm.setSearchFormList(new ArrayList<GrecipeRecipeFormulaList>());
		listForm.setDelFormList(new ArrayList<GrecipeRecipeFormulaList>());
		// 工程順序を保存する
		listForm.setProcStepNo(strProcStepNo);
		listForm.setTempProcStepNo(strProcStepNo);

		return search(mapping, form, request, response);
	}

	/**
	 * 配合量計の設定
	 * @param listForm
	 */
	private void setTotalQty(final HttpServletRequest request,
			final GrecipeFormulaListForm listForm) {
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);
		// 用途が製造時の場合
		if (listForm.getRecipeUse().equals(
			SelectRecipeUse.RECIPE_USE_PRODUCTION)) {
			// 配合量計の取得
			GrecipeRecipeFormulaList recipeFormulaList = grecipeFormulaListLogic
					.getSumQty(new BigDecimal(listForm.getRecipeId()),
						new BigDecimal(0));

			if (recipeFormulaList.getSumQty() == null) {
				recipeFormulaList.setSumQty(BigDecimal.ZERO);
			}
			// 配合量計の設定
			listForm.setTotalQty(checker
					.format(GrecipeConst.UNIT_DIVISION_HAIGO, recipeFormulaList
							.getSumQty()));
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 処方フォーミュラ検索 ロジッククラスを設定します。
	 * @param grecipeFormulaListLogic 処方フォーミュラ検索 ロジッククラス
	 */
	public void setGrecipeFormulaListLogic(
			final GrecipeFormulaListLogic grecipeFormulaListLogic) {
		this.grecipeFormulaListLogic = grecipeFormulaListLogic;
	}

}
