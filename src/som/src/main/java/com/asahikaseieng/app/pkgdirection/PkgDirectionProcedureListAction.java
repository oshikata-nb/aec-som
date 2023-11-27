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
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionProcedureList;

/**
 * 包装指図－工程一覧画面 Actionクラス
 * @author tosco
 */
public class PkgDirectionProcedureListAction extends AbstractPkgDirectionAction {

	/** 包装指図－製造指図プロシージャ検索 ロジッククラス */
	private PkgDirectionProcedureListLogic pkgDirectionProcedureListLogic;

	/**
	 * コンストラクタ
	 */
	public PkgDirectionProcedureListAction() {
	}

	/**
	 * タブID（PkgDirectionConstクラスで定義)を各子クラスで実装
	 * @return タブID
	 */
	@Override
	protected String getTabId() {
		return PkgDirectionConst.PROCEDURELIST;
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
		PkgDirectionProcedureListForm frm = (PkgDirectionProcedureListForm) form;
		frm.setDirtyFlg(null);

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_PKGDIRECTION,
			Constants.TAB_ID_PKGDIRECTION_PROCEDURE);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 包装指図－製造指図プロシージャ検索
		List<PkgDirectionDirectionProcedureList> searchList = pkgDirectionProcedureListLogic
				.getSearchList(frm);
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

		PkgDirectionProcedureListForm frm = (PkgDirectionProcedureListForm) form;
		List<PkgDirectionDirectionProcedureList> searchProcList = frm
				.getSearchProcList();

		PkgDirectionDirectionProcedureList bean = new PkgDirectionDirectionProcedureList();
		bean.setDirectionDivision(new BigDecimal(frm.getDirectionDivision()));
		bean.setDirectionNo(frm.getDirectionNo());
		bean.setFormulaMark("×"); // 配合
		bean.setStrStartDate(frm.getStrPlanedSdate());
		bean.setStartDate(frm.getPlanedSdate());
		bean.setStrEndDate(frm.getStrPlanedEdate());
		bean.setEndDate(frm.getPlanedEdate());

		// 要素がない場合
		if (searchProcList.size() == 0) {
			frm.getSearchProcList().add(bean);
		} else {
			// リスト追加ループ
			for (int i = 0; i < searchProcList.size(); i++) {
				PkgDirectionDirectionProcedureList directionBean = searchProcList
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
			PkgDirectionDirectionProcedureList directionBean = searchProcList
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

		PkgDirectionProcedureListForm frm = (PkgDirectionProcedureListForm) form;
		List<PkgDirectionDirectionProcedureList> searchProcList = frm
				.getSearchProcList();

		// 配合・検査存在チェック
		ActionMessages errors = pkgDirectionProcedureListLogic
				.checkForDelList(searchProcList);
		if (!errors.isEmpty()) {
			// エラーがあった場合
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		// 削除処理
		for (int i = searchProcList.size() - 1; i >= 0; i--) {
			PkgDirectionDirectionProcedureList directionBean = searchProcList
					.get(i);

			if (!directionBean.isCheckFlg()) {
				// チェック無は読み飛ばし
				continue;
			}

			searchProcList.remove(i);
		}

		// 追加データの後の工程順序を設定
		for (int i = 0; i < searchProcList.size(); i++) {
			PkgDirectionDirectionProcedureList directionBean = searchProcList
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

		PkgDirectionProcedureListForm frm = (PkgDirectionProcedureListForm) form;

		// 更新前チェックを行う
		ActionMessages errors = pkgDirectionProcedureListLogic
				.checkForRegist(frm);
		if (!errors.isEmpty()) {
			// エラーがあった場合
			super.saveErrors(request, errors);
			return mapping.findForward("error");
		}

		try {
			// 包装指図－製造指図プロシージャ更新処理
			pkgDirectionProcedureListLogic.regist(frm, getLoginInfo(request)
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

		PkgDirectionProcedureListForm frm = (PkgDirectionProcedureListForm) form;

		// 検索結果リストクリア
		frm
				.setSearchProcList(new ArrayList<PkgDirectionDirectionProcedureList>());

		return init(mapping, form, request, response);
	}

	/* -------------------- setter -------------------- */

	/**
	 * 包装指図－工程一覧画面 ロジック設定.
	 * @param pkgDirectionProcedureListLogic 包装指図－工程一覧画面 ロジック
	 */
	public void setPkgDirectionProcedureListLogic(
			final PkgDirectionProcedureListLogic pkgDirectionProcedureListLogic) {
		this.pkgDirectionProcedureListLogic = pkgDirectionProcedureListLogic;
	}

}
