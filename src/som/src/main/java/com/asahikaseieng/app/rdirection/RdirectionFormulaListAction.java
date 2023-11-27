/*
 * Created on 2009/02/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.rdirection;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionFormulaList;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionHeaderList;
import com.asahikaseieng.exception.LogicExceptionEx;

/**
 * 製造実績－配合画面
 * @author tosco
 */
public class RdirectionFormulaListAction extends AbstractRdirectionAction {

	/** 製造実績－フォーミュラ検索 ロジッククラス */
	private RdirectionFormulaListLogic rdirectionFormulaListLogic;

	/**
	 * コンストラクタ
	 */
	public RdirectionFormulaListAction() {
	}

	/**
	 * タブID（RdirectionConstクラスで定義)を各子クラスで実装
	 * @return タブID
	 */
	@Override
	protected String getTabId() {
		return RdirectionConst.FORMULALIST;
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

		RdirectionFormulaListForm frm = (RdirectionFormulaListForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_RDIRECTION,
			Constants.TAB_ID_RDIRECTION_FORMULA);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 工程順序コンボボックス設定
		frm.setSeqCombo(rdirectionCommonsLogic.createProcedureSetpNoCombobox(
			frm.getDirectionNo(), true));

		// 2009/11/11 初期表示で「０」検索を行う
		frm.setProcStepNo("0");
		this.search(mapping, form, request, response);

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

		RdirectionFormulaListForm listForm = (RdirectionFormulaListForm) form;
		listForm.setDirtyFlg(null);
		// 工程順序を保存する
		listForm.setTempProcStepNo(listForm.getProcStepNo());

		// 指図量計のデフォルト設定
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);
		// フォーマット情報設定
		setCheckDetail(listForm, checker);

		// 検索処理
		List<RdirectionDirectionFormulaList> searchList = rdirectionFormulaListLogic
				.getSearchList(listForm.getDirectionNo(), new BigDecimal(
						listForm.getProcStepNo()));
		listForm.setSearchFormList(searchList);
		listForm
				.setDelFormList(new ArrayList<RdirectionDirectionFormulaList>());

		// 数量のフォーマット
		for (RdirectionDirectionFormulaList bean : searchList) {
			bean.setStrQty(checker.format(RdirectionConst.UNIT_DIV_HAIGO, bean
					.getQty()));
			bean.setStrResultQty(checker.format(RdirectionConst.UNIT_DIV_HAIGO,
				bean.getResultQty()));
			bean.setStrLossQty(checker.format(RdirectionConst.UNIT_DIV_HAIGO,
				bean.getLossQty()));
			bean.setStrAdjustQty(checker.format(
				RdirectionConst.UNIT_DIV_HAIGO_ADJ, bean.getAdjustQty()));

			// 実績数量の変更前のﾃﾞｰﾀを保持
			bean.setStrBeforeResultQty(bean.getStrResultQty());
		}

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

		RdirectionFormulaListForm frm = (RdirectionFormulaListForm) form;
		ActionMessages errors = rdirectionFormulaListLogic
				.checkForAddDelList(frm);

		// エラーがあった場合
		if (!errors.isEmpty()) {
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		RdirectionDirectionFormulaList bean = new RdirectionDirectionFormulaList();
		bean.setDirectionDivision(BigDecimal.ONE);
		bean.setDirectionNo(frm.getDirectionNo());
		bean.setProcSeqCombo(rdirectionCommonsLogic
				.createProcedureSetpNoCombobox(frm.getDirectionNo(), false));
		bean.setStockpdQty(null);

		// 要素がない場合
		if (frm.getSearchFormList().size() == 0) {
			frm.getSearchFormList().add(bean);
		} else {
			// リスト追加ループ
			for (int i = 0; i < frm.getSearchFormList().size(); i++) {
				RdirectionDirectionFormulaList directionBean = frm
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
		for (RdirectionDirectionFormulaList directionBean : frm
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

		RdirectionFormulaListForm frm = (RdirectionFormulaListForm) form;
		ActionMessages errors = rdirectionFormulaListLogic
				.checkForAddDelList(frm);

		// エラーがあった場合
		if (!errors.isEmpty()) {
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		List<RdirectionDirectionFormulaList> delList = frm.getDelFormList();
		// 削除処理
		for (int i = frm.getSearchFormList().size() - 1; i >= 0; i--) {
			RdirectionDirectionFormulaList directionBean = frm
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

		RdirectionFormulaListForm frm = (RdirectionFormulaListForm) form;
		List<RdirectionDirectionFormulaList> searchFormList = frm
				.getSearchFormList();
		ActionMessages errors = new ActionMessages();

		// 製造ヘッダの実績日時がNullの場合エラー
		ActionMessages headerErrors = rdirectionFormulaListLogic
				.checkForResultDate(frm.getDirectionDivision(), frm
						.getDirectionNo());
		if (!headerErrors.isEmpty()) {
			// エラーがあった場合
			super.saveErrors(request, headerErrors);
			return mapping.findForward("error");
		}

		// 2011/02/01 登録時にﾛｯﾄ、ﾛｹが決定している場合、製造配合とロット在庫のロケが同一かチェックを追加
		boolean check = false;
		int i = 1;
		ActionMessages messages = new ActionMessages();

		// 配合リストを全てチェック
		for (RdirectionDirectionFormulaList bean : searchFormList) {

			// 画面の変更前の実績値と異なる場合ロケチェックを行う
			if (bean.getStrBeforeResultQty() != null
					&& !bean.getStrBeforeResultQty().equals(
						bean.getStrResultQty())) {

				// 製造配合とロット在庫のロケが不一致の場合エラーを画面に表示
				if (!rdirectionFormulaListLogic.checkForFormulaResultDate(bean
						.getDirectionNo(), bean.getStepNo(), bean.getLineNo())) {

					// エラーがあった場合
					messages.add("", new ActionMessage(
							"errors.rdirection.location.disagreement.row", i));
					check = true;
				}
			}
			i++;
		}

		// 削除を行った場合、製造配合とロット在庫のロケが同一かチェックを追加
		// 削除リストを全てチェック
		for (RdirectionDirectionFormulaList bean : frm.getDelFormList()) {

			// 製造配合とロット在庫のロケが不一致の場合エラーを画面に表示
			if (!rdirectionFormulaListLogic.checkForFormulaResultDate(bean
					.getDirectionNo(), bean.getStepNo(), bean.getLineNo())) {

				// エラーがあった場合
				messages.add("", new ActionMessage(
						"errors.rdirection.delete.location.disagreement.item",
						bean.getItemCd()));
				check = true;
			}
		}

		// 製造配合とロット在庫のロケが同一でないデータがあった場合エラーを表示し処理終了
		if (check) {
			addErrors(request, messages);
			return mapping.findForward("error");
		}

		// 品目マスタ存在チェック
		errors = rdirectionFormulaListLogic.checkForRegist(searchFormList);
		if (!errors.isEmpty()) {
			// エラーがあった場合
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		// 更新用データ作成
		RdirectionDirectionHeaderList header = RdirectionUtil
				.setDirectionHeader(frm, request);

		try {
			// 製造指図プロシージャ更新処理
			rdirectionFormulaListLogic.regist(frm, header,
				getLoginInfo(request).getTantoCd());
		} catch (LogicExceptionEx e) {
			if ("errors.rdirection.stock.update".equals(e.getMessage())) {
				// 在庫更新に失敗
				saveError(request, e.getMessage());
				return mapping.getInputForward();
			} else {
				throw e;
			}
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
		AbstractRdirectionForm commonForm = (AbstractRdirectionForm) form;
		String directionNo = commonForm.getDirectionNo();

		// 固有部分を取得
		RdirectionFormulaListForm listForm = (RdirectionFormulaListForm) form;
		String strProcStepNo = listForm.getTempProcStepNo();

		// 共通部分を再設定
		commonForm.clear();
		commonForm.setRecipeId(directionNo); // レシピインデック
		commonForm.setTabId(getTabId()); // タブID

		// 共通情報再検索処理
		RdirectionDirectionHeaderList header = rdirectionCommonsLogic
				.findByDirectionNo(directionNo);
		setCommonHeaderInfo(commonForm, header, request);

		// 固有部分再設定
		listForm
				.setSearchFormList(new ArrayList<RdirectionDirectionFormulaList>());
		listForm
				.setDelFormList(new ArrayList<RdirectionDirectionFormulaList>());
		listForm.setDirtyFlg(null);
		// 工程順序を保存する
		listForm.setProcStepNo(strProcStepNo);
		listForm.setTempProcStepNo(strProcStepNo);

		// 工程順序コンボボックスの作成
		listForm
				.setSeqCombo(rdirectionCommonsLogic
						.createProcedureSetpNoCombobox(listForm
								.getDirectionNo(), true));

		return search(mapping, form, request, response);
	}

	/**
	 * フォーマット情報設定
	 * @param listForm
	 * @param checker
	 */
	private void setCheckDetail(final RdirectionFormulaListForm listForm,
			final CheckDigitUtilsLogic checker) {
		// フォーマット用データ取得
		NumberChkDisitDetail checkDetail = new NumberChkDisitDetail();
		/* HAIGO */
		checkDetail = checker.getCheckDigit(RdirectionConst.UNIT_DIV_HAIGO,
			null, null);
		// 少数点桁数
		listForm.setDecimalPointHaigo(getBigDecimalString(checkDetail
				.getSmallnumLength()));
		// 端数区分
		listForm.setRoundDivisionHaigo(getBigDecimalString(checkDetail
				.getRoundDivision()));
		/* HAIGO_ADJ */
		checkDetail = checker.getCheckDigit(RdirectionConst.UNIT_DIV_HAIGO_ADJ,
			null, null);
		// 少数点桁数
		listForm.setDecimalPointAdj(getBigDecimalString(checkDetail
				.getSmallnumLength()));
		// 端数区分
		listForm.setRoundDivisionAdj(getBigDecimalString(checkDetail
				.getRoundDivision()));
	}

	/* -------------------- setter -------------------- */

	/**
	 * 製造実績－配合タグ フォーミュラを設定します。
	 * @param rdirectionFormulaListLogic フォーミュラ
	 */
	public void setRdirectionFormulaListLogic(
			final RdirectionFormulaListLogic rdirectionFormulaListLogic) {
		this.rdirectionFormulaListLogic = rdirectionFormulaListLogic;
	}

}
