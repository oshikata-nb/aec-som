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

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeHeaderList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeInspectionList;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 原処方-検査詳細 Actionクラス.
 * @author tosco
 */
public final class GrecipeInspectionDetailAction extends AbstractAction {

	/** 処方検査詳細検索 ロジッククラス */
	private GrecipeInspectionDetailLogic grecipeInspectionDetailLogic;

	/**
	 * コンストラクタ.
	 */
	public GrecipeInspectionDetailAction() {
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

		GrecipeInspectionDetailForm detailForm = (GrecipeInspectionDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, detailForm, Constants.MENU_ID_GRECIPE,
			Constants.TAB_ID_GRECIPE_INSPECTION);

		if (!detailForm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 処方ヘッダ検索処理
		GrecipeRecipeHeaderList header = grecipeInspectionDetailLogic
				.getHeader(AecNumberUtils.convertBigDecimal(detailForm
						.getRecipeId()), AecNumberUtils
						.convertBigDecimal(detailForm.getStepNo()));
		setCommonHeaderInfo(detailForm, header);

		// 処方検査検索
		GrecipeRecipeInspectionList bean = grecipeInspectionDetailLogic
				.getEntity(new BigDecimal(detailForm.getRecipeId()),
					new BigDecimal(detailForm.getStepNo()), new BigDecimal(
							detailForm.getLineNo()));
		setInspectionDeta(detailForm, bean);

		// Beanの内容をFormに保持
		detailForm.setDetailBean(bean);
		if (StringUtils.isEmpty(bean.getValueType())) {
			detailForm.setValueType("1"); // 数値
		}

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

		GrecipeInspectionDetailForm frm = (GrecipeInspectionDetailForm) form;

		GrecipeRecipeInspectionList bean = frm.getDetailBean();
		bean.setUpdatorCd(getLoginInfo(request).getTantoCd()); // 更新者ID
		// 画面内容をBeanへ設定
		setDetailFormData(frm, bean);

		// 各種名称マスタ存在チェック
		ActionMessages errors = grecipeInspectionDetailLogic
				.checkForUpdate(bean);
		if (!errors.isEmpty()) {
			// エラーがあった場合
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		// 処方検査更新処理
		grecipeInspectionDetailLogic.update(bean);

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
	 * @return GrecipeInspectionDetailForm
	 */
	private GrecipeInspectionDetailForm setCommonHeaderInfo(
			final GrecipeInspectionDetailForm form,
			final GrecipeRecipeHeaderList header) {
		form.setRecipeCd(header.getRecipeCd()); // レシピコード
		form.setRecipeVersion(getBigDecimalString(header.getRecipeVersion())); // レシピバージョン
		form.setRecipeName(header.getRecipeName()); // 処方名称
		form.setProduct(header.getProduct()); // 品目コード
		form.setItemName(header.getItemName()); // 品目名称
		form.setOperationCd(header.getOperationCd()); // 工程コード
		form.setOperationName(header.getOperationName()); // 工程名称
		form.setApprovalStatus(header.getApprovalStatus().toString()); // 承認ステータス
		form.setApprovalStatusName(header.getApprovalStatusName()); // 承認ステータス名称
		return form;
	}

	/**
	 * 処方検査検索結果を画面のFormに設定する。
	 * @param form 画面のForm
	 * @param inspectionBean 処方検査検索結果
	 * @return GrecipeInspectionDetailForm
	 */
	private GrecipeInspectionDetailForm setInspectionDeta(
			final GrecipeInspectionDetailForm form,
			final GrecipeRecipeInspectionList inspectionBean) {

		form.setInspectionCd(inspectionBean.getInspectionCd()); // 検査コード
		form.setInspectionName(inspectionBean.getInspectionName()); // 検査名称
		form.setStrInspectionCondition(inspectionBean.getCondition()); // 条件
		form.setStrInspectionDivision(inspectionBean.getDivision()); // 区分
		form.setValue1(inspectionBean.getValue1()); // 値1
		form.setValue2(inspectionBean.getValue2()); // 値2
		form.setValueType(inspectionBean.getValueType()); // 入力種類|1:数値,2:文字列
		form.setRemark(inspectionBean.getRemark()); // 備考
		form.setNotes(inspectionBean.getNotes()); // 注釈

		return form;
	}

	/**
	 * 画面の内容をBeanに設定
	 * @param frm 画面FORM
	 * @param bean 処方検査Bean
	 */
	private void setDetailFormData(final GrecipeInspectionDetailForm frm,
			final GrecipeRecipeInspectionList bean) {

		bean.setInspectionCd(frm.getInspectionCd()); // 検査コード
		bean.setCondition(frm.getStrInspectionCondition()); // 条件
		bean.setDivision(frm.getStrInspectionDivision()); // 区分
		bean.setValue1(frm.getValue1()); // 値1
		bean.setValue2(frm.getValue2()); // 値2
		bean.setValueType(frm.getValueType()); // 入力種類|1:数値,2:文字列
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
	 * 処方検査詳細検索 ロジッククラスを設定します。
	 * @param grecipeInspectionDetailLogic 処方検査詳細検索 ロジッククラス
	 */
	public void setGrecipeInspectionDetailLogic(
			final GrecipeInspectionDetailLogic grecipeInspectionDetailLogic) {
		this.grecipeInspectionDetailLogic = grecipeInspectionDetailLogic;
	}

}
