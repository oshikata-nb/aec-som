/*
 * Created on 2009/02/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.shipping;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.dao.nonentity.shipping.ShippingOrderSearchList;
import com.asahikaseieng.dao.nonentity.shipping.ShippingOrderSearchListPagerCondition;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 受注検索(ポップアップ) Actionクラス.
 * @author tosco
 */
public final class ShippingOrderSearchAction extends AbstractSearchAction {

	/** 受注検索ロジッククラス */
	private ShippingOrderSearchLogic shippingOrderSearchLogic;

	/**
	 * コンストラクタ.
	 */
	public ShippingOrderSearchAction() {
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
	 * 受注検索処理
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

		ShippingOrderSearchForm frm = (ShippingOrderSearchForm) form;

		//検索に失敗しても明細が消されるように、ここでクリア
		frm.setSearchList(new ArrayList<ShippingOrderSearchList>());

		//検索条件を取得
		ShippingOrderSearchListPagerCondition condition = (ShippingOrderSearchListPagerCondition) frm
				.getPager().getPagerCondition();
		//検索条件をセット
		condition.setSrhOrderNo(frm.getSrhOrderNo());
		condition.setSrhOrderDivision(frm.getSrhOrderDivision());
		condition.setSrhScheduledShippingDateFrom(frm.getSrhScheduledShippingDateFrom());
		condition.setSrhScheduledShippingDateTo(frm.getSrhScheduledShippingDateTo());
		condition.setSrhDeliveryCd(frm.getSrhDeliveryCd());
		condition.setSrhVenderCd(frm.getSrhVenderCd());
		condition.setSrhItemCd(frm.getSrhItemCd());
		condition.setSrhOtherCompanyCd1(frm.getSrhOtherCompanyCd1());

		//明細取得
		frm.setSearchList(shippingOrderSearchLogic.getList(condition));

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * 受注検索ロジッククラスを設定します。
	 * @param shippingOrderSearchLogic 受注検索ロジッククラス
	 */
	public void setShippingOrderSearchLogic(final ShippingOrderSearchLogic shippingOrderSearchLogic) {
		this.shippingOrderSearchLogic = shippingOrderSearchLogic;
	}
}
