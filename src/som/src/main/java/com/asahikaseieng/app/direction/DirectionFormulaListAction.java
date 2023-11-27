/*
 * Created on 2009/02/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.direction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionFormulaList;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionHeaderList;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.exception.LogicExceptionEx;

/**
 * 製造指図－配合画面
 * @author tosco
 */
public class DirectionFormulaListAction extends AbstractDirectionAction {

	/** 単位区分 "HAIGO" */
	private static final String UNIT_DIVISION_HAIGO = "HAIGO";

	/** 製造指図－フォーミュラ検索 ロジッククラス */
	private DirectionFormulaListLogic directionFormulaListLogic;

	/**
	 * コンストラクタ
	 */
	public DirectionFormulaListAction() {
	}

	/**
	 * タブID（DirectionConstクラスで定義)を各子クラスで実装
	 * @return タブID
	 */
	@Override
	protected String getTabId() {
		return DirectionConst.FORMULALIST;
	}

	/**
	 * 各子クラスの画面初期表示処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	@Override
	protected ActionForward initProcess(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		DirectionFormulaListForm frm = (DirectionFormulaListForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_DIRECTION,
			Constants.TAB_ID_DIRECTION_FORMULA);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 工程順序コンボボックス設定
		frm.setSeqCombo(directionCommonsLogic.createProcedureSetpNoCombobox(frm
				.getDirectionNo(), true));

		// 指図量計の取得
		DirectionDirectionFormulaList directionFormula = directionFormulaListLogic
				.getSumQty(frm.getDirectionNo(), new BigDecimal(0));

		// 指図量計の設定
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);
		frm.setTotalQty(checker.format(UNIT_DIVISION_HAIGO, directionFormula
				.getSumQty()));

		return mapping.findForward("success");
	}

	/**
	 * 検索処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward search(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("search.");
		}

		DirectionFormulaListForm listForm = (DirectionFormulaListForm) form;
		listForm.setDirtyFlg(null);
		// 工程順序を保存する
		listForm.setTempProcStepNo(listForm.getProcStepNo());

		// 指図量計のデフォルト設定
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);
		listForm.setTotalQty(checker.format(UNIT_DIVISION_HAIGO,
			BigDecimal.ZERO));

		// 検索処理
		List<DirectionDirectionFormulaList> searchList = directionFormulaListLogic
				.getSearchList(listForm.getDirectionNo(), new BigDecimal(
						listForm.getProcStepNo()));
		listForm.setSearchFormList(searchList);

		// 指図量のフォーマット
		for (DirectionDirectionFormulaList bean : searchList) {
			bean.setStrQty(checker.format(UNIT_DIVISION_HAIGO, bean.getQty()));
		}

		// フォーマット情報設定
		setCheckDetail(listForm, checker);

		return mapping.findForward("success");
	}

	/**
	 * 行追加処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward addlist(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("addlist.");
		}

		DirectionFormulaListForm frm = (DirectionFormulaListForm) form;
		ActionMessages errors = directionFormulaListLogic
				.checkForAddDelList(frm);

		// エラーがあった場合
		if (!errors.isEmpty()) {
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		DirectionDirectionFormulaList bean = new DirectionDirectionFormulaList();
		bean.setDirectionDivision(BigDecimal.ONE);
		bean.setDirectionNo(frm.getDirectionNo());
		bean.setProcSeqCombo(directionCommonsLogic
				.createProcedureSetpNoCombobox(frm.getDirectionNo(), false));

		// 要素がない場合
		if (frm.getSearchFormList().size() == 0) {
			frm.getSearchFormList().add(bean);
		} else {
			// リスト追加ループ
			for (int i = 0; i < frm.getSearchFormList().size(); i++) {
				DirectionDirectionFormulaList directionBean = frm
						.getSearchFormList().get(i);
				// チェックボックスがチェックされていた場合
				if (directionBean.isCheckFlg()) {
					// 新しい要素を追加
					frm.getSearchFormList().add(i, bean);
					break;
				}

				// チェックがない場合最後尾に追加
				if (i == frm.getSearchFormList().size() - 1) {
					// 新しい要素を追加
					frm.getSearchFormList().add(bean);
					break;
				}
			}
		}

		// チェックボックスクリア
		for (DirectionDirectionFormulaList directionBean : frm
				.getSearchFormList()) {
			directionBean.setCheckFlg(Boolean.FALSE);
		}

		return mapping.findForward("success");
	}

	/**
	 * 行削除処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward dellist(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("dellist.");
		}

		DirectionFormulaListForm frm = (DirectionFormulaListForm) form;
		ActionMessages errors = directionFormulaListLogic
				.checkForAddDelList(frm);

		// エラーがあった場合
		if (!errors.isEmpty()) {
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		List<DirectionDirectionFormulaList> delList = frm.getDelFormList();
		// 削除処理
		for (int i = frm.getSearchFormList().size() - 1; i >= 0; i--) {
			DirectionDirectionFormulaList directionBean = frm
					.getSearchFormList().get(i);

			if (!directionBean.isCheckFlg()) {
				// チェック無は読み飛ばし
				continue;
			}
			// 行削除
			frm.getSearchFormList().remove(i);
			// 行削除データ追加
			delList.add(directionBean);
		}

		// 行削除データ設定
		frm.setDelFormList(delList);

		return mapping.findForward("success");
	}

	/**
	 * 登録処理処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward regist(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("regist");
		}

		DirectionFormulaListForm frm = (DirectionFormulaListForm) form;
		List<DirectionDirectionFormulaList> searchFormList = frm
				.getSearchFormList();
		ActionMessages errors = new ActionMessages();

		// 品目マスタ存在チェック
		errors = directionFormulaListLogic.checkForRegist(searchFormList);
		if (!errors.isEmpty()) {
			// エラーがあった場合
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		// 更新用データ作成
		DirectionDirectionHeaderList header = setDirectionHeader(frm, request);
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
			// 処方プロシージャ更新処理
			directionFormulaListLogic.regist(frm, header, getLoginInfo(request)
					.getTantoCd());

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
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("reSearch");
	}

	/**
	 * 再検索処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward reSearch(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("reSearch");
		}

		// 共通部分を取得
		AbstractDirectionForm commonForm = (AbstractDirectionForm) form;
		String directionNo = commonForm.getDirectionNo();

		// 固有部分を取得
		DirectionFormulaListForm listForm = (DirectionFormulaListForm) form;
		String strProcStepNo = listForm.getTempProcStepNo();

		// 共通部分を再設定
		commonForm.clear();
		commonForm.setRecipeId(directionNo); // レシピインデック
		commonForm.setTabId(getTabId()); // タブID

		// 共通情報再検索処理
		DirectionDirectionHeaderList header = directionCommonsLogic
				.findByDirectionNo(directionNo);
		setCommonHeaderInfo(commonForm, header, request);

		// 固有部分再設定
		listForm
				.setSearchFormList(new ArrayList<DirectionDirectionFormulaList>());
		listForm.setDelFormList(new ArrayList<DirectionDirectionFormulaList>());
		listForm.setDirtyFlg(null);
		// 工程順序を保存する
		listForm.setProcStepNo(strProcStepNo);
		listForm.setTempProcStepNo(strProcStepNo);

		// 工程順序コンボボックスの作成
		listForm
				.setSeqCombo(directionCommonsLogic
						.createProcedureSetpNoCombobox(listForm
								.getDirectionNo(), true));

		return search(mapping, form, request, response);
	}

	/**
	 * フォーマット情報設定
	 * @param listForm
	 * @param checker
	 */
	private void setCheckDetail(final DirectionFormulaListForm listForm,
			final CheckDigitUtilsLogic checker) {
		// フォーマット用データ取得
		NumberChkDisitDetail checkDetail = new NumberChkDisitDetail();
		checkDetail = checker.getCheckDigit(UNIT_DIVISION_HAIGO, null, null);
		// 少数点桁数
		listForm.setDecimalPoint(getBigDecimalString(checkDetail
				.getSmallnumLength()));
		// 端数区分
		listForm.setRoundDivision(getBigDecimalString(checkDetail
				.getRoundDivision()));
	}

	/* -------------------- setter -------------------- */

	/**
	 * 製造指図－フォーミュラを設定します。
	 * @param directionFormulaListLogic 製造指図－フォーミュラ
	 */
	public void setDirectionFormulaListLogic(
			final DirectionFormulaListLogic directionFormulaListLogic) {
		this.directionFormulaListLogic = directionFormulaListLogic;
	}

}
