/*
 * Created on 2009/03/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.materialrinput;

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
import com.asahikaseieng.dao.nonentity.materialrinput.MaterialRinputDetail;
import com.asahikaseieng.dao.nonentity.materialrinput.MaterialRinputDetailList;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 外注原材料投入実績入力 Actionクラス.
 * @author tosco
 */
public final class MaterialRinputDetailAction extends AbstractAction {

	/** 外注原材料投入実績入力ロジッククラス */
	private MaterialRinputDetailLogic materialRinputDetailLogic;

	/**
	 * コンストラクタ.
	 */
	public MaterialRinputDetailAction() {
		super();
	}

	/**
	 * 検索処理(一覧画面の発注番号リンク押下時)
	 * 
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
			getLog().debug("init.");
		}

		MaterialRinputDetailForm frm = (MaterialRinputDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_MATERIAL_RINPUT,
			Constants.TAB_ID_MATERIAL_RINPUT_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		try {
			CheckDigitUtilsLogic check = CheckDigitUtil
					.getCheckDigitUtils(request);

			// ヘッダ部検索(購買外注オーダ)
			MaterialRinputDetail bean = materialRinputDetailLogic.getHeader(frm
					.getBuySubcontractOrderNo(), check);
			// ヘッダ部データ設定
			setHeaderInfo(frm, bean);
			// javascriptでの桁数丸め用に必要となる値取得
			getCheckDigit(frm, check, bean);

			// 明細部検索(購買外注原材料投入実績)
			List<MaterialRinputDetailList> list = materialRinputDetailLogic
					.getDetailList(frm, check, bean.getVenderCd());

			// 基本処方データ設定
			if (list.size() > 0) {
				MaterialRinputDetailList listBean = list.get(0);
				frm.setRecipeId(convertString(listBean.getRecipeId()));
				frm.setRecipeCd(listBean.getRecipeCd());
				frm
						.setRecipeVersion(convertString(listBean
								.getRecipeVersion()));
				frm
						.setRecipeCdVersion(listBean.getRecipeCd()
								+ MaterialRinputDetailLogicImpl.RECIPE_CD_VERSION_SEPARATOR
								+ listBean.getRecipeVersion());
				frm.setRecipeName(listBean.getRecipeName());

				// アスプローバからのデータ(STEP_NO=0,LINE_NO=0,SEQ=0)以外の場合のみ、リストデータ設定
				if (!(BigDecimal.ZERO.equals(listBean.getStepNo())
						&& BigDecimal.ZERO.equals(listBean.getLineNo()) && BigDecimal.ZERO
						.equals(listBean.getSeq()))) {
					frm.setDetailList(list);
				}
			}
		} catch (NoDataException e) {
			/* エラーメッセージ */
			saveError(request, "errors.nodata.deleted");
			return mapping.findForward("back");
		}

		return mapping.findForward("success");

	}

	/**
	 * 計算処理(計算ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward calculate(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		MaterialRinputDetailForm frm = (MaterialRinputDetailForm) form;

		// 基本処方コードの存在チェック
		ActionMessages errMsgs = materialRinputDetailLogic.checkRecipe(frm);
		if (!errMsgs.isEmpty()) {
			saveErrors(request, errMsgs);
			return mapping.findForward("error");
		}

		// 明細部検索(処方フォーミュラ)
		frm.setDetailList(new ArrayList<MaterialRinputDetailList>());
		CheckDigitUtilsLogic check = CheckDigitUtil.getCheckDigitUtils(request);
		List<MaterialRinputDetailList> list = materialRinputDetailLogic
				.calculate(frm, check);
		frm.setDetailList(list);

		frm.setCalcFlg(true);

		return mapping.findForward("success");

	}

	/**
	 * 登録処理
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward insert(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("insert.");
		}

		MaterialRinputDetailForm frm = (MaterialRinputDetailForm) form;
		String tantoCd = getLoginInfo(request).getTantoCd();

		// 購買外注オーダ更新処理、購買外注原材料投入実績削除／登録処理
		materialRinputDetailLogic.insert(frm, tantoCd);

		frm.setCalcFlg(false);

		/* メッセージ */
		saveMessage(request, "message.complete.update");

		return mapping.findForward("reSearch");

	}

	/**
	 * 在庫引落リセット
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward reset(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		MaterialRinputDetailForm frm = (MaterialRinputDetailForm) form;
		String tantoCd = getLoginInfo(request).getTantoCd();

		try {
			// 在庫引落をリセットする
			materialRinputDetailLogic.reset(frm, tantoCd);
		} catch (LogicExceptionEx e) {
			String errMsg = "errors.materialrinput.move.stock.update";
			// 在庫更新に失敗
			if (errMsg.equals(e.getMessage())) {
				saveError(request, errMsg);
			} else {
				throw e;
			}
			return mapping.getInputForward();
		}

		/* メッセージ */
		saveMessage(request, "message.complete.update");
		return mapping.findForward("reSearch");
	}

	// /**
	// * 更新処理(受入入力画面の登録ボタン押下時)(入荷対象)
	// *
	// * @param mapping ActionMapping
	// * @param form ActionForm
	// * @param request HttpServletRequest
	// * @param response HttpServletResponse
	// * @return ActionForward
	// * @throws Exception Exception
	// */
	// public ActionForward update(final ActionMapping mapping,
	// final ActionForm form, final HttpServletRequest request,
	// final HttpServletResponse response) throws Exception {
	//
	// if (getLog().isDebugEnabled()) {
	// getLog().debug("update.");
	// }
	//
	// AcceptDetailForm frm = (AcceptDetailForm) form;
	// String tantoCd = getLoginInfo(request).getTantoCd();
	//
	// try {
	// //更新前チェックを行う
	// ActionMessages errors =
	// acceptDetailLogic.checkForRegist(frm.getOrganizationCd());
	// if (!errors.isEmpty()) {
	// //エラーがあった場合
	// super.saveErrors(request, errors);
	// return mapping.findForward("error");
	// }
	//
	// // 購買外注データ検索
	// PurchaseSubcontract bean =
	// acceptDetailLogic.getEntity(frm.getPurchaseNo());
	//
	// // 更新処理を実行
	// acceptDetailLogic.update(frm, tantoCd, bean);
	// } catch (NoDataException e) {
	// saveError(request, "errors.nodata.deleted");
	// return mapping.getInputForward();
	// }
	//
	// /* メッセージ */
	// saveMessage(request, "message.complete.update");
	//
	// return mapping.findForward("back");
	//
	// }

	/**
	 * 戻る処理(詳細画面または新規登録画面の戻るボタン押下時)
	 * 
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
	 * 戻る処理(詳細画面または新規登録画面の戻るボタン押下時)
	 * 
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward reSearch(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("reSearch.");
		}
		return this.init(mapping, form, request, response);

	}

	/**
	 * 共通情報をFORMへセット
	 * @param frm 受入入力FORM
	 * @param beanList 受入入力表示データ
	 */
	private void setHeaderInfo(final MaterialRinputDetailForm frm,
			final MaterialRinputDetail bean) {
		frm.setBuySubcontractOrderNo(bean.getBuySubcontractOrderNo()); // 発注番号
		frm.setOrderSheetNo(bean.getOrderSheetNo()); // 発注書NO
		frm.setStrOrderDate(bean.getStrOrderDate()); // 発注日
		frm.setStrAcceptDate(bean.getStrAcceptDate()); // 発注日
		frm.setStrSuggestedDeliverlimitDate(bean
				.getStrSuggestedDeliverlimitDate()); // 納品希望日
		frm.setItemCd(bean.getItemCd()); // 品目コード
		frm.setItemQueueName(bean.getItemName()); // 品目名称
		frm.setOtherCompanyCd1(bean.getOtherCompanyCd1()); // 他社コード１
		frm.setVenderCd(bean.getVenderCd()); // 仕入先コード
		frm.setVenderName(bean.getVenderName1()); // 仕入先名称
		frm.setVenderShortedName(bean.getVenderShortedName()); // 仕入先略称
		frm.setLocationCd(bean.getLocationCd()); // 納入ロケーション
		frm.setLocationName(bean.getLocationName()); // 納入先名称
		frm.setChargeOrganizationCd(bean.getChargeOrganizationCd()); // 担当部署コード
		frm.setChargeOrganizationName(bean.getChargeOrganizationName()); // 担当部署名称
		frm.setOrganizationCd(bean.getOrganizationCd()); // 部署コード
		frm.setOrganizationName(bean.getOrganizationName()); // 部署名称
		frm.setTantoCd(bean.getTantoCd()); // 発注者コード
		frm.setTantoNm(bean.getTantoNm()); // 発注者名称
		frm.setStrOrderQuantity(bean.getStrOrderQuantity()); // 発注数量
		frm.setUnit(bean.getUnit()); // 単位
		frm.setStyleOfPacking(bean.getStyleOfPacking()); // 荷姿
		frm.setStrOrderConvertQuantity(bean.getStrOrderConvertQuantity()); // 重量
		frm.setStrGoodHousingSum(bean.getStrGoodHousingSum()); // 生産出来高
		frm.setUnitDiv(bean.getUnitDiv()); // 運用管理単位(区分)
	}

	/**
	 * BigDecimalからStringへ型変換を行う
	 * @param decimal BigDecimal値
	 * @return String String型に変換した値
	 */
	private String convertString(final BigDecimal decimal) {
		String ret = null;
		if (decimal != null) {
			ret = decimal.toString();
		}
		return ret;
	}

	/**
	 * 数値桁数チェック部品からjavascriptでの桁数丸め用に必要となる値を取得
	 * @param frm 入荷入力画面Form
	 * @param check 数値項目用表示ロジッククラス
	 * @param bean 入荷入力Bean
	 */
	private void getCheckDigit(final MaterialRinputDetailForm frm,
			final CheckDigitUtilsLogic check, final MaterialRinputDetail bean) {
		// 生産出来高
		NumberChkDisitDetail detail = check.getCheckDigit(bean.getUnitDiv(),
			MaterialRinputDetailLogicImpl.VENDER_DIV_SI, bean.getVenderCd());
		if (detail.getSmallnumLength() != null) {
			frm.setDecimalPoint(detail.getSmallnumLength().toString()); // 小数点桁数
		}
		if (detail.getRoundDivision() != null) {
			frm.setRound(detail.getRoundDivision().toString()); // 端数区分
		}

		// 配合
		NumberChkDisitDetail detailHaigo = check.getCheckDigit(
			MaterialRinputDetailLogicImpl.UNIT_DIVISION_HAIGO,
			MaterialRinputDetailLogicImpl.VENDER_DIV_SI, bean.getVenderCd());
		if (detailHaigo.getSmallnumLength() != null) {
			frm
					.setDecimalPointHaigo(detailHaigo.getSmallnumLength()
							.toString()); // 小数点桁数
		}
		if (detailHaigo.getRoundDivision() != null) {
			frm.setRoundHaigo(detailHaigo.getRoundDivision().toString()); // 端数区分
		}

		// 配合(調整数量)
		NumberChkDisitDetail detailHaigofAdj = check.getCheckDigit(
			MaterialRinputDetailLogicImpl.UNIT_DIVISION_HAIGO_ADJ,
			MaterialRinputDetailLogicImpl.VENDER_DIV_SI, bean.getVenderCd());
		if (detailHaigofAdj.getSmallnumLength() != null) {
			frm.setDecimalPointHaigoAdj(detailHaigofAdj.getSmallnumLength()
					.toString()); // 小数点桁数
		}
		if (detailHaigofAdj.getRoundDivision() != null) {
			frm.setRoundHaigoAdj(detailHaigofAdj.getRoundDivision().toString()); // 端数区分
		}

	}

	/* -------------------- setter -------------------- */

	/**
	 * 外注原材料投入実績入力ロジッククラスを設定します。
	 * @param materialRinputDetailLogic 外注原材料投入実績入力ロジッククラス
	 */
	public void setMaterialRinputDetailLogic(
			final MaterialRinputDetailLogic materialRinputDetailLogic) {
		this.materialRinputDetailLogic = materialRinputDetailLogic;
	}
}
