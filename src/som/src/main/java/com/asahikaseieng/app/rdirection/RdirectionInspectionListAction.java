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
import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionHeaderList;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionInspectionList;

/**
 * 製造実績－検査タブ画面
 * @author tosco
 */
public class RdirectionInspectionListAction extends AbstractRdirectionAction {

	/** 製造実績検査タブ ロジッククラス */
	private RdirectionInspectionListLogic rdirectionInspectionListLogic;

	/**
	 * コンストラクタ
	 */
	public RdirectionInspectionListAction() {
	}

	/**
	 * タブID（DirectionConstクラスで定義)を各子クラスで実装
	 * @return タブID
	 */
	@Override
	protected String getTabId() {
		return RdirectionConst.INSPECTIONLIST;
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
	@Override
	protected ActionForward initProcess(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("▽▽▽　initProcess START　▽▽▽");
		}
		RdirectionInspectionListForm listForm = (RdirectionInspectionListForm) form;

		/* 権限取得 */
		getControlAuthority(request, listForm, Constants.MENU_ID_RDIRECTION,
			Constants.TAB_ID_RDIRECTION_INSPECTION);

		if (!listForm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 工程順序コンボボックス設定
		listForm
				.setProcedureStepNoCombo(rdirectionCommonsLogic
						.createProcedureSetpNoCombobox(listForm
								.getDirectionNo(), false));

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

		RdirectionInspectionListForm listForm = (RdirectionInspectionListForm) form;
		listForm.setSearchInspectionList(null);
		listForm.setSaveSearchInspectionList(null);
		listForm.setOperationCd(null);
		listForm.setOperationName(null);
		listForm.setDirtyFlg(null);
		listForm.setSelectKeyStepNo(listForm.getSelectProcedureStepNo());

		// 工程コード、工程名称を取得する
		String[] res = rdirectionInspectionListLogic.getOperationName(listForm);
		listForm.setOperationCd(res[0]);
		listForm.setOperationName(res[1]);

		// 製造実績-検査情報検索
		List<RdirectionDirectionInspectionList> searchList = rdirectionInspectionListLogic
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
		RdirectionInspectionListForm listForm = (RdirectionInspectionListForm) form;
		ActionMessages errors = rdirectionInspectionListLogic
				.checkForAddList(listForm);

		// エラーがあった場合
		if (!errors.isEmpty()) {
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		List<RdirectionDirectionInspectionList> list = listForm
				.getSearchInspectionList();
		if (list == null) {
			list = new ArrayList<RdirectionDirectionInspectionList>();
		}
		RdirectionDirectionInspectionList addBean = new RdirectionDirectionInspectionList();
		addBean.setDirectionDivision(new BigDecimal(listForm
				.getDirectionDivision())); // 指図区分
		addBean.setDirectionNo(listForm.getDirectionNo()); // 指図番号
		addBean.setStepNo(new BigDecimal(listForm.getSelectKeyStepNo())); // STEP_NO
		addBean.setStrLineNo(null); // LINE_NO
		addBean.setValueType("2");

		int idx = 0;
		for (idx = 0; idx < list.size(); idx++) {
			RdirectionDirectionInspectionList bean = list.get(idx);

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
				RdirectionDirectionInspectionList bean = list.get(i);
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
		RdirectionInspectionListForm listForm = (RdirectionInspectionListForm) form;
		ActionMessages errors = rdirectionInspectionListLogic
				.checkForDelList(listForm);

		// エラーがあった場合
		if (!errors.isEmpty()) {
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		List<RdirectionDirectionInspectionList> list = listForm
				.getSearchInspectionList();
		for (int i = list.size() - 1; i >= 0; i--) {
			RdirectionDirectionInspectionList bean = list.get(i);

			// チェック無しの場合は削除対象外
			if (!bean.isCheckFlg()) {
				continue;
			}
			list.remove(i);
		}

		// Seqを振り直す
		for (int i = 0; i < list.size(); i++) {
			RdirectionDirectionInspectionList bean = list.get(i);
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
		RdirectionInspectionListForm listForm = (RdirectionInspectionListForm) form;
		ActionMessages errors = rdirectionInspectionListLogic
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

		// 更新用データ作成
		RdirectionDirectionHeaderList header = RdirectionUtil
				.setDirectionHeader(listForm, request);
		// 製造実績ヘッダ更新
		rdirectionCommonsLogic.updateDirectionHeader(header);

		// 製造実績-検査情報更新処理
		rdirectionInspectionListLogic.regist(listForm, getLoginInfo(request)
				.getTantoCd());

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
		AbstractRdirectionForm commonForm = (AbstractRdirectionForm) form;
		// クリアするので、渡された指図番号を退避
		String strDirectionNo = commonForm.getDirectionNo();
		// クリア
		commonForm.clear();
		commonForm.setDirectionNo(strDirectionNo); // 指図番号
		// タブIDを設定
		commonForm.setTabId(getTabId());

		// 共通情報再検索処理
		RdirectionDirectionHeaderList header = rdirectionCommonsLogic
				.findByDirectionNo(strDirectionNo);
		setCommonHeaderInfo(commonForm, header, request);

		// 一覧部分を再設定する
		RdirectionInspectionListForm listForm = (RdirectionInspectionListForm) form;
		listForm.setSearchInspectionList(null);
		listForm.setSaveSearchInspectionList(null);
		listForm.setOperationCd(null);
		listForm.setOperationName(null);
		listForm.setDirtyFlg(null);
		listForm.setSelectKeyStepNo(listForm.getSelectProcedureStepNo());

		// 工程コード、工程名称を取得する
		String[] res = rdirectionInspectionListLogic.getOperationName(listForm);
		listForm.setOperationCd(res[0]);
		listForm.setOperationName(res[1]);

		// 製造実績-検査情報検索
		List<RdirectionDirectionInspectionList> searchList = rdirectionInspectionListLogic
				.getSearchList(listForm);
		listForm.setSearchInspectionList(searchList);
		listForm.setSaveSearchInspectionList(searchList);

		if (getLog().isDebugEnabled()) {
			getLog().debug("△△△　reSearch END　△△△");
		}
		return mapping.findForward("success");
	}

	/**
	 * 製造実績検査タブ ロジッククラスを設定します。
	 * @param rdirectionInspectionListLogic 製造実績検査タブ ロジッククラス
	 */
	public void setRdirectionInspectionListLogic(
			final RdirectionInspectionListLogic rdirectionInspectionListLogic) {
		this.rdirectionInspectionListLogic = rdirectionInspectionListLogic;
	}
}
