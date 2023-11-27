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
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionProcedureList;
import com.asahikaseieng.exception.LogicExceptionEx;

/**
 * 製造指図－工程画面
 * @author tosco
 */
public class DirectionProcedureListAction extends AbstractDirectionAction {

	/** 製造指図 工程 ロジック */
	private DirectionProcedureListLogic directionProcedureListLogic;

	/**
	 * コンストラクタ
	 */
	public DirectionProcedureListAction() {
	}

	/**
	 * タブID（MgrecipeConstクラスで定義)を各子クラスで実装
	 * @return タブID
	 */
	@Override
	protected String getTabId() {
		return DirectionConst.PROCEDURELIST;
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

		DirectionProcedureListForm frm = (DirectionProcedureListForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_DIRECTION,
			Constants.TAB_ID_DIRECTION_PROCEDURE);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		List<DirectionDirectionProcedureList> searchList = directionProcedureListLogic
				.getSearchList(frm.getDirectionNo());

		// 検索結果設定
		frm.setSearchProcList(searchList);

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

		DirectionProcedureListForm frm = (DirectionProcedureListForm) form;
		List<DirectionDirectionProcedureList> searchProcList = frm
				.getSearchProcList();

		DirectionDirectionProcedureList bean = new DirectionDirectionProcedureList();
		bean.setDirectionDivision(new BigDecimal(frm.getDirectionDivision())); // 製造区分
		bean.setDirectionNo(frm.getDirectionNo()); // 製造番号
		bean.setFormulaMark("×"); // 配合

		// 要素がない場合
		if (searchProcList.size() == 0) {
			frm.getSearchProcList().add(bean);
		} else {
			// リスト追加ループ
			for (int i = 0; i < searchProcList.size(); i++) {
				DirectionDirectionProcedureList directionBean = searchProcList
						.get(i);
				// チェックボックスがチェックされていた場合
				if (directionBean.isCheckFlg()) {
					// 新しい要素を追加
					searchProcList.add(i, bean);
					break;
				}

				// チェックがない場合最後尾に追加
				if (i == searchProcList.size() - 1) {
					// 新しい要素を追加
					frm.getSearchProcList().add(bean);
					break;
				}
			}
		}

		// 追加データの後の工程順序を設定
		for (int i = 0; i < searchProcList.size(); i++) {
			DirectionDirectionProcedureList directionBean = searchProcList
					.get(i);
			directionBean.setCheckFlg(Boolean.FALSE);
			directionBean.setSeq(new BigDecimal(i + 1));
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

		DirectionProcedureListForm frm = (DirectionProcedureListForm) form;
		List<DirectionDirectionProcedureList> searchProcList = frm
				.getSearchProcList();

		// 配合・検査存在チェック
		ActionMessages errors = directionProcedureListLogic
				.checkForDelList(searchProcList);
		if (!errors.isEmpty()) {
			// エラーがあった場合
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		// 削除処理
		for (int i = searchProcList.size() - 1; i >= 0; i--) {
			DirectionDirectionProcedureList directionBean = searchProcList
					.get(i);

			if (!directionBean.isCheckFlg()) {
				// チェック無は読み飛ばし
				continue;
			}

			searchProcList.remove(i);
		}

		// 追加データの後の工程順序を設定
		for (int i = 0; i < searchProcList.size(); i++) {
			DirectionDirectionProcedureList directionBean = searchProcList
					.get(i);
			directionBean.setSeq(new BigDecimal(i + 1));
		}

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

		DirectionProcedureListForm frm = (DirectionProcedureListForm) form;

		// 更新前チェックを行う
		ActionMessages errors = directionProcedureListLogic.checkForRegist(frm
				.getSearchProcList());
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
			// 製造指図プロシージャ更新処理
			directionProcedureListLogic.regist(frm, header, getLoginInfo(
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

		return mapping.findForward("init");
	}

	/**
	 * 再表示処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward reInit(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		DirectionProcedureListForm frm = (DirectionProcedureListForm) form;

		// 検索結果リストクリア
		frm.setSearchProcList(new ArrayList<DirectionDirectionProcedureList>());

		return init(mapping, form, request, response);
	}

	/* -------------------- setter -------------------- */

	/**
	 * 製造指図 工程 ロジックを設定します。
	 * @param directionProcedureListLogic 製造指図 工程 ロジック
	 */
	public void setDirectionProcedureListLogic(
			final DirectionProcedureListLogic directionProcedureListLogic) {
		this.directionProcedureListLogic = directionProcedureListLogic;
	}

}
