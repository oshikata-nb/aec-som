/*
 * Created on 2009/02/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.direction;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.comboboxes.SelectRecipeUse;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionHeaderList;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionInspectionList;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 製造指図-検査詳細 Actionクラス.
 * @author tosco
 */
public final class DirectionInspectionDetailAction extends AbstractAction {

	/** 製造指図検査詳細検索 ロジッククラス */
	private DirectionInspectionDetailLogic directionInspectionDetailLogic;

	/** 製造指図検査詳細検索 ロジッククラス */
	private DirectionCommonsLogic directionCommonsLogic;

	/**
	 * コンストラクタ.
	 */
	public DirectionInspectionDetailAction() {
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

		DirectionInspectionDetailForm detailForm = (DirectionInspectionDetailForm) form;
		AbstractDirectionForm commonForm = (AbstractDirectionForm) form;

		/* 権限取得 */
		getControlAuthority(request, detailForm, Constants.MENU_ID_DIRECTION,
			Constants.TAB_ID_DIRECTION_INSPECTION);

		if (!detailForm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 共通情報検索処理
		DirectionDirectionHeaderList common = directionCommonsLogic
				.findByDirectionNo(detailForm.getDirectionNo());
		// 更新時の為に、検索時の製造指図ヘッダー情報を格納しておく
		commonForm.setHeaderBean(common);
		commonForm.setDirectionStatus(getBigDecimalString(common
				.getDirectionStatus()));
		// 製造指図ヘッダ検索処理
		DirectionDirectionHeaderList header = directionInspectionDetailLogic
				.getInspectionHeader(detailForm.getDirectionNo(),
					AecNumberUtils.convertBigDecimal(detailForm.getStepNo()));
		setCommonHeaderInfo(detailForm, header);

		// 製造指図詳細検索
		DirectionDirectionInspectionList bean = directionInspectionDetailLogic
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

		DirectionInspectionDetailForm frm = (DirectionInspectionDetailForm) form;

		DirectionDirectionInspectionList bean = frm.getDetailBean();
		bean.setUpdatorCd(getLoginInfo(request).getTantoCd()); // 更新者ID
		// 画面内容をBeanへ設定
		setDetailFormData(frm, bean);

		// 各種名称マスタ存在チェック
		ActionMessages errors = directionInspectionDetailLogic
				.checkForUpdate(bean);
		if (!errors.isEmpty()) {
			// エラーがあった場合
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		// 更新用データ作成
		DirectionDirectionHeaderList header = DirectionUtil.setDirectionHeader(
			frm, request);
		boolean isIssued = false;
		if (DirectionConst.DIRECTION_STATUS_ISSUED.compareTo(header
				.getDirectionStatus()) == 0) {
			// 製造計画の更新チェック
			String errMsgKey = directionCommonsLogic.checkSeizoKeikaku(header
					.getDirectionNo());
			if (errMsgKey != null) {
				saveError(request, errMsgKey);
				return mapping.findForward("error");
			}
			isIssued = true;
		}

		try {
			// 製造指図検査更新処理
			directionInspectionDetailLogic.update(bean, header);

			if (isIssued) {
				// 指図ステータス=指図書発行済みの場合
				// 計装IFテーブルデータ削除（製造計画、製造指示）
				directionCommonsLogic.deleteSeizo(header.getDirectionNo());
			}
		} catch (LogicExceptionEx e) {
			String errMsg = "errors.direction.stock.update";
			// 在庫更新に失敗
			if (errMsg.equals(e.getMessage())) {
				saveError(request, errMsg);
			} else {
				throw e;
			}
			return mapping.getInputForward();
		} catch (DirectionLogicException e) {
			String[] replacements = e.getReplacements();
			if (replacements != null) {
				int len = replacements.length;
				for (int i = 0; i < len; i++) {
					String buf = getMessageResource(request, replacements[i]);
					if (StringUtils.isNotEmpty(buf)) {
						replacements[i] = buf;
					}
				}
			}
			addError(request, e.getKey(), (Object[]) replacements);
			return mapping.getInputForward();
		}

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
	 * @return DirectionInspectionDetailForm
	 */
	private DirectionInspectionDetailForm setCommonHeaderInfo(
			final DirectionInspectionDetailForm form,
			final DirectionDirectionHeaderList header) {

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
	 * 製造指図検査検索結果を画面のFormに設定する。
	 * @param form 画面のForm
	 * @param inspectionBean 製造指図検査-検索結果
	 * @return DirectionInspectionDetailForm
	 */
	private DirectionInspectionDetailForm setInspectionDeta(
			final DirectionInspectionDetailForm form,
			final DirectionDirectionInspectionList inspectionBean) {

		form.setDirectionDivision(getBigDecimalString(inspectionBean
				.getDirectionDivision())); // 指図区分
		form.setDirectionNo(inspectionBean.getDirectionNo()); // 指図番号
		form.setStepNo(getBigDecimalString(inspectionBean.getStepNo())); // ステップＮｏ
		form.setLineNo(getBigDecimalString(inspectionBean.getLineNo())); // 行Ｎｏ
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
	 * @param bean 製造指図検査Bean
	 */
	private void setDetailFormData(final DirectionInspectionDetailForm frm,
			final DirectionDirectionInspectionList bean) {
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

	/**
	 * エラーメッセージを追加する。
	 * @param request HttpServletRequest
	 * @param key メッセージキー
	 * @param objects 置換パラメータ
	 */
	protected void addError(final HttpServletRequest request, final String key,
			final Object... objects) {
		ActionMessages messages = new ActionMessages();
		messages.add("", new ActionMessage(key, objects));
		addErrors(request, messages);
	}

	/**
	 * メッセージプロパティファイルから指定したkeyに対応する文字列を取得する。
	 * @param request HttpServletRequest
	 * @param key メッセージキー
	 * @return メッセージキーに対応するメッセージ文字列
	 */
	protected String getMessageResource(final HttpServletRequest request,
			final String key) {
		MessageResources resource = getResources(request);
		return resource.getMessage(key);
	}

	/* -------------------- setter -------------------- */

	/**
	 * 製造指図検査-詳細検索 ロジッククラスを設定します。
	 * @param directionInspectionDetailLogic 製造指図検査詳細検索 ロジッククラス
	 */
	public void setDirectionInspectionDetailLogic(
			final DirectionInspectionDetailLogic directionInspectionDetailLogic) {
		this.directionInspectionDetailLogic = directionInspectionDetailLogic;
	}

	/**
	 * 製造指図-共通ロジッククラスを設定します。
	 * @param directionCommonsLogic 製造指図-共通ロジッククラス
	 */
	public void setDirectionCommonsLogic(
			final DirectionCommonsLogic directionCommonsLogic) {
		this.directionCommonsLogic = directionCommonsLogic;
	}
}
