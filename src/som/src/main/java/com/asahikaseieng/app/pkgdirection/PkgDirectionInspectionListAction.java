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
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionHeaderDetail;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionInspectionList;

/**
 * 包装指図－検査一覧画面 Actionクラス
 * @author tosco
 */
public class PkgDirectionInspectionListAction extends
		AbstractPkgDirectionAction {

	/** 包装指図検査タブ ロジッククラス */
	private PkgDirectionInspectionListLogic pkgDirectionInspectionListLogic;

	/**
	 * コンストラクタ
	 */
	public PkgDirectionInspectionListAction() {
	}

	/**
	 * タブID（PkgDirectionConstクラスで定義)を各子クラスで実装
	 * @return タブID
	 */
	@Override
	protected String getTabId() {
		return PkgDirectionConst.INSPECTIONLIST;
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
			getLog().debug("▽▽▽　initProcess START　▽▽▽");
		}
		PkgDirectionInspectionListForm listForm = (PkgDirectionInspectionListForm) form;
		listForm.setDirtyFlg(null);

		/* 権限取得 */
		getControlAuthority(request, listForm, Constants.MENU_ID_PKGDIRECTION,
			Constants.TAB_ID_PKGDIRECTION_INSPECTION);

		if (!listForm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 工程順序コンボボックス設定
		BigDecimal division = new BigDecimal(listForm.getDirectionDivision());
		String directionNo = listForm.getDirectionNo();
		listForm.setProcedureStepNoCombo(pkgDirectionCommonsLogic
				.createProcedureSetpNoCombobox(division, directionNo, false));

		if (getLog().isDebugEnabled()) {
			getLog().debug("△△△　initProcess END　△△△");
		}

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
			getLog().debug("▽▽▽　search START　▽▽▽");
		}

		PkgDirectionInspectionListForm listForm = (PkgDirectionInspectionListForm) form;
		listForm.setSearchInspectionList(null);
		listForm.setSaveSearchInspectionList(null);
		listForm.setOperationCd(null);
		listForm.setOperationName(null);
		listForm.setDirtyFlg(null);
		listForm.setSelectKeyStepNo(listForm.getSelectProcedureStepNo());

		// 工程コード、工程名称を取得する
		String[] res = pkgDirectionInspectionListLogic
				.getOperationName(listForm);
		listForm.setOperationCd(res[0]);
		listForm.setOperationName(res[1]);

		// 包装指図検査情報検索
		List<PkgDirectionDirectionInspectionList> searchList = pkgDirectionInspectionListLogic
				.getSearchList(listForm);
		listForm.setSearchInspectionList(searchList);
		listForm.setSaveSearchInspectionList(searchList);

		if (getLog().isDebugEnabled()) {
			getLog().debug("△△△　search END　△△△");
		}

		return mapping.findForward("success");
	}

	/**
	 * 行追加処理
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
			getLog().debug("▽▽▽　addlist START　▽▽▽");
		}
		PkgDirectionInspectionListForm listForm = (PkgDirectionInspectionListForm) form;
		ActionMessages errors = pkgDirectionInspectionListLogic
				.checkForAddList(listForm);

		// エラーがあった場合
		if (!errors.isEmpty()) {
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		List<PkgDirectionDirectionInspectionList> list = listForm
				.getSearchInspectionList();
		if (list == null) {
			list = new ArrayList<PkgDirectionDirectionInspectionList>();
		}
		PkgDirectionDirectionInspectionList addBean = new PkgDirectionDirectionInspectionList();
		addBean.setDirectionDivision(new BigDecimal(listForm
				.getDirectionDivision())); // 指図区分
		addBean.setDirectionNo(listForm.getDirectionNo()); // 指図番号
		addBean.setStepNo(new BigDecimal(listForm.getSelectKeyStepNo())); // STEP_NO
		addBean.setStrLineNo(null); // LINE_NO

		int idx = 0;
		for (idx = 0; idx < list.size(); idx++) {
			PkgDirectionDirectionInspectionList bean = list.get(idx);

			// チェック有りの場合
			if (bean.isCheckFlg()) {

				// 新しい要素を追加
				list.add(idx, addBean);
				break;
			}
		}

		// 新規、チェックボックスにチェックなしの場合、最後尾に追加
		if (idx == list.size()) {
			addBean.setSeq(new BigDecimal(list.size() + 1));
			list.add(addBean);
		} else {
			// リストに挿入した場合は、以降Seqを振り直す
			for (int i = idx; i < list.size(); i++) {
				PkgDirectionDirectionInspectionList bean = list.get(i);
				bean.setCheckFlg(Boolean.FALSE);
				bean.setSeq(new BigDecimal(i + 1));
			}
		}
		listForm.setSearchInspectionList(list);

		if (getLog().isDebugEnabled()) {
			getLog().debug("△△△　addlist END　△△△");
		}
		return mapping.findForward("success");
	}

	/**
	 * 行削除処理
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
			getLog().debug("▽▽▽　dellist START　▽▽▽");
		}
		PkgDirectionInspectionListForm listForm = (PkgDirectionInspectionListForm) form;
		ActionMessages errors = pkgDirectionInspectionListLogic
				.checkForDelList(listForm);

		// エラーがあった場合
		if (!errors.isEmpty()) {
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		List<PkgDirectionDirectionInspectionList> list = listForm
				.getSearchInspectionList();
		for (int i = list.size() - 1; i >= 0; i--) {
			PkgDirectionDirectionInspectionList bean = list.get(i);

			// チェック無しの場合は削除対象外
			if (!bean.isCheckFlg()) {
				continue;
			}
			list.remove(i);
		}

		// Seqを振り直す
		for (int i = 0; i < list.size(); i++) {
			PkgDirectionDirectionInspectionList bean = list.get(i);
			bean.setSeq(new BigDecimal(i + 1));
		}
		listForm.setSearchInspectionList(list);

		if (getLog().isDebugEnabled()) {
			getLog().debug("△△△　dellist END　△△△");
		}
		return mapping.findForward("success");
	}

	/**
	 * 登録処理
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
			getLog().debug("▽▽▽　regist START　▽▽▽");
		}
		PkgDirectionInspectionListForm listForm = (PkgDirectionInspectionListForm) form;
		ActionMessages errors = pkgDirectionInspectionListLogic
				.checkForRegist(listForm);

		// エラーがあった場合
		if (!errors.isEmpty()) {
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		// 更新の必要なし
		if (listForm.getSaveSearchInspectionList() == null
				&& listForm.getSearchInspectionList().size() == 0) {
			return mapping.findForward("success");
		}

		try {
			// 包装指図-検査情報更新処理
			pkgDirectionInspectionListLogic.regist(listForm, getLoginInfo(
				request).getTantoCd());

			// 包装計画削除
			pkgDirectionCommonsLogic.deleteHosoKeikaku(listForm
					.getDirectionNo());

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

		if (getLog().isDebugEnabled()) {
			getLog().debug("△△△　regist END　△△△");
		}
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
			getLog().debug("▽▽▽　reSearch START　▽▽▽");
		}

		// 共通部分を再設定する
		AbstractPkgDirectionForm commonForm = (AbstractPkgDirectionForm) form;
		// クリアするので、渡された指図区分、指図番号を退避
		String strDirectionDivision = commonForm.getDirectionDivision();
		String strDirectionNo = commonForm.getDirectionNo();
		// クリア
		commonForm.clear();
		commonForm.setDirectionDivision(strDirectionDivision); // 指図区分
		commonForm.setDirectionNo(strDirectionNo); // 指図番号
		// タブIDを設定
		commonForm.setTabId(getTabId());

		// 共通情報再検索処理
		PkgDirectionDirectionHeaderDetail header = pkgDirectionCommonsLogic
				.getEntity(strDirectionDivision, strDirectionNo);
		setCommonHeaderInfo(commonForm, header, request);

		// 固有部分を再設定する
		PkgDirectionInspectionListForm listForm = (PkgDirectionInspectionListForm) form;
		listForm.setSearchInspectionList(null);
		listForm.setSaveSearchInspectionList(null);
		listForm.setOperationCd(null);
		listForm.setOperationName(null);
		listForm.setDirtyFlg(null);
		listForm.setSelectKeyStepNo(listForm.getSelectProcedureStepNo());

		// 工程コード、工程名称を取得する
		String[] res = pkgDirectionInspectionListLogic
				.getOperationName(listForm);
		listForm.setOperationCd(res[0]);
		listForm.setOperationName(res[1]);

		// 包装指図検査情報検索
		List<PkgDirectionDirectionInspectionList> searchList = pkgDirectionInspectionListLogic
				.getSearchList(listForm);
		listForm.setSearchInspectionList(searchList);
		listForm.setSaveSearchInspectionList(searchList);

		if (getLog().isDebugEnabled()) {
			getLog().debug("△△△　reSearch END　△△△");
		}
		return mapping.findForward("success");
	}

	/**
	 * 包装指図検査タブ ロジッククラスを設定します。
	 * @param pkgDirectionInspectionListLogic 包装指図検査タブ ロジッククラス
	 */
	public void setPkgDirectionInspectionListLogic(
			final PkgDirectionInspectionListLogic pkgDirectionInspectionListLogic) {
		this.pkgDirectionInspectionListLogic = pkgDirectionInspectionListLogic;
	}

}
