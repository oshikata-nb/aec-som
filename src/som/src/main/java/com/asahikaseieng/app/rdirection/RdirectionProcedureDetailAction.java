/*
 * Created on 2009/02/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.rdirection;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionHeaderList;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionProcedureList;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 製造実績-工程詳細 Actionクラス.
 * @author tosco
 */
public final class RdirectionProcedureDetailAction extends AbstractAction {

	/** 区分 RECIPE1 */
	public static final String UNIT_DIV_RECIPE1 = "RECIPE1";

	/** 区分 RECIPE2 */
	public static final String UNIT_DIV_RECIPE2 = "RECIPE2";

	/** 区分 RECIPE5 */
	public static final String UNIT_DIV_RECIPE5 = "RECIPE5";

	/** 区分 その他 */
	public static final String UNIT_DIV_SONOTA = "SONOTA";

	/** 製造実績-共通ロジッククラス */
	private RdirectionCommonsLogic rdirectionCommonsLogic;

	/** 製造プロシージャ工程詳細検索 ロジッククラス */
	private RdirectionProcedureDetailLogic rdirectionProcedureDetailLogic;

	/**
	 * コンストラクタ.
	 */
	public RdirectionProcedureDetailAction() {
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

		RdirectionProcedureDetailForm detailForm = (RdirectionProcedureDetailForm) form;
		AbstractRdirectionForm commonForm = (AbstractRdirectionForm) form;

		/* 権限取得 */
		getControlAuthority(request, detailForm, Constants.MENU_ID_RDIRECTION,
			Constants.TAB_ID_RDIRECTION_PROCEDURE);

		if (!detailForm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// クリアするので、渡された指図番号を退避
		String directionNo = commonForm.getDirectionNo();
		String stepNo = detailForm.getStrStepNo();
		// クリア
		commonForm.clear();
		commonForm.setDirectionNo(directionNo);
		detailForm.setStrStepNo(stepNo);

		// 共通情報検索処理
		RdirectionDirectionHeaderList header = rdirectionCommonsLogic
				.findByDirectionNo(directionNo);
		setCommonHeaderInfo(detailForm, header);
		// 更新時の為に、検索時の製造指図ヘッダ情報を格納しておく
		commonForm.setHeaderBean(header);

		// 工程詳細検索
		RdirectionDirectionProcedureList bean = rdirectionProcedureDetailLogic
				.getByPrimaryKey(directionNo, new BigDecimal(stepNo));
		// 数値カンマ編集
		formatNum(request, bean);
		// javascript用数値フォーマット定数の設定
		setFormatData(request, detailForm);
		// BeanをFormに一括コピー
		IgnoreCaseBeanUtils.copyProperties(detailForm, bean);
		detailForm.setProcedureRemark(bean.getRemark()); // 備考
		detailForm.setProcedureNotes(bean.getNotes()); // 注釈

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

		RdirectionProcedureDetailForm detailForm = (RdirectionProcedureDetailForm) form;

		// 更新用データ作成
		RdirectionDirectionHeaderList header = RdirectionUtil
				.setDirectionHeader(detailForm, request);
		// 製造指図ヘッダ更新
		rdirectionCommonsLogic.updateDirectionHeader(header);

		// 更新値設定
		RdirectionDirectionProcedureList bean = detailForm.getDetailBean();
		bean.setUpdatorCd(getLoginInfo(request).getTantoCd()); // 更新者ID
		// 画面内容をBeanへ設定
		setDetailFormData(detailForm, bean);

		// 製造プロシージャ更新処理
		rdirectionProcedureDetailLogic.update(bean);

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
	private void setCommonHeaderInfo(
			final RdirectionProcedureDetailForm detailForm,
			final RdirectionDirectionHeaderList header) {
		detailForm.setItemCd(header.getItemCd());
		detailForm.setItemName(header.getItemName());
		detailForm.setDirectionStatus(RdirectionUtil.getBigDecimalString(header
				.getDirectionStatus()));
	}

	/**
	 * 数値のカンマ編集を行う
	 * @param request リクエスト
	 * @param bean 製造指図プロシージャBean
	 */
	private void formatNum(final HttpServletRequest request,
			final RdirectionDirectionProcedureList bean) {
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);
		// 数値のカンマ編集
		bean.setStrConditionTemp(checker.format(UNIT_DIV_RECIPE2, bean
				.getConditionTemp())); // 温度
		bean.setStrResultConditionTemp(checker.format(UNIT_DIV_RECIPE2, bean
				.getResultConditionTemp())); // 温度
		bean.setStrConditionTime(checker.format(UNIT_DIV_RECIPE1, bean
				.getConditionTime())); // 時間
		bean.setStrStirSpeed1(checker.format(UNIT_DIV_RECIPE1, bean
				.getStirSpeed1())); // 攪拌速度1
		bean.setStrResultStirSpeed(checker.format(UNIT_DIV_RECIPE1, bean
				.getResultStirSpeed())); // 攪拌速度1
		bean.setStrStirSpeed2(checker.format(UNIT_DIV_RECIPE1, bean
				.getStirSpeed2())); // 攪拌速度2
		bean.setStrWaterWeight(checker.format(UNIT_DIV_SONOTA, bean
				.getWaterWeight())); // 洗浄水絶対重量
		bean.setStrPh(checker.format(UNIT_DIV_RECIPE2, bean.getPh())); // pH
		bean
				.setStrResultPh(checker.format(UNIT_DIV_RECIPE2, bean
						.getResultPh())); // pH

		// 日付フォーマット変換
		if (bean.getResultSdate() != null) {
			bean.setStrResultSDay(AecDateUtils.dateFormat(
				bean.getResultSdate(), "yyyy/MM/dd"));
			bean.setStrResultSTime(AecDateUtils.dateFormat(bean
					.getResultSdate(), "HH:mm"));
		}
		if (bean.getResultEdate() != null) {
			bean.setStrResultEDay(AecDateUtils.dateFormat(
				bean.getResultEdate(), "yyyy/MM/dd"));
			bean.setStrResultETime(AecDateUtils.dateFormat(bean
					.getResultEdate(), "HH:mm"));
		}

		if (bean.getMainStream() != null) {
			bean.setStrMainStream(bean.getMainStream().toString()); // 本流/予備溶解
		}
	}

	/**
	 * 画面の内容をBeanに設定
	 * @param frm 画面FORM
	 * @param bean 製造指図プロシージャBean
	 */
	private void setDetailFormData(final RdirectionProcedureDetailForm frm,
			final RdirectionDirectionProcedureList bean) {
		bean.setConditionTemp(AecNumberUtils.convertBigDecimal(frm
				.getStrConditionTemp())); // 温度
		bean.setResultConditionTemp(AecNumberUtils.convertBigDecimal(frm
				.getStrResultConditionTemp())); // 温度
		bean.setConditionTime(AecNumberUtils.convertBigDecimal(frm
				.getStrConditionTime())); // 時間
		bean.setStirSpeed1(AecNumberUtils.convertBigDecimal(frm
				.getStrStirSpeed1())); // 攪拌速度1
		bean.setResultStirSpeed(AecNumberUtils.convertBigDecimal(frm
				.getStrResultStirSpeed())); // 攪拌速度1
		bean.setStirSpeed2(AecNumberUtils.convertBigDecimal(frm
				.getStrStirSpeed2())); // 攪拌速度2
		bean.setWaterWeight(AecNumberUtils.convertBigDecimal(frm
				.getStrWaterWeight())); // 洗浄水絶対重量
		bean.setMainStream(AecNumberUtils.convertBigDecimal(frm
				.getStrMainStream())); // 本流/予備溶解
		bean.setPh(AecNumberUtils.convertBigDecimal(frm.getStrPh())); // pH

		bean.setCondition(frm.getCondition()); // 工程
		bean.setRemark(frm.getProcedureRemark()); // 備考
		bean.setNotes(frm.getProcedureNotes()); // 注釈

		// 日付と時間を結合し、Timestamp型に変換しセット
		// 工程開始実績日時
		String strDay = frm.getStrResultSDay();
		String strTime = frm.getStrResultSTime();
		bean.setResultSdate(getTimestampYmdHmsFormat(strDay, strTime, ""));
		// 工程終了実績日時
		strDay = frm.getStrResultEDay();
		strTime = frm.getStrResultETime();
		bean.setResultEdate(getTimestampYmdHmsFormat(strDay, strTime, ""));

	}

	/**
	 * 数値フォーマット情報設定（ javascript用）
	 * @param request HttpServletRequest
	 * @param detailForm 工程タグ
	 */
	private void setFormatData(final HttpServletRequest request,
			final RdirectionProcedureDetailForm detailForm) {
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);
		NumberChkDisitDetail checkDetail = new NumberChkDisitDetail();

		// RECIPE1
		checkDetail = checker.getCheckDigit(UNIT_DIV_RECIPE1, null, null);
		detailForm.setDecimalPointRecipe1(RdirectionUtil
				.getBigDecimalString(checkDetail.getSmallnumLength()));
		detailForm.setRoundDivisionRecipe1(RdirectionUtil
				.getBigDecimalString(checkDetail.getRoundDivision()));

		// RECIPE2
		checkDetail = checker.getCheckDigit(UNIT_DIV_RECIPE2, null, null);
		detailForm.setDecimalPointRecipe2(RdirectionUtil
				.getBigDecimalString(checkDetail.getSmallnumLength()));
		detailForm.setRoundDivisionRecipe2(RdirectionUtil
				.getBigDecimalString(checkDetail.getRoundDivision()));

		// SONOTA
		checkDetail = checker.getCheckDigit(UNIT_DIV_SONOTA, null, null);
		detailForm.setDecimalPointSonota(RdirectionUtil
				.getBigDecimalString(checkDetail.getSmallnumLength()));
		detailForm.setRoundDivisionSonota(RdirectionUtil
				.getBigDecimalString(checkDetail.getRoundDivision()));
	}

	/**
	 * 日付文字列、時間文字列を結合してTimestamp型で返却する
	 * @param strDay 日付(yyyy/MM/dd)
	 * @param strTime 時分(HH:mm)
	 * @param strDefTime デフォルト時間(HH:mm:ss)
	 * @return Timestamp 文字列結合後のTimestamp
	 */
	private Timestamp getTimestampYmdHmsFormat(final String strDay,
			final String strTime, final String strDefTime) {
		Timestamp timestamp = null;
		String strFormat = "yyyy/MM/dd HH:mm:ss";
		if (StringUtils.isNotEmpty(strDay)) {
			String strDate = strDay;
			if (StringUtils.isNotEmpty(strTime)) {
				String[] strHms = strDefTime.split(":", 3);
				strDate = strDate + " " + strTime;
				if (strHms != null && strHms.length == 3) {
					strDate = strDate + ":" + strHms[2];
				} else {
					strFormat = "yyyy/MM/dd HH:mm";
				}
			} else {
				strDate = strDate + " " + strDefTime;
			}
			timestamp = AecDateUtils
					.getTimestampYmdHmFormat(strDate, strFormat);
		}
		return timestamp;
	}

	/* -------------------- setter -------------------- */

	/**
	 * 製造実績-共通ロジッククラスを設定します。
	 * @param rdirectionCommonsLogic 製造実績-共通ロジッククラス
	 */
	public void setRdirectionCommonsLogic(
			final RdirectionCommonsLogic rdirectionCommonsLogic) {
		this.rdirectionCommonsLogic = rdirectionCommonsLogic;
	}

	/**
	 * 製造プロシージャ工程詳細検索 ロジッククラスを設定します。
	 * @param rdirectionProcedureDetailLogic 製造プロシージャ工程詳細検索 ロジッククラス
	 */
	public void setRdirectionProcedureDetailLogic(
			final RdirectionProcedureDetailLogic rdirectionProcedureDetailLogic) {
		this.rdirectionProcedureDetailLogic = rdirectionProcedureDetailLogic;
	}

}
