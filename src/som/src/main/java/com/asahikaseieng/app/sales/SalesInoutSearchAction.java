/*
 * Created on 2009/04/13
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
import com.asahikaseieng.dao.nonentity.salesinout.SalesInoutSearchList;
import com.asahikaseieng.dao.nonentity.salesinout.SalesInoutSearchListPagerCondition;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 受払検索(ポップアップ) Actionクラス.
 * @author t1344224
 */
public final class SalesInoutSearchAction extends AbstractSearchAction {

	/** 受払検索ロジッククラス */
	private SalesInoutSearchLogic salesInoutSearchLogic;

	/**
	 * コンストラクタ.
	 */
	public SalesInoutSearchAction() {
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
		return null;
	}

	/**
	 * 受払検索処理
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
		SalesInoutSearchForm frm = (SalesInoutSearchForm) form;

		// 検索に失敗しても明細が消されるように、ここでクリア
		frm.setSearchList(new ArrayList<SalesInoutSearchList>());

		// 検索条件を取得
		SalesInoutSearchListPagerCondition condition = (SalesInoutSearchListPagerCondition) frm
				.getPager().getPagerCondition();

		condition.setSrhItemCd(frm.getSrhItemCd());
		condition.setSrhSalesDate(frm.getSrhSalesDate());
		condition.setSrhVenderCd(frm.getSrhVenderCd());

		// 品目情報取得
		Item bean = salesInoutSearchLogic.getItemData(frm.getSrhItemCd());

		String exchange = new String();

		if (bean != null) {

			frm.setSrhItemName(bean.getItemName());
			frm.setSrhOtherCompanyCd1(bean.getOtherCompanyCd1());
			exchange = bean.getUnitOfOperationManagement();
		} else {
			exchange = null;

		}
		// 明細取得
		frm.setSearchList(salesInoutSearchLogic.getList(condition, frm
				.getSrhVenderCd(), exchange));

		/* 初期検索有り */
		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * 納入先検索ロジッククラスを設定します。
	 * @param salesInoutSearchLogic 納入先検索ロジッククラス
	 */
	public void setSalesInoutSearchLogic(
			final SalesInoutSearchLogic salesInoutSearchLogic) {
		this.salesInoutSearchLogic = salesInoutSearchLogic;
	}
}
