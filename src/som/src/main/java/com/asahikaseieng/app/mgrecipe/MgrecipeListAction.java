/*
 * Created on 2009/01/14
 *
 * $copyright$
 * 基本処方検索
 *
 */
package com.asahikaseieng.app.mgrecipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeListPagerCondition;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeHeaderList;
import com.asahikaseieng.dao.nonentity.recipeforreport.RecipeReportConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 基本処方検索 Actionクラス.
 * @author tosco
 * 
 */
public final class MgrecipeListAction extends AbstractSearchAction {

	/** 基本処方共通ロジッククラス */
	private MgrecipeCommonsLogic mgrecipeCommonsLogic;

	/** 基本処方検索のロジッククラス */
	private MgrecipeListLogic mgrecipeListLogic;

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
	public MgrecipeListAction() {
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

		MgrecipeListForm listForm = (MgrecipeListForm) form;
		listForm.clear();
		// 生産ラインコンボボックス
		listForm.setLineCombo(mgrecipeCommonsLogic.createLineCombobox(false));
		// ステータスコンボボックス
		listForm.setStatusCombo(mgrecipeCommonsLogic
				.createStatusAllCombobox(false));

		// 初期検索設定
		MgrecipeListPagerCondition condition = (MgrecipeListPagerCondition) listForm
				.getPager().getPagerCondition();
		listForm.setRecipeStatus(MgrecipeConst.COMBO_ALL_VALUE);
		condition.setRecipeUse(MgrecipeConst.RECIPE_USE_PRODUCTION.toString());

		if (listForm.getLineCombo() != null) {
			for (ComboBoxItems item : listForm.getLineCombo()) {
				// 最初の1件目の値を設定
				listForm.setProductionLine(item.getValues());
				condition.setProductionLine(item.getValues());
				break;
			}
		}

		return mapping.findForward("success");
	}

	/**
	 * 基本処方検索－検索処理(検索ボタン押下時)
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

		MgrecipeListForm listForm = (MgrecipeListForm) form;

		// クリア
		listForm.setSearchList(new ArrayList<RecipeHeaderList>());

		// Excelダウンロードフラグクリア
		listForm.setExcelDownloadFlg(false);

		// 検索条件を取得
		MgrecipeListPagerCondition condition = (MgrecipeListPagerCondition) listForm
				.getPager().getPagerCondition();
		if (listForm.getOp().equals("reSearch")) {
			// 他画面から戻ってきた場合
			listForm.setRecipeCd(removeLikeString(condition.getRecipeCd())); // 基本処方コード
			listForm.setRecipeVersion(removeLikeString(condition
					.getRecipeVersion())); // バージョン
			listForm.setRecipeUse(condition.getRecipeUse()); // 用途
			listForm.setProductionLine(condition.getProductionLine()); // 生産工場
			listForm.setProduct(removeLikeString(condition.getProduct())); // 品目コード
			listForm.setRecipeName(removeLikeString(condition.getRecipeName())); // 基本処方名称
			listForm.setOtherCompanyCd1(condition.getOtherCompanyCd1()); // 他社コード１

			if (StringUtils.isEmpty(condition.getProductionLine())) {
				// 生産工場=0:すべての場合
				listForm.setProductionLine(MgrecipeConst.COMBO_ALL_VALUE);
			} else {
				// 生産工場=0:すべて以外の場合
				listForm.setProductionLine(condition.getProductionLine());
			}

			if (StringUtils.isEmpty(condition.getRecipeStatus())) {
				// ステータス=0:すべての場合
				listForm.setRecipeStatus(MgrecipeConst.COMBO_ALL_VALUE);
			} else {
				// ステータス=0:すべて以外の場合
				listForm.setRecipeStatus(condition.getRecipeStatus());
			}

			if (StringUtils.isEmpty(condition.getApprovalStatus())) {
				// 承認ステータス=0:すべての場合
				listForm.setApprovalStatus(MgrecipeConst.COMBO_ALL_VALUE);
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
			condition.setProductionLine(listForm.getProductionLine()); // 生産工場
			condition.setProduct(listForm.getProduct()); // 品目コード
			condition.setRecipeName(listForm.getRecipeName()); // 基本処方名称
			condition.setOtherCompanyCd1(listForm.getOtherCompanyCd1()); // 他社コード１

			String productionLine = listForm.getProductionLine(); // 生産工場

			if (MgrecipeConst.COMBO_ALL_VALUE.equals(productionLine)) {
				// 生産工場=0:すべての場合
				condition.setProductionLine(null);
			} else {
				// 生産工場=0:すべて以外の場合
				condition.setProductionLine(productionLine);
			}

			String status = listForm.getRecipeStatus(); // ステータス

			if (MgrecipeConst.COMBO_ALL_VALUE.equals(status)) {
				// ステータス=0:すべての場合
				condition.setRecipeStatus(null);
			} else {
				// ステータス=0:すべて以外の場合
				condition.setRecipeStatus(status);
			}

			String approvalStatus = listForm.getApprovalStatus(); // 承認ステータス

			if (MgrecipeConst.COMBO_ALL_VALUE.equals(approvalStatus)) {
				// 承認ステータス=0:すべての場合
				condition.setApprovalStatus(null);
			} else {
				// 承認ステータス=0:すべて以外の場合
				condition.setApprovalStatus(approvalStatus);
			}
		}

		// 処方ヘッダを検索
		List<RecipeHeaderList> result = mgrecipeListLogic
				.getSearchList(condition);

		// 帳票(Excel)用に検索条件を保持
		RecipeReportConditionForReport reportCondition = new RecipeReportConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		listForm.setCondition(reportCondition);

		listForm.setSearchList(result);
		// 帳票印刷チェックボックス生成
		boolean[] printChecks = new boolean[result.size()];
		Arrays.fill(printChecks, false);
		listForm.setPrintCheck(printChecks);

		return mapping.findForward("success");
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

		MgrecipeListForm frm = (MgrecipeListForm) form;
		// Excelダウンロードフラグクリア
		frm.setExcelDownloadFlg(false);

		frm.getCondition().setRecipeType("3");

		FileDownloadInfo info = null;

		/* 帳票(Excel) */
		info = recipeExcelDecorator.createReport(frm.getCondition());
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		// ExcelダウンロードフラグＯＮ
		frm.setExcelDownloadFlg(true);

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * 基本処方検索のロジッククラスを設定します。
	 * @param mgrecipeListLogic 基本処方検索のロジッククラス
	 */
	public void setMgrecipeListLogic(final MgrecipeListLogic mgrecipeListLogic) {
		this.mgrecipeListLogic = mgrecipeListLogic;
	}

	/**
	 * 基本処方共通ロジッククラスを設定します。
	 * @param mgrecipeCommonsLogic 基本処方共通ロジッククラス
	 */
	public void setMgrecipeCommonLogic(
			final MgrecipeCommonsLogic mgrecipeCommonsLogic) {
		this.mgrecipeCommonsLogic = mgrecipeCommonsLogic;
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
