/*
 * Created on 2009/02/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.rdirection;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.comboboxes.SelectRecipeUse;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionHeaderList;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionInspectionList;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 製造実績-検査詳細 Actionクラス.
 * @author tosco
 */
public final class RdirectionInspectionDetailAction extends AbstractAction {

	/** 製造実績検査-詳細検索 ロジッククラス */
	private RdirectionInspectionDetailLogic rdirectionInspectionDetailLogic;

	/** 製造実績検索 ロジッククラス */
	private RdirectionCommonsLogic rdirectionCommonsLogic;

	/**
	 * コンストラクタ.
	 */
	public RdirectionInspectionDetailAction() {
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

		RdirectionInspectionDetailForm detailForm = (RdirectionInspectionDetailForm) form;
		AbstractRdirectionForm commonForm = (AbstractRdirectionForm) form;

		/* 権限取得 */
		getControlAuthority(request, detailForm, Constants.MENU_ID_RDIRECTION,
			Constants.TAB_ID_RDIRECTION_INSPECTION);

		if (!detailForm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 共通情報検索処理
		RdirectionDirectionHeaderList common = rdirectionCommonsLogic
				.findByDirectionNo(detailForm.getDirectionNo());
		// 更新時の為に、検索時の製造実績ヘッダー情報を格納しておく
		commonForm.setHeaderBean(common);
		commonForm.setDirectionStatus(getBigDecimalString(common
				.getDirectionStatus()));
		// 製造実績ヘッダ検索処理
		RdirectionDirectionHeaderList header = rdirectionInspectionDetailLogic
				.getInspectionHeader(detailForm.getDirectionNo(),
					AecNumberUtils.convertBigDecimal(detailForm.getStepNo()));
		setCommonHeaderInfo(detailForm, header);

		// 製造実績詳細検索
		RdirectionDirectionInspectionList bean = rdirectionInspectionDetailLogic
				.getEntity(detailForm.getDirectionNo(), new BigDecimal(
						detailForm.getStepNo()), new BigDecimal(detailForm
						.getLineNo()));
		setInspectionDeta(detailForm, bean);

		// ラジオボタンにデフォルト値セット
		if (StringUtils.isEmpty(detailForm.getValueType())) {
			detailForm.setValueType("1");
		}
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

		RdirectionInspectionDetailForm frm = (RdirectionInspectionDetailForm) form;

		RdirectionDirectionInspectionList bean = frm.getDetailBean();
		bean.setUpdatorCd(getLoginInfo(request).getTantoCd()); // 更新者ID
		// 画面内容をBeanへ設定
		setDetailFormData(frm, bean);

		// 各種名称マスタ存在チェック
		ActionMessages errors = rdirectionInspectionDetailLogic
				.checkForUpdate(bean);
		if (!errors.isEmpty()) {
			// エラーがあった場合
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		// 更新用データ作成
		RdirectionDirectionHeaderList header = RdirectionUtil
				.setDirectionHeader(frm, request);
		// 製造実績ヘッダ更新
		rdirectionCommonsLogic.updateDirectionHeader(header);

		// 製造実績検査更新処理
		rdirectionInspectionDetailLogic.update(bean);

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
	 * @param form RdirectionInspectionDetailForm
	 * @param header ヘッダー情報
	 * @return RdirectionInspectionDetailForm
	 */
	private RdirectionInspectionDetailForm setCommonHeaderInfo(
			final RdirectionInspectionDetailForm form,
			final RdirectionDirectionHeaderList header) {

		form.setRecipeCd(header.getRecipeCd()); // レシピコード
		form.setRecipeVersion(getBigDecimalString(header.getRecipeVersion())); // レシピバージョン
		form.setRecipeName(header.getRecipeName()); // 処方名称
		form.setRecipeUse(getBigDecimalString(header.getRecipeUse())); // 用途
		form.setRecipeUseName(SelectRecipeUse.getName(form.getRecipeUse())); // 用途名
		form.setItemCd(header.getItemCd()); // 品目コード
		form.setItemName(header.getItemName()); // 品目名称
		form.setOperationCd(header.getOperationCd()); // 工程コード
		form.setOperationName(header.getOperationName()); // 工程名称
		return form;
	}

	/**
	 * 製造実績検査-検索結果を画面のFormに設定する。
	 * @param form RdirectionInspectionDetailForm
	 * @param inspectionBean 製造実績検査-検索結果
	 * @return RdirectionInspectionDetailForm
	 */
	private RdirectionInspectionDetailForm setInspectionDeta(
			final RdirectionInspectionDetailForm form,
			final RdirectionDirectionInspectionList inspectionBean) {

		form.setDirectionDivision(getBigDecimalString(inspectionBean
				.getDirectionDivision())); // 指図区分
		form.setDirectionNo(inspectionBean.getDirectionNo()); // 指図番号
		form.setStepNo(getBigDecimalString(inspectionBean.getStepNo())); // ステップＮｏ
		form.setLineNo(getBigDecimalString(inspectionBean.getLineNo())); // 行Ｎｏ
		form.setInspectionCd(inspectionBean.getInspectionCd()); // 検査コード
		form.setInspectionName(inspectionBean.getInspectionName()); // 検査名称
		form.setStrInspectionCondition(inspectionBean.getCondition()); // 条件
		form.setStrInspectionDivision(inspectionBean.getDivision()); // 区分
		form.setResultValue1(inspectionBean.getResultValue1()); // 値1
		form.setValue1(inspectionBean.getValue1()); // 値2
		form.setValue2(inspectionBean.getValue2()); // 値2
		form.setValueType(inspectionBean.getValueType()); // 入力種類|1:数値,2:文字列
		form.setRemark(inspectionBean.getRemark()); // 備考
		form.setNotes(inspectionBean.getNotes()); // 注釈

		return form;
	}

	/**
	 * 画面の内容をBeanに設定
	 * @param frm RdirectionInspectionDetailForm
	 * @param bean 製造実績検査Bean
	 */
	private void setDetailFormData(final RdirectionInspectionDetailForm frm,
			final RdirectionDirectionInspectionList bean) {
		BigDecimal division = new BigDecimal(frm.getDirectionDivision());
		BigDecimal stepNo = new BigDecimal(frm.getStepNo());
		BigDecimal lineNo = new BigDecimal(frm.getLineNo());

		bean.setDirectionDivision(division); // 指図区分
		bean.setDirectionNo(frm.getDirectionNo()); // 指図番号
		bean.setStepNo(stepNo); // ステップＮｏ
		bean.setLineNo(lineNo); // 行Ｎｏ
		bean.setInspectionCd(frm.getInspectionCd()); // 検査コード
		bean.setCondition(frm.getStrInspectionCondition()); // 条件
		bean.setDivision(frm.getStrInspectionDivision()); // 区分
		bean.setResultValue1(frm.getResultValue1()); // 値1
		bean.setValue2(frm.getValue2()); // 値2
		bean.setValue1(frm.getValue1()); // 値2
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
	 * 製造実績検査-詳細検索 ロジッククラスを設定します。
	 * @param rdirectionInspectionDetailLogic 製造実績検査詳細検索 ロジッククラス
	 */
	public void setDirectionInspectionDetailLogic(
			final RdirectionInspectionDetailLogic rdirectionInspectionDetailLogic) {
		this.rdirectionInspectionDetailLogic = rdirectionInspectionDetailLogic;
	}

	/**
	 * 製造実績-共通ロジッククラスを設定します。
	 * @param rdirectionCommonsLogic 製造実績-共通ロジッククラス
	 */
	public void setRdirectionCommonsLogic(
			final RdirectionCommonsLogic rdirectionCommonsLogic) {
		this.rdirectionCommonsLogic = rdirectionCommonsLogic;
	}
}
