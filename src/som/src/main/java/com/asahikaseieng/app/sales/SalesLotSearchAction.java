/*
 * Created on 2009/03/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.sales;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.dao.entity.master.item.Item;
import com.asahikaseieng.dao.nonentity.sales.SalesLotSearchList;
import com.asahikaseieng.dao.nonentity.sales.SalesLotSearchListPagerCondition;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * ロット検索(ポップアップ) Actionクラス.
 * @author tosco
 */
public final class SalesLotSearchAction extends AbstractSearchAction {

	/** ロット検索ロジッククラス */
	private SalesLotSearchLogic salesLotSearchLogic;

	/**
	 * コンストラクタ.
	 */
	public SalesLotSearchAction() {
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
		return null;
	}

	/**
	 * ロット検索処理
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

		SalesLotSearchForm frm = (SalesLotSearchForm) form;

		Item item = salesLotSearchLogic.getItem(frm.getSrhItemCd());
		//品目情報をセット
		frm.setSrhItemName(item.getItemName());
		frm.setSrhStyleOfPacking(item.getStyleOfPacking());
		frm.setSrhOtherCompanyCd1(item.getOtherCompanyCd1());
		frm.setSrhUnitDivision(item.getUnitOfOperationManagement());

		//検索に失敗しても明細が消されるように、ここでクリア
		frm.setSearchList(new ArrayList<SalesLotSearchList>());

		//検索条件を取得
		SalesLotSearchListPagerCondition condition = (SalesLotSearchListPagerCondition) frm
				.getPager().getPagerCondition();
		//検索条件をセット
		condition.setSrhItemCd(frm.getSrhItemCd());
		condition.setSrhLocationCd(frm.getSrhLocationCd());
		condition.setSrhLotNo(frm.getSrhLotNo());

		//明細取得
		frm.setSearchList(salesLotSearchLogic.getList(condition, frm.getSrhUnitDivision(),
			frm.getVenderDivision(), frm.getVenderCd()));

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * ロット検索ロジッククラスを設定します。
	 * @param salesLotSearchLogic ロット検索ロジッククラス
	 */
	public void setSalesLotSearchLogic(final SalesLotSearchLogic salesLotSearchLogic) {
		this.salesLotSearchLogic = salesLotSearchLogic;
	}
}
