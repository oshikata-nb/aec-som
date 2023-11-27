/*
 * Created on 2009/02/03
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
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.comboboxes.SelectRecipeUse;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeHeaderList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeProcedureList;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 原処方-工程詳細 Actionクラス.
 * @author tosco
 */
public final class GrecipeProcedureDetailAction extends AbstractAction {

	/** 区分 RECIPE1 */
	public static final String UNIT_DIV_RECIPE1 = "RECIPE1";

	/** 区分 RECIPE2 */
	public static final String UNIT_DIV_RECIPE2 = "RECIPE2";

	/** 区分 RECIPE5 */
	public static final String UNIT_DIV_RECIPE5 = "RECIPE5";

	/** 区分 その他 */
	public static final String UNIT_DIV_SONOTA = "SONOTA";

	/** 原処方-工程詳細 ロジッククラス */
	private GrecipeProcedureDetailLogic grecipeProcedureDetailLogic;

	/** 単位区分 "HAIGO" */
	private static final String UNIT_DIVISION_HAIGO = "HAIGO";

	/**
	 * コンストラクタ.
	 */
	public GrecipeProcedureDetailAction() {
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

		GrecipeProcedureDetailForm detailForm = (GrecipeProcedureDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, detailForm, Constants.MENU_ID_GRECIPE,
			Constants.TAB_ID_GRECIPE_PROCEDURE);

		if (!detailForm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 処方ヘッダ検索処理
		GrecipeRecipeHeaderList header = grecipeProcedureDetailLogic.getHeader(
			AecNumberUtils.convertBigDecimal(detailForm.getRecipeId()),
			AecNumberUtils.convertBigDecimal(detailForm.getStepNo()));
		setCommonHeaderInfo(request, detailForm, header);

		// 処方プロシージャ検索
		GrecipeRecipeProcedureList bean = grecipeProcedureDetailLogic
				.getEntity(new BigDecimal(detailForm.getRecipeId()),
					new BigDecimal(detailForm.getStepNo()));
		// 数値カンマ編集
		formatNum(request, bean, header.getRecipeUse());

		// 数値フォーマット情報設定
		setFormatData(request, detailForm);

		// BeanをFormに一括コピー
		IgnoreCaseBeanUtils.copyProperties(detailForm, bean);
		// Beanの内容をFormに保持
		detailForm.setDetailBean(bean);

		return mapping.findForward("success");
	}

	/**
	 * 数値フォーマット情報設定
	 * @param request HttpServletRequest
	 * @param detailForm 工程タグ
	 */
	private void setFormatData(final HttpServletRequest request,
			final GrecipeProcedureDetailForm detailForm) {
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);
		NumberChkDisitDetail checkDetail = new NumberChkDisitDetail();

		// RECIPE1
		checkDetail = checker.getCheckDigit(UNIT_DIV_RECIPE1, null, null);
		detailForm.setDecimalPointRecipe1(getBigDecimalString(checkDetail
				.getSmallnumLength()));
		detailForm.setRoundDivisionRecipe1(getBigDecimalString(checkDetail
				.getRoundDivision()));

		// RECIPE2
		checkDetail = checker.getCheckDigit(UNIT_DIV_RECIPE2, null, null);
		detailForm.setDecimalPointRecipe2(getBigDecimalString(checkDetail
				.getSmallnumLength()));
		detailForm.setRoundDivisionRecipe2(getBigDecimalString(checkDetail
				.getRoundDivision()));

		// RECIPE5
		checkDetail = checker.getCheckDigit(UNIT_DIV_RECIPE5, null, null);
		detailForm.setDecimalPointRecipe5(getBigDecimalString(checkDetail
				.getSmallnumLength()));
		detailForm.setRoundDivisionRecipe5(getBigDecimalString(checkDetail
				.getRoundDivision()));

		// SONOTA
		checkDetail = checker.getCheckDigit(UNIT_DIV_SONOTA, null, null);
		detailForm.setDecimalPointSonota(getBigDecimalString(checkDetail
				.getSmallnumLength()));
		detailForm.setRoundDivisionSonota(getBigDecimalString(checkDetail
				.getRoundDivision()));
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

		GrecipeProcedureDetailForm frm = (GrecipeProcedureDetailForm) form;

		GrecipeRecipeProcedureList bean = frm.getDetailBean();
		bean.setUpdatorCd(getLoginInfo(request).getTantoCd()); // 更新者ID
		// 画面内容をBeanへ設定
		setDetailFormData(frm, bean);

		// 工程マスタ存在チェック
		ActionMessages errors = grecipeProcedureDetailLogic
				.checkForUpdate(bean);
		if (!errors.isEmpty()) {
			// エラーがあった場合
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		// 処方プロシージャ更新処理
		grecipeProcedureDetailLogic.update(bean);

		/* メッセージ */
		saveMessage(request, "message.complete.update");

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
	 * @param request リクエスト
	 * @param form 画面のForm
	 * @param header ヘッダー情報
	 * @return GrecipeProcedureDetailForm
	 */
	private GrecipeProcedureDetailForm setCommonHeaderInfo(
			final HttpServletRequest request,
			final GrecipeProcedureDetailForm form,
			final GrecipeRecipeHeaderList header) {
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);
		form.setRecipeCd(header.getRecipeCd());
		form.setRecipeVersion(getBigDecimalString(header.getRecipeVersion()));
		form.setRecipeUse(getBigDecimalString(header.getRecipeUse()));
		form.setRecipeUseName(SelectRecipeUse.getName(form.getRecipeUse()));
		form.setProduct(header.getProduct());
		form.setItemName(header.getItemName());
		form.setRecipeStatus(header.getRecipeStatus().toString());
		form.setApprovalStatus(header.getApprovalStatus().toString());
		form.setApprovalStatusName(header.getApprovalStatusName());
		form.setSumQty(checker.format(UNIT_DIVISION_HAIGO, header.getSumQty())); // 配合量計

		return form;
	}

	/**
	 * 数値のカンマ編集を行う
	 * @param request リクエスト
	 * @param bean 処方プロシージャBean
	 * @param recipeUse 用途
	 */
	private void formatNum(final HttpServletRequest request,
			final GrecipeRecipeProcedureList bean, final BigDecimal recipeUse) {
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		if (GrecipeConst.RECIPE_USE_PRODUCTION.equals(recipeUse)) {
			// 製造の場合
			bean.setStrConditionTemp(checker.format(UNIT_DIV_RECIPE2, bean
					.getConditionTemp())); // 温度
			bean.setStrConditionTime(checker.format(UNIT_DIV_RECIPE1, bean
					.getConditionTime())); // 時間
			bean.setStrStirSpeed1(checker.format(UNIT_DIV_RECIPE1, bean
					.getStirSpeed1())); // 攪拌速度1
			bean.setStrStirSpeed2(checker.format(UNIT_DIV_RECIPE1, bean
					.getStirSpeed2())); // 攪拌速度2
			bean.setStrWaterWeight(checker.format(UNIT_DIV_SONOTA, bean
					.getWaterWeight())); // 洗浄水絶対重量
			if (bean.getMainStream() != null) {
				bean.setStrMainStream(bean.getMainStream().toString()); // 本流/予備溶解
			}

		} else if (GrecipeConst.RECIPE_USE_PACKING.equals(recipeUse)) {
			// 包装の場合
			bean.setStrNet(checker.format(UNIT_DIV_RECIPE5, bean.getNet())); // 正味質量
			bean.setStrWeightMin(checker.format(UNIT_DIV_RECIPE5, bean
					.getWeightMin())); // 量目管理幅最小
			bean.setStrWeightMax(checker.format(UNIT_DIV_RECIPE5, bean
					.getWeightMax())); // 量目管理幅最大
			bean.setStrFilter(checker
					.format(UNIT_DIV_RECIPE5, bean.getFilter())); // 濾過用フィルター
			bean.setStrMesh(checker.format(UNIT_DIV_RECIPE5, bean.getMesh())); // 濾過用メッシュ
			bean.setStrAutoCheckerMin(checker.format(UNIT_DIV_RECIPE5, bean
					.getAutoCheckerMin())); // オートチェッカー最小
			bean.setStrAutoCheckerMax(checker.format(UNIT_DIV_RECIPE5, bean
					.getAutoCheckerMax())); // オートチェッカー最大
			bean.setStrGrossCheckerMin(checker.format(UNIT_DIV_RECIPE5, bean
					.getGrossCheckerMin())); // グロスチェッカー最小
			bean.setStrGrossCheckerMax(checker.format(UNIT_DIV_RECIPE5, bean
					.getGrossCheckerMax())); // グロスチェッカー最大
			bean.setStrAutoCheckerAve(checker.format(UNIT_DIV_RECIPE5, bean
					.getAutoCheckerAve())); // オートチェッカー中心
			bean.setStrGrossCheckerAve(checker.format(UNIT_DIV_RECIPE5, bean
					.getGrossCheckerAve())); // グロスチェッカー中心
			bean.setStrOpeningTorqueMin(checker.format(UNIT_DIV_RECIPE5, bean
					.getOpeningTorqueMin())); // 開きトルク最小
			bean.setStrOpeningTorqueMax(checker.format(UNIT_DIV_RECIPE5, bean
					.getOpeningTorqueMax())); // 開きトルク最大
			bean.setStrClosingTorqueMin(checker.format(UNIT_DIV_RECIPE5, bean
					.getClosingTorqueMin())); // 閉めトルク最小
			bean.setStrClosingTorqueMax(checker.format(UNIT_DIV_RECIPE5, bean
					.getClosingTorqueMax())); // 閉めトルク最大
			bean.setStrTorquePressure(checker.format(UNIT_DIV_RECIPE5, bean
					.getTorquePressure())); // トルク圧
			bean.setStrAirPressure(checker.format(UNIT_DIV_RECIPE5, bean
					.getAirPressure())); // エアー圧
			bean.setStrVcloseTime(checker.format(UNIT_DIV_RECIPE5, bean
					.getVcloseTime())); // 巻締め時間
			bean.setStrHotAirPresetTemp(checker.format(UNIT_DIV_RECIPE5, bean
					.getHotAirPresetTemp())); // ホットエアー設定温度
			bean.setStrHotAirPressure(checker.format(UNIT_DIV_RECIPE5, bean
					.getHotAirPressure())); // ホットエアー吹き出し圧力
			bean.setStrFirstHeatSeal(checker.format(UNIT_DIV_RECIPE5, bean
					.getFirstHeatSeal())); // 第一ヒートシール設定温度
			bean.setStrSecondHeatSeal(checker.format(UNIT_DIV_RECIPE5, bean
					.getSecondHeatSeal())); // 第二ヒートシール設定温度
		}
	}

	/**
	 * 画面の内容をBeanに設定
	 * @param frm 画面FORM
	 * @param bean 処方プロシージャBean
	 */
	private void setDetailFormData(final GrecipeProcedureDetailForm frm,
			final GrecipeRecipeProcedureList bean) {
		BigDecimal recipeUse = AecNumberUtils.convertBigDecimal(frm
				.getRecipeUse()); // 用途

		bean.setOperationCd(frm.getOperationCd()); // 工程コード

		if (GrecipeConst.RECIPE_USE_PRODUCTION.equals(recipeUse)) {
			// 製造の場合
			bean.setConditionTemp(AecNumberUtils.convertBigDecimal(frm
					.getStrConditionTemp())); // 温度
			bean.setConditionTime(AecNumberUtils.convertBigDecimal(frm
					.getStrConditionTime())); // 時間
			bean.setStirSpeed1(AecNumberUtils.convertBigDecimal(frm
					.getStrStirSpeed1())); // 攪拌速度1
			bean.setStirSpeed2(AecNumberUtils.convertBigDecimal(frm
					.getStrStirSpeed2())); // 攪拌速度2
			bean.setWaterWeight(AecNumberUtils.convertBigDecimal(frm
					.getStrWaterWeight())); // 洗浄水絶対重量
			bean.setMainStream(AecNumberUtils.convertBigDecimal(frm
					.getStrMainStream())); // 本流/予備溶解

		} else if (GrecipeConst.RECIPE_USE_PACKING.equals(recipeUse)) {
			// 包装の場合
			bean.setNet(AecNumberUtils.convertBigDecimal(frm.getStrNet())); // 正味質量
			bean.setWeightMin(AecNumberUtils.convertBigDecimal(frm
					.getStrWeightMin())); // 量目管理幅最小
			bean.setWeightMax(AecNumberUtils.convertBigDecimal(frm
					.getStrWeightMax())); // 量目管理幅最大
			bean
					.setFilter(AecNumberUtils.convertBigDecimal(frm
							.getStrFilter())); // 濾過用フィルター
			bean.setMesh(AecNumberUtils.convertBigDecimal(frm.getStrMesh())); // 濾過用メッシュ
			bean.setAutoCheckerMin(AecNumberUtils.convertBigDecimal(frm
					.getStrAutoCheckerMin())); // オートチェッカー最小
			bean.setAutoCheckerMax(AecNumberUtils.convertBigDecimal(frm
					.getStrAutoCheckerMax())); // オートチェッカー最大
			bean.setGrossCheckerMin(AecNumberUtils.convertBigDecimal(frm
					.getStrGrossCheckerMin())); // グロスチェッカー最小
			bean.setGrossCheckerMax(AecNumberUtils.convertBigDecimal(frm
					.getStrGrossCheckerMax())); // グロスチェッカー最大
			bean.setAutoCheckerAve(AecNumberUtils.convertBigDecimal(frm
					.getStrAutoCheckerAve())); // オートチェッカー中心
			bean.setGrossCheckerAve(AecNumberUtils.convertBigDecimal(frm
					.getStrGrossCheckerAve())); // グロスチェッカー中心
			bean.setOpeningTorqueMin(AecNumberUtils.convertBigDecimal(frm
					.getStrOpeningTorqueMin())); // 開きトルク最小
			bean.setOpeningTorqueMax(AecNumberUtils.convertBigDecimal(frm
					.getStrOpeningTorqueMax())); // 開きトルク最大
			bean.setClosingTorqueMin(AecNumberUtils.convertBigDecimal(frm
					.getStrClosingTorqueMin())); // 閉めトルク最小
			bean.setClosingTorqueMax(AecNumberUtils.convertBigDecimal(frm
					.getStrClosingTorqueMax())); // 閉めトルク最大
			bean.setTorquePressure(AecNumberUtils.convertBigDecimal(frm
					.getStrTorquePressure())); // トルク圧
			bean.setAirPressure(AecNumberUtils.convertBigDecimal(frm
					.getStrAirPressure())); // エアー圧
			bean.setVcloseTime(AecNumberUtils.convertBigDecimal(frm
					.getStrVcloseTime())); // 巻締め時間
			bean.setHotAirPresetTemp(AecNumberUtils.convertBigDecimal(frm
					.getStrHotAirPresetTemp())); // ホットエアー設定温度
			bean.setHotAirPressure(AecNumberUtils.convertBigDecimal(frm
					.getStrHotAirPressure())); // ホットエアー吹き出し圧力
			bean.setFirstHeatSeal(AecNumberUtils.convertBigDecimal(frm
					.getStrFirstHeatSeal())); // 第一ヒートシール設定温度
			bean.setSecondHeatSeal(AecNumberUtils.convertBigDecimal(frm
					.getStrSecondHeatSeal())); // 第二ヒートシール設定温度
		}
		bean.setCondition(frm.getCondition()); // 条件
		bean.setRemark(frm.getRemark()); // 備考
		bean.setNotes(frm.getNotes()); // 注釈
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
	 * 原処方-工程詳細 ロジッククラスを設定します。
	 * @param grecipeProcedureDetailLogic 原処方-工程詳細 ロジッククラス
	 */
	public void setGrecipeProcedureDetailLogic(
			final GrecipeProcedureDetailLogic grecipeProcedureDetailLogic) {
		this.grecipeProcedureDetailLogic = grecipeProcedureDetailLogic;
	}

}
