/*
 * Created on 2009/03/26
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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionFormulaList;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionHeaderList;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 製造実績－配合詳細 Actionクラス.
 * @author tosco
 */
public final class RdirectionFormulaDetailAction extends AbstractAction {

	/** 製造実績-共通ロジッククラス */
	private RdirectionCommonsLogic rdirectionCommonsLogic;

	/** 製造実績－フォーミュラ配合詳細検索 ロジッククラス */
	private RdirectionFormulaDetailLogic rdirectionFormulaDetailLogic;

	/** 製造区分 製造 */
	protected static final String DIRECTION_DIVISION = "1";

	/**
	 * コンストラクタ.
	 */
	public RdirectionFormulaDetailAction() {
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

		RdirectionFormulaDetailForm detailForm = (RdirectionFormulaDetailForm) form;
		AbstractRdirectionForm commonForm = (AbstractRdirectionForm) form;

		/* 権限取得 */
		getControlAuthority(request, detailForm, Constants.MENU_ID_RDIRECTION,
			Constants.TAB_ID_RDIRECTION_FORMULA);

		if (!detailForm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		saveKeyData(detailForm, commonForm);

		// 共通情報検索処理
		RdirectionDirectionHeaderList header = rdirectionCommonsLogic
				.findByDirectionNo(detailForm.getDirectionNo());
		// 更新時の為に、検索時の製造指図－ヘッダー情報を格納しておく
		commonForm.setHeaderBean(header);
		setCommonHeaderInfo(detailForm, header, request);

		// 工程コード、工程名称を取得する
		String[] res = rdirectionFormulaDetailLogic.getOperationName(detailForm
				.getDirectionNo(), new BigDecimal(detailForm.getStepNo()));
		detailForm.setOperationCd(res[0]);
		detailForm.setOperationName(res[1]);

		// 製造指図－フォーミュラ検索
		RdirectionDirectionFormulaList bean = rdirectionFormulaDetailLogic
				.getByPrimaryKey(detailForm.getDirectionNo(), new BigDecimal(
						detailForm.getStepNo()), new BigDecimal(detailForm
						.getLineNo()));
		setFormulaData(detailForm, bean, request);
		// Beanの内容をFormに保持
		detailForm.setDetailBean(bean);

		// 画面変更前の実績数量を保持
		detailForm.setBeforResultQty(detailForm.getResultQty());

		return mapping.findForward("success");
	}

	/**
	 * キー項目の保存
	 * @param detailForm
	 * @param commonForm
	 */
	private void saveKeyData(final RdirectionFormulaDetailForm detailForm,
			final AbstractRdirectionForm commonForm) {
		// クリアするので、渡された指図番号・STEP_NO、LINE_NOを退避
		String directionNo = commonForm.getDirectionNo();
		String stepNo = detailForm.getStepNo();
		String lineNo = detailForm.getLineNo();
		// クリア
		commonForm.clear();
		// クリアされた指図番号・STEP_NO、LINE_NOを移行
		commonForm.setDirectionNo(directionNo);
		detailForm.setStepNo(stepNo);
		detailForm.setLineNo(lineNo);
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

		RdirectionFormulaDetailForm detailForm = (RdirectionFormulaDetailForm) form;

		// 製造ヘッダの実績日時がNullの場合エラー
		ActionMessages headerErrors = rdirectionFormulaDetailLogic
				.checkForResultDate(DIRECTION_DIVISION, detailForm
						.getDirectionNo());
		if (!headerErrors.isEmpty()) {
			// エラーがあった場合
			super.saveErrors(request, headerErrors);
			return mapping.findForward("error");
		}

		// 2011/02/01 登録時にﾛｯﾄ、ﾛｹが決定している場合、製造配合とロット在庫のロケが同一かチェックを追加

		// 登録ボタン押下時に数量が異なっていた場合ロケ同一チェックを行う
		if (detailForm.getBeforResultQty() != null
				&& !detailForm.getBeforResultQty().equals(
					detailForm.getResultQty())) {

			ActionMessages formulaErrors = rdirectionFormulaDetailLogic
					.checkForFormulaResultDate(detailForm.getDirectionNo(),
						new BigDecimal(detailForm.getStepNo()), new BigDecimal(
								detailForm.getLineNo()));

			// 製造配合とロット在庫のロケが不一致の場合エラーを画面に表示
			if (!formulaErrors.isEmpty()) {
				// エラーがあった場合
				super.saveErrors(request, formulaErrors);
				return mapping.findForward("error");
			}
		}
		RdirectionDirectionFormulaList bean = detailForm.getDetailBean();
		RdirectionDirectionFormulaList updBean = new RdirectionDirectionFormulaList();
		IgnoreCaseBeanUtils.copyProperties(updBean, bean);

		updBean.setUpdatorCd(getLoginInfo(request).getTantoCd()); // 更新者ID
		// 画面内容をBeanへ設定
		setDetailFormData(detailForm, updBean);

		// 品目マスタ存在チェック
		ActionMessages errors = rdirectionFormulaDetailLogic.checkForUpdate(
			updBean, bean);
		if (!errors.isEmpty()) {
			// エラーがあった場合
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		// 更新用データ作成
		RdirectionDirectionHeaderList header = RdirectionUtil
				.setDirectionHeader(detailForm, request);

		try {
			// 製造指図－フォーミュラ更新処理
			rdirectionFormulaDetailLogic.update(updBean, header);
		} catch (RdirectionLogicException e) {
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
			if (StringUtils.isNotEmpty(e.getModuleCd())) {
				// エラーログに出力する
				rdirectionCommonsLogic.outPutErrorLog(e.getModuleCd(), e
						.getInsideErrCd(), e.getInsideErrMsg(), getLoginInfo(
					request).getTantoCd());
			}
			return mapping.getInputForward();
		}

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
	 */
	private void setCommonHeaderInfo(final RdirectionFormulaDetailForm form,
			final RdirectionDirectionHeaderList header,
			final HttpServletRequest request) {
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);
		form.setItemCd(header.getItemCd()); // 品目コード
		form.setItemName(header.getItemName()); // 品目名称
		// 仕込み予定数量
		form.setPlanedQty(checker.format(RdirectionConst.UNIT_DIV_HAIGO, header
				.getPlanedQty()));
		form
				.setUnitOfOperationManagement(header
						.getUnitOfOperationManagement());
		form.setUnitName(header.getUnitName());
		form.setDirectionStatus(RdirectionUtil.getBigDecimalString(header
				.getDirectionStatus()));
	}

	/**
	 * 製造指図－フォーミュラ検索結果を画面のFormに設定する。
	 * @param form 画面のForm
	 * @param formulaBean 製造指図－フォーミュラ検索結果
	 * @return MgrecipeFormulaDetailForm
	 */
	private RdirectionFormulaDetailForm setFormulaData(
			final RdirectionFormulaDetailForm form,
			final RdirectionDirectionFormulaList formulaBean,
			final HttpServletRequest request) {
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		form.setSeq(RdirectionUtil.getBigDecimalString(formulaBean.getSeq())); // サブステップ
		form.setFormulaItemCd(formulaBean.getItemCd()); // 品目コード
		form.setFormulaItemName(formulaBean.getItemName()); // 品目名称

		// 指図量
		form.setQty(checker.format(RdirectionConst.UNIT_DIV_HAIGO, formulaBean
				.getQty()));
		// 投入速度
		form.setTonyuSokudo(checker.format(RdirectionConst.UNIT_DIV_RECIPE1,
			formulaBean.getTonyusokudo()));
		// 在庫引落量
		form.setStockpdQty(checker.format(RdirectionConst.UNIT_DIV_HAIGO,
			formulaBean.getStockpdQty()));
		// 実績数量
		form.setResultQty(checker.format(RdirectionConst.UNIT_DIV_HAIGO,
			formulaBean.getResultQty()));
		// ロス数量
		form.setLossQty(checker.format(RdirectionConst.UNIT_DIV_HAIGO,
			formulaBean.getLossQty()));
		// 調整数量
		form.setAdjustQty(checker.format(RdirectionConst.UNIT_DIV_HAIGO_ADJ,
			formulaBean.getAdjustQty()));
		form.setFormulaUnitName(formulaBean.getUnitName()); // 単位

		// 数値フォーマット情報の設定
		NumberChkDisitDetail checkDetail = new NumberChkDisitDetail();

		checkDetail = checker.getCheckDigit(RdirectionConst.UNIT_DIV_HAIGO,
			null, null);
		form.setDecimalPointHaigo(RdirectionUtil
				.getBigDecimalString(checkDetail.getSmallnumLength()));
		form.setRoundDivisionHaigo(RdirectionUtil
				.getBigDecimalString(checkDetail.getRoundDivision()));

		checkDetail = checker.getCheckDigit(RdirectionConst.UNIT_DIV_HAIGO_ADJ,
			null, null);
		form.setDecimalPointAdj(RdirectionUtil.getBigDecimalString(checkDetail
				.getSmallnumLength()));
		form.setRoundDivisionAdj(RdirectionUtil.getBigDecimalString(checkDetail
				.getRoundDivision()));

		checkDetail = checker.getCheckDigit(RdirectionConst.UNIT_DIV_RECIPE1,
			null, null);
		form.setTonyuSokudoDP(RdirectionUtil.getBigDecimalString(checkDetail
				.getSmallnumLength()));
		form.setTonyuSokudoRD(RdirectionUtil.getBigDecimalString(checkDetail
				.getRoundDivision()));

		form.setTonyu(RdirectionUtil
				.getBigDecimalString(formulaBean.getTonyu())); // 投入方法
		form.setDataRead(RdirectionUtil.getBigDecimalString(formulaBean
				.getDataread())); // データ読取
		form.setLotNo(formulaBean.getLotNo()); // 入荷ロット

		form.setStepCondition(formulaBean.getStepCondition()); // 投入条件
		form.setRemark(formulaBean.getRemark()); // 備考
		form.setNotes(formulaBean.getNotes()); // 注釈

		form.setStrLotDivision(formulaBean.getStrLotDivision()); // ロット管理区分
		form.setStrStockDivision(formulaBean.getStrStockDivision()); // 在庫管理区分

		return form;
	}

	/**
	 * 画面の内容をBeanに設定
	 * @param frm 画面FORM
	 * @param bean 製造指図－フォーミュラBean
	 */
	private void setDetailFormData(final RdirectionFormulaDetailForm frm,
			final RdirectionDirectionFormulaList bean) {

		bean.setItemCd(frm.getFormulaItemCd()); // 品目コード
		bean.setQty(AecNumberUtils.convertBigDecimal(frm.getQty())); // 指図量
		bean.setTonyu(AecNumberUtils.convertBigDecimal(frm.getTonyu())); // 投入方法
		bean.setDataread(AecNumberUtils.convertBigDecimal(frm.getDataRead())); // データ読取
		bean.setTonyusokudo(AecNumberUtils.convertBigDecimal(frm
				.getTonyuSokudo())); // 投入速度
		bean.setResultQty(AecNumberUtils.convertBigDecimal(frm.getResultQty())); // 実績数量
		bean.setLossQty(AecNumberUtils.convertBigDecimal(frm.getLossQty())); // ロス数量
		bean.setAdjustQty(AecNumberUtils.convertBigDecimal(frm.getAdjustQty())); // 調整数量

		// 在庫引落量を設定
		BigDecimal stockpdQty = AecNumberUtils.convertBigDecimal(frm
				.getResultQty());
		if (bean.getLossQty() != null) {
			stockpdQty = stockpdQty.add(bean.getLossQty());
		}
		if (bean.getAdjustQty() != null) {
			stockpdQty = stockpdQty.add(bean.getAdjustQty());
		}
		bean.setStockpdQty(stockpdQty);

		bean.setLotNo(frm.getLotNo()); // 入荷ロット
		bean.setStepCondition(frm.getStepCondition()); // 投入条件
		bean.setRemark(frm.getRemark()); // 備考
		bean.setNotes(frm.getNotes()); // 注釈
	}

	/**
	 * メッセージプロパティファイルから指定したkeyに対応する文字列を取得する。
	 * @param request HttpServletRequest
	 * @param key メッセージキー
	 * @return メッセージキーに対応するメッセージ文字列
	 */
	private String getMessageResource(final HttpServletRequest request,
			final String key) {
		MessageResources resource = getResources(request);
		return resource.getMessage(key);
	}

	/**
	 * エラーメッセージを追加する。
	 * @param request HttpServletRequest
	 * @param key メッセージキー
	 * @param objects 置換パラメータ
	 */
	private void addError(final HttpServletRequest request, final String key,
			final Object... objects) {
		ActionMessages messages = new ActionMessages();
		messages.add("", new ActionMessage(key, objects));
		addErrors(request, messages);
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
	 * 製造実績－フォーミュラ配合詳細検索 ロジッククラスを設定します。
	 * @param rdirectionFormulaDetailLogic 製造実績－フォーミュラ配合詳細検索 ロジッククラス
	 */
	public void setDirectionFormulaDetailLogic(
			final RdirectionFormulaDetailLogic rdirectionFormulaDetailLogic) {
		this.rdirectionFormulaDetailLogic = rdirectionFormulaDetailLogic;
	}
}
