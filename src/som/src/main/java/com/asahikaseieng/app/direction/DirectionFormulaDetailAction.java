/*
 * Created on 2009/03/10
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
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionFormulaList;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionHeaderList;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 製造指図－配合詳細 Actionクラス.
 * @author tosco
 */
public final class DirectionFormulaDetailAction extends AbstractAction {

	/** 製造指図-共通ロジッククラス */
	private DirectionCommonsLogic directionCommonsLogic;
	/** 製造指図－フォーミュラ配合詳細検索 ロジッククラス */
	private DirectionFormulaDetailLogic directionFormulaDetailLogic;

	/** 区分　RECIPE1 */
	public static final String UNIT_DIV_RECIPE1 = "RECIPE1";
	/** 単位区分 "HAIGO" */
	private static final String UNIT_DIVISION_HAIGO = "HAIGO";

	/**
	 * コンストラクタ.
	 */
	public DirectionFormulaDetailAction() {
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

		DirectionFormulaDetailForm detailForm = (DirectionFormulaDetailForm) form;
		AbstractDirectionForm commonForm = (AbstractDirectionForm) form;

		/* 権限取得 */
		getControlAuthority(request, detailForm, Constants.MENU_ID_DIRECTION,
			Constants.TAB_ID_DIRECTION_FORMULA);

		if (!detailForm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		saveKeyData(detailForm, commonForm);

		//共通情報検索処理
		DirectionDirectionHeaderList header =
			directionCommonsLogic.findByDirectionNo(detailForm.getDirectionNo());
		//更新時の為に、検索時の製造指図－ヘッダー情報を格納しておく
		commonForm.setHeaderBean(header);
		setCommonHeaderInfo(detailForm, header, request);

		//工程コード、工程名称を取得する
		String[] res = directionFormulaDetailLogic.getOperationName(detailForm.getDirectionNo()
			, new BigDecimal(detailForm.getStepNo()));
		detailForm.setOperationCd(res[0]);
		detailForm.setOperationName(res[1]);

		// 製造指図－フォーミュラ検索
		DirectionDirectionFormulaList bean
			= directionFormulaDetailLogic.getByPrimaryKey(detailForm.getDirectionNo()
									, new BigDecimal(detailForm.getStepNo())
									, new BigDecimal(detailForm.getLineNo()));
		setFormulaData(detailForm, bean, request);
		// Beanの内容をFormに保持
		detailForm.setDetailBean(bean);

		return mapping.findForward("success");
	}

	/**
	 * キー項目の保存
	 * @param detailForm
	 * @param commonForm
	 */
	private void saveKeyData(final DirectionFormulaDetailForm detailForm
			, final AbstractDirectionForm commonForm) {
		//クリアするので、渡された指図番号・STEP_NO、LINE_NOを退避
		String directionNo = commonForm.getDirectionNo();
		String stepNo = detailForm.getStepNo();
		String lineNo = detailForm.getLineNo();
		//クリア
		commonForm.clear();
		//クリアされた指図番号・STEP_NO、LINE_NOを移行
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

		DirectionFormulaDetailForm detailForm = (DirectionFormulaDetailForm) form;

		DirectionDirectionFormulaList bean = detailForm.getDetailBean();
		bean.setUpdatorCd(getLoginInfo(request).getTantoCd());	// 更新者ID
		// 画面内容をBeanへ設定
		setDetailFormData(detailForm, bean);

		// 品目マスタ存在チェック
		ActionMessages errors = directionFormulaDetailLogic.checkForUpdate(bean);
		if (!errors.isEmpty()) {
		//エラーがあった場合
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

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

		try {
			// 製造指図－フォーミュラ更新処理
			directionFormulaDetailLogic.update(bean, header);

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
	private void setCommonHeaderInfo(final DirectionFormulaDetailForm form
			, final DirectionDirectionHeaderList header, final HttpServletRequest request) {
		CheckDigitUtilsLogic checker = CheckDigitUtil.getCheckDigitUtils(request);
		form.setItemCd(header.getItemCd());	// 品目コード
		form.setItemName(header.getItemName());	// 品目名称
		// 仕込み予定数量
		form.setPlanedQty(checker.format(DirectionConst.UNIT_DIVISION_HAIGO, header.getPlanedQty()));
//		form.setPlanedQty(checker.format(header.getUnitOfOperationManagement(), header.getPlanedQty()));
		form.setUnitOfOperationManagement(header.getUnitOfOperationManagement());
		form.setUnitName(header.getUnitName());
		form.setDirectionStatus(DirectionUtil.getBigDecimalString(header.getDirectionStatus()));
	}

	/**
	 * 製造指図－フォーミュラ検索結果を画面のFormに設定する。
	 * @param form 画面のForm
	 * @param formulaBean 製造指図－フォーミュラ検索結果
	 * @return MgrecipeFormulaDetailForm
	 */
	private DirectionFormulaDetailForm setFormulaData(
			final DirectionFormulaDetailForm form, final DirectionDirectionFormulaList formulaBean,
			final HttpServletRequest request) {
		CheckDigitUtilsLogic checker = CheckDigitUtil.getCheckDigitUtils(request);

		form.setSeq(DirectionUtil.getBigDecimalString(formulaBean.getSeq()));	// サブステップ
		form.setFormulaItemCd(formulaBean.getItemCd());	// 品目コード
		form.setFormulaItemName(formulaBean.getItemName());	// 品目名称

		form.setQty(checker.format(UNIT_DIVISION_HAIGO, formulaBean.getQty()));	// 指図量
		form.setFormulaUnitName(formulaBean.getUnitName());	// 単位
		NumberChkDisitDetail checkDetail = new NumberChkDisitDetail();
		checkDetail = checker.getCheckDigit(UNIT_DIVISION_HAIGO, null, null);
		form.setQtyDecimalPoint(DirectionUtil.getBigDecimalString(checkDetail.getSmallnumLength()));
		form.setQtyRoundDivision(DirectionUtil.getBigDecimalString(checkDetail.getRoundDivision()));
//		form.setUnitDivision(formulaBean.getUnitDiv());	// 数値区分

		form.setTonyu(DirectionUtil.getBigDecimalString(formulaBean.getTonyu()));	// 投入方法
		form.setDataRead(DirectionUtil.getBigDecimalString(formulaBean.getDataread()));	// データ読取 
		form.setLotNo(formulaBean.getLotNo());	// 入荷ロット

		form.setTonyuSokudo(checker.format(UNIT_DIV_RECIPE1, formulaBean.getTonyusokudo()));	// 投入速度
		checkDetail = checker.getCheckDigit(UNIT_DIV_RECIPE1, null, null);
		form.setTonyuSokudoDP(DirectionUtil.getBigDecimalString(checkDetail.getSmallnumLength()));
		form.setTonyuSokudoRD(DirectionUtil.getBigDecimalString(checkDetail.getRoundDivision()));

		form.setStepCondition(formulaBean.getStepCondition());	// 投入条件
		form.setRemark(formulaBean.getRemark());	// 備考
		form.setNotes(formulaBean.getNotes());	// 注釈

		return form;
	}

	/**
	 * 画面の内容をBeanに設定
	 * @param frm 画面FORM
	 * @param bean 製造指図－フォーミュラBean
	 */
	private void setDetailFormData(final DirectionFormulaDetailForm frm, final DirectionDirectionFormulaList bean) {

		bean.setItemCd(frm.getFormulaItemCd());	// 品目コード
		bean.setQty(AecNumberUtils.convertBigDecimal(frm.getQty()));	// 指図量
		bean.setTonyu(AecNumberUtils.convertBigDecimal(frm.getTonyu()));	// 投入方法 
		bean.setDataread(AecNumberUtils.convertBigDecimal(frm.getDataRead()));	// データ読込
		bean.setTonyusokudo(AecNumberUtils.convertBigDecimal(frm.getTonyuSokudo()));	// 投入速度
		bean.setLotNo(frm.getLotNo());		// 入荷ロット
		bean.setStepCondition(frm.getStepCondition());	// 投入条件
		bean.setRemark(frm.getRemark());	// 備考
		bean.setNotes(frm.getNotes());		// 注釈
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
	 * 製造指図－フォーミュラ配合詳細検索 ロジッククラスを設定します。
	 * @param directionFormulaDetailLogic 製造指図－フォーミュラ配合詳細検索 ロジッククラス
	 */
	public void setDirectionFormulaDetailLogic(
			final DirectionFormulaDetailLogic directionFormulaDetailLogic) {
		this.directionFormulaDetailLogic = directionFormulaDetailLogic;
	}
}
