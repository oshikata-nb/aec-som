/*
 * Created on 2009/02/12
 * AECS佐藤 ログインユーザの受注納入先区分を反映した納入先ポップアップの初期表示 2020/01/21
 * $copyright$
 *
 */
package com.asahikaseieng.app.sales;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.dao.nonentity.master.belongdetail.BelongDetailDao;
import com.asahikaseieng.dao.nonentity.sales.SalesDeliverySearchList;
import com.asahikaseieng.dao.nonentity.sales.SalesDeliverySearchListPagerCondition;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 受注検索(ポップアップ) Actionクラス.
 * @author tosco
 */
public final class SalesDeliverySearchAction extends AbstractSearchAction {

	/** 受注検索ロジッククラス */
	private SalesDeliverySearchLogic salesDeliverySearchLogic;
	
	/** 所属マスタ用Dao */
	private BelongDetailDao belongDetailDao;

	/**
	 * コンストラクタ.
	 */
	public SalesDeliverySearchAction() {
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
		SalesDeliverySearchForm frm = (SalesDeliverySearchForm) form;

		/* ログイン情報取得*/
		String tantoCd = getLoginInfo(request).getTantoCd();
		
		/* AECS佐藤 受注納入先区分を所属マスターから取得 2020/01/21 */
		String OrderDeliveryKbn;
		if(belongDetailDao.getOrderDeliveryKbn(tantoCd) == null){
			OrderDeliveryKbn = "2";
		}
		else{
			OrderDeliveryKbn = belongDetailDao.getOrderDeliveryKbn(tantoCd);
		}
			
		/** 検索入力：区分 */
		frm.setSrhDivision(OrderDeliveryKbn);

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

		SalesDeliverySearchForm frm = (SalesDeliverySearchForm) form;

		// 検索に失敗しても明細が消されるように、ここでクリア
		frm.setSearchList(new ArrayList<SalesDeliverySearchList>());

		// 検索条件を取得
		SalesDeliverySearchListPagerCondition condition = (SalesDeliverySearchListPagerCondition) frm
				.getPager().getPagerCondition();
		// 検索条件をセット
		condition.setSrhDeliveryCd(frm.getSrhDeliveryCd());
		condition.setSrhDivision(frm.getSrhDivision());

		// 明細取得
		frm.setSearchList(salesDeliverySearchLogic.getList(condition));

		return mapping.findForward("success");
	}

	/**
	 * コンボボックス変更処理
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward setDeliveryDivision(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		
		if (getLog().isDebugEnabled()) {
			getLog().debug("setDeliveryDivision");
		}
		return mapping.findForward("success");
	}
	
	
	
	
	/* -------------------- setter -------------------- */
	
	/**
	 * belongDetailDaoを設定します。
	 * @param belongDetailDao belongDetailDao
	 */
	public void setBelongDetailDao(final BelongDetailDao belongDetailDao) {
		this.belongDetailDao = belongDetailDao;
	}

	/**
	 * 納入先検索ロジッククラスを設定します。
	 * @param salesDeliverySearchLogic 納入先検索ロジッククラス
	 */
	public void setShippingOrderSearchLogic(
			final SalesDeliverySearchLogic salesDeliverySearchLogic) {
		this.salesDeliverySearchLogic = salesDeliverySearchLogic;
	}
}
