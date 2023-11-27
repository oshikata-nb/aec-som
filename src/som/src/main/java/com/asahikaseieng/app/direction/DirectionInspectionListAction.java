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
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionHeaderList;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionInspectionList;
import com.asahikaseieng.exception.LogicExceptionEx;

/**
 * 製造指図－検査画面
 * @author tosco
 */
public class DirectionInspectionListAction extends AbstractDirectionAction {

	/** 製造指図検査タブ ロジッククラス */
	private DirectionInspectionListLogic directionInspectionListLogic;

	/**
	 * コンストラクタ
	 */
	public DirectionInspectionListAction() {
	}

	/**
	 * タブID（DirectionConstクラスで定義)を各子クラスで実装
	 * @return タブID
	 */
	@Override
	protected String getTabId() {
		return DirectionConst.INSPECTIONLIST;
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
		DirectionInspectionListForm listForm = (DirectionInspectionListForm) form;

		/* 権限取得 */
		getControlAuthority(request, listForm, Constants.MENU_ID_DIRECTION,
			Constants.TAB_ID_DIRECTION_INSPECTION);

		if (!listForm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 工程順序コンボボックス設定
		listForm
				.setProcedureStepNoCombo(directionCommonsLogic
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

		DirectionInspectionListForm listForm = (DirectionInspectionListForm) form;
		listForm.setSearchInspectionList(null);
		listForm.setSaveSearchInspectionList(null);
		listForm.setOperationCd(null);
		listForm.setOperationName(null);
		listForm.setDirtyFlg(null);
		listForm.setSelectKeyStepNo(listForm.getSelectProcedureStepNo());

		// 工程コード、工程名称を取得する
		String[] res = directionInspectionListLogic.getOperationName(listForm);
		listForm.setOperationCd(res[0]);
		listForm.setOperationName(res[1]);

		// 製造指図検査情報検索
		List<DirectionDirectionInspectionList> searchList = directionInspectionListLogic
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
		DirectionInspectionListForm listForm = (DirectionInspectionListForm) form;
		ActionMessages errors = directionInspectionListLogic
				.checkForAddList(listForm);

		// エラーがあった場合
		if (!errors.isEmpty()) {
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		List<DirectionDirectionInspectionList> list = listForm
				.getSearchInspectionList();
		if (list == null) {
			list = new ArrayList<DirectionDirectionInspectionList>();
		}
		DirectionDirectionInspectionList addBean = new DirectionDirectionInspectionList();
		addBean.setDirectionDivision(new BigDecimal(listForm
				.getDirectionDivision())); // 指図区分
		addBean.setDirectionNo(listForm.getDirectionNo()); // 指図番号
		addBean.setStepNo(new BigDecimal(listForm.getSelectKeyStepNo())); // STEP_NO
		addBean.setStrLineNo(null); // LINE_NO

		int idx = 0;
		for (idx = 0; idx < list.size(); idx++) {
			DirectionDirectionInspectionList bean = list.get(idx);

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
				DirectionDirectionInspectionList bean = list.get(i);
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
		DirectionInspectionListForm listForm = (DirectionInspectionListForm) form;
		ActionMessages errors = directionInspectionListLogic
				.checkForDelList(listForm);

		// エラーがあった場合
		if (!errors.isEmpty()) {
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		List<DirectionDirectionInspectionList> list = listForm
				.getSearchInspectionList();
		for (int i = list.size() - 1; i >= 0; i--) {
			DirectionDirectionInspectionList bean = list.get(i);

			// チェック無しの場合は削除対象外
			if (!bean.isCheckFlg()) {
				continue;
			}
			list.remove(i);
		}

		// Seqを振り直す
		for (int i = 0; i < list.size(); i++) {
			DirectionDirectionInspectionList bean = list.get(i);
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
		DirectionInspectionListForm listForm = (DirectionInspectionListForm) form;
		ActionMessages errors = directionInspectionListLogic
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
		DirectionDirectionHeaderList header = setDirectionHeader(listForm,
			request);
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

		// 製造指図検査情報更新処理
		try {
			directionInspectionListLogic.regist(listForm, header, getLoginInfo(
				request).getTantoCd());

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
		AbstractDirectionForm commonForm = (AbstractDirectionForm) form;
		// クリアするので、渡された指図番号を退避
		String strDirectionNo = commonForm.getDirectionNo();
		// クリア
		commonForm.clear();
		commonForm.setDirectionNo(strDirectionNo); // 指図番号
		// タブIDを設定
		commonForm.setTabId(getTabId());

		// 共通情報再検索処理
		DirectionDirectionHeaderList header = directionCommonsLogic
				.findByDirectionNo(strDirectionNo);
		setCommonHeaderInfo(commonForm, header, request);

		// 一覧部分を再設定する
		DirectionInspectionListForm listForm = (DirectionInspectionListForm) form;
		listForm.setSearchInspectionList(null);
		listForm.setSaveSearchInspectionList(null);
		listForm.setOperationCd(null);
		listForm.setOperationName(null);
		listForm.setDirtyFlg(null);
		listForm.setSelectKeyStepNo(listForm.getSelectProcedureStepNo());

		// 工程コード、工程名称を取得する
		String[] res = directionInspectionListLogic.getOperationName(listForm);
		listForm.setOperationCd(res[0]);
		listForm.setOperationName(res[1]);

		// 製造指図検査情報検索
		List<DirectionDirectionInspectionList> searchList = directionInspectionListLogic
				.getSearchList(listForm);
		listForm.setSearchInspectionList(searchList);
		listForm.setSaveSearchInspectionList(searchList);

		if (getLog().isDebugEnabled()) {
			getLog().debug("△△△　reSearch END　△△△");
		}
		return mapping.findForward("success");
	}

	/**
	 * 製造指図検査タブ ロジッククラスを設定します。
	 * @param directionInspectionListLogic 製造指図検査タブ ロジッククラス
	 */
	public void setDirectionInspectionListLogic(
			final DirectionInspectionListLogic directionInspectionListLogic) {
		this.directionInspectionListLogic = directionInspectionListLogic;
	}
}
