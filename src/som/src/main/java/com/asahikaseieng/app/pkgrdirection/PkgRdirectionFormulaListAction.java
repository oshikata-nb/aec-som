/*
 * Created on 2009/03/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgrdirection;

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
import com.asahikaseieng.app.mgrecipe.MgrecipeUtil;
import com.asahikaseieng.app.pkgdirection.PkgDirectionConst;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionFormulaList;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionHeaderDetail;
import com.asahikaseieng.exception.LogicExceptionEx;

/**
 * 包装実績－配合一覧画面 Actionクラス
 * @author tosco
 */
public class PkgRdirectionFormulaListAction extends AbstractPkgRdirectionAction {

	/** 包装実績－配合一覧画面 ロジッククラス */
	private PkgRdirectionFormulaListLogic pkgRdirectionFormulaListLogic;

	/**
	 * コンストラクタ
	 */
	public PkgRdirectionFormulaListAction() {
	}

	/**
	 * タブID（PkgRdirectionConstクラスで定義)を各子クラスで実装
	 * @return タブID
	 */
	@Override
	protected String getTabId() {
		return PkgRdirectionConst.FORMULALIST;
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
		PkgRdirectionFormulaListForm frm = (PkgRdirectionFormulaListForm) form;
		frm.setDirtyFlg(null);

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_PKGRDIRECTION,
			Constants.TAB_ID_PKGRDIRECTION_FORMULA);

		BigDecimal directionDivision = new BigDecimal(frm
				.getDirectionDivision());
		String directionNo = frm.getDirectionNo();

		// 工程順序コンボ作成
		frm.setSeqCombo(pkgRdirectionCommonsLogic
				.createProcedureSetpNoCombobox(directionDivision, directionNo,
					true));

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

		PkgRdirectionFormulaListForm frm = (PkgRdirectionFormulaListForm) form;
		frm.setDirtyFlg(null);

		// 工程順序を保存する
		frm.setTempProcStepNo(frm.getProcStepNo());

		// 検索処理
		List<PkgRdirectionDirectionFormulaList> searchList = pkgRdirectionFormulaListLogic
				.getSearchList(frm);
		frm.setSearchFormList(searchList);
		frm.setDelFormList(new ArrayList<PkgRdirectionDirectionFormulaList>());
		// 配合一覧編集
		pkgRdirectionFormulaListLogic.editList(request, frm);

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
		PkgRdirectionFormulaListForm frm = (PkgRdirectionFormulaListForm) form;
		ActionMessages errors = pkgRdirectionFormulaListLogic
				.checkForAddDelList(frm);

		// エラーがあった場合
		if (!errors.isEmpty()) {
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);
		BigDecimal directionDivision = new BigDecimal(frm
				.getDirectionDivision());
		String directionNo = frm.getDirectionNo();

		PkgRdirectionDirectionFormulaList addBean = new PkgRdirectionDirectionFormulaList();
		addBean.setDirectionDivision(directionDivision);
		addBean.setDirectionNo(directionNo);
		addBean.setStrQty(checker.format(PkgDirectionConst.UNIT_DIVISION_HAIGO,
			BigDecimal.ZERO));
		addBean.setStrResultQty(checker.format(
			PkgDirectionConst.UNIT_DIVISION_HAIGO, BigDecimal.ZERO));
		addBean.setRcpUse(frm.getRecipeUse()); // 用途
		addBean.setProcSeqCombo(pkgRdirectionCommonsLogic
				.createProcedureSetpNoCombobox(directionDivision, directionNo,
					false));

		// 要素がない場合
		if (frm.getSearchFormList().size() == 0) {
			frm.getSearchFormList().add(addBean);
		} else {
			// リスト追加ループ
			for (int i = 0; i < frm.getSearchFormList().size(); i++) {
				PkgRdirectionDirectionFormulaList bean = frm
						.getSearchFormList().get(i);
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
		PkgRdirectionFormulaListForm frm = (PkgRdirectionFormulaListForm) form;
		ActionMessages errors = pkgRdirectionFormulaListLogic
				.checkForAddDelList(frm);

		// エラーがあった場合
		if (!errors.isEmpty()) {
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		List<PkgRdirectionDirectionFormulaList> delList = frm.getDelFormList();
		// 削除処理
		for (int i = frm.getSearchFormList().size() - 1; i >= 0; i--) {
			PkgRdirectionDirectionFormulaList bean = frm.getSearchFormList()
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
	 * 投入実績取消 .
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward reset(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("reset");
		}
		PkgRdirectionFormulaListForm frm = (PkgRdirectionFormulaListForm) form;
		String tantoCd = getLoginInfo(request).getTantoCd();
		try {
			// 2011/02/01 登録時にﾛｯﾄ、ﾛｹが決定している場合、製造配合とロット在庫のロケが同一かチェックを追加
			ActionMessages messages = new ActionMessages();

			// 在庫引落リセット対象を取得
			PkgRdirectionDirectionFormulaList bean = frm.getSearchFormList()
					.get(Integer.parseInt(frm.getLine()));

			// 製造配合とロット在庫のロケが不一致の場合エラーを画面に表示
			if (!pkgRdirectionFormulaListLogic.checkForFormulaResultDate(bean
					.getDirectionNo(), bean.getStepNo(), bean.getLineNo(), bean
					.getDirectionDivision())) {

				// エラーがあった場合
				messages.add("", new ActionMessage(
						"errors.rdirection.location.disagreement.row",
						new BigDecimal(frm.getLine()).add(BigDecimal.ONE)));
				addErrors(request, messages);
				return mapping.findForward("error");
			}

			pkgRdirectionFormulaListLogic.reset(frm, tantoCd);
		} catch (LogicExceptionEx e) {
			if ("errors.pkgrdirection.stock.update".equals(e.getMessage())) {
				// 在庫更新に失敗
				saveError(request, e.getMessage());
				return mapping.getInputForward();
			} else {
				throw e;
			}
		}
		return mapping.findForward("reSearch");
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
		PkgRdirectionFormulaListForm frm = (PkgRdirectionFormulaListForm) form;
		List<PkgRdirectionDirectionFormulaList> searchFormList = frm
				.getSearchFormList();
		ActionMessages errors = new ActionMessages();

		// 製造ヘッダの実績日時がNullの場合エラー
		ActionMessages headerErrors = pkgRdirectionFormulaListLogic
				.checkForResultDate(frm.getDirectionDivision(), frm
						.getDirectionNo());
		if (!headerErrors.isEmpty()) {
			// エラーがあった場合
			super.saveErrors(request, headerErrors);
			return mapping.findForward("error");
		}

		// 2011/02/01 登録時にﾛｯﾄ、ﾛｹが決定している場合、製造配合とロット在庫のロケが同一かチェックを追加
		boolean check = false;
		ActionMessages messages = new ActionMessages();

		// 削除を行った場合、製造配合とロット在庫のロケが同一かチェックを追加
		// 削除リストを全てチェック
		for (PkgRdirectionDirectionFormulaList bean : frm.getDelFormList()) {

			// 製造配合とロット在庫のロケが不一致の場合エラーを画面に表示
			if (!pkgRdirectionFormulaListLogic.checkForFormulaResultDate(bean
					.getDirectionNo(), bean.getStepNo(), bean.getLineNo(), bean
					.getDirectionDivision())) {

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
		errors = pkgRdirectionFormulaListLogic.checkForRegist(frm);
		if (!errors.isEmpty()) {
			// エラーがあった場合
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		try {
			// 製造指図プロシージャ更新処理
			pkgRdirectionFormulaListLogic.regist(frm, getLoginInfo(request)
					.getTantoCd());
		} catch (LogicExceptionEx e) {
			if ("errors.pkgrdirection.stock.update".equals(e.getMessage())) {
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
		AbstractPkgRdirectionForm commonForm = (AbstractPkgRdirectionForm) form;

		// 指図区分、包装指図番号を退避
		String directionDivision = commonForm.getDirectionDivision();
		String directionNo = commonForm.getDirectionNo();

		// 固有部分を取得
		PkgRdirectionFormulaListForm listForm = (PkgRdirectionFormulaListForm) form;
		String strProcStepNo = listForm.getProcStepNo();

		// 共通部分を再設定
		commonForm.clear();
		commonForm.setDirectionDivision(directionDivision);
		commonForm.setDirectionNo(directionNo);
		commonForm.setTabId(getTabId()); // タブID

		// 共通情報再検索処理
		PkgRdirectionDirectionHeaderDetail header = null;
		header = pkgRdirectionCommonsLogic.getEntity(directionDivision,
			directionNo);
		setCommonHeaderInfo(commonForm, header, request);

		// 固有部分再設定
		listForm
				.setSearchFormList(new ArrayList<PkgRdirectionDirectionFormulaList>());
		listForm
				.setDelFormList(new ArrayList<PkgRdirectionDirectionFormulaList>());
		listForm.setDirtyFlg(null);

		// 工程順序を保存する
		listForm.setProcStepNo(strProcStepNo);
		listForm.setTempProcStepNo(strProcStepNo);

		// 工程順序コンボ作成
		listForm.setSeqCombo(pkgRdirectionCommonsLogic
				.createProcedureSetpNoCombobox(
					new BigDecimal(directionDivision), directionNo, true));

		return search(mapping, form, request, response);
	}

	/**
	 * 包装実績－配合一覧画面 ロジッククラス設定
	 * @param pkgRdirectionFormulaListLogic 包装実績－配合一覧画面 ロジッククラス
	 */
	public void setPkgDirectionFormulaListLogic(
			final PkgRdirectionFormulaListLogic pkgRdirectionFormulaListLogic) {
		this.pkgRdirectionFormulaListLogic = pkgRdirectionFormulaListLogic;
	}
}
