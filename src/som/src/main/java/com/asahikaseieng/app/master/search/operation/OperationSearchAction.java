/*
 * Created on 2009/01/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.search.operation;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.comboboxes.SelectRecipeUse;
import com.asahikaseieng.dao.nonentity.master.search.operation.OperationSearchList;
import com.asahikaseieng.dao.nonentity.master.search.operation.OperationSearchListPagerCondition;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 工程マスタ検索(ポップアップ) Actionクラス.
 * @author tosco
 */
public final class OperationSearchAction extends AbstractSearchAction {

	/** 工程マスタ検索ロジッククラス */
	private OperationSearchLogic operationSearchLogic;

	/**
	 * コンストラクタ.
	 */
	public OperationSearchAction() {
		super();
	}

	/**
	 * 初期処理
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	protected ActionForward initImpl(final ActionMapping mapping,
			final AbstractSearchForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("initImpl");
		}

		/* 初期検索有り */
		OperationSearchForm frm = (OperationSearchForm) form;
		// 用途名称の設定
		frm.setRecipeUseName(SelectRecipeUse.getName(frm.getSrhRecipeUse()));
		return null;
	}

	/**
	 * 工程マスタ検索処理
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	protected ActionForward searchImpl(final ActionMapping mapping,
			final HttpServletRequest request, final AbstractSearchForm form)
			throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchImpl");
		}

		OperationSearchForm frm = (OperationSearchForm) form;

		/* 検索に失敗しても明細が消されるように、ここでクリア */
		frm.setSearchList(new ArrayList<OperationSearchList>());

		/* 検索条件を取得 */
		OperationSearchListPagerCondition condition = (OperationSearchListPagerCondition) frm
				.getPager().getPagerCondition();
		/* 検索条件をセット */
		condition.setOperationCd(frm.getSrhOperationCd());
		condition.setOperationName(frm.getSrhOperationName());
		condition.setRecipeUse(frm.getSrhRecipeUse());

		/* 明細取得 */
		frm.setSearchList(operationSearchLogic.getList(condition));

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * 工程マスタ検索ロジッククラスを設定します。
	 * @param operationSearchLogic 工程マスタ検索ロジッククラス
	 */
	public void setOperationSearchLogic(final OperationSearchLogic operationSearchLogic) {
		this.operationSearchLogic = operationSearchLogic;
	}
}
