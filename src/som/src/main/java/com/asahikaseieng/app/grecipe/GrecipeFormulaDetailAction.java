/*
 * Created on 2009/03/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.grecipe;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.comboboxes.SelectRecipeUse;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeFormulaList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeHeaderList;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 原処方-配合詳細 Actionクラス.
 * @author tosco
 */
public final class GrecipeFormulaDetailAction extends AbstractAction {

	/** 処方フォーミュラ配合詳細検索 ロジッククラス */
	private GrecipeFormulaDetailLogic grecipeFormulaDetailLogic;

	/** 数値桁数チェック ロジッククラス */
	private CheckDigitUtilsLogic checker;

	/**
	 * コンストラクタ.
	 */
	public GrecipeFormulaDetailAction() {
		super();
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
	public ActionForward init(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("init");
		}
		GrecipeFormulaDetailForm detailForm = (GrecipeFormulaDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, detailForm, Constants.MENU_ID_GRECIPE,
			Constants.TAB_ID_GRECIPE_FORMULA);

		if (!detailForm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 処方ヘッダ検索処理
		GrecipeRecipeHeaderList header = grecipeFormulaDetailLogic.getHeader(
			AecNumberUtils.convertBigDecimal(detailForm.getRecipeId()),
			AecNumberUtils.convertBigDecimal(detailForm.getStepNo()));
		setCommonHeaderInfo(detailForm, header);

		// 処方フォーミュラ検索
		GrecipeRecipeFormulaList bean = grecipeFormulaDetailLogic.getEntity(
			new BigDecimal(detailForm.getRecipeId()), new BigDecimal(detailForm
					.getStepNo()), new BigDecimal(detailForm.getLineNo()));
		setFormulaData(detailForm, bean);
		// Beanの内容をFormに保持
		detailForm.setDetailBean(bean);

		return mapping.findForward("success");
	}

	/**
	 * 更新処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward update(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("update");
		}
		GrecipeFormulaDetailForm detailForm = (GrecipeFormulaDetailForm) form;

		GrecipeRecipeFormulaList bean = detailForm.getDetailBean();
		bean.setUpdatorCd(getLoginInfo(request).getTantoCd()); // 更新者ID
		// 画面内容をBeanへ設定
		setDetailFormData(detailForm, bean);

		// 品目マスタ存在チェック
		ActionMessages errors = grecipeFormulaDetailLogic.checkForUpdate(bean);
		if (!errors.isEmpty()) {
			// エラーがあった場合
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		// 処方フォーミュラ更新処理
		grecipeFormulaDetailLogic.update(bean, detailForm);

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("back");
	}

	/**
	 * 戻る処理.
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward back(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("back.");
		}

		return mapping.findForward("back");
	}

	/**
	 * ヘッダー情報を画面のFormに設定する。
	 * @param form 画面のForm
	 * @param header ヘッダー情報
	 * @return GrecipeFormulaDetailForm
	 */
	private GrecipeFormulaDetailForm setCommonHeaderInfo(
			final GrecipeFormulaDetailForm form,
			final GrecipeRecipeHeaderList header) {
		form.setRecipeCd(header.getRecipeCd()); // レシピコード
		form.setRecipeVersion(getBigDecimalString(header.getRecipeVersion())); // レシピバージョン
		form.setRecipeName(header.getRecipeName()); // 処方名称
		form.setRecipeUse(getBigDecimalString(header.getRecipeUse())); // 用途
		form.setRecipeUseName(SelectRecipeUse.getName(form.getRecipeUse())); // 用途名
		form.setProduct(header.getProduct()); // 品目コード
		form.setItemName(header.getItemName()); // 品目名称
		form.setOperationCd(header.getOperationCd()); // 工程コード
		form.setOperationName(header.getOperationName()); // 工程名称
		form.setRecipeStatus(getBigDecimalString(header.getRecipeStatus())); // RECPI_STATUS
		form.setApprovalStatus(getBigDecimalString(header.getApprovalStatus())); // 承認ステータス
		form.setApprovalStatusName(header.getApprovalStatusName()); // 承認ステータス名称
		form.setSumQty(checker.format(GrecipeConst.UNIT_DIVISION_HAIGO, header
				.getSumQty())); // 配合量計
		form.setStyleOfPacking(header.getStyleOfPacking()); // 荷姿
		// 表示時処方ヘッダ情報を取得、設定
		form.setHeaderBean(header);

		return form;
	}

	/**
	 * 処方フォーミュラ検索結果を画面のFormに設定する。
	 * @param form 画面のForm
	 * @param formulaBean 処方フォーミュラ検索結果
	 * @return GrecipeFormulaDetailForm
	 */
	private GrecipeFormulaDetailForm setFormulaData(
			final GrecipeFormulaDetailForm form,
			final GrecipeRecipeFormulaList formulaBean) {
		form.setSeq(getBigDecimalString(formulaBean.getSeq())); // サブステップ
		form.setItemCd(formulaBean.getItemCd()); // 品目コード
		form.setFormulaItemName(formulaBean.getItemName()); // 品目名称
		form.setQty(checker.format(GrecipeConst.UNIT_DIVISION_HAIGO,
			formulaBean.getQty())); // 配合量
		if (form.getRecipeUse().equals(SelectRecipeUse.RECIPE_USE_PACKING)) {
			// 用途が包装
			form.setUnitDivision(formulaBean.getUnitDivision()); // 数値区分
			form.setUnitName(formulaBean.getUnitName()); // 単位
		}
		form.setUnitDivision(formulaBean.getUnitDivision()); // 数値区分
		form.setUnitName(formulaBean.getUnitName()); // 単位
		form.setRemark(formulaBean.getRemark()); // 備考
		form.setNotes(formulaBean.getNotes()); // 注釈
		form.setTonyu(getBigDecimalString(formulaBean.getTonyu())); // 投入方法
		form.setTonyuSokudo(checker.format("RECIPE1", formulaBean
				.getTonyusokudo())); // 投入速度
		form.setDataRead(getBigDecimalString(formulaBean.getDataread())); // データ読取

		return form;
	}

	/**
	 * 画面の内容をBeanに設定
	 * @param frm 画面FORM
	 * @param bean 処方フォーミュラBean
	 */
	private void setDetailFormData(final GrecipeFormulaDetailForm frm,
			final GrecipeRecipeFormulaList bean) {
		BigDecimal recipeUse = AecNumberUtils.convertBigDecimal(frm
				.getRecipeUse()); // 用途

		bean.setItemCd(frm.getItemCd()); // 品目コード
		bean.setQty(AecNumberUtils.convertBigDecimal(frm.getQty())); // 配合量
		bean.setRemark(frm.getRemark()); // 備考
		bean.setNotes(frm.getNotes()); // 注釈
		if (GrecipeConst.RECIPE_USE_PRODUCTION.equals(recipeUse)) {
			// 製造の場合
			bean.setTonyu(AecNumberUtils.convertBigDecimal(frm.getTonyu())); // 投入方法
			bean.setTonyusokudo(AecNumberUtils.convertBigDecimal(frm
					.getTonyuSokudo())); // 投入速度
			bean.setDataread(AecNumberUtils
					.convertBigDecimal(frm.getDataRead())); // データ読込
		}
	}

	/**
	 * BigDecimalの文字列表現を取得する BigDecimal=null時はnullを返す
	 * @param dec BigDecimal
	 * @return BigDecimalの文字列表現
	 */
	protected String getBigDecimalString(final BigDecimal dec) {
		String res = null;
		if (dec != null) {
			res = dec.toString();
		}
		return res;
	}

	/* -------------------- setter -------------------- */

	/**
	 * 処方フォーミュラ配合詳細検索 ロジッククラスを設定します。
	 * @param grecipeFormulaDetailLogic 処方フォーミュラ配合詳細検索 ロジッククラス
	 */
	public void setGrecipeFormulaDetailLogic(
			final GrecipeFormulaDetailLogic grecipeFormulaDetailLogic) {
		this.grecipeFormulaDetailLogic = grecipeFormulaDetailLogic;
	}

	/**
	 * 数値桁数チェック ロジッククラスを設定します。
	 * @param checker checker
	 */
	public void setChecker(final CheckDigitUtilsLogic checker) {
		this.checker = checker;
	}

}
