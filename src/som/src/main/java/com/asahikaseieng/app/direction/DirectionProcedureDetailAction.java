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
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionHeaderList;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionProcedureList;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 製造指図-工程詳細 Actionクラス.
 * @author tosco
 */
public final class DirectionProcedureDetailAction extends AbstractAction {

	/** 区分　RECIPE1 */
	public static final String UNIT_DIV_RECIPE1 = "RECIPE1";
	/** 区分　RECIPE2 */
	public static final String UNIT_DIV_RECIPE2 = "RECIPE2";
	/** 区分　RECIPE5 */
	public static final String UNIT_DIV_RECIPE5 = "RECIPE5";
	/** 区分　その他 */
	public static final String UNIT_DIV_SONOTA = "SONOTA";

	/** 製造指図-共通ロジッククラス */
	private DirectionCommonsLogic directionCommonsLogic;

	/** 製造プロシージャ工程詳細検索 ロジッククラス */
	private DirectionProcedureDetailLogic directionProcedureDetailLogic;

	/**
	 * コンストラクタ.
	 */
	public DirectionProcedureDetailAction() {
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
	public ActionForward init(final ActionMapping mapping, final ActionForm form,
			final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		DirectionProcedureDetailForm detailForm = (DirectionProcedureDetailForm) form;
		AbstractDirectionForm commonForm = (AbstractDirectionForm) form;

		/* 権限取得 */
		getControlAuthority(request, detailForm, Constants.MENU_ID_DIRECTION,
			Constants.TAB_ID_DIRECTION_PROCEDURE);

		if (!detailForm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		//クリアするので、渡された指図番号を退避
		String directionNo = commonForm.getDirectionNo();
		String stepNo = detailForm.getStrStepNo();
		//クリア
		commonForm.clear();
		commonForm.setDirectionNo(directionNo);
		detailForm.setStrStepNo(stepNo);

		//共通情報検索処理
		DirectionDirectionHeaderList header = directionCommonsLogic.findByDirectionNo(directionNo);
		setCommonHeaderInfo(detailForm, header);
		//更新時の為に、検索時の処方ヘッダー情報を格納しておく
		commonForm.setHeaderBean(header);

		// 工程詳細検索
		DirectionDirectionProcedureList bean =
			directionProcedureDetailLogic.getByPrimaryKey(directionNo, new BigDecimal(stepNo));
		// 数値カンマ編集 + 移し変え
		formatNum(detailForm, bean, request);
		// javascript用数値フォーマット定数の設定
		setFormatData(request, detailForm);
		// BeanをFormに一括コピー
		IgnoreCaseBeanUtils.copyProperties(detailForm, bean);
		detailForm.setProcedureRemark(bean.getRemark());	// 備考
		detailForm.setProcedureNotes(bean.getNotes());		// 注釈

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

		DirectionProcedureDetailForm detailForm = (DirectionProcedureDetailForm) form;

		//更新用データ作成
		DirectionDirectionHeaderList header = DirectionUtil.setDirectionHeader(detailForm, request);
		boolean isIssued = false;
		if (DirectionConst.DIRECTION_STATUS_ISSUED.compareTo(header.getDirectionStatus()) == 0) {
			// 製造計画の更新チェック
			String errMsgKey = directionCommonsLogic.checkSeizoKeikaku(header.getDirectionNo());
			if (errMsgKey != null) {
				saveError(request, errMsgKey);
				return mapping.findForward("error");
			}
			isIssued = true;
		}

		// 更新値設定
		DirectionDirectionProcedureList bean = detailForm.getDetailBean();
		bean.setUpdatorCd(getLoginInfo(request).getTantoCd());	// 更新者ID
		// 画面内容をBeanへ設定
		setDetailFormData(detailForm, bean);

		try {
			// 製造プロシージャ更新処理
			directionProcedureDetailLogic.update(bean, header);

			if (isIssued) {
				//指図ステータス=指図書発行済みの場合
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
	 * @param detailForm 画面のForm
	 * @param header ヘッダー情報
	 */
	private void setCommonHeaderInfo(final DirectionProcedureDetailForm detailForm
			, final DirectionDirectionHeaderList header) {
		detailForm.setItemCd(header.getItemCd());
		detailForm.setItemName(header.getItemName());
		detailForm.setDirectionStatus(DirectionUtil.getBigDecimalString(header.getDirectionStatus()));
	}

	/**
	 * 数値のカンマ編集を行う
	 * @param frm 画面FORM
	 * @param bean 製造指図プロシージャBean
	 * @param request リクエスト
	 */
	private void formatNum(final DirectionProcedureDetailForm frm
							, final DirectionDirectionProcedureList bean
							, final HttpServletRequest request) {
		CheckDigitUtilsLogic checker = CheckDigitUtil.getCheckDigitUtils(request);
		// 数値のカンマ編集
		bean.setStrConditionTemp(checker.format(UNIT_DIV_RECIPE2, bean.getConditionTemp())); // 温度
		bean.setStrConditionTime(checker.format(UNIT_DIV_RECIPE1, bean.getConditionTime())); // 時間
		bean.setStrStirSpeed1(checker.format(UNIT_DIV_RECIPE1, bean.getStirSpeed1()));	// 攪拌速度1
		bean.setStrStirSpeed2(checker.format(UNIT_DIV_RECIPE1, bean.getStirSpeed2()));	// 攪拌速度2
		bean.setStrWaterWeight(checker.format(UNIT_DIV_SONOTA, bean.getWaterWeight())); // 洗浄水絶対重量
		if (bean.getMainStream() != null) {
			bean.setStrMainStream(bean.getMainStream().toString());		// 本流/予備溶解
		}
		frm.setCondition(bean.getCondition());		// 工程条件自由入力
		frm.setProcedureRemark(bean.getRemark());	// 備考
		frm.setProcedureNotes(bean.getNotes());		// 注釈
	}

	/**
	 * 画面の内容をBeanに設定
	 * @param frm 画面FORM
	 * @param bean 製造指図プロシージャBean
	 */
	private void setDetailFormData(final DirectionProcedureDetailForm frm
								, final DirectionDirectionProcedureList bean) {
		bean.setConditionTemp(AecNumberUtils.convertBigDecimal(frm.getStrConditionTemp()));	// 温度
		bean.setConditionTime(AecNumberUtils.convertBigDecimal(frm.getStrConditionTime()));	// 時間
		bean.setStirSpeed1(AecNumberUtils.convertBigDecimal(frm.getStrStirSpeed1()));	// 攪拌速度1
		bean.setStirSpeed2(AecNumberUtils.convertBigDecimal(frm.getStrStirSpeed2()));	// 攪拌速度2
		bean.setWaterWeight(AecNumberUtils.convertBigDecimal(frm.getStrWaterWeight()));	// 洗浄水絶対重量
		bean.setMainStream(AecNumberUtils.convertBigDecimal(frm.getStrMainStream()));	// 本流/予備溶解
		bean.setCondition(frm.getCondition());		// 工程条件自由入力
		bean.setRemark(frm.getProcedureRemark());	// 備考
		bean.setNotes(frm.getProcedureNotes());		// 注釈
	}

	/**
	 * 数値フォーマット情報設定（ javascript用）
	 * @param request HttpServletRequest
	 * @param detailForm 工程タグ
	 */
	private void setFormatData(final HttpServletRequest request, final DirectionProcedureDetailForm detailForm) {
		CheckDigitUtilsLogic checker = CheckDigitUtil.getCheckDigitUtils(request);
		NumberChkDisitDetail checkDetail = new NumberChkDisitDetail();

		// RECIPE1
		checkDetail = checker.getCheckDigit(UNIT_DIV_RECIPE1, null, null);
		detailForm.setDecimalPointRecipe1(DirectionUtil.getBigDecimalString(checkDetail.getSmallnumLength()));
		detailForm.setRoundDivisionRecipe1(DirectionUtil.getBigDecimalString(checkDetail.getRoundDivision()));

		// RECIPE2
		checkDetail = checker.getCheckDigit(UNIT_DIV_RECIPE2, null, null);
		detailForm.setDecimalPointRecipe2(DirectionUtil.getBigDecimalString(checkDetail.getSmallnumLength()));
		detailForm.setRoundDivisionRecipe2(DirectionUtil.getBigDecimalString(checkDetail.getRoundDivision()));

		// SONOTA
		checkDetail = checker.getCheckDigit(UNIT_DIV_SONOTA, null, null);
		detailForm.setDecimalPointSonota(DirectionUtil.getBigDecimalString(checkDetail.getSmallnumLength()));
		detailForm.setRoundDivisionSonota(DirectionUtil.getBigDecimalString(checkDetail.getRoundDivision()));
	}

	/**
	 * エラーメッセージを追加する。
	 * @param request HttpServletRequest
	 * @param key メッセージキー
	 * @param objects 置換パラメータ
	 */
	protected void addError(final HttpServletRequest request, final String key, final Object... objects) {
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
	protected String getMessageResource(final HttpServletRequest request, final String key) {
		 MessageResources resource = getResources(request);
		 return resource.getMessage(key);
	}

	/* -------------------- setter -------------------- */

	/**
	 * 製造指図-共通ロジッククラスを設定します。
	 * @param directionCommonsLogic 製造指図-共通ロジッククラス
	 */
	public void setDirectionCommonsLogic(final DirectionCommonsLogic directionCommonsLogic) {
		this.directionCommonsLogic = directionCommonsLogic;
	}

	/**
	 * 製造プロシージャ工程詳細検索 ロジッククラスを設定します。
	 * @param directionProcedureDetailLogic 製造プロシージャ工程詳細検索 ロジッククラス
	 */
	public void setDirectionProcedureDetailLogic(
			final DirectionProcedureDetailLogic directionProcedureDetailLogic) {
		this.directionProcedureDetailLogic = directionProcedureDetailLogic;
	}

}
