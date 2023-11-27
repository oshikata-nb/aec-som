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

import com.asahikaseieng.dao.entity.master.item.Item;
import com.asahikaseieng.dao.nonentity.shipping.ShippingLotSearchList;
import com.asahikaseieng.dao.nonentity.shipping.ShippingLotSearchListPagerCondition;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * ロット検索(ポップアップ) Actionクラス.
 * @author tosco
 */
public final class ShippingLotSearchAction extends AbstractSearchAction {

	/** ロット検索ロジッククラス */
	private ShippingLotSearchLogic shippingLotSearchLogic;

	/**
	 * コンストラクタ.
	 */
	public ShippingLotSearchAction() {
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

		ShippingLotSearchForm frm = (ShippingLotSearchForm) form;

		Item item = shippingLotSearchLogic.getItem(frm.getSrhItemCd());
		//品目情報をセット
		frm.setSrhItemName(item.getItemName());
		frm.setSrhStyleOfPacking(item.getStyleOfPacking());
		frm.setSrhOtherCompanyCd1(item.getOtherCompanyCd1());
		frm.setSrhUnitDivision(item.getUnitOfOperationManagement());

		//検索に失敗しても明細が消されるように、ここでクリア
		frm.setSearchList(new ArrayList<ShippingLotSearchList>());

		//検索条件を取得
		ShippingLotSearchListPagerCondition condition = (ShippingLotSearchListPagerCondition) frm
				.getPager().getPagerCondition();
		//検索条件をセット
		condition.setSrhItemCd(frm.getSrhItemCd());
		condition.setSrhLocationCd(frm.getSrhLocationCd());
		condition.setSrhLotNo(frm.getSrhLotNo());

		//明細取得
		frm.setSearchList(shippingLotSearchLogic.getList(condition, frm.getSrhUnitDivision(),
			frm.getVenderDivision(), frm.getVenderCd()));

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * ロット検索ロジッククラスを設定します。
	 * @param shippingLotSearchLogic ロット検索ロジッククラス
	 */
	public void setShippingLotSearchLogic(final ShippingLotSearchLogic shippingLotSearchLogic) {
		this.shippingLotSearchLogic = shippingLotSearchLogic;
	}
}
