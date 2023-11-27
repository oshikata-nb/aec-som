/*
 * Created on 2009/03/16
 *
 * $copyright$
 * 原処方-一覧検索
 *
 */
package com.asahikaseieng.app.grecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.mgrecipe.RecipeExcelDecorator;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeListPagerCondition;
import com.asahikaseieng.dao.nonentity.recipeforreport.RecipeReportConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 原処方-一覧検索 Actionクラス.
 * @author tosco
 * 
 */
public final class GrecipeListAction extends AbstractSearchAction {

	/** 一覧検索のロジッククラス */
	private GrecipeListLogic grecipeListLogic;

	/** 基本処方共通ロジッククラス */
	private GrecipeCommonsLogic grecipeCommonsLogic;

	/** 帳票（Excel）用エクセルデコレーター */
	private RecipeExcelDecorator recipeExcelDecorator;

	/* 「％」 */
	private static final String STR_PERCENT;
	static {
		ResourceBundle rb = Constants.RB_APPLICATION_PROPERTIES;
		STR_PERCENT = rb.getString("item.tani.percent");
	}

	/**
	 * コンストラクタ.
	 */
	public GrecipeListAction() {
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

		GrecipeListForm listForm = (GrecipeListForm) form;
		// ステータスコンボボックス
		listForm.setStatusCombo(grecipeCommonsLogic.createStatusAllCombobox());

		// 初期検索設定
		GrecipeListPagerCondition condition = (GrecipeListPagerCondition) listForm
				.getPager().getPagerCondition();
		listForm.setRecipeStatus(GrecipeConst.COMBO_ALL_VALUE);
		condition.setRecipeUse(GrecipeConst.RECIPE_USE_PRODUCTION.toString());

		return mapping.findForward("success");
	}

	/**
	 * 一覧検索－検索処理(検索ボタン押下時)
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
		GrecipeListForm listForm = (GrecipeListForm) form;
		// クリア
		listForm.setSearchList(new ArrayList<GrecipeListBean>());

		// Excelダウンロードフラグクリア
		listForm.setExcelDownloadFlg(false);

		// 検索条件を取得
		GrecipeListPagerCondition condition = (GrecipeListPagerCondition) listForm
				.getPager().getPagerCondition();

		if (listForm.getOp().equals("reSearch")) {
			// 他画面から戻ってきた場合
			listForm.setRecipeCd(removeLikeString(condition.getRecipeCd())); // 基本処方コード
			listForm.setRecipeVersion(removeLikeString(condition
					.getRecipeVersion())); // バージョン
			listForm.setRecipeUse(condition.getRecipeUse()); // 用途
			listForm.setProduct(removeLikeString(condition.getItemCd())); // 品目コード
			listForm.setOtherCompanyCd1(condition.getOtherCompanyCd1()); // 他社コード１
			if (StringUtils.isEmpty(condition.getRecipeStatus())) {
				// ステータス=0:すべての場合
				listForm.setRecipeStatus(GrecipeConst.COMBO_ALL_VALUE);
			} else {
				// ステータス=0:すべて以外の場合
				listForm.setRecipeStatus(condition.getRecipeStatus());
			}

			if (StringUtils.isEmpty(condition.getApprovalStatus())) {
				// 承認ステータス=0:すべての場合
				listForm.setApprovalStatus(GrecipeConst.COMBO_ALL_VALUE);
			} else {
				// 承認ステータス=0:すべて以外の場合
				listForm.setApprovalStatus(condition.getApprovalStatus());
			}
		} else {
			// 通常検索
			// 検索条件をセット
			condition.setRecipeCd(listForm.getRecipeCd()); // 基本処方コード
			condition.setRecipeVersion(listForm.getRecipeVersion()); // バージョン
			condition.setRecipeUse(listForm.getRecipeUse()); // 用途
			condition.setItemCd(listForm.getProduct()); // 品目コード
			condition.setOtherCompanyCd1(listForm.getOtherCompanyCd1()); // 他社コード１
			String status = listForm.getRecipeStatus(); // ステータス
			if (GrecipeConst.COMBO_ALL_VALUE.equals(status)) {
				// ステータス=0:すべての場合
				condition.setRecipeStatus(null);
			} else {
				// ステータス=0:すべて以外の場合
				condition.setRecipeStatus(status);
			}

			String approvalStatus = listForm.getApprovalStatus(); // 承認ステータス

			if (GrecipeConst.COMBO_ALL_VALUE.equals(approvalStatus)) {
				// 承認ステータス=0:すべての場合
				condition.setApprovalStatus(null);
			} else {
				// 承認ステータス=0:すべて以外の場合
				condition.setApprovalStatus(approvalStatus);
			}
		}
		// 帳票(Excel)用に検索条件を保持
		RecipeReportConditionForReport reportCondition = new RecipeReportConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);

		// 品目コードだけ基本処方と異なるので手動でセット
		reportCondition.setProduct(condition.getItemCd());

		listForm.setCondition(reportCondition);

		// 処方ヘッダを検索
		List<GrecipeListBean> result = grecipeListLogic
				.getSearchList(condition);
		listForm.setSearchList(result);

		return mapping.findForward("success");
	}

	/**
	 * 帳票(Excel)ボタン押下時
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward report(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		GrecipeListForm frm = (GrecipeListForm) form;

		// Excelダウンロードフラグクリア
		frm.setExcelDownloadFlg(false);

		frm.getCondition().setRecipeType("1");

		FileDownloadInfo info = null;

		/* 帳票(Excel) */
		info = recipeExcelDecorator.createReport(frm.getCondition());
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		// ExcelダウンロードフラグＯＮ
		frm.setExcelDownloadFlg(true);

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
		GrecipeListForm listForm = (GrecipeListForm) form;
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

	/**
	 * LIKE検索用の文字列を削除する
	 * @param s 検索用文字列
	 * @return LIKE用の文字列を削除したもの
	 */
	private String removeLikeString(final String s) {
		String res = s;
		if (StringUtils.isNotEmpty(s)) {
			StringBuilder buf = new StringBuilder(s);
			String left = buf.substring(0, 1);
			if (left.equals(STR_PERCENT)) {
				buf = buf.delete(0, 1);
			}
			int length = buf.length();
			String right = buf.substring(length - 1, length);
			if (right.equals(STR_PERCENT)) {
				buf = buf.delete(length - 1, length);
			}
			res = buf.toString();
		}
		return res;
	}

	/* -------------------- setter -------------------- */

	/**
	 * 一覧検索のロジッククラスを設定します。
	 * @param grecipeListLogic 一覧検索のロジッククラス
	 */
	public void setGrecipeListLogic(final GrecipeListLogic grecipeListLogic) {
		this.grecipeListLogic = grecipeListLogic;
	}

	/**
	 * 基本処方共通ロジッククラスを設定します。
	 * @param grecipeCommonsLogic 基本処方共通ロジッククラス
	 */
	public void setGrecipeCommonsLogic(
			final GrecipeCommonsLogic grecipeCommonsLogic) {
		this.grecipeCommonsLogic = grecipeCommonsLogic;
	}

	/**
	 * 帳票(Excel)用
	 * @param recipeExcelDecorator 帳票(Excel)用
	 */
	public void setRecipeExcelDecorator(
			final RecipeExcelDecorator recipeExcelDecorator) {
		this.recipeExcelDecorator = recipeExcelDecorator;
	}
}
