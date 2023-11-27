/*
 * Created on 2009/02/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mgrecipe;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.comboboxes.SelectRecipeUse;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeAsprovaList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeHeaderList;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 基本処方-Asprova詳細 Actionクラス.
 * @author tosco
 */
public final class MgrecipeAsprovaDetailAction extends AbstractAction {

	/** 処方ASPROVA Asprova詳細検索 ロジッククラス */
	private MgrecipeAsprovaDetailLogic mgrecipeAsprovaDetailLogic;

	/**
	 * コンストラクタ.
	 */
	public MgrecipeAsprovaDetailAction() {
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

		MgrecipeAsprovaDetailForm detailForm = (MgrecipeAsprovaDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, detailForm, Constants.MENU_ID_MRECIPE,
			Constants.TAB_ID_MRECIPE_ASPROVA);

		if (!detailForm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 処方ヘッダ検索処理
		RecipeHeaderList header = mgrecipeAsprovaDetailLogic
				.getHeader(AecNumberUtils.convertBigDecimal(detailForm
						.getRecipeId()));
		setCommonHeaderInfo(detailForm, header);

		// 処方ASPROVA 検索
		RecipeAsprovaList bean = mgrecipeAsprovaDetailLogic.findByPrimaryKey(
			new BigDecimal(detailForm.getRecipeId()), detailForm
					.getResouceGroupCd(), detailForm.getOperationGroupCd(),
			detailForm.getResouceCd());
		setAsprovaData(detailForm, bean);
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

		MgrecipeAsprovaDetailForm frm = (MgrecipeAsprovaDetailForm) form;

		RecipeAsprovaList bean = frm.getDetailBean();
		bean.setUpdatorCd(getLoginInfo(request).getTantoCd()); // 更新者ID
		// 画面内容をBeanへ設定
		setDetailData(frm, bean);

		// 設備グループマスタと、設備マスタ存在チェック
		ActionMessages errors = mgrecipeAsprovaDetailLogic.checkForUpdate(bean);
		if (!errors.isEmpty()) {
			// エラーがあった場合
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		// 処方ASPROVA 更新処理
		mgrecipeAsprovaDetailLogic.update(bean);

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
	 * @return MgrecipeAsprovaDetailForm
	 */
	private MgrecipeAsprovaDetailForm setCommonHeaderInfo(
			final MgrecipeAsprovaDetailForm form, final RecipeHeaderList header) {
		form.setRecipeCd(header.getRecipeCd()); // レシピコード
		form.setRecipeVersion(getBigDecimalString(header.getRecipeVersion())); // レシピバージョン
		form.setRecipeName(header.getRecipeName()); // 処方名称
		form.setRecipeUse(getBigDecimalString(header.getRecipeUse())); // 用途
		form.setRecipeUseName(SelectRecipeUse.getName(form.getRecipeUse())); // 用途名
		form.setProduct(header.getProduct()); // 品目コード
		form.setItemName(header.getItemName()); // 品目名称
		form.setRecipeStatus(header.getRecipeStatus().toString()); // RECPI_STATUS
		form.setApprovalStatus(header.getApprovalStatus().toString()); // 承認ステータス
		form.setStyleOfPacking(header.getStyleOfPacking()); // 荷姿
		form.setProductionLineName(header.getProductionLineName()); // 生産工場
		form.setApprovalStatusName(header.getApprovalStatusName()); // 承認ステータス名称

		return form;
	}

	/**
	 * 処方ASPROVA 検索結果を画面のFormに設定する。
	 * @param form 画面のForm
	 * @param asprovaBean 処方ASPROVA 検索結果
	 * @return MgrecipeAsprovaDetailForm
	 */
	private MgrecipeAsprovaDetailForm setAsprovaData(
			final MgrecipeAsprovaDetailForm form,
			final RecipeAsprovaList asprovaBean) {
		form.setResouceName(asprovaBean.getResouceName()); // 設備名称
		form.setRecipePriority(getBigDecimalString(asprovaBean
				.getRecipePriority())); // 優先度
		form.setMaejikan(getBigDecimalString(asprovaBean.getMaejikan())); // 前段取時間（分）
		form.setAtojikan(getBigDecimalString(asprovaBean.getAtojikan())); // 後段取時間（分）
		form.setMachineWorkTime1(getBigDecimalString(asprovaBean
				.getMachineWorkTime1())); // 設備稼働時間1(分)
		form.setMachineWorkTime2(getBigDecimalString(asprovaBean
				.getMachineWorkTime2())); // 設備稼働時間2(分)
		form
				.setManWorkTime1(getBigDecimalString(asprovaBean
						.getManWorkTime1())); // 人作業時間1（分）
		form
				.setManWorkTime2(getBigDecimalString(asprovaBean
						.getManWorkTime2())); // 人作業時間2（分）
		form.setProcessWorkTime1(getBigDecimalString(asprovaBean
				.getProcessWorkTime1())); // 工程作業時間1(分)
		form.setProcessWorkTime2(getBigDecimalString(asprovaBean
				.getProcessWorkTime2())); // 工程作業時間2(分)

		return form;
	}

	/**
	 * 画面の内容をBeanに設定
	 * @param frm 画面FORM
	 * @param bean 処方ASPROVA Bean
	 */
	private void setDetailData(final MgrecipeAsprovaDetailForm frm,
			final RecipeAsprovaList bean) {

		bean.setRecipePriority(AecNumberUtils.convertBigDecimal(frm
				.getRecipePriority())); // 優先度
		bean.setMaejikan(AecNumberUtils.convertBigDecimal(frm.getMaejikan())); // 前時間
		bean.setAtojikan(AecNumberUtils.convertBigDecimal(frm.getAtojikan())); // 後時間
		bean.setProcessWorkTime1(AecNumberUtils.convertBigDecimal(frm
				.getProcessWorkTime1())); // 工程作業時間1
		bean.setProcessWorkTime2(AecNumberUtils.convertBigDecimal(frm
				.getProcessWorkTime2())); // 工程作業時間2
		bean.setMachineWorkTime1(AecNumberUtils.convertBigDecimal(frm
				.getMachineWorkTime1())); // 設備稼働時間1
		bean.setMachineWorkTime2(AecNumberUtils.convertBigDecimal(frm
				.getMachineWorkTime2())); // 設備稼働時間2
		bean.setManWorkTime1(AecNumberUtils.convertBigDecimal(frm
				.getManWorkTime1())); // 人作業時間1
		bean.setManWorkTime2(AecNumberUtils.convertBigDecimal(frm
				.getManWorkTime2())); // 人作業時間2
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
	 * mgrecipeAsprovaDetailLogicを設定します。
	 * @param mgrecipeAsprovaDetailLogic mgrecipeAsprovaDetailLogic
	 */
	public void setMgrecipeAsprovaDetailLogic(
			final MgrecipeAsprovaDetailLogic mgrecipeAsprovaDetailLogic) {
		this.mgrecipeAsprovaDetailLogic = mgrecipeAsprovaDetailLogic;
	}

}
