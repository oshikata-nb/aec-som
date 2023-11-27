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
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.comboboxes.SelectRecipeUse;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeFormulaList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeHeaderList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeHeaderListDao;

/**
 * 基本処方-配合一覧 Actionクラス.
 * @author tosco
 */
public final class MgrecipeFormulaListAction extends AbstractMgrecipeAction {

	/** 単位区分 "HAIGO" */
	private static final String UNIT_DIVISION_HAIGO = "HAIGO";

	/** 基本処方共通検索 ロジッククラス */
	private MgrecipeCommonsLogic mgrecipeCommonsLogic;

	/** 処方フォーミュラ検索 ロジッククラス */
	private MgrecipeFormulaListLogic mgrecipeFormulaListLogic;

	/** 処方ヘッダ検索 ロジッククラス */
	private MgrecipeHeaderLogic mgrecipeHeaderLogic;

	/**
	 * コンストラクタ.
	 */
	public MgrecipeFormulaListAction() {
		super();
	}

	/**
	 * タブID（MgrecipeConstクラスで定義)を各子クラスで実装
	 * @return タブID
	 */
	@Override
	protected String getTabId() {
		// タブID-配合一覧
		return MgrecipeConst.FORMULALIST;
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
		MgrecipeFormulaListForm listForm = (MgrecipeFormulaListForm) form;
		listForm.setDirtyFlg(null);

		/* 権限取得 */
		getControlAuthority(request, listForm, Constants.MENU_ID_MRECIPE,
			Constants.TAB_ID_MRECIPE_FORMULA);

		if (!listForm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 表示時処方ヘッダ情報を取得、設定
		listForm.setHeaderBean(mgrecipeHeaderLogic.findByPrimaryKey(listForm
				.getRecipeId(), RecipeHeaderListDao.RECIPE_TYPE_MASTER));

		// 工程順序コンボボックスの作成
		listForm.setSeqCombo(mgrecipeCommonsLogic
				.createProcedureSetpNoCombobox(listForm.getRecipeId(), true));

		// 配合量計の設定
		setTotalQty(request, listForm);

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

		MgrecipeFormulaListForm listForm = (MgrecipeFormulaListForm) form;
		listForm.setDirtyFlg(null);

		// 工程順序を保存する
		listForm.setTempProcStepNo(listForm.getProcStepNo());

		// 配合量計のデフォルト
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);
		listForm.setTotalQty(checker.format(UNIT_DIVISION_HAIGO,
			BigDecimal.ZERO));

		// 検索処理
		List<RecipeFormulaList> searchList = mgrecipeFormulaListLogic
				.getSearchList(new BigDecimal(listForm.getRecipeId()),
					new BigDecimal(listForm.getProcStepNo()));

		for (RecipeFormulaList bean : searchList) {
			// 配合量のフォーマット
			bean.setStrQty(checker.format(UNIT_DIVISION_HAIGO, bean.getQty()));
			// フォーマット情報を設定する
			setNumberChkDisitDetail(request, bean);

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

		MgrecipeFormulaListForm frm = (MgrecipeFormulaListForm) form;
		List<RecipeFormulaList> searchFormList = frm.getSearchFormList();

		ActionMessages errors = mgrecipeFormulaListLogic
				.checkForAddDelList(frm);
		// エラーがあった場合
		if (!errors.isEmpty()) {
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		RecipeFormulaList bean = new RecipeFormulaList();
		bean.setRecipeId(new BigDecimal(frm.getRecipeId())); // レシピインデックス
		bean.setRcpUse(frm.getRecipeUse()); // 用途
		bean.setProcSeqCombo(mgrecipeCommonsLogic
				.createProcedureSetpNoCombobox(frm.getRecipeId(), false));
		bean.setTonyu(BigDecimal.ONE);
		bean.setDataread(BigDecimal.ONE);

		// フォーマット情報を設定する
		setNumberChkDisitDetail(request, bean);

		// 要素がない場合
		if (searchFormList.size() == 0) {
			frm.getSearchFormList().add(bean);
		} else {
			// リスト追加ループ
			for (int i = 0; i < searchFormList.size(); i++) {
				RecipeFormulaList recipeBean = searchFormList.get(i);
				// チェックボックスがチェックされていた場合
				if (recipeBean.isCheckFlg()) {
					// 新しい要素を追加
					searchFormList.add(i, bean);
					break;
				}

				// チェックがない場合最後尾に追加
				if (i == frm.getSearchFormList().size() - 1) {
					// 新しい要素を追加
					searchFormList.add(bean);
					break;
				}
			}
		}

		// チェックボックスクリア処理
		for (RecipeFormulaList formulaBeanint : searchFormList) {
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

		MgrecipeFormulaListForm frm = (MgrecipeFormulaListForm) form;
		ActionMessages errors = mgrecipeFormulaListLogic
				.checkForAddDelList(frm);

		// エラーがあった場合
		if (!errors.isEmpty()) {
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		List<RecipeFormulaList> delList = frm.getDelFormList();
		// 削除処理
		for (int i = frm.getSearchFormList().size() - 1; i >= 0; i--) {
			RecipeFormulaList recipeBean = frm.getSearchFormList().get(i);

			if (!recipeBean.isCheckFlg()) {
				// チェック無は読み飛ばし
				continue;
			}
			// 行削除
			frm.getSearchFormList().remove(i);
			// 行削除データ追加
			delList.add(recipeBean);
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

		MgrecipeFormulaListForm frm = (MgrecipeFormulaListForm) form;
		List<RecipeFormulaList> searchFormList = frm.getSearchFormList();
		ActionMessages errors = new ActionMessages();

		// 品目マスタ存在チェック
		errors = mgrecipeFormulaListLogic.checkForRegist(searchFormList);
		if (!errors.isEmpty()) {
			// エラーがあった場合
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		// 処方プロシージャ更新処理
		mgrecipeFormulaListLogic
				.regist(frm, getLoginInfo(request).getTantoCd());

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

		// 共通部分を取得
		AbstractMgrecipeForm commonForm = (AbstractMgrecipeForm) form;
		String strRecipeId = commonForm.getRecipeId();

		// 固有部分を取得
		MgrecipeFormulaListForm listForm = (MgrecipeFormulaListForm) form;
		String strProcStepNo = listForm.getProcStepNo();

		// 共通部分を再設定
		commonForm.clear();
		commonForm.setRecipeId(strRecipeId); // レシピインデック
		commonForm.setTabId(getTabId()); // タブID

		// 共通情報再検索処理
		RecipeHeaderList header = mgrecipeCommonsLogic
				.getCommonHeader(strRecipeId);
		setCommonHeaderInfo(commonForm, header);
		// 表示時処方ヘッダ情報を取得、設定
		listForm.setHeaderBean(mgrecipeHeaderLogic.findByPrimaryKey(
			strRecipeId, RecipeHeaderListDao.RECIPE_TYPE_MASTER));

		// 固有部分再設定
		listForm.setSearchFormList(new ArrayList<RecipeFormulaList>());
		listForm.setDelFormList(new ArrayList<RecipeFormulaList>());
		listForm.setDirtyFlg(null);
		// 工程順序を保存する
		listForm.setProcStepNo(strProcStepNo);
		listForm.setTempProcStepNo(strProcStepNo);

		// 工程順序コンボボックスの作成
		listForm.setSeqCombo(mgrecipeCommonsLogic
				.createProcedureSetpNoCombobox(listForm.getRecipeId(), true));

		return search(mapping, form, request, response);
	}

	/**
	 * 配合量計の設定
	 * @param listForm
	 */
	private void setTotalQty(final HttpServletRequest request,
			final MgrecipeFormulaListForm listForm) {
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);
		// 用途が製造時の場合
		if (listForm.getRecipeUse().equals(
			SelectRecipeUse.RECIPE_USE_PRODUCTION)) {

			// 配合量計の取得
			RecipeFormulaList recipeFormulaList = mgrecipeFormulaListLogic
					.getSumQty(new BigDecimal(listForm.getRecipeId()),
						new BigDecimal(0));

			// 配合量計の設定
			if (recipeFormulaList.getSumQty() == null) {
				listForm.setTotalQty(checker.format(UNIT_DIVISION_HAIGO,
					BigDecimal.ZERO));
			} else {
				listForm.setTotalQty(checker.format(UNIT_DIVISION_HAIGO,
					recipeFormulaList.getSumQty()));
			}
		}
	}

	/**
	 * フォーマット情報を設定する
	 * @param request
	 * @param bean
	 */
	private void setNumberChkDisitDetail(final HttpServletRequest request,
			final RecipeFormulaList bean) {
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);
		NumberChkDisitDetail checkDetail = checker.getCheckDigit(
			UNIT_DIVISION_HAIGO, null, null);
		// 少数点桁数
		bean.setDecimalPoint(getBigDecimalString(checkDetail
				.getSmallnumLength()));
		// 端数区分
		bean.setRoundDivision(getBigDecimalString(checkDetail
				.getRoundDivision()));
	}

	/* -------------------- setter -------------------- */

	/**
	 * 基本処方検索 ロジッククラスを設定します。
	 * @param mgrecipeCommonsLogic 基本処方検索 ロジッククラス
	 */
	public void setMgrecipeCommonsLogic(
			final MgrecipeCommonsLogic mgrecipeCommonsLogic) {
		this.mgrecipeCommonsLogic = mgrecipeCommonsLogic;
	}

	/**
	 * 処方フォーミュラ検索 ロジッククラスを設定します。
	 * @param mgrecipeFormulaListLogic 処方フォーミュラ検索 ロジッククラス
	 */
	public void setMgrecipeFormulaListLogic(
			final MgrecipeFormulaListLogic mgrecipeFormulaListLogic) {
		this.mgrecipeFormulaListLogic = mgrecipeFormulaListLogic;
	}

	/**
	 * 処方ヘッダ検索 ロジッククラスを設定します。
	 * @param mgrecipeHeaderLogic 処方ヘッダ検索 ロジッククラス
	 */
	public void setMgrecipeHeaderLogic(
			final MgrecipeHeaderLogic mgrecipeHeaderLogic) {
		this.mgrecipeHeaderLogic = mgrecipeHeaderLogic;
	}

}
