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
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionProcedureList;

/**
 * 製造実績－工程画面
 * @author tosco
 */
public class RdirectionProcedureListAction extends AbstractRdirectionAction {

	/** 製造実績 工程 ロジック */
	private RdirectionProcedureListLogic rdirectionProcedureListLogic;

	/**
	 * コンストラクタ
	 */
	public RdirectionProcedureListAction() {
	}

	/**
	 * タブID（MgrecipeConstクラスで定義)を各子クラスで実装
	 * @return タブID
	 */
	@Override
	protected String getTabId() {
		return RdirectionConst.PROCEDURELIST;
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

		RdirectionProcedureListForm frm = (RdirectionProcedureListForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_RDIRECTION,
			Constants.TAB_ID_RDIRECTION_PROCEDURE);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		List<RdirectionDirectionProcedureList> searchList = rdirectionProcedureListLogic
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

		RdirectionProcedureListForm frm = (RdirectionProcedureListForm) form;
		List<RdirectionDirectionProcedureList> searchProcList = frm
				.getSearchProcList();

		RdirectionDirectionProcedureList bean = new RdirectionDirectionProcedureList();
		bean.setDirectionDivision(new BigDecimal(frm.getDirectionDivision())); // 製造区分
		bean.setDirectionNo(frm.getDirectionNo()); // 製造番号
		bean.setFormulaMark("×"); // 配合

		// 要素がない場合
		if (searchProcList.size() == 0) {
			frm.getSearchProcList().add(bean);
		} else {
			// リスト追加ループ
			for (int i = 0; i < searchProcList.size(); i++) {
				RdirectionDirectionProcedureList directionBean = searchProcList
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
			RdirectionDirectionProcedureList directionBean = searchProcList
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

		RdirectionProcedureListForm frm = (RdirectionProcedureListForm) form;
		List<RdirectionDirectionProcedureList> searchProcList = frm
				.getSearchProcList();

		// 配合・検査存在チェック
		ActionMessages errors = rdirectionProcedureListLogic
				.checkForDelList(searchProcList);
		if (!errors.isEmpty()) {
			// エラーがあった場合
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		// 削除処理
		for (int i = searchProcList.size() - 1; i >= 0; i--) {
			RdirectionDirectionProcedureList directionBean = searchProcList
					.get(i);

			if (!directionBean.isCheckFlg()) {
				// チェック無は読み飛ばし
				continue;
			}

			searchProcList.remove(i);
		}

		// データ削除後の工程順序を設定
		for (int i = 0; i < searchProcList.size(); i++) {
			RdirectionDirectionProcedureList directionBean = searchProcList
					.get(i);
			directionBean.setSeq(new BigDecimal(i + 1));
		}

		return mapping.findForward("success");
	}

	/**
	 * 登録処理.
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

		RdirectionProcedureListForm frm = (RdirectionProcedureListForm) form;

		// 更新前チェックを行う
		ActionMessages errors = rdirectionProcedureListLogic.checkForRegist(frm
				.getSearchProcList());
		if (!errors.isEmpty()) {
			// エラーがあった場合
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		// 製造指図ヘッダ更新用データ作成
		RdirectionDirectionHeaderList header = RdirectionUtil
				.setDirectionHeader(frm, request);
		// 製造指図ヘッダ更新
		rdirectionCommonsLogic.updateDirectionHeader(header);

		// 製造指図プロシージャ更新処理
		rdirectionProcedureListLogic.regist(frm, getLoginInfo(request)
				.getTantoCd());

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

		RdirectionProcedureListForm frm = (RdirectionProcedureListForm) form;

		// 検索結果リストクリア
		frm
				.setSearchProcList(new ArrayList<RdirectionDirectionProcedureList>());

		return init(mapping, form, request, response);
	}

	/* -------------------- setter -------------------- */

	/**
	 * 製造実績 工程 ロジックを設定します。
	 * @param rdirectionProcedureListLogic 製造実績 工程 ロジック
	 */
	public void setRdirectionProcedureListLogic(
			final RdirectionProcedureListLogic rdirectionProcedureListLogic) {
		this.rdirectionProcedureListLogic = rdirectionProcedureListLogic;
	}

}
