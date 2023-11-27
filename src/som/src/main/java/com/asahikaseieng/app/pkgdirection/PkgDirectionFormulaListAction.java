/*
 * Created on 2009/02/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgdirection;

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
import com.asahikaseieng.app.mgrecipe.MgrecipeUtil;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionFormulaList;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionHeaderDetail;

/**
 * 包装指図－配合一覧画面 Actionクラス
 * @author tosco
 */
public class PkgDirectionFormulaListAction extends AbstractPkgDirectionAction {

	/** 包装指図－配合一覧画面 ロジッククラス */
	private PkgDirectionFormulaListLogic pkgDirectionFormulaListLogic;

	/**
	 * コンストラクタ
	 */
	public PkgDirectionFormulaListAction() {
	}

	/**
	 * タブID（PkgDirectionConstクラスで定義)を各子クラスで実装
	 * @return タブID
	 */
	@Override
	protected String getTabId() {
		return PkgDirectionConst.FORMULALIST;
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
		PkgDirectionFormulaListForm frm = (PkgDirectionFormulaListForm) form;
		frm.setDirtyFlg(null);

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_PKGDIRECTION,
			Constants.TAB_ID_PKGDIRECTION_FORMULA);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		BigDecimal directionDivision = new BigDecimal(frm
				.getDirectionDivision());
		String directionNo = frm.getDirectionNo();

		// 工程順序コンボ作成
		frm.setSeqCombo(pkgDirectionCommonsLogic.createProcedureSetpNoCombobox(
			directionDivision, directionNo, true));

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

		PkgDirectionFormulaListForm frm = (PkgDirectionFormulaListForm) form;
		frm.setDirtyFlg(null);

		// 工程順序を保存する
		frm.setTempProcStepNo(frm.getProcStepNo());

		// 検索処理
		List<PkgDirectionDirectionFormulaList> searchList = pkgDirectionFormulaListLogic
				.getSearchList(frm);

		// 予定数量のフォーマット
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);
		NumberChkDisitDetail checkDetail = new NumberChkDisitDetail();
		for (PkgDirectionDirectionFormulaList bean : searchList) {

			// 用途を設定
			bean.setRcpUse(frm.getRecipeUse());

			// 予定数量を設定
			bean.setStrQty(checker.format(
				PkgDirectionConst.UNIT_DIVISION_HAIGO, bean.getQty()));

		}
		frm.setSearchFormList(searchList);

		checkDetail = checker.getCheckDigit(
			PkgDirectionConst.UNIT_DIVISION_HAIGO, null, null);

		// 少数点桁数
		frm.setDecimalPoint(checkDetail.getSmallnumLength().toString());
		// 端数区分
		frm.setRoundDivision(checkDetail.getRoundDivision().toString());

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
		PkgDirectionFormulaListForm frm = (PkgDirectionFormulaListForm) form;
		ActionMessages errors = pkgDirectionFormulaListLogic
				.checkForAddDelList(frm);

		// エラーがあった場合
		if (!errors.isEmpty()) {
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}
		BigDecimal directionDivision = new BigDecimal(frm
				.getDirectionDivision());
		String directionNo = frm.getDirectionNo();

		PkgDirectionDirectionFormulaList addBean = new PkgDirectionDirectionFormulaList();
		addBean.setDirectionDivision(directionDivision);
		addBean.setDirectionNo(directionNo);
		addBean.setRcpUse(frm.getRecipeUse()); // 用途
		addBean.setProcSeqCombo(pkgDirectionCommonsLogic
				.createProcedureSetpNoCombobox(directionDivision, directionNo,
					false));

		// 要素がない場合
		if (frm.getSearchFormList().size() == 0) {
			frm.getSearchFormList().add(addBean);
		} else {
			// リスト追加ループ
			for (int i = 0; i < frm.getSearchFormList().size(); i++) {
				PkgDirectionDirectionFormulaList bean = frm.getSearchFormList()
						.get(i);
				// チェックボックスがチェックされていた場合
				if (bean.isCheckFlg()) {
					// 新しい要素を追加
					frm.getSearchFormList().add(i, addBean);
					bean.setCheckFlg(Boolean.FALSE);
					break;
				}

				// チェックがない場合最後尾に追加
				if (i == frm.getSearchFormList().size() - 1) {
					// 新しい要素を追加
					frm.getSearchFormList().add(addBean);
					break;
				}
			}
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
		PkgDirectionFormulaListForm frm = (PkgDirectionFormulaListForm) form;
		ActionMessages errors = pkgDirectionFormulaListLogic
				.checkForAddDelList(frm);

		// エラーがあった場合
		if (!errors.isEmpty()) {
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		List<PkgDirectionDirectionFormulaList> delList = frm.getDelFormList();
		// 削除処理
		for (int i = frm.getSearchFormList().size() - 1; i >= 0; i--) {
			PkgDirectionDirectionFormulaList bean = frm.getSearchFormList()
					.get(i);

			if (!bean.isCheckFlg()) {
				// チェック無は読み飛ばし
				continue;
			}
			// 行削除
			frm.getSearchFormList().remove(i);
			// 行削除データ追加
			delList.add(bean);
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
		PkgDirectionFormulaListForm frm = (PkgDirectionFormulaListForm) form;
		List<PkgDirectionDirectionFormulaList> searchFormList = frm
				.getSearchFormList();
		ActionMessages errors = new ActionMessages();

		// 一覧存在チェック
		if (searchFormList == null) {
			errors = MgrecipeUtil.addError(errors,
				"errors.pkgdirection.no.search");
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}
		// 更新の必要なし
		if (searchFormList.isEmpty()
				&& (frm.getDelFormList() == null || frm.getDelFormList()
						.isEmpty())) {
			errors = MgrecipeUtil.addError(errors, "errors.nodata");
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}
		// 品目マスタ存在チェック
		errors = pkgDirectionFormulaListLogic.checkForRegist(frm);
		if (!errors.isEmpty()) {
			// エラーがあった場合
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		try {
			// 包装指図－フォーミュラ更新処理
			pkgDirectionFormulaListLogic.regist(frm, getLoginInfo(request)
					.getTantoCd());

			// 包装計画削除
			pkgDirectionCommonsLogic.deleteHosoKeikaku(frm.getDirectionNo());

		} catch (PkgDirectionLogicException e) {
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
		AbstractPkgDirectionForm commonForm = (AbstractPkgDirectionForm) form;

		// 指図区分、包装指図番号を退避
		String directionDivision = commonForm.getDirectionDivision();
		String directionNo = commonForm.getDirectionNo();

		// 固有部分を取得
		PkgDirectionFormulaListForm listForm = (PkgDirectionFormulaListForm) form;
		String strProcStepNo = listForm.getProcStepNo();

		// 共通部分を再設定
		commonForm.clear();
		commonForm.setDirectionDivision(directionDivision);
		commonForm.setDirectionNo(directionNo);
		commonForm.setTabId(getTabId()); // タブID

		// 共通情報再検索処理
		PkgDirectionDirectionHeaderDetail header = null;
		header = pkgDirectionCommonsLogic.getEntity(directionDivision,
			directionNo);
		setCommonHeaderInfo(commonForm, header, request);

		// 固有部分再設定
		listForm
				.setSearchFormList(new ArrayList<PkgDirectionDirectionFormulaList>());
		listForm
				.setDelFormList(new ArrayList<PkgDirectionDirectionFormulaList>());
		listForm.setDirtyFlg(null);

		// 工程順序を保存する
		listForm.setProcStepNo(strProcStepNo);
		listForm.setTempProcStepNo(strProcStepNo);

		// 工程順序コンボ作成
		listForm.setSeqCombo(pkgDirectionCommonsLogic
				.createProcedureSetpNoCombobox(
					new BigDecimal(directionDivision), directionNo, true));

		return search(mapping, form, request, response);
	}

	/* -------------------- setter -------------------- */

	/**
	 * 包装指図－配合一覧画面 ロジッククラス設定
	 * @param pkgDirectionFormulaListLogic 包装指図－配合一覧画面 ロジッククラス
	 */
	public void setPkgDirectionFormulaListLogic(
			final PkgDirectionFormulaListLogic pkgDirectionFormulaListLogic) {
		this.pkgDirectionFormulaListLogic = pkgDirectionFormulaListLogic;
	}

}
